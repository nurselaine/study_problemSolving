package leetcode150;

public class GasStation {

    public static void main(String[] args){
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
//        int result = canCompleteCircuit(gas, cost);
        int result = greedyCanCompleteCircuit(gas, cost);
        System.out.println(result);

        gas = new int[]{2, 3, 4};
        cost = new int[]{3, 4, 3};
//        result = canCompleteCircuit(gas, cost);
        result = greedyCanCompleteCircuit(gas, cost);
        System.out.println(result);

        // 2 3 4
        // 3 4 3

        // 2
        // 2 - 3 = -1

        // 3
        // 3 - 4 = -1

        // 4
        // 4 - 3 = 1
        // 1 - 3 = -2
        //
    }

    /**
     * Given N elements for both gas and cost arrays
     * find the starting index where gas[i] - cost[i]
     * never equals 0 going from i all the way back to i
     *
     * gas: 1 2 3 4 5
     * cost: 3 4 5 1 2
     *
     * start = 0
     * gas = 0
     * cost = 0
     *
     * gas - cost
     * starting index 0:
     * gas = 1 - 3 = -2
     * cost = 3
     * Starting index 1:
     * gas = 2 - 4 = -2
     * cost = 4
     * Starting index 2:
     * gas = 3 - 5 = -2
     * cost = 5
     * Starting index 3:
     *
     * PLAN
     * declare a totalGas and Nstops counter
     * iterate over N
     * declare a total stops value
     * while:
     * 1. total stops < N
     * 2. total gas is > 0
     * then continue to loop
     *
     * if totalStops == N then return i
     *
     * if all elements have been checked and do not meet conditions then return -1
     * */
    public static int canCompleteCircuit(int[] gas, int[] cost){
        int N = gas.length;

        for(int i = 0; i < N; i++){ // 2

            int totalGas = gas[i]; // 3
            int totalStops = 0;
            while(totalStops < N && totalGas > 0){  //
                int index = (i + totalStops) % N; // 1
                int nextIndex = (i + 1 + totalStops) % N; // 2
                totalGas -= cost[index];
                if(totalGas >= 0){
                    totalGas += gas[nextIndex];
                    totalStops++;
                } else {
                    break;
                }
            }

            if(totalGas > 0 && totalStops >= N) return i;
        }

        return -1;
    }

    /**
     * Optimized - greedy solution
     *
     * Use an array gain to caluclate net change between gas[i] - cost[i]
     *
     * at each elemetn i, the amont of gas is changed by gain[i]
     * if gas total drops below 0 then the next element cannot be reached
     *
     * Iterate over N elements
     * calculate net gain for each element in a total variable
     * if the total variable is >= 0 then return the last starting index - 1
     * where the net gain was > 0 otherwise return -1
     * */

    // 1 2 3 4 5
    // 4 5 1 2 3

    // -3 -3 2 2 2

    // 2 3 4
    // 3 4 3

    // -1 -1 1
    /**
     * Plan
     * declare a total and current total accumulator
     *
     * check if the currentTotal falls below 0 - this means that
     * the
     * */
    public static int greedyCanCompleteCircuit(int[] gas, int[] cost){
        int total = 0;
        int start = 0;
        int currTotal = 0;

        for(int i = 0; i < gas.length; i++){
            currTotal += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if(currTotal < 0){
                start = i + 1;
                currTotal = 0;
            }
        }

        return total >= 0 ? start : -1;
    }
}
