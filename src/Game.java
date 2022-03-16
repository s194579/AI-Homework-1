import java.util.ArrayList;

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
        //The game is over if the player who's turn it is has no seeds in any house
        boolean p1Lost = gameState.isP1Turn() && gameState.p1HousesSum() == 0;
        boolean p2Lost = !gameState.isP1Turn() && gameState.p2HousesSum() == 0;
        return p1Lost || p2Lost;
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
            }
        }

        //If our last seed not placed in our own store, we lose the turn
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

    //MinMax methods
    //Actions: Gives legal actions in a current state
    public ArrayList<Integer> actions(State state){
        int[] data = state.getData();
        ArrayList<Integer> legalMoves = new ArrayList<Integer>();
        //Handle if we are P1 or P2
        int location = 0;
        if(!state.isP1Turn()){
            location+=7;
        }
        //Find houses where there are seeds
        for(int i = 1; i < 7; i++){
            if(data[i+location] != 0){
                legalMoves.add(i+location);
            }
        }
        return legalMoves;
    }
}
