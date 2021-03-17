package Erp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import Erp.dao.EmployeeDetailDao;
import Erp.database.JdbcConn;
import Erp.dto.Employee;
import Erp.dto.EmployeeDetail;
import Erp.ui.exception.SqlConstraintException;

public class EmployeeDetailDaoImpl implements EmployeeDetailDao {
	private static EmployeeDetailDaoImpl instance = new EmployeeDetailDaoImpl();

	public EmployeeDetailDaoImpl() {
	}

	public static EmployeeDetailDaoImpl getInstance() {
		if (instance == null) {
			instance = new EmployeeDetailDaoImpl();
		}
		return instance;
	}

	@Override
	public EmployeeDetail selectEmployeeDetailByNo(Employee employee) {
		String sql = "select empNo,pic,gender,hiredate,pass from emp_detail where empno = ?";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, employee.getEmpNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getEmployeeDetail(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private EmployeeDetail getEmployeeDetail(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		boolean gender = rs.getBoolean("gender");
		Date hiredate = rs.getTime("hiredate");
		byte[] pic = rs.getBytes("pic");
		return new EmployeeDetail(empNo, gender, hiredate, pic);
	}

	@Override
	public int insertEmployeeDetail(EmployeeDetail empDetail) {
		String sql = "insert into emp_detail(empNo,pic,gender,hiredate,pass)" + " values(?,?,?,?,password(?))";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, empDetail.getEmpNo());
			pstmt.setBytes(2, empDetail.getPic());
			pstmt.setBoolean(3, empDetail.isGender());
			// util.Date -> sql.Date로 변환
			pstmt.setTimestamp(4, new Timestamp(empDetail.getHiredate().getTime()));
			pstmt.setString(5, empDetail.getPass());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SqlConstraintException(e.getMessage(), e);
		}
	}

	@Override
	public int updateEmployeeDetail(EmployeeDetail empDetail) {
		String sql = "update emp_detail" + " set pic = ?, gender = ?, hiredate = ?, pass = ?" + " where empNo =?";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setBytes(1, empDetail.getPic());
			pstmt.setBoolean(2, empDetail.isGender());
			pstmt.setTimestamp(3, new Timestamp(empDetail.getHiredate().getTime()));
			pstmt.setString(4, empDetail.getPass());
			pstmt.setInt(5, empDetail.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new SqlConstraintException(e.getMessage(), e);
		}
	}

	@Override
	public int deleteEmployeeDetail(Employee employee) {
		String sql = "delete" + " from emp_detail " + " where empNo = ?;";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement std = con.prepareStatement(sql)) {
			std.setInt(1, employee.getEmpNo());
			return std.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
