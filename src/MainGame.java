import java.io.File;
import java.util.*;

/*
*The class of TicTacToe Game Main code
*For 2 players only
*its console based game
*/
public class MainGame {
    /**
     * this application will let 2 players resume old games if there is one
     */
    public static void main(String[] args) {
        // TODO this code will run a TicTacToe Game and validate win/draw , position and
        // player Symbol
        boolean resumeGame = false;
        boolean gameWon = false;
        boolean player1 = true;
        String saveFileName = "data/save.txt";
        int currentPlayer = 0;
        int turn = 1;
        Scanner scan = new Scanner(System.in);
        char[][] mainBlock = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' } };
        // will be filled by the load file
        char[][] resumeBlock = new char[3][3];
        char player1Symble = ' ';
        char player2Symble = ' ';

        File loadFile = new File(saveFileName);
        // If the Load file Is Already there
        if (loadFile.exists()) {
            System.out.println("Load game? y,n");
            char loadStatus = scan.next().charAt(0);
            if (loadStatus == 'y') {
                // get data from the load File
                SaveAndLoad.loadGame(saveFileName, currentPlayer, resumeBlock, turn);
                player1Symble = SaveAndLoad.loadPlayerSymbol(saveFileName)[0];
                player2Symble = SaveAndLoad.loadPlayerSymbol(saveFileName)[1];
                mainBlock = resumeBlock.clone();
                resumeGame = true;
            }
        } else {
            resumeGame = false;
        }

        // setting the player turn
        if (currentPlayer == 0 || currentPlayer == 1) {
            player1 = true;
        } else {
            player1 = false;
        }
        // if starting New Game
        if (!resumeGame) {
            // user 1 choose and confirm their Symbol
            System.out.println("Player 1 ENTER YOUR SYMBOL:");
            player1Symble = scan.next().charAt(0);
            PlayTurn.settingPlayerSymbol(1, player1Symble);

            // player2 choose and confirm their Symbol
            System.out.println("Player 2 ENTER YOUR SYMBOL:");
            player2Symble = scan.next().charAt(0);
            PlayTurn.settingPlayerSymbol(2, player2Symble);
            // Checking if the players symbol is the same or not
            while (player1Symble == player2Symble) {
                System.out.println("Player 2 symbole is the same as Player 1 \nChange YOUR SYMBOL!!:");
                player2Symble = scan.next().charAt(0);

            }
        }

        // the start of the game!!
        while (!gameWon) {
            if (player1) {
                displayBoard(mainBlock, player1Symble, player2Symble);
                // running the code to get and check players choices
                PlayTurn.playerTurn(mainBlock, player1Symble, gameWon, turn, 1);
                turn++;
                // saving the status of the game after each turn
                SaveAndLoad.saveGame(saveFileName, player1Symble, player2Symble, 2, mainBlock, turn);
                player1 = false;
            }

            if (!player1) {
                displayBoard(mainBlock, player1Symble, player2Symble);
                // running the code to get and check players choices
                PlayTurn.playerTurn(mainBlock, player2Symble, gameWon, turn, 2);
                turn++;
                SaveAndLoad.saveGame(saveFileName, player1Symble, player2Symble, 1, mainBlock, turn);
                player1 = true;
            }
        }
    }

    /**
     * a public method to display the TicTacToe board and users Symbols.
     * 
     * @param mainBlock:      the game board
     * @param player1Symbole  : First player Symbol
     * @param player2Symbole: Second Player Symbol
     */
    public static void displayBoard(char[][] mainBlock, char player1Symbole, char player2Symbole) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                System.out.print(" " + mainBlock[row][column] + " ");
            }
            if (row < 2)
                System.out.println("\n---------");

            else {
                System.out.println();
            }
        }
        System.out.println("\nPlayer1 Symbol: " + player1Symbole);
        System.out.println("Player2 Symbol: " + player2Symbole);
    }

}
