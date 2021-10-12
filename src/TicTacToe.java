/*
10/12/2021

Write a Tic Tac Toe game where the player is
playing against the computer. As usual, if the
player gets a row, column, or diagonal full of
X's, they win. If the computer gets a row, column,
or diagonal full of O's they win. If the board is
full of symbols and neither get a full row, column, or
diagonal of their symbols, it is a tie.

Credit: https://www.youtube.com/channel/UC_fFL5jgoCOrwAVoM_fBYwA
 */

import java.util.*;

public class TicTacToe {

    static ArrayList <Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList <Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) throws InputMismatchException {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'—', '+', '—', '+', '—'},
                {' ', '|', ' ', '|', ' '},
                {'—', '+', '—', '+', '—'},
                {' ', '|', ' ', '|', ' '}};
        printGameBoard(gameBoard);

        while(true) {

            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9):");
            Random random = new Random();

            int playerPos = scan.nextInt();

            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) {

                System.out.println("Positions taken. Please enter a valid position");
                playerPos = scan.nextInt();
            }

            placePiece(gameBoard, playerPos, "player");

            String result = checkWin();

            if (result.length() > 0) {

                System.out.println(result);
                break;
            }

            int cpuPos = random.nextInt(9) + 1;

            while(playerPositions.contains(cpuPos) || cpuPositions.contains(playerPositions)) {

                cpuPos = random.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            result = checkWin();

            if (result.length() > 0) {

                System.out.println(result);
                break;
            }
            System.out.println(result);
        }

    }

    public static void printGameBoard(char[][] gameBoard) {

        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);

        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);

        }

        switch(pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }

    }

    public static String checkWin() {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List lowRow = Arrays.asList(7, 8, 9);

        List leftColumn = Arrays.asList(1, 4, 7);
        List midColumn = Arrays.asList(2, 5, 8);
        List rightColumn = Arrays.asList(3, 6, 9);

        List diagonal1 = Arrays.asList(1, 5, 9);
        List diagonal2 = Arrays.asList(7, 5, 3);

        List <List> win = new ArrayList<List>();
        win.add(topRow);
        win.add(midRow);
        win.add(lowRow);
        win.add(leftColumn);
        win.add(midColumn);
        win.add(rightColumn);
        win.add(diagonal1);
        win.add(diagonal2);

        for (List l : win) {

            if (playerPositions.containsAll(l)) {
                return "Congratulations, you win!";

            } else if (cpuPositions.containsAll(l)) {
                return "Gamer over. the CPU wins.";

            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "Tie game.";
            }
        }

        return "";
    }

}
