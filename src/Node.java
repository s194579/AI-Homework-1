import java.util.ArrayList;
import java.util.List;

public class Node {
    public State state;
    public boolean turnRoot; //Tells if node represents the first state in a player's turn
    public Node parent;
    public List<Node> extChildren;
    public List<Node> intChildren;
    public List<Node> intLeaves;
    public int lastMovePerformed;

    public Node(State state, boolean turnRoot, Node parent){
        this.state = state;
        this.turnRoot = turnRoot;
        this.parent = parent;
        extChildren = new ArrayList<Node>();
        intChildren = new ArrayList<Node>();
        intLeaves = new ArrayList<Node>();
    }

    public Node(State state, boolean turnRoot, Node parent, int lastMovePerformed){
        this.state = state;
        this.turnRoot = turnRoot;
        this.parent = parent;
        extChildren = new ArrayList<Node>();
        intChildren = new ArrayList<Node>();
        intLeaves = new ArrayList<Node>();
        this.lastMovePerformed = lastMovePerformed;
    }
}


