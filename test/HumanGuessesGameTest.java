import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class HumanGuessesGameTest {

    //TESTING GetRandom
    @Test
    public void testGetRandomInRange(){
        HumanGuessesGame game = new HumanGuessesGame();
        Random rand = new Random();

        assertTrue(game.GetRandom(rand) >= 1 && game.GetRandom(rand) <= HumanGuessesGame.UPPER_BOUND);
    }

    @Test
    public void testGetRandomWithSameStartPt() {
        HumanGuessesGame game1 = new HumanGuessesGame();
        HumanGuessesGame game2 = new HumanGuessesGame();

        assertEquals(game1.GetRandom(new Random(123)), game2.GetRandom(new Random(123)));
    }

    @Test
    public void testGetRandomWithNegStartPt() {
        HumanGuessesGame game = new HumanGuessesGame();

        assertTrue(game.GetRandom(new Random(-123)) >= 1 && game.GetRandom(new Random(-123)) <= HumanGuessesGame.UPPER_BOUND);
    }

    @Test
    public void testGetRandomReturnValue() {
        HumanGuessesGame game = new HumanGuessesGame();

        assertEquals(true, game.GetRandom(new Random()) >= 1 && game.GetRandom(new Random()) <= HumanGuessesGame.UPPER_BOUND);
        assertEquals(346, game.GetRandom(new Random(123)));
    }

}
