package triviaDB_maven.triviamania;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.mashape.unirest.http.exceptions.UnirestException;

@SuppressWarnings("serial")
public class FrameTF extends JPanel {

	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();
	private JPanel test = new JPanel();
	private JTextArea Question = new JTextArea();
	private JLabel Display = new JLabel();
	private JButton answerTrue = new JButton();
	private JButton answerFalse = new JButton();
	private JButton answer3 = new JButton();
	private JButton answer4 = new JButton();
	private JButton nextQuestion = new JButton();
	@SuppressWarnings("unused")
	private String editQuestion;

	public FrameTF() {

		this.setLayout(new BorderLayout());
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		Question.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(50, 50, 50, 50)));
		
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new GridLayout(2, 2, 25, 25));
		test.setLayout(new GridLayout(3, 1, 25, 25));
		Display.setVisible(false);
		Question.setEditable(false);
		nextQuestion.setVisible(false);
		answerTrue.setVisible(true);
		answerFalse.setVisible(true);
		Question.setLineWrap(true);
		Question.setWrapStyleWord(true);
		nextQuestion.setText("Next!");

		add(panel1, BorderLayout.NORTH);
		add(test, BorderLayout.SOUTH);
		panel1.add(Question);
		test.add(panel2);
		test.add(panel3);
		test.add(panel4);
		panel2.add(answerTrue);
		panel2.add(answerFalse);
		panel2.add(answer3);
		panel2.add(answer4);
		panel3.add(Display);
		panel4.add(nextQuestion);

	}

	public void LoadNextQuestion(final QuestionObject qObject) {

		Question.setText(editQuestion = qObject.getQuestion());

		final ActionListener actionAll = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (qObject.getCorrectAnswer().equals(((JButton) e.getSource()).getText())) {
					Display.setVisible(true);
					Display.setText("You are Correct!");
					App.scoreAdd();
					answerTrue.setEnabled(false);
					answerFalse.setEnabled(false);
					answer3.setEnabled(false);
					answer4.setEnabled(false);
					nextQuestion.setVisible(true);
				} else {
					Display.setVisible(true);
					Display.setText("You are Wrong! The answer was " + qObject.getCorrectAnswer() + "!");
					answerTrue.setEnabled(false);
					answerFalse.setEnabled(false);
					answer3.setEnabled(false);
					answer4.setEnabled(false);
					nextQuestion.setVisible(true);
				}
			}
		};

		final ActionListener actionTrue = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (qObject.getCorrectAnswer().equals("True")) {
					Display.setVisible(true);
					Display.setText("You are Correct!");
					App.scoreAdd();
					answerTrue.setEnabled(false);
					answerFalse.setEnabled(false);
					nextQuestion.setVisible(true);
				} else {
					Display.setVisible(true);
					Display.setText("You are Wrong! The answer was False!");
					answerTrue.setEnabled(false);
					answerFalse.setEnabled(false);
					nextQuestion.setVisible(true);
				}
			}
		};

		final ActionListener actionFalse = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (qObject.getCorrectAnswer().equals("False")) {
					Display.setVisible(true);
					Display.setText("You are Correct!");
					App.scoreAdd();
					answerTrue.setEnabled(false);
					answerFalse.setEnabled(false);
					nextQuestion.setVisible(true);
				} else {
					Display.setVisible(true);
					Display.setText("You are Wrong! The answer was True!");
					answerTrue.setEnabled(false);
					answerFalse.setEnabled(false);
					nextQuestion.setVisible(true);
				}
			}
		};

		answer3.addActionListener(actionAll);
		answer4.addActionListener(actionAll);

		if (qObject.isType()) {
			answerTrue.addActionListener(actionTrue);
			answerFalse.addActionListener(actionFalse);
			answerTrue.setText("True");
			answerFalse.setText("False");
			answer3.setVisible(false);
			answer4.setVisible(false);
			answerTrue.setEnabled(true);
			answerFalse.setEnabled(true);

			answerTrue.removeActionListener(actionAll);
			answerFalse.removeActionListener(actionAll);

		} else {
			answerTrue.addActionListener(actionAll);
			answerFalse.addActionListener(actionAll);
			answerTrue.setEnabled(true);
			answerFalse.setEnabled(true);
			answer3.setEnabled(true);
			answer4.setEnabled(true);
			answer3.setVisible(true);
			answer4.setVisible(true);
			answerTrue.setText(qObject.getAnswers().get(0));
			answerFalse.setText(qObject.getAnswers().get(1));
			answer3.setText(qObject.getAnswers().get(2));
			answer4.setText(qObject.getAnswers().get(3));
		}

		final ActionListener actionNext = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Display.setVisible(false);
				Display.setText("");
				nextQuestion.setVisible(false);
				answerTrue.removeActionListener(actionAll);
				answerFalse.removeActionListener(actionAll);
				answer3.removeActionListener(actionAll);
				answer4.removeActionListener(actionAll);
				answerTrue.removeActionListener(actionTrue);
				answerFalse.removeActionListener(actionFalse);

				try {
					QuestionObject qEnd = App.getNextQuestion();
					if (qEnd == null) {
						JOptionPane.showMessageDialog(panel1,
								"      Your Score, Great Job! \n" + "                          " + App.getScore());
						System.exit(0);
					}
					LoadNextQuestion(qEnd);
					nextQuestion.removeActionListener(this);
				} catch (UnirestException e1) {

					e1.printStackTrace();
				}

			}

		};
		nextQuestion.addActionListener(actionNext);
	}

}
