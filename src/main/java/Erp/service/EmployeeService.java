package Erp.service;

import java.util.List;

import Erp.dao.DepartmentDao;
import Erp.dao.EmployeeDao;
import Erp.dao.TitleDao;
import Erp.dao.impl.DepartmentDaoImpl;
import Erp.dao.impl.EmployeeDaoImpl;
import Erp.dao.impl.TitleDaoImpl;
import Erp.dto.Department;
import Erp.dto.Employee;
import Erp.dto.Title;

public class EmployeeService {
 private DepartmentDao deptDao = DepartmentDaoImpl.getInstance();
 private TitleDao titleDao = TitleDaoImpl.getInstance();
 private EmployeeDao empDao = EmployeeDaoImpl.getInstance();
 
 public List<Department> showDeptList(){
	 return deptDao.selectDepartmentByAll();
 }
 
 public List<Title> showTList(){
	 return titleDao.selectTitleByAll();
 }
 
 public List<Employee> showEmpList(){
	 return empDao.selectEmployeeByAll();
 }

public List<Employee> showEmployeeByDept(Department dept) {
	return empDao.selectEmployeeByDept(dept);
}
 
 
 
}
