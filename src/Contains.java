import java.util.HashSet;
import java.util.Set;

public class Contains {

    public static void main(String[] args){
        String[] s = new String[]{"hello", "my", "dog"};
        String[] t = new String[]{"aa", "AB", "Hello", "world"};
        boolean res = containsString(s, t);
        System.out.println(res);
    }

    /**
     * Given two lists of strings, return if list 1 contains anything in list 2
     *
     * PLAN
     * Create a hashmap
     *
     * Add values of list2 to hashmap
     *
     * iterate over list 1
     * for each str in list1
     * check if:
     * 1. map contains string
     *  - return true if it does
     *
     * otherwise return false
     * */
    public static boolean containsString(String[] s, String[] t){
        Set<String> set = new HashSet<>();
        for(String str : t){
            set.add(str);
        }

        for(String str : s){
            if(set.contains(str)){
                return true;
            }
        }

        return false;
    }
}
