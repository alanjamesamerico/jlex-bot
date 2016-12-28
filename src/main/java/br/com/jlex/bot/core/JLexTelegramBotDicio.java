package br.com.jlex.bot.core;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import br.com.jlex.bot.models.commands.JLexBotCommands;

public class JLexTelegramBotDicio extends TelegramLongPollingBot {
	
	public void onUpdateReceived(Update update) {
		System.out.println("\t> GetUpdate..");
		if (update.hasMessage() && update.getMessage().hasText()) {
			System.out.println("Text: " + update.getMessage().getText());
			SendMessage message = new SendMessage();
			message.setChatId(update.getMessage().getChatId());
			message.setText("[Testando Quartz]\n\nPalavra enviada: " + update.getMessage().getText());
			try {
				sendMessage(message);
				System.out.println("\t> FIM GetUpdate..");
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
	    }
	}
	
	public String getBotUsername() { 
		return JLexBotCommands.USER_NAME;
	}

	@Override
	public String getBotToken() {
		return JLexBotCommands.TOKEN;
	}
}
