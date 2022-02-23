public class Game {
    private State gameState;

    public Game() {
        //Setting up the initial state
        int[] initialState = new int[14];
        initialState = initialSetUp(initialState);
        gameState = new State(initialState);


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

    private boolean goalTest(State state){
        int[] stateData = state.getData();
        for(int i = 1; i < 7; i++){
            if(stateData[i] != 0 ){
                
            }
        }
    }

}
