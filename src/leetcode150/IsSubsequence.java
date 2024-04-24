package leetcode150;

public class IsSubsequence {

    public static void main(String[] args){
        String s = "abc";
        String t = "ahbgdc";
        boolean res = isSubsequence(s, t);
        System.out.println(res);

        s = "axc";
        res = isSubsequence(s, t);
        System.out.println(res);
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
}
