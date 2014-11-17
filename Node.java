package hashtrie;
import java.util.HashMap;
import java.util.Map;

public class Node 
{
	char letter;
	boolean isWord;
	public Map<Character,Node> edges;
	
	public Node(char letter, boolean isWord) 
        {
		this.letter = letter;
		this.isWord = isWord;
		edges = new HashMap<Character,Node>();
	}
}

