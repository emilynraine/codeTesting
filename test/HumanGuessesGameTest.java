import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class HumanGuessesGameTest {

    //TESTING GetRandom
    @Test
    public void testGetRandomValInRange(){
        HumanGuessesGame game = new HumanGuessesGame();
        assertEquals(true,game.GetRandom(new Random()) >= 1 && game.GetRandom(new Random()) <= HumanGuessesGame.UPPER_BOUND);
    }

    @Test
    public void testGetRandomEqualInitialVal() {
        HumanGuessesGame game1 = new HumanGuessesGame();
        HumanGuessesGame game2 = new HumanGuessesGame();
        assertEquals(game1.GetRandom(new Random(567)), game2.GetRandom(new Random(567)));
    }

    @Test
    public void testGetRandomNegInitialVal() {
        HumanGuessesGame game = new HumanGuessesGame();
        assertEquals(true,game.GetRandom(new Random(-567)) >= 1 && game.GetRandom(new Random(-567)) <= HumanGuessesGame.UPPER_BOUND);
    }

    @Test
    public void testGetRandomIfSameSeed() {
        HumanGuessesGame game = new HumanGuessesGame();
        assertEquals(246, game.GetRandom(new Random(567)));
    }
}
