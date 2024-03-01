import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class wordSubsets {

    public static void main(String[] args){
        String[] test1 = new String[]{"leetcode", "neetcode", "flower"};
        String[] test2 = new String[]{"el", "ol", "ee"};
        List<String> res = wordSubsets(test1, test2);
        for(String s : res){
            System.out.println(s);
        }
    }

    // Problem:
    // Find all the words in words1 that have all the characters from words2
    // in it. Words in words1 must have max occurrences of the characters in
    // strings from words2.
    // words1 ["flower", "maple"]
    // words2 [o, e]
    // only flower has 1x o and 1x e
    // maple only has 1x e
    // result : [flower]

    // PLAN
    // Use a map to count all chars and get the max char occurrence
    // for words in words2
    // then iterate through all the words in words1 and create a
    // char count map for each word
    // 1. Once char count map for words1 is >= size of the max char count map 2
    // - check whether all char in words2 char count map are in words1 char count map
    // - if the char occurrence for words1 is >= the occurrence in words2 char count
    // then add words1 words to resulting array
    // repeat for all words1 words
    public static List<String> wordSubsets(String[] words1, String[] words2){
        List<String> result = new ArrayList<>();
        Map<Character, Integer> maxWords2Count = new HashMap<>();
        // find max count for all words in words2
        for(int i = 0; i < words2.length; i++){ // 0
            Map<Character, Integer> words2Count = new HashMap<>(); //
            String word2 = words2[i]; // e
            for(int j = 0; j < word2.length(); j++){ // 0
                char ch = word2.charAt(j); // e
                words2Count.put(ch, words2Count.getOrDefault(ch, 0) + 1); //
                // update maxWords2Count if ch in words2Count is > maxwords2count
                if(!maxWords2Count.containsKey(ch) || words2Count.get(ch) > maxWords2Count.get(ch)){
                    maxWords2Count.put(ch, words2Count.get(ch));
                }
            }
        }

        // find the max count of all words1 words
        for(int i = 0; i < words1.length; i++){
            String word1 = words1[i];
            Map<Character, Integer> words1Count = new HashMap<>();

            int j = 0;
            boolean isValid = false;
            while(j < word1.length() && !isValid){
                char ch = word1.charAt(j);
                words1Count.put(ch, words1Count.getOrDefault(ch, 0) + 1);

                if(words1Count.size() >= maxWords2Count.size()){
                    isValid = validOccurrences(words1Count, maxWords2Count);
                }
                j++;
            }

            if(isValid){
                result.add(word1);
            }
        }

        return result;
    }

    private static boolean validOccurrences(Map<Character, Integer> m1, Map<Character, Integer> m2){

        for(Map.Entry<Character, Integer> entry : m2.entrySet()){
            if(!m1.containsKey(entry.getKey()) || m1.get(entry.getKey()) < entry.getValue()){
                return false;
            }
        }
        return true;
    }
}
