package CodePath.Week3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Session1 {
    public static void main(String[] args){
        testUniqueNumOccurences();
    }

    public static void testUniqueNumOccurences(){
        int[] nums = new int[]{1, 1, 2, 2, 3, 3, 3};
        boolean res = uniqueNumOccurrences(nums);
        System.out.println(res);
    }

    public static void testReverseString(){
        String s = "a good   example";
        s = reverseWords(s);
        System.out.println(s);
    }

    public static void testValidPalindrome(){
        String s = "A man, a plan, a canal: Panama";
        boolean res = validPalindrome(s);
        System.out.println(res);
    }

    /**
     * determine whether a given string is a palindrome
     *
     * questions
     * - do spaces and non letter values count as part of the palindrome?
     * - Can the string be empty?
     * - Can we change the string?
     *
     * example
     *
     *
     * Match
     * - sorting
     * - two pointers, 1 at start and 1 at end indices
     *
     * Plan
     * - initialize 2 ptrs and continue moving pointers until both meet
     * in the center OR ptrs encounter mismatching characters
     * return true if ptrs meet in the center, otherwise return false
     *
     * */
    public static boolean validPalindrome(String s){
        int left = 0;
        int right = s.length()-1;
        s = s.toLowerCase();

        while(left < right){
            if(!Character.isLetter(s.charAt(left))){
                left++;
            } else if(!Character.isLetter(s.charAt(right))){
                right--;
            } else if(s.charAt(left) != s.charAt(right)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }

    /**
     * return true if the arr elements have unqiue occurences
     * otherwise return false
     *
     * question:
     * - can there be empty array?
     * - time/space constraints?
     * - how large can array get?
     *
     * example:
     * [1, 1, 2, 2, 2, 3] => return
     * [1, 2, 3] => false
     *
     * method
     * - use a set to check if there are duplicate occurneces
     * - use a map to count occurences
     * - use sorting + set to check on occurences
     * time: O(N)
     * space: O(N)
     * */
    public static boolean uniqueNumOccurrences(int[] arr){
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : arr){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        Set<Integer> set = new HashSet<>(map.values());
        return map.values().size() == set.size();
    }

    /**
     * given 2 sorted arrays, merge the second array into
     * the first array in sorted order
     * */
    public static void mergeSortedArray(int[] nums1, int[] nums2){

    }

//    public static boolean isSubsequence(String s, String t){
//
//    }

    /**
     * Reverse words in a string
     * - word: a sequence of non-space characters separated by at least one space
     *
     * the return string should only have a single space
     * separating the words - remove any extra space
     *
     * questions:
     * - are there any digits or punctionation/special characters?
     * - can the string be emtpy only 1 word + space?
     * - time/space constraints?
     *
     * example
     * - hello world => world hello
     *
     * Method
     * - two pointers
     * - stack
     * - multiple passes
     * */
    public static String reverseWords(String s){
        s = s.trim();
        String[] words = s.split("\\s+");

        int left = 0;
        int right = words.length - 1;
        while(left < right){
            String temp = words[left];
            words[left++] = words[right];
            words[right--] = temp;
        }
        return String.join(" ", words);
    }
}
