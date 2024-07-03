package CodePath.Week5;

import java.util.*;

public class Session1 {
    public static void main(String[] args){
        testPowerOfFour();
    }

    public static void testPowerOfFour(){
        boolean res = powerOfFour(64);
        System.out.println(res);
    }

    public static void testMaxPow(){
        double res = myPow(2.000, -2);
        System.out.println(res);
    }

    public static void testLogCrawler(){
        String[] logs = new String[]{"d1/","d2/","../","d21/","./"};
        int minOp = crawlerLogFolder(logs);
        System.out.println(minOp);
    }

    public static void testShortestDistanceChar(){
        String s = "loveleetcode";
        // [3,2,1,0,1,0,0,1,2,2,1,0]
        int[] ans = shortestDistanceToChar(s, 'e');
        for(int i : ans){
            System.out.print(i + " ");
        }
    }

    public static void testValidPalindromeII(){
        String s = "ssspp";
        boolean valid = validPalindromeII(s);
        System.out.println(valid);
    }

    /**
     * Problem #1: Valid Palindrome II
     * Given a string s, return true if the s can be palindrome
     * after deleting at most one character from it.
     *
     * questions
     * - can s be empty?
     * - what characters are in the string?
     * - is it case sensitive?
     * - how many characters can there be? Max length?
     * - can there be more than one palindrome?
     * - do the letters need to maintain the same order?
     *
     * examples
     * - stjjklts => true
     * - abbcdebba => false
     * - a => true
     *
     * methods
     * - multiple passes to check each variation of the string
     * - 2 ptrs to validate string characters
     * - stack to reverse string
     *
     * plan
     * - initialize 2 ptrs, l and r, and a counter for deletedChar
     * declare a check palindrome => helper function
     * check if char l == r => increment l and decrement r
     * otherwise => increment deletechar count
     * => call method with l + 1, r call method with l, r + 1 with deleteChar updated
     *
     * time: O(N)
     * space: O(1)
     * */
     public static boolean validPalindromeII(String s){
        int l = 0;
        int r = s.length() - 1;
        int deletedChar = 0;
        return validatePalindrome(s, l, r, deletedChar);
     }

     private static boolean validatePalindrome(String s, int l, int r, int deletedChar){
         if(deletedChar > 1){
             return false;
         }
         if(l >= r){
             return true;
         }
         if(s.charAt(l) != s.charAt(r)){
             return validatePalindrome(s, l + 1, r, deletedChar + 1)
                     || validatePalindrome(s, l, r - 1, deletedChar + 1);
         }
         return validatePalindrome(s, l + 1, r - 1, deletedChar);
     }

    /**
     * Problem #2: Shortest Distance to a Character
     * Given a string s and a character c that occurs in s,
     * return an array of integers answer where answer.length == s.length
     * and answer[i] is the distance from index i to the closest occurrence of character c in s.
     *
     * The distance between two indices i and j is abs(i - j), where abs is the absolute value function.
     *
     * Example 1:
     *
     * Input: s = "loveleetcode", c = "e"
     * Output: [3,2,1,0,1,0,0,1,2,2,1,0]
     * Explanation: The character 'e' appears at indices 3, 5, 6, and 11 (0-indexed).
     * The closest occurrence of 'e' for index 0 is at index 3, so the distance is abs(0 - 3) = 3.
     * The closest occurrence of 'e' for index 1 is at index 3, so the distance is abs(1 - 3) = 2.
     * For index 4, there is a tie between the 'e' at index 3 and the 'e' at index 5, but the distance is still the same: abs(4 - 3) == abs(4 - 5) = 1.
     * The closest occurrence of 'e' for index 8 is at index 6, so the distance is abs(8 - 6) = 2.
     *
     * questions
     * - will c always be included in the string?
     * - can either of them be empty string/char?
     * - what is the min/max length of s?
     * - are there any special char considerations?
     * - any spaces and case sensitivity?
     * - time/space complexity?
     *
     * examples
     * - Ilovetocode char = o
     * ans[] = [2, 1, 0, 1, 2, 1, 0, 1, 0, 1, 2]
     *          [i, l, o, v, e, t, o, c, o, d, e]
     *
     * methods
     * - multiple passes to understand where c resides and then to update ans[] with e locations
     * - use a hashmap to store the different between the last char c seen
     * - 2 ptrs to find the next closest char c and to follow the ptr with the different being calcuated
     *
     * plan
     * initialize ans array and fill with -1
     * create array list and push in each index where c is seen is s
     * declare ptr for list
     * iterate over ans
     * if ptr is < list size then
     * calculate min(i - list[ptr], i - list[i - 1 or 0])
     *
     * increment r
     * return the ans array
     *
     * time complexity: O(N) + O(N) * O(N) = O(N^2)?
     * space: not counting ans => O(N) for call stack?
     * */
    public static int[] shortestDistanceToChar(String s, char c){
        // lomido c = o
        int S = s.length(); // 6
        int[] ans = new int[S];
        Arrays.fill(ans, S);
        for(int i = 0; i < S; i++){ // 1
            if(s.charAt(i) == c) ans[i] = 0;
            if(ans[i] == 0){
                ans = updateMinValues(ans, i, i - 1, i + 1);
            }
        }
        return ans;
    }

    private static int[] updateMinValues(int[] arr, int start, int l, int r){
        if(l < 0 && r >= arr.length){ // -1 1 3
            return arr;
        }
        if(l >= 0 && arr[l] != 0){
            arr[l] = Math.min(arr[l], Math.abs(start - l)); // 1
        }
        if(r < arr.length &&  arr[r] != 0 ){
            arr[r] = Math.min(arr[r], Math.abs(start - r)); // 2
        }
        return updateMinValues(arr, start, l - 1, r + 1); //-1 3
    }

    /**
     * Problem #3: Crawler Log Folder
     * The Leetcode file system keeps a log each time some user performs a change folder operation.
     *
     * The operations are described below:
     *
     * "../" : Move to the parent folder of the current folder.
     * (If you are already in the main folder, remain in the same folder).
     * "./" : Remain in the same folder.
     * "x/" : Move to the child folder named x (This folder is guaranteed to always exist).
     * You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.
     *
     * The file system starts in the main folder, then the operations in logs are performed.
     *
     * Return the minimum number of operations needed to go back to the main folder after the change folder operations.
     *
     * Example 1:
     *
     * Input: logs = ["d1/","d2/","../","d21/","./"]
     * Output: 2
     * Example 2:
     *
     * Input: logs = ["d1/","d2/","./","d3/","../","d31/"]
     * Output: 3
     * Example 3:
     *
     * Input: logs = ["d1/","../","../","../"]
     * Output: 0
     *
     * questions
     * - does the folder name x always start with d? can it be any string?
     * - will there be any unknown commands in the logs array?
     * - if you ../ when already in the home directory then
     * do you stay in the same place?
     *
     * methods
     * - use a stack to "move in and out of directories"
     * - use a hashmap to check the commands and store commands with O(1) recall
     * - use a counter and ptrs to iterate over array
     *
     * plan
     * create a hashmap add commands ../, ./, x/
     * iterate over the logs arr
     * check if char at 0 is a letter or digit => x/
     * =>  add 1 to total
     * if command ./ then add 0
     * if command ../ then add -1
     * */
    public static int crawlerLogFolder(String[] logs){
        Map<String, Integer> map = new HashMap<>();
        map.put("./", 0);
        map.put("../", -1);
        int minOp = 0;
        for(String log : logs){
            if(Character.isLetter(log.charAt(0))){
                minOp++; // 3
            } else if (minOp > 0){
                minOp += map.get(log); // 2
            }
        }
        return minOp;
    }


    /**
     * (Bonus) Problem #4: Pow(x, n)
     * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
     *
     * find the product of x^n
     *
     * method
     * iterate
     *
     * plan
     * declare an acc
     * iterate from 1 to n
     * multiply acc by x each iteration
     * return acc
     * */
    public static double pow(double x, int n){
        double acc = x;
        if(n < 0){
            x = 1.000 / x;
            acc = x;
            n *= -1;
        }
        for(int i = 2; i <= n; i++){
            acc *= x;
        }
        return acc;
    }

    public static double myPow(double x, int n){
        if (n == 0){
            return 1;
        }
        if(x == 0 || x == 1 || n == 1){
            return x;
        }

        double half = myPow(x, n / 2);
        if(n % 2 == 0){
            return half * half;
        } else {
            if(n < 0){
                return half * half / x;
            } else {
                return half * half * x;
            }
        }
    }


    /**
     * (Bonus) Problem #5: Power of Four
     * Given an integer n, return true if it is a power of four. Otherwise, return false.
     *
     * An integer n is a power of four, if there exists an integer x such that n == 4x.
     *
     *
     * */
    public static boolean powerOfFour(int n){
        if(n == 1) return true;
        if(n % 4 != 0 || n == 0) return false;
        return powerOfFour(n / 4);
    }
}
