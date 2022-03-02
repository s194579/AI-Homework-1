public class UIPlayer implements Player{
    //The human player playing from the terminal
    public int getMove(State state) {
        return UIController.getUserMove(state);
    }


}
