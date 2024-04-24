package leetcode150;

public class ContainerWithMostWater {
    public static void main(String[] args){
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        int res = maxArea(height);
        System.out.println(res);

        height = new int[]{1, 1};
        res = maxArea(height);
        System.out.println(res);
    }


    /**
     * Given an array of heights - there are n heights
     * that make up heights of different containers
     * find the container with the greatest area
     *
     * PLAN
     * declare a l and r ptr
     * declare a maxArea
     * while l < r
     * calculate area between l and r
     * - area should be (r - l + 1) * min(l, r)
     *  - this reps w * h but width + 1 because it is 0-indexed
     *
     * compare the area with the maxArea and update as needed
     * move the l/r ptr if the current ptr is < the other ptr
     * 1. move l ptr + 1 index if it is < r ptr
     * 2. move r ptr - 1 index if it is <= l ptr
     *
     * return the max area
     * */
    public static int maxArea(int[] height){
        int l = 0;
        int r = height.length - 1;
        int maxArea = 0;
        while(l < r){
            int area = (r - l) * Math.min(height[r], height[l]);
            maxArea = Math.max(maxArea, area);
            if(height[r] >= height[l]){
                l++;
            } else {
                r--;
            }
        }
        return maxArea;
    }
}
