import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatsPanelTest {

    //TESTING calculateNumGames
    @Test
    public void testCalculateNumGamesLargeLowerBound() {
        StatsPanel statsPanel = new StatsPanel(null);
        assertEquals(9, statsPanel.calculateNumGames(12, StatsPanel.BIN_EDGES.length-1));
        assertEquals(3, statsPanel.calculateNumGames(15, StatsPanel.BIN_EDGES.length-1));
    }

    @Test
    public void testCalculateNumGamesEmptyBin() {
        StatsPanel statsPanel = new StatsPanel(null);
        assertEquals(0, statsPanel.calculateNumGames(5, 1));
    }

    @Test
    public void testCalculateNumGamesHigherBinVal() {
        StatsPanel statsPanel = new StatsPanel(null);
        assertEquals(0, statsPanel.calculateNumGames(15, 0));
    }

    //TESTING updateResultsNums
    @Test
    public void testUpdateResultsNumsFirstBin() {
        StatsPanel statsPanel = new StatsPanel(null);
        assertEquals(3, statsPanel.updateResultsNums(0));
    }

    @Test
    public void testUpdateResultsNumsMiddleBin() {
        StatsPanel statsPanel = new StatsPanel(null);
        assertEquals(4, statsPanel.updateResultsNums(3));
    }

    @Test
    public void testUpdateResultsNumsLastBin() {
        StatsPanel statsPanel = new StatsPanel(null);
        assertEquals(5, statsPanel.updateResultsNums( StatsPanel.BIN_EDGES.length-1));
    }
}







