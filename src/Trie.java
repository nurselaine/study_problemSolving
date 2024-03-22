import java.nio.file.Paths;
import java.util.Map;

public class Trie {

    public TrieNode root;
    public Trie(){
        this.root = new TrieNode();
    }

    // insert
    public void insert(String word){
        // get current ptr
        TrieNode curr = root;

        // iterate over word chars
        for(char ch : word.toCharArray()){
            // check if root has word[i]
            Map<Character, TrieNode> children = curr.children;

            if(!children.containsKey(ch)){
                children.put(ch, new TrieNode());
            }
            // update current to current child where word[i] exists
            curr = children.get(ch);
            // create word[i] key and new treenode child in root
        }
        curr.isLeaf = true;
    }

    // search
    public boolean search(String word){
        // get current ptr
        TrieNode curr = root;
        // iterate over word chars
        for(char ch : word.toCharArray()){
            // check if root has word[i]
            Map<Character, TrieNode> children = curr.children;
            // update current to current child where word[i] exists
            if(!children.containsKey(ch)){
                return false;
            }
            curr = children.get(ch);
        }
        // return whether current isLeaf is true
        return curr.isLeaf;
    }

    // startsWith
    public boolean startsWith(String prefix){
        // get current ptr
        TrieNode curr = root;

        // iterate over word chars
        for(char ch : prefix.toCharArray()){
            Map<Character, TrieNode> children = curr.children;
            // check if root has word[i]
            if(!children.containsKey(ch)){
                return false;
            }
            // update current to current child where word[i] exists
            // continue updating until current != contains key word[i]
            curr = children.get(ch);

        }
         return true;
    }
}
