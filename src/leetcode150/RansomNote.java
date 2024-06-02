package leetcode150;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
        for(char ch : magazine.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for(int i = 0; i < ransomNote.length(); i++){
            char c = ransomNote.charAt(i);
            Integer count = map.getOrDefault(c, 0);
            if(count == 0){
                return false;
            } else {
                map.put(c, count - 1);
            }
        }

        return true;
    }


    /**
     * Implement using stack
     * */
    public static boolean canConstructStack(String ransomNote, String magazine){
        Stack<Character> stack = new Stack<>();
        char[] s = ransomNote.toCharArray();
        char[] m = magazine.toCharArray();
        Arrays.sort(s);
        Arrays.sort(m);

        for(char ch : s){
            stack.push(ch);
        }

        for(int i = m.length - 1; i >= 0; i--){
            if(stack.isEmpty()) return true;
            if(stack.peek() == m[i]){
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static boolean canConstructArray(String ransomNote, String magazine){
        char[] letters = new char[26];
        for(char ch : magazine.toCharArray()){
            letters[ch - 'a']++;
        }

        for(char ch : ransomNote.toCharArray()){
            if(letters[ch - 'a'] == 0){
                return false;
            }
            letters[ch - 'a']--;
        }
        return true;
    }
}
