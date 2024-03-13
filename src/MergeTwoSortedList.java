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

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) {
            return list2;
        } else if (list2 == null){
            return list1;
        } else if (list1.val <= list2.val){
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
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
