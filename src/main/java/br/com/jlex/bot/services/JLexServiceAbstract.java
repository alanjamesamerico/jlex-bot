package br.com.jlex.bot.services;

import java.io.Serializable;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import br.com.jlex.bot.core.JLexTelegramBot;

@DisallowConcurrentExecution
public abstract class JLexServiceAbstract extends QuartzJobBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
	}
	
	public void startBot() {
		ApiContextInitializer.init();
		try {
			System.out.println("#################################"
					+ "\n#\tBot Inicialized\t\t#\n"
					+ "#################################");
			new TelegramBotsApi().registerBot(new JLexTelegramBot()); // Registrando o JLex Bot
		} catch (TelegramApiRequestException e) {
			// TODO Criar uma Exception personalizada.
			System.err.println("Erro ao iniciar o JLex Bot!");
			e.printStackTrace();
		}
	}
	
	public void stopBot() {
		// TODO Implementar
	}
}
