import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            String maxLine = "";
            int maxWords = 0;

            while ((line = br.readLine()) != null) {
                int wordCount = line.split("\\s+").length;
                if (wordCount > maxWords) {
                    maxWords = wordCount;
                    maxLine = line;
                }
            }

            System.out.println(maxLine);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}