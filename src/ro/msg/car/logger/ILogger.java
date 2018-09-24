package ro.msg.car.logger;

public interface ILogger {

	void logWarning(String message, Object... placeholders);
	
	void logInfo(String message, Object... placeholders);
	
	void logError(String message, Object... placeholders);
	
	void logDebug(String message, Object... placeholders);
}
