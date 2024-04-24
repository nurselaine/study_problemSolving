package leetcode150;

public class ReverseWords {

    public static void main(String[] args){
        String s = "  hello world  ";
        String res = reverseWords(s);
        System.out.println(res);

        s = "the sky is blue";
        res = reverseWords(s);
        System.out.println(res);

        s = "a good  example";
        res = reverseWords(s);
        System.out.println(res);
    }


    /**
     * given a string, reverse the order of words
     *
     * PLAN
     * trim the string to remove extra spaces on the outside
     * Create a stringbuilder string
     * translate string to array
     * iterate over array from right to left
     * check if
     * 1. value is " "
     * - continue on
     * otherwise
     * add current array element to string builder
     *
     * return string builder
     * */
    public static String reverseWords(String s){
        s = s.trim();
        String[] sArr = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for(int i = sArr.length - 1; i >= 0; i--){
            if(!sArr[i].isEmpty()){
                sb.append(sArr[i] + " ");
            }
        }

        return sb.toString().trim();
    }
}
