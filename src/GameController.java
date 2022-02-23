public class GameController {
    private Game game;
    public Player p1, p2;



    public void start(){
        game = new Game();

        while (!game.goalTest()){
            boolean p1Turn = game.getState().isP1Turn();
            int nextMove;
            if (p1Turn){
                nextMove = p1.getMove(game.getState());
            } else {
                nextMove = p2.getMove(game.getState());
            }
            game.performMove(nextMove);
        }
    }
}
