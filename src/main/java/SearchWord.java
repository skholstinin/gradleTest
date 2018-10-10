import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.Callable;

public class SearchWord implements Callable<Queue> {
    static final Logger myLogger = Logger.getLogger(SearchWord.class);
    ArrayList<StringBuilder> listInputStringBuilder = null;
    FindingResource findingResource = null;
    private String[] words;
    private int currentItem = 0;

    public SearchWord(FindingResource findingResource, ArrayList<StringBuilder> listInputStringBuilder, int currentItem, String[] words) {
        this.findingResource = findingResource;
        this.listInputStringBuilder = listInputStringBuilder;
        this.words = words;
        this.currentItem = currentItem;
    }

    @Override
    public Queue call() {
        ArrayList<String> strArray = null;
        String spliter = null;
        if (listInputStringBuilder.get(currentItem).length() > 0) {
            spliter = listInputStringBuilder.get(currentItem).toString();
            strArray.toArray(spliter.split("[\\.!?]"));
            for (String w : words) {
                ArrayList<String> findingString = new ArrayList<>();
                Iterator str = strArray.iterator();
                while (str.hasNext()) {
                    String s = (String) str.next();
                    if (s.toLowerCase().contains(w.toLowerCase())) {
                        myLogger.info("find" + w);
                        findingString.add(s + ".\r\n");
                    }
                    str.remove();
                }
                findingResource.addItemToQueue(findingString);
            }
        }
        return findingResource.getQueue();
    }
}
