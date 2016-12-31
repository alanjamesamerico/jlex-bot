package br.com.jlex.bot.core;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.common.base.Charsets;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

import br.com.jlex.bot.models.commands.TagCommands;

public class JLexWebScraping {
	
	private static	String word;
	private	static final String 	URL_SCRAPING 	= "https://www.dicio.com.br/";
	private static final String		URL_IMAGE 		= "https://s.dicio.com.br/";
	private static final String		IMG_JPG			= ".jpg";
	public 	static UserAgent 	scraping;
	
    public JLexWebScraping() {
	}
    
	@SuppressWarnings("static-access")
	public JLexWebScraping(String word) throws ResponseException {
		this.word = word;
    	scraping = new UserAgent();
		scraping.visit(URL_SCRAPING + word);
	}
    
	public static String getTag(String word) throws ResponseException, NotFound{
		scraping = new UserAgent();
		scraping.visit(URL_SCRAPING + word);
		System.out.println(URL_SCRAPING);
		return scraping.doc.findFirst(TagCommands.h1).getText();
	}
	
	public static String getTest(){
		return "Test Scraping static";
	}
	
	@SuppressWarnings("static-access")
	public String getWordSearch() throws NotFound {
		return this.scraping.doc.findFirst(TagCommands.h1).getText();
	}
	
	@SuppressWarnings("static-access")
	public String getSignificance() throws NotFound {
		return this.scraping.doc.findFirst(TagCommands.h2).getText();
	}
	
	public List<String> getDescription() throws UnsupportedEncodingException {
		List<String> descriptions = new ArrayList<String>();
		Elements spans = scraping.doc.findEach("<p itemprop='description'>")
									 .findEach(TagCommands.span);
		for (Element span : spans) {
			descriptions.add(new String(span.getText().getBytes(),Charsets.UTF_8)); // ISO-8859-1
		}
		return descriptions;
	}
	
	public static String getBaseUrl(){
		return URL_SCRAPING;
	}
	
	public static String getBaseUrlImage() {
		return URL_IMAGE + word + IMG_JPG;
	}
	
	public static BufferedImage getImageWord(String urlPath) throws IOException {
		BufferedImage img = ImageIO.read(new URL(urlPath));
		return img;
	}
}
