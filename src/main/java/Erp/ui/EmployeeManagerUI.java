package Erp.ui;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import Erp.dto.Employee;
import Erp.service.EmployeeService;
import Erp.ui.content.AbstractCotentPanel;
import Erp.ui.content.EmployeePanel;
import Erp.ui.list.AbstractCustomTablePanel;
import Erp.ui.list.EmployeeTablePanel;

@SuppressWarnings("serial")
public class EmployeeManagerUI extends AbstractManagerUI<Employee> {

	private EmployeeService service;
	
	public EmployeeManagerUI() {
		empListByTitleItem.setText(AbstractManagerUI.EMP_MENU);
	}

	@Override
	protected void setService() {
		service = new EmployeeService();		
	}

	@Override
	protected void tableLoadData() {
		((EmployeeTablePanel)pList).setService(service);
		pList.loadData();		
	}

	@Override
	protected AbstractCotentPanel<Employee> creatContentPanel() {
		EmployeePanel empPanel = new EmployeePanel();
		empPanel.setService(service);
		return empPanel;
	}

	@Override
	protected AbstractCustomTablePanel<Employee> creatTablePanel() {
		return new EmployeeTablePanel();
	}

	@Override
	protected void actionPerformdMenuGubun() {
		throw new UnsupportedOperationException("제공되지 않음");		
	}

	@Override
	protected void actionPerformdMenuUpdate() {
		Employee updateEmp = pList.getItem();
		pContent.setItem(updateEmp);
		btnAdd.setText("수정");		
	}

	@Override
	protected void actionPerformdMenuDelete() {
		Employee delEmp = pList.getItem();
		service.removeEmployee(delEmp);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delEmp + "삭제 되었습니다.");
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Employee updateEmp = pContent.getItem();
		service.modifyEmployee(updateEmp);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateEmp.getEmpName() + "정보가 수정되었습니다.");
		
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee employee = pContent.getItem();
		service.addEmployee(employee);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, employee.getEmpName() + " 추가했습니다.");
		
	}

}
