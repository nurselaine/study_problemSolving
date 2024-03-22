public class TrieImplementation {
    public static void main(String[] args){
        Trie trie = new Trie();
        trie.insert("Hello");
        boolean contains = trie.search("Hello");
        System.out.println(contains);
        contains = trie.search("hello");
        System.out.println(contains);

        boolean starts = trie.startsWith("Hell");
        System.out.println(starts);
        starts = trie.startsWith("Pie");
        System.out.println(starts);
    }
}
