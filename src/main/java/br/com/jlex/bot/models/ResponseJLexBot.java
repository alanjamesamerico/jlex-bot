package br.com.jlex.bot.models;

import java.awt.image.BufferedImage;
import java.util.List;

public class ResponseJLexBot {
	
	String word;
	String significance;
	BufferedImage image;
	List<String> descriptions;
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getSignificance() {
		return significance;
	}
	
	public void setSignificance(String significance) {
		this.significance = significance;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public List<String> getDescriptions() {
		return descriptions;
	}
	
	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}
}
