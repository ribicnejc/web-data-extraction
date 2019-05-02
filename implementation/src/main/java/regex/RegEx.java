package regex;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import utils.HTMLHelper;

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
        Document document = Jsoup.parse(HTMLHelper.getHTMLString(path));

        return null;
    }

    public static String parsePageRtvSlo() throws Exception{
        return HTMLHelper.getHTMLString("input/rtvslo.si/Audi A6 50 TDI quattro_ nemir v premijskem razredu - RTVSLO.si.html");
    }
}
