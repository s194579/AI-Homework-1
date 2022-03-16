public class GameController {
    private Game game;
    private State gameState;
    public Player p1, p2;
    static int turn = 0;
    public double p1Wins = 0;
    public double p2Wins = 0;

    public void start(int initalSeedsPrHouse){
        turn = 0;
        game = new Game();
        gameState = game.initialSetUp(initalSeedsPrHouse);
        while (!game.goalTest(gameState)){
            UIController.printState(gameState, turn);
            boolean p1Turn = gameState.isP1Turn();
            int nextMove;
            if (p1Turn){
                nextMove = p1.getMove(gameState);
            } else {
                nextMove = p2.getMove(gameState);
            }
            game.performMove(gameState,nextMove);
            turn++;
        }
        UIController.printState(gameState, turn);
        UIController.printWinner(gameState.getP1PointsFinishedGame(), gameState.getP2PointsFinishedGame());
        assignPoints();
    }

    private void assignPoints(){
        int pointDiff = gameState.getPointDiffFinishedGame();
        if (pointDiff == 0){
            p1Wins += 0.5;
            p2Wins += 0.5;
        } else if (pointDiff > 0){
            p1Wins += 1;
        } else {
            p2Wins += 1;
        }

    }
}
