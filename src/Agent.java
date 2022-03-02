import java.util.ArrayList;
import java.util.List;

public class Agent implements Player{
    private boolean isP1;
    ArrayList<Integer> moveSequence;
    boolean hasNextMoveReady = false;
    int moveSeqIndex = 0;
    int searchDepth = 5;
    int minimalVaue = -10000;
    int maximalValue = 10000;
    Node lastChildVisisted;
    Game game = new Game();

    public Agent(boolean isP1){
        this.isP1 = isP1;
    }

    public int getMove(State state) {
        if (!hasNextMoveReady){
            calculateMoveSeq(state);
            if (moveSequence.size()==0){
                System.out.println("ERROR - Agent was asked for next move, but none were possible");
            }
            hasNextMoveReady = true;
            moveSeqIndex = 0;
        }
        int result = moveSequence.get(moveSeqIndex);
        moveSeqIndex++;
        if (moveSeqIndex == moveSequence.size()){
            hasNextMoveReady = false;
        }
        return result;
    }

    public void calculateMoveSeq(State origianlState){
        //Clone state so we don't mess up the original
        State simState = origianlState.clone();
        Node rootNode = new Node(simState, true, null);



        ArrayList<Integer> moveSeq = new ArrayList<Integer>();
        //Make each index be the move it chooses at a given node.
        //This way we may move all the way through our composite node.
        moveSequence = moveSeq;
    }

    public int minMax(Node node, int remaingDepth, boolean maximizing){
        //Generate internal children
        //Generate external children FROM internal children



        //Perform minmax on external children
        int val;
        Node bestInitalMoveNode = null;
        if (remaingDepth==0){
            return evaluateState(node.state);
        }
        if (maximizing){
            val = minimalVaue;
            for (Node extChild: node.extChildren) {
                int childVal = minMax(extChild, remaingDepth-1, false);
                if (childVal > val){
                    val = childVal;
                    bestInitalMoveNode = extChild;
                }
            }
        } else {
            val = maximalValue;
            for (Node extChild: node.extChildren) {
                int childVal = minMax(extChild, remaingDepth-1, true);
                if (childVal < val){
                    val = childVal;
                    bestInitalMoveNode = extChild;
                }
            }
        }
        if (remaingDepth == searchDepth){
            lastChildVisisted = bestInitalMoveNode; //Lets us find the best move-sequence from the roots child-node
        }
        return val;
    }

    void generateIntAndExtChildren(Node root, List<Node> intChildren, List<Node> extChildren){
    }

    //Given a node, returns its children
    List<Node> expandNode(Node node){
        return null;
    }


    //Evaluates how good a state is for this agent
    public int evaluateState(State state){
        //Currently the evaluation is only based on how many seeds are in the banks
        int bankPosition = 0;
        if(isP1){
            bankPosition = 7;
        }
        int myBank = state.getData()[bankPosition];
        int opponentBank = state.getData()[(bankPosition+7)%14];

        return myBank - opponentBank;
    }

}
