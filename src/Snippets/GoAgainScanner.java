package Snippets;


import java.util.InputMismatchException;
import java.util.Scanner;

public class GoAgainScanner {
    public static void main(String[] args) {
        boolean goAgain = false;
        Scanner scr = new Scanner(System.in);
        do {
            System.out.println("Try again? ");
            goAgain = scr.nextBoolean();
            if (goAgain == true) {
                System.out.print("let's bend something!\n");
            }

        }while (goAgain);
    }
}
