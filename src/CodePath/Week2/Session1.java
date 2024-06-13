package CodePath.Week2;

import Node.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Session1 {
    public static void main(String[] args){
        testRemoveDuplicates();
    }

    public static void testRemoveDuplicates(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(8);
        ListNode node5 = new ListNode(2);
        node1.next = node2;
        node2.next = node5;
        node5.next = node3;
        node3.next = node4;
        printLL(node1);
        ListNode newList = removeDuplicates(node1);
        printLL(newList);

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
     * I
     * R
     * - iterate over list nodes and add each visited node to a set
     * - use two pointers a curr and prev and as soon as curr node
     * is found in the set, return prev node - otherwise return null
     * E
     * time: O(N)
     * space: O(N)
     * */
    public static ListNode linkedListCycleII(ListNode head){
        Set<ListNode> visited = new HashSet<>();
        ListNode curr = head;
        ListNode prev = null;
        while(curr != null){
            if(visited.contains(curr)){
                return curr;
            }
            visited.add(curr);
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    /**
     * Solve Linked List cycle II in constant space
     *
     * U
     * find the point where a cycle begins in a LL
     *
     * M
     * - two pointers and temp node to explore the ll
     *
     * P
     * - assign a fast and slow pointer, find where the fast and slow
     * pointer meet in the ll (detect a cycle)
     * - return null of fast pointer is null or points to null (no cycle)
     * - using the head pointer given, iterate the same speed with slow pointer
     * which will give the point where the cycle begins when head and slow pointer
     * meet
     * IRE
     * */
    public static ListNode linkedListCycleIIv2(ListNode head){
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null || fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast){
                break;
            }
        }
        if(fast == null || fast.next == null){
            return null;
        }
        while(head != slow){
            head = head.next;
            slow = slow.next;
        }
        return head;
    }


    /**
     * U
     * Remove a node from a ll given the node and the ll
     *
     * questions
     * - will the provided node always be in the ll?
     * - How large/small is the ll
     * - any time/space constraints?
     * - can there be multiple of a given node? yes
     *
     * example
     * head -> 1 -> 2 -> 3 -> null; node = 2
     *  => head -> 1 -> 3 -> null
     *          1         2
     *  head -> 1 -> 2 -> 3 -> 2 -> null; node = 2
     *      *  => head -> 1 -> 3 -> null
     *  head -> 1 -> 1 -> 1 -> null; node = 1
     *      => head -> null
     *  head -> null => null
     * M
     * - multiple passes
     * - temp node
     * - two pointers
     *
     * P
     * - instantiate 2 pointers, p1 & p2
     * - iterate over the list until p2 reaches the end
     * - while iterating over the list
     * - check while p2 == give and p1 == p2 node => move p2
     * - update p1.next to p2
     * - reassign p1 to p2
     * IRE
     *
     * */
    public static ListNode removeDuplicates(ListNode head){
        ListNode p1 = head;
        ListNode p2 = head;
        while(p2 != null){
            while(p2 != null && p1.val == p2.val){
                p2 = p2.next;
            }
            p1.next = p2;
            p1 = p2;
        }
        return head;
    }

    /**
     * Design a Linked List either singly or doubly
     * Node: val & next attributes
     *
     * Class name is MyLinkedList
     *
     * get(int index)
     * void addAtHead(int val)
     * void addAtTail(int val)
     * void addAtIndex(int index, int val)
     * void deleteAtIndex(int index)
     *
     * - Are there any space/time constraints?
     * - what sort of elements should the list take => int
     * - how large can the linked list class be able to handle?
     * - will the provided index be 0-indexed?
     *
     * Plan
     * initialize a size value
     * create a head pointer
     * create a tail pointer
     *
     * constructor
     * - initialize have parameters for val (int), next (ptr), & prev (ptr)
     * - initialize parameters for just val, next
     * - initialize parameters for val only where next and prev are null
     *
     * int get(int index)
     * - check if index is < size of LL => return -1
     * - create a counter value & a temp node for iterating
     * - if index is < size /2 then iterate from the front of the list
     * - stop when counter is index - 1 value and return the current node
     * - else when index > size / 2 iterate from the end of the list
     * - stop when counter is size - counter == index & return the curr node value
     *
     * void addAtHead(int val)
     * - create a new ListNode with val that points to the current head
     * - update head to new list node
     *
     * void addAtTail(int val)
     * - create a new ListNode with val that pointer prev pointer to tail pointer
     * - update tail to point to new list node
     *
     * void addAtIndex(int index, int val)
     * - initialize a counter
     * - create a new node with val & temp node for iterating
     * - check if index < size / 2
     * - iterate from head and increment counter each step
     * - when counter == index - 1
     *  => update new node to pointer to temp.next and curr.next to pointer to new node
     * - else if index >= size / 2
     * - iterate from the tail and increment counter
     * - when size - counter == index
     *  => update new node prev point to curr.prev and new node next to curr and curr
     * */
}
