package Week6.Files;

import java.io.File;
import java.util.Scanner;

/**
 * This program lists the files in a directory specified by
 * the user. The user is asked to type in a directory name.
 * If the name entered by the user is not a directory, a
 * message is printed and the program ends. Directories that contain
 * other directories show up with their content - other
 * directories and their files
 */
public class DirectoryListEnhanced {

    private static void DirContentList(File dir, String indentation) {
        String[] files; //files that current direcrlty contains
        System.out.println(indentation + "Directory \"" + dir.getName() + "\":");
        files = dir.list();
        indentation += "    "; // increase the indentation level to show the structure in a pretty way

        for (String s : files) {
            File file = new File(dir, s);
            if (file.isDirectory()) {
                DirContentList(file, indentation);
            } else {
                System.out.println(indentation + s);
            }
        }
    }

    public static void main(String[] args) {

        String directoryName; // Directory name entered by the user.
        File directory; // File object referring to the directory.
        String[] files; // Array of file names in the directory.
        Scanner scanner; // For reading a line of input from the user.
        scanner = new Scanner(System.in); // scanner reads from standard input.

        System.out.print("Enter a directory name to list its content: ");
        directoryName = scanner.nextLine().trim();
        directory = new File(directoryName);

        if (!directory.isDirectory()) {
            if (!directory.exists())
                System.out.println("There is no such directory!");
            else
                System.out.println("That file is not a directory.");
        } else {
/*
            files = directory.list();
            System.out.println("Files in directory \"" + directory + "\":");
            for (String file : files) {
                System.out.println(" " + file);
*/
            DirContentList(directory, " ");
        }
    } // end main()
}