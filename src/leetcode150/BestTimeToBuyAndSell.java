package leetcode150;

public class BestTimeToBuyAndSell {

    public static void main(String[] args){
        int[] prices = new int[]{7,1,5,3,6,4};
//        int result = bestTimeToBuyAndSell(prices);
//        System.out.println(result);
//
//        int[] prices2 = new int[]{7,6,4,3,1};
//        result = bestTimeToBuyAndSell(prices2);
//        System.out.println(result);

//        int result = bestTimeToBuyAndSellII(prices);
//        System.out.println(result);

        int[] prices2 = new int[]{1, 2, 3, 4, 5};
        int result = bestTimeToBuyAndSellII(prices2);
        System.out.println(result);
    }

    /**
     * Given array of prices, find the max profit by choosing
     * a single day to buy and chossing another day to sell
     *
     * Max profit should be the max smallest index and the
     * min largest index
     *
     * PLAN
     * Declare a maxProfit and currProfit result
     * Declare a min ptr
     * iterate over prices
     * check if i > min
     * - calculate currProfit and update MaxProfit if it is >
     * if max < min then update min to i and continue
     * */
    public static int bestTimeToBuyAndSell(int[] prices){
        int maxProfit = 0;
        int buy = 0;
        for(int i = 0; i < prices.length; i++){
            if(prices[i] > prices[buy]){
                maxProfit = Math.max(maxProfit, (prices[i] - prices[buy]));
            } else {
                buy = i;
            }
        }
        return maxProfit;
    }

    /**
     * given prices, buy and sell 1 or more times to find the max profit
     *
     * PLAN
     * declare a maxProfit value and a buy ptr
     * Iterate over all prices
     * check if
     * 1. i != N AND i > buy value AND i + 1 < i then
     * - add i - buy value to maxProfit and update buy to i + 1
     * */
    public static int bestTimeToBuyAndSellII(int[] prices){
        int maxProfit = 0;
        int buy = 0;

        for(int i = 1; i < prices.length - 1; i++){
            if(prices[i - 1] > prices[i]){
                maxProfit += prices[i - 1] - prices[buy];
                buy = i;
            }
        }
        return maxProfit + prices[prices.length - 1] - prices[buy];
    }
}
