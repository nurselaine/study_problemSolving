import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // create a pins list
        List<Pin> Pins = new ArrayList<>();
        Pins.add(new Pin(1, 4, "L"));
        Pins.add(new Pin(2,3,"R"));
        Pins.add(new Pin(4,10,"R"));
        Pins.add(new Pin(5,6,"L"));


        System.out.println(getMaxPins(Pins, 7));
        System.out.println(getMaxPinsOp(Pins, 7));

        System.out.println("Hello world!");
    }

    // pins problem
    public static int getMaxPins(List<Pin> Pins, int SL){

        // get subList for L and R col
        List<Pin> leftCol = new ArrayList<>();
        List<Pin> rightCol = new ArrayList<>();

        for(int i = 0; i < Pins.size(); i++){
            if(Pins.get(i).row.equals("L")){
                leftCol.add(Pins.get(i));
            } else {
                rightCol.add(Pins.get(i));
            }
        }

        // found max pins that fit in SL for L and R col
        int max = getMaxColPins(leftCol, SL);
        max += getMaxColPins(rightCol, SL);

        return max;
    }

    public static int getMaxPinsOp(List<Pin> Pins, int SL){
        // sort the pins
        Pins.sort((Pin a, Pin b) -> a.top - b.top);

        // declare SL bounds
        int lower = 0;
        int upper = SL;
        int upperMax = 0;
        int counter = 0;
        int max = 0;

        // find upper bound
        for(Pin p : Pins){
            upperMax = Math.max(p.bottom, upperMax);
        }

        // iterate each for each window
        while(upper <= upperMax){

            // iterate each pin
            for(Pin p : Pins){

                // check if value is within ptr bounds
                if(p.top >= lower && p.bottom <= upper){
                    counter++;
                }
                // update max and upper ptr
                max = Math.max(max, counter);
                upper++;
                lower++;
            }
        }
        return max;
    }
    // PLAN - SLIDING WINDOW APPROACH
    // sort pins by start index
    // iterate each pin and check how many pins fit within the given screen size
    // check given bounds by using ptrs lower and upper to simulate SL
    // 1. pin is within ptr bounds then increment pin count
    // 2. pin is out of lower bound - do not increment pin count and restart ptrs
    // SL ptrs should increment by 1 until upper bound reaches end of Pins
    // repeat steps above each time and track max number of pins counted each time ptrs are reset

    private static int getMaxColPins(List<Pin> Pins, int SL){// 1, 4   6, 9
        int max = 0; // 4
        int curr = 0;

        for(int i = 0; i < Pins.size(); i++){ // 0
            int len = Pins.get(i).bottom - Pins.get(i).top + 1; // 4
            curr = len <= SL ? 1 : 0; // 1
            int j = i + 1; // 1
            // iterate over the rest of list to check how many other pins fit in the SL
            while(len < SL && j < Pins.size()){ // 4 1
                // check whether adding another pin is possible
                int newPin = Pins.get(j).bottom - Pins.get(j).top + 1; // 9 - 6 + 1 4
                if(len + newPin < SL){ // 4 + 4 < 5
                    curr++;
                }
                max = Math.max(max, curr); //
                j++;
            }
        }
        return max;
    }

    // BRUTE FORCE PLAN
    // a list of pins contains unsorted pins
    // the column matters as you can only place L pins on L col and R respectfuly
    // create a sub list for L pins and one for R pins
    // iterate thru sub list and find max combination of L/R pins combos that fit the screen len
    // 1. check every combination starting from the first pin to the last pin
    // 2. return the max pins that fit in the column
    // repeat this for both L and R columns
    // return the sum of the L + R pins to get the max # of visible pins in given screen length

    //

    public static class Pin{
        public int top;
        public int bottom;
        public String row;
        public Pin(int top, int bottom, String row){
            this.top =top;
            this.bottom = bottom;
            this.row = row;
        }
    }
}

// pins problem
/**
 *Pinterest app screen is two columns of images (pins).
 * Each pin has a top and bottom index (top < bottom), and has a column represented by "L" or "R".
 * Given an unsorted list of non-overlapping pins like
 *
 * pins = [(top_ind,bottom_ind,column),...,]
 * and a screen_length of screen_len
 * Return the maximum number of pins the user can possibly see (fully).
 * That is, complete this:
 *
 * def get_max_pins(pins,screen_len):
 *     max_pins = 0
 * 	...
 *     return max_pins
 * Example:
 *
 * input:
 *   pins = [(1,4,"L"),(2,3,"R"),(4,8,"R"),(6,9,"L")]
 *   screen_len = 5
 * output: 2
 *
 * the important part in the instruction is given all the pins top and bottom
 * index and column. Determine that max number of pins (find all combinations of all pins) that
 * can fit in the max screen height. Is there a leetcode question like this?
 * */