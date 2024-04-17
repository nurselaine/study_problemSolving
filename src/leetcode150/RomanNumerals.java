package leetcode150;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RomanNumerals {

    public static Map<Character, Integer> map = new HashMap<>();
    public static void main(String[] args){
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

//        int result = romanToInt("MCMXCIV");
//        System.out.println(result);
//        result = refactorRomanToInt("MCMXCIV");
//        System.out.println(result);

//        int result = refactorRomanToInt("IV");
//        System.out.println(result);

//        result = romanToInt("III");
//        System.out.println(result);
//        result = romanToInt("LVIII");
//        System.out.println(result);

        String res = intToRoman(10);
        System.out.println(res);
    }


    /**
     * Roman numerals are normally written smallest to largest left to right
     *
     * there are special digits
     * 4: IV (1 before 5 to subtract making 4)
     * 9: IX
     *
     * I before V AND X (4 and 9)
     * X before L AND C (40 and 90)
     * C before D AND M (400 and 900)
     *
     * Given a roman numeral, convert to an integer
     *
     * MCM
     * 1000 + 100 + 1000
     * PLAN
     * iterate over string
     * check if:
     * 1. char[i - 1]
     * */
    public static int romanToInt(String s){
        int total = 0;

        for(int i = 0; i < s.length(); i++){

            char ch = s.charAt(i);

            if(i > 0 && (
                    (s.charAt(i - 1) == 'I' && (ch == 'V' || ch == 'X')) ||
                    (s.charAt(i - 1) == 'X' && (ch == 'L' || ch == 'C')) ||
                    (s.charAt(i - 1) == 'C' && (ch == 'D' || ch == 'M'))
            )){
                total += map.get(ch) - (map.get(s.charAt(i - 1)) * 2);
            } else {
                total += map.get(ch);
            }
            System.out.println(total);

        }
        return total;
    }

    public static int refactorRomanToInt(String s){
        int total = 0;

        for(int i = s.length() - 1; i >= 0; i--){

            if(i > 0 && map.get(s.charAt(i - 1)) < map.get(s.charAt(i))){
                total += map.get(s.charAt(i)) - map.get(s.charAt(i - 1));
                i--;
            }  else if (i == 0) {
                if(i == 0) total += map.get(s.charAt(i));
            }else {
                total += map.get(s.charAt(i));
            }
        }
        return total;
    }


    /**
     * Convert an integer to its roman numberal symbol
     *
     *
     * 3 - III
     *
     * 58
     * 8 - VIII
     * 50 - L
     * LVIII
     *
     * 1994
     * 4 - IV
     * 90 - XC
     * 900 - CM
     * 1000 - M
     * MCMXCIV
     *
     * check if
     * 1. digit < 4
     * 2. digit <
     * PLAN
     * initialize array with values 1 - 9 in sorted order and array
     * with roman numeral symbol
     * 1 4 5 9 10
     * fn - find the greatest value roman numeral that fits into digit
     * while digit > 0
     *
     * find the smallest digit in map that fits into digit
     *
     * subtract digit with largest factor amount
     * update result value with result from map
     * */
    public static String intToRoman(int num){
        String[] numeral = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String result = "";
        int multiplier = 1;

        while(num > 0){
            int digit = (num % 10);
            if(digit > 0){
                result += numeral[digit - 1] ;
            }
            num /= 10;
            multiplier *= 10;

        }

        return result;
    }
}
