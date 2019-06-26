package Snippets;

import Week1.Excercises.TextIO;

public class TryCatch {
    try {
        // some routines
    }
        catch (IllegalArgumentException e) {
        TextIO.putln("Sorry, I can't find a solution.");
        TextIO.putln(e.getMessage());
    }
}
