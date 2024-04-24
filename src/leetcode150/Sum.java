package leetcode150;

public class Sum {

    public static void main(String[] args){
        int[] nums = new int[]{2, 7, 11, 15};
        int[] res = twoSum(nums, 9);
        for(int n : res){
            System.out.print(n + " ");
        }
        System.out.println("");

        nums = new int[]{2, 3, 4};
        res = twoSum(nums, 6);
        for(int n : res){
            System.out.print(n + " ");
        }
        System.out.println("");

        nums = new int[]{5, 25, 75};
        res = twoSum(nums, 100);
        for(int n : res){
            System.out.print(n + " ");
        }

        System.out.println("");

        nums = new int[]{3,24,50,79,88,150,345};
        res = twoSum(nums, 200);
        for(int n : res){
            System.out.print(n + " ");
        }
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

}
