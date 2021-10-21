package Search;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class Crawldata extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField texturl1;
	private JTextField savedata;
	private JButton btngetdata;
	private JLabel idgetdata;
    private Boolean checkgetdata=false;
	public static String totaldata;
	private JButton btsearch;
	private JButton btdelete;
public	static Crawldata frameCrawldata=new Crawldata();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					frameCrawldata.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Crawldata() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("H\u1EC6 TH\u00D4NG T\u00CCM KI\u1EBEM V\u0102N B\u1EA2N");
		lblNewLabel.setSize(new Dimension(10, 10));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 37));
		lblNewLabel.setBounds(164, 11, 707, 94);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Vui l\u00F2ng nh\u1EADp link url ");
		lblNewLabel_1.setBounds(108, 141, 161, 14);
		contentPane.add(lblNewLabel_1);
		
		texturl1 = new JTextField();
		texturl1.setBounds(274, 138, 408, 20);
		contentPane.add(texturl1);
		texturl1.setColumns(10);
		
		savedata = new JTextField();
		savedata.setBounds(274, 208, 408, 20);
		contentPane.add(savedata);
		savedata.setColumns(10);
		
		btngetdata = new JButton("Get Data");
		btngetdata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texturl= texturl1.getText();
				String savedt=savedata.getText();
				System.out.print(savedt);
				
				//"https://tiki.vn/dien-thoai-may-tinh-bang/c1789"
				if(texturl==null||texturl==""||savedt==""||savedt==null||texturl.length()==0||savedt.length()==0)
				{
					  JOptionPane.showMessageDialog(contentPane, "Vui lòng điền đầy đủ thông tin");
				}
				else
				{
				try {
					crawldata(texturl,savedt);
					idgetdata.setText("Tổng số văn bản lấy được là: "+totaldata);
					 JOptionPane.showMessageDialog(contentPane, "Đã lấy dữ liệu thành công");
					checkgetdata=true;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btngetdata.setBounds(274, 333, 89, 23);
		contentPane.add(btngetdata);
		
		JLabel lblNewLabel_2 = new JLabel("Th\u01B0 m\u1EE5c l\u01B0u tr\u1EEF");
		lblNewLabel_2.setBounds(108, 211, 96, 14);
		contentPane.add(lblNewLabel_2);
		
		idgetdata = new JLabel("");
		idgetdata.setBounds(120, 282, 243, 14);
		contentPane.add(idgetdata);
		
		btsearch = new JButton("Start");
		btsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(checkgetdata)
				{
				SearchText frame = new SearchText();
				frame.setVisible(true);
				}
				else
				{
					  JOptionPane.showMessageDialog(contentPane, "Vui lòng lấy dữ liệu trước");
				}
			
			}
			
		});
		btsearch.setBounds(593, 333, 89, 23);
		contentPane.add(btsearch);
		
		btdelete = new JButton("Delete");
		btdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkgetdata)
				{
					Arrays.stream(new File("D:\\Lucene\\Data").listFiles()).forEach(File::delete);
					 JOptionPane.showMessageDialog(contentPane, "Đã xóa dữ liệu thành công");
				}
				else
				{
					  JOptionPane.showMessageDialog(contentPane, "Vui lòng lấy dữ liệu trước");
				}
			
			}
		});
		btdelete.setBounds(446, 333, 89, 23);
		contentPane.add(btdelete);
	}
	
	public static void crawldata(String url1,String savedt) throws IOException{
		
		savedt=savedt+"/data";
		/*
		if(url=="https://tiki.vn/dien-thoai-may-tinh-bang/c1789")
		{
			
	        ArrayList<MobileItems> listMobiles = new ArrayList<MobileItems>();

	        org.jsoup.nodes.Document doc = Jsoup.connect(url).get();

	        Elements elements = doc.getElementsByClass("name");
	       
	        for (Element e : elements){
	          MobileItems item = new MobileItems();
	            item.setTitle(e.text());
	              listMobiles.add(item);
	              }
	        

	        //Chuyển listMobiles thành JSON text
	         Gson gson = new Gson();
	        String jsonData = gson.toJson(listMobiles);

	        System.out.println(jsonData);
	        totaldata=""+listMobiles.size();
	        for (int i = 0; i < listMobiles.size(); i++){
	         //   System.out.println(i + " Title: " + listMobiles.get(i).getTitle());
			 try {
				File file = new File(savedt + i + ".doc");
				 System.out.println(savedt+i);
			 FileWriter myWriter = new FileWriter(savedt + i + ".doc");
			 myWriter.write(" Title: " + listMobiles.get(i).getTitle());
			 myWriter.close();
			 } 
			 catch (IOException e)
			  { System.out.println("An error occurred."); 
			     e.printStackTrace(); 
			  }
			 
	        }
		}
		if(url=="https://vnexpress.net/so-hoa")
		{
              */
	        ArrayList<Itemnew> itemnew = new ArrayList<Itemnew>();
	       
	       for(int i=1;i<10;i++) {
	    	   System.out.print("data page"+i);
	    	 String newurl=url1+"-p"+i; 
	    	
	        org.jsoup.nodes.Document doc = Jsoup.connect(newurl).get();

	        Elements elements = doc.getElementsByClass("item-news item-news-common");
	        for (Element e : elements){
	        	Itemnew item = new Itemnew();
	        	item.setDescription(e.text());
	            itemnew.add(item);
	             System.out.println("runing");
	          }
	        
	      }
	       /*
	         Gson gson = new Gson();
	        String jsonData = gson.toJson(itemnew);
             
	        FileWriter filejson = new FileWriter("D:\\Lucenefile.json");
	        filejson.write(jsonData);
	        filejson.close();
	        System.out.println(jsonData);
	        */
	       
	        totaldata=""+itemnew.size();
	        for (int i = 0; i < itemnew.size(); i++){
			 try { File file = new File(savedt + i + ".doc");
			 
			 FileWriter myWriter = new FileWriter(savedt + i + ".doc");
			 myWriter.write(itemnew.get(i).getDescription());
			 myWriter.close();
			 } 
			 catch (IOException e)
			  { System.out.println("An error occurred."); 
			     e.printStackTrace(); 
			  }
		}

        
		 
    }
}
