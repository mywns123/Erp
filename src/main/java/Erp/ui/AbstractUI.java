package Erp.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import Erp.ui.content.AbstractCotentPanel;
import Erp.ui.exception.InvalidCheckException;
import Erp.ui.exception.SqlConstraintException;
import Erp.ui.list.AbstractCustomTablePanel;

@SuppressWarnings("serial")
public abstract class AbstractUI<T> extends JFrame implements ActionListener {

	private JPanel contentPane;
	protected JButton btnAdd;
	private JButton btnClear;

	protected AbstractCotentPanel<T> pContent;
	protected AbstractCustomTablePanel<T> pList;
	protected JMenuItem empListByTitleItem;

	protected static final String TITLE_MENU = "동일 직책 사원 보기";
	protected static final String DEPT_MENU = "동일 부서 사원 보기";
	protected static final String EMP_MENU = "사원 세부정보 보기";

	public AbstractUI() {
		setService(); // service연결
		initialize();
		tableLoadData(); // component load data
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pContent = creatContentPanel();
		contentPane.add(pContent);

		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);

		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtn.add(btnClear);

		pList = creatTablePanel();
		contentPane.add(pList);

		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(this);
		popMenu.add(updateItem);

		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(this);
		popMenu.add(deleteItem);

		empListByTitleItem = new JMenuItem("동일 직책 사원 보기");
		empListByTitleItem.addActionListener(this);
		popMenu.add(empListByTitleItem);

		return popMenu;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() instanceof JMenuItem) {
				if (e.getActionCommand().equals("삭제")) {
					actionPerformdMenuDelete();
				}
				if (e.getActionCommand().equals("수정")) {
					actionPerformdMenuUpdate();
				}
				if (e.getActionCommand().contentEquals(AbstractUI.TITLE_MENU)
						|| e.getActionCommand().contentEquals(AbstractUI.DEPT_MENU)
						|| e.getActionCommand().contentEquals(AbstractUI.EMP_MENU)) {
					actionPerformdMenuGubun();
				}
			} else {
				if (e.getSource() == btnClear) {
					actionPerformedBtnClear(e);
				}
				if (e.getSource() == btnAdd) {
					if (e.getActionCommand().contentEquals("추가")) {
						actionPerformedBtnAdd(e);
					} else {
						actionPerformedBtnUpdate(e);
					}
				}
			}
		} catch (InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected abstract void setService();

	protected abstract void tableLoadData();

	protected abstract AbstractCotentPanel<T> creatContentPanel();

	protected abstract AbstractCustomTablePanel<T> creatTablePanel();

	protected abstract void actionPerformdMenuGubun();

	protected abstract void actionPerformdMenuUpdate();

	protected abstract void actionPerformdMenuDelete();

	protected abstract void actionPerformedBtnUpdate(ActionEvent e);

	protected abstract void actionPerformedBtnAdd(ActionEvent e);

	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.clearTf();

		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}

}
