# trie

This is my challenge for the final stage of the Slingshot Internship. The following is the way I implemented the trie data structure in Java. 
```public class TrieNode {
		HashMap<Character, TrieNode> childs;
		boolean isEnd;

		public TrieNode() {
			childs = new HashMap<Character, TrieNode>();
			@SuppressWarnings("unused")
			boolean isEnd = false;
		}

		public HashMap<Character, TrieNode> getChilds() {
			return (HashMap<Character, TrieNode>) childs;
		}

		public boolean isEnd() {
			return isEnd;
		}

		Character character;
	}
```
