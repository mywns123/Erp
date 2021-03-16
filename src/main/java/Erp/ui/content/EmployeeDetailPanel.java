package Erp.ui.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import Erp.dto.EmployeeDetail;
import Erp.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class EmployeeDetailPanel extends AbstractCotentPanel<EmployeeDetail> implements ActionListener {
	private JPasswordField pfPass1;
	private JPasswordField pfPass2;
	private String imgPath = System.getProperty("user.dir") + File.separator + "images" + File.separator;
	private JLabel lblPic;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
	private JButton btnAddPic;
	private JLabel lblPassConfirm;
	
	
	public EmployeeDetailPanel() {		
		initialize();
		loadPic(null);
	}
	
	private void loadPic(String imgFilePath) {
		Image chanagImage = null;
		if(imgFilePath == null) {
		ImageIcon icon = new ImageIcon(imgPath + "1.jpg");
		chanagImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		}else {
			ImageIcon icon = new ImageIcon(imgFilePath);
			chanagImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		}
		ImageIcon  chanagIcon =  new ImageIcon(chanagImage);
		lblPic.setIcon(chanagIcon);		
	}
	
	private void initialize() {
		setBorder(new TitledBorder(null, "사원세부정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pTop = new JPanel();
		add(pTop, BorderLayout.NORTH);
		
		JPanel pPic = new JPanel();
		pTop.add(pPic);
		pPic.setLayout(new BorderLayout(0, 0));
		
		lblPic = new JLabel();		
		lblPic.setPreferredSize(new Dimension(100, 150));
		pPic.add(lblPic, BorderLayout.CENTER);
		
		btnAddPic = new JButton("사진추가");
		btnAddPic.addActionListener(this);
		pPic.add(btnAddPic, BorderLayout.SOUTH);
		
		JPanel pItem = new JPanel();
		add(pItem);
		pItem.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pContent = new JPanel();
		pItem.add(pContent);
		pContent.setLayout(new GridLayout(0, 2, 10, 0));
		
		JLabel lblHireDate = new JLabel("입사일");
		lblHireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblHireDate);
		
		JDateChooser dateHire = new JDateChooser(new Date());
		pContent.add(dateHire);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblGender);
		
		JPanel pGender = new JPanel();
		pContent.add(pGender);
		pGender.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JRadioButton rdbtnFemale = new JRadioButton("여");
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setSelected(true);
		pGender.add(rdbtnFemale);
		
		JRadioButton rdbtnMale = new JRadioButton("남");
		buttonGroup.add(rdbtnMale);
		pGender.add(rdbtnMale);
		
		JLabel lblPass1 = new JLabel("비밀번호");
		lblPass1.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblPass1);
		
		pfPass1 = new JPasswordField();
		pfPass1.getDocument().addDocumentListener(listener);
		pContent.add(pfPass1);
		
		JLabel lblPass2 = new JLabel("비밀번호 확인");
		lblPass2.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblPass2);
		
		pfPass2 = new JPasswordField();
		pfPass2.getDocument().addDocumentListener(listener);
		pContent.add(pfPass2);
		
		JPanel pSpace = new JPanel();
		pContent.add(pSpace);
		
		lblPassConfirm = new JLabel("New label");
		lblPassConfirm.setFont(new Font("굴림", Font.BOLD, 20));
		lblPassConfirm.setForeground(Color.RED);
		lblPassConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		pContent.add(lblPassConfirm);
	}

	@Override
	public void setItem(EmployeeDetail item) {
		
	}

	@Override
	public EmployeeDetail getItem() {
		return null;
	}

	@Override
	public void validCheck() {
		if (!lblPassConfirm.getText().equals("일치")) {
			throw new InvalidCheckException("비밀번호 불일치");
		}
	}

	@Override
	public void clearTf() {
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddPic) {
			actionPerformedBtnAddPic(e);
		}
	}
	protected void actionPerformedBtnAddPic(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg & png images", "jpg", "png", "gif");
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int res = chooser.showOpenDialog(null);
		if(res != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.","경고",JOptionPane.WARNING_MESSAGE);
		return;
		}
		String imgFilePath = chooser.getSelectedFile().getPath();
		loadPic(imgFilePath);
	}
	
	DocumentListener listener = new DocumentListener() {
		@Override
		public void removeUpdate(DocumentEvent e) {
			getMessage();
		}
		@Override
		public void insertUpdate(DocumentEvent e) {
			getMessage();
		}
		@Override
		public void changedUpdate(DocumentEvent e) {
			getMessage();
		}
		
		private void getMessage() {
			String pw1 = new String(pfPass1.getPassword());
			String pw2 = String.valueOf(pfPass2.getPassword());
			if (pw1.equals(pw2)) {
				lblPassConfirm.setText("일치");
			}else {
				lblPassConfirm.setText("불일치");
			}
		}
	};
}
