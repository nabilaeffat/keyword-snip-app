import keywordSnipApp.util.ConstantDataFile;

//import keywordSnipApp.util.ConstantDataFile;

public class SearchEngine {

	
	static DirectBuffer dbf = new DirectBuffer();
	static IndirectBuffer idbf = new IndirectBuffer();
	static BufferedIOStream bis = new BufferedIOStream();
	static ProgrammerManaged pmba = new ProgrammerManaged();

	public static void main(String[] args) {

		//run the program from batch file
		if (args.length != 0) {
			
			String inFileStr = args[2];
			int bufferSize = Integer.parseInt(args[1]);

			String method = args[0];
			switch (method) {
			case "DirectBuffer":
				// error is here, it is not able to find Direct buffer class
				dbf.ReadDirectBuffer(bufferSize, inFileStr);
				break;
			case "IndirectBuffer":
				idbf.ReadIndirectBuffer(bufferSize, inFileStr);
				break;
			case "BufferedIOStream":
				bis.ReadBufferedIOStream(bufferSize, inFileStr);
				break;
			case "ProgrammerManaged":
				pmba.ReadProgrammerManaged(bufferSize, inFileStr);
				break;
			default:
				throw new IllegalArgumentException("Invalid method: " + method);
			}

		}
		else {
			//run program from eclispe
			String inFileStr = ConstantDataFile.INPUT_FILE_NAME;
			int bufferSizeKB = 1;
			int bufferSize = bufferSizeKB * 1024;
			

			//dbf.ReadDirectBuffer(bufferSize,inFileStr);

			//idbf.ReadIndirectBuffer(bufferSize,inFileStr);

			//bis.ReadBufferedIOStream(bufferSize,inFileStr);

			pmba.ReadProgrammerManaged(bufferSize,inFileStr);
			
		}

	}

}
