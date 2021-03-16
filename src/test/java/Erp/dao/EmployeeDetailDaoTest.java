package Erp.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.channels.FileLockInterruptionException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Erp.dao.impl.EmployeeDetailDaoImpl;
import Erp.dto.Employee;
import Erp.dto.EmployeeDetail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDetailDaoTest {
	
	private EmployeeDetailDao dao= EmployeeDetailDaoImpl.getInstance(); 
		
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test02SelectEmployeeDetailByNo() {
		System.out.printf("%s()%n","test02SelectEmployeeDetailByNo");
		
		EmployeeDetail employeeDetail  = dao.selectEmployeeDetailByNo(new Employee(1003));
		Assert.assertNotNull(employeeDetail);
		
		System.out.println(employeeDetail);
	}

	@Test
	public void test01InsertEmployeeDetail() {
		System.out.printf("%s()%n","test01InsertEmployeeDetail");
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.getTime();
		
//		EmployeeDetail empDetail = new EmployeeDetail(1003,true, cal.getTitme(),"1234",getImage("1.jpg"));
		EmployeeDetail empDetail = new EmployeeDetail(1003,true, new Date(),"1234",getImage("1.jpg"));
		int res = dao.insertEmployeeDetail(empDetail);
		
		Assert.assertEquals(1, res);
	}

	private byte[] getImage(String imgName) {
		byte[] pic = null;
	     //     /images/imgName
		File file = new File(System.getProperty("user.dir") + File.separator + "images",imgName);
		try(InputStream is =new FileInputStream(file)){
			pic = new byte[is.available()];
			is.read(pic);			
		}catch (FileLockInterruptionException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pic;
	}

	@Test
	public void test03UpdateEmployeeDetail() {
		System.out.printf("%s()%n", "test03UpdateEmployeeDetail");
		
		EmployeeDetail empDetail = new EmployeeDetail(1003,false, new Date(),"1234",getImage("flower.jpg"));
		int res = dao.updateEmployeeDetail(empDetail);	
		
		Assert.assertEquals(1, res);

		System.out.println(dao.selectEmployeeDetailByNo(new Employee(1003)));
	}

	@Test
	public void test04DeleteEmployeeDetail() {
		System.out.printf("%s()%n", "test04DeleteEmployeeDetail");
		Employee employee = new Employee(1003);
		int res = dao.deleteEmployeeDetail(employee);
		
		Assert.assertEquals(1, res);
		
		EmployeeDetail employeeDetail = dao.selectEmployeeDetailByNo(new Employee(1003));
		Assert.assertNull(employeeDetail);
	}

}
