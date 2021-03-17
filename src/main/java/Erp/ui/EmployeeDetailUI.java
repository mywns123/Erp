package Erp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Erp.dto.Employee;
import Erp.dto.EmployeeDetail;
import Erp.service.EmployeeDetailService;
import Erp.ui.content.EmployeeDetailPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class EmployeeDetailUI extends JFrame implements ActionListener {
	
	private JPanel contentPane;
	private JPanel pBtns;
	private EmployeeDetailPanel pItem;
	private JButton btnAdd;
	private EmployeeDetailService service;
	private JButton btnCancel;	
	
	
	public EmployeeDetailUI(boolean isBtns,EmployeeDetailService service) {
		this.service = service;
		initialize(isBtns);
	}
	
	private void initialize(boolean isBtns) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pItem = new EmployeeDetailPanel();
		contentPane.add(pItem, BorderLayout.CENTER);
		
		pBtns = new JPanel();
			
		contentPane.add(pBtns, BorderLayout.SOUTH);

		btnAdd = new JButton();
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		btnCancel = new JButton();
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);	
		
		if(isBtns) {			
			btnAdd.setText("추가");
			btnCancel.setText("취소");
		}
		else {
			btnAdd.setText("수정");
			btnCancel.setText("삭제");
		}
		
	}
	
	public void setEmpNO(Employee empNo) {
		pItem.setTfEmpno(empNo);
	}
	
	
	
	public void setDetailItem(EmployeeDetail empDetail) {
//		btnAdd.setText("수정");
		pItem.setItem(empDetail);
	}
	
	
	
	////////////////////////////////////////////////////////////////////////
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contentEquals("삭제")) {
			actionPerformedBtnDel(e);
		}
		if (e.getActionCommand().contentEquals("취소")) {
			actionPerformedBtnCancel(e);
		}
		if (e.getActionCommand().contentEquals("추가")) {
			actionPerformedBtnAdd(e);
		}
		if (e.getActionCommand().contentEquals("수정")) {
			actionPerformedBtnUpdate(e);
		}
	}
	
	private void actionPerformedBtnUpdate(ActionEvent e) {
		EmployeeDetail updateEmpDetail = pItem.getItem();
		service.modifyEmployeeDetail(updateEmpDetail);
		pItem.clearTf();
		JOptionPane.showMessageDialog(null, "수정완료");	
		dispose();
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		// 1.입력된
		// 2. service에 적용
		EmployeeDetail newEmpDetail = pItem.getItem();
		service.addEmployeeDetail(newEmpDetail);
		pItem.clearTf();
		JOptionPane.showMessageDialog(null, "추가완료");
		dispose();
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pItem.clearTf();
		if(btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}
	protected void actionPerformedBtnDel(ActionEvent e) {
		EmployeeDetail empDetail = pItem.getItem();
		service.removeEmployeeDetail(new Employee(empDetail.getEmpNo()));
		pItem.clearTf();		
		JOptionPane.showMessageDialog(null, "삭제완료");
	}
}
