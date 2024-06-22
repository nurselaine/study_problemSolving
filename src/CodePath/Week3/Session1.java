package CodePath.Week3;

import java.util.*;

public class Session1 {
    public static void main(String[] args){
        testMaxWealth();
    }

    public static void testMaxWealth(){
        int[][] accounts = new int[][]{{2, 8, 7}, {7, 1, 3}, {1, 9, 5}};
        int max = maxWealth(accounts);
        System.out.println(max);
    }

    public static void testNumMatchingSubseq(){
        String[] words = new String[]{"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"};
        String s = "dsahjpjauf";
        int res = numMatchinSubseqII(s, words);
        System.out.println(res);
    }
    public static void testIsSubsequence(){
        String s = "";
        String t = "ahbgdc";
        boolean res = isSubsequence(s, t);
        System.out.println(res);
    }

    public static void testMergeSortedArray(){
        int[] nums1 = new int[]{2, 4, 0, 0, 0, 0, 0};
        int[] nums2 = new int[]{-1, 1, 2, 5, 6};
        mergeSortedArray(nums1, nums2, Math.abs(nums1.length - nums2.length), nums2.length);
        printArr(nums1);
    }

    private static void printArr(int[] nums){
        for(int n : nums){
            System.out.print(n + " ");
        }
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
     *
     * - both arrays are in increasing order
     * - can be different lengths
     * - arrays can be empty
     *
     * questions
     * - Can arrays be empty?
     * - time/space constraints?
     * - Does nums1 need to be sorted in place?
     * - what should come first, nums1 or nums2 element if they are equal?
     *
     * example
     * [1 2 2 3 0 0] [2 5 6] => [1 2 2 3 5 6]
     *        m       n
     * [1 2 3] [] => [1 2 3]
     * [1 4 5 6] [5] => [1 4 5 6]
     *      m     n
     *
     * methods
     * - two pointers: can be used to track element in nums1 and nums2
     * - sliding window: may over complicate problem
     * - hashmap: not needed to count/track values
     * - binary search: not a sorted array so does not apply
     *
     * plan
     * initialize pointer to track end of nums1 c, nums2 b , and nums1 values a
     * while m >= 0
     * assign c to the greater value and decrement pointer accordingly
     *
     * if n < nums2 length
     * - from m + n
     * - update nums1[n + m] to equal the remainder of nums2
     * */
    public static void mergeSortedArray(int[] nums1, int[] nums2, int m, int n){
        int c = m + n - 1;
        m--;
        n--;
        while(n >= 0){
            if((m >= 0 && nums2[n] >= nums1[m]) || m < 0){
                nums1[c--] = nums2[n--];
            } else {
                nums1[c--] = nums1[m--];
            }
        }
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

    /**
     * Given two strings s and t return true if s is a subsequence of t, otherwise return false
     * subsequence: adcde ace => consecutive letters in a word
     *
     * questions
     * - can either string be empty?
     * - are two empty strings subsequences?
     * - time/space constraint?
     *
     * examples
     * - abcde ace => true
     * - abdec aed => false
     * - a '' => false
     *
     * methods
     * - 2 pointers: can be used to track letters in a subsequence
     * - binary search: not applicable to this problem
     * - hashmap: can be used to count letters but overcomplicates problem
     * - sorting: cannot change order of letters
     *
     * plan
     * initialize 2 pointers for each string
     * while sPtr is < len of S string OR tPtr is < len of T
     * if elem at sPtr and tPtr are equal increment s and t ptr
     * otherwise increment t ptr
     * return sPtr == to s len
     *
     * time: O(s + t)
     * space: O(1)
     * */
    public static boolean isSubsequence(String s, String t){
        int sPtr = 0;
        int tPtr = 0;
        int S = s.length();
        int T = t.length();
        while(sPtr < S && tPtr < T){
            if(s.charAt(sPtr) == t.charAt(tPtr)){
                sPtr++;
            }
            tPtr++;
        }
        return sPtr == S;
    }

    /**
     * Gvien a string s and string array words, return the num of words that is a
     * subsequence of s
     *
     * questions
     * - can either have spaces, digits, special characters?
     * - Is it case sensitive?
     * - how large is the words array?
     * - time/space constraints?
     *
     * example
     * - [abc, acbdep, abedc, abdkc, uhsvbc], s= abc => 4
     * - [], s = vjd => 0
     *
     * methods
     * - two pointers: validates whether word[i] is subsequence of s
     * - binary search: not sorted
     * - hashmap: overcomplicates
     * - sorting: not necessary
     *
     * plan
     * - declare a counter to return as result
     * iterate over words
     * for each word, inc counter when isSubsubsequence is true
     *
     * eval:
     * time: O(s * m) ~ too slow
     * space: O(1)
     * */
    public static int numMatchingSubseq(String s, String[] words){
        int counter = 0;
        for(String w : words){
            if(isSubsequence(w, s)){
                counter++;
            }
        }
        return counter;
    }

    /**
     * Plan
     * use an array of 26 to store letters of words
     * create a node class to store each word and char index
     * iterate over the given string s
     * for each char, find the arraylist of word nodes stored that start with char c
     * for each node in the arraylist
     * increment the current index of the node
     * check if:
     * 1. current index == node's word length => increment counter
     * 2. otherwise
     * => calculate the new starting letter with index and add it to the letters array
     * => clear the old array list for the curr char arraylist
     * continue until all characters in s have been traversed
     * return the final count
     * */
    public static int numMatchinSubseqII(String s, String[] words){
        ArrayList<Node>[] head = new ArrayList[26];
        int result = 0;
        for(int i = 0; i < 26; i++){
            head[i] = new ArrayList<Node>();
        }
        for(String word : words){
            head[word.charAt(0) - 'a'].add(new Node(word, 0));
        }

        for(char c : s.toCharArray()){
            ArrayList<Node> bucket = head[c - 'a'];
            for(Node node : bucket){
                node.index++;
                if(node.index == node.word.length() - 1){
                    result++;
                } else {
                    head[node.word.charAt(node.index) -'a'].add(node);
                }
            }
            head[c - 'a'].clear();
        }
        return result;
    }

    private static class Node {
        public String word;
        public int index;
        public Node (String word, int index){
            this.word = word;
            this.index = index;
        }
    }

    /**
     * given a mxn int grid where accounts[i][j] is
     * the amount of money the ith customer has in the
     * jth bank. Return the wealth that the richest customer has
     *
     * questions
     * - can the value inside of bank be negative?
     * - do all customers have j accounts?
     * - will there be a minimum of 1 customer and 1 bank?
     * - what's the max # of customers and banks?
     * - what are the time and space constraints?
     *
     * examples
     * - [2, 2, 2] = 6
     *   [1, 2, 3] = 6
     *   [1, 1, 5] = 7
     *   => 7
     * - [1, 1]
     *   [2, 2]
     *   [2, 2]
     *   => 4
     *
     * methods
     * - two pointers: may overcomplication problem
     * - binary search: not application
     * - sliding window: not application
     * - sorting: can be helpful to eliminate customer
     *
     * plan
     * declare a max value to return
     * iterate over each customer
     * initialize a totalValue
     * for each bank [i][j] => add value to totalValue
     * update max value if totalValue exceeds
     * return max value
     *
     * For each customer array
     * - sort array in descending order
     * -
     *
     * time: O(m * n)
     * space: O(1)
     * */
    public static int maxWealth(int[][] accounts){
        int max = Integer.MIN_VALUE;
        for(int[] customer : accounts){
            int totalValue = 0;
            for(int bank : customer){
                totalValue += bank;
            }
            max = Math.max(max, totalValue);
        }
        return max;
    }
}
