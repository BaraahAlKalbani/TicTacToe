import java.io.*;
import java.util.Scanner;

public class PlayTurn {
    // setting the object for the winCondition class
    MainGame mainGame = new MainGame();

   
    /**
     * a method to confirm the player symbol
     * @param playerNo : the number of the player that is playing
     * @param playerSymble : the Player Symbol
     */
    public static void settingPlayerSymbol(int playerNo, char playerSymble) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Player " + playerNo + " YOUR SYMBOL IS:" + playerSymble + "\nIs that Correct?");
        char confirm = scan.next().charAt(0);
        // Confirming player Symbol
        if (confirm == 'n' || confirm == 'N') {
            System.out.println("Player " + playerNo + " ENTER YOUR NEW SYMBOL:");
            playerSymble = scan.next().charAt(0);
        }
    }

 
    /**
     * a public method to get the input of the player and setting it
     * @param block : the game board
     * @param playerSymble : the symbol of the current player
     * @param gameWon : check if the game won or not
     * @param turn :the number of turns
     * @param playernumber :the number of the playing player
     */
    public static void playerTurn(char[][] block, char playerSymble, boolean gameWon, int turn, int playernumber) {

        System.out.println("Player " + playernumber + " Enter Your Move:");
        Scanner scan = new Scanner(System.in);
        char input = scan.next().charAt(0);
        boolean inputFound = false;

        // do while loop will repeat until the player input an available/valid position
        do {
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    if (input == block[row][column]) {
                        // Setting the player symbol to the position he choose if its available
                        block[row][column] = playerSymble;
                        inputFound = true;

                        // calling the checkForWinner method
                        gameWon = WinCondition.checkForWinner(block);
                    }
                }
            }
            // if the position was not valid_Already taken the player must enter Another
            // position until the input is found!
            if (!inputFound) {
                System.out.println("Position not Found!! Enter Another:");
                input = scan.next().charAt(0);
            }

            // if the input position not Found the above code block will repeat until its
            // found
        } while (!inputFound);

        // incrementing the players turn
        //turn++;

        // Checking if there is a match to end the game
        if (gameWon) {
            System.out.println("===================================");
            System.out.println("Player " + playernumber + " HAS WON !!!!!");
            MainGame.displayBoard(block, playerSymble, input);
            File saveFile = new File("data/save.txt");
            //delete the save file after the win
            if (saveFile.delete()) {
              
            }
            System.exit(0);
        }
        // if there is no win check if the turns reached its end and have a draw!!
        else if (turn > 9) {
            System.out.println("===================================");
            System.out.println("Draw!!");
            MainGame.displayBoard(block, playerSymble, input);
            File saveFile = new File("data/save.txt");
            //delete the save file after the draw
            if (saveFile.delete()) {
            }
            System.exit(0);
        }

    }
}
