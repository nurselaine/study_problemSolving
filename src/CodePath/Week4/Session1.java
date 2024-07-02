package CodePath.Week4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Session1 {

    public static void main(String[] args){
        testClimb();
    }

    public static void testClimb(){
        int n = 4;
        int c = climbingStairs(n);
        System.out.println(c);
    }

    public static void testRobII(){
        int[] nums = new int[]{1, 2, 3};
        int val = robII(nums);
        System.out.println(val);
    }

    public static void testLongestCommonSubsequence(){
        String text1 = "";
        String text2 = "aaaaaa";
        int result = longestCommonSubsequence(text1, text2);
        System.out.println(result);
    }

    public static void testRob(){
        int[] nums = new int[]{1, 2, 3, 1};
        int val = rob(nums);
        System.out.println(val);
    }

    public static void testPalindromePermutation(){
        String s = "aabcaacb";
        boolean res = canPermutePalindrome(s);
        System.out.println(res);
        s = "a";
        res = canPermutePalindrome(s);
        System.out.println(res);
        s = "acba";
        res = canPermutePalindrome(s);
        System.out.println(res);
    }

    public static void testMeetingRoomsII(){
        int[][] intervals = new int[][]{{3, 4}, {4, 8}, {5, 8}, {2, 6}};
        int rooms = minMeetingRooms(intervals);
        System.out.println(rooms);
        intervals = new int[][]{{3, 4}, {4, 8}};
        rooms = minMeetingRooms(intervals);
        System.out.println(rooms);
        intervals = new int[][]{{1, 2}};
        rooms = minMeetingRooms(intervals);
        System.out.println(rooms);
    }

    public static void testLastStoneWeight(){
        int[] stones = new int[]{8, 7, 5, 3, 1};
        int result = lastStoneWeight(stones);
        stones = new int[]{5, 4, 3, 2, 1};
        result = lastStoneWeight(stones);
        System.out.println(result);
        stones = new int[]{3};
        result = lastStoneWeight(stones);
        System.out.println(result);
    }

    public static void testKMostFreqWords(){
        String[] words = new String[]{"I", "love", "leetcode", "I", "love", "coding"};
        String[] result = kMostFreqWords(words, 2);
        words = new String[]{"I", "am", "ham", "I", "am", "human"};
        result = kMostFreqWords(words, 3);
//        words = new String[]{"I", "am", "ham"};
//        result = kMostFreqWords(words, 3);
        for(String s : result){
            System.out.print(s + " ");
        }
    }

    public static String[] kMostFreqWords(String[] words, int k){
        Map<String, Integer> wordCount = countWordFreq(words);
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>((a, b) -> {
            if(a.getValue() == b.getValue()){
                return b.getKey().compareTo(a.getKey());
            }
            return a.getValue() - b.getValue();
        });

        for(Map.Entry<String, Integer> entry : wordCount.entrySet()){
            minHeap.offer(entry);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        String[] result = new String[k];
        for(int i = k - 1; i >= 0; i--){
            result[i] = minHeap.poll().getKey();
        }

        return result;
    }

    private static Map<String, Integer> countWordFreq(String[] words){
        Map<String, Integer> map = new HashMap<>();
        for(String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        return map;
    }

    /**
     * Last stone weight
     * given an array of stones where each element represents a stone weight
     * , play a game where each turn the 2 heaviest stones are smashed together
     * and broken leaving the difference in their weight as a resulting stone
     *
     * - when stone[i] == stone[j] => no stone is left
     * - when stone[x] != stone[y] and x <= y then stone[z] = stone[y] - stone[x]
     * - an array of integers
     * - return 0 if there are no remaining stones after smashing all stones
     *
     * questions
     * - are the elements positive integers only? yes
     * - what is the max weight of a stone? 1000
     * - How many stones can there be? 30
     * - Are there time or space constraints?
     *
     * examples
     * - [8, 7, 5, 1, 3] => 8 -7 [1, 5, 1, 3] => 5 -3 [1, 1, 2] => 2 -1 [1 1] => 0
     * - [5 3 1] => 5 -3 => [2, 1] 2 - 1 => [1] => 1
     * - [1] => 1
     *
     * methods
     * - multiple iterations: double for loop while there is 1 or more stones left
     * - sorting: heaviest to lightest
     * - Heap - sorts stone from heaviest
     *  - O(N) build heap
     *  - logN * N + n/2
     * - two pointers: this will overcomplicate this problem
     *
     * plan
     * initialize a max heap with all stone weight values
     * while heap size is greater or equal to 2
     * - remove 2 stones from heap
     * - find the resulting stone: stone[i] - stone[j]
     * - add resulting stone back into heap
     * return the resulting value in heap
     *
     * review
     * using a maxheap to order all stones from largest to smallest
     * then smashing the 2 largests by removed from the top of maxheap
     * taking the residual stone and adding back into the heap
     * returning the remaining value
     *
     * [8, 7, 5, 1, 3]
     * [1]
     *
     * runtime: O(N) + O(N - 1) * O(LogN) => O(NLogN)
     * memory: O(N)
     * */
    public static int lastStoneWeight(int[] stones){
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for(int stone : stones){
            maxHeap.offer(stone);
        }

        while(maxHeap.size() >= 2){
            int stone1 = maxHeap.poll(); // 1
            int stone2 = maxHeap.poll(); // 1
            int resultStone = stone1 - stone2; // 0
            maxHeap.offer(resultStone); // 0
        }
        return maxHeap.poll();
    }

    /**
     * Meeting Rooms II
     * Given an array of meeting time intervals where intervals[i] = [start, end]
     * return the minimum number of conference rooms required
     *
     * questions
     * - What is the min and max number of meeting intervals?
     * - Are the times integer or decimal values?
     * - An additional meeting room is required when the end time of a
     * meeting is greater than or equal to the start time of another meeting?
     * - What are the time or space constraints?
     * - Would a end time == start time require a new meeting room? No
     * - Is the meeting intervals sorted?
     *
     * examples
     * - [[6, 7], [13, 15], [9,11], [10, 14]] => 3
     * - [[1, 3]] => 1
     * - [[6, 11], [10,11], [9,11]] => 3
     * - [] => 0
     *
     * methods
     * - sorting in ascending order by the start times then using 2 pointers to check preceding meeting
     * - priority queue: tracking the meetings by start times
     *  - create heap O(N)
     *  - remove from heap: O(logN)
     * - multiple looping: to search for the next closest meeting time
     * - binary search: not applicable
     * - sliding window: would over complicate problem
     * - hashtable: would over complicate problem
     *
     * plan
     * initialize a roomCounter and a minHeap ordered by meeting start times
     * add all meeting intervals to heap
     * while heap size is at least 2
     * - remove the top of the heap
     * check if:
     * 1. removed end time is >= current top of heap start time
     *  => increment roomCounter
     * return the roomCounter for the total rooms needed
     *
     * sort the intervals by the start time
     * initialize a minHeap and add the first meetings end time to the heap
     * - the heap will track all the ending times
     * each iteration of the intervals, check whether the top of the heap is
     * less than the current interval's start time => this means the room is free
     * to use and the top element can be removed from the heap
     * the heap tells us the minimum number of rooms needed
     *
     * review
     * keep a room counter and initialize a meeting end time heap
     * then iterate over all the meeting times. If the heap is empty (first meeting)
     * then add the end time to the minHeap.
     * otherwise, check if the current start time is < the earlier end time (top of heap)
     * if it is then add the current end time to the heap
     * else remove the top of the heap and add the current end time of the heap
     *
     * time: O(NlogN) + O(N) * O(2logN) => O(NLogN)
     * space: O(N)
     * */
    public static int minMeetingRooms(int[][] intervals){
        int roomCounter = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for(int[] interval : intervals){
            if(minHeap.isEmpty() || interval[0] < minHeap.peek()){
                minHeap.offer(interval[1]);
            } else {
                minHeap.poll();
                minHeap.offer(interval[1]);
            }
            roomCounter = Math.max(roomCounter, minHeap.size());
        }

        return roomCounter;
//        if (intervals.length == 0) return 0;
//        int roomCounter = 1;
//        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
//        // [[6, 7], [13, 15], [9,11], [10, 14]] => 3
//        for(int[] interval : intervals){
//            minHeap.offer(interval);
//        }
//        // {[6, 7], [9, 11] [10,14] [13, 15]}
//
//        while(minHeap.size() >= 2){
//            int[] currentMeeting = minHeap.poll(); // [10, 14]
//            if(currentMeeting[1] > minHeap.peek()[0]){ // 13
//                roomCounter++; // 2
//            }
//        }
//        return roomCounter; // 2
    }

    /**
     * Palindrome Permutations
     * given a string return true if a permutation of the string can form a
     * palindrome and false otherwise
     *
     * - string consist of lowercase letters without spaces
     * - palindrome consist of even count letter OR even count letters and one odd count letter
     * - palindrome can consist of one letter
     *
     * questions
     * - can the string be empty?
     *  - what should be returned if string is empty?
     * - what is the max length of the string?
     * - time/space constraints?
     *
     * examples
     * - "abccb" => true
     * - "acba" => false
     * - "acddac" => true
     * - "a" => true
     *
     * methods
     * - sorting in lexographic order and using sliding window and odd num flag to check string validity
     * - Hashmap for counting characters + checking map for even and at up to one odd letter count
     *  - O(1) to access character & O(N) space
     * - Alphabet array of 26 - add each char count to arr and iterate over values to check # of odd num counts
     *  - O(N) to initialize and O(1)
     * - multiple passes over string and checking char count with odd number flag
     *
     * plan
     * initialize a map
     * add each char count to map
     * iterate over map value set and declare a oddNumFlag
     * increment odd num flag each time an odd number is encountered
     * return whether flag is > 1
     *
     * review
     * - initialize a map and count each char into map
     * - iterate over each map value
     * - increment odd number counter for each odd num
     * encountered
     * - return whether odd number is > 1
     *
     * time: O(N) + O(N) => O(N)
     * time: O(N) + O(26) => O(N)
     * space: O(1)
     * space: O(1)
     * */
    public static boolean canPermutePalindrome(String s){
        int[] charArr = new int[26];
        for(char c : s.toCharArray()){
            charArr[c - 'a']++;
        }
        int oddNumCounter = 0;
        for(int ch : charArr){
            if(ch % 2 != 0){
                oddNumCounter++;
            }
            if(oddNumCounter > 1) return false;
        }
        return true;
//        Map<Character, Integer> charCount = new HashMap<>();
//
//        for(char c : s.toCharArray()){
//            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
//        }
//        int oddNumCounter = 0;
//        for(int count : charCount.values()){
//            if(count % 2 != 0){
//                oddNumCounter++;
//            }
//        }
//        return oddNumCounter <= 1;
    }

    /**
     * Given a list of values representing money in each house[i] of a neighborhood
     * find the max about of money a robber can take. If the robber steals from
     * two adjacent houses i + [i + 1] then they will be stopped
     * - find the max amount of money without getting stopped
     *
     * questions
     * - can any of the values be negative?
     * - What is the min and max number of houses in the list?
     * - Are there time or space constraints?
     *
     * examples
     * - [3 2 5 1 2] => 10
     * - [3 4] => 4
     * - [1] => 1
     *
     * methods
     * - recursively calculate all possibilities and use a memo table to track prev calculated totals
     * - 2 pointers and multiple passes: not time effective
     *
     * plan
     * - getRoute => helper function
     * declare a hashmap to store calculated totals
     * check if current index is greater than nums size
     *  => return the current total
     * check if the current index is in the memo table
     *  => return the value
     * calculate the value of the current value at index + recursively calling index + 2 AND compare
     * with the value of recursive call to index + 1
     * add the current index with the max between the two above values to the map
     * */
    public static int rob(int[] nums){
        Map<Integer, Integer> memo = new HashMap<>();
        return getRoute(nums, memo, 0);
    }

    private static int getRoute(int[] nums, Map<Integer, Integer> memo, int i){
        if(i >= nums.length){
            return 0;
        }

        if(memo.containsKey(i)){
            return memo.get(i);
        }

        int runningTotal = Math.max(getRoute(nums, memo, i + 1), (nums[i] + getRoute(nums, memo, i + 2)));

        memo.put(i, runningTotal);
        return runningTotal;
    }

    /**
     * Given 2 strings, determine if either are a subsequence of the other
     * subsequence: when characters of a string appear in the same order as the other string
     * => ace akcnel are subsequence
     *
     * questions
     * - Can either string have spaces or special characters?
     * - Does upper or lower case matter?
     * - How long can either string be?
     * - Can either string be emtpy?
     * - TIme/Space constraints?
     *
     * examples
     * - s = ace t = akcjel => 3
     * - s = aa t = bbbabbb => 1
     * - s = che t = lkj => 0
     * - s = "" t = jdi => 0
     *
     * methods
     * - multiple passes: check for every letter and every possible combination
     *  => very time expensive
     * - Sorting/binary search: not applicable
     * - 2 pointers: possibly can use to track indicies of each string
     * - Hashmap: possibly can use to track length of subsequences and matching characters
     * - recursively checking for matching values
     *
     * plan
     * checkMatch => helper fn to count num of matching letters in order
     * - initialize a map to hold index, subsequence length
     * initialize 2 pointers to track index of text1 and text2
     * base cases
     * 1. check if either ptr is > length of its corresponding string
     * => 0
     * 2. check if map contains current index
     * => return value of map at index
     * 3. check if values at each ptr are equal
     * => 1 + checkMatch(ptr1 + 1, ptr2 + 1)
     * max between checkMatch(ptr1 + 1, ptr2) , checkMatch(ptr1, ptr2 + 1)
     * put current index , max value into map
     * return map at the current index
     *
     * evaluate
     * runtime: O(N) if N = len text1 + len text2
     * memory: O(N) + O(N) => O(N)
     * */
    public static int longestCommonSubsequence(String text1, String text2){
        int[][] memo = new int[text1.length()][text2.length()];
        return checkMatch(text1, text2, 0, 0, memo);
    }

    //  s = ace t = akcjel => 3
    private static int checkMatch(String text1, String text2, int t1, int t2, int[][] memo){
        if (t1 >= text1.length() || t2 >= text2.length()){ // 3 5
            return 0;
        }
        if(memo[t1][t2] > 0){
            return memo[t1][t2];
        }
        if(text1.charAt(t1) == text2.charAt(t2)){ // e e
            return 1 + checkMatch(text1, text2, t1 + 1, t2 + 1, memo); // 1 + 1 + 1 + 0
        }
        memo[t1][t2] = Math.max(checkMatch(text1, text2, t1 + 1, t2, memo), checkMatch(text1, text2,t1, t2 + 1, memo));
        return memo[t1][t2];
    }

    /**
     * given a list of houses and their values
     * find the max value that can be robbed from each house
     * - robber cannot rob 2 adjacent houses
     * => nums[i] + nums[i + 1} not ok
     * - first house + last house are considered adjacent
     * => nums[0] + nums[N - 1]
     *
     * questions
     * - can the list be empty?
     * - what's the max size of the list?
     * - what's the min size?
     * - can the values ever be negative?
     * - time/space constraints?
     *
     * examples
     * - [3 4 1 9] => 13
     * - [1] => 1
     * - [1 2 3] => 3
     *
     * methods
     * - recursion + memoization to check the amount starting at index 0 going from house i + 2
     * and repeat the following action for each index - more of an exhaustive search where I want
     * to find the best possible outcome for the robber
     * - loop through many times - not as time effective
     *
     * plan
     * declare a hashmap to store the generated totalMax values at index
     * return max values of findRoute starting for subarrays index 0 to N - 1 AND 1 to N
     *
     * findRoute => helper function
     * create pointer for current index
     * check if index is < length of list
     *  => return 0
     * check if map contains index
     * Find the max between:
     * 1. find the value starting at current index nums[i] + the total values of alternating houses => findRoute(i + 2)
     * 2. find the total value starting at nums[i + 1] by recursively checking findRoute(i + 1)
     * put index, max into map
     * return map[index]
     * */
    public static int robII(int[] nums){
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        return Math.max(findRoute(nums, 0, nums.length - 1, map1), findRoute(nums, 1, nums.length, map2));
    }

    // 0 1 2 3
    // 12 13 9 0
    // 13 9 2 9
    private static int findRoute(int[] nums, int index, int N, HashMap<Integer, Integer> map){
        // [3 4 1 9] => 13 N = 3
        if(index >= N){ // 3
            return 0;
        }
        if(map.containsKey(index)){
            return map.get(index);
        }
        int maxTotal = Math.max(findRoute(nums, index + 1, N, map),
                nums[index] + findRoute(nums, index + 2, N, map));
        map.put(index, maxTotal);
        return map.get(index);
    }

    public static int climbingStairs(int n){
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return climb(n, 0, memo);
    }

    // 0 1 2 3 4 5
    // 2 1 1 1 0 0
    // 1 1 0 0 0  0
    // 3 2 1 1
    private static int climb(int n, int i, int[] memo){
        // [0 1 2 3 4]
        if(i > n){ //
            return 0;
        }
        if(i == n){
            return 1;
        }
        if(memo[i] != -1){
            return memo[i];
        }

        int steps = climb(n, i + 1, memo)+ climb(n, i + 2, memo);
        memo[i] = steps;
        return memo[i];
    }
}
