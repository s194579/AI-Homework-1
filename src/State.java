public class State {
    private int[] data;
    private boolean p1Turn;

    public State(int[] array, boolean turn){
        //1-6: player1 houses
        //7: player1 store
        //8-14: player2 houses
        //0: player2 store
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

    public State clone(){
        int[] cloneData = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            cloneData[i] = data[i];
        }
        State cloneState = new State(cloneData, p1Turn);
        return cloneState;
    }
}
