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

    public int p1HousesSum(){
        return data[1] + data[2] + data[3] + data[4] + data[5] + data[6];
    }

    public int p2HousesSum(){
        return data[8] + data[9] + data[10] + data[11] + data[12] + data[13];
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

    /**
     * Returns P1_points - P2_points of a finished game
     */
    public int getPointDiffFinishedGame(){
        //Count p1's points positively and p2's negatively
        int pointdiff = getP1PointsFinishedGame() - getP2PointsFinishedGame();
        return pointdiff;
    }

    public int getP1PointsFinishedGame(){
        int points = data[7];
        boolean p2CausedFinish = p2HousesSum() == 0 && !isP1Turn();
        if (p2CausedFinish){
            //Count seeds in p1's houses as p1's points
            points += p1HousesSum();
        }
        return points;
    }

    public int getP2PointsFinishedGame(){
        int points = data[0];
        boolean p1CausedFinish = p1HousesSum() == 0 && isP1Turn();
        if (p1CausedFinish){
            //Count seeds in p1's houses as p1's points
            points += p2HousesSum();
        }
        return points;
    }
}
