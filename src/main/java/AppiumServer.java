import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.tools.ant.types.LogLevel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AppiumServer {

    private static AppiumServer INSTANCE;
    private AppiumDriverLocalService service;

    private AppiumServer() {
        initComponents();
    }

    private void initComponents() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(4723);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, LogLevel.DEBUG.toString());
        builder.withEnvironment(new ProcessBuilder().environment());
        builder.withLogFile(createLoggerFile());
        service = AppiumDriverLocalService.buildService(builder);
    }

    public static AppiumServer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppiumServer();
        }
        return INSTANCE;
    }

    void startServer() {
        if (service != null && !service.isRunning()) {
            service.start();
        }
    }

    void stopServer(){
        if (service != null && !service.isRunning()) {
            service.stop();
        }
    }

    private File createLoggerFile() {
        // Specific Log File Path
        String fileName = new SimpleDateFormat("dd-MM-yyyy HHmm'.txt'").format(new Date());
        File logDirectory = new File("logs/");
        if (!logDirectory.exists()) {
            logDirectory.mkdir();
        }
        return new File(logDirectory, fileName);
    }

}
