package WorkPaySystem.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import WorkPaySystem.dbconn;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class NewWorker extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField age;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewWorker frame = new NewWorker();
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
	public NewWorker() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblName.setBounds(89, 90, 78, 20);
		contentPane.add(lblName);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAge.setBounds(89, 131, 78, 20);
		contentPane.add(lblAge);
		
		JLabel lblWorkType = new JLabel("Work Type:");
		lblWorkType.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblWorkType.setBounds(89, 179, 92, 20);
		contentPane.add(lblWorkType);
		
		name = new JTextField();
		name.setBounds(202, 90, 132, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		age = new JTextField();
		age.setColumns(10);
		age.setBounds(202, 133, 132, 20);
		contentPane.add(age);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(202, 181, 132, 20);
		try {
			dbconn conn = new dbconn();
			ResultSet rst = conn.getStmt().executeQuery("select * from worktype");
			while(rst.next()) {
				comboBox.addItem(rst.getString("typename"));
			}

		}catch(Exception e) {
			System.out.println(e);
		}
		contentPane.add(comboBox);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					dbconn conn = new dbconn();
//					System.out.println();
					int n = comboBox.getSelectedIndex()+1;
					conn.getStmt().executeUpdate("Insert into workersdetail (id,name,age,type,totalrup) value (null,'"+name.getText()+"','"+age.getText()+"','"+n+"',0)");
					dispose();
					new Mainframe();
				}catch(Exception e) {
					System.out.println(e);
				}
			}
		});
		btnAdd.setBounds(222, 244, 112, 41);
		contentPane.add(btnAdd);
		
		JLabel lblAddNewWorker = new JLabel("Add New Worker");
		lblAddNewWorker.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblAddNewWorker.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewWorker.setBounds(111, 31, 223, 29);
		contentPane.add(lblAddNewWorker);
	}
}
