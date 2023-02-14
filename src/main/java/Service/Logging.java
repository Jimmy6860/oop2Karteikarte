package Service;

import java.io.IOException;
import java.util.logging.LogManager;

public class Logging {
    static {
        LogManager logManager = LogManager.getLogManager();
        try {
            logManager.readConfiguration(Logging.class.getClassLoader().getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
