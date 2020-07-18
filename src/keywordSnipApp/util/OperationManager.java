package keywordSnipApp.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

/*
 * this class contain all the method to search the keywords and create has table
 * 
 * */

public class OperationManager {

	Hashtable<String, ArrayList<String>> snippetsHashTableData;
	private static KeyWords keyWords = new KeyWords();

	public OperationManager() {
		snippetsHashTableData = new Hashtable<String, ArrayList<String>>();

	}

	public Hashtable<String, ArrayList<String>> getSnippetsHashTableData() {
		return snippetsHashTableData;
	}

	public void setSnippetsHashTableData(Hashtable<String, ArrayList<String>> snippetsHashTableData) {
		this.snippetsHashTableData = snippetsHashTableData;
	}	/*
	 * search the keyword and return a hashtable with kewwords key and sippets list
	 */

	public Hashtable<String, ArrayList<String>> insertData(ArrayList<String> contentsList) {

		ArrayList<String> snippetsList = null;

		for (int i = 0; i < keyWords.getList().size(); i++) {

			snippetsList = new ArrayList<String>();

			for (int k = 0; k < contentsList.size(); k++) {
				if (contentsList.get(k).contains(keyWords.getList().get(i))) {
					snippetsList.add(contentsList.get(k));

				}

			}

			if (snippetsList.size() != 0) {
				snippetsHashTableData.put(keyWords.getList().get(i), snippetsList);

			}
		}
		return snippetsHashTableData;
	}
	
	
	

	/*
	 * search the keyword add kewword as key and sippets as array list to the hash
	 * table and return boolean
	 */
	
	
	/*
	 * public boolean addData(ArrayList<String> contentsList) {
	 * 
	 * ArrayList<String> snippetsList = null; boolean result = false;
	 * 
	 * for (int i = 0; i < keyWords.getList().size(); i++) {
	 * 
	 * snippetsList = new ArrayList<String>();
	 * 
	 * for (int k = 0; k < contentsList.size(); k++) { if
	 * (contentsList.get(k).contains(keyWords.getList().get(i))) {
	 * 
	 * snippetsList.add(contentsList.get(k));
	 * 
	 * }
	 * 
	 * }
	 * 
	 * if (snippetsList.size() != 0) {
	 * snippetsHashTableData.put(keyWords.getList().get(i), snippetsList); result =
	 * true;
	 * 
	 * } } return result; }
	 */

	// record the method respose in a log file
	public void logWriter(String text) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("info.log", true));
			writer.newLine();
			writer.write(text);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	/*
	 * public void logWriter(String fileName, String text) throws IOException {
	 * 
	 * BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
	 * writer.newLine(); writer.write(text); writer.close(); }
	 */
}
