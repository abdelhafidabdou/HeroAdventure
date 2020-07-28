package fr.cleva.interview.adventurehero.rest;
import fr.cleva.interview.adventurehero.exception.NotAutorisedMove;
import fr.cleva.interview.adventurehero.model.Coordinate;
import fr.cleva.interview.adventurehero.model.MoveDetails;
import fr.cleva.interview.adventurehero.service.CardService;
import fr.cleva.interview.adventurehero.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;


@RestController
@RequestMapping("api/card")
public class CardRestController {

    private   CardService cardService;

    @Autowired
    public CardRestController(CardService cardService) {this.cardService = cardService;}


    @CrossOrigin
    @GetMapping
    public ResponseEntity<Card> getCard() {
        Optional<Card> card = cardService.readCard();
        return card.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @CrossOrigin
    @PostMapping(value = "/move")
    public ResponseEntity<Coordinate> move(@RequestBody MoveDetails move)   {
        try {
        return cardService.move(move).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    } catch (NotAutorisedMove   e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e );
     }catch (Exception e) {
        e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e );
    }

}



}
