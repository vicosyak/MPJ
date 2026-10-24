import java.util.HashMap;
import java.util.Scanner;

class Translator {
    HashMap<String, String> dictionary = new HashMap<>();

    public void addWord(String eng, String ukr) {
        dictionary.put(eng.toLowerCase(), ukr.toLowerCase());
    }

    public String translatePhrase(String phrase) {
        String[] words = phrase.split(" ");
        String result = "";

        for (String word : words) {
            String w = word.toLowerCase();
            if (dictionary.containsKey(w)) {
                result += dictionary.get(w) + " ";
            } else {
                result += word + " ";
            }
        }
        return result.trim();
    }
}

public class Main {
    public static void main(String[] args) {
        Translator translator = new Translator();

        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");
        translator.addWord("my", "мій");
        translator.addWord("friend", "друг");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Скільки нових слів хочете додати в словник?");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            System.out.print("Англійське слово: ");
            String eng = scanner.nextLine();
            System.out.print("Український переклад: ");
            String ukr = scanner.nextLine();
            translator.addWord(eng, ukr);
        }

        System.out.println("Введіть фразу англійською для перекладу:");
        String phrase = scanner.nextLine();

        String result = translator.translatePhrase(phrase);
        System.out.println("Переклад: " + result);
    }
}