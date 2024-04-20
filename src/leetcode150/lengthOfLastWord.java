package leetcode150;

public class lengthOfLastWord {

    public static void main(String[] args){
//        String s = "Hello World";
//        int res = lengthOfLastWord(s);
//        System.out.println(res);
//
//        s = "    fly me   to    the moon  ";
//        res = lengthOfLastWord(s);
//        System.out.println(res);
//
//        s = "Leffy is still joyboy";
//        res = lengthOfLastWord(s);
//        System.out.println(res);

        String[] strs = new String[]{"flower", "flow", "flight"};
//        String result = longestCommonPrefix(strs);
        String result = lcpDivideAndConquer(strs);
        System.out.println(result);
        strs = new String[]{"dog", "racecar", "car"};
//        result = longestCommonPrefix(strs);
        result = lcpDivideAndConquer(strs);
        System.out.println(result);
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


    /**
     * Write a fn to find the longest common prefix amongst
     * all strings in given array
     *
     * PLAN
     * using first word
     * iterate over all other words
     * while:
     * 1. first word length is > current word
     * 2. first word != current word
     * substring first word by 1 char
     *
     * return first word
     * */
    public static String longestCommonPrefix(String[] strs){
        String prefix = strs[0];


        for(int i = 1; i < strs.length; i++){

            while(!prefix.equals(strs[i])){
                if(prefix.length() == strs[i].length()){
                    prefix = prefix.substring(0, prefix.length() - 1);
                    strs[i] = strs[i].substring(0, strs[i].length() - 1);
                } else {
                    int len = Math.min(prefix.length(), strs[i].length());
                    prefix = prefix.substring(0, len);
                    strs[i] = strs[i].substring(0, len);
                }
            }

            if(prefix.isEmpty()){
                return prefix;
            }
        }
        return prefix;
    }


    /**
     * Split strs array into 2 subarrays where leftArr returns a common prefix
     * and rightArr returns another prefix, then we use those two prefices
     * to check the common prefix between the 2 subarrays and return it
     *
     * PLAN
     * use a helper function
     * */
    public static String lcpDivideAndConquer(String[] strs){
        return divide(strs, 0, strs.length - 1);
    }

    private static String divide(String[] strs, int l, int r){
        if(l >= r){
            return strs[l];
        } else {
            int mid = (l + r) / 2;
            String left = divide(strs, l, mid);
            String right = divide(strs, mid + 1, r);
            return combine(left, right);
        }
    }

    private static String combine(String l, String r){
        int min = Math.min(l.length(), r.length());
        int index = 0;
        while(index < min && l.charAt(index) == r.charAt(index)){
            index++;
        }
        return l.substring(0, index);
    }
}

