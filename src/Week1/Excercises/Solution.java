package Week1.Excercises;

import static Week1.Excercises.Equation.root;

/**
 * This program will compute and print one of the solutions
 * to an equation of the form A*X*X + B*X + C = 0, where
 * A, B, and C are numbers entered by the user.  It depends
 * on the non-standard class TextIO for doing input/output.
 */
public class Solution {

    public static void main(String[] args) {

        double a, b, c;   // Coefficients in the equation.
        double solution;  // The solution computed for the equation.
        boolean goAgain;  // Set to true if the user wants to
        //   solve another equation.

        TextIO.putln("This program will print a solution of an equation");
        TextIO.putln("of the form A*X*X + B*X + C = 0, where A, B, and");
        TextIO.putln("C are values that you enter.");

        do {

            /* Get the coefficients from the user. */

            TextIO.putln();
            TextIO.putln("Enter values for A, B, and C:");
            TextIO.put("A = ");
            a = TextIO.getlnDouble();
            TextIO.put("B = ");
            b = TextIO.getlnDouble();
            TextIO.put("C = ");
            c = TextIO.getlnDouble();
            TextIO.putln();

         /* Print the solution, or an error message, if
            there is no solution. */

            try {
                solution = root(a,b,c);
                TextIO.putln("A solution of the equation is " + solution);
            }
            catch (IllegalArgumentException e) {
                TextIO.putln("Sorry, I can't find a solution.");
                TextIO.putln(e.getMessage());
            }

            /* Find out whether the user wants to go again. */

            TextIO.putln();
            TextIO.put("Do you want to solve another equation? ");
            goAgain = TextIO.getlnBoolean();

        } while (goAgain);

    } // end main

}  // end class