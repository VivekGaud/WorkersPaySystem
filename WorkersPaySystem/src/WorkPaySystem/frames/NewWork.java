package WorkPaySystem.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import WorkPaySystem.dbconn;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewWork extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtYyyymmdd;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;
	private static int id;
	float total;
	String s="";
	DefaultTableModel model;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewWork frame = new NewWork(id);
					System.out.println(id+"slcmslml");
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
	public NewWork(int id) {
		this.id = id;
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblName.setBounds(107, 26, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDate.setBounds(107, 51, 46, 14);
		contentPane.add(lblDate);
		
		JLabel lblWorkType = new JLabel("Work Type:");
		lblWorkType.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblWorkType.setBounds(107, 76, 75, 14);
		contentPane.add(lblWorkType);
		
		JLabel lblPiece = new JLabel("Piece:");
		lblPiece.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPiece.setBounds(107, 101, 46, 14);
		contentPane.add(lblPiece);
		
		JLabel lblRate = new JLabel("Rate:");
		lblRate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblRate.setBounds(107, 126, 46, 14);
		contentPane.add(lblRate);
		
		JLabel lblRupees = new JLabel("Rupees:");
		lblRupees.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblRupees.setBounds(107, 151, 64, 14);
		contentPane.add(lblRupees);
		
		textField = new JTextField();
		textField.setBounds(218, 26, 142, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		try {
			dbconn conn = new dbconn();
			ResultSet rs = conn.getStmt().executeQuery("select * from workersdetail where id = '"+id+"'");
			if(rs.next()) {
				textField.setText(rs.getString("name"));
			}

		}catch(Exception e) {
			System.out.println(e);
		}
		
		txtYyyymmdd = new JTextField();
		txtYyyymmdd.setText("YYYY-MM-DD");
		txtYyyymmdd.setColumns(10);
		txtYyyymmdd.setBounds(218, 49, 142, 20);
		contentPane.add(txtYyyymmdd);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(218, 99, 142, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(218, 124, 142, 20);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(218, 149, 109, 20);
		contentPane.add(textField_5);
		textField_5.setEditable(false);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(218, 74, 142, 20);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 223, 431, 134);
		contentPane.add(scrollPane);
		
		table = new JTable();
		model = new DefaultTableModel();
		Object[] column = {"id","Date","Work Type","Piece","Rate","Rupee"};
		Object[] row = new Object[6];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		try {
			dbconn conn = new dbconn();
			ResultSet rs = conn.getStmt().executeQuery("SELECT wid,date,m.typename,Piece,Rate,Rupee from workdetail s join worktype m on s.type = m.wtid where wksId='"+id+"'");
			while(rs.next()) {
				row[0] = rs.getInt("wid");
				row[1] = rs.getDate("date");
				row[2] = rs.getString("typename");
				row[3] = rs.getInt("piece");
				row[4] = rs.getFloat("rate");
				row[5] = rs.getFloat("rupee");
//				System.out.println(row[4]);
				model.addRow(row);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		scrollPane.setViewportView(table);
		
		JButton btnCalculate = new JButton("calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					dbconn conn = new dbconn();
					ResultSet rst = conn.getStmt().executeQuery("select totalrup from workersdetail where id = '"+id+"'");
					if(rst.next()) {
						total = rst.getFloat("totalrup");
					}
					total = total +( Float.parseFloat(textField_3.getText()) * Float.parseFloat(textField_4.getText())) ;
					textField_5.setText(s+total);
				}catch(Exception e) {
					System.out.println(e);
				}
				
			}
		});
		btnCalculate.setBounds(337, 148, 104, 23);
		contentPane.add(btnCalculate);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(228, 189, 89, 23);
		contentPane.add(btnAdd);
	}

}
