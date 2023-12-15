package utilities;

import io.qameta.allure.Allure;

public class AllureLogger {

    public static void addLogsToAllureReport(String logs, Throwable exception) {
        Allure.addAttachment("Logs", "text/plain", logs);
        if (exception != null) {
            Allure.addAttachment("Exception", "text/plain", exception.toString());
        }
    }
}
