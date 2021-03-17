package Erp.service;

import java.util.List;

import Erp.dao.EmployeeDao;
import Erp.dao.TitleDao;
import Erp.dao.impl.EmployeeDaoImpl;
import Erp.dao.impl.TitleDaoImpl;
import Erp.dto.Employee;
import Erp.dto.Title;

public class TitleService {
	private TitleDao dao = TitleDaoImpl.getInstance();
	private EmployeeDao dao1 = EmployeeDaoImpl.getInstance();

	public List<Title> showTitles() {
		return dao.selectTitleByAll();
	}

	public void addTitle(Title title) {
		dao.insertTitle(title);
	}

	public void removeTitle(Title title) {
		dao.deleteTitle(title.gettNo());
	}

	public List<Employee> showEmployeeGroupByTitle(Title title) {

		return dao1.selectEmployeeByTitle(title);
	}

	public void modifyTitle(Title title) {
		dao.updateTitle(title);

	}

}
