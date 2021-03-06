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

import java.io.File;

import org.junit.Test;

/**
 * @author cmanlh@163.com
 * 
 */
public class FMMAnalyzerTest {
	@Test
	public void run() {

		File dictFile = new File("trunk\\resource\\dic\\dict.dic");

		FMMAnalyzer analyzer = FMMAnalyzer.getAnalyzer(dictFile);

		System.out
				.println(analyzer
						.analysis("由于科技所限，古代交流和交易是以陆路为主，辅以近海，所以古代交流和交易最为便捷的地方必然位于那时候已知的大陆的中心，也就是亚非欧大陆的交界处，和人类最早征服的海域，地中海沿岸。加上良好的气候和物产，所以人类文明史上最先进文明和最强势的国家大多诞生于这个地区。古埃及，古希腊，两河的诸多文明，但反过来同样的便捷和强势文明和国家的扎堆，又导致了战争，瘟疫极为频繁地爆发，强大的文明和帝国一个个如流星般消逝。也就是说，在这个地区，交流和交易的收益非常高，但是成本也非常高。导致了这个地区总能产生最先进的文明和国家，但很难产生长寿的文明和国家。"));

	}
}
