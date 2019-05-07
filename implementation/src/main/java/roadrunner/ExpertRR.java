package roadrunner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import roadrunner.AST2.RRElement;
import roadrunner.AST2.RRNode;
import roadrunner.AST2.RRReg;
import roadrunner.AST2.RRText;
import utils.HTMLHelper;

import java.util.ArrayList;

public class ExpertRR {
    public static void main(String[] args) throws Exception{
        startRoadRunner(HTMLHelper.getHTMLString("input/rrsample/s1.html"),
                HTMLHelper.getHTMLString("input/rrsample/s2.html"));
    }

    private static void startRoadRunner(String page1, String page2) {
        Document document1 = Jsoup.parse(page1);
        Document document2 = Jsoup.parse(page2);

        Element neki = document1.body(); //getElementsByTag("html").first();
        RRElement wrapper = new RRElement(neki.tagName());
        wrapper.parse(neki);

        Element doc2 = document2.body(); //document2.body();
        RRElement sample = new RRElement(doc2.tagName());
        sample.parse(doc2);


        roadRun(wrapper, sample);

        wrapper.printSelf(0);
    }

    private static boolean roadRun(RRNode currW, RRNode currS) {
        if(currW instanceof RRElement && currS instanceof RRElement) {
            RRElement wrap = (RRElement) currW;
            RRElement samp = (RRElement) currS;

            if(wrap.getTag().equals(samp.getTag())) {
                // proceed
                ArrayList<RRNode> wChildren = wrap.getChildren();
                ArrayList<RRNode> sChildren = samp.getChildren();
                for(int i = 0, j = 0; i < wChildren.size();) {
                    RRNode wChild = wChildren.get(i);
                    RRNode sChild = sChildren.get(j);
                    if(!roadRun(wChild, sChild)) {
                        // handle mismatch itr

                        // handle mismatch opt

                    } else {
                        i++;
                        j++;
                    }
                }
            } else {
                // mismatch
                return false;
            }

        }
        if(currW instanceof RRElement && currS instanceof RRText) {
            // mismatch
            RRElement wrap = (RRElement) currW;
            RRText samp = (RRText) currS;
            return false;
        }
        if(currW instanceof RRText && currS instanceof RRElement) {
            // mismatch
            RRText wrap = (RRText) currW;
            RRElement samp = (RRElement) currS;
            return false;
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
                int idx = children.indexOf(wrap);
                children.set(idx, new RRReg(parent, RRReg.TYP_PCD, ""));
            }
        }
        return true;
    }
}
