package WordsUsageSlack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class CountWordsInFile {
    static void countEachWord(String fileName, Map<String, Integer> words) throws FileNotFoundException {
        Scanner file = new Scanner(new File(fileName));
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

    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Integer> words = new HashMap<String, Integer>();
        countEachWord("/users/michael.genenko/desktop/support-escalations/2019-01-01.txt", words);
        System.out.println(words);
    }
}