package fr.cleva.interview.adventurehero.model;

import java.util.List;

public class Card {

    private int  width;
    private int length;
    private List<Coordinate> occupiedSquares;

    public Card() {
    }

    public Card(int width, int length, List<Coordinate> occupiedSquares) {
        this.width = width;
        this.length = length;
        this.occupiedSquares = occupiedSquares;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Coordinate> getOccupiedSquares() {
        return occupiedSquares;
    }

    public void setOccupiedSquares(List<Coordinate> occupiedSquares) {
        this.occupiedSquares = occupiedSquares;
    }
}
