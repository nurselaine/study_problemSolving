package leetcode150;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    public static void main(String[] args){
        String r = "a";
        String m = "b";
        boolean result = canConstruct(r, m);
        System.out.println(result);

        r = "aa";
        m = "ab";
        result = canConstruct(r, m);
        System.out.println(result);

        r = "aa";
        m = "aab";
        result = canConstruct(r, m);
        System.out.println(result);
    }

    /**
     * Given two strings, return true if ransomNote can be constructed by using
     * letters from magazine and false otherwise
     *
     * ransomNote; wheat
     * Magazine: "helloworld
     * returns false
     *
     * ransomNote: real
     * magazine: healer
     * returns true
     *
     * PLAN
     * Use a hashmap to count each char occurence in randsomeNote
     * iterate over magazine
     * check if:
     * 1. letter is in map
     *  - if it is check if value is 1 then remove from map
     *  - otherwise decrement value by 1
     *
     *  once map is empty return true
     *  otherwise return false
     * */
    public static boolean canConstruct(String ransomNote, String magazine){
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : ransomNote.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int index = 0;
        while(!map.isEmpty() && index < magazine.length()){

            char ch = magazine.charAt(index);
            if(map.containsKey(ch)){
                int count = map.get(ch) - 1;

                if(count <= 0){
                    map.remove(ch);
                } else {
                    map.put(ch, count);
                }
            }
            index++;
        }

        return map.isEmpty();
    }
}
