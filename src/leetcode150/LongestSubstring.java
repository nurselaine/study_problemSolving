package leetcode150;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {
    public static void main(String[] args){
        String test1 = "abcabcbb";
        int result = lengthOfLongestSubstring(test1);
        System.out.println(result);
        String test2 = "bbbbb";
        result = lengthOfLongestSubstring(test2);
        System.out.println(result);
        String test3 = "pwwkew";
        result = lengthOfLongestSubstring(test3);
        System.out.println(result);
    }

    /**
     * Given a string find the length of the longest substring
     * without any repeating characters
     *
     * abcabcb
     *      lr
     * set: a b c
     * maxlen = 2
     *
     * PLAN
     * Intialize a set and maxlen counter
     * declare a l and r ptr
     * while l ptr < r ptr and r ptr < s length
     * curr -> str[r]
     * check if
     * 1. curr is in set
     *  - get first indexOf curr and move l to index + 1
     * 2. update set with curr & update maxLen value
     * return maxlen
     * check if
     * increment r ptr
     * */
    public static int lengthOfLongestSubstring(String s){
        Set<Character> set = new HashSet<>();
        int maxLen = 0;
        int l = 0;
        int r = 0;
        while(l <= r && r < s.length()){
            char curr = s.charAt(r);
            if(set.contains(curr)){
                int occ = s.indexOf(curr, l);
                while(l < s.length() && l != occ){
                    set.remove(s.charAt(l));
                    l++;
                }
                l++;
            } else {
                set.add(curr);
                maxLen = Math.max(maxLen, r - l + 1);
            }
            r++;
        }
        return maxLen;
    }
}
