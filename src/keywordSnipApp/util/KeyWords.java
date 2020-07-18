package keywordSnipApp.util;

import java.util.ArrayList;

/*
 * this class contain the list of keyword
*/
public class KeyWords {

	private ArrayList<String> keywordList;

	/**
	 * Creating list of keywords
	 */
	public KeyWords() {
		keywordList = new ArrayList<String>();

		keywordList.add(ConstantDataFile.KEYWORD_1);
		keywordList.add(ConstantDataFile.KEYWORD_2);
		keywordList.add(ConstantDataFile.KEYWORD_3);
		keywordList.add(ConstantDataFile.KEYWORD_4);
		keywordList.add(ConstantDataFile.KEYWORD_5);
		keywordList.add(ConstantDataFile.KEYWORD_6);

		// keywordList.add(ConstantDataFile.KEYWORD_7);
		// keywordList.add(ConstantDataFile.KEYWORD_8);
		// keywordList.add(ConstantDataFile.KEYWORD_9);

	}

	public ArrayList<String> getList() {
		return keywordList;
	}
}
