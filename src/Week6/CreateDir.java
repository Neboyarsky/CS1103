package Week6;

import java.io.File;
public class CreateDir {

    public static void main(String args[]) {
        String dirname = "/users/michaelgenenko/ideacreated";
        File d = new File(dirname);

        // Create directory now.
        d.mkdirs();
    }
}