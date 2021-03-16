package Erp.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.channels.FileLockInterruptionException;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Erp.dao.impl.EmployeeDetailDaoImpl;
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
		fail("Not yet implemented");
	}

	@Test
	public void test01InsertEmployeeDetail() {
		System.out.printf("%s()%n","test01InsertEmployeeDetail");
		EmployeeDetail empDetail = new EmployeeDetail(1003,true, new Date(),"1234",getImage("1.jpg"));
		int res = dao.insertEmployeeDetail(empDetail);
		
		Assert.assertEquals(1, res);
	}

	private byte[] getImage(String imgName) {
		byte[] pic = null;
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
		fail("Not yet implemented");
	}

	@Test
	public void test04DeleteEmployeeDetail() {
		fail("Not yet implemented");
	}

}
