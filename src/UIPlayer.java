public class UIPlayer implements Player{
    private UIController uiController = new UIController();

    public int getMove(State state) {
        uiController.printState(state);
        return uiController.getUserMove(state.isP1Turn());
    }


}
