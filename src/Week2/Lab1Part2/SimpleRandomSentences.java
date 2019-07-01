package Week2.Lab1Part2;

public class SimpleRandomSentences {
    /**
     * Arrays of words to be used when building random sentences
     */
    private static final String[] conjunction = {"and", "or", "but", "because"};
    private static final String[] proper_noun = {"Fred", "Jane", "Richard Nixon", "Miss America"};
    private static final String[] common_noun = {"man", "woman", "fish", "elephant", "unicorn"};
    private static final String[] determiner = {"A", "The", "Every", "Some"};
    private static final String[] adjective = {"big", "tiny", "pretty", "bald"};
    private static final String[] intransitive_verb = {"runs", "jumps", "talks", "sleeps"};
    private static final String[] transitive_verb = {"loves", "hates", "sees", "knows", "looks for", "finds"};

    public static void main(String[] args) {

        boolean goAgain = true;
        // prints a random sentence then asks whether the user wants to see another one
        while (goAgain) {
            randomSentence();
            System.out.println(".\n");
            TextIO.putln();
            TextIO.put("Do you want a new random sentence? ");
            goAgain = TextIO.getlnBoolean();
        }
    }

    /**
     * Combines a random noun phrase with a random verb phrase,
     * optionally adds another sentence glued to the original
     * one with a conjunction
     */
    private static void randomSentence() {
        randomNounPhrase();
        randomVerbPhrase();
        if (Math.random() > 0.75) {
            System.out.print(" " + randomItem(conjunction));
            randomSentence();
        }
    }

    /**
     * With 3/4 probability generates just a noun phrase
     * With 1/4 probability generates a noun phrase with
     * a determiner
     */
    private static void randomNounPhrase() {

        if (Math.random() > 0.75)
            System.out.print(" " + randomItem(proper_noun));
        else {
            System.out.print(" " + randomItem(determiner));
            if (Math.random() > 0.5)
                System.out.print(" " + randomItem(adjective));
            System.out.print(" " + randomItem(common_noun));
            if (Math.random() > 0.5) {
                System.out.print(" who");
                randomVerbPhrase();
            }
        }
    }


    /**
     * Generates a verb to be added to the noun,
     * with certain probability
     */
    private static void randomVerbPhrase() {

        if (Math.random() > 0.75)
            System.out.print(" " + randomItem(intransitive_verb));
        else if (Math.random() > 0.50) {
            System.out.print(" " + randomItem(transitive_verb));
            randomNounPhrase();
        } else if (Math.random() > 0.25)
            System.out.print(" is " + randomItem(adjective));
        else {
            System.out.print(" does not believe that");
            randomNounPhrase();
            randomVerbPhrase();
        }
    }

    /**
     * Selects a random element from an array of strings
    */
    private static String randomItem(String[] listOfStrings) {
        return listOfStrings[(int) (Math.random() * listOfStrings.length)];

    }
}
