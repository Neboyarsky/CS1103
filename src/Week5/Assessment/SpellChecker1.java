package Week5.Assessment;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SpellChecker1 {
   private static HashSet<String> dictionary = new HashSet<>();
   public static void main(String[] args) {
       // Call to create dictionary
       //Change the file name as per your directory structure
       createDictionary("Documents\\words.txt");

       // Printing size of the Dictionary
       System.out.println("Size of the dictionary is : " + dictionary.size());
      
       //Calling checkWords
      
       checkWords();
   }
   // This method will be used to read data from file and store it in dictionary ,
   // This dictionary can be further used to check the words whether they are
   // correctly spelled or not.
   static void createDictionary(String fileName) {
       try {
           Scanner filein = new Scanner(new File(fileName));
           while (filein.hasNext()) {
               String tk = filein.next();
               // Convert to lower letter
               tk.toLowerCase();
               // Add to dictionary
               dictionary.add(tk);
           }
       } catch (FileNotFoundException e) {
           JOptionPane.showMessageDialog(null, "Please jucorrect file.");
       }
   }
   static void checkWords() {
       File file = getInputFileNameFromUser();
       try {
           Scanner in = new Scanner(file);
           // Use delimeter
           in.useDelimiter("[^a-zA-Z]+");
           while (in.hasNext()) {
               String tk = in.next();
               // Convert to lower letter
               tk.toLowerCase();
               // Check if the word is not in dictionary , then print the suggestions
               if (!dictionary.contains(tk)) {

                   TreeSet<String> suggestions = corrections(tk, dictionary);

                   if(suggestions!=null)
                   {
                   // Print the word and the suggestions
                   System.out.println(tk + " : " + suggestions);
                   }
                   else
                   {
                       System.out.println(tk + " : " + "(no suggestions)");
                   }
               }
           }
       } catch (FileNotFoundException e) {
           JOptionPane.showMessageDialog(null, "Please Enter the correct file.");
       }
   }
   /**
   * Lets the user select an input file using a standard file selection dialog
   * box. If the user cancels the dialog without selecting a file, the return
   * value is null.
   */
   static File getInputFileNameFromUser() {
     JFileChooser fileDialog = new JFileChooser();
       fileDialog.setDialogTitle("Select File for Input");
       int option = fileDialog.showOpenDialog(null);
       if (option != JFileChooser.APPROVE_OPTION)
           return null;
       else
         return fileDialog.getSelectedFile();
   }
   static TreeSet<String> corrections(String badWord, HashSet<String> dictionary) {
       TreeSet<String> corrections = new TreeSet<>();
       int badWordLen = badWord.length();
       // Swapping i with i+1
       for (int i = 1; i < badWordLen - 1; i++) {
           corrections.add(
                   badWord.substring(0, i) + badWord.charAt(i + 1) + badWord.charAt(i) + badWord.substring(i + 2));
       }
       // deleting one char, skipping i
       for (int i = 0; i < badWordLen; i++) {
           corrections.add(badWord.substring(0, i) + badWord.substring(i + 1));
       }
       // inserting one char
       for (int i = 0; i < badWordLen + 1; i++) {
           for (char j = 'a'; j <= 'z'; j++)
               corrections.add(badWord.substring(0, i) + j + badWord.substring(i));
       }
       return corrections;
   }
}
