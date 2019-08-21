package Week5.Assessment;

import java.util.Scanner;
import java.util.HashSet;
import java.util.TreeSet;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;


/**
 * This program works as a spell checker, utilising a HashSet for storing the dictionary which is used for comparison
 * of a user selected file.  A TreeSet is used to compile a list of possible alternative spellings to any words that
 * the program recognises as badWord.
 * User selects files to check from their device with a JFileChooser dialog window.  
 */
public class SpellChecker2 {
	
    /**
     * User selects an input file using a file selection dialog box.
     * The return value is null if no file is selected.
     *
     * @return A file selected by the user, or null.
     */
    static File getUserInputFileName() {

        JFileChooser fileDialog = new JFileChooser();
        fileDialog.setDialogTitle("Select File for Input");
        int option = fileDialog.showOpenDialog(null);
        if (option != JFileChooser.APPROVE_OPTION)
            return null;
        else
            return fileDialog.getSelectedFile();

    } // end getUserInputFileName()

    
    /*
     * Provides correctly spelled possibilities to misspelled words (badWord).
     * Words are checked for incorrectness against words in words.txt file.
     * 
     * @param  String badWord, HashSet<String> dictionary.
     *
     * @return possibleWords.
     */
    static TreeSet<String> corrections(String badWord, HashSet<String> dictionary) {

        TreeSet<String> possibleWords =  new TreeSet<String>();
        String subStr1, subStr2, possibility;

        for (int i = 0; i < badWord.length(); i++) {

            // Remove character i from the word.
            subStr1 = badWord.substring(0, i);
            subStr2 = badWord.substring(i + 1);

            // Delete any one of the letters from the misspelled word.
            possibility = subStr1 + subStr2;
            if (dictionary.contains(possibility))
                possibleWords.add(possibility);

            // Change any letter in the misspelled word into any other
            // letter.    
            for (char ch = 'a'; ch <= 'z'; ch++) {
                possibility = subStr1 + ch + subStr2;
                if (dictionary.contains(possibility))
                    possibleWords.add(possibility);
            }

            // Divide the word into two substrings.
            subStr1 = badWord.substring(0, i);
            subStr2 = badWord.substring(i);

            // Insert any letter at any point in the misspelled word.
            for (char ch = 'a'; ch <= 'z'; ch++) {
                possibility = subStr1 + ch + subStr2;
                if (dictionary.contains(possibility))
                    possibleWords.add(possibility);
            }

            // Insert a space at any point in the misspelled word and check
            // that both of the words that are produced are in the dictionary.
            char ch = ' ';
            possibility = subStr1 + ch + subStr2;
            if (dictionary.contains(subStr1) && dictionary.contains(subStr2))
                      possibleWords.add(possibility);

        }

        // Swap any two neighbouring characters in the misspelled word.
        for (int i = 1; i < badWord.length(); i++) {
            subStr1 = badWord.substring(0, i - 1);
            char ch1 = badWord.charAt(i - 1);
            char ch2 = badWord.charAt(i);
            subStr2 = badWord.substring(i + 1);
            possibility = subStr1 + ch2 + ch1 + subStr2;
            if (dictionary.contains(possibility))
                possibleWords.add(possibility);
        }

        return possibleWords;

    } // end corrections()

    
    public static void main(String[] args) {

        Scanner words;
        HashSet<String> dict = new HashSet<String>();
        Scanner userFile;

        try {
        	
            //File location of words.txt should be changed to location on your device when testing this program.
            words = new Scanner(new File("/users/michael.genenko/ideaprojects/cs1103/src/words.txt"));

            while (words.hasNext()) {
                String word = words.next();
                dict.add(word.toLowerCase());
               
            }

            userFile = new Scanner(getUserInputFileName());

            // Skip over any non-letter characters in the file.
            userFile.useDelimiter("[^a-zA-Z]+");

            HashSet<String> nonWords = new HashSet<String>();
            while (userFile.hasNext()) {
                String userWord = userFile.next();
                userWord = userWord.toLowerCase();
                if (!dict.contains(userWord) && 
                    !nonWords.contains(userWord)) {

                    nonWords.add(userWord);
                    TreeSet<String> areWords = new TreeSet<String>();
                    areWords = corrections(userWord, dict);
                    System.out.print(userWord + ": ");
                    if (areWords.isEmpty())
                        System.out.println("(no suggestions)");
                    else {
                        int count = 0;
                        for (String isWord: areWords) {
                            System.out.print(isWord);
                            if (count < areWords.size() - 1)
                                System.out.print(", ");
                            else
                                System.out.print("\n");
                            count++;
                        }
                    }

                }

            }

        }
        catch (FileNotFoundException e) {
            System.exit(0);
        }

    } // end main()

} // end class SpellChecker
