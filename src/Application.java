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
        gc.start(4);
    }

    public static void performAgentVsAgentTest(){
        GameController gc = new GameController();
        gc.p1 = new Agent(true,50);
        gc.p2 = new Agent(false,50);
        int testSize = 10;
        for (int i = 0; i < testSize; i++) {
            gc.start(4);
        }
        System.out.println();
        System.out.println("P1 won: " + gc.p1Wins + " and P2 won " + gc.p2Wins + " out of " + testSize +" games");

    }
}
