import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.StringWriter;
import com.opencsv.CSVWriter;


public class GameOverPanelTest {

    //TESTING setGameResults
    @Test
    public void testSetGameResultsHumanPlay(){
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);
        GameResult result = new GameResult(true, 3, 42);

        gameOverPanel.setGameResults(result, csvWriter);

        assertTrue(panelContainsText(gameOverPanel, "The answer was " + result.correctValue + "."));
        assertTrue(panelContainsText(gameOverPanel, "It took you 3 guesses."));

    }

    @Test
    public void testSetGameResultsCompPlay(){
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);
        GameResult result = new GameResult(false, 3, 42);

        gameOverPanel.setGameResults(result, csvWriter);

        assertTrue(panelContainsText(gameOverPanel, "The answer was " + result.correctValue + "."));
        assertTrue(panelContainsText(gameOverPanel, "It took me 3 guesses."));

    }

    @Test
    public void testSetGameResultsEmptyCSV(){
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        GameResult result = new GameResult(false, 42, 3);

        gameOverPanel.setGameResults(result, null);

        assertTrue(panelContainsText(gameOverPanel, "The answer was " + result.correctValue + "."));
        assertTrue(panelContainsText(gameOverPanel, "It took you 3 guesses."));

    }

    //TESTING SetText
    @Test
    public void testSetTextFirstGuess(){
        GameResult result = new GameResult(true, 42, 1);
        GameOverPanel gameOverPanel = new GameOverPanel(null);

        gameOverPanel.SetText(result);

        assertTrue(panelContainsText(gameOverPanel, "The answer was " + result.correctValue + "."));
        assertTrue(panelContainsText(gameOverPanel, "You guessed it on the first try!"));
    }

    @Test
    public void testSetTextGuesses(){
        GameResult result = new GameResult(true, 42, 5);
        GameOverPanel gameOverPanel = new GameOverPanel(null);

        gameOverPanel.SetText(result);

        assertTrue(panelContainsText(gameOverPanel, "The answer was " + result.correctValue + "."));
        assertTrue(panelContainsText(gameOverPanel, "It took you 5 guesses."));
    }

    @Test
    public void testSetTextNoGuesses(){
        GameResult result = new GameResult(true, 42, 0);
        GameOverPanel gameOverPanel = new GameOverPanel(null);

        gameOverPanel.SetText(result);

        assertTrue(panelContainsText(gameOverPanel, "The answer was " + result.correctValue + "."));
        assertTrue(panelContainsText(gameOverPanel, "It took you 0 guesses."));
    }

    //helper func
    private boolean panelContainsText(GameOverPanel panel, String text) {
        return panel.toString().contains(text);
    }

}
