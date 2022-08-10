package as.florenko.transfer.loggerTest;

import as.florenko.transfer.logger.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Date;

public class LoggerTest {
    private static final Date DATE = new Date();
    private static final String LOGGER_FILE_PATH = "D:\\Java Project\\transfer\\ServerLog.txt";
    private static final File SERVER_LOG = new File(LOGGER_FILE_PATH);
    private static final String STRING_TO_LOG = "*TEST STRING*";
    private StringBuilder sb = new StringBuilder();

    @BeforeAll
    public static void start() {
        System.out.println("START TESTING" + " " + DATE);
    }

    @AfterAll
    public static void end() {
        System.out.println("END TESTING" + " " + DATE);
    }

    @Test
    void loggerTest() throws InterruptedException {
        Logger logger = new Logger();
        logger.log(STRING_TO_LOG);
        boolean isExist = SERVER_LOG.exists();
        Thread.sleep(50); // Т.к Поток в Logger не успевает записать строку в ServerLog до начала чтения файла (readLog)
        Assertions.assertTrue(isExist);
        Assertions.assertEquals(STRING_TO_LOG, readLog());
    }

    public String readLog() {
        try (FileReader reader = new FileReader(SERVER_LOG)) {
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        int index = sb.indexOf("*");
        int testStringLength = STRING_TO_LOG.length();
        return sb.substring(index, testStringLength + index);
    }
}



