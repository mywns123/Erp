package Erp.ui.list;

import javax.swing.SwingConstants;

import Erp.dto.Department;
import Erp.service.DepartmentService;

public class DepartmentTable extends AbstractCustomTablePanel<Department> {
	private DepartmentService service = new DepartmentService();
	
	@Override
	public void initList() {
		list =service.showDepartment();		
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2);
		// 컬럼 너비 조정
		setTableCellWidth(100, 100,100);

	}

	@Override
	public Object[] toArray(Department t) {
		return new Object[] {t.getDeptno(), t.getDeptName(), t.getFloor()};
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {"부서번호", "부서명", "위치"};
	}

}
