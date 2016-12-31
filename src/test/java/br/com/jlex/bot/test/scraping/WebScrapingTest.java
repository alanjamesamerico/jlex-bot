package br.com.jlex.bot.test.scraping;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jaunt.JauntException;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import br.com.jlex.bot.core.JLexWebScraping;

public class WebScrapingTest {
	
	private JLexWebScraping dicioScraping;
	
	@Before
	public void setup() throws ResponseException {
		dicioScraping = new JLexWebScraping("amor");
	}
	
	@Test
	public void structTest() {
		
		try {
			
			String word = dicioScraping.getWordSearch();
			String significance = dicioScraping.getSignificance();
//			List<String> descriptions = dicioScraping.getDescription();
//			
			System.out.println(word + "\n" + significance);
//			
//			for (String text : descriptions) {
//				System.out.println(text);
//			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
//	@Test
	public void encodeTest() throws UnsupportedEncodingException {
		try{
			  UserAgent userAgent = new UserAgent(); // https://www.dicio.com.br/explanacao/
			  userAgent.visit("http://www.estadao.com.br/"); 
			  String s = userAgent.doc.findFirst("<h1>").getText();
			  System.out.println("Site: " + s);
			  
			  String ss = new String(s.getBytes(), "ISO-8859-1"); // ISO-8859-1
			  System.out.println(ss);
			  
			  byte[] latin1 = s.getBytes();
			  byte[] utf8 = new String(latin1, "ISO-8859-1").getBytes("UTF-8");
			  String sUtf8 = new String(utf8);
			  System.out.println("\nUTF_8: " + sUtf8);
			  
			  System.out.println("\n---\nResponse:\n" + userAgent.response);
			  
			  
			}
			catch(JauntException e){
			  System.err.println(e);
			}
	}
	
//	@Test
//	public void httpGetImageTest() throws IOException {
//		BufferedImage img = JLexWebScraping.getImageWord("https://s.dicio.com.br/amor.jpg");
//		ImageIO.write(img, "png", new File("C:/Users/Alan James/Desktop/amor.png"));
//	}
}
