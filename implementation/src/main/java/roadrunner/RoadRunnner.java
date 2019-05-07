package roadrunner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import roadrunner.AST.RElement;
import roadrunner.AST.RNode;
import roadrunner.AST.RText;
import utils.HTMLHelper;

/**
 * Road runner idea. Read two html documents. Create DOM tree for both of them and then
 * walk over the whole tree, and where you find attrs that are not the same, mark as important
 * data and continue walking. Important thing is that when finding different values, runner must
 * find position where the next "div" tag is the same.
 */
public class RoadRunnner {
    public static void main(String[] args) throws Exception{
        startRoadRunner(HTMLHelper.getHTMLString("input/rrsample/s1.html"),
                HTMLHelper.getHTMLString("input/rrsample/s2.html"));
    }

    public static void startRoadRunner(String page1, String page2) {
        Document document1 = Jsoup.parse(page1);
        Document document2 = Jsoup.parse(page2);

        Element neki = document1.getElementsByTag("html").first();
        RElement body = new RElement(neki.tagName());
        body.parse(neki);

        Element neki2 = document2.getElementsByTag("html").first(); //document2.body();
        RElement body2 = new RElement(neki2.tagName());
        body2.parse(neki2);

        searchRunner(body, body2);
        body.printSelf(0);
    }

    public static void searchRunner(RNode d1, RNode d2) {
        if (d1 instanceof RElement && d2 instanceof RElement) {
            if (((RElement)d1).getTag().equals(((RElement)d2).getTag())) {
                if (d1.getChildren().size() != d2.getChildren().size()) {
                    if (d1.sameChildren() && d2.sameChildren()) {
                        ((RElement) d1).setTag("(" + ((RElement) d1).getTag() + ")+");
                        ((RElement) d2).setTag("(" + ((RElement) d1).getTag() + ")+");
                    }
                }
            } else {
                ((RElement) d1).setTag("(" + ((RElement) d1).getTag() + ")?");
                ((RElement) d1).setTag("(" + ((RElement) d1).getTag() + ")?");
            }
            for (int i = 0; i < d1.getChildren().size(); i++) {
                for (int j = 0; j < d2.getChildren().size(); j++) {
                    if (i == j) {
                        searchRunner(d1.getChildren().get(i), d2.getChildren().get(j));
                    }
                }
            }
        } else if (d1 instanceof RText){
            if (!((RText)d1).getText().equals(((RText)d2).getText())) {
                ((RText) d1).setText("#PCDATA");
                ((RText) d2).setText("#PCDATA");
            }
        }
    }

}
