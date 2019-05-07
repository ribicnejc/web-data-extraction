package roadrunner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import roadrunner.AST.RElement;
import utils.HTMLHelper;

/**
 * Road runner idea. Read two html documents. Create DOM tree for both of them and then
 * walk over the whole tree, and where you find attrs that are not the same, mark as important
 * data and continue walking. Important thing is that when finding different values, runner must
 * find position where the next "div" tag is the same.
 */
public class RoadRunnner {
    public static void main(String[] args) throws Exception{
        System.out.println(startRoadRunner(HTMLHelper.getHTMLString("input/overstock.com/jewelry01.html"),
                HTMLHelper.getHTMLString("input/overstock.com/jewelry02.html")));
    }

    public static String startRoadRunner(String page1, String page2) {
        Document document1 = Jsoup.parse(page1);
        Document document2 = Jsoup.parse(page2);

        Element neki = document1.body();
        RElement body = new RElement(neki.tagName());
        body.parse(neki);

        Element neki2 = document2.body();
        RElement body2 = new RElement(neki2.tagName());
        body2.parse(neki2);

        body.printSelf();
        return "test";
    }
}
