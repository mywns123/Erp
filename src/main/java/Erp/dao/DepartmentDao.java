package Erp.dao;

import java.util.List;

import Erp.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();

	Department selectDepartmentByNo(Department department);

	int insertDepartment(Department department);

	int updateDepartment(Department department);

	int deleteDepartment(int deptno);

}
