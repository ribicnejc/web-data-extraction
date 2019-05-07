package roadrunner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import roadrunner.AST2.RRElement;
import roadrunner.AST2.RRNode;
import roadrunner.AST2.RRText;
import utils.HTMLHelper;

import java.util.ArrayList;

public class ExpertRR {
    public static void main(String[] args) throws Exception{
        startRoadRunner(HTMLHelper.getHTMLString("input/rrsample/s1.html"),
                HTMLHelper.getHTMLString("input/rrsample/s1.html"));
    }

    private static void startRoadRunner(String page1, String page2) {
        Document document1 = Jsoup.parse(page1);
        Document document2 = Jsoup.parse(page2);

        Element neki = document1.getElementsByTag("html").first();
        RRElement wrapper = new RRElement(neki.tagName());
        wrapper.parse(neki);

        Element neki2 = document2.getElementsByTag("html").first(); //document2.body();
        RRElement sample = new RRElement(neki2.tagName());
        wrapper.parse(neki2);

        RRElement currW = wrapper;
        RRElement currS = sample;

        roadRun(currW, currS);
    }

    private static void roadRun(RRNode currW, RRNode currS) {
        if(currW instanceof RRElement && currS instanceof RRElement) {
            RRElement wrap = (RRElement) currW;
            RRElement samp = (RRElement) currS;

        }
        if(currW instanceof RRElement && currS instanceof RRText) {
            RRElement wrap = (RRElement) currW;
            RRText samp = (RRText) currS;

        }
        if(currW instanceof RRText && currS instanceof RRElement) {
            RRText wrap = (RRText) currW;
            RRElement samp = (RRElement) currS;

        }
        if(currW instanceof RRText && currS instanceof RRText) {
            RRText wrap = (RRText) currW;
            RRText samp = (RRText) currS;

            if(wrap.getText().equals(samp.getText())) {
                // leave in the wrapper
            } else {
                // replace with pcdata
                RRElement parent = (RRElement) wrap.getParent();
                ArrayList<RRNode> children = parent.getChildren();
                
            }
        }
    }
}
