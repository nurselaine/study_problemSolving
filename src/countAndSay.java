import java.util.ArrayList;
import java.util.List;

public class countAndSay {

    public static void main(String[] args){
//        System.out.println("ans: " + countAndSay(4));
        System.out.println("ans " + reverseCountAndSay(1112));
    }

    // Given an integer, create a digit string where
    // call(n) = call(n - 1)
    // and n == 1 is 1 so
    // call(1) = 1
    // call(2) = 1 and to say 1 you say 11 (one 1's)
    // call(3) = 11 and to say 11 you say 21 (two 1's)
    // do this for n
    public static String countAndSay(int n){
        if(n == 1){
            return "1";
        }

        String s = countAndSay(n - 1);
        StringBuilder ans = new StringBuilder();
        int counter = 0;

        for(int i = 0; i < s.length(); i++){
            counter++;
            // check if i is last char or if i is not same as i + 1 in s
            if(i == s.length() - 1 || s.charAt(i) != s.charAt(i + 1)){
                ans.append(counter).append(s.charAt(i));
                counter = 0;
            }
        }
        return ans.toString();
    }
    // PLAN
    // recursively call countAndSay
    // use a StringBuilder and counter
    // the stringbuilder is to create the resulting "said number from the previous call"
    // and the counter is to count the occurences of each number in the previous call
    // itereate through the string of the previous call and update it with the new "said number"
    // return the stringbuilder string once done!

    // given a number
    // return the said number and all possible combinations of said number
    // reverse(1211) -> 21 (one 2 and one 1)
    // reverse(11) -> 1 (one 1
    public static List<String> reverseCountAndSay(int n){
        List<String> result = new ArrayList<>();
        reverseHelper(n, result);
        return result;
    }

    private static void reverseHelper(int n, List<String> result){
        String input = Integer.toString(n);
        // base cases
        if(input == null || input.length() == 1){
            return;
        } else if (input.length() == 2){
            int count = Character.getNumericValue(input.charAt(0));
            char val = input.charAt(1);
            result.add(generateString(count, val));
        } else if (input.length() == 3){
            int count = Integer.parseInt(input.substring(0, 2));
            char val = input.charAt(2);
            result.add(generateString(count, val));
        } else {
            // take entire string expect last char as digit
            int count = Integer.parseInt(input.substring(0, input.length() - 1));
            char val = input.charAt(input.length() - 1);
            result.add(generateString(count, val));
        }

        // iterate from 2 to input length
        for(int i = 2; i < input.length(); i++){
            // use a left and right sublist array
            List<String> leftSub = reverseCountAndSay(Integer.parseInt(input.substring(0, i)));
            List<String> rightSub = reverseCountAndSay(Integer.parseInt(input.substring(i)));

            // find all combinations of left + right sub lists
            for(String l : leftSub){
                for(String r : rightSub){
                    result.add(l + r);
                }
            }
        }
    }


    private static String generateString(int count, char digit){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < count; i++){
            sb.append(digit);
        }
        return sb.toString();
    }
    // PLAN
    // Input is converted to a string
    // cases where the length of string is
    // 1. return an emtpy string
    // 2. get the first digit as count and second digit as value and generate string
    // - add string to result
    // 3. get the first 2 digits as count and last digit as value and generate string
    // -- add string to result
    // 4+. otherwise substring input from 0 to input len - 1 as count and last digit as value, generate string
    // -- add string to result
    // starting at the 2nd index (because 1 is not valid) iterate over input
    // use a left and right sublists and recuresively call reverseCountAndSay
    // 1. left sublist should pass in the substring of the input from 0 to i
    // 2. right sublist should pass in the substring from i to end of input
    // loop through all combinations of left and right sublist strings
    // add these combinations into results list
    // return result
}
