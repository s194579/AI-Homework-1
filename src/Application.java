import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        //Create Game
        //Create Player
        //Create Agent
        //Create Game Controller
        //Create UI controller
        int searchTimeMillis = 0;
        boolean isPlayerAI = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player1:");
        String player1 = scanner.nextLine();

        System.out.println("Player2:");
        String player2 = scanner.nextLine();

        System.out.println("Number of seeds in each house:");
        int initialSeedsPrHouse = scanner.nextInt();

        if(player1.equals("AI") || player2.equals("AI")) {
            System.out.println("Time the AI has on each search layer:");
            searchTimeMillis = scanner.nextInt();
        }

        GameController gc = new GameController();

        if (player1.equals("AI")) {
            gc.p1 = new Agent(true, searchTimeMillis);
        }else {
            gc.p1 = new UIPlayer();
        }
        if (player2.equals("AI")) {
            gc.p2 = new Agent(false, searchTimeMillis);
        }else {
            gc.p2 = new UIPlayer();
        }

        gc.start(initialSeedsPrHouse);
    }
}
