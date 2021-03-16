package erp.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp.dao.impl.DepartmentDaoImpl;
import erp.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {

	private static DepartmentDao dao = DepartmentDaoImpl.getInstance();;
	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test04SelectDepartmentByAll() {
		System.out.printf("%s()%n", "testSelectDepartmentByAll");
		List <Department> dept = dao.selectDepartmentByAll();
		for(Department d : dept) {
			System.out.println(d);
		}
	}

	@Test
	public void test05SelectDepartmentByNo() {
		System.out.printf("%s()%n", "testSelectDepartmentByNo");
		Department dept = new Department(4);
		Department sdept = dao.selectDepartmentByNo(dept);
		Assert.assertNotNull(sdept);
		System.out.println(sdept);
	}

	@Test
	public void test01InsertDepartment() {
		System.out.printf("%s()%n", "testInsertDepartment");
		Department dept = new Department(5, "재무",11);
		int res = dao.insertDepartment(dept);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectDepartmentByNo(dept));
	}

	@Test
	public void test02UpdateDepartment() {
		System.out.printf("%s()%n", "testUpdateTitleDepartment");
		Department dept = new Department(5, "전산");
		int res = dao.updateDepartment(dept);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectDepartmentByNo(dept));
	}

	@Test
	public void test03DeleteDepartment() {
		System.out.printf("%s()%n", "testDeleteDepartment");
		int res = dao.deleteDepartment(5);
		Assert.assertEquals(1, res);
		dao.selectDepartmentByAll().stream().forEach(System.out::println);
	}
}
