import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.StringWriter;
import java.time.LocalDateTime;

import com.opencsv.CSVWriter;


public class GameOverPanelTest {

    //TESTING setGameResults
    @Test
    public void testSetGameResultsHumanPlayTrue(){
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);
        GameResult result = new GameResult(true, 30, 5);
        String time = "0";
        gameOverPanel.setGameResults(result, time, csvWriter);
        assertEquals("\"" + time + "\",\"5\"\n", stringWriter.toString());
    }

    @Test
    public void testSetGameResultsCompPlayTrue(){
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);
        GameResult result = new GameResult(false, 30, 5);
        String time = "0";
        gameOverPanel.setGameResults(result, time, csvWriter);
        assertEquals("", stringWriter.toString());
    }

    @Test
    public void testSetGameResultsFirstGuessRight(){
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);
        GameResult result = new GameResult(true, 1000, 1);
        String time = "0";
        gameOverPanel.setGameResults(result, time, csvWriter);
        assertEquals("\"" + time + "\",\"1\"\n", stringWriter.toString());
    }

    //TESTING SetText
    @Test
    public void testSetTextFirstGuessRight(){
        GameResult result = new GameResult(true, 20, 1);
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        gameOverPanel.SetText(result);
        assertEquals(gameOverPanel.answerTxt.getText(), "The answer was " + result.correctValue + ".");
        assertEquals(gameOverPanel.numGuessesTxt.getText(), "You guessed it on the first try!");
    }

    @Test
    public void testSetTextFixedNumGuesses(){
        GameResult result = new GameResult(true, 32, 5);
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        gameOverPanel.SetText(result);
        assertEquals(gameOverPanel.answerTxt.getText(), "The answer was " + result.correctValue + ".");
        assertEquals(gameOverPanel.numGuessesTxt.getText(), "It took you 5 guesses.");
    }

    @Test
    public void testSetTextNoGuesses(){
        GameResult result = new GameResult(true, 40, 0);
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        gameOverPanel.SetText(result);
        assertEquals(gameOverPanel.answerTxt.getText(), "The answer was " + result.correctValue + ".");
        assertEquals(gameOverPanel.numGuessesTxt.getText(), "It took you 0 guesses.");
    }

}
