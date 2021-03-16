package Erp.dto;

import java.util.Arrays;
import java.util.Date;

public class EmployeeDetail {
	private int empNo;
	private boolean gender;
	private Date hiredate;
	private String pass;
	private byte[] pic;
	
	public EmployeeDetail() {
	}
	
	public EmployeeDetail(int empNo) {
		this.empNo = empNo;
	}
	
	public EmployeeDetail(int empNo, boolean gender, Date hiredate, byte[] pic) {
		this.empNo = empNo;
		this.gender = gender;
		this.hiredate = hiredate;
		this.pic = pic;
	}
	
	public int getEmpNo() {
		return empNo;
	}
	
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	
	public boolean isGender() {
		return gender;
	}
	
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
	public Date getHiredate() {
		return hiredate;
	}
	
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	
	public byte[] getPic() {
		return pic;
	}
	
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Override
	public String toString() {
		return String.format("EmpDetail [empNo=%s, gender=%s, hiredate=%s, pic=%s]", empNo, gender, hiredate,
				Arrays.toString(pic));
	}
	
}
