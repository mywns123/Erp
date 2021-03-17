package Erp;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Erp.dto.Employee;
import Erp.dto.EmployeeDetail;
import Erp.service.EmployeeDetailService;
import Erp.service.EmployeeService;
import Erp.ui.content.EmployeeDetailPanel;
import Erp.ui.list.EmployeeTablePanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TestFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private EmployeeTablePanel table;
	private EmployeeDetailPanel panel;
	private JPanel panel_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestFrame() {
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		EmployeeService service = new EmployeeService();

		table = new EmployeeTablePanel();
		table.setService(service);
		table.loadData();
		contentPane.add(table);

		panel = new EmployeeDetailPanel();
		panel.setTfEmpno(new Employee(1003));
		contentPane.add(panel);

		panel_1 = new JPanel();
		contentPane.add(panel_1);

		btnNewButton = new JButton("가져오기");
		btnNewButton.addActionListener(this);
		panel_1.add(btnNewButton);

		btnNewButton_1 = new JButton("불러오기");
		btnNewButton_1.addActionListener(this);
		panel_1.add(btnNewButton_1);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton_1) {
			actionPerformedBtnNewButton_1(e);
		}
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
	}

	protected void actionPerformedBtnNewButton(ActionEvent e) {
		EmployeeDetail employeeDetail = panel.getItem();
		JOptionPane.showMessageDialog(null, employeeDetail);
	}

	protected void actionPerformedBtnNewButton_1(ActionEvent e) {
		EmployeeDetailService service = new EmployeeDetailService();
		EmployeeDetail empDetail = service.selectEmployeeDetailByEmpNo(new Employee(1003));
		panel.setItem(empDetail);
	}
	
}
