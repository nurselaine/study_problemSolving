package leetcode150;
import Node.ListNode;

import java.util.List;

public class LinkedList {

    public static void main(String[] args){
        ListNode head = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);
        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;

        boolean result = hasCycle(head);
        System.out.println(result);

    }

    /**
     * Given a head of a linked list, determine whether
     * the list has a cycle
     *
     * PLAN
     * check if list is empty then return false
     * Declare a slow and fast ptr
     * while fast != null
     * check if slow node is equal to fast node -> return true
     * update slow to be next node
     * update fast to the next next node
     *
     * return false
     * */
    public static boolean hasCycle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }
}
