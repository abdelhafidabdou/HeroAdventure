package fr.cleva.interview.adventurehero.service;

import fr.cleva.interview.adventurehero.exception.NotAutorisedMove;
import fr.cleva.interview.adventurehero.model.MoveDetails;
import fr.cleva.interview.adventurehero.model.Coordinate;
import fr.cleva.interview.adventurehero.model.Card;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CardService {

    public static final char CHAR = '#';
    public static final char LINE_BREAK = '\n';
    public static final String FILE_TO_INIT_CARD = "\\src\\main\\resources\\carte.txt";
    public static final char EST = 'E';
    public static final char OEST = 'O';
    public static final char NORD = 'N';
    public static final char SUD = 'S';
    public static final int LENGTH = 19;
    public static final int WIDTH = 19;
    public static final String IMPENETRABLE_MSG_EXCEPTION = "ce deplacement n'est pas autorise, bois impénétrables";
    public static final String OUT_OF_RANGE_MSG_EXCEPTION = "ce deplacement n'est pas autorise, hors de portee";
    public static final String USER_DIR = "user.dir";

    /***
     * read the map.txt file and creat the correspondant Card
     * @return Optional Of Card
     */
    public Optional<Card> readCard() {
        return Optional.ofNullable(readFileAndCreateCard(FILE_TO_INIT_CARD));
    }

    /***
     *
     *
     * @param moveDetails contains the initial position and the steps
     * @return the new position
     * @throws NotAutorisedMove when the steps pass by wood or out of range of the Card
     */
    public Optional<Coordinate> move (MoveDetails moveDetails) throws NotAutorisedMove {
        Card card = readCard().get();
        return Optional.of(calculateSteps(convertStringToCharList(moveDetails.getSteps()),card,moveDetails.getInitialCoordinates()));
    }


    /**
     * Excute all steps in the steps object and calculate the new position .
     * @param steps list of Steps ( 'N','S','E','O') of Type List<Character> .
     * @param card the card to move on .
     * @param coordinate initial coordinate of Type Coordinate
     * @return
     */
    public Coordinate calculateSteps(List<Character> steps,Card card,Coordinate coordinate){
        steps.forEach( elm ->{
                    switch (elm) {
                        case EST :  coordinate.incrementX();   break;
                        case OEST:  coordinate.decrementX();   break;
                        case NORD:  coordinate.decrementY();   break;
                        case SUD:  coordinate.incrementY();    break;
                    }
                    checkAutorisedMove(coordinate, card);
        });

        return coordinate;
    }


    /***
     * Convert a String to list of chars
     * @param steps of type String
     * @return List<Character>
     */
    public List<Character> convertStringToCharList(String steps) {
        return steps.chars() .mapToObj(e -> (char)e).collect(Collectors.toList());
    }


    /**
     * check if the Coordinate is in the card and its a allowed position
     * @param coordinate
     * @param card
     * @return true if the check secceed
     * @throws NotAutorisedMove
     */
    public boolean checkAutorisedMove(Coordinate coordinate, Card card) throws NotAutorisedMove{
        if(     coordinate.getX()>card.getWidth()
                || coordinate.getY() > card.getLength()
                || coordinate.getY() < 0
                || coordinate.getX() < 0 )
           throw new NotAutorisedMove(OUT_OF_RANGE_MSG_EXCEPTION);

        if(card.getOccupiedSquares().contains(coordinate))
            throw new NotAutorisedMove(IMPENETRABLE_MSG_EXCEPTION);
        System.out.println(card.getOccupiedSquares().contains(coordinate));
        return true;

    }


    /***
     * Read the intial carte.txt File and creat a Card object
     * @param fileName
     * @return Card
     */
    public Card readFileAndCreateCard(String fileName){
        int c = 0;
        int x = 0;
        int y = 0;
        String localDir = System.getProperty(USER_DIR);
        String relatifPath = localDir + "\\"+fileName;
        List<Coordinate> occupiedSquares = new ArrayList<>();

        File file ;
        FileReader fr =   null;
        BufferedReader br = null;

        try {
              file = new File(relatifPath);
              fr =   new FileReader(file);
              br = new BufferedReader(fr);
            while((c = br.read()) != -1)
            {
                char ch = (char) c;
                if (ch== CHAR){
                    occupiedSquares.add(new Coordinate(x,y));
                } x=x+1;
                if (ch== LINE_BREAK) {y=y+1; x = 0;}
            }
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            if( fr != null)
                try {
                    fr.close();
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return new Card(WIDTH,LENGTH,occupiedSquares);
    }


}


