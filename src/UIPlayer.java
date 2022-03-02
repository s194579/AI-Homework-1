public class UIPlayer implements Player{

    public int getMove(State state) {
        return UIController.getUserMove(state);
    }

}
