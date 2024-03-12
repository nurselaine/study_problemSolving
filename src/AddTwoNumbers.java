public class AddTwoNumbers {
    public static void main(String[] args){
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(9)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(9))));
        ListNode res = addTwoNumbers(l1, l2);
        while(res != null){
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode result = new ListNode(-1);
        ListNode curr = result;
        helperAdd(l1, l2, curr, 0);
        return result.next;
    }

    private static void helperAdd(ListNode l1, ListNode l2, ListNode result, int carry){
        if(l1 == null && l2 == null){
            if(carry > 0){
                result.next = new ListNode(carry);
            }
            return;
        } else if (l1 == null & l2 != null){
            result.next = new ListNode((l2.val + carry) % 10);
            helperAdd(l1, l2.next, result.next, (l2.val + carry) / 10);
        } else if (l2 == null & l1 != null) {
            result.next = new ListNode((l1.val + carry) % 10);
            helperAdd(l1.next, l2, result.next, (l1.val + carry) / 10);
        } else {
            result.next = new ListNode((l1.val + l2.val + carry) % 10);
            helperAdd(l1.next, l2.next, result.next, (l1.val + l2.val + carry) / 10);
        }
    }

    public static class ListNode {
        public int val;
        ListNode next;
        ListNode() {};

        ListNode(int val) { this.val = val; }

        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
