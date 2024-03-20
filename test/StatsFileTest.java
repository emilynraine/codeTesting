import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.StringReader;
import java.time.LocalDateTime;

public class StatsFileTest {
    //TESTING checkTime
    @Test
    public void testCheckTimeInTimeLimit() {
        LocalDateTime limit = LocalDateTime.now().minusDays(30);

        String csvData = "2024-03-01T00:00:00,5\n" +
                "2024-03-15T00:00:00,5\n" +
                "2024-02-01T00:00:00,3\n";

        CSVReader csvReader = new CSVReader(new StringReader(csvData));

        StatsFile statsFile = new StatsFile(limit, csvReader);

        statsFile.checkTime(LocalDateTime.parse("2024-03-01T00:00:00"), limit, 5);
        statsFile.checkTime(LocalDateTime.parse("2024-03-15T00:00:00"), limit, 5);
        statsFile.checkTime(LocalDateTime.parse("2024-02-01T00:00:00"), limit, 3);

        assertEquals(2, statsFile.numGames(5));
        assertEquals(0, statsFile.numGames(3));

    }

    @Test
    public void testCheckTimeBeforeTimeLimit() {
        LocalDateTime limit = LocalDateTime.now().minusDays(30);

        String csvData = "2024-03-01T00:00:00,5\n" +
                "2024-03-15T00:00:00,5\n" +
                "2024-02-01T00:00:00,3\n";

        CSVReader csvReader = new CSVReader(new StringReader(csvData));

        StatsFile statsFile = new StatsFile(limit, csvReader);

        statsFile.checkTime(LocalDateTime.parse("2024-01-01T00:00:00"), limit, 5);
        statsFile.checkTime(LocalDateTime.parse("2024-02-01T00:00:00"), limit, 5);

        assertEquals(2, statsFile.numGames(5));
        assertEquals(0, statsFile.numGames(3));
    }

    @Test
    public void testCheckTimeAtTimeLimit() {
        LocalDateTime limit = LocalDateTime.now().minusDays(30);

        String csvData = "2024-03-01T00:00:00,5\n" +
                "2024-03-15T00:00:00,5\n" +
                "2024-02-01T00:00:00,3\n";

        CSVReader csvReader = new CSVReader(new StringReader(csvData));

        StatsFile statsFile = new StatsFile(limit, csvReader);

        statsFile.checkTime(LocalDateTime.parse("2024-02-01T00:00:00"), limit, 5);
        statsFile.checkTime(LocalDateTime.parse("2024-03-01T00:00:00"), limit, 5);
        statsFile.checkTime(LocalDateTime.parse("2024-03-15T00:00:00"), limit, 5);

        assertEquals(4, statsFile.numGames(5));
        assertEquals(3, statsFile.numGames(5));
    }

    @Test
    public void testCheckTimeAfterTimeLimit() {
        LocalDateTime limit = LocalDateTime.now().minusDays(30);

        String csvData = "2024-03-01T00:00:00,5\n" +
                "2024-03-15T00:00:00,5\n" +
                "2024-02-01T00:00:00,3\n";

        CSVReader csvReader = new CSVReader(new StringReader(csvData));

        StatsFile statsFile = new StatsFile(limit, csvReader);

        statsFile.checkTime(LocalDateTime.parse("2024-04-01T00:00:00"), limit, 5);

        assertEquals(3, statsFile.numGames(5));
        assertEquals(0, statsFile.numGames(5));
    }

}
