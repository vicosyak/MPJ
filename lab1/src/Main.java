import java.util.Arrays;

public class Main {
    public static String[] minSym(String str1) {
        String[] words = str1.trim().split("\\s+");

        int minCount = Integer.MAX_VALUE;
        String minSymWord = "";

        for (int k = 0; k < words.length; k++) {
            int count = 0;
            String word = words[k];
            for (int i = 0; i < word.length(); i++) {

                boolean flag = true;

                for (int j = i + 1; j < word.length(); j++) {
                    if (word.charAt(i) == word.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    count++;
                }
            }
            if (count < minCount) {
                minCount = count;
                minSymWord = word;
            }
        }
        return new String[]{minSymWord};
    }

    public static void main(String[] args) {
        String[] result = minSym("");
        System.out.println(Arrays.toString(result));
    }
}
