package Erp.ui.content;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Erp.dto.Title;
import Erp.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class TitlePanel extends JPanel {
	private JTextField tftNO;
	private JTextField tftName;

	public TitlePanel() {
		initialize();
	}

	private void initialize() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "직책 정보", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new GridLayout(0, 2, 10, 10));

		JLabel lbltNO = new JLabel("직책번호");
		lbltNO.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lbltNO);

		tftNO = new JTextField();
		add(tftNO);
		tftNO.setColumns(10);

		JLabel lbltName = new JLabel("직책명");
		lbltName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lbltName);

		tftName = new JTextField();
		tftName.setColumns(10);
		add(tftName);
	}

	public void setTitle(Title title) {
		tftNO.setText(title.gettNo()+"");
		tftName.setText(title.gettName()+"");
	}

	public Title getTitle() {
		validCheck();
		int tNo = Integer.parseInt(tftNO.getText().trim());
		String tName = tftName.getText().trim();
		return new Title(tNo,tName);
	}

	private void validCheck() {
		if(tftNO.getText().contentEquals("") || tftName.getText().equals("")) {
			throw new InvalidCheckException();
		}
		
	}

	public void clearTf() {
		tftNO.setText("");
		tftName.setText("");
	}
}
