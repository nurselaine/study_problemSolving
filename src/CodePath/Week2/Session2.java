package CodePath.Week2;

import Node.ListNode;

import java.util.List;
import java.util.Stack;

public class Session2 {
    public static void main(String[] args){
        testSwapPairs();
    }

    public static void testSwapPairs(){
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        printLL(head);
        ListNode newList = swapPairs(head);
        printLL(newList);
    }


    public static void testDuplicatesFromSortedList(){
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        printLL(head);
        ListNode res = deleteDuplicates(head);
        printLL(res);

    }


    public static void testPalindromeLinkedList(){
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        boolean res = palindromeLL(head);
        System.out.println(res);

        ListNode head2 = new ListNode(1);
        ListNode node5 = new ListNode(9);
        ListNode node6 = new ListNode(1);
        ListNode node7 = new ListNode(1);
        ListNode node8 = new ListNode(1);
        head2.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        res = palindromeLL(head2);
        System.out.println(res);

    }

    public static void testAddTwoNumberI(){
        ListNode head = new ListNode(9);
        ListNode node2 = new ListNode(9);
        ListNode node3 = new ListNode(9);
        ListNode node4 = new ListNode(9);
        ListNode node5 = new ListNode(9);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode l2 = new ListNode(9);
        ListNode l2Node2 = new ListNode(9);
        ListNode l2Node3 = new ListNode(9);
        l2.next = l2Node2;
        l2Node2.next = l2Node3;
        // 9 9 9 9 9
        // 9 9 9
        // 8 9 9 0 0 1
        ListNode emptyList = addTwoNumbersI(head, l2);
        printLL(emptyList);

    }


    public static void testAddTwoNumbersR(){
        ListNode head = new ListNode(9);
        ListNode node2 = new ListNode(9);
        ListNode node3 = new ListNode(9);
        ListNode node4 = new ListNode(9);
        ListNode node5 = new ListNode(9);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode l2 = new ListNode(9);
        ListNode l2Node2 = new ListNode(9);
        ListNode l2Node3 = new ListNode(9);
        l2.next = l2Node2;
        l2Node2.next = l2Node3;
        printLL(head);
        System.out.println();
        printLL(l2);
        System.out.println();
        ListNode res = addTwoNumber(head, l2);
        printLL(res);
    }

    public static void testRemoveNthNode(){
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        head.next = node2;
        node2.next = node3;
        printLL(head);
        head = removeNthNode(head, 1);
        printLL(head);

        ListNode emptyList = new ListNode(-1);
        printLL(emptyList);
        emptyList = removeNthNode(emptyList, 1);
        System.out.println(emptyList);
    }

    public static void printLL(ListNode head){
        while(head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    /**
     * U
     * remove the nth node from the end of the LL
     *
     * Questions
     * - is the list sorted? no
     * - is n always a valid number?
     * - is 0-indexed? is n 1-indexed? 1-indexed
     *
     * Example
     *              t    f
     * head -> 1 -> 2 -> 3 -> null n = 1
     *
     * M
     * - multiple passes: first pass is to check # of nodes and second pass is to stop and remove node
     * - 2 pointers: space 2 pointers n modes from each other and when first ptr reaches the end, we can
     * delete the node at the second ptr
     *
     * P
     * - Initialized 2 ptrs and a temp head
     * - iterate from 1 to n and update ptr2 to be n nodes away from ptr 1
     * - while ptr 2 is not null, update ptr and ptr2 until ptr2.next is null
     * when that happens update ptr1.next to be ptr1.next.next to remove the nth node
     * return the head node
     * IRE
     *
     * */
    public static ListNode removeNthNode(ListNode head, int n){
        ListNode temp = new ListNode(0);
        ListNode lead = temp;
        ListNode trail = temp;
        for(int i = 0;i < n; i++){
            lead = lead.next;
        }
        while(lead.next != null){
            lead = lead.next;
            trail = trail.next;
        }
        trail.next = trail.next.next;
        return temp.next;
    }

    /**
     * Add two numbers given 2 ll, there are numbers stored in reverse order
     * in the linked list, return the sum of the digits stored in the ll after
     * getting the correct order
     *
     * U
     * - Are there any trailing zeros? Decimals => non-negative integers
     * - any constraints
     *
     * M
     * - multiple passes
     * - recursively add values
     * - create a new linked list
     *
     * P
     * - declare a helper function
     * - pass in the link lists and a remainder valuer and a list to return
     * - base cases
     *  - check if l1 and l2 are null => return null
     *  - check if l1 and l2 are not null
     *      - get sum, update remainder and create new node
     *  - if l1 is null and l2 is not null
     *      - get sum of l2 and remainder then make new node
     *  - if l2 i snull and l1 is not null repeat
     *  - return temp node.next = recurisve call to helper fn
     * IRE
     *
     * */
    public static ListNode addTwoNumber(ListNode l1, ListNode l2){
        ListNode res = new ListNode(0);
        addTwoHelper(l1, l2, 0, res);
        // reverse ll
        res = reverseLL(res.next);
        return res;
    }

    private static ListNode addTwoHelper(ListNode l1, ListNode l2, int carry, ListNode res){
        if(l1 == null && l2 == null){
            if(carry > 0){
                res.next = new ListNode(carry);
            }
            return null;
        } else if (l1 != null && l2 == null){
            int sum = l1.val + carry;
            int val = sum % 10;
            carry = sum / 10;
            res.next = new ListNode(val);
            return addTwoHelper(l1.next, null, carry, res.next);
        } else if (l1 == null && l2 != null){
            int sum = l2.val + carry;
            int val = sum % 10;
            carry = sum / 10;
            res.next = new ListNode(val);
            return addTwoHelper(null, l2.next, carry, res.next);
        }
        int sum = l1.val + l2.val + carry;
        int val = sum % 10;
        carry = sum / 10;
        res.next = new ListNode(val);
        return addTwoHelper(l1.next, l2.next, carry, res.next);
    }

    private static ListNode reverseLL(ListNode head){
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = null;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     *
     * U
     *
     * M
     * - multiple passes (inefficient - poor space and time)
     * - need multiple pointers
     *
     * 1->2->3->4->5->null
     * a  b
     * update a ptr to b ptr
     *  1 -> 2 => 1 -> 3
     * update b.next ptr to a
     *  2 -> 3 => 2 -> 1
     * Use a temp head and assign to b after ptr swap
     * => 2 -> 1 -> 3
     * temp = 3
     * now a = 3, b = 4
     * need a ptr to the end of the prev pair (1 & 2) =>
     * P
     * -
     * IRE
     * */

    public static ListNode addTwoNumbersI(ListNode l1, ListNode l2){

        ListNode res = new ListNode(0);
        ListNode temp = res;
        int carry = 0;

        while(l1 != null || l2 != null){
            int l1Sum = 0;
            int l2Sum = 0;
            if(l1 != null){
                l1Sum = l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                l2Sum = l2.val;
                l2 = l2.next;
            }
            int totalSum = l1Sum + l2Sum + carry;
            carry = totalSum / 10;
            temp.next = new ListNode(totalSum % 10);
            temp = temp.next;
        }
        if(carry > 0){
            temp.next = new ListNode(carry);
        }
        return res.next;
    }

    /**
     * Palindrome LinkedList: given the head of a singly linked list
     * return true if it is a palindrome or false otherwise
     *
     * questions:
     * - What is a palindrome: CATTAC => yes    RAT => no
     * - Is an empty linked list considered a palindrome? at least 1 node
     * - Is a single node considered a palindrome?
     * - How long/large can the list become? 1-10^5
     * - What type value are the node vals? => integer
     * - how large can the integers be in each node => 0-9
     *
     * examples
     * - 1-> 2-> 2 -> 1 -> null => true
     *                s              f
     * - 1 -> 2 -> 3 -> 2 -> 1 -> null
     *                          sf
     * - 1-> 2-> 3-> null => false
     * - 1 -> 1-> 1 -> 1 -> null => false
     * - 1 -> null => true
     *
     * if f.next => null then odd num palindrome
     * - there can be one value without a matching pair
     * if f => null then even num palindrome
     *
     *
     * Methods
     * - 2 pointers, fast and slow to find midpoint + stack
     * - multiple passes, not time efficient
     *
     * PLAN
     * - Initialize a fast and slow ptr, move fast ptr 2x as fast as slow
     * - Create a stack
     * - iterate over list with ptrs while slow is not null
     * - move slow and fast ptrs as described
     * - each slow move, add val to stack
     * - when fast reaches end of LL, continue moving slow
     * - and pop off the stack for each matching value until
     * slow reaches end of LL or non-matching values found => return false
     * - otherwise return true
     *
     * Review
     * Eval
     * Time: O(N)
     * Space: O(1) best, O(N) worse
     * */
    public static boolean palindromeLinkedList(ListNode head){
        if(head.next == null){
            return true;
        }
        ListNode f = head;
        ListNode s = head;
        Stack<Integer> stack = new Stack<>();

        while(s != null && f != null){
            while(f != null && f.next != null){
                stack.push(s.val);
                s = s.next;
                f = f.next.next;
            }

            if (f != null && f.next == null){
                s = s.next;
            }

            while(!stack.isEmpty() && s != null){
                if(s.val != stack.peek()){
                    return false;
                }
                stack.pop();
                s = s.next;
            }
        }
        return true;
    }

    public static boolean palindromeLL(ListNode head){
        if(head.next == null){
            return true;
        }

        ListNode fast = head;
        ListNode slow = head;
        ListNode stack = head;
        while(slow != null && fast != null){
            while(fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
            }

            if(fast != null){
                slow = slow.next;
            }
            slow = reverseList(slow);

            while(slow != null){
                if(slow.val != stack.val){
                    return false;
                }
                slow = slow.next;
                stack = stack.next;
            }
        }
        return true;
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

    /**
     * Remove duplicates from an ascending sorted list II given
     * the head of a LL, delete all the nodes that
     * are duplicate numbers leaving
     * only distinct numbers from the original list
     * - the returning list should be sorted
     *
     * Questions?
     * - Are the numbers positive and negative?
     * - Can the list be empty?
     * - How large can the list get?
     * - will there always be duplicates?
     * - If there is only one int then do we return null?
     *
     * Example
     * - 1 -> 2 -> 2 -> 3 -> null
     * => 1 -> 3 -> null
     * - 1 -> 1 -> null
     * => null
     * - 1 -> null => 1 -> null
     *
     * Methods
     * - Use a temp node bc we want to manipulate the LL
     * - use two pointers to track which nodes to skip
     *
     * PLAN
     * - Create a temp node and create a curr, prev, and next ptr
     * - iterate over the ll until curr is null
     * - check if curr == next then keep moving next until
     * curr != next then point curr.next to the new next
     * and point prev to curr.next to get rid of any duplicates
     * - continue until curr reaches the end
     * return the head node
     *
     * Evaluate:
     * - time: O(N)
     * - Space: O(1)
     * */
    // 0 1 2 3 3 4 4 5
    //     p     c
    // 0 1 2 5
    //       p c
    public static ListNode removeDuplicatesII(ListNode head){
        ListNode temp = new ListNode(0);
        temp.next = head;
        ListNode curr = head;
        ListNode prev = temp;

        while(prev != null && curr != null){

            ListNode next = curr.next;

            if (next != null && curr.val == next.val){
                while(curr != null && curr.val == next.val){
                    next = next.next;
                }
                prev.next = next;
                curr = next;
            } else {
                prev = curr;
                curr = next;
            }
        }
        return temp.next;
    }

    public static ListNode deleteDuplicates(ListNode head){
        if(head == null || head.next == null) {
            return head;
        }

        if (head.val != head.next.val){
            head.next = deleteDuplicates(head.next);
            return head;
        } else {
            while(head.next != null && head.val == head.next.val){
                head = head.next;
            }
            return deleteDuplicates(head.next);
        }
    }

    /**
     * Swap every two adjacent nodes and return its head,
     * do not modify the values of the nodes
     *
     * questions
     * - In an odd number list, do we need to swap the last node?
     * - How large can the list be?
     * - Are there time/space constraints?
     *
     * example
     * - 1 -> 2 -> 3 -> 4 -> null
     * => 2 -> 1 -> 4 -> 3 -> null
     * - 1 -> 2 -> 3 -> null
     * => 2 -> 1 -> 3 -> null
     * - 1 -> null
     * => 1 -> null
     *
     * Methods
     * - Use pointers + temp node, want to manipulate the LL
     * > using a curr, next, front, and back ptr
     *
     * Plan
     * initialize a temp head
     * keep a ptr to the head
     * iterate over list while there ptr .next.next is valid
     * swap 2 nodes ahead of the ptr
     * move the ptr to the second post-swapped node
     * continue until reached end of list
     *
     * Evaluate
     * - time: O(N)
     * - space: O(N) - stack trace \ O(1) no new space created
     * */
    public static ListNode swapPairs(ListNode head){
        ListNode temp = new ListNode(0);
        temp.next = head;
        swapPairsR(temp);
        return temp.next;
    }

    private static ListNode swapPairsR(ListNode head){
        if(head.next == null || head.next.next == null){
            return head;
        }
        ListNode prev = head.next;
        ListNode curr = head.next.next;
        prev.next = curr.next; // null
        curr.next = prev; // 3
        head.next = curr;
        return swapPairsR(prev);
    }

    public static ListNode reverseListII(ListNode head, int left ,int right){
        ListNode l = head;
        ListNode r = head;
        ListNode prev = null;
        int k = right - left;
        int counter = k;
        while(r != null && counter > 0){
            r = r.next;
            counter--;
        }

        if(r == null || r.next == null){
            return reverseList(head);
        }

        while(r != null && counter < k - 1){
            counter++;
            prev = l;
            l = l.next;
            r = r.next;
        }
        ListNode next = null;
        if(r.next == null){
            next = null;
        } else {
            next = r.next;
        }
        r.next = null;
        ListNode reversed = reverseList(l);
        prev.next = reversed;
        ListNode temp = reversed;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = next;
        return head;
    }
}
