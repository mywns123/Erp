package erp.dao;

import erp.dto.Employee;

public interface EmployeeDetailDao {

	Employee selectEmployeeDetailByNo(Employee employee);

	int insertEmployeeDetail(Employee employee);
	int updateEmployeeDetail(Employee employee);
	int deleteEmployeeDetail(Employee employee);
}
