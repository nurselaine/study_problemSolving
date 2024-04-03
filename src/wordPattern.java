import java.util.HashMap;
import java.util.Map;

public class wordPattern {
    public static void main(String[] args){
        String s1 = "abba";
        String s2 = "dog cat cat dog";
        boolean result = wordPattern(s1, s2);
        System.out.println(result);

        s1 = "abba";
        s2 = "dog dog dog dog";
        result = wordPattern(s1, s2);
        System.out.println(result);
    }

    /**
     * Given 2 strings
     * check if s2 matches the pattern found in s1
     *
     * PLAN
     * check if:
     * 1. s2 length is != s1 length
     *
     * use a hashmap with key as s1 char and value s2 string
     *
     * iterate over s1 characters for i = 0 to s1 length
     * if map does not contain s1 char then add to map
     * get s2[i] and add as value of s1 char in map
     *
     * if map does contain s1 char then
     * check if
     * 1. s2[i] matches with the value -> if not then return false
     *
     * return true otherwise
     * */
    public static boolean wordPattern(String s1, String s2){
        String[] s1Arr = s1.split("");
        String[] s2Arr = s2.split(" ");
        if(s1Arr.length != s2Arr.length){
            return false;
        }
        Map<String, String> map = new HashMap<>();
        // map
        // a dog
        // b dog

        for(int i = 0; i < s1.length(); i++){
            if(!map.containsKey(s1Arr[i]) && !map.containsValue(s2Arr[i])){
                map.put(s1Arr[i], s2Arr[i]);
            }

            if (!map.containsKey(s1Arr[i]) || !map.get(s1Arr[i]).equals(s2Arr[i])) {
                return false;
            }
        }
        return true;
    }
}
