public class CountingSubstrings {

    public static int result = 0;
    public static void main(String[] args){
        String test = "CABAAXBYA";

        int result = countSubstrings(test);
        System.out.println("Result: " + result);
    }

    public static int countSubstrings(String str){

        for(int i = 0; i < str.length() - 1; i++){
            if(str.charAt(i) == 'A'){
                helperCount(str, i, i + 1, str.substring(i, i + 1));
            }
        }

        return result;
    }

    private static void helperCount(String str, int start, int end, String substring){
        if(start == str.length() - 1 || end >= str.length()){
            return;
        }
        if(substring.startsWith("A") && substring.endsWith("B")){
            result++;
        }

        helperCount(str, start, end + 1, str.substring(start, end + 1));
    }
}
