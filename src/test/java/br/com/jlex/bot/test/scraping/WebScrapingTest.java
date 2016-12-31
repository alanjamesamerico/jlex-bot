package br.com.jlex.bot.test.scraping;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.jaunt.JauntException;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import br.com.jlex.bot.core.JLexWebScraping;

public class WebScrapingTest {
	
	private JLexWebScraping ws;
	
	@Before
	public void setup() throws ResponseException, IOException {
		ws = new JLexWebScraping("amor");
	}
	
	@Test
	public void testJsoup() throws IOException {
		
		String word = ws.getWordSearch();
		String significance = ws.getSignificance();
		System.out.println(word + "\n" + significance);
		
		ArrayList<String> contentSpans = (ArrayList<String>) ws.getDescription();
		for (String s : contentSpans) {
			System.out.println(s);
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
}
