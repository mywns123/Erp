package Erp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLClientInfoException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.TitledBorder;

import Erp.dao.EmployeeDao;
import Erp.dao.impl.EmployeeImpl;
import Erp.dto.Employee;
import Erp.dto.Title;
import Erp.service.TitleService;
import Erp.ui.content.TitlePanel;
import Erp.ui.exception.InvalidCheckException;
import Erp.ui.exception.NotSelectedException;
import Erp.ui.exception.SqlConstraintExption;
import Erp.ui.list.TitleTablePanel;

@SuppressWarnings("serial")
public class TitleManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private TitlePanel pContent;
	private TitleTablePanel pList;
	private JButton btnAdd;
	private TitleService service;
	
	public TitleManager() {
		service = new TitleService();
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "\uC9C1\uCC45\uAD00\uB9AC", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pContent = new TitlePanel();
		contentPane.add(pContent);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		JButton btnClear = new JButton("취소");
		pBtn.add(btnClear);
		
		pList = new TitleTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(popMenuListener);
		popMenu.add(updateItem);
		
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(popMenuListener);
		popMenu.add(deleteItem);			
		
		
		JMenuItem empListByTitleItem = new JMenuItem("동일직책사원 보기");
		empListByTitleItem.addActionListener(popMenuListener);
		popMenu.add(empListByTitleItem);
		
		return popMenu;
	}
	
	
	ActionListener popMenuListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {			
				if(e.getActionCommand().equals("삭제")) {
					Title delTitle = pList.getItem();
					service.removeTitle(delTitle);
					pList.loadData();
					JOptionPane.showMessageDialog(null,delTitle +  "삭제되었습니다.");
				}else if(e.getActionCommand().equals("동일직책사원 보기")) {		
					int i = pList.getItem().gettNo();
					Employee  selEmp   = new Employee(i);
//					List<Employee> emplist = service.showEmployeeByTitle(new Title(i));	
					for(Employee e1 : emplist) {
						System.out.println(e1);
						JOptionPane.showMessageDialog(null, e1);
					}
					
				}
				
				
				
			}catch(NotSelectedException | SqlConstraintExption e2) {
				JOptionPane.showMessageDialog(null,e2.getMessage());			
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			
			if(e.getActionCommand().equals("수정")) {
				btnAdd.setText("수정");
			}
			
			
			
			
		}
	};
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		try {
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
		}catch(InvalidCheckException | SqlConstraintExption e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
//			pContent.clearTf();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		switch(btnAdd.getText()) {
		case "추가" :
			Title title = pContent.getTitle();		
			service.addTitle(title);
			pContent.clearTf();		
			JOptionPane.showMessageDialog(null, title + "추가했습니다.");			
			pList.loadData();			
		case "수정" :
			//pContent에서 수정된 list가져오기
			//update수행
			//pList갱신
			//pContent clearTf()호출				
			Title title1 = pContent.getTitle();
			service.updateTitle(title1);
			pContent.clearTf();
			pList.loadData();
			btnAdd.setText("추가");
			JOptionPane.showMessageDialog(null, title1 + "수정했습니다.");	
		}
		
	}
}
