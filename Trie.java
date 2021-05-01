package trie;

import java.util.*;

public class Trie {
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

	public TrieNode parent;

	public Trie() {
		parent = new TrieNode();
	}

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

	public void display(TrieNode root, String fragment) {
		for (@SuppressWarnings("rawtypes") Map.Entry entry : root.childs.entrySet()) {
			TrieNode temp = (TrieNode) entry.getValue();
			if (temp.isEnd) {
				System.out.println(fragment + temp.character);
			}
			
			display(temp, fragment + temp.character);
		}
	}

	public static void main(String[] args) {
		Trie tree = new Trie();

		tree.add("apple");
		tree.add("ape");
		tree.add("banana");
		tree.add("clock");
		tree.add("test");
		tree.add("zoo"); 
		System.out.println("displayed trie with the word zoo");
		System.out.println("");
		tree.display(tree.parent, "");
		System.out.println("");
		tree.delete("zoo");
		System.out.println("displayed trie with the word zoo deleted");
		System.out.println("");
		tree.display(tree.parent, "");
		System.out.println("");
		System.out.println("autocomplete for words that start with 'a' ");
		System.out.println("");
		tree.autoComplete("a");
		System.out.println("");
		System.out.println("searching for the word 'clock'");
		System.out.println("clock is in the trie: " + tree.search("clock"));
		System.out.println("");
		System.out.println("searching for the word 'mice'");
		System.out.println("clock is in the trie: " + tree.search("mice"));
	}

}