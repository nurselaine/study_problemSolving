package CodePath.Week1;

import java.util.*;

public class Session2 {

    public static void main(String[] args){
//        testValidParenthesis();
//        testKthLargestNumber();
        testKthLargestClass();
    }

    /**
     * Valid Parenthesis
     *
     * Understand
     * - Check whether there are equal amounts of closing brackets
     * and opening brackets where each open brac matches the closing
     * bracket
     * - () : this is valid
     * - (()){} : this is valid
     * - ({[)}) : This is NOT valid
     * - {{{{ : this is NOT valid
     *
     * Mathod
     * - Using a stack and map which can be used for
     * mapping the opening and closing bracket and because
     * the stack will keep order of the completed pairs
     *
     * Plan
     * - Initialize the stack and map
     * - map should contain the possible open paranethsis and keys and closing paran as values
     * - iterate over the string of parans
     * - check if:
     *      - the current paren is opening then add to stack
     * - otherwise, check if:
     *      - take the top paran from the stack and check if it maps to the current closing paren
     * - return whether the stack is empty
     * */
    public static boolean validParenthesis(String s){
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        for(int i = 0; i < s.length(); i++){
            char curr = s.charAt(i);
            if(map.containsKey(curr)) {
                stack.push(curr);
            } else if(!stack.isEmpty() && map.get(stack.peek()) == curr) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void testValidParenthesis(){
        String paran = "()";
        boolean res = validParenthesis(paran);
        System.out.println(res);

        paran = "{{{{{{{";
        res = validParenthesis(paran);
        System.out.println(res);

        paran = "}";
        res = validParenthesis(paran);
        System.out.println(res);
    }

    /**
     * Kth Largest Element in a Stream
     *
     * Understand
     * - Given an integer K and an arary of integers find the kth largest element
     * - Is the length of the arr always greater than k?
     * - Will K be less than or equal to length of arr
     * - Return an integer found within the arr that represents the kth largest integer
     *
     * Method
     * - Use a max heap to order the array elements by max value
     *
     * Plan
     * - Initialize a max heap and order it by greater number first
     * - iterate over the arr
     * - add each element into the heap
     * - check if the heap is > k size
     * - if it is then remove
     * - once the entire array has been passed through the heap
     * - return the top value of the heap
     * */
    public static int kthLargestNumber(int k, int[] nums){
        if(k > nums.length){
            return -1;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for(int i : nums){
            if(maxHeap.size() == k){
                maxHeap.poll();
            }
            maxHeap.offer(i);
        }
        return maxHeap.poll();
    }

    public static void testKthLargestNumber(){
        int[] nums = new int[]{4, 5, 8, 2};
        int res = kthLargestNumber(3, nums);
        System.out.println(res);
    }

    /**
     * Number of Recent Calls
     * */

    /**
     * Min Stack
     *
     * design a stack that supports push, pop, top and retrieving mimimum element in constant time
     * */

    /**
     * Implement queue using stacks
     *
     * only use two stacks
     * */

    /**
     * Test KthLargest Class
     * */
    public static void testKthLargestClass(){

        KthLargestElement ktest = new KthLargestElement(3, new int []{4, 5, 8, 2});
        int res = ktest.add(10);
        System.out.println(res);
        res = ktest.add(15);
        System.out.println(res);
        res = ktest.add(10);
        System.out.println(res);

    }
}

/**
 * Design a class to find the kth largest element in a stream.
 * Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 *
 * Using a heap
 * k = 3
 * add([4, 5, 8 2]
 * getKthLargest() => 5
 * add(3) [4, 5. 8, 2,3]
 * getKthLargest() => 4
 * add(5) [4, 5. 8, 2, 5]
 * getKthLargest() => 5
 * add(10) => 5
 * */
class KthLargestElement {

    public int k;
    private PriorityQueue<Integer> minHeap;
    public KthLargestElement(int k, int[] nums){
        this.k = k;
        this.minHeap = new PriorityQueue<>();
        for(int i : nums){
            minHeap.offer(i);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
    }

    public int add(int val){
        minHeap.add(val);

        if(minHeap.size() > k){
            minHeap.poll();
        }

        return minHeap.peek();
    }
}

/**
 * Design a stack class
 * that supports retrieiving min element in a list in O(1) time
 * Push, Pop, Add, Min
 *
 * Understand
 * In a regular stack, we would need to search through the entire stack
 * The stack should order added items in ascending order where the top of
 * the stack is the min value and the bottom of the stack is the largest value
 *
 * Questions
 * Limitation is O(!) runtime
 * What sort of value does the stack hold? - Integer
 * Should I add error handling?
 *
 * Example
 * stack()
 * push(3)
 * push(8)
 * push(4)
 * getMin() => 3
 * pop()
 * getMin() => 4
 * push(1)
 * getMin() => 1
 * pop()
 * getMin() => 4
 *
 * Match
 * - keep 2 stacks, one a stack for insertion order and the other to
 * track the min values encountered
 *
 * Plan
 * - create a constructor
 * */
class MinStack {

    public Stack<Integer> stack;
    private Stack<Integer> minStack;
    private int min;
    public MinStack(int val){
        this.stack = new Stack<>();
        stack.push(val);
        minStack = new Stack<>();
        minStack.push(val);
        min = val;
    }

    public void push(int val){
        if(minStack.isEmpty() || val < minStack.peek()){
            minStack.push(val);
        } else {
            minStack.push(minStack.peek());
        }
        stack.push(val);
    }

    public void pop(){
        minStack.pop();
        stack.pop();
    }

    public int peek(){
        return stack.peek();
    }

    public int getMin(){
        return minStack.peek();
    }
}

class RecentCounter {
    public int privateRequests;
    private int timeElaspe;
    public RecentCounter(){
        privateRequests = 0;
    }

    public int ping(int t){
        return -1;
    }
}