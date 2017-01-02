package br.com.jlex.bot.test.scraping;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder;
import com.jaunt.ResponseException;

import br.com.jlex.bot.core.JLexWebScraping;

public class WebScrapingTest {
	
	private JLexWebScraping ws;
	
	@Before
	public void setup() throws ResponseException, IOException {
		ws = new JLexWebScraping("amor");
	}
	
//	@Test
	public void testJsoup() throws IOException {
		
		String word = ws.getWordSearch();
		String significance = ws.getSignificance();
		System.out.println(word + "\n" + significance);
		
		ArrayList<String> contentSpans = (ArrayList<String>) ws.getDescription();
		for (String s : contentSpans) {
			System.out.println(s);
		}
	}
	
	@Test
	public void testString(){
		
		String text = "necessidades";
		String s = StringUtils.substring(text, text.length()-1);
		boolean ss = StringUtils.contains(StringUtils.substring(text, text.length()-1), "s");
		
		String s3 = StringUtils.removeEnd(text, "s");
		
		System.out.println(s3);
	}
	
	
	
	
	
	
}
