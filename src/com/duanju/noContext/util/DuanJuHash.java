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
import cn.cmanlh.util.Hash;

import com.duanju.noContext.dictionary.Character;

/**
 * @author cmanlh@163.com
 * 
 */
public class DuanJuHash implements Hash<Character> {

	private DuanJuEntry[] data;

	private int size;

	private int capacity = 8;

	public DuanJuHash() {
		data = new DuanJuEntry[capacity];
	}

	public DuanJuHash(int capacity) {
		this.capacity = capacity;

		data = new DuanJuEntry[capacity];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Character get(int hashCode) {
		Entry<Character> entry = data[indexFor(hash(hashCode), capacity)];

		Character c = null;
		while (null != entry) {
			c = entry.getValue();

			if (c.hashCode() == hashCode) {
				return c;
			} else if (c.hashCode() > hashCode) {
				return null;
			}
			entry = entry.next();
		}

		return null;
	}

	@Override
	public boolean contains(int hashCode) {
		Entry<Character> entry = data[indexFor(hash(hashCode), capacity)];

		Character c = null;
		while (null != entry) {
			c = entry.getValue();

			if (c.hashCode() == hashCode) {
				return true;
			} else if (c.hashCode() > hashCode) {
				return false;
			}
			entry = entry.next();
		}

		return false;
	}

	@Override
	public void add(Character member) {
		int idx = indexFor(hash(member.hashCode()), capacity);
		if (null == data[idx]) {
			data[idx] = new DuanJuEntry(member);
		} else {
			data[idx].add(member);
		}
	}

	@Override
	public void remove(int hashCode) {
		int idx = indexFor(hash(hashCode), capacity);
		Entry<Character> entry = data[idx];

		if (null != entry) {
			data[idx] = (DuanJuEntry) entry.remove(hashCode);
		}
	}

	@Override
	public void addAll(Hash<? extends Character> members) {
		if (null != members && members instanceof DuanJuHash) {
			DuanJuHash tmpHash = (DuanJuHash) members;
			for (DuanJuEntry entry : tmpHash.data) {
				while (null != entry) {
					add(entry.getValue());

					entry = (DuanJuEntry) entry.next();
				}
			}
		}
	}

	/**
	 * 压缩空间到接近实际大小，用于节省空间，并优化散列分布。运行时慎用！
	 */
	public void trimToSize() {

	}

	private static int indexFor(int hash, int length) {
		return hash % length;
	}

	private static int hash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}
}
