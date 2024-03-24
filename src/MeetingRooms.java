import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms {
    public static void main(String[] args){
        int[][] test = new int[][]{new int[]{0, 3}, new int[]{4, 6}};
        boolean result = meetingRoomsI(test);
        System.out.println(result);
        int[][] test2 = new int[][]{new int[]{0, 3}, new int[]{2, 6}};
        result = meetingRoomsI(test2);
        System.out.println(result);

        int res = meetingRoomsII(test);
        System.out.println(res);
        res = meetingRoomsII(test2);
        System.out.println(res);

        int[][] test3 = new int[][]{new int[]{0, 3}, new int[]{2, 6}, new int[]{0, 30}, new int[]{4, 10}, new int[]{4, 17}};
        res = meetingRoomsII(test3);
        System.out.println(res);
    }

    // Given a list of intervals, determine whether
    // there are any overlaps in the end and start
    // values of each intervals - if so then return false
    // otherwise return true
    // PLAN
    // sort the intervals by the start values
    // iterate over all intervals
    // check if
    // 1. cur + 1 interval is within intervals size
    // 2. cur end time is greater than next start time
    //  if so then return false
    // otherwirse return true
    public static boolean meetingRoomsI(int[][] intervals){
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for(int i = 0; i < intervals.length; i++){
            if(i + 1 < intervals.length && intervals[i][1] > intervals[i + 1][0]){
                return false;
            }
        }
        return true;
    }

    // determine how many meeting rooms must be allocated for each interval
    // if an interval overlaps with another interval, they both need their separate
    // meeting rooms. If 2 meeting rooms have already been allocated, then future
    // intervals can use those meeting rooms for their meetings instead of allocating
    // new meeting rooms
    // PLAN
    // use a priority queue that sorts by end values of each interval
    // declare a maxRooms counter
    // sort intervals array by start times
    // iterate over intervals
    // check if:
    // 1. priority queue is emtpy
    // 2. priority queue top end time is > current start time
    //  if so then add the current interval into the heap
    //  otherwise remove the top of the heap and add the queue into the heap
    // each iteration check whether the current heap size is > than the maxRooms count
    // and update if so
    // return the maxRoom counter value
    public static int meetingRoomsII(int[][] intervals){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int maxRooms = 0;
        for(int i = 0; i < intervals.length; i++){
            if(minHeap.isEmpty() || minHeap.peek() > intervals[i][0]){
                minHeap.offer(intervals[i][1]);
            } else {
                minHeap.poll();
                minHeap.offer(intervals[i][1]);
            }
            maxRooms = Math.max(maxRooms, minHeap.size());
        }
        return maxRooms;
    }
}
