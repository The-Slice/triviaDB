package triviaDB_maven.triviamania;

import javax.swing.JFrame;

import com.mashape.unirest.http.exceptions.UnirestException;

@SuppressWarnings("serial")
public class TriviaFrame extends JFrame {
	FrameTF pane1 = new FrameTF();

	public TriviaFrame() throws UnirestException {
		// set the title do the frame has
		setTitle("Balls to the Walls Trivia");
		// create a TTTPanel

		pane1.LoadNextQuestion(App.getNextQuestion());

		// add the Panel to the Frame

		this.add(pane1);

		pack();
	}

}
