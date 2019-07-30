package Snippets;

public class GoAgainTextIO {
    public static void main(String[] args) {

        boolean goAgain;
        {
            do {
                TextIO.putln();
                TextIO.put("Do you want to do it again? ");
                goAgain = TextIO.getlnBoolean();
            } while (goAgain);
        }
    }
}