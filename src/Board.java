public class Board {

	int userBoard[][];
	int answerBoard[];
	private int currentR = 0, currentC = 0;

	public Board() {
		userBoard = new int[10][4];
	}

	public Board(String answer) {

		int numAnswer = Integer.parseInt(answer);

		userBoard = new int[10][4];

		if (numAnswer > 1000 && numAnswer < 9999) { // checks for 4 digit number
			for (int i = 0; i < 4; i++) {
				answerBoard[i] = (numAnswer % 10); // takes the digit in the
													// ones
													// place
				numAnswer /= 10; // divides out the first digit
			}
		}
	}

	public void generateRandomAnswer() {
		int[] answer = new int[4];
		int num;

		for (int i = 0; i < 4; i++) {
			while (true) {
				num = (int) (Math.random() * (5) + 1);
				if (num != answer[0] && num != answer[1] && num != answer[2]
						&& num != answer[3]) {
					answer[i] = num;
					break;
				}
			}
		}
		for (int i = 0; i < 4; i++)
			answerBoard = answer;
		// System.out.println(this); // THIS TELLS ME THE ANSWER IN THE CONSOLE
	}

	public boolean didYouWin(int r) {

		for (int i = 0; i < 4; i++)
			if (answerBoard[i] != userBoard[r][i])
				return false;

		return true;
	}

	public int getR() {
		return currentR;
	}

	public int getC() {
		return currentC;
	}

	public void setR(int r) {
		currentR = r;
	}

	public void setC(int c) {
		currentC = c;
	}

	public void changeR() {

		if (currentC == 3)
			if (currentR != 10)
				currentR++;
		// else you would lose
	}

	public void changeC() {

		if (currentC != 3)
			currentC++;
		else
			currentC = 0;
	}

	public int colorsCorrect(int r) {
		int colors = 0;

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				if (userBoard[r][i] == answerBoard[j])
					colors++;
		return colors;
	}

	public int spotCorrect(int r) {
		int spots = 0;

		for (int i = 0; i < 4; i++)
			if (userBoard[r][i] == answerBoard[i])
				spots++;

		return spots;
	}

	public String toString() {
		String s = "(";

		for (int i : answerBoard) {
			if (i == 1)
				s += "red/";
			if (i == 2)
				s += "blue/";
			if (i == 3)
				s += "green/";
			if (i == 4)
				s += "yellow/";
			if (i == 5)
				s += "cyan/";
			if (i == 6)
				s += "pink/";
		}
		s = s.substring(0, s.length()-1);
		s += ")";
		return s;
	}
}
