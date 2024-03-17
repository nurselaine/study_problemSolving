import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class validRequest {

    public static void main(String[] args){
        List<String> test1 = new ArrayList<>();
        test1.add("www.zzz.com");
        test1.add("www.zzz.com");
        test1.add("www.zzz.com");
        test1.add("www.zzz.com");
        test1.add("www.ypy.com");
        test1.add("www.zzz.com");
        List<String> responses = validRequest(test1);
        for(String res : responses){
            System.out.println(res);
        }
    }
    // given a list of domains return a list of responses
    // where each domain cannot be requested more than 2 times per 5 seconds (Indices)
    // and 5 times per 30 seconds

    // plan
    // use a hashmap and add the domain name as key and list of req as value
    // create a boolean list for each request
    // before adding in each req index into map
        // if no values in map entry then update boolean list index to true and add to map
        // if values in map entry:
            // calculate j = index - 4
            // iterate over j to i and count # of reqs
                // if count > 2
                    // update boolean list to false otherwise update to true and add to map entry
    // iterate over boolean list and update to response statements
    // 200: successful request or 429: bad request
    public static List<String> validRequest(List<String> requests){
        Map<String, List<Integer>> map = new HashMap<>();
        boolean[] res = new boolean[requests.size()];

        int c = 0;
        for(String s : requests){
            if(!map.containsKey(s)){
                map.put(s, new ArrayList<>());
                map.get(s).add(c);
                res[c] = true;
            } else {
                int j = c - 4;
                List<Integer> prevReq = map.get(s);
                map.get(s).add(c);
                int reqCount = 0;
                for(int i : prevReq){
                    if(i > j){
                        reqCount++;
                    }
                }
                if(reqCount > 2){
                    res[c] = false;
                } else {
                    res[c] = true;
                }
            }
            c++;
        }

        String success = "200: successful request";
        String failure = "429: bad request";
        List<String> responses = new ArrayList<>();
        for(int i = 0; i < res.length; i++){
            String result = res[i] ? success : failure;
            responses.add(result);
        }

        return responses;
    }


}

