import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        //Create Game
        //Create Player
        //Create Agent
        //Create Game Controller
        //Create UI controller

        GameController gc = new GameController();
        gc.p1 = new Agent(true,50);
        gc.p2 = new Agent(false,50);
        gc.start(12);
    }
}
