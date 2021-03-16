package Erp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import Erp.dao.EmployeeDetailDao;
import Erp.database.JdbcConn;
import Erp.dto.Employee;
import Erp.dto.EmployeeDetail;

public class EmployeeDetailDaoImpl implements EmployeeDetailDao {
	private static final EmployeeDetailDaoImpl instance = new EmployeeDetailDaoImpl();

	public EmployeeDetailDaoImpl() {
	}

	public static EmployeeDetailDaoImpl getInstance() {
		return instance;
	}
	@Override
	public Employee selectEmployeeDetailByNo(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertEmployeeDetail(EmployeeDetail empDetail) {
		String sql = "insert into emp_detail(empNo,pic,gender,hiredate,pass)"
				+" values(?,?,?,?,?);";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, empDetail.getEmpNo());
			pstmt.setBytes(2, empDetail.getPic());
			pstmt.setBoolean(3, empDetail.isGender());
			pstmt.setTimestamp(4,new Timestamp(empDetail.getHiredate().getTime()));
			pstmt.setString(5, empDetail.getPass());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEmployeeDetail(EmployeeDetail empDetail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployeeDetail(EmployeeDetail empDetail) {
		// TODO Auto-generated method stub
		return 0;
	}

}
