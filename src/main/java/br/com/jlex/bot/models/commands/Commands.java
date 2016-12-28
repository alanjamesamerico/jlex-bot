package br.com.jlex.bot.models.commands;

public class Commands {
	
	public static final String START = "/start";
	public static final String STOP = "/stop";
	
	public static boolean isStart(String message) {
		return message.equals("/start");
	}
	
	public static boolean isStop(String message) {
		return message.equals("/stop");
	}
}
