import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.StringWriter;
import com.opencsv.CSVWriter;


public class GameOverPanelTest {

    //TESTING setGameResults
    @Test
    public void testSetGameResultsHumanPlayTrue(){
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);
        GameResult result = new GameResult(true, 30, 5);
        gameOverPanel.setGameResults(result, csvWriter);
        assertEquals(true, panelContainsText(gameOverPanel, "The answer was " + result.correctValue + "."));
        assertEquals(true, panelContainsText(gameOverPanel, "It took you 5 guesses."));
    }

    @Test
    public void testSetGameResultsCompPlayTrue(){
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);
        GameResult result = new GameResult(false, 30, 5);
        gameOverPanel.setGameResults(result, csvWriter);
        assertEquals(true, panelContainsText(gameOverPanel, "The answer was " + result.correctValue + "."));
        assertEquals(true, panelContainsText(gameOverPanel, "It took me 5 guesses."));
    }

    @Test
    public void testSetGameResultsGivenEmptyCSV(){
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        GameResult result = new GameResult(false, 5, 3);
        gameOverPanel.setGameResults(result, null);
        assertEquals(true, panelContainsText(gameOverPanel, "The answer was " + result.correctValue + "."));
        assertEquals(true, panelContainsText(gameOverPanel, "It took you 3 guesses."));
    }

    //TESTING SetText
    @Test
    public void testSetTextFirstGuessRight(){
        GameResult result = new GameResult(true, 20, 1);
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        gameOverPanel.SetText(result);
        assertEquals(true, panelContainsText(gameOverPanel, "The answer was " + result.correctValue + "."));
        assertEquals(true, panelContainsText(gameOverPanel, "You guessed it on the first try!"));
    }

    @Test
    public void testSetTextFixedNumGuesses(){
        GameResult result = new GameResult(true, 32, 5);
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        gameOverPanel.SetText(result);
        assertEquals(true, panelContainsText(gameOverPanel, "The answer was " + result.correctValue + "."));
        assertEquals(true, panelContainsText(gameOverPanel, "It took you 5 guesses."));
    }

    @Test
    public void testSetTextNoGuesses(){
        GameResult result = new GameResult(true, 40, 0);
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        gameOverPanel.SetText(result);
        assertEquals(true, panelContainsText(gameOverPanel, "The answer was " + result.correctValue + "."));
        assertEquals(true, panelContainsText(gameOverPanel, "It took you 0 guesses."));
    }
    //helper func
    private boolean panelContainsText(GameOverPanel panel, String text) {
        return panel.toString().contains(text);
    }
}
