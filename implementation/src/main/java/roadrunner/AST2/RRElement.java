package roadrunner.AST2;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.ArrayList;

public class RRElement extends RRNode {

    private String tag;

    void parseDocument(Node node) {
        Element element;
        element = (Element) node;

        tag = element.tagName();

        parse(node);
    }

    public void printSelf(int depth) {
        for(int i = 0; i < depth; i++) System.out.print("  ");
        System.out.println("<" + tag + ">");
        for(RRNode node : getChildren())
            node.printSelf(depth+1);
        for(int i = 0; i < depth; i++) System.out.print("  ");
        System.out.println("</" + tag + ">");
    }

    // CONSTRUCTORS
    public RRElement(String tag) {
        super();
        this.tag = tag;
    }

    public RRElement(RRNode parent, String tag) {
        super(parent);
        this.tag = tag;
    }

    public RRElement(RRNode parent, ArrayList<RRNode> children, String tag) {
        super(parent, children);
        this.tag = tag;
    }

    // GETTERS AND SETTERS
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
