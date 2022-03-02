import java.util.ArrayList;

public class Agent implements Player{
    private boolean isP1;
    ArrayList<Integer> moveSequence;
    boolean hasNextMoveReady = false;
    int moveSeqIndex = 0;

    public int getMove(State state) {
        if (!hasNextMoveReady){
            calculateMoveSeq();
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

    public void calculateMoveSeq(){
        ArrayList<Integer> moveSeq = new ArrayList<Integer>();
        //Make each index be the move it chooses at a given node.
        //This way we may move all the way through our composite node.
        moveSequence = moveSeq;
    }

    public int minMax(State state){
        return 0;
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

    public void setIsP1(boolean isP1){
        this.isP1 = isP1;
    }

}
