public class Application {
    public static void main(String[] args) {
        //Create Game
        //Create Player
        //Create Agent
        //Create Game Controller
        //Create UI controller

        GameController gc = new GameController();
        gc.p1 = new UIPlayer();
        gc.p2 = new UIPlayer();
        gc.start();
    }
}
