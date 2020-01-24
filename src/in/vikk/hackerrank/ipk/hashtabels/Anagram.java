package in.vikk.hackerrank.ipk.hashtabels;

import in.vikk.hackerrank.ipk.util.Utility;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class Anagram {

    public static int sherlockAndAnagrams(String string) {
        int anagramCounter = 0;
        for (int i = 0; i < string.length(); i++) {
            for (int j = 0; j <= i; j++) {
                String str = string.substring(j, i + 1);
                int anagramCount = anagram(str, string) - 1;
                anagramCounter += anagramCount;
            }
        }
        return anagramCounter / 2;
    }

    public static int anagram(String word, String text) {
        final int ALPHABET_COUNT = 256;
        int[] count = new int[ALPHABET_COUNT];

        int wordLength = word.length();
        int textLength = text.length();

        // O(256)
        Arrays.fill(count, 0);

        // O(W)
        for (char c : word.toCharArray()) {
            count[c] += 1;
        }
        // O(W)
        for (int textIndex = 0; textIndex < wordLength; textIndex++) {
            count[text.charAt(textIndex)] -= 1;
        }

        Function<int[], Boolean> arNonZerosPresent = intArra -> Arrays.stream(intArra).filter(i -> i != 0).findFirst().isPresent();

        // O(256) - Find any non zero value for first the partition in count array
        int anagram = arNonZerosPresent.apply(count) ? 0 : 1;

        // O(T) - O(W)
        for (int textIndex = wordLength; textIndex < textLength; textIndex++) {
            count[text.charAt(textIndex)] -= 1;
            count[text.charAt(textIndex - wordLength)] += 1;
            // O(256)
            if (!arNonZerosPresent.apply(count)) {
                anagram += 1;
            }
        }

        return anagram;
    }

    public static void main(String[] args) {
//        System.out.println(sherlockAndAnagrams("ThinkPad"));
//        System.out.println(sherlockAndAnagrams("ThinkPhad"));
//        System.out.println(sherlockAndAnagrams("abba"));
//        System.out.println(sherlockAndAnagrams("abcd"));
//        System.out.println(sherlockAndAnagrams("ifailuhkqq"));
//        System.out.println(sherlockAndAnagrams("kkkk"));
//        System.out.println(sherlockAndAnagrams("cdcd"));
        test("in/vikk/hackerrank/ipk/testcases/Anagram/1.txt");
//        System.out.println(anagram("i", "ifailuhkqqiaf"));
//        System.out.println(anagram("hinkP", "ThinkPhad"));
    }

    private static void test(String fileName) {
        String[] a = Utility.readStringArrayFromFile(fileName);
        long startTime = new Date().getTime();
        for (String str : a) {
            System.out.println(sherlockAndAnagrams(str));
        }
        System.out.println("Execution Time: " + (new Date().getTime() - startTime) / 1000);
    }
}
