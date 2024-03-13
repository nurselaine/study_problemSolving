public class MergeTwoSortedList {

    public static void main(String[] args){
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        mergeTwoLists(l1, l2);
        while(l1 != null){
            System.out.print(l1.val + " ");
            l1 = l1.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if(l1 == null){
            return l2;
        } else if (l2 == null){
            return l1;
        } else if (l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode() {}
        public ListNode(int val) { this.val = val; }

        public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
}
