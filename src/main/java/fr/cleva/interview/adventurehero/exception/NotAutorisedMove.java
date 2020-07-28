package fr.cleva.interview.adventurehero.exception;

public class NotAutorisedMove extends RuntimeException{
    public NotAutorisedMove() {
        super();
    }

    public NotAutorisedMove(String s) {
        super(s);
    }
}
