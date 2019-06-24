package Lab1;

import java.util.Scanner;

public class StudentTest {
    public static void main(String[] args) {
        int finalScore = 0;
        int studentAnswer;
        Scanner kbd = new Scanner(System.in);

        AdditionProblem problem = new AdditionProblem();
        problem.printProblem();
        studentAnswer = kbd.nextInt();


    }
}
