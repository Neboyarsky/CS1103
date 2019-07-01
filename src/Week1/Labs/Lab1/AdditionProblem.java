package Week1.Labs.Lab1;

public class AdditionProblem {
    private int x, y, answer;

    public void generateProblem() {
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
