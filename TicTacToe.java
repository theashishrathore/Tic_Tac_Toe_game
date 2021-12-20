package com.nvtech.practice.TicTacToe;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToe implements ActionListener {

	private int again = 1000, xOrOCount = 0;
	private boolean win = false;
	private String letter; 
	private int[][] winCombinationMatrix;
	private JButton[] buttons;

	public TicTacToe() {
		letter = " ";
		winCombinationMatrix = new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 },
				{ 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };
		buttons = new JButton[9];
	}

	public void createGamePanel() {
		JFrame frame = new JFrame("Tic Tac Toe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 3));
		panel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
		panel.setBackground(Color.red);

		for (int i = 0; i <= 8; i++) {
			buttons[i] = new JButton();
			buttons[i].setText(" ");
			buttons[i].setFont(new Font("Dialog", 1, 60));
			buttons[i].setBackground(Color.white);
			buttons[i].addActionListener(this);
			panel.add(buttons[i]);
		}
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(500, 500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		String text = button.getText();
		System.out.println("============" + text);

		if ((xOrOCount % 2) == 0 && text.equals(" ") && win == false) {
			letter = "X";
			xOrOCount = xOrOCount + 1;
			System.out.println(letter + "\n" + xOrOCount);
		} else if ((xOrOCount % 2) == 1 && text.equals(" ") && win == false) {
			letter = "O";
			xOrOCount = xOrOCount + 1;
			System.out.println(letter + "\n" + xOrOCount);
		}
		button.setText(letter);
		for (int i = 0; i <= 7; i++) {
			if (buttons[winCombinationMatrix[i][0]].getText().equals(buttons[winCombinationMatrix[i][1]].getText())

					&& buttons[winCombinationMatrix[i][1]].getText()
							.equals(buttons[winCombinationMatrix[i][2]].getText())
					&& buttons[winCombinationMatrix[i][0]].getText() != " ") {
				buttons[winCombinationMatrix[i][0]].setBackground(Color.red);
				buttons[winCombinationMatrix[i][1]].setBackground(Color.red);
				buttons[winCombinationMatrix[i][2]].setBackground(Color.red);
				win = true;
			}
		}
		showConfirmDialog();
	}

	private void showConfirmDialog() {
		if (win == true) {
			again = JOptionPane.showConfirmDialog(null, letter + " wins the game!  Do you want to play again?",
					letter + "won!", JOptionPane.YES_NO_OPTION);
		} else if (xOrOCount == 9 && win == false) {
			again = JOptionPane.showConfirmDialog(null, "The game was tie!  Do you want to play again?", "Tie game!",
					JOptionPane.YES_NO_OPTION);
			win = true;
		}
		if (again == JOptionPane.YES_OPTION && win == true) {
			clearButtons();
			win = false;
		} else if (again == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}

	private void clearButtons() {
		for (int i = 0; i <= 8; i++) {
			buttons[i].setText(" ");
			buttons[i].setBackground(Color.white);
		}
		xOrOCount = 0;
	}
}