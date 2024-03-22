import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class kthBiggestPairSum {

    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        int[] nums = new int[]{1, 2, 3, 5};

        int result = kthBiggestPairSum(nums, 3);
        System.out.println(result);
        result = kthBiggestPairSum(list, 3);
        System.out.println(result);

        List<Integer> list2 = new ArrayList<>();
        list2.add(4);
        list2.add(2);
        list2.add(5);
        list2.add(5);
        nums = new int[]{4, 2, 5,5};
        // 5 5 4 2
        // 10 9 7 -> return 7
        System.out.println(kthBiggestPairSum(nums, 3));

        nums = new int[]{4, 2, 5,5};
        System.out.println(kthBiggestPairSum(nums, 10));

        nums = new int[]{4, 8};
        System.out.println(kthBiggestPairSum(nums, 1));
    }

    // given an int list find the kth biggest pair sum
    // find the sums of pairs in the list and return the pair
    // with the max sum
    // PLAN
    // sort the given list in descending order
    // add the 0 index with the k-1 index and return as that will be the kth biggest pair sum
    public static int kthBiggestPairSum(List<Integer> nums, int k){
        int length = nums.size() -1;
        if(k > length || k < 0 || length < 1) return -1;

        Collections.sort(nums, (a, b) -> b - a);
        return nums.get(0) + nums.get(k);
    }
    public static int kthBiggestPairSum(int[] nums, int k){
        int length = nums.length -1;
        if(k > length || k < 0 || length < 1) return -1;

        Arrays.sort(nums);
        return nums[length] + nums[length - k];
    }
}
