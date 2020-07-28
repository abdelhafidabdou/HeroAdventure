package fr.cleva.interview.adventurehero.service;
import fr.cleva.interview.adventurehero.exception.NotAutorisedMove;
import fr.cleva.interview.adventurehero.model.Coordinate;
import fr.cleva.interview.adventurehero.model.MoveDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;


@ActiveProfiles("test")
@SpringBootTest
class CardServiceTest {


    @Autowired
    CardService cardService;

    @Test
    void shouldReadCard() {

        cardService.readCard();
        assertNotNull(cardService.readCard().get());
        assertNotNull(cardService.readCard().get().getOccupiedSquares());
        assertEquals(cardService.readCard().get().getLength(),19);
        assertEquals(cardService.readCard().get().getWidth(),19);
        assertTrue(cardService.readCard().get().getOccupiedSquares().size()>0);
    }

    @Test
    void shouldMove() {
        Coordinate init = new Coordinate(3,0);
        String steps = "SS";
        MoveDetails moveDetails = new MoveDetails(init,steps);
        Coordinate result = cardService.move(moveDetails).get();
        assertEquals(result.getX(),3);
        assertEquals(result.getY(),2);
    }

    @Test
    void shouldNotMoveAndRiseException() {
        Coordinate init = new Coordinate(3,0);
        String steps = "OOOO";
        MoveDetails moveDetails = new MoveDetails(init,steps);
         assertThatThrownBy(()->{ cardService.move(moveDetails);}).isInstanceOf(NotAutorisedMove.class);

    }

    @Test
    void shouldNotMoveAndRiseExceptionOutOfRange() {
        Coordinate init = new Coordinate(3,0);
        String steps = "OO";
        MoveDetails moveDetails = new MoveDetails(init,steps);
        assertThatThrownBy(()->{ cardService.move(moveDetails);}).isInstanceOf(NotAutorisedMove.class);

    }

    @Test
    void shouldCalculateSteps() {
        Coordinate init = new Coordinate(3,0);
        Coordinate ExpectedResult = new Coordinate(3,1);
        List<Character> steps = Arrays.asList('S');
        assertEquals(cardService.calculateSteps(steps,cardService.readCard().get(),init ),ExpectedResult);
    }



}
