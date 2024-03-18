import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatsPanelTest {

    //TESTING calculateNumGames
    @Test
    public void testCalculateNumGames() {
        StatsPanel statsPanel = new StatsPanel(null);

        int lowerBound = 1;
        int binIndex = 0;

        int result = statsPanel.calculateNumGames(lowerBound, binIndex);

        assertEquals(3, result);
    }

    //TESTING updateResultsNums
    @Test
    public void testUpdateResultsNums() {
        StatsPanel statsPanel = new StatsPanel(null);

        int[] testBins = {3, 5, 7, 4, 6};
        int binIndex = 0;

        int result = statsPanel.updateResultsNums(binIndex);

        assertEquals(testBins[binIndex], result);
    }

}







