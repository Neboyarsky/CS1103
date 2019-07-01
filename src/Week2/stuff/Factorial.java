package Week2.stuff;

public class Factorial {
    public static void main(String[] args) {
        long A;
        boolean goAgain = true; // want to try again?

        TextIO.putln("This program calculates N! (factorial of N).");
        while (goAgain) {
            TextIO.put("Enter a positive integer N: ");
            A = TextIO.getInt();
            TextIO.putln(A + "! = " + factorial(A));
            TextIO.put("Do you want to do it again? ");
            goAgain = TextIO.getlnBoolean();
        }
        TextIO.put("Thanks for calculating factorials with us!");
    }

    /**
     *
     * @param n - argument in factorial function
     * @return - factorial value n! = 1*2*3*...*(n-1)*n
     */
    public static long factorial(long n){
        long fact;
        if (n==1) {
            fact = 1;
        } else {
            fact = n * factorial(n - 1);
        }
        return fact;
    }
}
