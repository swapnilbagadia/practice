package main.java.slidingwindows;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {


    public static void main(String[] args) {
        String text = "ADOBECODEBANC";
        char[] characters = {'A', 'B', 'C'};

        int[] result = {0, Integer.MAX_VALUE};

        getMinimumWindow(result, text, characters);

        System.out.println(text.substring(result[0], result[1] + 1));

    }


    static void getMinimumWindow(int[] result, String text, char[] characters) {
        Map<Character, Integer> countMap = new HashMap<>();
        //Preprocessing Hashmap
        for (int i = 0; i < characters.length; i++) {
            countMap.put(characters[i], 0);
        }
        //initialised with all characters missing
        int missing = characters.length;
        int slow = 0;
        for (int fast = 0; fast < text.length(); fast++) {
            char c1 = text.charAt(fast);
            if (countMap.containsKey(c1)) {
                if (countMap.get(c1) == 0) {
                    missing--;
                }
                countMap.put(c1, countMap.get(c1) + 1);

            }
            while (missing == 0) {
                char c2 = text.charAt(slow);
                if (countMap.containsKey(c2)) {
                    if ((result[1] - result[0]) > (fast - slow)) {
                        result[1] = fast;
                        result[0] = slow;
                    }
                    Integer count = countMap.get(c2);
                    if (count != null && count > 0) {
                        countMap.put(c2, --count);
                        if (count == 0) {
                            missing++;
                        }
                    }
                }
                slow++;
            }
        }
    }
}