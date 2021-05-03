package Erp.ui;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import Erp.dto.Employee;
import Erp.dto.EmployeeDetail;
import Erp.service.EmployeeDetailService;
import Erp.service.EmployeeService;
import Erp.ui.content.AbstractCotentPanel;
import Erp.ui.content.EmployeePanel;
import Erp.ui.list.AbstractCustomTablePanel;
import Erp.ui.list.EmployeeTablePanel;

@SuppressWarnings("serial")
public class EmployeeManagerUI extends AbstractUI<Employee> {

	private EmployeeService service;
	private EmployeeDetailService detailService;

	public EmployeeManagerUI() {
		empListByTitleItem.setText(AbstractUI.EMP_MENU);
	}

	@Override
	protected void setService() {
		service = new EmployeeService();
		detailService = new EmployeeDetailService();
	}

	@Override
	protected void tableLoadData() {
		((EmployeeTablePanel) pList).setService(service);
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
		Employee emp = pList.getItem();
		EmployeeDetail empDetail = detailService.selectEmployeeDetailByEmpNo(emp);

		// 나중에 처리
		EmployeeDetailUI frame;
		if (empDetail == null) {
			frame = new EmployeeDetailUI(true, detailService);
		} else {
			frame = new EmployeeDetailUI(false, detailService);
			frame.setDetailItem(empDetail);
		}
		frame.setEmpNO(emp);
		frame.setVisible(true);
		/*
		 * JFrame subFrame = new JFrame("사원 세부 정보"); subFrame.setBounds(this.getWidth(),
		 * this.getHeight(), 450 ,500);
		 * 
		 * EmployeeDetailPanel subDetailPanel = new EmployeeDetailPanel();
		 * subDetailPanel.setItem(empDetail);
		 * 
		 * subFrame.add(subDetailPanel, BorderLayout.CENTER);
		 * 
		 * subFrame.setVisible(true);
		 */
//		throw new UnsupportedOperationException("제공되지 않음");		
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
