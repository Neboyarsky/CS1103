/**
 * Using https://www.wrike.com/saml/metadata as a file-source
 * it is actually spring_saml_metadata.xml file stored on a web-service
 */

package Week1.Lab2;

import java.io.*;
import java.net.URL;

public class Lab2Part2 {

    private static void copyStream(InputStream in, OutputStream out)
            throws IOException {
        int oneByte = in.read();
        while (oneByte >= 0) { // negative value indicates end-of-stream
            out.write(oneByte);
            oneByte = in.read();
        }
    }

    public static void main(String[] args) {
        InputStream userInput = null;
        OutputStream output = null;

        try {
            userInput = new URL("https://www.wrike.com/saml/metadata").openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        try {
            copyStream(userInput, output);
        } catch (IOException e1) {
            e1.printStackTrace();
        }*/
    }
}
