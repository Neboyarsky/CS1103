package Week3.Exercises;

public class Q1 {
    static void showOutput(int mark) {
        if (mark == 0) {
            System.out.print("*");
        }
        else {
            System.out.print("[");
            showOutput(mark - 1);
            System.out.print(",");
            showOutput(mark - 1);
            System.out.print("]");
        }
    }

    public static void main(String[] args) {
        showOutput(0);
        System.out.println();
        showOutput(1);
        System.out.println();
        showOutput(2);
        System.out.println();
        showOutput(3);
    }
}