import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

public class HumanGuessesGameTest {

    //TESTING GetRandom
    @Test
    public void testGetRandom(){
        HumanGuessesGame game = new HumanGuessesGame();
        Random rand = new Random();

        int randNum = game.GetRandom(rand);
        assertEquals(true, randNum >= 1 && randNum <= HumanGuessesGame.UPPER_BOUND);
    }

}
