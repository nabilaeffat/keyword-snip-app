
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import keywordSnipApp.util.ConstantDataFile;
import keywordSnipApp.util.OperationManager;

public class BufferedIOStream {

	private long startTime, elapsedTime; // for speed benchmarking

	private OperationManager operationManager = new OperationManager();

	public void ReadBufferedIOStream(int bufferSize, String inFileStr) {

		// Using Buffered Stream I/O
		System.out.println("Using Buffered Stream");
		// Check file length
		File fileIn = new File(inFileStr);
		System.out.println("File size is "+ fileIn.length() + " bytes");

		// Using FileChannel with indirect ByteBuffer
		String bufferLog = " Using Buffered Stream with an indirect ByteBuffer of " + bufferSize + " and File size is "
				+ fileIn.length() + " bytes";
		//System.out.println(bufferLog);


		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(ConstantDataFile.OUTPUT_FILE_NAME))) {

			BufferedOutputStream writeLog = new BufferedOutputStream(
					new FileOutputStream(ConstantDataFile.BUFFER_STREAM_log));

			startTime = System.nanoTime();
			int bytesCount;
			byte[] contents = new byte[80];

			ArrayList<String> contentsList = new ArrayList<String>();

			while ((bytesCount = in.read(contents)) != -1) {
				String text = new String(contents); // convert byte to text
				contentsList.add(text);
			}

			if (contentsList != null) {

				if (operationManager.insertData(contentsList) != null) {

					// Write All HashTable Data
					for (Map.Entry<String, ArrayList<String>> entry : operationManager.getSnippetsHashTableData()
							.entrySet()) {
						System.out.println("Keywords: "+ entry.getKey());
						for (int k = 0; k < entry.getValue().size(); k++) {
							out.write(entry.getValue().get(k).getBytes());
							System.out.println("Snippets "+k+": "+entry.getValue().get(k));
							
						}

					}

					// Write HashTable Data by Key

					/*
					 * ArrayList<String> list = operationManager.getSnippetsHashTableData()
					 * .get(ConstantDataFile.KEYWORD_1); if (list != null) { for (int k = 0; k <
					 * list.size(); k++) { System.out.println(list.get(k));
					 * 
					 * out.write(list.get(k).getBytes());
					 * 
					 * } }
					 */
				}
			}

			elapsedTime = (System.nanoTime() - startTime);

			String logInfo = new Date() + bufferLog + " Elapsed Time is " + (elapsedTime / 1000000.0) + " msec";
			System.out.println(logInfo);
			operationManager.logWriter(logInfo);

		} catch (IOException ex) {
			ex.printStackTrace();
			operationManager.logWriter(new Date() +" Using FileChannel with direct ByteBuffer Error: " +ex.getMessage());

		}
	}
}
