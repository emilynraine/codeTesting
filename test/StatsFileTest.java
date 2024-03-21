import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.StringReader;
import java.time.LocalDateTime;

public class StatsFileTest {
    //TESTING checkTime
    @Test
    public void testCheckTimeInTimeLimit() {
        String csvData = "2024-03-01T00:00:00,5\n" +
                "2024-03-15T00:00:00,5\n" +
                "2024-02-01T00:00:00,3\n";
        CSVReader csvReader = new CSVReader(new StringReader(csvData));
        StatsFile statsFile = new StatsFile(LocalDateTime.now().minusDays(30), csvReader);
        statsFile.checkTime(LocalDateTime.parse("2024-03-01T00:00:00"), LocalDateTime.now().minusDays(30), 5);
        statsFile.checkTime(LocalDateTime.parse("2024-03-15T00:00:00"), LocalDateTime.now().minusDays(30), 5);
        assertEquals(4, statsFile.numGames(5));
        assertEquals(0, statsFile.numGames(3));
    }

    @Test
    public void testCheckTimeBeforeTimeLimit() {
        String csvData = "2024-03-01T00:00:00,5\n" +
                "2024-03-15T00:00:00,5\n" +
                "2024-02-01T00:00:00,3\n";
        CSVReader csvReader = new CSVReader(new StringReader(csvData));
        StatsFile statsFile = new StatsFile(LocalDateTime.now().minusDays(30), csvReader);
        statsFile.checkTime(LocalDateTime.parse("2024-01-01T00:00:00"), LocalDateTime.now().minusDays(30), 5);
        statsFile.checkTime(LocalDateTime.parse("2024-02-01T00:00:00"), LocalDateTime.now().minusDays(30), 5);
        assertEquals(2, statsFile.numGames(5));
        assertEquals(0, statsFile.numGames(3));
    }

    @Test
    public void testCheckTimeAtTimeLimit() {
        String csvData = "2024-03-01T00:00:00,5\n" +
                "2024-03-15T00:00:00,5\n" +
                "2024-02-01T00:00:00,3\n";
        CSVReader csvReader = new CSVReader(new StringReader(csvData));
        StatsFile statsFile = new StatsFile(LocalDateTime.now().minusDays(30), csvReader);
        statsFile.checkTime(LocalDateTime.parse("2024-02-01T00:00:00"), LocalDateTime.now().minusDays(30), 5);
        statsFile.checkTime(LocalDateTime.parse("2024-03-01T00:00:00"), LocalDateTime.now().minusDays(30), 5);
        statsFile.checkTime(LocalDateTime.parse("2024-03-15T00:00:00"), LocalDateTime.now().minusDays(30), 5);
        assertEquals(4, statsFile.numGames(5));
        assertEquals(0, statsFile.numGames(3));
    }

    @Test
    public void testCheckTimeAfterTimeLimit() {
        String csvData = "2024-03-01T00:00:00,5\n" +
                "2024-03-15T00:00:00,5\n" +
                "2024-02-01T00:00:00,3\n";
        CSVReader csvReader = new CSVReader(new StringReader(csvData));
        StatsFile statsFile = new StatsFile(LocalDateTime.now().minusDays(30), csvReader);
        statsFile.checkTime(LocalDateTime.parse("2024-04-01T00:00:00"), LocalDateTime.now().minusDays(30), 5);
        assertEquals(3, statsFile.numGames(5));
        assertEquals(0, statsFile.numGames(3));
    }
}
