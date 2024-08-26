package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class IOSearcher {
   public static boolean search(String word, String... fileNames) {
        for (String fileName : fileNames) {
            //try-with-resources => insure that the resources that we open will be close at the end
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(word)) {
                        return true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
