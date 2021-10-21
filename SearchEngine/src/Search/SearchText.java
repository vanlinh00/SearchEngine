package Search;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;
import java.util.Scanner;
import java.awt.Dimension;
import javax.swing.JTextPane;
public class SearchText extends JFrame {
	private JButton btnsearch;
	private JTextField text1;
	private JLabel totalTime;
	private JTable table;
	private JButton btreaddoc;
	private JTextPane textPane;

	
/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchText frame = new SearchText();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	
	public SearchText() {
		setSize(new Dimension(828, 513));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 888, 513);
		final JPanel contentPane = new JPanel();
		contentPane.setMaximumSize(new Dimension(102819, 102767));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnsearch = new JButton("T\u00ECm Ki\u1EBFm");
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String textkey=text1.getText();
				if(textkey==""||textkey==null)
				{
					 System.out.println(" du lieu nhap vao trong");
				}
				
				 LuceneTester Tester = new LuceneTester();
				
				 Tester.PathFile.clear();
				 DefaultTableModel model = (DefaultTableModel) table.getModel();
				 model.setRowCount(0);
				 if(Tester.PathFile.size()==0&&table.getRowCount()==0)
				 {
				 Tester.SearchEngine(textkey);
				 totalTime.setText("Đã tìm thấy "+Tester.totalducfound1+" kết quả"+" trong "+Tester.totalTime1+" ms");
				 System.out.println("get path file ok"+ Tester.PathFile.size());
				 
				for(int i=0;i<Tester.PathFile.size();i++)
				{
				String data1 = i+"";
				 String data2 =Tester.PathFile.get(i) ;
				 System.out.println("get path file ok"+data2); 
				 Object[] row = { data1, data2};
				 model = (DefaultTableModel) table.getModel();
			     model.addRow(row);
				}
				}
				
			}
		});
		
		btnsearch.setBounds(619, 46, 107, 33);
		contentPane.add(btnsearch);
		
		
		text1 = new JTextField();
		text1.setBounds(232, 43, 353, 39);
		contentPane.add(text1);
		text1.setColumns(10);
		
		totalTime = new JLabel("");
		totalTime.setBounds(106, 100, 199, 33);
		contentPane.add(totalTime);
		
		// chưa ok
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Phone"
			}
		));
		
	
		table.setBackground(SystemColor.control);
		table.setBounds(44, 184, 334, 243);
		contentPane.add(table);
		
		
	//ok	
		btreaddoc = new JButton("Đọc file");
		btreaddoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 LuceneTester Tester = new LuceneTester();
			  int seclectrow= table.getSelectedRow();
			  if(seclectrow==-1)
			  {
				  JOptionPane.showMessageDialog(contentPane, "Hãy chọn 1 file để đọc");
			  }
			  else
			  {
				  
				 String strFileDirectoryPath = Tester.PathFile.get(seclectrow);
				 System.out.print(Tester.PathFile.get(seclectrow));
					File file = new File(strFileDirectoryPath);
					try {
					Scanner scan = new Scanner(file);
					while (scan.hasNextLine()) {
				     String scannedline = scan.nextLine();
				      textPane.setText(scannedline);
					}
				
					scan.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					
					
			  }
			}
		});
		btreaddoc.setBounds(400, 262, 89, 23);
		contentPane.add(btreaddoc);
		
		textPane = new JTextPane();
		textPane.setBackground(SystemColor.control);
		textPane.setBounds(509, 184, 311, 243);
		contentPane.add(textPane);
		
		
		
	}
}



