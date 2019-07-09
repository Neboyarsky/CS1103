package Week3.Exercises;

public class MyFibonacci {
    public static int fibonacci(int n) {
        if (n == 1 || n ==0) {
            return 1;
        }
        else {
            return (fibonacci(n - 1) + fibonacci(n - 2));
        }
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        else {
            return n * factorial (n - 1);
        }
    }

    public static void main(String[] args) {
        TextIO.put("Enter a positive integer to count fibonacci number in this position\n");
        TextIO.put("And its factorial: \n");
        int n = TextIO.getlnInt();
        TextIO.put("Factorial of " + n + " is " + factorial(n) + "\n");
        TextIO.put("Fibonacci number in position " + n + " is " + fibonacci(n));
    }
}
