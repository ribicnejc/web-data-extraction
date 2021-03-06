package roadrunner.AST;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.util.ArrayList;
import java.util.List;

public abstract class RNode {

    private RNode parent;
    private ArrayList<RNode> children;

    abstract void parseDocument(Node node);
    abstract public void printSelf(int depth);

    public void parse(Node node) {

        List<Node> allChildren = node.childNodes();
        for(Node child : allChildren) {
            if(child instanceof Element) {
                RElement rElement = new RElement(this, null);
                rElement.parseDocument(child);
                children.add(rElement);
            }
            if(child instanceof TextNode) {
                RText rText = new RText(this, null);
                rText.parseDocument(child);
                if(!rText.getText().trim().equals(""))
                    children.add(rText);
            }
        }
    }

    // CONSTRUCTORS
    public RNode() {
        this(null, new ArrayList<>());
    }

    public RNode(RNode parent) {
        this(parent, new ArrayList<>());
    }

    public RNode(RNode parent, ArrayList<RNode> children) {
        this.parent = parent;
        this.children = children;
    }

    // GETTERS AND SETTERS
    public RNode getParent() {
        return parent;
    }

    public void setParent(RNode parent) {
        this.parent = parent;
    }

    public ArrayList<RNode> getChildren() {
        return children;
    }

    public boolean sameChildren() {
        RNode tmp = null;
        for (RNode node: children) {
            if (tmp == null) tmp = node;
            if (tmp instanceof RElement && node instanceof RElement) {
                if (((RElement) tmp).getTag().equals(((RElement) node).getTag())) {
                    tmp = node;
                } else return false;
            } else {
                return false;
            }
        }
        return true;
    }

    public void setChildren(ArrayList<RNode> children) {
        this.children = children;
    }
}
