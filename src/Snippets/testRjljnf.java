package Snippets;

import java.util.Scanner;

public class testRjljnf {
    public static void main(String[] args) {
        int str = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number to process:\n");
        try {
            str = sc.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("You entered \n" + str);

    }
}
