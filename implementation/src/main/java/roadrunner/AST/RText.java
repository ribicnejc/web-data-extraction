package roadrunner.AST;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.util.ArrayList;

public class RText extends RNode {

    private String text;

    void parseDocument(Node node) {
        TextNode txt;
        txt = (TextNode) node;

        text = txt.getWholeText();

        parse(node);
    }

    // CONSTRUCTORS
    public RText(String text) {
        super();
        this.text = text;
    }

    public RText(RNode parent, String text) {
        super(parent);
        this.text = text;
    }

    public RText(RNode parent, ArrayList<RNode> children, String text) {
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
