import java.util.*;

public class courseSchedule {

    public static void main(String[] args){
        int[][] test1 = new int[][]{new int[]{1, 0}};
        boolean result = courseSchedule(2, test1);
        System.out.println("Result " + result);
        int[][] test2 = new int[][]{new int[]{1, 0}, {0, 1}};
        result = courseSchedule(2, test2);
        System.out.println("Result " + result);
        int[][] test3 = new int[][]{};
        result = courseSchedule(1, test3);
        System.out.println("Result " + result);
        int[][] test4 = new int[][]{new int[]{1, 4}, {2, 4}, {3, 1}, {3, 2}};
        result = courseSchedule(5, test4);
        System.out.println("Result " + result);
    }

    // From the prereqs list determine if the list contains
    // a cycle where [course, prereq] each prereq must be taken
    // before each course. A cycle occurs when the prereq has a
    // a prereq that is the course
    // PLAN
    // use Kahn's algorithm
    // find the indegree for each course
    // create a queue and all courses with indgree of 0
    // create an adjacency list to hold all the courses needed to be take for each prereq
    // while the queue is not empty
    // remove from the queue
    // iterate over each course prereqs
    // update their indegree and add to queue when indegree is 0
    // keep a course count
    // return wehther the course count is equal to num courses
    // if it is not then not all the courses were able to be taken because of
    // a cycle
    public static boolean courseSchedule(int numCourses, int[][] prereqs){
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        for(int[] p : prereqs){
            int course = p[0];
            int prereq = p[1];
            map.put(prereq, map.getOrDefault(prereq, new ArrayList<>()));
            map.get(prereq).add(course);
            indegree[course]++;
        }

        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }
        int visitedCourses = 0;
        while(!queue.isEmpty()){
            int curr = queue.remove();
            visitedCourses++;

            if(map.get(curr) != null){
                for(int c : map.get(curr)){
                    indegree[c]--;
                    if(indegree[c] == 0){
                        queue.add(c);
                    }
                }
            }
        }
        return visitedCourses == numCourses;

    }
}
