public class GameController {
    private Game game;
    private State gameState;
    public Player p1, p2;


    public void start(){
        game = new Game();
        gameState = game.initialSetUp();
        while (!game.goalTest(gameState)){
            UIController.printState(gameState);
            boolean p1Turn = gameState.isP1Turn();
            int nextMove;
            if (p1Turn){
                nextMove = p1.getMove(gameState);
            } else {
                nextMove = p2.getMove(gameState);
            }
            game.performMove(gameState,nextMove);
        }

        UIController.printWinner(gameState.getData()[7], gameState.getData()[0]);
    }
}
