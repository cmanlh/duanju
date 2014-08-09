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
package com.duanju.noContext.util;

import cn.cmanlh.util.Entry;

import com.duanju.noContext.dictionary.Character;

/**
 * @author cmanlh@163.com
 * 
 */
public class DuanJuEntry implements Entry<Character> {
	private Character c;

	private DuanJuEntry next;

	public DuanJuEntry(Character c) {
		this.c = c;
	}

	@Override
	public Character getValue() {
		return c;
	}

	@Override
	public Entry<Character> next() {
		return next;
	}

	@Override
	public void add(Character member) {
		if (c.hashCode() == member.hashCode()) {
			DuanJuHash nextChar = c.nextChar();
			if (null != nextChar) {
				c.nextChar().addAll(member.nextChar());
			} else {
				c = member;
			}

			return;
		}

		Character tmp = member;
		if (c.hashCode() > member.hashCode()) {
			tmp = c;
			c = member;
		}

		if (null == next) {
			next = new DuanJuEntry(tmp);
		} else {
			next.add(tmp);
		}
	}

	@Override
	public Entry<Character> remove(int hashCode) {
		DuanJuEntry newEntry = null;

		if (c.hashCode() == hashCode) {
			if (next != null) {
				c = next.c;
				next = next.next;

				newEntry = this;
			}
		} else if (c.hashCode() > hashCode) {
			newEntry = this;
		} else {
			next = (DuanJuEntry) next.remove(hashCode);
			newEntry = this;
		}

		return newEntry;
	}

	@Override
	public int hashCode() {
		return c.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DuanJuEntry) {
			if (((DuanJuEntry) obj).c.hashCode() == c.hashCode()) {
				return true;
			}
		}

		return false;
	}

}
