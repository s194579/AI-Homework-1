import java.util.ArrayList;

public class Agent implements Player{
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

}
