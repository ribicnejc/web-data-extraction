package roadrunner.AST;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.ArrayList;

public class RElement extends RNode {

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
        for(RNode node : getChildren())
            node.printSelf(depth+1);
        for(int i = 0; i < depth; i++) System.out.print("  ");
        System.out.println("</" + tag + ">");
    }

    // CONSTRUCTORS
    public RElement(String tag) {
        super();
        this.tag = tag;
    }

    public RElement(RNode parent, String tag) {
        super(parent);
        this.tag = tag;
    }

    public RElement(RNode parent, ArrayList<RNode> children, String tag) {
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
