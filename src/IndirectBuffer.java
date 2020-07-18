
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import keywordSnipApp.util.ConstantDataFile;
import keywordSnipApp.util.OperationManager;

public class IndirectBuffer {

	private String outFileStr = ConstantDataFile.OUTPUT_FILE_NAME;

	private long startTime, elapsedTime; // for speed benchmarking

	private OperationManager operationManager = new OperationManager();

	public void ReadIndirectBuffer(int bufferSize, String inFileStr) {

		// Check file length
		File fileIn = new File(inFileStr);
		// Using FileChannel with indirect ByteBuffer
		String bufferLog = " Using File Channel with an indirect ByteBuffer of " + bufferSize + " and File size is "
				+ fileIn.length() + " bytes";


		ArrayList<String> contentsList = new ArrayList<String>();

		try (FileChannel in = new FileInputStream(inFileStr).getChannel();
				FileChannel out = new FileOutputStream(outFileStr).getChannel()) {
			// Allocate an indirect ByteBuffer
			ByteBuffer bytebuf = ByteBuffer.allocate(bufferSize);

			startTime = System.nanoTime();
			int bytesCount;
			while ((bytesCount = in.read(bytebuf)) > 0) { // Read data from file into ByteBuffer
				// flip the buffer which set the limit to current position, and position to 0.

				bytebuf.flip();
				// out.write(bytebuf); // Write data from ByteBuffer to file
				String text = StandardCharsets.UTF_8.decode(bytebuf).toString();

				contentsList.add(text);

				bytebuf.clear(); // For the next read
			}
			
			//search keywords and add to a hashTable

			if (operationManager.insertData(contentsList) != null) {

				for (Map.Entry<String, ArrayList<String>> entry : operationManager.getSnippetsHashTableData()
						.entrySet()) {
					System.out.println("Keywords: "+ entry.getKey());
					for (int k = 0; k < entry.getValue().size(); k++) {
						// Write data from ByteBuffer to file
						out.write(ByteBuffer.wrap(entry.getValue().get(k).getBytes()));
						System.out.println("Snips "+k+": "+entry.getValue().get(k));

					}
				}

				// Write HashTable Data by Key
				/*
				 * ArrayList<String> list =
				 * operationManager.getSnippetsHashTableData().get(ConstantDataFile.KEYWORD_6);
				 * if ( list!= null) { for (int k = 0; k < list.size(); k++) {
				 * out.write(ByteBuffer.wrap(list.get(k).getBytes()));
				 * 
				 * } }
				 */

			}

			elapsedTime = (System.nanoTime() - startTime);

			String logInfo = new Date() + bufferLog + " Elapsed Time is " + (elapsedTime / 1000000.0) + " msec";
			System.out.println(logInfo);
			//log the respose time
			operationManager.logWriter(logInfo);

		} catch (IOException ex) {
			ex.printStackTrace();
			//log error
			operationManager.logWriter(new Date() +" Using FileChannel with direct ByteBuffer Error: " +ex.getMessage());

		}
	}

}
