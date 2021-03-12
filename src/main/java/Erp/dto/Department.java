package Erp.dto;

public class Department {
	private int deptNo;
	private String deptName;
	private int floor;
	
//	private List<Employee> list;

	public Department(int deptNo, String deptName) {
		this.deptNo = deptNo;
		this.deptName = deptName;
	}


	public Department() {
		// TODO Auto-generated constructor stub
	}
	

	public Department(int deptNo) {
		this.deptNo = deptNo;
	}


	public Department(int deptNo, String deptName, int floor) {
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.floor = floor;
	}

	public int getDeptno() {
		return deptNo;
	}

	public void setDeptno(int deptno) {
		this.deptNo = deptno;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}


	@Override
	public String toString() {
		return String.format("Department [deptNo=%s, deptName=%s, floor=%s]", deptNo, deptName, floor);
	}

	

}
