
public class WinCondition {
    
  //a method to check the blocks in the board and see if there is any matches
    public static boolean checkForWinner(char[][] block) {
        boolean win = false;
        boolean rows = false;
        boolean columns = false;
        boolean diagonal = false;
        boolean diagonal2 = false;
        
        //checking by row
        for (int row = 0; row <= 2; row++) {
            if ((block[row][0] == block[row][1] && block[row][1] == block[row][2]))
                rows = true;
        }
        
        //checking by column
        for (int column = 0; column <= 2; column++) {
            if ((block[0][column] == block[1][column] && block[1][column] == block[2][column]))
                columns = true;
        }
        
        //checking by diagonal
        if ((block[0][0] == block[1][1] && block[1][1] == block[2][2]))
            diagonal = true;
        
        if ((block[0][2] == block[1][1] && block[1][1] == block[2][0]))
            diagonal2 = true;
        
        //check if there is any matches an set the win to true 
        if (rows == true || columns == true || diagonal == true || diagonal2 == true)
            win = true;

        return win;
    }
    

}
