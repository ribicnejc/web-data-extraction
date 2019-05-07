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

    public void parse(Node node) {
        for(Element el: getElementChildrenFromDocument(node)) {
            RElement rElement = new RElement(this, null);
            rElement.parseDocument(el);
            children.add(rElement);
        }

        for(TextNode txt : getTextChildrenFromDocument(node)) {
            RText rText = new RText(this, null);
            rText.parseDocument(txt);
            children.add(rText);
        }
    }

    ArrayList<Element> getElementChildrenFromDocument(Node node) {
        List<Node> allChildren = node.childNodes();
        ArrayList<Element> elementChildren = new ArrayList<>();
        for(Node child : allChildren) {
            if(child instanceof Element) elementChildren.add((Element) child);
        }
        return elementChildren;
    }

    ArrayList<TextNode> getTextChildrenFromDocument(Node node) {
        List<Node> allChildren = node.childNodes();
        ArrayList<TextNode> elementChildren = new ArrayList<>();
        for(Node child : allChildren) {
            if(child instanceof TextNode) elementChildren.add((TextNode) child);
        }
        return elementChildren;
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

    public void setChildren(ArrayList<RNode> children) {
        this.children = children;
    }
}
