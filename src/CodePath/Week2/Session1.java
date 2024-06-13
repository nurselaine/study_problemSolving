package CodePath.Week2;

import Node.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Session1 {
    public static void main(String[] args){
        testLinkListCycleII();
    }

    public static void testLinkListCycleII(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(8);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        ListNode result = linkedListCycleII(node1);
        System.out.println("Cycle starts at: " + result.val);
        ListNode node = new ListNode(1);
        result = linkedListCycleII(node);
        System.out.println("Cycle starts at: " + result);

        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(2);
        ListNode node7 = new ListNode(6);
        ListNode node8 = new ListNode(8);
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        printLL(node5);
        System.out.println("Cycle starts at: " + result);
    }

    public static void testRemoveElements(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(8);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        printLL(node1);
        removeElements(node1, 6);
        printLL(node1);
    }

    public static void testReverseLL(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(8);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        printLL(node1);
        node1 = reverseList(node1);
        printLL(node1);
    }

    public static void printLL(ListNode head){
        while(head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    /**
     * Remove all occurences of the given value in the linked list
     *
     * Questions
     * - is this a singly linked list?
     * - can the list be empty?
     * - how large can the list be?
     * - will the val always be included in the list?
     *
     * example
     * val = 6
     * head -> 1 -> 2 -> 6 -> 8 -> null
     *  => head -> 1 -> 2 -> 8 -> null
     *
     * Plan
     * create a curr and next ptr
     * while curr is not null
     *  check if curr next is == val
     *      update next ptr to curr next next
 *      otherwise move curr to curr.next
 *      return head
     * */
    public static ListNode removeElements(ListNode head, int val){
        ListNode curr = head;

        while(curr != null || curr.next != null){
            if(curr.next.val == val){
                curr = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    /**
     *  U
     *  - Reorder the ll in the opposite order it is given
     *
     *  - Will the list be empty? => null
     *  - Are there any time/space constraints?
     *  - How large can the ll be?
     *
     *  Example
     *  head -> 1 -> 2 -> 5 -> null
     *     => head -> 5 -> 2 -> 1 -> null
     *  head -> 1 -> null
     *     => head -> 1 -> null
     *  head -> null => null
     *
     *  M
     *  - Use pointers
     *  - multiple passes
     *  - sentinel (temp) node
     *
     *  P
     *  Use a curr, prev, and next pointer. Iterate over the ll
     *  and update curr to point to next then reassing prev to curr
     *  and curr to next
     *
     *  repeat over the entire ll and return prev pointer
     *  I
     *  R
     *  use 3 pointers: curr, prev and next
     *  update next to curr -> next
     *  assign curr.next to prev
     *  and assign prev to curr
     *  lastly assign curr to next
     *  and return prev pointer
     *
     *  E
     *  time: O(N)
     *  space: O(1)
     * */
    public static ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode next = null;
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
     * U
     * - Detect whether there is a cycle in the linked list and
     * return the node where the cycle starts
     *
     * Questions
     * - Will there always be a cycle?
     * - Can the LL be emtpy -> return null?
     * - Is this a singly linked list?
     * - What are the size and time constraints?
     *
     * Example
     * head => 1 -> 2 -> 3 -> 8
     *              ^---------|
     *      => returns node 8
     * head => 1 -> 2 -> 3 -> null
     *  => return null
     *  head => null
     *      *  => return null
     * M
     * - temp node to iterate over + visited hashset to track
     * visited nodes
     * - two pointers, curr and prev + visited set
     *
     * P
     * - Initialize a visited set and a prev and curr temp node
     * - iterate over the list
     * - update prev to curr & add curr to the visited set
     * - => return null if curr is null
     * - otherwise keep iterating over the ll until
     * curr visits a node in set and then return prev
     * IRE
     *
     * */
    public static ListNode linkedListCycleII(ListNode head){
        Set<ListNode> visited = new HashSet<>();
        ListNode curr = head;
        ListNode prev = null;
        while(curr != null){
            if(visited.contains(curr)){
                return prev;
            }
            visited.add(curr);
            prev = curr;
            curr = curr.next;
        }
        return null;
    }
}
