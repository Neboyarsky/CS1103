package Week1.Labs.Lab1;

public class DeductionProblem {
    private int x, y, answer;

    public DeductionProblem() {
        x = (int)(10 + 90*Math.random()); // number from 10 to 99
        y = (int)(90 * Math.random()); // number from 0 to 89
        answer = x - y;
    }

    public String printProblem() {
        return "solve the problem: " + x + " - " + y;
    }

    public int getAnswer() {
        return answer;
    }

}
