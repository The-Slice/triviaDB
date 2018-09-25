package triviaDB_maven.triviamania;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class App {
	static final int amountQ = 25;
	static HttpResponse<JsonNode> triviaAccess;
	static int QuestionNumber = 0;
	static int score = 0;

	public static QuestionObject getNextQuestion() throws UnirestException {
		if (triviaAccess == null) {
			startGame();
		} else if (QuestionNumber == amountQ) {
			return null;
		}
		JSONObject Question = (JSONObject) triviaAccess.getBody().getObject().getJSONArray("results")
				.get(QuestionNumber);

		QuestionNumber++;
		return Parse(Question);

	}

	public static QuestionObject Parse(JSONObject Question) {
		QuestionObject qObject = new QuestionObject();

		qObject.setType(StringEscapeUtils.unescapeHtml(Question.getString("type")).equals("boolean"));
		qObject.setQuestion(StringEscapeUtils.unescapeHtml(Question.getString("question").trim()));
		qObject.setAnswers(Question.getJSONArray("incorrect_answers").iterator());
		qObject.appendAnswer(StringEscapeUtils.unescapeHtml(Question.getString("correct_answer")));
		qObject.setCorrectAnswer(StringEscapeUtils.unescapeHtml(Question.getString("correct_answer")));

		qObject.shuffleAnswers();

		return qObject;

	}

	public static void startGame() throws UnirestException {

		/*
		 * triviaAccess = Unirest.get("https://opentdb.com/api.php?amount=" +
		 * amountQ + "&type=boolean").asJson(); QuestionNumber = 0;
		 */

		triviaAccess = Unirest.get("https://opentdb.com/api.php?amount=" + amountQ).asJson();
		QuestionNumber = 0;
		score = 0;

	}

	public static void scoreAdd() {
		score++;
	}

	public static int getScore() {
		return score;
	}
}
