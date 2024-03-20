import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatsPanelTest {

    //TESTING calculateNumGames
    @Test
    public void testCalculateNumGamesLargeLowerBound() {
        StatsPanel statsPanel = new StatsPanel(null);
        assertEquals(9, statsPanel.calculateNumGames(12, StatsPanel.BIN_EDGES.length-1));
        assertEquals(10, statsPanel.calculateNumGames(13, StatsPanel.BIN_EDGES.length-1));
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
    public void testUpdateResultsNumsOutOfBoundsBin() {
        StatsPanel statsPanel = new StatsPanel(null);
        assertEquals(0, statsPanel.updateResultsNums(StatsPanel.BIN_EDGES.length-1));
    }

    @Test
    public void testUpdateResultsNumsTestBinOne() {
        StatsPanel statsPanel = new StatsPanel(null);
        int[] testBins = {3, 5, 7, 4, 6};
        assertEquals(testBins[0], statsPanel.updateResultsNums(0));
    }

    @Test
    public void testUpdateResultsNumsTestBinTwo() {
        StatsPanel statsPanel = new StatsPanel(null);
        int[] testBins = {2, 6, 8, 3, 5};
        assertEquals(testBins[StatsPanel.BIN_EDGES.length-1], statsPanel.updateResultsNums( StatsPanel.BIN_EDGES.length-1));
    }

    @Test
    public void testUpdateResultsNumsNegBinIndex() {
        StatsPanel statsPanel = new StatsPanel(null);
        assertEquals(0, statsPanel.updateResultsNums(-1));
    }
}







