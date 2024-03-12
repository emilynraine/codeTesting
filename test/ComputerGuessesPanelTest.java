import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComputerGuessesPanelTest {

    //TESTING HandleGuess

    @Test
    public void oneHigher() {
        ComputerGuessesPanel panel = new ComputerGuessesPanel(null, null);
        panel.HandleGuess(true);
        assertEquals(501, panel.getLowerBound());
        assertEquals(1000, panel.getUpperBound());
        assertEquals(500, panel.getLastGuess());
    }

    @Test
    public void oneLower() {
        ComputerGuessesPanel panel = new ComputerGuessesPanel(null, null);
        panel.HandleGuess(false);
        assertEquals(1, panel.getLowerBound());
        assertEquals(499, panel.getUpperBound());
        assertEquals(500, panel.getLastGuess());
    }

    @Test
    public void twoHigher() {
        ComputerGuessesPanel panel = new ComputerGuessesPanel(null, null);
        panel.HandleGuess(true);
        assertEquals(501, panel.getLowerBound());
        assertEquals(1000, panel.getUpperBound());
        assertEquals(500, panel.getLastGuess());

        panel.HandleGuess(true);
        assertEquals(751, panel.getLowerBound());
        assertEquals(1000, panel.getUpperBound());
        assertEquals(750, panel.getLastGuess());
    }

    @Test
    public void higherLower() {
        ComputerGuessesPanel panel = new ComputerGuessesPanel(null, null);
        panel.HandleGuess(true);
        assertEquals(501, panel.getLowerBound());
        assertEquals(1000, panel.getUpperBound());
        assertEquals(500, panel.getLastGuess());

        panel.HandleGuess(false);
        assertEquals(501, panel.getLowerBound());
        assertEquals(749, panel.getUpperBound());
        assertEquals(750, panel.getLastGuess());
    }

    @Test
    public void twoLower() {
        ComputerGuessesPanel panel = new ComputerGuessesPanel(null, null);
        panel.HandleGuess(false);
        assertEquals(1, panel.getLowerBound());
        assertEquals(499, panel.getUpperBound());
        assertEquals(500, panel.getLastGuess());

        panel.HandleGuess(false);
        assertEquals(1, panel.getLowerBound());
        assertEquals(249, panel.getUpperBound());
        assertEquals(250, panel.getLastGuess());
    }

    @Test
    public void testHandleGuess_UpperBoundReached() {
        ComputerGuessesPanel panel = new ComputerGuessesPanel(null, null);
        for(int i=0; i<12; i++) {
            panel.HandleGuess(true);
        }
        assertEquals(1000, panel.getLastGuess());
        assertEquals(1000, panel.getLowerBound());
        assertEquals(1000, panel.getUpperBound());
    }

    @Test
    public void testHandleGuess_LowerBoundReached() {
        ComputerGuessesPanel panel = new ComputerGuessesPanel(null, null);
        for(int i=0; i<12; i++) {
            panel.HandleGuess(false);
        }
        assertEquals(1, panel.getLastGuess());
        assertEquals(1, panel.getLowerBound());
        assertEquals(1, panel.getUpperBound());
    }
}
