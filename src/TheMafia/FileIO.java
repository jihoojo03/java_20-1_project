package TheMafia;

import java.io.*;
import java.util.Scanner;

public class FileIO {
	private int check = 0;
	private String filename = "mafia_save.txt";
	private PrintWriter outputStream;
	private Scanner inputStream;
	private int fin;		//Indicating ending id of whole story. For initializing txt file when restart after once ending
	
	public FileIO(int check) {
		this.check = check;
		// If check is 1, save mode.
		if(check == 1) {
			try {
				outputStream = new PrintWriter( new FileOutputStream(filename, true));
				outputStream.println();
			}
			catch(FileNotFoundException e) {
				System.out.println("Error when writing on " + filename);
				System.exit(0);
			}
		}
		// If check is 2, load mode.
		else if(check == 2) {
			try {	inputStream = new Scanner(new File(filename));	}
			catch(FileNotFoundException e) {
				System.out.println("Error when reading on " + filename);
				System.exit(0);
			}
		}
	}
	
	// to save 
	public void save(String str, int cur, int next) {
		outputStream.println(cur + " " + next + " "+ str );
	}

	public void load() throws IOException{
		RandomAccessFile file = new RandomAccessFile(filename, "r");
		long fileSize = file.length();
		long pos = fileSize - 1;
		int notend = 0;
		// For read last line
		while(true) {
			file.seek(pos);
			// For read per line
			if(file.readByte() == '\n') {
				// Avoid to read and break on '\n' that is end of file.
				if( notend == 0 )
					notend = 1;
				else
					break;
			}
			pos--;
		}
		file.seek(pos);
		
		byte[] buff = new byte[20];		// Byte 20 is enough to read first and second integers.
		file.read(buff);

		String str2 = new String(buff);
		String array[] = str2.split(" ");
		this.fin = Integer.parseInt(array[1]);	// In mafia_save.txt, second integer of last line has next id.
		file.close();
	}
	public int wheretoplay() {	return fin; }
	
	public void close() {
		if(check == 1)
			closeWriter();
		else if(check == 2)
			closeReader();
	}
	private void closeWriter() {	this.outputStream.close();	}
	private void closeReader() {	this.inputStream.close();	}
}