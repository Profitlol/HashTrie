package hashtrie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HashTrie {

    int wordsCount;
    Map<Character, Node> root;

    public HashTrie() 
    {
        root = new HashMap<Character, Node>();
        wordsCount = 0;
    }

    /**
     * adds word(s) to the trie. returns true if word is added else
     * returns false if word is already there or there is a null entry
     */
    public boolean insertWord(String word) 
    {
        if (word != null) {
            char[] letters = word.toCharArray();
            char first = letters[0];
            Node current;
	    // if first letter exists then retrieve it from the root,
            // otherwise add the letter to root.
            if (root.containsKey(first)) 
                current = root.get(first);
            else 
            {
                Node temp = new Node(first, false);
                root.put(first, temp);
                current = temp;
            }
            // loop through rest of letters
            for (int i = 1; i < letters.length; i++) 
            {
                char currChar = letters[i];
		// if the current character already lies within one of the edges
                // then set current to that character. if not, create it.
                if (current.edges.containsKey(currChar)) 
                    current = current.edges.get(currChar);
                else 
                {
                    Node node = new Node(currChar, false);
                    current.edges.put(currChar, node);
                    current = node;
                }
            }
	    // if the word already exists then the last letter makes it a full word, so return false.
            // otherwise, the word did not exist before so set the last letter as a full word and
            // return true.
            if (!current.isWord) 
            {
                wordsCount++;
                current.isWord = true;
                return true;
            }
            return false;
        }
        return false;

    }

    //finds word in trie
    // true = word found, else false = not found
    public boolean searchWord(String word) 
    {
        if (word == null) 
            return false;        
        char[] letters = word.toCharArray();
        char first = letters[0];
        if (!root.containsKey(first)) 
            return false;       
        Node current = root.get(first);
        for (int i = 1; i < letters.length; i++) 
        {
            char currChar = letters[i];
            if (!current.edges.containsKey(currChar)) 
            {
                return false;
            }
            current = current.edges.get(currChar);
        }
        // return false in the case where current represents a prefix and is not a word.
        if (current != null && !current.isWord) 
            return false;        
        return true;
    }

    // checks # of words added to trie
    // why? why not?
    // need to see if my test is correct
    public int getTotalWords() {
        return wordsCount;
    }

}
