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
        //The loop checks if either players houses are empty, if they are, we are done with the game.
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
        int seeds = gameState.getData()[house];
        gameState.getData()[house] = 0;
        int location = (house + 1) % 14;

        //The while loop puts seeds into the proper houses and stores.
        while(seeds > 0){
            //We put seeds into p1's store if it is their turn.
            if (location == 7 && gameState.isP1Turn()){
                gameState.getData()[location]++;
                seeds--;
            //We put seeds into p2's store if it is their turn.
            }else if (location == 0 && !gameState.isP1Turn()){
                gameState.getData()[location]++;
                seeds--;
            //No matter whose turn it is, we put a seed any house we come across
            }else{
                gameState.getData()[location]++;
                seeds--;
            }
            if(seeds != 0) {
                location = (location + 1) % 14;
            }else{
                //If we have placed all seeds into a non-empty (>1) house, we must continue our move.
                if(gameState.getData()[location]>1 && location != 7 && location != 0){
                    seeds = gameState.getData()[location];
                    gameState.getData()[location] = 0;
                    location = (location + 1) % 14;
                }
            }
        }
        //If our last seed was placed in a house, we get to move again.
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

}
