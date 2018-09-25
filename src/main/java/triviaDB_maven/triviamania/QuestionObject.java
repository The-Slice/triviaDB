package triviaDB_maven.triviamania;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.apache.commons.lang.StringEscapeUtils;

public class QuestionObject {
	private boolean Type;
	private String question;
	private ArrayList<String> Answers = new ArrayList<String>();
	private String correctAnswer;

	@Override
	public String toString() {
		return "QuestionObject [Type=" + Type + ", question=" + question + ", Answers=" + Answers + ", correctAnswer="
				+ correctAnswer + "]";
	}

	public QuestionObject() {

	}

	public boolean isType() {
		return Type;
	}

	public void setType(boolean type) {
		Type = type;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<String> getAnswers() {
		return Answers;
	}

	public void setAnswers(@SuppressWarnings("rawtypes") Iterator answers) {
		while (answers.hasNext()) {
			this.Answers.add(StringEscapeUtils.unescapeHtml((String) answers.next()));
		}
	}

	public void appendAnswer(String answer) {
		this.Answers.add(answer);
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public ArrayList<String> shuffleAnswers() {
		Collections.shuffle(Answers);
		return Answers;
	}

}
