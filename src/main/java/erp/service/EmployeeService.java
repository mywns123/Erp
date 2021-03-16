package erp.service;

import java.util.List;

import erp.dao.DepartmentDao;
import erp.dao.EmployeeDao;
import erp.dao.TitleDao;
import erp.dao.impl.DepartmentDaoImpl;
import erp.dao.impl.EmployeeDaoImpl;
import erp.dao.impl.TitleDaoImpl;
import erp.dto.Department;
import erp.dto.Employee;
import erp.dto.Title;

public class EmployeeService {
	private DepartmentDao deptDao = DepartmentDaoImpl.getInstance();
	private TitleDao titleDao = TitleDaoImpl.getInstance();
	private EmployeeDao empDao = EmployeeDaoImpl.getInstance();

	public List<Department> showDeptList() {
		return deptDao.selectDepartmentByAll();
	}

	public List<Title> showTList() {
		return titleDao.selectTitleByAll();
	}

	public List<Employee> showEmpList() {
		return empDao.selectEmployeeByAll();
	}

	public List<Employee> showEmployeeByDept(Department dept) {
		return empDao.selectEmployeeByDept(dept);
	}

	public int removeEmployee(Employee employee) {
		return empDao.deleteEmployee(employee);
	}

	public int modifyEmployee(Employee employee) {
		return empDao.updateEmployee(employee);
	}

	public int addEmployee(Employee employee) {
		return empDao.insertEmployee(employee);
	}
}
