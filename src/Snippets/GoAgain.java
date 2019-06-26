package Snippets;

import Week1.Excercises.TextIO;

public class GoAgain {
    boolean goAgain;
    do {
        TextIO.putln();
        TextIO.put("Do you want to solve another equation? ");
        goAgain = TextIO.getlnBoolean();

    } while (goAgain);
}
