import javax.naming.InitialContext;
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
        int move = moveSequence.get(moveSeqIndex);
        moveSeqIndex++;
        if (moveSeqIndex == moveSequence.size()){
            hasNextMoveReady = false;
        }
        UIController.printAIPlayerMove(state.isP1Turn() ? "P1" : "P2",move);
        return move;
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
        boolean gameOver = game.goalTest(node.state);
        if (gameOver){
            return evaluateFinishedGame(node.state);
        }

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

    public void generateIntAndExtChildren(Node root, List<Node> rootIntChildren, List<Node> rootExtChildren){
        //A node is a intChild if it has the same player turn as its parent
        //A node is a extChild if it has a different player turn than its parent
        //TODO:
        //Get all moves
        //Execute all moves and see node type
        List<Node> children = expandNode(root);


    }

    //Given a node, returns its children
    public List<Node> expandNode(Node node){
        List<Node> resultingNodes = null;

        State initState = node.state;
        ArrayList<Integer> moves = game.Actions(initState);
        for(int i = 0; i < moves.size(); i++){
            State newState = game.performMove(initState,moves.get(i));
            Node child = new Node(newState, !(newState.isP1Turn() == initState.isP1Turn()), node);
            resultingNodes.add(child);
        }

        return resultingNodes;
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

    public int evaluateFinishedGame(State state){
        int pointdiff = state.getData()[7] - state.getData()[0];
        if (!state.isP1Turn()){
            pointdiff *= -1;
        }
        if (pointdiff == 0 ){
            return 0;
        } else if (pointdiff > 0){
            return maximalValue;
        } else {
            return minimalVaue;
        }
    }

}
