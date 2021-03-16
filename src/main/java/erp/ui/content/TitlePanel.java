package erp.ui.content;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import erp.dto.Title;
import erp.ui.exception.InvalidCheckException;

@SuppressWarnings("serial")
public class TitlePanel extends AbstractCotentPanel<Title> {
	private JTextField tftNO;
	private JTextField tftName;

	public TitlePanel() {
		initialize();
	}

	public void initialize() {
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

	@Override
	public void clearTf() {
		tftNO.setText("");
		tftName.setText("");
	}

	@Override
	public void setItem(Title item) {
		tftNO.setText(item.gettNo()+"");
		tftName.setText(item.gettName()+"");		
	}

	@Override
	public Title getItem() {
		validCheck();
		int tNo = Integer.parseInt(tftNO.getText().trim());
		String tName = tftName.getText().trim();
		return new Title(tNo,tName);
	}

	@Override
	public void validCheck() {
		if(tftNO.getText().contentEquals("") || tftName.getText().equals("")) {
			throw new InvalidCheckException();
		}
		
	}
}
