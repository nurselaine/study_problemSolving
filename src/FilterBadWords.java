public class FilterBadWords {

    public static void main(String[] args){
        String[] badPhrases = new String[]{"hate u", "hatred"};
        String query = "Oh man, I hate u.";
        String query2 = "I love you";
        String query3 = "I hate u, but I love you";
        boolean result = filterBadWords(badPhrases, query);
        System.out.println(query + " " + result);
        result = filterBadWords(badPhrases, query2);
        System.out.println(query2 + " " + result);
        result = filterBadWords(badPhrases, query3);
        System.out.println(query3 + " " + result);
    }

    // Given a string and a list of bad words, determine whether
    // the string contains any of the bad words in the string
    // if the string does then return false
    // otherwise return true

    // ex. [bad, hate, hatred]  string: "I love birds" || "I feel un-hatred"
    // PLAN
    // add all bad words into trie
    // iterate through all char in phrase
    // if phrase char is in trie root
    // check if the preceding chars lead to a leaf node in the trie
    // if it does then return false
    // otherwise return true
    public static boolean filterBadWords(String[] badWords, String phrase){
        Trie badTrie = new Trie();
        for(String badWord : badWords) {
            badTrie.insert(badWord);
        }

        for(int i = 0; i < phrase.length(); i++){
            char ch = phrase.charAt(i);
            int index = i + 1;
            String substr = phrase.substring(i, index);
            while(index < phrase.length() && badTrie.startsWith(substr)){
                if(badTrie.search(substr)){
                    return true;
                }
                substr = phrase.substring(i, ++index);
            }

        }
        return false;
    }
}
