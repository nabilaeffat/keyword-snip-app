
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import keywordSnipApp.util.ConstantDataFile;
import keywordSnipApp.util.OperationManager;

public class ProgrammerManaged {

	private String outFileStr = ConstantDataFile.OUTPUT_FILE_NAME;

	private long startTime, elapsedTime; // for speed benchmarking
	private OperationManager operationManager = new OperationManager();

	public void ReadProgrammerManaged(int bufferSize, String inFileStr) {

		// Using a programmer-managed byte-array
		File fileIn = new File(inFileStr);

		String bufferLog = " Using a programmer-managed byte-array of " + bufferSize + " and File size is "
				+ fileIn.length() + " bytes";
		System.out.println(bufferLog);


		try (FileInputStream in = new FileInputStream(inFileStr);
				FileOutputStream out = new FileOutputStream(outFileStr)) {

			startTime = System.nanoTime();
			byte[] byteArray = new byte[bufferSize]; // byte-array

			ArrayList<String> contentsList = new ArrayList<String>();

			int bytesCount;
			// read file convert to string
			while ((bytesCount = in.read(byteArray)) != -1) {
				String text = new String(byteArray, 0, bytesCount);
				contentsList.add(text);

			}
			if (operationManager.insertData(contentsList) != null) {

				for (Map.Entry<String, ArrayList<String>> entry : operationManager.getSnippetsHashTableData()
						.entrySet()) {
					System.out.println("Keywords: "+ entry.getKey());
					for (int k = 0; k < entry.getValue().size(); k++) {
						// Write data from ByteBuffer to file
						out.write(entry.getValue().get(k).getBytes());
						System.out.println("Snips "+k+": "+entry.getValue().get(k));

					}
				}

				// Write HashTable Data by Key
				/*
				 * ArrayList<String> list =
				 * operationManager.getSnippetsHashTableData().get(ConstantDataFile.KEYWORD_6);
				 * if ( list!= null) { for (int k = 0; k < list.size(); k++) {
				 * out.write(list.get(k).getBytes());
				 * 
				 * } }
				 */
			}
			elapsedTime = (System.nanoTime() - startTime);
			//log the respose time
			String logInfo = new Date() + bufferLog + " Elapsed Time is " + (elapsedTime / 1000000.0) + " msec";
			System.out.println(logInfo);
			operationManager.logWriter( logInfo);

		} catch (IOException ex) {
			ex.printStackTrace();
			//log error
			operationManager.logWriter(new Date() +" Using FileChannel with direct ByteBuffer Error: " +ex.getMessage());

		}
	}
}
