package CodePath.Week4;

import java.util.*;

public class Session2 {

    public static void main(String[] args){
        testMaxStack();
    }

    public static void testMaxStack(){
        MaxStack maxStack = new MaxStack();
        maxStack.push(2);
        int max = maxStack.findMax();
        System.out.println(max);
        maxStack.push(6);
        max = maxStack.findMax();
        System.out.println(max);
        System.out.println(maxStack.pop());
        System.out.println(maxStack.findMax());
    }

    /**
     * given an int array and an int k, return the kth largest element
     *
     * questions
     * - is the array sorted? no
     * - does the array of identical values/unique values?
     * - are the values positive & negative?
     * - what is the max size of the array?
     * - can the array be empty?
     * - is k within the range of 0 to len of array?
     * - time/space constraints?
     *
     * examples
     * - [1 2 2 3 4] k = 3
     * => 3
     * - [8 3, 10, 33, 2] k = 2
     * => 10
     * - [1] k = 1 => 1
     * - [] k = 0 => -1
     *
     * methods
     * - sorting and count to kth largest number O(NlogN)
     * - heap (O(N) to build + O(logN)) add my values in and keep my heap size <= k
     * - multiple passes over to find max values and update array to remove k max values
     *
     * plan
     * initializing a min heap  & visited set
     * adding each unique value in the array to the minheap
     *  if heap size > k
     *      remove the top of the heal
     * return top of heap
     *
     * review
     * using a minheap to order values in ascending
     * and a visitied set to track add only unique values to
     * the minheap - we can add values up to k min heap size
     * and remove the top of the minheap after reviewing all
     * the nums
     *
     * runtime: O(N)
     * memory: O(K)
     * */
    public static int kthLargest(int[] nums, int k){
        // [8 3, 8 10, 33, 2] k = 2
        // [1] k = 1
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int n : nums){ // 1
            minHeap.offer(n); // 1
            if(minHeap.size() > k){ // 1
                minHeap.poll(); //
            }
        }
        return minHeap.poll();
    }

    /**
     * given 2 arrays where one is a queue and the other is a stack
     * find the number of non matching values left in the queue
     * after going over each value to match between teh stack and queue
     * you can only remove values from queu and stack when they are matching
     *
     * - queue and stack are always equal size
     * - values will always be 0 and 1 in arrays
     *
     * questions
     * - what is the min size of the queue?
     * - what is the max size of the queue?
     * - can either be empty?
     * - are there any time or space constraints?
     *
     * examples
     * - [1 0 1 1] [0 0 1 1] => 3
     * - [1 0 1] [0 1 1] => 0
     * - [] [] => 0
     *
     * methods
     * - 2 ptrs and using a queue to iterate over queue
     *  - we can also use a counter to check how many students have lined up again
     * - arraylist becauase its easy to pop from the top and move it to the back
     *  - it shifts all of the elements left/shift
     *
     * plan
     * - initialize a queue and create a counter to count # of people getting back in line
     * - initialize a ptr for the stack
     * while stack ptr is < stack size
     *   check if queue head is == stack top
     *      remove head and pop top of queue and stack
     *   otherwise
     *      increment the elapsed students & remove head of queue and add back to end
     *   check if elasped students == to size of queue
     *      return queue size
     * return 0
     * */
    public static int countStudents(int[] students, int[] sandwiches){
        // [1 0 1]
        // [0 1 1]
        Queue<Integer> queue = new LinkedList<>();
        for(int student : students){
            queue.add(student);
        }
        int elapsedCount = 0;
        int s = 0;
        int S = sandwiches.length;
        while(s < S){
            if(queue.peek() == sandwiches[s]){
                queue.remove();
                s++;
                elapsedCount = 0;
            } else {
                elapsedCount++;
                int top = queue.remove();
                queue.add(top);
            }
            if(elapsedCount == queue.size()){
                return queue.size();
            }
        }
        return 0;
    }

//    public static int addTwoNumbersII(){
//
//    }

    public static class MaxStack {

        private Stack<int[]> stack;
        public MaxStack(){
            stack = new Stack<>();

        }

        // Create a new int[] with
        // int[0] => value
        // int[1[ => current max
        // find current max by compairng whether
        // top int[1] is >= current value
        // if it is then new int[]{val, top[1]
        // otherwise new int[val, val]
        // if stack is empty then add int[val, val]
        public void push(int val){
            if(stack.isEmpty()){
                stack.push(new int[]{val, val});
            } else if (val > stack.peek()[1]){
                stack.push(new int[]{val, val});
            } else {
                stack.push(new int[]{val, stack.peek()[0]});
            }
        }

        // peek from top and return top[1]
        // if stack is empty return -1
        public int findMax(){
            if(stack.isEmpty()){
                return -1;
            }
            return stack.peek()[1];
        }

        // pop from top
        public int pop(){
            return stack.pop()[0];
        }
    }
}
