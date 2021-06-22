package form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Chronological;
import model.Combined;
import model.Functional;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txName;
	private JTextField txMobile;
	private JTextField txPhone;
	private JTextField txAddress;
	private JTextField txMail;
	private JRadioButton rdbtnFunctional;
	private JRadioButton rdbtnChronological;
	private JRadioButton rdbtnCombined;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txName = new JTextField();
		txName.setBounds(12, 28, 114, 19);
		contentPane.add(txName);
		txName.setColumns(10);
		
		txMobile = new JTextField();
		txMobile.setBounds(12, 75, 114, 19);
		contentPane.add(txMobile);
		txMobile.setColumns(10);
		
		txPhone = new JTextField();
		txPhone.setBounds(12, 125, 114, 19);
		contentPane.add(txPhone);
		txPhone.setColumns(10);
		
		txAddress = new JTextField();
		txAddress.setBounds(12, 172, 114, 19);
		contentPane.add(txAddress);
		txAddress.setColumns(10);
		
		txMail = new JTextField();
		txMail.setBounds(12, 226, 114, 19);
		contentPane.add(txMail);
		txMail.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(12, 12, 70, 15);
		contentPane.add(lblName);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setBounds(12, 59, 70, 15);
		contentPane.add(lblMobile);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(12, 111, 70, 15);
		contentPane.add(lblPhone);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(12, 156, 70, 15);
		contentPane.add(lblAddress);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(12, 210, 70, 15);
		contentPane.add(lblMail);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(157, 12, 70, 15);
		contentPane.add(lblType);
		
		rdbtnFunctional = new JRadioButton("Functional");
		rdbtnFunctional.setBounds(157, 35, 149, 23);
		contentPane.add(rdbtnFunctional);
		
		rdbtnChronological = new JRadioButton("Chronological");
		rdbtnChronological.setBounds(157, 72, 149, 23);
		contentPane.add(rdbtnChronological);
		
		rdbtnCombined = new JRadioButton("Combined");
		rdbtnCombined.setBounds(157, 106, 149, 23);
		contentPane.add(rdbtnCombined);
		
		ButtonGroup b = new ButtonGroup();
		b.add(rdbtnFunctional);
		b.add(rdbtnChronological);
		b.add(rdbtnCombined);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txName.getText();
				String mobile = txMobile.getText();
				String phone = txPhone.getText();
				String address = txAddress.getText();
				String mail = txMail.getText();
				
				if(rdbtnFunctional.isSelected()){
					Functional cv = new Functional(name, mobile, phone, address, mail);
					CvForm.newInstance(cv);
				}else if(rdbtnChronological.isSelected()){
					Chronological cv = new Chronological(name, mobile, phone, address, mail);
					CvForm.newInstance(cv);
				}else if(rdbtnCombined.isSelected()){
					Combined cv = new Combined(name, mobile, phone, address, mail);
					CvForm.newInstance(cv);
				}else{
					JOptionPane.showMessageDialog(null, "Please select a cv type");
				}
			}
		});
		btnCreate.setBounds(157, 223, 117, 25);
		contentPane.add(btnCreate);
	}
}
