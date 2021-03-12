package Erp.service;

import java.util.List;

import Erp.dao.DepartmentDao;
import Erp.dao.impl.DepartmentDaoImpl;
import Erp.dto.Department;

public class DepartmentService {
	private DepartmentDao dao = DepartmentDaoImpl.getInstance();
	
	public List<Department> showDepartment(){
		return dao.selectDepartmentByAll();
		
	}
}
