package Week1.Lab1;

public class MultiplicationProblem {
    private int x, y, answer;

    public MultiplicationProblem() {
        x = (int)(10 * Math.random()); // number from 0 to 9
        y = (int)(10 * Math.random()); // number from 0 to 9
        answer = x * y;
    }

    public String printProblem() {
        return "solve the problem: " + x + " * " + y;
    }

    public int getAnswer() {
        return answer;
    }

}
