package br.com.jlex.bot.test.api;

import org.junit.Test;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;

import br.com.jlex.bot.core.JLexTelegramBot;
import br.com.jlex.bot.services.JLexService;

public class TelegramBotAPITest {
	
	@Test
	public void telegramApiTest() {
		
		try {
			
			ApiContextInitializer.init();
			System.out.println("EM EXECUÇÃO...\n================\n");
			new JLexService().initGetMessagesSenders();
			new TelegramBotsApi().registerBot(new JLexTelegramBot());
			Thread.sleep(80000000);
			System.out.println("\n----\n\tFIM DA EXECUÇÃO!");
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
