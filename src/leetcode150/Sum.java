package leetcode150;

import java.util.*;

public class Sum {

    public static void main(String[] args){
//        int[] nums = new int[]{2, 7, 11, 15};
//        int[] res = twoSum(nums, 9);
//        for(int n : res){
//            System.out.print(n + " ");
//        }
//        System.out.println("");
//
//        nums = new int[]{2, 3, 4};
//        res = twoSum(nums, 6);
//        for(int n : res){
//            System.out.print(n + " ");
//        }
//        System.out.println("");
//
//        nums = new int[]{5, 25, 75};
//        res = twoSum(nums, 100);
//        for(int n : res){
//            System.out.print(n + " ");
//        }
//
//        System.out.println("");
//
//        nums = new int[]{3,24,50,79,88,150,345};
//        res = twoSum(nums, 200);
//        for(int n : res){
//            System.out.print(n + " ");
//        }

        // testing 3sum
//        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
//        List<List<Integer>> res = threeSum(nums);
//        for(List<Integer> l : res){
//            for(int n : l){
//                System.out.print(n + " ");
//            }
//            System.out.println();
//        }

        // testing minimum size subarray sum
        int[] nums = new int[]{2, 3, 1,2, 4, 3};
        int target = 7;
        int res = minSubArrayLen(target, nums);
        System.out.println("result :" + res);

        nums = new int[]{1, 4, 4};
        target = 4;
        res = minSubArrayLen(target, nums);
        System.out.println(res);

        nums = new int[]{1,1,1,1,1,1,1,1};
        target = 11;
        res = minSubArrayLen(target, nums);
        System.out.println(res);
    }

    /**
     * Given a sorted array, find two numbers
     * that add update to the target number
     *
     * PLAN
     * declare a l and r ptr
     * while l < r
     * calc mid = l + r / 2
     * check if l + r == target
     * if l + r > target
     *  update r to mid - 1
     * if l + r < target
     *  update l to mid + 1
     * otherwise
     *  return [l, r]
     * */
    public static int[] twoSum(int[] nums, int target){
        int l = 0;
        int r = nums.length - 1; // 2
        // 3,24,50,79,88,150,345
        while(l < r){
            if(nums[l] + nums[r] == target){
                return new int[]{l + 1, r + 1};
            } else if (nums[l] + nums[r] > target){
                r--; // 3
            } else {
                l++; // 1
            }
        }
        return new int[0];
    }

    /**
     * Given an int array return ALL combinations (size=3)
     * such that the sum of 3 elements (all unique) equal to 0
     *
     * PLAN
     * Initialize a set to hold unqiue lists (combos)
     * Initialize a list to return
     * iterate over nums
     * for each nums[i]: call helper
     *
     * Helper fn
     * input: nums and nums[i]
     * initialize 2 ptrs (l and r)
     * l starts at nums[i + 1]
     * r starts at nums[nums.length - 1]
     * while l and r do not overlap
     * check if:
     * sum = nums[l] + nums[r] + nums[i] == 0
     * if so then create list with values and check it set
     * contains list
     * - if not then add to result
     * - otherwise move on
     *
     * check if: sum > 0
     * then if nums[l] > nums[r]
     * increment l
     * otherwise decrement r
     * sum
     * */
    public static List<List<Integer>> threeSum(int[] nums){
        Set<List<Integer>> set = new HashSet<>();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++){
            if(i == 0 || nums[i] != nums[i - 1]){
                helperThreeSum(nums, set, res, i);
            }
        }
        return res;
    }

    private static void helperThreeSum(int[] nums, Set<List<Integer>> set, List<List<Integer>> res, int n){
        int l = n + 1;
        int r = nums.length - 1;

        while(l < r){

            int sum = nums[l] + nums[r] + nums[n];
            if(sum == 0){
                res.add(Arrays.asList(nums[n], nums[l], nums[r]));
                set.add(res.get(res.size() - 1));
                l++;
                while(l < r && nums[l] == nums[l - 1]){
                    l++;
                }
            } else if (sum < 0){
                l++;
            } else {
                r--;
            }
        }
    }

    /**
     * Given an array of positive integers and a target, return
     * the length of a subarray whose sum is >= target
     * if there is no subarray then return 0
     *
     * do not sort the array
     *
     * PLAN
     * initialize a l and r ptr
     * declare running sum and res
     * iterate over nums while l < len of nums
     * if running sum < target
     * - increment r (update running sum)
     * if running sum >= target
     * - update res with min(res, r - l + 1)
     * - increment l and subtract from running sum
     * */
    public static int minSubArrayLen(int target, int[] nums){
        int right = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){

            while(right < nums.length && sum < target){
                sum += nums[right];
                right++;
            }

            if(sum >= target){
                result = Math.min(result, right - i);
            }
            sum -= nums[i];
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
