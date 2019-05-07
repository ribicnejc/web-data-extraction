package roadrunner.AST2;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.util.ArrayList;

public class RRText extends RRNode {

    private String text;

    void parseDocument(Node node) {
        TextNode txt;
        txt = (TextNode) node;

        text = txt.getWholeText().trim();

        parse(node);
    }

    public void printSelf(int depth) {
        for(int i = 0; i < depth; i++) System.out.print("  ");
        System.out.println(text);
        for(RRNode node : getChildren())
            node.printSelf(depth);
    }

    // CONSTRUCTORS
    public RRText(String text) {
        super();
        this.text = text;
    }

    public RRText(RRNode parent, String text) {
        super(parent);
        this.text = text;
    }

    public RRText(RRNode parent, ArrayList<RRNode> children, String text) {
        super(parent, children);
        this.text = text;
    }

    // GETTERS AND SETTERS
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
