import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agent implements Player{
    private boolean isP1;
    ArrayList<Integer> moveSequence;
    boolean hasNextMoveReady = false;
    int moveSeqIndex = 0;
    int searchDepth = 7;
    int minimalValue = -10000;
    int maximalValue = 10000;
    Node goalNodeThisTurn;
    Game game = new Game();
    public int extNodesEvaluated = 0;

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
        goalNodeThisTurn = null;
        return move;
    }

    public void calculateMoveSeq(State origianlState){
        extNodesEvaluated = 0;

        //Clone state so we don't mess up the original
        State simState = origianlState.clone();
        Node rootNode = new Node(simState, true, null);

        //This sets LastExtChildVisited to the contain the state, we want to get to
        miniMax(rootNode,searchDepth,true);

        System.out.println("Evaluated " + extNodesEvaluated + " nodes to find move seq");

        //Recreate move sequence
        ArrayList<Integer> moveSeq = new ArrayList<Integer>();
        Node currNode = goalNodeThisTurn;
        while (currNode.parent != null){
            moveSeq.add(currNode.lastMovePerformed);
            currNode = currNode.parent;
        }
        Collections.reverse(moveSeq);


        //Make each index be the move it chooses at a given node.
        //This way we may move all the way through our composite node.
        moveSequence = moveSeq;
    }

    public int miniMax(Node node, int remaingDepth, boolean maximizing){
        extNodesEvaluated++;
        //Base case checks
        boolean gameOver = game.goalTest(node.state);
        if (gameOver){
            return evaluateFinishedGame(node.state);
        }

        if (remaingDepth==0){
            return evaluateState(node.state);
        }

        //Generate children
        generateIntAndExtChildren(node,node.intChildren,node.extChildren, node.intLeaves);

        //Perform minmax on external children
        int miniMaxScore;
        Node bestInitalMoveNode = null;

        if (maximizing){
            miniMaxScore = minimalValue;
            for (Node leaf: node.intLeaves){
                int endVal = evaluateFinishedGame(leaf.state);
                if (endVal > miniMaxScore){
                    miniMaxScore = endVal;
                    bestInitalMoveNode = leaf;
                }
            }

            for (Node extChild: node.extChildren) {
                int childVal = miniMax(extChild, remaingDepth-1, false);
                if (childVal > miniMaxScore){
                    miniMaxScore = childVal;
                    bestInitalMoveNode = extChild;
                }
            }
        } else {
            miniMaxScore = maximalValue;
            for (Node leaf: node.intLeaves){
                int endVal = evaluateFinishedGame(leaf.state);
                if (endVal < miniMaxScore){
                    miniMaxScore = endVal;
                    bestInitalMoveNode = leaf;
                }
            }

            for (Node extChild: node.extChildren) {
                int childVal = miniMax(extChild, remaingDepth-1, true);
                if (childVal < miniMaxScore){
                    miniMaxScore = childVal;
                    bestInitalMoveNode = extChild;
                }
            }
        }
        //If we are in the root node, choose the best initial move.
        if (remaingDepth == searchDepth){
            goalNodeThisTurn = bestInitalMoveNode; //Lets us find the best move-sequence from the roots child-node
        }
        return miniMaxScore;
    }

    public void  generateIntAndExtChildren(Node root, List<Node> rootIntChildren, List<Node> rootExtChildren, List<Node> internalLeaves){
        //A node is a intChild if it has the same player turn as its parent
        //A node is a extChild if it has a different player turn than its parent
        List<Node> children = expandNode(root);

        //Sort child nodes
        for(int i = 0; i < children.size(); i++){
            if(children.get(i).turnRoot){
                rootExtChildren.add(children.get(i));
            } else {
                rootIntChildren.add(children.get(i));
                //If a child was an intNode, expand it.
                List<Node> descendants = expandNode(children.get(i));
                if (!descendants.isEmpty()){
                    children.addAll(descendants);
                } else {
                    internalLeaves.add(children.get(i));
                }
            }
        }
        //We now have all ext and int children of the root,
        //and can pick any ext child to do the same on, continuing the minmax chain
    }

    //Given a node, returns its children.
    public List<Node> expandNode(Node node){
        List<Node> children = new ArrayList<>();
        State initState = node.state;
        ArrayList<Integer> moves = game.Actions(initState);

        for(int i = 0; i < moves.size(); i++){
            State cloneState = initState.clone();
            State newState = game.performMove(cloneState,moves.get(i));
            //If player turn has changed, the node is an extChild
            Node child = new Node(newState, !(newState.isP1Turn() == initState.isP1Turn()), node, moves.get(i));
            children.add(child);
        }
        return children;
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
            return maximalValue-1;
        } else {
            return minimalValue+1;
        }
    }

}
