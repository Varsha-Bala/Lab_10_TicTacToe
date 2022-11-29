import java.util.Scanner;

public class Main
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [][] = new String[ROW][COL];

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int rowMoveX = 0; int colMoveX = 0; int rowMoveO = 0; int colMoveO = 0;
        boolean playAgain = false;

        do
        {
            clearBoard();
            display();
            do
            {
                do
                {
                    rowMoveX = SafeInput.getRangedInt(in, "Player X row move [1, 2, 3] : ", 1, 3) - 1;
                    colMoveX = SafeInput.getRangedInt(in, "Player X column move [1, 2, 3] : ", 1, 3) - 1;
                    if (isValidMove(rowMoveX, colMoveX))
                    {
                        board[rowMoveX][colMoveX] = "X";
                        display();
                        break;
                    }
                    else
                    {
                        System.out.println("Player X move invalid. Try again");
                    }
                } while (true);

                if (isWin("X"))
                {
                    playAgain = SafeInput.getYNConfirm(in, "Player X wins. Play again?");
                    break;
                }

                if (isTie() == true)
                {
                    display();
                    playAgain = SafeInput.getYNConfirm(in, "It is a tie. Play again?");
                    break;
                }

                do
                {
                    rowMoveO = SafeInput.getRangedInt(in, "Player O row move [1, 2, 3] : ", 1, 3) - 1;
                    colMoveO = SafeInput.getRangedInt(in, "Player O column move [1, 2, 3] : ", 1, 3) - 1;
                    if (isValidMove(rowMoveO, colMoveO))
                    {
                        board[rowMoveO][colMoveO] = "O";
                        display();
                        break;
                    }
                    else
                    {
                        display();
                        System.out.println("Player O move invalid. Try again");
                    }
                } while (true);


                if (isWin("O"))
                {
                    playAgain = SafeInput.getYNConfirm(in, "Player O wins. Play again?");
                    break;
                }

                if (isTie() == true)
                {
                    display();
                    playAgain = SafeInput.getYNConfirm(in, "It is a tie. Play again?");
                    break;
                }
            } while (true);
        } while (playAgain);
    }

    private static void clearBoard() // sets all the board elements to a space
    {
        for (int row = 0; row < ROW; row++)
            for (int col = 0; col < COL; col++)
                board[row][col] = " ";
    }

    private static void display() // shows the Tic Tac Toe game used as part of the promt for the users move choice…
    {
        for (int row = 0; row < ROW; row++)
        {
            for (int col = 0; col < COL; col++)
            {
                System.out.print(board[row][col] + "|");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col) // returns true if there is a space at the given proposed move coordinates which means it is a legal move.
    {
        if (board[row][col] == " ")
            return true;
        else
            return false;
    }

    private static boolean isWin(String player) // checks to see if there is a win state on the current board for the specified player (X or O) This method in turn calls three additional methods that break down the 3 kinds of wins that are possible.
    {
        if (isColWin(player) || isRowWin(player) || isDiagnalWin(player))
            return true;
        else
            return false;
    }

    private static boolean isColWin(String player) // checks for a col win for specified player
    {
        boolean colWin = false;
        for (int col = 0; col < COL; col++)
        {
            colWin = false;
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player)
                colWin =  true;
            if (colWin == true)
            {
                //System.out.println("colWin true " + player);
                break;
            }
        }
        return colWin;
    }

    private static boolean isRowWin(String player) // checks for a row win for the specified player
    {
        boolean rowWin = false;
        for (int row = 0; row < ROW; row++)
        {
            rowWin = false;
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player)
                rowWin = true;
            if (rowWin == true)
            {
                //System.out.println("rowWin true " + player);
                break;
            }
        }
        return rowWin;
    }

    private static boolean isDiagnalWin(String player) // checks for a diagonal win for the specified player
    {
        boolean diaWin = false;
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            diaWin = true;
        else if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
            diaWin = true;
        return diaWin;
    }

    private static boolean isTie() // checks for a tie condition:
    {
        boolean tie = true;
        for (int row = 0; row < ROW; row++)
            for (int col = 0; col < COL; col++)
                if (board[row][col] == " ")
                    tie = false;
        return tie;
    }
}

