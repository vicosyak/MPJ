import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.*;

public class Task4 {
    public static void main(String[] args) {
        String urlString = "https://www.youtube.com/";
        Map<String, Integer> tags = new TreeMap<>();

        try {
            URL url = new URL(urlString);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            Pattern pattern = Pattern.compile("<([a-zA-Z0-9]+)\\b[^>]*>");

            while ((inputLine = in.readLine()) != null) {
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    String tag = matcher.group(1).toLowerCase();
                    tags.put(tag, tags.getOrDefault(tag, 0) + 1);
                }
            }
            in.close();

            for (Map.Entry<String, Integer> entry : tags.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }

            List<Map.Entry<String, Integer>> list = new ArrayList<>(tags.entrySet());
            list.sort(Map.Entry.comparingByValue());

            for (Map.Entry<String, Integer> entry : list) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}