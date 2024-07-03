package CodePath.Week4;

import Node.ListNode;

import java.util.*;

public class Session2 {

    public static void main(String[] args){
        testValidStack();
    }

    public static void testValidStack(){
        int[] popped = new int[]{1, 2, 3, 4, 5};
        int[] pushed = new int[]{4, 5, 3, 2, 1};
        boolean val = validStackSequence(popped, pushed);
        System.out.println(val);
    }

    public static void testAddTwoNumII(){
        ListNode head1 = new ListNode(7);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        head1.next = node1;
        node1.next = node2;
        node2.next = node3;

        ListNode head2 = new ListNode(5);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(4);
        head2.next = node4;
        node4.next = node5;

        ListNode result = addTwoNumbers(head1, head2);
        printLL(result);
    }

    public static void printLL(ListNode head){
        while(head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static void testNumHungryStudents(){
        int[] students = new int[]{1, 0, 1,1};
        int[] sandwhiches = new int[]{0, 0, 1, 1};
        int res = numStudentsHungry(students, sandwhiches);
        System.out.println(res);
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

    /**
     * Given 2 linked list with non-neg integers as values, find the sum
     * of the values in the linked list.
     * - the values do not contain leading 0s
     * - values range from 0-9
     * - the most significant digit comes first
     *
     * questions
     * - can either list be empty?
     * - can we use extra space?
     * - time constraints?
     *
     * examples
     * - [1, 2, 3] [5, 6, 7] => 6 => 9 => 0 =>
     * - [9 8 0 9] [1 2 1] => 9 -> 9 -> 3 -> 0 ->
     * - [1 2 3] [] => 1 -> 2 -> 3->
     *
     * methods
     * - 2 pointers and while loop
     * - recursively add and carry over digit then find next node
     * - stack to reverse linked list values and new ll result
     *
     * plan
     * initialize a 2 stacks for l1 and l2
     * iterate over each list and add both values into the stack
     * create a sum stack
     * declare a temp node point to result ll
     * while both stack are not empty
     * find sum of the tops of each stack + carry
     * calculate the carry sum / 10
     * and then add the % 10 of sum as new node to stack
     * initialize a result list and temp head
     * while result stack is not empty
     * remove and add top of stack to result list
     *
     * */
    public static ListNode addTwoNumbersII(ListNode L1, ListNode L2){
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<ListNode> resultStack = new Stack<>();

        ListNode l1 = L1;
        ListNode l2 = L2;
        int carry1 = 0; int carry2 = 0;
        while(l1 != null || l1 != null){
            if(l1 != null){
                int sum = l1.val + carry1;
                stack1.push(sum % 10);
                carry1 = sum / 10;
                l1 = l1.next;
            }
            if(l2 != null){
                int sum = l2.val + carry2;
                stack2.push(sum % 10);
                carry2 = sum / 10;
                l2 = l2.next;
            }
        }

        int carry = 0;
        int sum = 0;
        while(!stack1.isEmpty() || !stack2.isEmpty()){
            if(!stack1.isEmpty() && !stack2.isEmpty()){
                sum = stack1.pop() + stack2.pop() + carry;
            } else if (stack1.isEmpty()){
                sum = stack2.pop() + carry;
            } else {
                sum = stack1.pop() + carry;
            }
            carry = sum / 10;
            resultStack.push(new ListNode(sum % 10));
        }
        ListNode result = new ListNode(-1);
        ListNode temp = result;
        while(!resultStack.isEmpty()){
            temp.next = resultStack.pop();
            temp = temp.next;
        }
        return result.next;
    }

    // find the sum of the linked lists
    // plan
    // reverse both linked lists
    // declare ptrs for each ll
    // initiailize a sum and carry value
    // while boht ptrs are not null
    // check l1 and l2 are not null get sum + carry
    // check l1 null and l2 not null get sum + carry
    // check l1 not null and l2 null get sum + carry
    // create new node with sum % 10 and add to result ll
    // update carry to be sum / 10
    // move result ptr to next node
    // once done check if carry is != 0 and add carry node
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode rl1 = reverseList(l1);
        ListNode rl2 = reverseList(l2);

        int sum = 0;
        int carry = 0;
        ListNode res = new ListNode(-1);

        while(rl1 != null || rl2 != null){
            if(rl1 != null) sum += rl1.val;
            if(rl2 != null) sum += rl2.val;

            res.val = sum % 10;
            carry = sum / 10;
            ListNode prev = new ListNode(-1);
            prev.next = res;
            res = prev;
            sum = carry;

            rl1 = rl1 != null ? rl1.next : null;
            rl2 = rl2 != null ?rl2.next : null;
        }
        return carry == 0 ? res.next : res;
    }

    private static ListNode reverseList(ListNode head){
        ListNode curr = head;
        ListNode next = head;
        ListNode prev = null;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

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

    // methods: queue & stack with pointers and a counter for tracking loops in queue
    // plan
    // initialize a queue and add each student to the queue
    // declare pointer for the sandwich array s and create a counter to check for
    // students that must line up again
    // while s ptr has not reached end of sandwhich array, then
    // check if head of queue and sandwhich[s] match
    // then increment s and remove head of queue and set repeat line up to 0
    // otherwise increment the repeated lined up student counter
    // check if the size of queue is the same as the number of students that
    // have lined up => this means that we've hit a loop in the queue and return queue size
    // otherwise return 0 if all s reaches sandwhiches length
    public static int numStudentsHungry(int[] students, int[] sandwiches){
        Queue<Integer> queue = new LinkedList<>();
        for(int student : students){
            queue.add(student);
        }
        int s = 0;
        int elapsedStudents = 0;
        while(s < sandwiches.length){
            if(queue.peek() == sandwiches[s]){
                queue.remove();
                s++;
                elapsedStudents = 0;
            } else {
                int top = queue.remove();
                queue.add(top);
                elapsedStudents++;
            }
            if(elapsedStudents == queue.size()){
                return queue.size();
            }
        }
        return 0;
    }

    /**
     * Given 2 int arrays pushed and popped with each distinct values
     * return true if the result of a sequence of push and pop operations
     * on an initially empty stack
     *
     * questions
     * - can either array be empty
     * - will arrays always be the same size?
     * - what's the max size of the arry?
     * - are there any time/space constraints?
     *
     * examples
     * - [1 2 3] [2 3 1]
     * => push(1) push(2) pop() {2} push(3) pop() {3} pop() {1} true
     * - [1 2 3] [3 1 2]
     * => false
     * - [1] [0]
     * => false
     *
     * methods
     * - use a stack to maintain the values in pushed that can be popped and a ptr
     * to popped
     * - multiple passes with 2 ptrs
     *
     * plan
     * - initialize a stack and 2 ptrs for pushed and popped
     * - while popped ptr is < popped length
     * check if pushed[p1] != popped[p2] => add value to stack
     * otherwise remove top value from stack + increment popped ptr
     * continue until p1 or p2 reach length of arrays
     * return size of stack (if it is true then stack size should be 0)
     * */
    public static boolean validStackSequence(int[] pushed, int[] popped){
        // 1 2 3
        // 2 3 1
        Stack<Integer> stack = new Stack<>();
        int p1 = 0;
        int p2 = 0;

        while( p1 < pushed.length && p2 < popped.length){ // 2 1
            if(p1 < pushed.length) stack.push(pushed[p1++]); // 1 -> 3
            while(!stack.isEmpty() && stack.peek() == popped[p2]){ // 1 == 1
                stack.pop(); //
                p2++; // 3
                p1++; // 4
            }
        }
        return stack.isEmpty();
    }

}
