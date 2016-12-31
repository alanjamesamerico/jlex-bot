package br.com.jlex.bot.core;

import java.io.Serializable;

import org.quartz.DisallowConcurrentExecution;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@DisallowConcurrentExecution
public class QuartzTest implements Serializable {
	
	private static final long serialVersionUID = -3063354024422459224L;
	
	public QuartzTest() {
		
	}

	public static void test() {
		ApiContextInitializer.init();
		System.out.println("[TEST] \tExecutanto Teste..\n");
		try {
			
			synchronized (Thread.currentThread()) {
				new TelegramBotsApi().registerBot(new JLexTelegramBot());
				System.out.println("[FIM GET UPDATE]");
			}
			
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		} catch (Throwable  t) {
			t.printStackTrace();
		}
	}
}
