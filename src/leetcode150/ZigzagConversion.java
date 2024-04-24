package leetcode150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZigzagConversion {

    public static void main(String[] args){
        String s = "PAYPALISHIRING";
        String res = convert(s, 3);
        System.out.println(res);
//        String res = convertR(s, 3);
//        System.out.println(res);

        s = "HELLOWORLD";
        res = convert(s, 4);
        System.out.println(res);

        s = "SEVENTYTWO";
        res = convert(s, 7);
        System.out.println(res);

        s = "AB";
        res = convert(s, 1);
        System.out.println(res);

    }

    /**
     * Given a string and num of rows count
     * convert the string to a zigzag pattern
     * and translate the string to its zigzag
     * conversion and return the string representing
     * the transformed state
     *
     * example
     * PAYPALISHIRING
     * P     A     H     N
     * A  P  L  S  I  I  G
     * Y     I     R
     * PAHNAPLSIIGYIR
     *
     * PLAN
     * declare a list with numRow lists
     * each list inside the list will present a different
     * row with a set of letters
     *
     * iterate over all the letters in s and for each letter
     * add into the list at corresponding list
     * use a list index tracker and update the count each time a letter is added
     * to a list - once counter reaches numRows - 1, reset counter to 0
     *
     * Once all letters are add into corresponding lists
     * iterate over all lists and combine each list values into 1 string
     * return the string
     * */
    public static String convert(String s, int numRows){
        if(numRows ==1 ){
            return s;
        }
        List<List<Character>> list = new ArrayList<>();

        for(int i = 0; i < numRows; i++){
            list.add(new ArrayList<>());
        }
        int listId = 0;

        int index = 0;
        while(index < s.length()){

            list.get(listId).add(s.charAt(index));
            if(listId == numRows - 1){
                while (index + 1 < s.length() && listId > 0){
                    index++;
                    listId--;
                    list.get(listId).add(s.charAt(index));
                }
            }
            listId++;
            index++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numRows; i++){
            List<Character> currList = list.get(i);
            for(int j = 0; j < currList.size(); j++){
                sb.append(currList.get(j));
            }
        }

        return sb.toString();
    }

    // recursive solution?
    /**
     * PLAN
     * inputs: index, map of lists, key, numRows, inc
     * check if index == s length
     *  return
     * check if
     * 1. key = numROws -> inc = false
     * 2. key = 0 -> inc = true
     *
     * add current letter to list in map[key]
     * increment current index
     *
     * check if
     * 1. inc = true
     *  recursiveFn with key + 1
     * 2. inc = false
     *  recursiveFn with key - 1
     * */
    public static String convertR(String s, int numRows){
        Map<Integer, List<Character>> map = new HashMap<>();
        for(int i = 1; i <= numRows; i++){
            map.put(i, new ArrayList<>());
        }
        convertR(s, numRows, map, 0, 0, true);
        System.out.println(map.toString());
        return s;
    }

    private static void convertR(String s, int numRows, Map<Integer, List<Character>> map, int index, int key, boolean inc){
        if(index == s.length()){
            return;
        }

        map.get(key).add(s.charAt(index));
        index++;

        if (key == numRows){
            inc = false;
        } else if (key == 0){
            inc = true;
        }
        if(inc){
            key++;
            convertR(s, numRows, map, index, key, inc);
        } else {
            key--;
            convertR(s, numRows, map, index, key, inc);
        }
    }
}
