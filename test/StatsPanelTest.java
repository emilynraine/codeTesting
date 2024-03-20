import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatsPanelTest {

    //TESTING calculateNumGames
    @Test
    public void testCalculateNumGamesLastBin() {
        StatsPanel statsPanel = new StatsPanel(null);
        int lowerBound = 12;
        int binIndex = StatsPanel.BIN_EDGES.length-1;

        assertEquals(9, statsPanel.calculateNumGames(lowerBound, binIndex));
        assertEquals(10, statsPanel.calculateNumGames(lowerBound + 1, binIndex));
    }

    @Test
    public void testCalculateNumGamesEmptyBin() {
        StatsPanel statsPanel = new StatsPanel(null);
        int lowerBound = 5;
        int binIndex = 1;

        assertEquals(0, statsPanel.calculateNumGames(lowerBound, binIndex));
    }

    @Test
    public void testCalculateNumGamesUpperBin() {
        StatsPanel statsPanel = new StatsPanel(null);
        int lowerBound = 15;
        int binIndex = 0;

        assertEquals(0, statsPanel.calculateNumGames(lowerBound, binIndex));
    }

    //TESTING updateResultsNums
    @Test
    public void testUpdateResultsNumsOutOfBounds() {
        StatsPanel statsPanel = new StatsPanel(null);
        int binIndex = StatsPanel.BIN_EDGES.length-1;

        assertEquals(0, statsPanel.updateResultsNums(binIndex));
    }

    @Test
    public void testUpdateResultsNumsFirstBin() {
        StatsPanel statsPanel = new StatsPanel(null);
        int[] testBins = {3, 5, 7, 4, 6};
        int binIndex = 0;

        assertEquals(testBins[binIndex], statsPanel.updateResultsNums(binIndex));
    }

    @Test
    public void testUpdateResultsNumsLastBin() {
        StatsPanel statsPanel = new StatsPanel(null);
        int[] testBins = {2, 6, 8, 3, 5};
        int binIndex = StatsPanel.BIN_EDGES.length-1;

        assertEquals(testBins[binIndex], statsPanel.updateResultsNums(binIndex));
    }

    @Test
    public void testUpdateResultsNumsNegBinIndex() {
        StatsPanel statsPanel = new StatsPanel(null);
        int binIndex = -1;

        assertEquals(0, statsPanel.updateResultsNums(binIndex));
    }

}







