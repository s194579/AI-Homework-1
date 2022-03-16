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

        System.out.println("Perform tests before playing the game? Write \"test\" for testing or something else for playing.");
        if (scanner.nextLine().toLowerCase().equals("test")){
            System.out.println("Provide the search time in milliseconds (integer)");
            int testSearchTimeMillis = scanner.nextInt();
            System.out.println("Provide the number of seeds in each house initially");
            int testSeedsPrHouse = scanner.nextInt();
            System.out.println("Provide the number of games the agents should play (size of the test)");
            int numOfGames = scanner.nextInt();
            performAgentVsAgentTest(testSearchTimeMillis,testSeedsPrHouse, numOfGames);
        }

        System.out.println("Player1:");
        System.out.println("Write H for human player or AI for AI player");
        String player1 = scanner.nextLine();

        System.out.println("Player2:");
        System.out.println("Write H for human player or AI for AI player");
        String player2 = scanner.nextLine();

        System.out.println("Give the number of starting seeds in each house:");
        int initialSeedsPrHouse = scanner.nextInt();

        if(player1.equals("AI") || player2.equals("AI")) {
            System.out.println("Time the AI has on each search layer:");
            System.out.println("Time is in milliseconds");
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

    public static void performAgentVsAgentTest(int searchTimeMillis, int initialSeedsPrHouse, int numOfGames){
        GameController gc = new GameController();
        gc.p1 = new Agent(true,searchTimeMillis);
        gc.p2 = new Agent(false,searchTimeMillis);
        int testSize = numOfGames;
        for (int i = 0; i < testSize; i++) {
            gc.start(initialSeedsPrHouse);
        }
        System.out.println();
        System.out.println("P1 won: " + gc.p1Wins + " and P2 won " + gc.p2Wins + " out of " + testSize +" games");

    }
}
