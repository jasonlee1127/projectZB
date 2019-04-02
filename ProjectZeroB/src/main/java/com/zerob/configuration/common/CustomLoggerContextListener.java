package com.zerob.configuration.common;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;

/**
 * 2019-04-01 - jasonLee
 * TODO: Logback Java config 버전 확인 후 변경
 * @author jason
 */
public class CustomLoggerContextListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

	private boolean started = false;
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
		if (started) return;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isResetResistant() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onStart(LoggerContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(LoggerContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop(LoggerContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLevelChange(Logger logger, Level level) {
		// TODO Auto-generated method stub
		
	}

}
