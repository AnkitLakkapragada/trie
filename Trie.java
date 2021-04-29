package trie;

import java.io.*;
import java.util.*;

import com.google.api.client.util.Lists;

import com.google.api.services.storage.Storage;
import com.google.api.services.storage.model.Bucket;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.storage.StorageOptions;

public class Trie {

	public class TrieNode {
		boolean isEnd;
		TrieNode[] total_chars = new TrieNode[26];

		public TrieNode() {
			isEnd = false;
		}

	}

	public TrieNode parent;

	public Trie() {
		parent = new TrieNode();
	}

	public void add(String word) {
		TrieNode newParent = parent;
		for (int i = 0; i < word.length(); i++) {
			char temp = word.charAt(i);
			if (newParent.total_chars[temp - 'a'] != null) {
				newParent = newParent.total_chars[temp - 'a'];
			} else if (newParent.total_chars[temp - 'a'] == null) {
				TrieNode child = new TrieNode();
				newParent.total_chars[temp - 'a'] = child;
				newParent = child;
			} else {
				newParent = null;
				System.out.println("Error!");
			}
		}
		if(newParent != null) {
			parent.isEnd = true;
		}
		else {
			System.out.println("Error");
		}
		
	}

	public void delete(String word) {
		TrieNode newParent = parent;
		for(int i = 0; i < word.length(); i++) {
			char temp = word.charAt(i);
			if(newParent.total_chars[temp - 'a'] == null) {
				System.out.println("Exit");
				break;
			}
			else {
				if(newParent.isEnd) {
					newParent = null;
				}
				else {
					newParent = newParent.total_chars[temp - 'a'];
				}
			}
		}
		
	}	

	public boolean search(String word) {
		TrieNode newParent = parent;
		for(int i = 0; i < word.length(); i++) {
			char temp = word.charAt(i);
			if(newParent.total_chars[temp - 'a'] == null) {
				return false;
			}
			else {
				 newParent = newParent.total_chars[temp - 'a'];
			}
		}
		return true;
	}
}
