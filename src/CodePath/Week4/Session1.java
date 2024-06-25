package CodePath.Week4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Session1 {

    public static void main(String[] args){
        testLastStoneWeight();
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
}
