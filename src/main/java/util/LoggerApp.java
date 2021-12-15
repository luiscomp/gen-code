package util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerApp {

	private static FileHandler handler;

	private static Logger logger;

	private static void iniciarLog() {
		try {
			if (handler == null) {
				handler = new FileHandler("logs/GenNode.log");
				handler.setFormatter(new SimpleFormatter());
			}

			if (logger == null) {
				logger = Logger.getLogger("5min.gennode");
				logger.addHandler(handler);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void logInfo(String mensagem) {
		iniciarLog();
		logger.log(Level.INFO, mensagem);
	}

	public static void logError(String mensagem, Throwable t) {
		iniciarLog();
		logger.log(Level.SEVERE, mensagem, t);
	}

}
