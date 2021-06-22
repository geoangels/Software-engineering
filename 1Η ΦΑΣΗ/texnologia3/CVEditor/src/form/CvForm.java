package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.BasicCv;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CvForm extends JFrame {
	
	private BasicCv cv;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CvForm frame = new CvForm();
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
	public CvForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 327, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Proffessional Profile");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListableForm.newinstance(cv.getSections().get(0));
			}
		});
		btnNewButton.setBounds(12, 12, 301, 25);
		contentPane.add(btnNewButton);
		
		JButton btnSkills = new JButton("Skills");
		btnSkills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListableForm.newinstance(cv.getSections().get(1));
			}
		});
		btnSkills.setBounds(12, 49, 301, 25);
		contentPane.add(btnSkills);
		
		JButton btnExperience = new JButton("Experience");
		btnExperience.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListableForm.newinstance(cv.getSections().get(2));
			}
		});
		btnExperience.setBounds(12, 86, 301, 25);
		contentPane.add(btnExperience);
		
		JButton btnEducation = new JButton("Education");
		btnEducation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListableForm.newinstance(cv.getSections().get(3));
			}
		});
		btnEducation.setBounds(12, 123, 301, 25);
		contentPane.add(btnEducation);
		
		JButton btnCourses = new JButton("Courses");
		btnCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListableForm.newinstance(cv.getSections().get(4));
			}
		});
		btnCourses.setBounds(12, 160, 301, 25);
		contentPane.add(btnCourses);
		
		JButton btnAdditional = new JButton("Additional");
		btnAdditional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListableForm.newinstance(cv.getSections().get(5));
			}
		});
		btnAdditional.setBounds(12, 197, 301, 25);
		contentPane.add(btnAdditional);
		
		JButton btnInterests = new JButton("Interests");
		btnInterests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListableForm.newinstance(cv.getSections().get(6));
			}
		});
		btnInterests.setBounds(12, 236, 301, 25);
		contentPane.add(btnInterests);
	}
	
	public static void newInstance(BasicCv cv){
		CvForm form = new CvForm();
		form.cv = cv;
		form.show();
	}

}
