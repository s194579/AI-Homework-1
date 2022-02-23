public class Game {
    private State gameState;
    private int seeds;

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

    public State performMove(int house){
        seeds = gameState.getData()[house];
        gameState.getData()[house] = 0;
        int location = (house + 1) % 14;

        // puting seeds into house and stores
        while(seeds > 0){
            if (location == 7 && gameState.isP1Turn()){
                gameState.getData()[location]++;
                seeds--;
            //
            }else if (location == 0 && !gameState.isP1Turn()){
                gameState.getData()[location]++;
                seeds--;
            }else{
                gameState.getData()[location]++;
                seeds--;
            }
            if(seeds != 0) {
                location = (location + 1) % 14;
            }else{
                if(gameState.getData()[location]>1 && location != 7 && location != 0){
                    seeds = gameState.getData()[location];
                    gameState.getData()[location] = 0;
                    location = (location + 1) % 14;
                }
            }
        }
        if(gameState.isP1Turn()){
            if (location != 7){
                gameState.setP1Turn(false);
            }
        }else{
            if (location != 0){
                gameState.setP1Turn(true);
            }
        }
        return gameState;
    }

    public State getState(){
        return gameState;
    }
    private void addSeed(int location){
        gameState.getData()[location]++;
        seeds--;
    }
    private void takeSeeds(int location){
        seeds = gameState.getData()[location];
        gameState.getData()[location] = 0;
    }

}
