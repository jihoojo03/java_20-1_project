package TheMafia;

public class Selection {
	private int select[] = new int[11]; //hoxy 사용할지도 몰라 저장해두는 변수
	private int totalNext[][] = new int[11][3]; 

	public Selection() {
		totalNext[0][0] = 10027;
		totalNext[0][1] = 10046;
		totalNext[0][2] = 0;
		totalNext[1][0] = 10031;
		totalNext[1][1] = 10046;
		totalNext[1][2] = 0;
		totalNext[2][0] = 10297;
		totalNext[2][1] = 10290;
		totalNext[2][2] = 10282;
		totalNext[3][0] = 10365;
		totalNext[3][1] = 10387;
		totalNext[3][2] = 0;
		totalNext[4][0] = 10473;
		totalNext[4][1] = 10482;
		totalNext[4][2] = 0;
		totalNext[5][0] = 10491;
		totalNext[5][1] = 10507;
		totalNext[5][2] = 0;
		totalNext[6][0] = 10547;
		totalNext[6][1] = 10559;
		totalNext[6][2] = 10582;
		totalNext[7][0] = 10640;
		totalNext[7][1] = 10640;
		totalNext[7][2] = 10647;
		totalNext[8][0] = 10650;
		totalNext[8][1] = 10640;
		totalNext[8][2] = 10640;
		totalNext[9][0] = 10640;
		totalNext[9][1] = 10651;
		totalNext[9][2] = 10640;
		totalNext[10][0] = 10654;
		totalNext[10][1] = 10640;
		totalNext[10][2] = 10640;
	}
	
	public int getNextId(int id, int userSelect) {
		
		int num = id % 90000 - 1;
		select[num] = userSelect;
		int choice = userSelect - 1;
		
		return totalNext[num][choice];
	}
	
	public int getSelectionAmount(int id) {
		int num = id % 10000;
		
		System.out.println(num);
		if(num == 1 || num == 2 || num == 4 || num == 5 || num == 6) return 2;
		else return 3;
	}
	
}
