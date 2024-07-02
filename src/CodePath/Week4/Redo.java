package CodePath.Week4;
/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
        import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    public static void main(String[] args) {
        testSortSearchItem();
    }

    public static void testSortSearchItem(){
        List<String> Colors = new ArrayList<>();
        List<ISearchResult> searchResults = new ArrayList<>();
        // List<ISearchResult> sortedList = sortSearchItems(searchResults, "Neetha");
        // for(ISearchResult result : sortedList){
        //   System.out.print(result.Brand + " ");
        // }
        ISearchResult obj1 = new ISearchResult("SKU", "elaine", false, Colors);
        ISearchResult obj2 = new ISearchResult("SKU", "Neetha", true, Colors);
        ISearchResult obj3 = new ISearchResult("SKU", "Neetha", false, Colors);
        ISearchResult obj4 = new ISearchResult("SKU", "Angel", true, Colors);

        searchResults.add(obj1);
        searchResults.add(obj2);
        searchResults.add(obj3);
        searchResults.add(obj4);

        List<ISearchResult> sortedList = sortSearchItems(searchResults, "Neetha", true);
        for(ISearchResult result : sortedList){
            System.out.print(result.Brand + " ");
        }
    }

    public static List<ISearchResult> sortSearchItems(List<ISearchResult> searchResult, String preferredBrand, boolean isSustainable){
        // 1901, BP, zella, zella
        List<ISearchResult> resultSearch = new ArrayList<>();
        // res = {zella, zella, 1901, BP}
        int priorityIndex = 0;
        for(ISearchResult result : searchResult){
            if(result.Brand.equals(preferredBrand)){
                resultSearch.add(0, result);
                priorityIndex++;
            } else {
                resultSearch.add(result);
            }
        }
        System.out.println(resultSearch);

        for(ISearchResult result : resultSearch){
            if(result.IsSustainable == isSustainable){
                resultSearch.add(priorityIndex, result);
            } else {
                resultSearch.add(result);
            }
        }
        return resultSearch;

    }
    // 1. preferred brands
    // 2. IsSustainable

    /**

     */

    // IPersonalizedContext
// + PreferredBrands: List<String>
// + PrefersSustainableFashion: Bool
// + PreferredColors: List<Color>
    public static class IPersonalizedContext {
        public List<String> PreferredBrands;
        public boolean PrefersSustainableFashion;
        public List<String> PreferredColors;
        public IPersonalizedContext(List<String> PreferredBrand, boolean PrefersSustainableFashion, List<String> PreferredColors){
            this.PreferredBrands = PreferredBrand;
            this.PrefersSustainableFashion = PrefersSustainableFashion;
            this.PreferredColors = PreferredColors;
        }

    }

    // ISearchResult
// + SKU: GUID
// + Brand: String
// + IsSustainable: Nullable<Bool>
// + Colors: List<Color>
    public static class ISearchResult {
        public String SKU;
        public String Brand;
        public boolean IsSustainable;
        public List<String> Colors;

        public ISearchResult(String SKU, String Brand, boolean IsSustainable, List<String> Colors){
            this.SKU = SKU;
            this.Brand = Brand;
            this.IsSustainable = IsSustainable;
            this.Colors = Colors;
        }
    }
}

// Your previous Plain Text content is preserved below:

// We want our shoppers to look good and feel their best! That’s why we need your help to make sure we’re showing our customers the most relevant products whenever they search on our apps and sites.

// We’d like you to build a sorting algorithm that takes an array of search results and moves the most relevant ones to the front. Fortunately, your algorithm will have access to our IPersonalizedContext interface, which has information like PreferredBrands to help.

// For our first iteration, we want your algorithm to move all items that contain a PreferredBrand to the front of the result set. These are the interfaces provided:

// IPersonalizedContext
// + PreferredBrands: List<String>
// + PrefersSustainableFashion: Bool
// + PreferredColors: List<Color>

// ISearchResult
// + SKU: GUID
// + Brand: String
// + IsSustainable: Nullable<Bool>
// + Colors: List<Color>


//   - takes an array of search results and moves the most relevant ones to the front
//   - IPersonalizedContext interface, which has information like PreferredBrands to help.
//   - move all items that contain a PreferredBrand to the front of the result set
//   - input is list of search result => sort search results list to bring preferred brand to the top

//   questions
//   - items that are not the most relevant => order does not matter
//   - Json can be empty? yes
//   - Json size? small array for now
//   - All fields will be required? yes
//   - preferred brands may not appear in searchResults? yes
//   - time/space constraints? not right now

/*
methods
  - create a new arraylist for the results
  add into arraylist
  if brand == preferred brand
    add to top of array list
  - Arrays sort
  heap - sorting by preferred brand
*/

// Examples
// Input Array as JSON
// {
//     "searchResults": [ // zella
//         {
//             "sku": "91f561a6-dc50-468e-9e94-509d2fe2a87e",
//             "brand": "1901",
//             "isSustainable": true,
//             "colors": [
//                 "yellow"
//             ]
//         },
//         {
//             "sku": "bd57f060-526b-4f89-8cff-01bd68463dba",
//             "brand": "BP",
//             "isSustainable": true,
//             "colors": [
//                 "purple",
//                 "yellow"
//             ]
//         },
//         {
//             "sku": "a02129f0-e871-4a2b-9a9e-991381404f72",
//             "brand": "Zella",
//             "isSustainable": true,
//             "colors": [
//                 "blue",
//                 "black"
//             ]
//         },
//         {
//             "sku": "f86c7bf2-1b9c-4ab2-9a72-ae2ca2b97ce2",
//             "brand": "Zella",
//             "isSustainable": false,
//             "colors": [
//                 "blue",
//                 "black",
//                 "green"
//             ]
//         }
//     ]
// }

// Expected Output for PreferredBrand = “Zella”
// {
//     "searchResults": [
//         {
//             "sku": "a02129f0-e871-4a2b-9a9e-991381404f72",
//             "brand": "Zella",
//             "isSustainable": true,
//             "colors": [
//                 "blue",
//                 "black"
//             ]
//         },
//         {
//             "sku": "f86c7bf2-1b9c-4ab2-9a72-ae2ca2b97ce2",
//             "brand": "Zella",
//             "isSustainable": false,
//             "colors": [
//                 "blue",
//                 "black",
//                 "green"
//             ]
//         },
//         {
//             "sku": "91f561a6-dc50-468e-9e94-509d2fe2a87e",
//             "brand": "1901",
//             "isSustainable": true,
//             "colors": [
//                 "yellow"
//             ]
//         },
//         {
//             "sku": "bd57f060-526b-4f89-8cff-01bd68463dba",
//             "brand": "BP",
//             "isSustainable": true,
//             "colors": [
//                 "purple",
//                 "yellow"
//             ]
//         }
//     ]
// }