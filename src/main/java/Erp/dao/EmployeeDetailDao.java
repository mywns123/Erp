package Erp.dao;

import Erp.dto.Employee;
import Erp.dto.EmployeeDetail;

public interface EmployeeDetailDao {

	Employee selectEmployeeDetailByNo(Employee employee);

	int insertEmployeeDetail(EmployeeDetail empDetail);
	int updateEmployeeDetail(EmployeeDetail empDetail);
	int deleteEmployeeDetail(EmployeeDetail empDetail);
}
