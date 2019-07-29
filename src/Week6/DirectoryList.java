package Week6;

import java.io.*;
import java.util.*;

public class DirectoryList {


    public static void main(String[] args) {

        String directoryName;  // Directory name entered by the user.
        File directory;        // File object referring to the directory.
        Scanner scanner;       // For reading a line of input from the user.

        scanner = new Scanner(System.in);  // scanner reads from standard input.

        System.out.print("Enter a directory name: ");
        directoryName = scanner.nextLine().trim();
        directory = new File(directoryName);

        if (directory.isDirectory() == false) {
            if (directory.exists() == false)
                System.out.println("This directory does not exist.");
            else
                System.out.println("Please select a directory, not a file.");
        }
        else {
            DirContentList(directory,"");
        }

    } // end main()


    private static void DirContentList(File dir, String indent) {
        String[] files;  // List of names of files in the directory.
        System.out.println(indent + "Directory \"" + dir.getName() + "\":");
        indent += "   ";  // Increase the indentation for listing the contents.
        files = dir.list();
        for (int i = 0; i < files.length; i++) {
            // If the file is a  directory, list its contents
            // recursively.  Otherwise, just print its name.
            File f = new File(dir, files[i]);
            if (f.isDirectory())
                DirContentList(f, indent);
            else
                System.out.println(indent + files[i]);
        }
    }
}

