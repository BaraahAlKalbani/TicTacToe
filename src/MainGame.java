import java.util.*;

/*
*The class of TicTacToe Game Main code
*For 2 players only
*its console based game
*/
public class MainGame {

    public static void main(String[] args) {
        // TODO this code will run a TicTacToe Game and validate win/draw , position and player Symbol

        char[][] block = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' } };
        char player1Symble; 
        char player2Symble;
        
        //Initiating an object for the PlayTurn class
        PlayTurn playTurn =new PlayTurn();
        int turn = 1;

        Scanner scan = new Scanner(System.in);

        boolean gameWon = false;
        boolean player1 = true;
        
        //user 1 choose and confirm their Symbol 
        System.out.println("Player 1 ENTER YOUR SYMBOL:");
        player1Symble = scan.next().charAt(0);
        PlayTurn.settingPlayerSymbol(1,player1Symble);

        //player2  choose and confirm their Symbol 
        System.out.println("Player 2 ENTER YOUR SYMBOL:");
        player2Symble = scan.next().charAt(0);
        PlayTurn.settingPlayerSymbol(2,player2Symble);
        //Checking if the players symbol is the same or not
        while (player1Symble == player2Symble) {
            System.out.println("Player 2 symbole is the same as Player 1 \nChange YOUR SYMBOL!!:");
            player2Symble = scan.next().charAt(0);
            
        }

        //the start of the game!!
        while (!gameWon) {
            if (player1) {
                displayBoard(block, player1Symble, player2Symble);
                //running the code to get and check players choices
                playTurn.playerTurn(block,player1Symble,gameWon,turn, 1);
                player1 = false;
            }

            if (!player1) {
                displayBoard(block, player1Symble, player2Symble);
                playTurn.playerTurn(block,player2Symble,gameWon,turn, 2);
                player1 = true;   
            }
        }
    }
    
  //a public method to display the TicTacToe board and users Symbols.
    public static void displayBoard(char[][] block, char player1Symbole, char player2Symbole) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                System.out.print(" " + block[row][column] + " ");
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
