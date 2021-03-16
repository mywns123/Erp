package Erp.dao;

import Erp.dto.Employee;

public interface EmployeeDetailDao {

	Employee selectEmployeeDetailByNo(Employee employee);

	int insertEmployeeDetail(Employee employee);
	int updateEmployeeDetail(Employee employee);
	int deleteEmployeeDetail(Employee employee);
}
