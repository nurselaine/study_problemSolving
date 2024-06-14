package CodePath.Week2;

import Node.ListNode;

import java.util.List;
import java.util.Stack;

public class Session2 {
    public static void main(String[] args){
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
}
