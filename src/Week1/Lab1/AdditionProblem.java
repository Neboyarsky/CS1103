package Week1.Lab1;

public class AdditionProblem {
    private int x, y, answer;

    public AdditionProblem() {
        x = (int)(10 + 40*Math.random()); // number from 10 to 59
        y = (int)(40 * Math.random()); // number from 0 to 39
        answer = x + y;
    }

    public void printProblem() {
        System.out.println ("Solve this problem: " + x + " + " + y + " ");
    }

    public int getAnswer() {
        return answer;
    }
}
