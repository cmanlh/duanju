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
public class Dictionary {
	private DuanJuHash hash;

	public Dictionary(int capacity) {
		hash = new DuanJuHash(capacity);
	}

	public void addWord(CharSequence word) {
		Character c = Character.getInstance(word);
		if (null != c) {
			hash.add(c);
		}
	}

	public Character find(char c) {
		return hash.get(c);
	}

}
