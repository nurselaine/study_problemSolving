package leetcode150;

public class Palindrome {

    public static void main(String[] args){
        String s1 = "A man, a plan, a canal: Panama";
        boolean res = isPalindrome(s1);
        System.out.println(res);

        String s2 = "race a car";
        res = isPalindrome(s2);
        System.out.println(res);

        String empty = " ";
        res = isPalindrome(empty);
        System.out.println(res);
    }

    /**
     * Check if a phrase is a valid palindrome
     *
     * PLAN
     * declare a l and r ptr
     * l and right ptr should be the same char
     * if one instance is not the same then immediately return
     * false otherwise continue
     * checking until l and r ptr's meet
     * */
    public static boolean isPalindrome(String s){
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // string should not have
        return isPalindrome(s, 0, s.length() - 1);
    }

    private static boolean isPalindrome(String s, int l, int r){
        if(l >= r){
            return true;
        } else if (s.charAt(l) != s.charAt(r)){
            return false;
        }
        return isPalindrome(s, l + 1, r - 1);
    }
}
