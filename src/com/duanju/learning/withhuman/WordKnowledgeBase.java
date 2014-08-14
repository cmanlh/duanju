package com.duanju.learning.withhuman;

import java.util.List;

import com.duanju.noContext.dictionary.Character;
import com.duanju.noContext.dictionary.Dictionary;
import com.duanju.noContext.util.DuanJuHash;

public class WordKnowledgeBase implements KnowledgeBase {
	private Dictionary dic;

	public WordKnowledgeBase(List<CharSequence> wordList) {
		dic = new Dictionary(18);
		for (CharSequence word : wordList) {
			dic.addWord(word);
		}
	}

	@Override
	public boolean knew(CharSequence knowledge) {
		if (null == knowledge || knowledge.length() == 0) {
			return false;
		}

		int length = knowledge.length();
		Character c = dic.find(knowledge.charAt(0));
		if (null == c) {
			return false;
		}

		DuanJuHash children = null;
		for (int i = 1; i < length; i++) {
			children = c.nextChar();
			if (null == children) {
				return false;
			}

			c = children.get(knowledge.charAt(i));
			if (null == c) {
				return false;
			}
		}

		if (c.beWordGotHere()) {
			return true;
		} else {
			return false;
		}
	}
}
