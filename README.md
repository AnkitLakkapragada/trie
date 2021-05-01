# trie

This is my challenge for the final stage of the Slingshot Internship. The following is the way I implemented the trie data structure in Java. 
```
public class TrieNode {
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
This is where I implemented the TrieNode in the Trie class. Each TrieNode has a HashMap that stores the children under it. Additionally, they all have a Character value and a boolean that tells us if this specific TrieNode represents the last Character in a word.

```
public void add(String words) {
		TrieNode temp = parent;
		for (int i = 0; i < words.length(); i++) {
			TrieNode node = temp.childs.get(words.charAt(i));
			if (!temp.getChilds().containsKey(words.charAt(i))) {
				TrieNode newone = new TrieNode();
				newone.character = words.charAt(i);
				temp.childs.put(words.charAt(i), newone);
				temp = temp.childs.get(words.charAt(i));
			} else {
				temp = node;
			}

		}
		temp.isEnd = true;
	}
```
This is my add function. The way this function works is a user inputs a word. Then for each character, if it doesn't exist in the HashMap of the parent's children, a new TrieNode is created and assigned in the HashMap. Then the newly created child node becomes the parent node. The last TrieNode in a word is considered theEnd as it contains the last character and as a result, the isEnd, boolean is set to true; 

```
public void delete(String words) {
		if (!search(words)) {
			System.out.println("Word not found!");
		} else {
			TrieNode temp = parent;
			for (int i = 0; i < words.length(); i++) {
				TrieNode node = temp.childs.get(words.charAt(i));
				if (node != null) {
					temp = node;
				}
			}
			if (temp.isEnd) {
				temp.isEnd = false;
			}
		}
	}
```
This is my delete function. This function first searches if the word exists. If it doesn't, it returns an error to the user that the word could not be found. Otherwise, it iterates through the word to the end where it changes the TrieNode's isEnd boolean from true to false. This way, more characters can be added and you are technically deleting the word without affecting other words. 

```
public boolean search(String words) {
		TrieNode temp = parent;
		for (int i = 0; i < words.length(); i++) {
			TrieNode node = temp.childs.get(words.charAt(i));
			if (node != null) {
				temp = node;
			} else {
				return false;
			}

		}
		if (temp.isEnd) {
			return true;
		} else {
			return false;
		}
	}
```
This is my search function. This function will keep looking for a TrieNode in the parent's HashMap that has a key of the next character in a word. If all characters are found in the Trie and the TrieNode that represents the last character is correctly the end of the world (given by the boolean), this method will return true. If any of these conditions are not met, it will return false. 

```
public void findFromFragment(TrieNode parent, String fragment) {
		for (@SuppressWarnings("rawtypes") Map.Entry entry : parent.childs.entrySet()) {
			TrieNode child = (TrieNode) entry.getValue();
			if (child.isEnd) {
				System.out.println(fragment + child.character);
			}

			findFromFragment(child, fragment + child.character);
		}
	}

	public void autoComplete(String fragment) {
		TrieNode node = parent;

		for (int i = 0; i < fragment.length(); i++) {
			Character c = fragment.charAt(i);
			boolean root_found = false;
			for (@SuppressWarnings("rawtypes") Map.Entry entry : parent.childs.entrySet()) {
				TrieNode temp = (TrieNode) entry.getValue();
				if (temp.character != c) {
				}
				else {
					node = temp;
					root_found = true;
					break;
				}
			}

			if (!root_found) {
				System.out.println("No autocomplete suggestions!");
				return;
			}
		}

		findFromFragment(node, fragment);

	}
```
The autocomplete method has two methods. The first method that is called autoComplete looks for the fragment that the user provides. It trys to find the parent node to start searching from. Once that is found, it is sent to the method above called findFromFragment. This method iterates through all the TrieNodes in the HashMap of the parent to assemble and print the words. 

```
public void display(TrieNode root, String fragment) {
		for (@SuppressWarnings("rawtypes") Map.Entry entry : root.childs.entrySet()) {
			TrieNode temp = (TrieNode) entry.getValue();
			if (temp.isEnd) {
				System.out.println(fragment + temp.character);
			}
			
			display(temp, fragment + temp.character);
		}
	}
```
This is the display method. The display method works in a similiar way where every word is assembled in the Trie and is printed when it reaches the last TrieNode. It works using recursion so all children nodes can be traveresed at once. 
