package br.com.jlex.bot.services;

import java.io.Serializable;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.telegram.telegrambots.ApiContextInitializer;

import br.com.jlex.bot.core.QuartzTest;

@DisallowConcurrentExecution
public abstract class JLexServiceAbstract extends QuartzJobBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			synchronized (Thread.currentThread()) {
				System.out.println("[Thread] Name  - " + Thread.currentThread().getName());
				System.out.println("[Thread] State - " + Thread.currentThread().getState());
				
				QuartzTest.test();
				
				System.out.println("# FIM TEST #");
			}
		} catch (Throwable  t) {
			t.printStackTrace();
		}
	}
	
	//@Scheduled(fixedDelay=5000)
	public void initGetMessagesSenders() {
		ApiContextInitializer.init();
		try {
			synchronized (this) {
				System.out.println("[Thread] Name  - " + Thread.currentThread().getName());
				System.out.println("[Thread] State - " + Thread.currentThread().getState());
				
				QuartzTest.test();
//				System.out.println("testando...");
				
				System.out.println("# FIM TEST #");
			}
		} catch (Throwable  t) {
			System.err.println("\t* ERROR INIT *");
			t.printStackTrace();
		}
	}
}
