package WordsUsageSlack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

class CountWordsInFile {
    void countEachWord(String fileName, Map<String, Integer> words) throws FileNotFoundException {
        File src;
        Scanner file = new Scanner(fileName);
        while (file.hasNext()) {
            String word = file.next();
            Integer count = words.get(word);
            if (count != null) {
                count++;
            }
            else
                count = 1;
            words.put(word, count);
        }
    }
}