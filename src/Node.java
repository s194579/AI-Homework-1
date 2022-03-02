import java.util.List;

public class Node {
    public State state;
    public boolean turnRoot; //Tells if node represents the first state in a player's turn
    public Node parent;
    public List<Node> extChildren;
    public List<Node> intChildren;
}
