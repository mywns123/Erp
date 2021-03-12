package Erp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLClientInfoException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.TitledBorder;

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
		
		JButton btnCleat = new JButton("취소");
		pBtn.add(btnCleat);
		
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
		popMenu.add(updateItem);
		
		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(popMenuListener);
		popMenu.add(deleteItem);			
		
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
				}
			}catch(NotSelectedException | SqlConstraintExption e2) {
				JOptionPane.showMessageDialog(null,e2.getMessage());			
			}catch(Exception e2) {
				e2.printStackTrace();
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
		Title title = pContent.getTitle();
		
		
		service.addTitle(title);
		pContent.clearTf();		
		JOptionPane.showMessageDialog(null, title + "추가했습니다.");
		
		pList.loadData();
	}
}
