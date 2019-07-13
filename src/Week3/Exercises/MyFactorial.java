package Week3.Exercises;

public class MyFactorial {
    static int myFactor(int n) {
        if (n == 0) {
            return 1;
        }
        else {
            return myFactor (n - 1) * n;
        }
    }

    static int myFibo(int n) {
        if (n == 0 || n == 1){
            return 1;
        }
        else {
            return (myFibo(n - 1) + myFibo(n - 2));
        }
    }

    public static void main(String[] args) {
        TextIO.put("\n The program counts factorial of a given positive integer.\n");
        TextIO.put("\n It also prints the item #N in Fibonacci sequence.\n");
        TextIO.put("\n Enter a positive integer.\n");
        int N = TextIO.getlnInt();
        TextIO.put("Factorial of " + N + " is " + myFactor(N) + "\n");
        TextIO.put("Fibonacci #" + N + " is " + myFibo(N) + "\n");
    }
}
