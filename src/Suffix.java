import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Suffix {

    public static void main(String[] args){
        String[] strs = new String[]{"hello", "ello", "lo", "marshmello", "cello", "lemon", "blue", "red"};
        String[] suffixArr = findSuffix2(strs);
        for(String s : suffixArr){
            System.out.print(s + " ");
        }
    }

    /**
     * PROBLEM
     * Given an array of string find all the string that contains a suffix of
     * any other string in the array
     *
     * ex. [hello, jello, ello, marshmellow, red, blue]
     * --> returns [hello, jello, marshmellow]
     * */

    /**
     * PLAN
     * Sort strs array by string length in asceding order
     * Check string[i] and get the length of string[i]
     * 1. check every preceding string, if string[i + n] length is > string[i] length
     *   - substring string]i + n] and get the last string[i] length char from string[i + n]
     *   - if substring string[i + n] is equal to string[i] then add string[i + n] to resulting array
     *   - otherwirse move on
     * repeat this step until reaching the last string in strs array and return the resulint array
     * */
    public static String[] findSuffix(String[] strs){
        Arrays.sort(strs, Comparator.comparingInt(String::length));
        List<String> result = new ArrayList<>();

        // iterate over each string
        for(int i = 0; i < strs.length - 1; i++){
            int length = strs[i].length();
            for(int j = i + 1; j < strs.length ;j++){
                String nextStr = strs[j];
                int nextLen = nextStr.length();
                if(length <= nextLen){
                    // get the last length char from next string and compare with cur string
                    String nextSuffix = nextStr.substring(nextStr.length() - length);
                    if(strs[i].equals(nextSuffix)){
                        result.add(strs[j]);
                    }
                }
            }
        }

        return result.toArray(new String[result.size()]);
    }

    public static String[] findSuffix2(String[] strs){
        List<String> result = new ArrayList<>();

        for(int i = 0; i < strs.length; i++){
            for(int j = 0; j < strs.length; j++){
                if(i != j && strs[i].length() <= strs[j].length() && strs[j].endsWith(strs[i])){
                    result.add(strs[j]);
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }

}


