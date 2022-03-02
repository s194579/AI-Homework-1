public class Game {

    private int seeds;


    //Setting up the initial state
    public State initialSetUp() {
        int[] initialState = new int[14];
        initialState[0] = 0;
        initialState[7] = 0;
        for(int i = 1; i < 7; i++){
            initialState[i] = 4;
            initialState[i+7] = 4;
        }
        return new State(initialState,true);
    }

    public boolean goalTest(State gameState){
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

    public State performMove(State gameState,int house){
        int location = takeSeeds(gameState,house);

        //The while loop puts seeds into the proper houses and stores.
        while(seeds > 0){
            //We put seeds into p1's store if it is their turn.
            if (location == 7 && gameState.isP1Turn()){
                addSeed(gameState,location);
            //We put seeds into p2's store if it is their turn.
            }else if (location == 0 && !gameState.isP1Turn()){
                addSeed(gameState,location);
            //No matter whose turn it is, we put a seed any house we come across
            }else if (location != 0 && location != 7){
                addSeed(gameState,location);
            }
            if(seeds != 0) {
                location = (location + 1) % 14;
            }else{
                //If we have placed all seeds into a non-empty (>1) house, we must continue our move.
                if(gameState.getData()[location]>1 && location != 7 && location != 0){
                    location = takeSeeds(gameState,location);

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

    private void addSeed(State gameState,int location){
        //This method places a seed in a house or store
        gameState.getData()[location]++;
        seeds--;

    }
    private int takeSeeds(State gameState,int location){
        //This method takes all seeds from a house
        seeds = gameState.getData()[location];
        gameState.getData()[location] = 0;
        return (location + 1) % 14;
    }
}
