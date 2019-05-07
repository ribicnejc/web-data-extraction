package roadrunner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
        System.out.println(startRoadRunner(HTMLHelper.getHTMLString("input/rrsample/s1.html"),
                HTMLHelper.getHTMLString("input/rrsample/s1.html")));
    }

    public static String startRoadRunner(String page1, String page2) {
        Document document1 = Jsoup.parse(page1);
        Document document2 = Jsoup.parse(page2);

        Element neki = document1.getElementsByTag("html").first();
        RElement body = new RElement(neki.tagName());
        body.parse(neki);

        Element neki2 = document2.getElementsByTag("html").first(); //document2.body();
        RElement body2 = new RElement(neki2.tagName());
        body2.parse(neki2);

        body.printSelf(0);
        return "test";
    }

    /*
    Tree rBuilder;

    fun search(d1, d2, rBuilder):
        if d1.tag() == d2.tag()
            if d1.childs == d2.childs
                rBuilder.addNode(d1.tag)
            else rBuilder.addNode(findIter(d1, d2))
            if d1.text == d2.text
                rBuilder.addNode(d1.text)
            else rBuilder.addNode(findText(d1, d2))
         else
            rBuilder.addNode(findOptional(d1, d2))

        for c1, c2 in d1, d2:
            align(c1, c2)
            search(c1, c2)
        for sib1, sib2 in d1, d2:
            align(sib1, sib2)
            search(sib1, sib2)

        rBuilder.node(d1.parent)
        c1 = d1.firstChild()
        c2 = d2.firstChild()
        if c1.tag == c2.tag

        if c1.isTextAttr() && c2.isTextAttr():
            if c1.text != c2.text:
 */

}
