package Erp.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Erp.dao.impl.TitleDaoImpl;
import Erp.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TitleDaoTest {

	private static TitleDao dao = TitleDaoImpl.getInstance();;

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test04SelectTitleByAll() {
		System.out.printf("%s()%n", "testSelectTitleByAll");
		List<Title> titleList = dao.selectTitleByAll();
		Assert.assertNotNull(titleList);
//		titleList.stream().forEach(System.out::println);
		for (Title t : titleList) {
			System.out.println(t);
		}
	}

	@Test
	public void test05SelectTitleByNo() {
		System.out.printf("%s()%n", "testSelectTitleByNo");
		Title title = new Title(5);
		Title searchTitle = dao.selectTitleByNo(title);
		Assert.assertNotNull(searchTitle);
		System.out.println(searchTitle);

	}

	@Test
	public void test01InsertTitle() {
		System.out.printf("%s()%n", "testInsertTitle");
		Title title = new Title(6, "인턴");
		int res = dao.insertTitle(title);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectTitleByNo(title));

	}

	@Test
	public void test02UpdateTitle() {
		System.out.printf("%s()%n", "testUpdateTitle");
		Title title = new Title(6, "계약직");
		int res = dao.updateTitle(title);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectTitleByNo(title));
	}

	@Test
	public void test03DeleteTitle() {
		System.out.printf("%s()%n", "testDeleteTitle");
		int res = dao.deleteTitle(6);
		Assert.assertEquals(1, res);
		dao.selectTitleByAll().stream().forEach(System.out::println);
	}

}
