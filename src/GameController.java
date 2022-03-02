public class GameController {
    private Game game;
    private State gameState;
    public Player p1, p2;


    public void start(){
        game = new Game();
        p1.setIsP1(true);
        p2.setIsP1(false);
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
    }
}
