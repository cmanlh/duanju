/**
 *    Copyright 2014 cmanlh@163.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.duanju.noContext.dictionary;

import com.duanju.noContext.util.DuanJuHash;

/**
 * @author cmanlh@163.com
 * 
 */
public class Character {
	private char c;

	private DuanJuHash nextChar = null;

	private boolean beWordWithPres = false;

	private Character() {
	}

	public static Character getInstance(CharSequence word) {
		if (null == word || word.length() < 1) {
			return null;
		}

		Character character = new Character();
		character.c = word.charAt(0);
		if (word.length() > 1) {
			character.addMoreWord(word);
		} else {
			character.beWordWithPres = true;
		}

		return character;
	}

	public boolean addMoreWord(CharSequence word) {
		if (null == word || word.length() < 2) {
			return false;
		}

		if (null == nextChar) {
			nextChar = new DuanJuHash();
		}

		nextChar.add(getInstance(word.subSequence(1, word.length())));

		return true;
	}

	public boolean beWordGotHere() {
		return beWordWithPres;
	}

	public DuanJuHash nextChar() {
		return nextChar;
	}

	@Override
	public int hashCode() {
		return c;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}

		if (obj instanceof Character) {
			if (((Character) obj).c == this.c) {
				return true;
			}
		}

		return false;
	}

}
