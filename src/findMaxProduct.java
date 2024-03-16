import java.util.ArrayList;
import java.util.List;

public class findMaxProduct {

    public static long maxProduct = 0;
    public static int[] result;

    public static void main(String[] args){
//        int[] test1 = new int[]{1, 2, -1, 5, 6};
//        int[] res = findMaxProduct(test1, 2);
//        for(int i = 0; i < res.length; i++){
//            System.out.print(res[i] + " ");
//        }
//
//        System.out.println("");
//
//        int[] test2 = new int[]{1, 2, -1, 5, 0};
//        int[] res2 = findMaxProduct(test2, 1);
//        for(int i = 0; i < res2.length; i++){
//            System.out.print(res2[i] + " ");
//        }

//        int[] test3 = new int[]{5, 2, 1, 0, -2, -5};
//        int[] res3 = findMaxProduct(test3, 4);
//        for(int i = 0; i < res3.length; i++){
//            System.out.print(res3[i] + " ");
//        }

        int[] test4 = new int[]{2000, 1500, 1200, 1, 0, -2, -5, -10, -100, -1000};
        int[] res4 = findMaxProduct(test4, 3);
        for(int i = 0; i < res4.length; i++){
            System.out.print(res4[i] + " ");
        }
    }

    // Given an integer list nums and a number k,
    // find k integers which give the max product.
    // If there are multiple answers possible, return any of them.
    //
    //numbers can be negative (need to give nlogn approach or better than that)
    // nums = [1, 2, -1, 5, 0], k = 2, ans = [2, 5]
    // nums = [1, 2, -1, 5, 0], k = 1, ans = [5]
    // nums = [5, 2, 1, 0, -2, -5], k = 4, ans = [5, 2, -2, -5]
    // nums = [2000, 1500, 1200, 1, 0, -2, -5, -10, -100, -1000], k = 3, ans [2000, 1500, 1200]
    // plan
    // use helper fn to find all possible combinations of k where each combo's product is >= the last
    // combination
    public static int[] findMaxProduct(int[] nums, int k){
        result = new int[k];
        List<Integer> list = new ArrayList<>();
        findConbo(nums, k, 0, 1, list);
        return result;
    }

    private static void findConbo(int[] nums, int k, int index, long product, List<Integer> vals){
        if (product > maxProduct && vals.size() == k){
            maxProduct = product;
            result = vals.stream().mapToInt(Integer::intValue).toArray();
            return;
        } else if(index >= nums.length || vals.size() >= k){
            return;
        }

        vals.add(nums[index]);
        findConbo(nums, k,index + 1, product * nums[index], vals);

        vals.remove(vals.size() - 1);
        findConbo(nums, k, index + 1, product, vals);
    }

    // plan
    // Create a max heap of pairs where key: integer val: absolute value of integer
    // - sort heap by the absolute value
    // keep max product va`l
    // pop off k values from heap and count negative values
    // if negative value is popped off
    // check if next val is negative
    // if value is positive and total neg count is > 1
    // while next val is pos, get positive pair (if possible)
    // when next neg value is popped, compare negative product with pos pair product
}
