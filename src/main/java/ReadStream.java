import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.Callable;


public class ReadStream implements Callable<ArrayList<StringBuilder>> {
    static final Logger myLogger = Logger.getLogger(ReadStream.class);
    private static final int MAX_READ_SIZE = 10_000_000;
    ArrayList<StringBuilder> listStringBuilder = new ArrayList<>();
    private InputStream inputStream;
    private int finalLength = 0;


    public ReadStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    @Override
    public ArrayList<StringBuilder> call() {
        int length = 0;
        StringBuilder result = null;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            length = inputStream.available();
            myLogger.info("Read file size " + length);
            while (length > 0) {
                String newLine = System.getProperty("line.separator");
                String line;
                boolean flag = false;
                result = new StringBuilder();
                while (finalLength < MAX_READ_SIZE && (line = bufferedReader.readLine()) != null) {
                    finalLength += line.length();
                    result.append(flag ? newLine : "").append(line);
                    flag = true;
                }
                listStringBuilder.add(result);
                myLogger.info("Length current string " + result.length());
                if (finalLength == 0) {
                    break;
                }
                length -= finalLength;
                finalLength = 0;
            }
        } catch (IOException e) {
            myLogger.error("Error", e);
        }
        return listStringBuilder;
    }
}
