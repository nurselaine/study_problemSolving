package leetcode150;

import java.util.ArrayList;
import java.util.List;

public class IsSubsequence {

    public static void main(String[] args){
//        String s = "abc";
//        String t = "ahbgdc";
//        boolean res = isSubsequence(s, t);
//        System.out.println(res);
//
//        s = "axc";
//        res = isSubsequence(s, t);
//        System.out.println(res);

        List<List<Integer>> test = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            test.add(new ArrayList<>());
        }

        test.get(0).add(11);
        test.get(0).add(2);
        test.get(0).add(4);
        test.get(1).add(4);
        test.get(1).add(5);
        test.get(1).add(6);
        test.get(2).add(10);
        test.get(2).add(8);
        test.get(2).add(-12);

        int result = diagonalDifference(test);
        System.out.println(result);

    }

    /**
     * Check is s is a subsequence of t
     * a subsequence is a new string that is formed from
     * from deleting some characters without disturbing the
     * relative positions of the remaining characters
     *
     * ace is a subsequence of abcde
     * since a c e are present in abcde
     *
     * PLAN
     * if current index of s == s.length
     *  return true
     * if current index of t is >= t length return false
     * if char at s index matches char at t index then increment s
     * recursively check char in s and t and increment index t each call
     *
     * */
    public static boolean isSubsequence(String s, String t){
        return isSubsequence(s, t, 0, 0);
    }

    private static boolean isSubsequence(String s, String t, int sIndex, int tIndex){
        if(sIndex == s.length()){
            return true;
        } else if (tIndex >= t.length()){
            return false;
        }

        if(s.charAt(sIndex) == t.charAt(tIndex)){
            sIndex++;
        }
        return isSubsequence(s, t, sIndex, tIndex + 1);
    }

    public static int diagonalDifference(List<List<Integer>> arr){
        int n = arr.size();
        int lCol = 0;
        int rCol = n - 1;
        int lSum = 0;
        int rSum = 0;

        for(int i = 0; i < arr.size(); i++){
            lSum += arr.get(i).get(lCol++);
            rSum += arr.get(i).get(rCol--);
        }

        return Math.abs(rSum - lSum);
    }
}
