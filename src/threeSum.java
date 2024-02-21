import java.util.ArrayList;
import java.util.List;

public class threeSum {
    public static void main(String[] args){

    }

    // Three sum problem
    // find 3 integers in nums that sum up to 0
    // PLAN
    // Iterate through each integer in nums
    // find the pair of elements that complement above integer
    // use binary search and sort array first
    // check the value of the elements
    // if it is equal to 0 then add elem to array
    // if it is less than 0 then move start ptr up
    // if it is greater than 0 then move end ptr down
    // do this until nums[i] to be check with is >= 0
    // make sure to not add duplicates
    // 1. Do not find complements of a digit that has already been checked - nums[i] == nums[i + 1]
    public static List<List<Integer>> threeSum(int[] nums){
    }

    private static void threeSumHelper(){

    }

    // find all subsets of a list
    // when finding all subsets, we need to check the possible subsets with
    // element A included in the subset and element A not included in the subset
    // the recursive steps to find the subsets are
    // track omitted/included element using an index
    // if the index is greater than the collection size then we know we've checked all possible combinations
    // of the given collection and we can add the current subset to the resulting array
    // otherwise continue to check subsets
    // 1. add the element at the index to the subset
    // call the subset recursively so it finds all combinations with that element added
    // 2. remove the element at the index to the subset
    // call the subset recursively so it finds all combinations without that element added
    // each time one of these calls completes, it will stop at the base case and the current subset will be
    // added to the results array
    public static List<List<Integer>> subsets(int[] nums){
        // create returning array
        List<List<Integer>> result = new ArrayList<>();
        // create a starting subset to pass into method
        List<Integer> subset = new ArrayList<>();

        // call helper method to add combinations of nums to result
        subsetsHelper(nums, result, subset, 0);

        return result;

    }

    private static void subsetsHelper(int[] nums, List<List<Integer>> result, List<Integer> subset, int index){
        if(index >= nums.length){
            result.add(new ArrayList<>(subset));
            return;
        }

        // include element at index in subset
        subset.add(nums[index]);
        subsetsHelper(nums, result, subset, index + 1);
        // exclude element at index in subset
        subset.remove(subset.size() - 1);
        subsetsHelper(nums, result, subset, index + 1);
    }

}
