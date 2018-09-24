package ro.msg.car.logger.impl;

import ro.msg.car.logger.ILogger;

public class SysoLogger implements ILogger {

	@Override
	public void logWarning(String message, Object... placeholders) {
		System.out.println("WARNING: " + String.format(message, placeholders));
	}

	@Override
	public void logInfo(String message, Object... placeholders) {
		System.out.println("INFO: " + String.format(message, placeholders));
	}

	@Override
	public void logError(String message, Object... placeholders) {
		System.out.println("ERROR: " + String.format(message, placeholders));
	}
	
	@Override
	public void logDebug(String message, Object... placeholders) {
		System.out.println("DEBUG: " + String.format(message, placeholders));
	}

}
