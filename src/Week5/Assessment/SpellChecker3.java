package Week5.Assessment;

import javax.swing.*;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class SpellChecker3{
    private static HashSet dictionaryHashSet;
    private static HashSet inputFileHashSet;
    private static TreeSet outputCorrectionTreeSet;

    public static void main(String args[])    {
        try{
            //Read the dictionary file words.txt
            readDictionaryFile();
            // Print the count of the words
            if(!dictionaryHashSet.isEmpty())            {
                System.out.println("Count of total number of words read from the dictionary-->"+dictionaryHashSet.size());}

            //Asks user to enter file name which contains the words that needs to be spell checked
            File inputFileForSpellCheckTest = getInputFileNameFromUser();
            Scanner testInputFileIn = new Scanner(inputFileForSpellCheckTest);
            inputFileHashSet = new HashSet();
            while (testInputFileIn.hasNext()) {
                testInputFileIn.useDelimiter("[^a-zA-Z]+");
                String tk = testInputFileIn.next();
                inputFileHashSet.add(tk.toLowerCase());}
            if(!inputFileHashSet.isEmpty()){
                System.out.println("Count of total number of words read from the input test file-->"+inputFileHashSet.size());}

            //Check for spelling
            //Only print the words that are not found in the dictionary
            printCorrections();}
        catch(Exception e){
            e.printStackTrace();}}

    public static void readDictionaryFile(){
        try {
            Scanner filein = new Scanner(System.in);
            filein = new Scanner(new File("/users/michael.genenko/ideaprojects/cs1103/src/words.txt"));
            dictionaryHashSet = new HashSet();
            while (filein.hasNext()) {
                String tk = filein.next();
                dictionaryHashSet.add(tk.toLowerCase());}}
        catch(Exception e){
                e.printStackTrace();}}
    public static File getInputFileNameFromUser(){
        JFileChooser fileDialog = new JFileChooser();
        fileDialog.setDialogTitle("Select File for Input");
        int option = fileDialog.showOpenDialog(null);
        if (option != JFileChooser.APPROVE_OPTION)
            return null;
        else
            return fileDialog.getSelectedFile();}
    public static void printCorrections(){
        outputCorrectionTreeSet = new TreeSet();
        if(!inputFileHashSet.isEmpty()){
            Iterator itr = inputFileHashSet.iterator();
            while(itr.hasNext()){
                String inputWord = itr.next().toString();
                if(!dictionaryHashSet.contains(inputWord)){
                    String outputString = checkForSplitWords(inputWord);
                    if((outputString == null || outputString.trim().length()==0)){
                        outputCorrectionTreeSet.add(inputWord+": (no suggestions)");}
                    else{
                        outputCorrectionTreeSet.add(inputWord+": "+outputString);}
}
}
}
    if(!outputCorrectionTreeSet.isEmpty()){
            Iterator itr = outputCorrectionTreeSet.iterator();
            while(itr.hasNext()) {
                System.out.println(itr.next().toString());}
}
}
    public static String checkForSplitWords(String inputWord){
        String splitWords = "";
        int newPosition = 0;
        for(int i=0; i<inputWord.length();i++){
            if(dictionaryHashSet.contains(inputWord.substring(newPosition,i))){
                splitWords +=  inputWord.substring(newPosition,i) +" ";
                newPosition = i;}
}
        return splitWords;}
}