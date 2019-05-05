package regex;

import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Avtonet;
import objects.Overstock;
import objects.Rtvslo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import utils.HTMLHelper;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

    public static void main(String[] args) throws Exception {
        System.out.println("################## REGEX ################");
        System.out.println("############ Parsing overstock ###########");

        System.out.println(RegEx.parsePageOverstock(0));
        System.out.println(RegEx.parsePageOverstock(1));

        System.out.println("############ ################# ###########");
        System.out.println("############ Parsing rtvslo ###########");

        System.out.println(RegEx.parsePageRtvSlo(0));
        System.out.println(RegEx.parsePageRtvSlo(1));

        System.out.println("############ ################# ###########");
        System.out.println("############ Parsing avtonet ###########");

        System.out.println(RegEx.parsePageAvtoNet(0));
        System.out.println(RegEx.parsePageAvtoNet(1));

        System.out.println("##########################################");
    }

    public static String parsePageAvtoNet(int pageIndex) throws Exception {
        String path = "";
        switch (pageIndex) {
            case 0:
                path = "input/avto.net/Mazda RX-7.html";
                break;
            case 1:
                path = "input/avto.net/Toyota GT 86.html";
                break;
        }
        String htmlFile = HTMLHelper.getHTMLString(path);
        Matcher titleMatcher = getMatcher("<h1>([a-zA-Z \\-&;0-9 <>\\/]+)<", htmlFile);
        Matcher sellerMatcher = getMatcher("OglasMenuBox RoundedBottom ShadowBtm([\\n\\t\\r< a-z=\"0-9.\\/A-Z_\\-\\:\\;>!&Č]*)", htmlFile);
        Matcher contentMatcher = getMatcher("<div class=\"ContentBox ContentBox640 Rounded ShadowBtm\">([ \\n<!\\-a-zA-Z>0-9=\":\\/ž\\r\\t&;,()č.š+\\*\\'\\_Č]+)<!-", htmlFile);
        Matcher priceMathcer = getMatcher("OglasDataCenaTOP\"([a-zA-ZčČ \\n\\r\\t\" \\-:;= !0-9\\,\\.€$>]+)", htmlFile);

        titleMatcher.find();
        contentMatcher.find();
        contentMatcher.find();
        sellerMatcher.find();
        priceMathcer.find();

        Avtonet avtonet = new Avtonet(titleMatcher.group(1),
                priceMathcer.group(1),
                sellerMatcher.group(1),
                contentMatcher.group(1));

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(avtonet);
    }

    public static String parsePageOverstock(int pageIndex) throws Exception {
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

    public static String parsePageRtvSlo(int pageIndex) throws Exception {
        String path = "";
        switch (pageIndex) {
            case 0:
                path = "input/rtvslo.si/Volvo XC 40 D4 AWD momentum_ suvereno med najboljše v razredu - RTVSLO.si.html";
                break;
            case 1:
                path = "input/rtvslo.si/Audi A6 50 TDI quattro_ nemir v premijskem razredu - RTVSLO.si.html";
                break;
        }
        String htmlFile = HTMLHelper.getHTMLString(path);
        Matcher titleMatcher = getMatcher("<h1>([A-Za-z 0-9:]+)", htmlFile);
        Matcher subTitleMatcher = getMatcher("<div class=\"subtitle\">([A-Za-z 0-9:]+)", htmlFile);
        Matcher leadMatcher = getMatcher("<p class=\"lead\">([A-Za-z 0-9:\\.ž,ščćđ]+)", htmlFile);
        Matcher contentMathcer = getMatcher("<div class=\"article-header-media\">([\\n\\r\\t<a-z =\\\"\\-A-Z:\\/.>0-9_,šŠč]+)<\\/div", htmlFile);
        Matcher authorMatcher = getMatcher("<div class=\"author-name\">([a-zA-Z ]+)", htmlFile);
        Matcher publishedTimeMatcher = getMatcher("<div class=\"publish-meta\">([\\n\\r\\t0-9. a-z:]+)", htmlFile);

        authorMatcher.find();
        publishedTimeMatcher.find();
        titleMatcher.find();
        subTitleMatcher.find();
        leadMatcher.find();
        contentMathcer.find();
        Rtvslo rtvslo = new Rtvslo(
                authorMatcher.group(1),
                publishedTimeMatcher.group(1),
                titleMatcher.group(1),
                subTitleMatcher.group(1),
                leadMatcher.group(1),
                contentMathcer.group(1));

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rtvslo);
    }

    private static Matcher getMatcher(String regex, String content) {
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        return pattern.matcher(content);
    }
}
