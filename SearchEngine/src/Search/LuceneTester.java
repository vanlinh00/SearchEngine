package Search;

import java.io.FileWriter;
import java.io.IOException;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;



import com.google.gson.Gson;
import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//import java.util.ArrayList;

public class LuceneTester {
	String indexDir = "D:\\Lucene\\Index";
	String dataDir = "D:\\Lucene\\Data";
	Indexer indexer;
	Searcher searcher;
    public static	long totalTime1;
    public static	int totalducfound1;
   public static ArrayList<String> PathFile = new ArrayList<String>();
   
 /*
	public static void main(String[] args) {
	
		          try {
					crawldata();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          
	        
	}
		  
		
/*
	public static void crawldata() throws IOException{

        ArrayList<Itemnew> itemnew = new ArrayList<Itemnew>();
       String url= "https://vnexpress.net/so-hoa";
       for(int i=1;i<10;i++) {
    	 String newurl=url+"/cong-nghe-p"+1; 
        org.jsoup.nodes.Document doc = Jsoup.connect(newurl).get();

        Elements elements = doc.getElementsByClass("item-news item-news-common");
        for (Element e : elements){
        	Itemnew item = new Itemnew();
        	item.setDescription(e.text());
            itemnew.add(item);
      System.out.println("runing");
          }
        
      }
         Gson gson = new Gson();
        String jsonData = gson.toJson(itemnew);

        System.out.println(jsonData);
        for (int i = 0; i < itemnew.size(); i++){
         //   System.out.println(i + " Title: " + listMobiles.get(i).getTitle());
		 try { File file = new File("D:\\Lucene\\Data\\Data" + i + ".doc");
		 
		 FileWriter myWriter = new FileWriter("D:\\Lucene\\Data\\Data" + i + ".doc");
		 myWriter.write(itemnew.get(i).getDescription());
		 myWriter.close();
		 } 
		 catch (IOException e)
		  { System.out.println("An error occurred."); 
		     e.printStackTrace(); 
		  }
		 
        }
        
		 
    }
  */
	public void SearchEngine(String searchQuery)
	{	
		System.out.println("ban dang muon tim tu khoa"+searchQuery);
		  LuceneTester tester; 
	
		  tester = new LuceneTester(); 
		  try {
			tester.createIndex();
			  try {
				tester.search(searchQuery);
			} catch (ParseException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} catch (IOException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	private void createIndex() throws IOException {
		indexer = new Indexer(indexDir);
		int numIndexed;
		
		long startTime = System.currentTimeMillis();
		numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
		long endTime = System.currentTimeMillis();
		
		indexer.close();
		System.out.println(numIndexed + " File indexed, time taken: " + (endTime - startTime) + " ms");
		
		
		
	}

	private void search(String searchQuery) throws IOException, ParseException {
		String strFileDirectoryPath = "";
		searcher = new Searcher(indexDir);
		
		long startTime = System.currentTimeMillis();
		TopDocs hits = searcher.search(searchQuery);
		long endTime = System.currentTimeMillis();

		System.out.println(hits.totalHits + " documents found. Time :" + (endTime - startTime));
		
		totalducfound1=hits.totalHits;
		
		totalTime1=endTime-startTime;
	
		
		for (ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			
			System.out.println("File: " + doc.get(LuceneConstants.FILE_PATH));
			PathFile.add(doc.get(LuceneConstants.FILE_PATH));
			strFileDirectoryPath = doc.get(LuceneConstants.FILE_PATH);
		}

//		// Construct file object
//		File file = new File(strFileDirectoryPath);
//		try {
//			Scanner scan = new Scanner(file);
//			while (scan.hasNextLine()) {
//				String scannedline = scan.nextLine();
//				//System.out.println(scannedline);
//			}
//			scan.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("loi o day a");
//			e.printStackTrace();
//		}
     
		searcher.close();
	}
	
}