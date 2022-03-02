public class UIPlayer implements Player{
    //The human player playing from the terminal
    public int getMove(State state) {
        return UIController.getUserMove(state);
    }

    @Override
    public void setIsP1(boolean isP1) {
        //This is pointless for UIPlayers, but important for agents.
    }
}
