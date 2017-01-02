package br.com.jlex.bot.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import br.com.jlex.bot.models.commands.TagCommands;

public class JLexWebScraping {
	
	private static 	Document 	doc;
	private static	String 		word;
	
	private	static final String 	URL_SCRAPING 	= "https://www.dicio.com.br/";
	private static final String		URL_IMAGE 		= "https://s.dicio.com.br/";
	private static final String		IMG_JPG			= ".jpg";
	
    public JLexWebScraping() {
	}
    
	@SuppressWarnings("static-access")
	public JLexWebScraping(String word) throws IOException {
		this.word = word;
		doc = Jsoup.connect(URL_SCRAPING + word).get();
	}
    
	public String getWordSearch() {
		return doc.select(TagCommands.h1).first().text();
	}
	
	public String getSignificance() {
		return doc.select(TagCommands.h2).first().text();
	}
	
	public List<String> getDescription() {
		ArrayList<String> contentSpans = new ArrayList<String>();
		doc.select(TagCommands.p_itemprop)
				.forEach( span -> {
					for (int i = 0; i < span.getElementsByTag("span").size(); i++) {
						contentSpans.add(span.getElementsByTag("span").get(i).text());
					}
				});
		return contentSpans;
	}
	
	public static String getBaseUrl(){
		return URL_SCRAPING;
	}
	
	public String getUrlImage() {
		return URL_IMAGE + word + IMG_JPG;
	}
}
