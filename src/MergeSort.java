import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {

        int[] test1 = new int[0];
        int[] test2 = new int[]{3, 9, 2, 1};
        int[] test3 = new int[]{0, 1, 2, 3, 4};
        int[] test4 = new int[]{3, 1, 4, 1, 5, 9, 2, 6, 5};
        int[] test5 = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        printMergeSortTestcase(test1);
        printMergeSortTestcase(test2);
        printMergeSortTestcase(test3);
        printMergeSortTestcase(test4);
        printMergeSortTestcase(test5);
    }

    /**
     * THIS METHOD PRINTS OUT THE TEST INPUT ARRAY AND
     * CALLS MERGESORT ON TEST ARRAY TO PRINT OUT
     * A SORTED ARRAY
     *
     * INPUT: INTEGER ARRAY
     * OUTPUT: PRINTED VALUES OF TEST BEFORE AND AFTER
     * MERGESORT
     * */
    private static void printMergeSortTestcase(int[] test){
        System.out.println("<--- Testcase --->");
        System.out.print("Input: ");
        for(int i : test){
            System.out.print(i + " ");
        }
        System.out.println("");
        mergeSort(test, 0, test.length - 1);
        System.out.print("Result: ");
        for(int i : test){
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    /**
     * THIS METHOD IS THE IMPLEMENTATION OF MERGE SORT
     * THAT SPLITS THE GIVEN ARRAY LOG N TIMES UNTIL
     * THE ARRAY IS SORTED
     *
     * INPUT: TEST ARRAY AND STARTING INDEX OF ARRAY AND END INDEX
     * OF ARRAY SEGMENTS
     * OUTPUT: SORTS ARRAY IN PLACE MEANING IT SORTS THE ORIGINAL ARRAY
     * */
    public static void mergeSort(int[] nums, int start, int end) {
        if(start < end) {
            int mid = (start + end) / 2;
            mergeSort(nums, start, mid);
            mergeSort(nums, mid + 1, end);
            merge(nums, start, mid, end);
        }
    }

    /**
     * THIS METHOD MERGES TWO ARRAYS INTO 1 SORTED ARRAY
     *
     * INPUT INTEGER ARRAY AND START AND MID POINTERS TO
     * CALCULATE SIDE OF THE TWO INTEGER ARRAY
     * OUTPUT: UPDATES THE ARRAY IN PLACE WITH
     * VALUES SORTED
     * */
    private static void merge(int[] nums, int start, int mid, int end) {
        int[] left = Arrays.copyOfRange(nums, start, mid + 1);
        int[] right = Arrays.copyOfRange(nums, mid + 1, end + 1);

        int lptr = 0, rptr = 0, i = start;
        while(lptr < left.length && rptr < right.length) {
            if(left[lptr] < right[rptr]){
                nums[i++] = left[lptr++];
            } else {
                nums[i++] = right[rptr++];
            }
        }

        if(lptr < left.length) {
            while(lptr < left.length){
                nums[i++] = left[lptr++];
            }
        } else if (rptr < right.length){
            while(rptr < right.length){
                nums[i++] = right[rptr++];
            }
        }
    }
}
