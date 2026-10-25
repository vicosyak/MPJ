import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static String[] MinSym(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new String[0];
        }

        String result = Arrays.stream(input.split("\\s+"))
                .min(Comparator.comparingLong(word -> word.chars().distinct().count()))
                .orElse("");

        return new String[]{result};
    }

    public static void main(String[] args) {
        String text = "abc aabb ccc";
        String[] output = MinSym(text);

        System.out.println(Arrays.toString(output));
    }
}