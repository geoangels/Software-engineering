package form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.BasicCv;
import model.ImportHelper;
import model.ImportLatexhelper;

public class AllCvForm extends JFrame {
	
	private ArrayList<BasicCv> cvs = new ArrayList<>();	

	private JPanel contentPane;

	private JList list;
	private JList list_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllCvForm frame = new AllCvForm();
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
	public AllCvForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreatedCv = new JLabel("All Cvs");
		lblCreatedCv.setBounds(12, 12, 89, 15);
		contentPane.add(lblCreatedCv);
		
		list = new JList();
		list.setBounds(12, 27, 218, 252);
		contentPane.add(list);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openMainWindow();
			}
		});
		btnNew.setBounds(248, 211, 117, 25);
		contentPane.add(btnNew);
		
		list_1 = new JList();
		list_1.setBounds(377, 27, 218, 252);
		contentPane.add(list_1);
		
		JLabel lblMergeToLeft = new JLabel("Merge to left");
		lblMergeToLeft.setBounds(380, 12, 117, 15);
		contentPane.add(lblMergeToLeft);
		
		JButton btnMerge = new JButton("<<Merge");
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openDifferencesWindow();
			}
		});
		btnMerge.setBounds(248, 103, 117, 25);
		contentPane.add(btnMerge);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectFile();
			}
		});
		btnOpen.setBounds(248, 248, 117, 25);
		contentPane.add(btnOpen);
		
		JButton button = new JButton("<Edit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = list.getSelectedIndex();
				if(i>=0){
					CvForm.newInstance(cvs.get(i));
				}
			}
		});
		button.setBounds(248, 171, 117, 25);
		contentPane.add(button);
	}
	
	
	protected void selectFile() {
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    String path = selectedFile.getAbsolutePath();
		    if(path.contains(".txt")){
		    	BasicCv cv = ImportHelper.readText(path);
		    	cvs.add(cv);
		    	refresh();
		    }else if(path.contains(".tex")){
		    	BasicCv cv = ImportLatexhelper.readLatex(path);
		    	cvs.add(cv);
		    	refresh();
		    }else{
		    	JOptionPane.showMessageDialog(null, "Not supported format");
		    }
		}
	}

	protected void openDifferencesWindow() {
		int i = list.getSelectedIndex();
		int j = list_1.getSelectedIndex();
		
		if(i>=0 && j>=0 && i!=j){
			BasicCv difs = cvs.get(i).differencesCV(cvs.get(j));
			DifferencesForm.newInstance(cvs.get(i), difs, this);
		}
		
	}

	protected void openMainWindow() {
		MainWindow.newInstance(this);
		
	}

	public void refresh(){
		
		DefaultListModel<BasicCv> model = new DefaultListModel<>();

		for ( BasicCv cv: cvs){
			model.addElement( cv );
		}
		list.setModel(model);
		list_1.setModel(model);
		
	}
	
	public void addCv(BasicCv cv){
		cvs.add(cv);
		refresh();
	}
}
