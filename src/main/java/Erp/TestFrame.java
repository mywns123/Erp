package Erp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Erp.dto.Department;
import Erp.dto.Employee;
import Erp.dto.Title;
import Erp.service.EmployeeService;
import Erp.ui.content.EmployeePanel;
import Erp.ui.exception.InvalidCheckException;
import Erp.ui.exception.SqlConstraintException;
import Erp.ui.list.EmployeeTablePanel;

@SuppressWarnings("serial")
public class TestFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private JButton btnSet;
	private EmployeePanel pEmpItem;
	private JButton btnCancel;
	private EmployeeTablePanel table;
	
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
		setBounds(100, 100, 450, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		EmployeeService service = new EmployeeService();
		
		
		pEmpItem = new EmployeePanel();
		pEmpItem.setService(service);
		contentPane.add(pEmpItem);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnSet = new JButton("Set");
		btnSet.addActionListener(this);
		pBtn.add(btnSet);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
		
		table = new EmployeeTablePanel();
		table.setService(service);
		table.loadData();
		contentPane.add(table);
	}

	public void actionPerformed(ActionEvent e) {
	
		try {
		if (e.getSource() == btnSet) {
			actionPerformedBtnSet(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		}catch(InvalidCheckException | SqlConstraintException e1){
			JOptionPane.showMessageDialog(null, e1.getMessage());
			
		}catch(Exception e1){
			e1.printStackTrace();			
		}
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee emp =pEmpItem.getItem();
		String message = String.format(
			"empNO %d%n, empName %s%n, title(%d)%n,	dept(%d)%n, manager(%s)%n, salart(%s)"
				, emp.getEmpNo(), emp.getEmpName(), emp.getTitle(),emp.getDept(), emp.getManager(), emp.getSalary());
			JOptionPane.showMessageDialog(null, message);	
	}
	protected void actionPerformedBtnSet(ActionEvent e) {
	Employee emp = new Employee(1003, "조민희", new Title(3), new Employee(4377), 3000000,new  Department(2));
	pEmpItem.setItem(emp);
		
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pEmpItem.clearTf();
	}
}
