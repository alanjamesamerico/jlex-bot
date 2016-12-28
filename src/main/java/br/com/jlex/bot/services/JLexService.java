package br.com.jlex.bot.services;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.stereotype.Component;

@Component("jlexService")
@DisallowConcurrentExecution
public class JLexService extends JLexServiceAbstract {

	private static final long serialVersionUID = -6143356813365910975L;
	
	@SuppressWarnings("unused")
	private String telegramAPi;
	

}
