package CodePath.Week1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Session1 {

    public static void main(String[] args){
        p5DestinationCityTest();
    }

    public static void p5DestinationCityTest(){
        String[][] paths = new String[][]{{"London", "New York"}, {"New York", "Lima"}, {"Lima", "Sao Paulo"}};
        String res = destinationCity(paths);
        System.out.println(res);

        paths = new String[][]{{"B", "C"}, {"D", "B"}, {"C", "A"}};
        res = destinationCity(paths);
        System.out.println(res);
    }

    /**
     * You are given the array paths, where paths[i] = [cityAi, cityBi]
     * means there exists a direct path going from cityAi to cityBi.
     * Return the destination city, that is, the city without any
     * path outgoing to another city.
     *
     * It is guaranteed that the graph of paths forms a line without any loop,
     * therefore, there will be exactly one destination city.
     *
     * Understand
     * Given a string matrix - find the element that appears only once
     * Are the strings inside of the matrix fixed size
     * can there be multiple strings in a single path array
     *
     * Match
     * use a set
     * add each element into the set
     * when the element is encountered again
     * in an outgoing/incoming flight then
     * remove element from set
     * the city that does not have any outgoing path
     * will be the only value left in the set
     * */
    public static String destinationCity(String[][] paths){
        Set<String> incoming = new HashSet<>();
        Set<String> outgoing = new HashSet<>();

        for(String[] trip : paths){
            incoming.add(trip[0]);
            outgoing.add(trip[1]);
        }

        for(String city : outgoing){
            if(!incoming.contains(city)){
                return city;
            }
        }
        return "";
    }
}
