import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.StringWriter;
import com.opencsv.CSVWriter;


public class GameOverPanelTest {

    //TESTING setGameResults
    @Test
    public void testSetGameResults(){
        GameOverPanel gameOverPanel = new GameOverPanel(null);
        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);
        GameResult result = new GameResult(true, 3, 42);

        gameOverPanel.setGameResults(result, csvWriter);

        assertTrue(panelContainsText(gameOverPanel, "The answer was " + result.correctValue + "."));
        assertTrue(panelContainsText(gameOverPanel, "It took you 5 guesses."));

    }

    //TESTING SetText
    @Test
    public void testSetText(){
        GameResult result = new GameResult(true, 42, 3);
        GameOverPanel panel = new GameOverPanel(null);
        GameOverPanel gameOverPanel = new GameOverPanel(null);

        gameOverPanel.SetText(result);

        assertTrue(panelContainsText(gameOverPanel, "The answer was " + result.correctValue + "."));
        assertTrue(panelContainsText(gameOverPanel, "It took you 3 guesses."));

    }

    //helper func
    private boolean panelContainsText(GameOverPanel panel, String text) {
        return panel.toString().contains(text);
    }

}
