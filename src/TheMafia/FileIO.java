package TheMafia;

import java.io.*;

public class FileIO {
	private String filename = "mafia_save.txt";
	BufferedWriter bw = null;
	BufferedReader br = null;
	File file = null;
	public int fin;
	private String part;

	public FileIO() {
		file = new File(filename);
	}
	// Save String array (script) and id into "mafia_save.txt"
	public void save(String[] str, int[] id) {
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"UTF8"));
			for( int i=0; i<str.length; i++) {
				bw.write(id[i] + " "+ str[i] + "\n");
			}
			bw.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	// Save the String part into the "mafia_save.txt"
	public void save_part(String part) {
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"UTF8"));
			bw.write("part: " + part + "\n");
			bw.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	// Getting the id to start. With setting String part
	public int load() {		
		try {
			br = new BufferedReader(new InputStreamReader( new FileInputStream(file),"UTF8"));
			String line;
			String cut ="";
			while((line = br.readLine()) != null) {
				if(line.contains("part"))	// Set String part
					this.part = line.substring(6);
				else	// Cut and get id from the line
					cut = line.substring(0,5);
			}
			// fin: last id in "mafia_save.txt"
			fin = Integer.parseInt(cut);
			br.close();
		}
		catch(FileNotFoundException e){
		      e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		// return fin, last id
		return fin;
	}
	// Getting String part
	public String getpart() {
		return this.part;
	}
}