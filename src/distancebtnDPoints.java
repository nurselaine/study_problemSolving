public class distancebtnDPoints {

    public static void main(String[] args){
        double test1 = distanceNDPoint(new int[]{1, 1}, new int[]{2, 2}, 2);
        System.out.println("Distance 2D: " + test1);
        double test2 = distanceNDPoint(new int[]{1, 1, 1}, new int[]{2, 2, 2}, 3);
        System.out.println("Distance 3D: " + test2);
        double test3 = distanceNDPoint(new int[]{1, 1, 1, 1}, new int[]{2, 2, 2, 2}, 4);
        System.out.println("Distance 4D: " + test3);
    }

    public static double distanceNDPoint(int[] p1, int[] p2, int numDimensions){
        double dist = 0;

        for(int i = 0; i < numDimensions; i++){
            dist += Math.pow((p2[i] - p1[i]), 2);
        }

        return Math.sqrt(dist);
    }
}
