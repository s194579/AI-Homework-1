public class GameController {
    private Game game;
    private Player p1, p2;

    public void start(){
        game = new Game();

        while (!game.goalTest()){
            boolean p1Turn = game.getState().isP1Turn();
            int nextMove;
            if (p1Turn){
                nextMove = p1.getMove();
            } else {
                nextMove = p2.getMove();
            }
            game.performMove(nextMove);
        }
    }
}
