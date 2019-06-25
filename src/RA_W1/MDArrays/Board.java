package RA_W1.MDArrays;

public class Board {
    public static void main(String[] args) {
        int size = 8;
        int board[][] = new int[size][size];
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                if ((i % 2) == (j % 2)) {
                    board[i][j] = 1;
                }
                else {
                    board[i][j] = 0;
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
