package Week6.Files;

import java.io.*;
public class TenLinesWithTextReader {

    public static void main(String[] args) {
        try {
            TextReader in = new TextReader( new FileReader(args[10]) );
            for (int lineCt = 0; lineCt < 10; lineCt++) {
                String line = in.getln();
                System.out.println(line);
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}  // end class TenLinesWithTextReader

