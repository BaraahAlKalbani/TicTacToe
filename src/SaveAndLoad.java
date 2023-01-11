import java.io.*;
import java.util.*;

public class SaveAndLoad {
    /**
     * this method will return the players saved Symbols
     * 
     * @param fileName : the file name and directory of the Save File
     * @return : an array of the players Symbols
     */
    public static char[] loadPlayerSymbol(String fileName) {
        char player1 = 0, player2 = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // Read the game state from the file
            String line = reader.readLine();
            player1 = line.charAt(0);
            line = reader.readLine();
            player2 = line.charAt(0);

            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
        char[] playersSymbols = { player1, player2 };
        return playersSymbols;
    }

    /**
     * this Method will save the current Game state
     * 
     * @param fileName      : the directory of the save file
     * @param player1       : player 1 Symbol
     * @param player2       : Player 2 Symbol
     * @param currentPlayer : the next player turn
     * @param board         : the current status of the game board
     * @param turn          : the number of played turns
     */
    public static void saveGame(String fileName, char player1, char player2, int currentPlayer, char[][] board,
            int turn) {
        try {
            File saveFile = new File(fileName);
            if (saveFile.createNewFile()) {
                System.out.println("SaveFile created successfully");
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            // Write the game state to the file
            writer.write(String.valueOf(player1) + "\n");
            writer.write(String.valueOf(player2) + "\n");
            writer.write(currentPlayer + "\n");
            writer.write(turn + "\n");
            writer.write("Board:\n");
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    writer.write(String.valueOf(board[i][j]));
                }
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    /**
     *  Load the game state from a file
     * @param fileName      : the directory of the save file
     * @param currentPlayer : the next player turn
     * @param board         : the current status of the game board
     * @param turn          : the number of played turns
     */
    public static void loadGame(String fileName, int currentPlayer, char[][] board, int turn) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            // Read the game state from the file
            String line = reader.readLine();
            line = reader.readLine();
            line = reader.readLine();
            if (line.charAt(0) == '1') {
                currentPlayer = 1;
            } else {
                currentPlayer = 2;
            }
            line = reader.readLine();
            turn = Integer.parseInt(line);
            reader.readLine(); // skip the "Board:" line
            ArrayList<char[]> rows = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                rows.add(line.toCharArray());
            }
            for (int i = 0; i < rows.size(); i++) {
                board[i] = rows.get(i);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }

}
