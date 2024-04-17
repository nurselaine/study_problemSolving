package leetcode150;

public class lengthOfLastWord {

    public static void main(String[] args){
        String s = "Hello World";
        int res = lengthOfLastWord(s);
        System.out.println(res);

        s = "    fly me   to    the moon  ";
        res = lengthOfLastWord(s);
        System.out.println(res);

        s = "Leffy is still joyboy";
        res = lengthOfLastWord(s);
        System.out.println(res);
    }

    /**
     * Given a string, return the length of the last word in
     * the string
     *
     * PLAN
     * split array by spaces
     * returm the length of the last array element
     * */
    public static int lengthOfLastWord(String s){
        String[] strArr = s.split(" ");
        return strArr[strArr.length - 1].length();
    }
}

