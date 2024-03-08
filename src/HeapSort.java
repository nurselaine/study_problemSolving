public class HeapSort {
    public static void main(String[] args){
        int[] test1 = new int[]{3, 2, 4, 1, 5};
        int[] test2 = new int[]{0, 2, 6, 3, 2, 1, 3, 6, 7};
        int[] test3 = new int[]{0, 1, 2};
        int[] test4 = new int[]{1};

        System.out.print("Testing odd numbered array: ");
        testHeapSort(test1);

        System.out.print("Testing array with duplicate values: ");
        testHeapSort(test2);

        System.out.print("Testing 3 values: ");
        testHeapSort(test3);

        System.out.print("Testing 1 value: ");
        testHeapSort(test4);

    }

    public static void testHeapSort(int[] arr){
        for(int n : arr){
            System.out.print(n + " ");
        }
        System.out.print("\nResult: ");
        heapSort(arr);
        for(int n : arr){
            System.out.print(n + " ");
        }
        System.out.println("");
    }

    // Heap Sort (minimum heap)
    // Find the mid point of the arr
    // check starting at midpoint whether right and left child are smaller than parent
    // if 1 child is smaller than parent then swap parent and smaller child
    // if both child are smaller than parent then choose lesser value child
    // once parent is smallest value between 2 children then decrememnt mid
    // continue this step until mid reaches 0 of the array
    // this index should now be the smallest element in the collection and the array
    // should be sorted in heap order
    // min parent < child node
    public static void heapSort(int[] arr){

        int SIZE = arr.length;
        int currSize = SIZE - 1;

        // step 1: heapify arr
        for(int i = SIZE / 2 - 1; i >= 0; i--){
            heapify(arr, arr.length,i);
        }

        for(int i = SIZE - 1; i >= 0; i--){

            // step 2: swap first and last element
            swap(arr, 0, i);

            // step 3: heapify array minus 1 element
            heapify(arr, currSize, 0);
            currSize--;
        }

    }

    private static void heapify(int[] arr, int SIZE, int parent){

        int largest = parent ;
        int leftChild = getLeftChild(largest);
        int rightChild = getRightChild(largest);

        if(leftChild < SIZE && arr[leftChild] > arr[largest]){
            largest = leftChild;
        }

        if(rightChild < SIZE && arr[rightChild] > arr[largest]){
            largest = rightChild;
        }

        if(largest != parent){
            swap(arr, largest, parent);
            heapify(arr, SIZE, largest);
        }
    }


    private static void swap(int[] arr, int parent, int child){
        int temp = arr[parent];
        arr[parent] = arr[child];
        arr[child] = temp;
    }

    private static int getParent(int index){
        return (index - 1) / 2;
    }

    private static int getLeftChild(int index){
        return index * 2 + 1;
    }

    private static int getRightChild(int index){
        return index * 2 + 2;
    }
}
