public class State {
    private int[] data;
    private boolean p1Turn;

    public State(int[] array, boolean turn){
        //0: player1 store
        //1-6: player1 houses
        //7: player2 store
        //8-14: player2 houses
        data = array;
        p1Turn = turn;
    }
    public int[] getData(){
        return data;
    }
    public boolean isP1Turn(){
        return p1Turn;
    }

    public void setP1Turn(boolean b) {
        p1Turn = b;
    }
}
