package Snippets;


import java.io.*;

public class TryReaders {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char c = 0;       //Reading character
        try {
            c = (char)br.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(c);
    }
}
