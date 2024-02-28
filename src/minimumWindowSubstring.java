import java.util.HashMap;
import java.util.Map;

// Problem: Find the smallest substring of s where all char of t are present
// ADOBEC -> 6
// BECODEBA -> 8
// CODEBA -> 6
// BANC -> 4
// return 4
// PLAN
// Use a map to count letters in t and keep a minLen value to update shortest substring
// use a start and end pointer to iterate string
// 1. inc start if it is a letter in t (update occurence value in map)
// - then inc end ptr and update a substring map
// use a helpful fn to check whether substring contains all chars in t map
// continue expanding substring until helperfn returns true then update resulting string based on
// the length
//  - Update start ptr to the next char in map t

public class minimumWindowSubstring {

    public String minWindow(String s, String t) {

        // return empty string if s length is < t
        if(s.length() < t.length()){
            return "";
        }

        Map<Character, Integer> tMap = new HashMap<>();
        // populate tMap with t char occurences
        for(int i = 0; i < t.length(); i++){
            char ch = t.charAt(i);
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
        }

        // use substring map to track char in substring
        Map<Character, Integer> sMap = new HashMap<>();
        String result = "";
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        // {a:1}

        while(end < s.length()){
            // ab
            // find starting point for substring
            while(start < s.length() && !tMap.containsKey(s.charAt(start))){
                start++; // 1
                end = start; // 1
            }

            boolean foundSS = false;

            while(end < s.length() && !foundSS){
                // {a:1}
                sMap.put(s.charAt(end), sMap.getOrDefault(s.charAt(end), 0) + 1);
                foundSS = validateSubstringChar(tMap, sMap);
                end++; // 1
            }

            if(foundSS){
                String ss = s.substring(start, end); // 9 13 BANC
                result = minLen > ss.length() ? ss : result; // BANC
                minLen = Math.min(minLen, ss.length()); // 4
                start++; // 7
                // reset map
                sMap.clear();
            }
        }
        return result;

    }

    private boolean validateSubstringChar(Map<Character, Integer> tMap, Map<Character, Integer> sMap){
        // {a:1 b:1 c:1}
        // {a:2 b:1 c:2 e:2}
        if(sMap.isEmpty()){
            return false;
        }

        for(Map.Entry<Character, Integer> entry : tMap.entrySet()){
            if(!sMap.containsKey(entry.getKey())){
                return false;
            }
            if (sMap.get(entry.getKey()) < entry.getValue()){
                return false;
            }
        }

        return true;
    }
}
