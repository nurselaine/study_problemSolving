package leetcode150;

import java.util.*;

public class RandomizedSet {
    private Map<Integer, Integer> map;
    private List<Integer> list;
    private Random rand;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    // insert into map with
    // index as value nd VAL as key
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }

        map.put(val, list.size()); // 1
        list.add(val);

        return true;
    }

    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }


        // remove val from arraylist
        int i = map.get(val); // index of val being removed
        int temp = list.get(list.size() - 1); // last vaue in list to be swapped
        list.set(i, temp);
        list.remove(list.size() - 1); //remove last val

        map.put(temp, i);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        int N = list.size();
        int randomIndex = rand.nextInt(N);
        // System.out.println(N);
        // System.out.println(list.get(randomIndex) + " " + randomIndex);
        return list.get(randomIndex);
    }
}
