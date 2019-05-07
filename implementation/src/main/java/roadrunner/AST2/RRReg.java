package roadrunner.AST2;

import org.jsoup.nodes.Node;

import java.util.ArrayList;

public class RRReg extends RRNode {

    public static final int TYP_OPT = 1;
    public static final int TYP_ITR = 2;
    public static final int TYP_PCD = 3;

    private int type;
    private String content;

    @Override
    public void printSelf(int depth) {
        for(int i = 0; i < depth; i++) System.out.print("  ");
        switch(type) {
            case TYP_OPT:
                System.out.println("( " + content + " )?");
                break;
            case TYP_ITR:
                System.out.println("( " + content + " )+");
                break;
            case TYP_PCD:
                System.out.println("#PCDATA");
                break;
            default: throw new IllegalArgumentException("Internal error");
        }
    }


    // CONSTRUCTORS
    public RRReg(int type, String content) {
        super();
        this.type = type;
        this.content = content;
    }

    public RRReg(RRNode parent, int type, String content) {
        super(parent);
        this.type = type;
        this.content = content;
    }

    public RRReg(RRNode parent, ArrayList<RRNode> children, int type, String content) {
        super(parent, children);
        this.type = type;
        this.content = content;
    }

    // GETTERS AND SETTERS
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // NOT NEEDED
    @Override
    void parseDocument(Node node) {}
}
