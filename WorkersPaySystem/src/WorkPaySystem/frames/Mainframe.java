package WorkPaySystem.frames;

import java.awt.EventQueue;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import WorkPaySystem.dbconn;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mainframe {

	private JFrame frmWorkersPaySystem;
	private JTable table;
	DefaultTableModel model;
	JComboBox<String> comboBox;
	int count = 1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainframe window = new Mainframe();
					window.frmWorkersPaySystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mainframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Home frame
		
		frmWorkersPaySystem = new JFrame();
		frmWorkersPaySystem.setTitle("Workers Pay System");
		frmWorkersPaySystem.setVisible(true);
		frmWorkersPaySystem.setBounds(100, 100, 467, 374);
		frmWorkersPaySystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWorkersPaySystem.getContentPane().setLayout(null);
		
		//To create new worker
		JButton btnAddWorker = new JButton("Add Worker");
		btnAddWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewWorker nw = new NewWorker();
				nw.setVisible(true);
				frmWorkersPaySystem.dispose();
			}
		});
		btnAddWorker.setBounds(55, 29, 103, 23);
		frmWorkersPaySystem.getContentPane().add(btnAddWorker);
		
		//Button to show details of selected worker
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					dbconn conn = new dbconn();
					ResultSet rst = conn.getStmt().executeQuery("select * from workersdetail where name = '"+comboBox.getSelectedItem().toString()+"'");
					if(rst.next()) {
						int wid = rst.getInt("id");
						NewWork nwk = new NewWork(wid);
					}

				}catch(Exception e) {
					System.out.println(e);
				}
				frmWorkersPaySystem.dispose();
				
			}
		});
		btnShow.setBounds(303, 29, 89, 23);
		frmWorkersPaySystem.getContentPane().add(btnShow);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 82, 389, 223);
		frmWorkersPaySystem.getContentPane().add(scrollPane);
		
		//table to show current wokers details
		table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
		model = new DefaultTableModel();
		Object[] column = {"id","Name","Age","Work Type","Total"};
		Object[] row = new Object[5];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		try {
			dbconn conn = new dbconn();
			ResultSet rs = conn.getStmt().executeQuery("SELECT id,name,age,m.typename,totalrup from workersdetail s join worktype m on s.type = m.wtid order by s.id");
			while(rs.next()) {
				row[0] = count++;
				row[1] = rs.getString("name");
				row[2] = rs.getInt("age");
				row[3] = rs.getString("typename");
				row[4] = rs.getFloat("totalrup");
//				System.out.println(row[4]);
				model.addRow(row);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		scrollPane.setViewportView(table);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(190, 30, 103, 20);
		try {
			dbconn conn = new dbconn();
			ResultSet rst = conn.getStmt().executeQuery("select * from workersdetail");
			while(rst.next()) {
				comboBox.addItem(rst.getString("name"));
			}

		}catch(Exception e) {
			System.out.println(e);
		}
		frmWorkersPaySystem.getContentPane().add(comboBox);
	}
}
