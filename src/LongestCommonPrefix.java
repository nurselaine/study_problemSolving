public class LongestCommonPrefix {

    public static void main(String[] args){
        String[] test1 = new String[]{"flower", "flow", "flight"};
        String[] test2 = new String[]{"dog", "racecar", "car"};
        String[] test3 = new String[]{"ab", "a"};
        String result = longestCommonPrefixBS(test1);
        System.out.println(result);
    }

    // Find the longest common prefix in a list of strings
    // Plan
    // Get the first word from the array
    // iterate through the array not including the first word
    // check if each word starts with the first word of the array
    // if it does not then remove a character from the end of the first word
    // continue until the current word in the array starts with the first word
    // repeat to check that the first word in the array matches all the words in the array
    public static String longestCommonPrefix(String[] strs){
        String prefix = strs[0];

        for(int i = 1; i < strs.length; i++){

            // if curr word does not start with prefix
            while(!strs[i].startsWith(prefix)){

                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.length() == 0){
                    return "";
                }
            }
        }
        return prefix;
    }

    // Find the longest common prefix using binary search
    // find the shortest length word in the array
    // this is the longest the prefix can be
    // use a start and end ptr and then calculate the mid
    // use a helper function
    // substring the first word to be mid length and check whether
    // the words in strs starts with this first word.
    // if it does not the return false from the helper function
    // - make the mid value smaller by updating the end value
    // if it does contain the substring in all words of str
    // - make the mid value larger by updating the start value
    // repeat until start and end ptr overlap
    // the longest common prefix will be a substring from the beginning
    // of the first word to the mid value of the curr start and end ptr values
    public static String longestCommonPrefixBS(String[] strs){

        // find the shortest length string
        int shortestLen = Integer.MAX_VALUE;

        for(String s : strs){
            shortestLen = Math.min(shortestLen, strs.length);
        }

        // use start and end ptrs
        int start = 0;
        int end = shortestLen;

        while(start <= end){
            int mid = (start + end) / 2;

            if (isAPrefix(strs, mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return strs[0].substring(0, (start + end) / 2);
    }

    private static boolean isAPrefix(String[] strs, int len){

        String prefix = strs[0].substring(0, len);
        for(int i = 1; i < strs.length; i++){
            if(!strs[i].startsWith(prefix)){
                return false;
            }
        }
        return true;
    }
}
