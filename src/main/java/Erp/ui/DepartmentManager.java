package Erp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import Erp.dto.Department;
import Erp.dto.Employee;
import Erp.service.DepartmentService;
import Erp.ui.content.DepartmentPanel;
import Erp.ui.content.AbstractCotentPanel;
import Erp.ui.exception.InvalidCheckException;
import Erp.ui.exception.NotSelectedException;
import Erp.ui.exception.SqlConstraintException;
import Erp.ui.list.AbstractCustomTablePanel;
import Erp.ui.list.DepartmentTablePanel;

@SuppressWarnings("serial")
public class DepartmentManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private DepartmentService service;
	private AbstractCotentPanel<Department> panel;
	private JButton btnCancel;
	private AbstractCustomTablePanel<Department> pList;
	
	
	public DepartmentManager() {
		service = new DepartmentService();
		initialize();
	}
	private void initialize() {
		setTitle("부서 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		panel = new DepartmentPanel();
		contentPane.add((DepartmentPanel)panel);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
		
		pList = new DepartmentTablePanel();
		((DepartmentTablePanel)pList).setService(service);
		pList.loadData();
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(popupMenuListner);
		popMenu.add(updateItem);

		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(popupMenuListner);
		popMenu.add(deleteItem);
		
		JMenuItem empListByDeptItem = new JMenuItem("동일 부서 사원 보기");
		empListByDeptItem.addActionListener(popupMenuListner);
		popMenu.add(empListByDeptItem);
		
		return popMenu;
	}
	
	
	ActionListener popupMenuListner = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(e.getActionCommand().equals("삭제")) {
					Department delDept = pList.getItem();
					service.removeDepartment(delDept);
					pList.loadData();
					JOptionPane.showMessageDialog(null, delDept + "삭제 되었습니다.");
				}
				if(e.getActionCommand().equals("수정")) {
					Department updateDept = pList.getItem();
					panel.setItem(updateDept);
					btnAdd.setText("수정");
				}

				if (e.getActionCommand().contentEquals("동일 부서 사원 보기")) {
				Department dept = pList.getItem();
				List<Employee> list = service.showEmployeeGroupByDepartment(dept);
				
				if (list == null) {
					JOptionPane.showMessageDialog(null, "해당 사원이 없음", "동일 부서 사원", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				List<String> strList = list
						.parallelStream()
						.map( s->{ return String.format("%s(%d)", s.getEmpName(), s.getEmpNo()); })
						.collect(Collectors.toList());
				
				JOptionPane.showMessageDialog(null, strList, "동일 부서 사원", JOptionPane.INFORMATION_MESSAGE);
				}
			}catch (NotSelectedException | SqlConstraintException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	};
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		try {
			if (e.getSource() == btnAdd) {
				if (e.getActionCommand().contentEquals("추가")) {
					actionPerformedBtnAdd(e);
				}else {
					actionPerformedBtnUpdate(e);
				}
			}
		}catch (InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
//			pContent.clearTf();
		}catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	private void actionPerformedBtnUpdate(ActionEvent e) {
		Department updateDept = panel.getItem();
		service.modifyDepartment(updateDept);
		pList.loadData();
		panel.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateDept.getDeptName() + "정보가 수정되었습니다.");
		
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department department = panel.getItem();
		service.addDepartment(department);
		pList.loadData();
		panel.clearTf();
		JOptionPane.showMessageDialog(null, department + " 추가했습니다.");
		
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		panel.clearTf();
	}
}
