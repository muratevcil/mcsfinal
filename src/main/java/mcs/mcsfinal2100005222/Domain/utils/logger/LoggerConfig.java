package mcs.mcsfinal2100005222.Domain.utils.logger;

import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Configuration
public class LoggerConfig {

    private static Logger logger;

    public LoggerConfig() throws IOException {
        this.logger = Logger.getLogger("com.vox");

        // Log dosyalarının saklanacağı /logs/ klasörünü oluştur
        File logDir = new File("logs");
        if (!logDir.exists()) {
            logDir.mkdir();
        }

        // Tarihi "yyyy-MM-dd_HH-mm-ss" formatında alıp dosya ismi olarak kullanıyoruz
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String logFileName = "logs/app_log_" + timestamp + ".log";

        // Her boot'ta yeni bir dosya oluşturuyor
        FileHandler fileHandler = new FileHandler(logFileName, true);
        fileHandler.setFormatter(new SimpleFormatter());

        this.logger.addHandler(fileHandler);
        this.logger.setLevel(Level.INFO);

        this.logger.log(Level.INFO, "LoggerConfig initialized.");
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}