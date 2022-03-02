import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UIController {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> allowedColumns = new ArrayList<>(Arrays.asList("A","B","C","D","E","F"));

    public static void printState(State state){
        int[] d = state.getData();
        System.out.printf(" \t%d\t%d\t%d\t%d\t%d\t%d%n",d[13],d[12],d[11],d[10],d[9],d[8]);
        System.out.printf("%d\t \t \t \t \t \t \t%d%n",d[0],d[7]);
        System.out.printf(" \t%d\t%d\t%d\t%d\t%d\t%d%n",d[1],d[2],d[3],d[4],d[5],d[6]);
        System.out.println(" \tA\tB\tC\tD\tE\tF");
        System.out.println("-----------------------------");
    }


    public static int getUserMove(State state){
        boolean isP1sTurn = state.isP1Turn();
        String column;
        while (true){
            if (isP1sTurn){
                System.out.println("Player 1's turn");
            } else{
                System.out.println("Player 2's turn");
            }
            System.out.println("Choose the letter corresponding to the house to pick from.");
            column = scanner.nextLine();
            if (!allowedColumns.contains(column)){
                System.out.println("You can only choose A, B, C, D, E or F.\n");
                continue;
            }

            int listIndex = allowedColumns.indexOf(column);
            int index = isP1sTurn ? listIndex + 1 :  13 - listIndex;
            boolean legalHouse = index <= 13 &&  state.getData()[index] != 0;
            if (!legalHouse){
                System.out.println("You cannot choose an empty house.\n");
                continue;
            }
            return index;
        }
    }

    public static void printAIPlayerMove(String playerName, int movePerformed){
        String column = movePerformed < 7 ? allowedColumns.get(movePerformed-1) : allowedColumns.get(13-movePerformed);
        System.out.println("AI player: " + playerName + " performed move: " + column);
    }
}
