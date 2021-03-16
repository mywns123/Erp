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
import Erp.dto.Title;
import Erp.service.DepartmentService;
import Erp.service.TitleService;
import Erp.ui.content.DepartmentPanel;
import Erp.ui.content.AbstractCotentPanel;
import Erp.ui.content.TitlePanel;
import Erp.ui.exception.InvalidCheckException;
import Erp.ui.exception.NotSelectedException;
import Erp.ui.exception.SqlConstraintException;
import Erp.ui.list.AbstractCustomTablePanel;
import Erp.ui.list.DepartmentTablePanel;
import Erp.ui.list.TitleTablePanel;

@SuppressWarnings("serial")
public class TitleManager extends JFrame implements ActionListener  {
	
	private JPanel contentPane;
	private JButton btnAdd;	
	private TitlePanel panel;
	private JButton btnCancel;
	private TitleTablePanel pList;
	
	private TitleService service;
	
	public TitleManager() {
	
		service = new TitleService();
		initialize();	
	}
	
	private void initialize() {
		setTitle("직책 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		panel = new TitlePanel();
		contentPane.add(panel);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
		
		pList = new TitleTablePanel();
		((TitleTablePanel)pList).setService(service);
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
		
		JMenuItem empListByDeptItem = new JMenuItem("동일직책 사원 보기");
//		empListByitem.addActionListener(popupMenuListner);
		popMenu.add(empListByDeptItem);
		
		return popMenu;
	}
	ActionListener popupMenuListner = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		try {
			if(e.getActionCommand().equals("삭제")) {
				Title title = pList.getItem();
				service.removeTitle(title);
				pList.loadData();
				JOptionPane.showMessageDialog(null, title + "삭제 되었습니다.");
			}
			if(e.getActionCommand().equals("수정")) {
				Title updateTitle = pList.getItem();
				panel.setItem(updateTitle);
				btnAdd.setText("수정");
			}

			if (e.getActionCommand().contentEquals("동일 직책 사원 보기")) {
			Title title = pList.getItem();
			List<Employee> list = service.showEmployeeGroupByTitle(title);
			
			if (list == null) {
				JOptionPane.showMessageDialog(null, "해당 사원이 없음", "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			List<String> strList = list
					.parallelStream()
					.map( s->{ return String.format("%s(%d)", s.getEmpName(), s.getEmpNo()); })
					.collect(Collectors.toList());
			
			JOptionPane.showMessageDialog(null, strList, "동일 직책 사원", JOptionPane.INFORMATION_MESSAGE);
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
		Title updateTitle = panel.getItem();
		service.modifyTitle(updateTitle);
		pList.loadData();
		panel.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateTitle.gettName() + "정보가 수정되었습니다.");
		
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Title title = panel.getItem();
		service.addTitle(title);
		pList.loadData();
		panel.clearTf();
		JOptionPane.showMessageDialog(null, title + " 추가했습니다.");
		
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		panel.clearTf();
	}
}

	

