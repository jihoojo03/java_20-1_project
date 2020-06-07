package TheMafia;


import java.io.*;

public class Game {
	public String script[][] = new String[1000][6];
	public int Row = 0;
	public int Col = 5;
	public int firstId = 0;
	
	public void readCsv(String part) {
		
		int row =0 ,i=0;
		File csv = new File("C://story.csv"); //.csv 파일 저장위치에 따라 수정 필요 
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(csv));
			String line = "";
				
			line = br.readLine();
			while((line = br.readLine()) != null) {
				
				String[] token = line.split(",");
				if(!token[0].equals(part)) {
					continue;
				}
				if(token.length>6) {
					for(i=6; i<token.length;i++) {
						token[5] = token[5] +','+token[i];
					}
				}
				if(token[5].startsWith("\"")) {
				token[5] = token[5].substring(1);
				token[5] = token[5].substring(0,token[5].length() - 1);
				}
				for(i=0;i<Col;i++) {
					script[row][i] = token[i+1];
				}
				row++;
			}
			br.close(); 
			
		}
	
		catch(FileNotFoundException e){
		      e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		Row = row;
		firstId = Integer.parseInt(script[0][0]);

		for(int j=0;j<Row;j++) { //출력 확인 구문
			for(i=0;i<Col;i++) {
				System.out.print(script[j][i] + ",");
			}
			System.out.println("");
		}
	}
	
	public int getFirstId() {
		return firstId;
	}
	
	public int getPlace(int id) { 
		String ID = Integer.toString(id);
		int place = 0;
		for(int i=0;i<Row;i++) {
			if(script[i][0].equals(ID)) {
				place = Integer.parseInt(script[i][1]);
				break;
			}
		}
		return place;
	}
	
	public int getCharacter(int id) {
		String ID = Integer.toString(id);
		int character = 0;
		for(int i=0;i<Row;i++) {
			if(script[i][0].equals(ID)) {
				character = Integer.parseInt(script[i][2]);
				break;
			}
		}
		return character;
	}
	
	public int getBGM(int id) {
		String ID = Integer.toString(id);
		int bgm = 0;
		for(int i=0;i<Row;i++) {
			if(script[i][0].equals(ID)) {
				bgm = Integer.parseInt(script[i][3]);
				break;
			}
		}
		return bgm;
	}
	
	public String getScript(int id) {
		String ID = Integer.toString(id);
		String scr = "";
		for(int i=0;i<Row;i++) {
			if(script[i][0].equals(ID)) {
				scr = script[i][4];
				break;
			}
		}
		return scr;
	}
	
	public int getTotalNum() {
		return Row;
	}
	
	public int getNextId(int id) {
		String ID = Integer.toString(id);
		String nextId = "";
		int intNextId = 0;
		
		for(int i=0;i<Row;i++) {
			if(script[i][0].equals(ID)) {
				if(i==Row-1) {
					return 0;
				}
				nextId = script[i+1][0];
				break;
			}
		}
		intNextId = Integer.parseInt(nextId);
		return intNextId;
	}
	
}