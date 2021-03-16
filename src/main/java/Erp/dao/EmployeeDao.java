package Erp.dao;

import java.util.List;

import Erp.dto.Department;
import Erp.dto.Employee;
import Erp.dto.Title;

public interface EmployeeDao {
	List<Employee> selectEmployeeByAll();
	Employee selectEmployeeByNo(Employee employee);

	int insertEmployee(Employee employee);
	int updateEmployee(Employee employee);
	int deleteEmployee(Employee employee);
	
	List<Employee> selectEmployeeByTitle(Title title);
	List<Employee> selectEmployeeByDept(Department dept);

	
}
