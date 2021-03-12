package Erp.service;

import java.util.List;

import Erp.dao.EmployeeDao;
import Erp.dao.TitleDao;
import Erp.dao.impl.EmployeeImpl;
import Erp.dao.impl.TitleDaoImpl;
import Erp.dto.Employee;
import Erp.dto.Title;

public class TitleService {
	private TitleDao dao = TitleDaoImpl.getInstance();
	private EmployeeDao dao1 = EmployeeImpl.getInstance();
	
	public List<Title> showTitles(){
		return dao.selectTitleByAll();
		}
	public void addTitle(Title title) {
		dao.insertTitle(title);
	}
	
	public void removeTitle(Title title) {
		dao.deleteTitle(title.gettNo());
	}
	
	public void updateTitle(Title title) {
		dao.updateTitle(title);
	}
	
	public List<Employee> showEmployeeByTitle(Employee employee){
		return dao1.selectEmployeeByTitle(employee);
		}
	
	
	
}
