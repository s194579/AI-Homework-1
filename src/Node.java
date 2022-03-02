import java.util.ArrayList;

public class Node {
    private ArrayList<Node> internalChildren;
    private ArrayList<Node> externalChildren;
    private Node parent;
    private boolean turnRoot;
    private State state;

    public Node(Node parent, boolean turnRoot){
        this.parent = parent;
        this.turnRoot = turnRoot;
    }

}
