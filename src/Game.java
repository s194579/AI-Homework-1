public class Game {
    private State gameState;

    public Game() {
        //Setting up the initial state
        int[] initialState = new int[14];
        initialState = initialSetUp(initialState);
        gameState = new State(initialState,true);
    }

    private int[] initialSetUp(int[] state) {
        state[0] = 0;
        state[7] = 0;
        for(int i = 1; i < 7; i++){
            state[i] = 4;
            state[i+7] = 4;
        }
        return state;
    }

    public boolean goalTest(){
        int[] stateData = gameState.getData();
        boolean p1Done = true; //Default values
        boolean p2Done = true;
        for(int i = 1; i < 7; i++){
            if(stateData[i] != 0 ){
                p1Done = false;
            }
            if(stateData[i+7] != 0){
                p2Done = false;
            }
        }
        return p1Done || p2Done;
    }

    public State performMove(){
        return gameState;
    }

    public State getState(){
        return gameState;
    }

}
