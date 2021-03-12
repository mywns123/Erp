package Erp.dao;

import java.util.List;

import Erp.dto.Employee;

public interface EmployeeDao {
	List<Employee> selectEmployeeByAll();

	Employee selectEmployeeByNo(Employee employee);

	int insertEmployee(Employee employee);

	int updateEmployee(Employee employee);

	int deleteEmployee(int empNo);
}
