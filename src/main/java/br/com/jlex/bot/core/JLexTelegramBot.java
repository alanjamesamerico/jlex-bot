package br.com.jlex.bot.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;

import br.com.jlex.bot.models.MessageBot;
import br.com.jlex.bot.models.commands.Commands;
import br.com.jlex.bot.models.commands.JLexBotCommands;

public class JLexTelegramBot extends TelegramLongPollingBot {
	
	private JLexWebScraping ws = new JLexWebScraping();
	
	public void onUpdateReceived(Update update) {
		System.out.println("\t> Receved..");
		if (update.hasMessage() && update.getMessage().hasText()) {
			System.out.println("> Text: " + update.getMessage().getText());
	        handleIncomingMessage(update.getMessage());
	    } else {
	    	SendMessage message = new SendMessage() 
	    		.setChatId(update.getMessage().getChatId())
	            .setText("Desculpe, não entendi o que você digitou.\n Por favor, digite novamente =)");
			try {
				sendMessage(message);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
	    }
	}
	
	public void handleIncomingMessage(Message message) {
		try {
			if (Commands.isStart(message.getText())) {
				sendWelcomeJlex(message);
			} else if (Commands.isStop(message.getText())) {
				sendGoodByeJlex(message);
			} else {
				sendResponseSearch(message);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendResponseSearch(Message message) throws TelegramApiException {
		
		if (isValideWordReceived(message)){
			SendPhoto sendPhoto = new SendPhoto();
			sendPhoto.setChatId(message.getChatId());
			sendPhoto.setPhoto(ws.getUrlImage());
			
			try {
				
				sendPhoto(sendPhoto);
				Thread.sleep(1500);
				
			} catch (TelegramApiException e) {
				SendMessage msg = new SendMessage();
				msg.setChatId(message.getChatId());
				msg.setText(MessageBot.IMG_NOT_FOUND);
				sendMessage(msg);
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				
				SendMessage response = generateResponseMessage(ws, message);
				sendMessage(response);
				
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean isValideWordReceived(Message message){
		
		try {
			
			ws = new JLexWebScraping(StringUtils.lowerCase(message.getText()));
			String word = ws.getWordSearch();
			
			if (!StringUtils.isEmpty(word)){ 
				return true; 
			} else {
				responseWordNotFound(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void responseWordNotFound(Message message) {
		try {
			SendMessage msg = new SendMessage();
			msg.setChatId(message.getChatId());
			msg.setText(MessageBot.WORD_NOT_FOUND);
			sendMessage(msg);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	private SendMessage generateResponseMessage(JLexWebScraping ws, Message message) {
		
 		SendMessage response = new SendMessage();
		String content = "";
		response.setChatId(message.getChatId());
		content = ws.getWordSearch() +"\n---\n\n"+ 
				ws.getSignificance() + "\n";
		
		List<String> descriptions = ws.getDescription();
		if (!descriptions.isEmpty()) {
			for (String description : descriptions) {
				content = content + "> " + description +"\n\n";
			}
			response.setText(content);
		} else {
			responseWordNotFound(message);
		}
		
		return response;
	}

	private void sendGoodByeJlex(Message message) {
		try {
			
			SendMessage responseMessage = new SendMessage();
			responseMessage.setChatId(message.getChatId());
			responseMessage.setText("Ei"+ message.getFrom().getFirstName() +", não vai embora =/");
			sendMessage(responseMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendWelcomeJlex(Message message) {
		try {
			
			SendMessage responseMessage = new SendMessage();
			responseMessage.setChatId(message.getChatId());
			responseMessage.setText("Ei " + getFullNameSender(message) +", "+ MessageBot.WELCOME);
			sendMessage(responseMessage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getFullNameSender(Message message) {
		boolean firstName = StringUtils.isNoneEmpty(message.getFrom().getFirstName());
		boolean lastName = StringUtils.isNoneEmpty(message.getFrom().getLastName());
		if (firstName && lastName) {
			return message.getFrom().getFirstName()+" "+ message.getFrom().getLastName();
		} else {
			return message.getFrom().getFirstName();
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
