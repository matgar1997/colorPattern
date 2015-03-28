import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class swingGui extends Board implements ActionListener {

	// Declare All Swing Variables
	JFrame frame = new JFrame("Welcome to Master Mind");

	JPanel mainPanel = new JPanel(new BorderLayout());

	JPanel northPanel = new JPanel();
	JPanel eastPanel = new JPanel();
	JPanel westPanel = new JPanel();
	JPanel southPanel = new JPanel();
	JPanel centerPanel = new JPanel(new GridLayout(10, 4));

	JButton redButton = new JButton(" ");
	JButton blueButton = new JButton(" ");
	JButton greenButton = new JButton(" ");
	JButton yellowButton = new JButton(" ");
	JButton cyanButton = new JButton(" ");
	JButton pinkButton = new JButton(" ");

	JButton single = new JButton("Single Player");
	JButton setUp = new JButton("RESET");

	JLabel correctColorLabels[] = new JLabel[10];
	JLabel correctSpotLabels[] = new JLabel[10];

	JLabel spotLabel = new JLabel("Spots");
	JLabel colorLabel = new JLabel("Colors");

	JButton displayBoard[][] = new JButton[10][4];

	Board myBoard;

	public swingGui() {
		// Adding Action Listeners
		stuffToHappenInConstructor();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() != single && e.getSource() != setUp)
			((JComponent) e.getSource()).setVisible(false);

		if (e.getSource() == redButton) {
			colorIt(myBoard.getR(), myBoard.getC(), 1);
			behindSceneUserArray(myBoard.getR(), myBoard.getC(), 1);
		}
		if (e.getSource() == blueButton) {
			colorIt(myBoard.getR(), myBoard.getC(), 2);
			behindSceneUserArray(myBoard.getR(), myBoard.getC(), 2);
		}
		if (e.getSource() == greenButton) {
			colorIt(myBoard.getR(), myBoard.getC(), 3);
			behindSceneUserArray(myBoard.getR(), myBoard.getC(), 3);
		}
		if (e.getSource() == yellowButton) {
			colorIt(myBoard.getR(), myBoard.getC(), 4);
			behindSceneUserArray(myBoard.getR(), myBoard.getC(), 4);
		}
		if (e.getSource() == cyanButton) {
			colorIt(myBoard.getR(), myBoard.getC(), 5);
			behindSceneUserArray(myBoard.getR(), myBoard.getC(), 5);
		}
		if (e.getSource() == pinkButton) {
			colorIt(myBoard.getR(), myBoard.getC(), 6);
			behindSceneUserArray(myBoard.getR(), myBoard.getC(), 6);

		}
		if (e.getSource() == single) {
			myBoard = new Board();
			myBoard.generateRandomAnswer();

			redButton.setEnabled(true);
			blueButton.setEnabled(true);
			greenButton.setEnabled(true);
			yellowButton.setEnabled(true);
			pinkButton.setEnabled(true);
			cyanButton.setEnabled(true);
			setUp.setEnabled(true);

			JOptionPane.showMessageDialog(frame,
					"Random Answer Generated, Good Luck!");
		}
		if (e.getSource() == setUp) {
			resetAll();
		}
	}

	public void setCorrectLabel(int r) {
		int count = 0;

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				if (userBoard[r][i] == answerBoard[j])
					count++;
		correctColorLabels[r].setText("" + count);
	}

	public void setSpotLabel(int r) {
		int count = 0;

		for (int i = 0; i < 4; i++)
			if (answerBoard[i] == userBoard[r][i])
				count++;
		correctSpotLabels[r].setText("" + count);
	}

	public void addButtons() {

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				displayBoard[i][j].setBackground(Color.WHITE);
				displayBoard[i][j].setEnabled(false);
				centerPanel.add(displayBoard[i][j]); // change to center
			}
		}
		southPanel.add(single);
		southPanel.add(setUp);
	}

	public void addLabels() {
		eastPanel.add(colorLabel);
		westPanel.add(spotLabel);

		for (int i = 0; i < 10; i++) {
			eastPanel.add(correctColorLabels[i]);
			westPanel.add(correctSpotLabels[i]);
			eastPanel.add(Box.createVerticalStrut(14));
			westPanel.add(Box.createVerticalStrut(14));
			eastPanel.add(Box.createHorizontalStrut(20));
			westPanel.add(Box.createHorizontalStrut(25));
		}

		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
	}

	public void setLabels(int spot, int color, int r) {
		correctColorLabels[r].setText(Integer.toString(color));
		correctSpotLabels[r].setText(Integer.toString(spot));
	}

	public void addBasicFunctionality() {
		frame.setVisible(true);
		frame.setSize(350, 450);
		frame.add(mainPanel);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void addPanelsToBorderLayout() {
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		mainPanel.add(eastPanel, BorderLayout.EAST);
		mainPanel.add(westPanel, BorderLayout.WEST);
	}

	public void behindSceneUserArray(int r, int c, int color) {
		myBoard.userBoard[r][c] = color;
		if (c == 3) {
			setLabels(myBoard.spotCorrect(r), myBoard.colorsCorrect(r), r);
			myBoard.colorsCorrect(r);
			myBoard.spotCorrect(r);
			if (myBoard.didYouWin(r) == true){
				JOptionPane.showMessageDialog(frame, "You Win");
				resetAll();
			}

			redButton.setVisible(true);
			blueButton.setVisible(true);
			greenButton.setVisible(true);
			yellowButton.setVisible(true);
			pinkButton.setVisible(true);
			cyanButton.setVisible(true);
		}
		myBoard.changeR();
		myBoard.changeC();
		
		if (myBoard.didYouWin(r) == false && myBoard.getR() == 10) {
			JOptionPane.showMessageDialog(frame, "You Lost! the answer was"+myBoard.toString());
			resetAll();
		}
	}

	public void colorIt(int r, int c, int color) {

		switch (color) {

		case 1:
			displayBoard[r][c].setBackground(Color.RED);
			break;
		case 2:
			displayBoard[r][c].setBackground(Color.BLUE);
			break;
		case 3:
			displayBoard[r][c].setBackground(Color.GREEN);
			break;
		case 4:
			displayBoard[r][c].setBackground(Color.YELLOW);
			break;
		case 5:
			displayBoard[r][c].setBackground(Color.CYAN);
			break;
		case 6:
			displayBoard[r][c].setBackground(Color.PINK);
			break;
		case 7:
			displayBoard[r][c].setBackground(Color.WHITE);
			break;
		}
	}

	public void stuffToHappenInConstructor() {
		// Adding Action Listeners
		redButton.addActionListener(this);
		blueButton.addActionListener(this);
		greenButton.addActionListener(this);
		yellowButton.addActionListener(this);
		cyanButton.addActionListener(this);
		pinkButton.addActionListener(this);

		redButton.setEnabled(false);
		blueButton.setEnabled(false);
		greenButton.setEnabled(false);
		yellowButton.setEnabled(false);
		pinkButton.setEnabled(false);
		cyanButton.setEnabled(false);
		
		setUp.setEnabled(false);
		
		single.addActionListener(this);
		setUp.addActionListener(this);

		redButton.setBackground(Color.RED);
		blueButton.setBackground(Color.BLUE);
		greenButton.setBackground(Color.GREEN);
		yellowButton.setBackground(Color.YELLOW);
		pinkButton.setBackground(Color.PINK);
		cyanButton.setBackground(Color.CYAN);

		// TEST
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));

		northPanel.add(redButton);
		northPanel.add(blueButton);
		northPanel.add(greenButton);
		northPanel.add(yellowButton);
		northPanel.add(cyanButton);
		northPanel.add(pinkButton);

		addPanelsToBorderLayout();
		addObjectsToArrays();
		addButtons();
		addLabels();
		addBasicFunctionality();
	}

	public void addObjectsToArrays() {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 4; j++)
				displayBoard[i][j] = new JButton(" ");
		for (int i = 0; i < 10; i++) {
			correctColorLabels[i] = new JLabel("#");
			correctSpotLabels[i] = new JLabel("#");
		}
	}

	public void resetAll() {

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 4; j++) {
				// centerPanel.add(displayBoard[i][j]); // change to center
				displayBoard[i][j].setBackground(Color.WHITE);
			}

		for (int i = 0; i < 10; i++) {
			correctColorLabels[i].setText("#");
			correctSpotLabels[i].setText("#");
		}

		myBoard.setC(0);
		myBoard.setR(0);

		redButton.setEnabled(false);
		blueButton.setEnabled(false);
		greenButton.setEnabled(false);
		yellowButton.setEnabled(false);
		pinkButton.setEnabled(false);
		cyanButton.setEnabled(false);

		setUp.setEnabled(false);
	}
}
