import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class HumanGuessesGameTest {

    //TESTING GetRandom
    @Test
    public void testGetRandomValInRange(){
        HumanGuessesGame game = new HumanGuessesGame();
        Random rand = new RandomStub(1, 1);
        int result = game.GetRandom(rand);
        assertEquals(2, result);
    }

    @Test
    public void testGetRandomNumLowerBoundHigh() {
        HumanGuessesGame game = new HumanGuessesGame();
        Random rand = new RandomStub(1000, 1);
        int result = game.GetRandom(rand);
        assertEquals(1001, result);
    }

    @Test
    public void testGetRandomLowerBoundLower() {
        HumanGuessesGame game = new HumanGuessesGame();
        Random rand = new RandomStub(500, 1000);
        int result = game.GetRandom(rand);
        assertEquals(501, result);
    }
}
