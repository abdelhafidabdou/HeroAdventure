package fr.cleva.interview.adventurehero.model;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate() {
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void incrementX() {
        this.x = this.x+1;
    }
    public void incrementY() {
        this.y = this.y+1;
    }

    public void decrementX() {
        this.x = this.x-1;
    }
    public void decrementY() {
        this.y = this.y-1;
    }


    @Override
    public boolean equals(Object obj) {
        Coordinate coordinate =   (Coordinate)obj;
        return this.getX()== coordinate.getX() && this.getY() == coordinate.getY();
    }

    @Override
    public String toString() {
        return  "Coordinate { "+x+", "+y+" }";
    }
}
