package triviaDB_maven.triviamania;

import javax.swing.JFrame;

import com.mashape.unirest.http.exceptions.UnirestException;

public class TriviaDB {

	public static void main(String[] args) throws UnirestException {

		TriviaFrame tp = new TriviaFrame();
		tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tp.setSize(500, 500);
		tp.setVisible(true);

	}
}
