package form;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Item;
import model.Listable;

public class ListableForm extends JFrame {

	private JPanel contentPane;
	private Listable listable;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JButton btnNewButton;
	private JList list;
	private JButton btnDelete;
	private JButton btnEdit;
	private JTextField textField_1;
	private JLabel lblText;
	private JLabel lblDateiso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListableForm frame = new ListableForm();
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
	public ListableForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(12, 12, 424, 15);
		contentPane.add(lblNewLabel);
		
		list = new JList();
		list.setBounds(22, 115, 353, 312);
		contentPane.add(list);
		
		textField = new JTextField();
		textField.setBounds(261, 30, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateString = textField_1.getText();
				
				if(dateString == null || dateString.equals("")){
					listable.getItemList().add(new Item(textField.getText()));
				}else{
					try{
						DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						Date date = format.parse(dateString);
						boolean isOk = checkDate(date, listable.getItemList());
						if(isOk){
							listable.getItemList().add(new Item(textField.getText(), date));
						}else{
							JOptionPane.showMessageDialog(null, "Wrong date order");
						}
						
					}catch(Exception ee){
						
					}
				}
				
				refresh();
			}
		});
		btnNewButton.setBounds(387, 73, 117, 25);
		contentPane.add(btnNewButton);
		
		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = list.getSelectedIndex();
				if(i>=0){
					listable.getItemList().remove(i);
				}
				refresh();
			}
		});
		btnDelete.setBounds(387, 121, 117, 25);
		contentPane.add(btnDelete);
		
		btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = list.getSelectedIndex();
				if(i>=0){
					ListableForm.newinstance(listable.getItemList().get(i));
				}
			}
		});
		btnEdit.setBounds(387, 158, 117, 25);
		contentPane.add(btnEdit);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setBounds(261, 79, 114, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblText = new JLabel("Text");
		lblText.setBounds(261, 12, 70, 15);
		contentPane.add(lblText);
		
		lblDateiso = new JLabel("Date (ISO)");
		lblDateiso.setBounds(261, 61, 106, 15);
		contentPane.add(lblDateiso);
	}
	
	
	

	public static void newinstance(Listable listable){
		ListableForm form = new ListableForm();
		form.listable = listable;
		form.refresh();
		form.show();
	}
	
	public void refresh(){
		lblNewLabel.setText(listable.getListableTitle());
		
		DefaultListModel<Item> model = new DefaultListModel<>();

		for ( Item item: listable.getItemList()){
			model.addElement( item );
		}
		list.setModel(model);
		
	}
	
	
	public boolean checkDate(Date date, ArrayList<Item> itemList) {
		Date lastDate = null;
		for(Item item:itemList){
			if(item.getDate() != null){
				lastDate = item.getDate();
			}
		}
		
		if(lastDate == null || date.after(lastDate)){
			return true;
		}else{
			return false;
		}
	}
}
