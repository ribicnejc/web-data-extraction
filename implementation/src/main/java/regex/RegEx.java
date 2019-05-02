package regex;

import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Overstock;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import utils.HTMLHelper;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

    public static String parsePageAvtoNet(int pageIndex) throws Exception{
//        String path = "";
//        switch (pageIndex) {
//            case 0:
//                path = "input/avto.net/audi_a8.html";
//                break;
//            case 1:
//                path = "input/avto.net/ford_mustang.html";
//                break;
//        }
        return HTMLHelper.getHTMLString("input/avto.net/audi_a8.html");
    }

    public static String parsePageOverstock(int pageIndex) throws Exception{
        String path = "";
        switch (pageIndex) {
            case 0:
                path = "input/overstock.com/jewelry01.html";
                break;
            case 1:
                path = "input/overstock.com/jewelry02.html";
                break;
        }
        String htmlFile = HTMLHelper.getHTMLString(path);
        Matcher titleMatcher = getMatcher("<a href=(.*?)><b>([a-zA-Z0-9-\\.( ')]+)<\\/b><\\/a><br>", htmlFile);
        Matcher contentMatcher = getMatcher("<span class=\\\"normal\\\">([a-zA-Z \r\t\n.,0-9\\-'()]+)", htmlFile);
        Matcher listPriceMatcher = getMatcher("<td align=\"left\" nowrap=\"nowrap\"><s>([$0-9.,]+)<\\/s>", htmlFile);
        Matcher priceMathcer = getMatcher("<span class=\"bigred\"><b>([$0-9.,]+)<\\/b>", htmlFile);
        Matcher savingMatcher = getMatcher("<td align=\"left\" nowrap=\"nowrap\"><span class=\"littleorange\">([$0-9.,]+)", htmlFile);
        Matcher savingPercentMatcher = getMatcher("<td align=\"left\" nowrap=\"nowrap\"><span class=\"littleorange\">([$0-9., ]+)([()0-9%]+)", htmlFile);

        int i = 0;
        ArrayList<Overstock> overstocks = new ArrayList<>();
        while (titleMatcher.find() && contentMatcher.find() && listPriceMatcher.find() && priceMathcer.find() && savingMatcher.find() && savingPercentMatcher.find()) {
            Overstock overstock = new Overstock(listPriceMatcher.group(1),
                    priceMathcer.group(1),
                    savingMatcher.group(1),
                    savingPercentMatcher.group(2),
                    titleMatcher.group(2),
                    contentMatcher.group(1) + "\nClick here to purchase.");
            overstocks.add(overstock);
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(overstocks);
    }

    public static String parsePageRtvSlo() throws Exception{
        return HTMLHelper.getHTMLString("input/rtvslo.si/Audi A6 50 TDI quattro_ nemir v premijskem razredu - RTVSLO.si.html");
    }

    private static Matcher getMatcher(String regex, String content) {
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        return pattern.matcher(content);
    }
}
