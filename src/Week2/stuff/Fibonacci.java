package Week2.stuff;

public class Fibonacci {

    public static void main(String[] args) {
        int N; // the number of element in Fibonacci sequence
        boolean goAgain = true; // want to try again?
        TextIO.putln("This will count Nth elemlent in Fibonacci sequence.");

        while (goAgain) {
                TextIO.putln("Please enter non-negative integer N: ");
                TextIO.putln("Preferably, not bigger than 50, or the calculation will take too long: ");
                N = TextIO.getInt(); // reading the number of element in F sequence
                TextIO.putln(FibCalc(N)); // printing the value of Nth element in F sequence
                TextIO.putln();
                TextIO.put("Do you want to do it again? ");
                goAgain = TextIO.getlnBoolean();
            }
        }

    private static int FibCalc(int n) {

        if (n == 0 || n == 1) {
            return 1;
        }
        return FibCalc(n - 2)+(FibCalc(n - 1));
    }


}
