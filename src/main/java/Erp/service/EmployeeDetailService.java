package Erp.service;

import Erp.dao.EmployeeDetailDao;
import Erp.dao.impl.EmployeeDetailDaoImpl;
import Erp.dto.Employee;
import Erp.dto.EmployeeDetail;

public class EmployeeDetailService {
	private EmployeeDetailDao empDetailDao = EmployeeDetailDaoImpl.getInstance();

	public EmployeeDetail selectEmployeeDetailByEmpNo(Employee employee) {
		return empDetailDao.selectEmployeeDetailByNo(employee);
	}

	public void addEmployeeDetail(EmployeeDetail empDetail) {
		empDetailDao.insertEmployeeDetail(empDetail);
	}

	public void modifyEmployeeDetail(EmployeeDetail empDetail) {
		empDetailDao.updateEmployeeDetail(empDetail);
	}

	public void removeEmployeeDetail(Employee employee) {
		empDetailDao.deleteEmployeeDetail(employee);
	}

}
