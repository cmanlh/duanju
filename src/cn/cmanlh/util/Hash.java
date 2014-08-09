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
package cn.cmanlh.util;

/**
 * @author cmanlh@163.com
 * 
 */
public interface Hash<T> {

	public int size();

	public T get(int hashCode);

	public boolean contains(int hashCode);

	public void addAll(Hash<? extends T> members);

	/**
	 * 添加一个成员
	 * 
	 * @param member
	 * @return
	 */
	public void add(T member);

	/**
	 * 移除一个成员
	 * 
	 * @param member
	 * @return
	 */
	public void remove(int hashCode);
}
