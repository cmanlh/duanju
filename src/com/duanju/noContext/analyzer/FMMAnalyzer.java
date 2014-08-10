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
package com.duanju.noContext.analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.duanju.metadata.Metadata;
import com.duanju.noContext.dictionary.Dictionary;
import com.duanju.noContext.util.DuanJuHash;

/**
 * 正向最大匹配(Forward Maximum Matching)
 * 
 * @author cmanlh@163.com
 * 
 */
public class FMMAnalyzer implements Analyzer {
	private Dictionary dict;

	private FMMAnalyzer() {
	}

	public static FMMAnalyzer getAnalyzer(File file) {
		FMMAnalyzer analyzer = new FMMAnalyzer();
		analyzer.dict = new Dictionary(1024);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String word = null;
			while (null != (word = reader.readLine())) {
				analyzer.dict.addWord(word);
			}

			return analyzer;
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	@Override
	public List<CharSequence> analysis(CharSequence input) {
		if (null == input || input.length() < 1) {
			return null;
		}

		List<CharSequence> result = new ArrayList<CharSequence>();
		int size = input.length();
		int wStartIndex = 0, wEndIndex = 0;
		com.duanju.noContext.dictionary.Character got = null;
		for (int index = 0; index < size; index++) {
			got = dict.find(input.charAt(index));
			if (null != got) {
				int preWordStartIndex = wStartIndex, preWordEndIndex = index;

				wStartIndex = index;
				wEndIndex = -1;
				DuanJuHash nextChildren = null;
				while (null != got) {
					if (got.beWordGotHere()) {
						wEndIndex = index;
					}

					nextChildren = got.nextChar();
					if (null != nextChildren) {
						index += 1;
						if (index < size) {
							got = nextChildren.get(input.charAt(index));
						} else {
							break;
						}
					} else {
						break;
					}
				}

				if (-1 == wEndIndex) {
					index = wStartIndex;
					wStartIndex = preWordStartIndex;
				} else {
					if (preWordStartIndex != wStartIndex) {
						result.add(input.subSequence(preWordStartIndex, preWordEndIndex));
					}

					result.add(input.subSequence(wStartIndex, wEndIndex + 1));

					index = wEndIndex;
					wStartIndex = wEndIndex + 1;
				}
			}
		}

		if (wStartIndex < size) {
			result.add(input.subSequence(wStartIndex, size));
		}

		return result;
	}

	@Override
	public List<Metadata> mark(CharSequence input) {
		return null;
	}

}
