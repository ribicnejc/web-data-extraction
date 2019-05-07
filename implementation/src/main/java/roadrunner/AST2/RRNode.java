package roadrunner.AST2;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.util.ArrayList;
import java.util.List;

public abstract class RRNode {

    private RRNode parent;
    private ArrayList<RRNode> children;

    abstract void parseDocument(Node node);
    abstract public void printSelf(int depth);

    public void parse(Node node) {

        List<Node> allChildren = node.childNodes();
        for(Node child : allChildren) {
            if(child instanceof Element) {
                RRElement RRElement = new RRElement(this, null);
                RRElement.parseDocument(child);
                children.add(RRElement);
            }
            if(child instanceof TextNode) {
                RRText rText = new RRText(this, null);
                rText.parseDocument(child);
                if(!rText.getText().trim().equals(""))
                    children.add(rText);
            }
        }
    }

    // CONSTRUCTORS
    public RRNode() {
        this(null, new ArrayList<>());
    }

    public RRNode(RRNode parent) {
        this(parent, new ArrayList<>());
    }

    public RRNode(RRNode parent, ArrayList<RRNode> children) {
        this.parent = parent;
        this.children = children;
    }

    // GETTERS AND SETTERS
    public RRNode getParent() {
        return parent;
    }

    public void setParent(RRNode parent) {
        this.parent = parent;
    }

    public ArrayList<RRNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<RRNode> children) {
        this.children = children;
    }
}
