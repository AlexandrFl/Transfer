package as.florenko.transfer.logger;

import java.io.*;
import java.util.Date;

public class Logger implements Loggerable {
    private static final Date DATE = new Date();
    private static final String loggerFilePath = "D:\\Java Project\\transfer\\ServerLog.txt";
    private static final File SERVER_LOG = new File(loggerFilePath);
    private static final BufferedWriter inLogFile;

    static {
        try {
            inLogFile = new BufferedWriter(new FileWriter(SERVER_LOG, false));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void log(String msg) {
        new Thread(() -> {
            if (SERVER_LOG.exists()) {
                try {
                    inLogFile.write(DATE + "\n" + msg);
                    inLogFile.newLine();
                    inLogFile.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
