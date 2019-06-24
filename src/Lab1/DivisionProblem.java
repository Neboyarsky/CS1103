package Lab1;

public class DivisionProblem {
    private int x, y, answer;

    public DivisionProblem() {
        answer = (int)(10 * Math.random()); // number from 0 to 9
        y = (int)(10 * Math.random()); // number from 0 to 9
        x = answer * y;
    }

    public String printProblem() {
        return "solve the problem: " + x + " + " + y;
    }

    public int getAnswer() {
        return answer;
    }

}
