package br.com.jlex.bot.services;

import java.io.Serializable;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import br.com.jlex.bot.core.QuartzTest;

@Component("jlexService")
@DisallowConcurrentExecution
public class JLexService extends QuartzJobBean implements Serializable {
	
	private static final long serialVersionUID = 2282281468243216898L;
	
	private String telegramApi;
	
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
		try {
			synchronized (this) {
				System.out.println("[Thread] Name  - " + Thread.currentThread().getName());
				System.out.println("[Thread] State - " + Thread.currentThread().getState());
				
				QuartzTest.test();
				
				System.out.println("# FIM TEST #");
			}
		} catch (Throwable  t) {
			System.err.println("\t* ERROR INIT *");
			t.printStackTrace();
		}
	}
}
