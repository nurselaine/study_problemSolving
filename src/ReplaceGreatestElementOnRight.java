public class ReplaceGreatestElementOnRight {

    public static void main(String[] args){
        int[] arr = new int[]{17, 18, 5, 4, 6, 1};
        replaceElements(arr);
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println("");

        arr = new int[0];
        replaceElements(arr);
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println("");

        arr = new int[]{1};
        replaceElements(arr);
        for(int i : arr){
            System.out.print(i + " ");
        }
    }

    // given an array, replace every element with the greatest element among the
    // elements to its right
    // Replace the last element with -1
    // PLAN
    // declare a max element value
    // iterate over nums from right to left
    // replace each element with the max element value
    // & update max element with the preceding value if it is
    // greater than the current max
    // return the array
    public static int[] replaceElements(int[] nums){
        int max = -1;

        for(int i = nums.length - 1; i >= 0; i--){
            int curr = nums[i];
            nums[i] = max;
            max = Math.max(max, curr);
        }
        return nums;
    }
}
