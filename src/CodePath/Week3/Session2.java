package CodePath.Week3;

public class Session2 {
    public static void main(String[] args){
        testSqrt();
    }

    public static void testSqrt(){
        int res = mySqrt(121);
        System.out.println(res);
    }

    public static void testSearchInRotatedArr(){
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int res = searchInRotatedArr(nums, 4);
        System.out.println(res);
    }

    public static void testMinInRotatedArr(){
        int[] nums = new int[]{3, 1, 2};
        int min = findMinInRotatedArr(nums);
        System.out.println(min);
    }

    public static void testFirstBadVersion(){
        int result = firstBadVersion(1);
        System.out.println(result);
    }

    public static void testBinarySearch(){
        int[] nums = new int[]{9, 9};
        int res = binarySearch(nums, 9);
        System.out.println(res);
        res = binarySearch(nums, 2);
        System.out.println(res);
    }

    /**
     * Given an array of integers nums which is sorted in
     * ascending order, and an integer target, write a
     * function to search target in nums. If target exists,
     * then return its index. Otherwise, return -1.
     *
     * You must write an algorithm with O(log n) runtime
     * complexity.
     * questions
     * - Can there be null values?
     * - is target included in the nums array? => -1 if not included
     * - can array be empty?
     * - How large can array be?
     * - Any space constraints?
     * - Is index 0-indexed?
     *
     * example
     * - 1 2 2 3 4 tar = 4 => 4
     * - 1 1 2 3 tar = 1 => 0
     * - 3 4 6 6 tar = 2 => -1
     * - [] => -1
     *
     * methods
     * - binary search
     * - iterate over
     *
     * Plan
     * - initialize 2 pointers left and right
     * - while left < right ptr then calculate the mid
     * mid is the avg of left and right
     * - check if the element at index mid is == target
     * - if element at mid is < target then update
     * left ptr to mid
     * - if element at mid is > taret then update right ptr to mid -1
     * - if element at mid == target then return mid
     * - when left > right ptr then return -1
     *
     * Review
     * - update left pointer when mid value is less than the
     * target value
     * - update right pointer when mid value is greater than
     * Evaluate
     * time: O(logN)
     * space: O(1)
     */
    public static int binarySearch(int[] nums, int target){
        int l = 0;
        int r = nums.length - 1;
        while(l < r){
            // -1 0 3 5 9 12
            // l 0 r 5
            int mid = (l + r) / 2; // 2
            if(nums[mid] == target){
                return mid;
            } else if (nums[mid] < target){
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return -1;
    }

    /**
     * You are a product manager and currently leading a team to
     * develop a new product. Unfortunately, the latest version of your
     * product fails the quality check. Since each version is developed
     * based on the previous version, all the versions after a bad version
     * are also bad.
     *
     * Suppose you have n versions [1, 2, ..., n] and you want to find out
     * the first bad one, which causes all the following ones to be bad.
     *
     * You are given an API bool isBadVersion(version) which returns whether
     * version is bad. Implement a function to find the first bad version.
     * You should minimize the number of calls to the API.
     *
     * questions
     * - will there always be a bad version?
     * - are there any time/space limitations?
     * - can the first version be bad?
     * - how many versions max can there be?
     * - what should I return
     *
     * example
     * - 1g, 2g, 3g, 4b, 5b, 6b, 7b => 4
     * - 1b => 1
     *
     * Methods
     * - use iteration through all versions
     * - use 2 pointers to move to the right until bad version found
     *
     * Plan
     * - initialize a left and right ptr
     * - while left < right then calculate a mid pointer
     * - call isBadVersion and pass in mid value
     * - if returns false AND isBadVersion with mid -1 is true
     *  => return mid
     * -if returns true then move left pointer to mid + 1
     * - if returns fast and isBadVersion with mid - 1 is true
     * => update right pointer to mid - 1
     *
     * continue until left > right then return -1
     *
     * review:
     * - check whether the mid point is a bad version, if it is then
     * update right ptr to mid
     * otherwise if it is a good version then move left pointer up
     * to mid + 1
     * when left pointer meets right pointer => we have found the
     * first bad version so we break out of the
     *
     * eval:
     * time: O(logN)
     * space: O(1)
     * */
    public static int firstBadVersion(int n){
        int left = 0;
        int right = n;
        // 1 2 3 4 5
        while(left < right){
            int mid = (left + (right - left)) / 2;
            // this method prevents int overflow value
            // when (left + right) are both large int that can sum to greater than 2^32 digits
//            int mid = (right + left) / 2;
            if(!isBadVersion(mid)){
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
    private static int version = 1;
    private static boolean isBadVersion(int n){
        return n < version ? true : false;
    }

    /**
     * given an integer array that is rotated n indices,
     * find the min value
     * - want to find a pivot point and where the arr
     * is rotated
     *
     * will always have a max and min value
     * will always be increasing unless reset
     *
     * check if mid is greater than or less
     * than right pointer
     *
     * if r < m then min is on right of middle => inflection point
     *
     * methods
     * - iterate
     * - binary search
     *
     * Plan
     * - implement binary search
     * - find inflection point of rotation by checking
     * if right < midpoint
     * => move left to mid + 1
     * if right > midpoint
     * => move right to mid
     *
     * return left element
     *
     * eval
     * time: O(LogN)
     * space: O(1)
     *
     * */
    public static int findMinInRotatedArr(int[] nums){
        int left = 0;
        int right = nums.length - 1;

        while(left < right){
            int mid = left + ((right - left) / 2);
            if(nums[right] < nums[mid]){
                left = mid + 1;
            } else if (nums[right] > nums[mid]){
                right = mid;
            }
        }
        return nums[left];
    }

    /**
     * Search in a rotated sorted array, given a target int
     *
     * will always be in ascending order
     * target may not exist in array
     * will always be a min and max
     *
     * questions
     * - time/space constraints
     *
     * examples
     * - [4, 5, 6, 7, 0, 1, 2] => target = 6
     *   l         m        r
     * - [2 3 4 5 6 7 0 1] => target = 6
     *    l     m       r
     *
     * methods
     * - sorting: exceeds time constraint
     * - 2 pointers: can be useful with binary search
     * - hashmap: complicates this problem
     * - sliding window: not needed and hard to use in a rotated array
     * - binary search: helps to find inflection point of a rotated array
     * and also find a target values
     *
     * plan
     * - declare a help function to find the inflection point the rotated arry
     *  - create 2 ptrs and compare the r ptr with mid point
     *  - return the inflection point
     * - declare a helper function to find the target value using the
     * inflection point as a starting and end point
     * - in the main function, use the findTargetVal helper function and call
     * it twice to determine whether target is in the 2 partitions of the nums array
     * from 0 - inflection point and inflection point to nums.length
     * return the index of target or -1
     * */
    public static int searchInRotatedArr(int[] nums, int target){
        int inflection = findInflection(nums);
        System.out.println(inflection);

        return findTargetIndex(nums, 0, inflection, target) +
                findTargetIndex(nums, inflection, nums.length - 1, target) + 1;
    }

    private static int findInflection(int[] nums){
        int l = 0;
        int r = nums.length - 1;
        while(l < r){
            int mid = l + (r - l)/ 2;
            if(nums[r] < nums[mid]){
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private static int findTargetIndex(int[] nums, int start, int end, int target){
        while(start <= end){
            int mid = start + (end - start) / 2;
            if (nums[mid] > target){
                end = mid - 1;
            } else if(nums[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * given a non-negative integer x, return the square
     * root of x rounded down to the nearest integer.
     * The returned integer should be non-negative as well.
     *
     * You must not use any built-in exponent function or operator.
     *
     * questions
     * - time constraint?
     *
     * square root of 1 is 1
     * the square root of a number n is always < n / 2
     * all values are positive numbers
     * returning a whole number
     *
     * examples
     * check the r * r value to see if need to pivot
     * to left side
     *
     * methods
     * - two pointers: not useful in thisscenario, not given an arr
     * - binary search, can search between 1 to n /2 for a value that is
     * close or equal to sqrt of n
     * - hashmap : not useful to store values of square roots
     * - sliding window: not useful in this situation
     *
     * plan
     * if n = 0 or 1 then return n
     * - initialize 2 ptrs l and r
     * where r = n /2
     * - while l < r
     * - calculate a midpoint
     * - check if midpoint * 2 >= n
     * => return
     * */
    public static int mySqrt(int x){
        int l = 0;
        int r = x;
        int sqrt = 0;
        if(x == 0) return 0;

        while(l <= r){
            int mid = l + (r - l) / 2;
            if(x /mid == mid){
                return mid;
            } else if (x / mid < mid){
                r = mid - 1;
            } else {
                l = mid + 1;
                sqrt = mid;
            }
        }
        return sqrt;
    }
}
