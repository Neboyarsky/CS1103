package Week2.Labs.L1P2;

public class SimpleRandomSentences {
    // List of words to be used when building random sentences
    static final String[] conjunction = {"and", "or", "but", "because"};
    static final String[] proper_noun = {"Fred", "Jane", "Richard Nixon", "Miss America"};
    static final String[] common_noun = {"man", "woman", "fish", "elephant", "unicorn"};
    static final String[] determiner = {"a", "the", "every", "some"};
    static final String[] adjective = {"big", "tiny", "pretty", "bald"};
    static final String[] intransitive_verb = {"runs", "jumps", "talks", "sleeps"};
    static final String[] transitive_verb = {"loves", "hates", "sees", "knows", "looks for", "finds"};

    public static void main(String[] args) {
        while (true) {
            randomSentence();
            System.out.println(".\n\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
        }
    }


    static void randomSentence() {
        randomNounPhrase();
        randomVerbPhrase();
        if (Math.random() > 0.75) {
            System.out.print(" " + randomItem(conjunction));
            randomSentence();
        }
    }


    static void randomNounPhrase() {

        if (Math.random() > 0.75)
            System.out.print(" " + randomItem(proper_noun));
        else {
            System.out.print(" " + randomItem(determiner));
            if (Math.random() > 0.5)
                System.out.print(" " + randomItem(adjective) + ".");
            System.out.print(" " + randomItem(common_noun));
            if (Math.random() > 0.5) {
                System.out.print(" who");
                randomVerbPhrase();
            }
        }
    }

    static void randomVerbPhrase() {

        if (Math.random() > 0.75)
            System.out.print(" " + randomItem(intransitive_verb));
        else if (Math.random() > 0.50) {
            System.out.print(" " + randomItem(transitive_verb));
            randomNounPhrase();
        } else if (Math.random() > 0.25)
            System.out.print(" is " + randomItem(adjective));
        else {
            System.out.print(" believes that");
            randomNounPhrase();
            randomVerbPhrase();
        }
    }

    static String randomItem(String[] listOfStrings) {
        return listOfStrings[(int) (Math.random() * listOfStrings.length)];

    }
}
