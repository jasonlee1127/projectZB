package com.zerob.configuration.common;

import org.slf4j.Logger;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.status.InfoStatus;
import ch.qos.logback.core.status.StatusManager;

/**
 * 2019-04-01 - jasonLee
 * TODO: Logback Java config 버전 확인 후 변경
 * @author jason
 */
public class LoggerConfigurer {

	public void configure(LoggerContext loggerContext) {
		loggerContext.reset();
		
		StatusManager sm = loggerContext.getStatusManager();
		
		if (sm != null) {
			sm.add(new InfoStatus("Setting configuration from " + getClass().getName(), loggerContext));
		}
		
		/**
		 * Root Level
		 */
		Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
		
		rootLogger.isInfoEnabled();
		
	}
	
	protected void configureConsoleAppender(LoggerContext loggerContext, Logger rootLogger) {
        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<ILoggingEvent>();
        consoleAppender.setContext(loggerContext);
        consoleAppender.setName("myapp-log-console");
//        consoleAppender.setEncoder(getEncoder(loggerContext));
        consoleAppender.start();

//        rootLogger.addAppender(consoleAppender);
    }
}
