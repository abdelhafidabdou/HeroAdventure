package fr.cleva.interview.adventurehero.model;

public class MoveDetails {

     Coordinate initialCoordinates;
     String steps;

    public MoveDetails() {
    }

    public MoveDetails(Coordinate initialcoordinates, String steps) {
        this.initialCoordinates = initialcoordinates;
        this.steps = steps;
    }

    public Coordinate getInitialCoordinates() {
        return initialCoordinates;
    }

    public void setInitialCoordinates(Coordinate initialCoordinates) {
        this.initialCoordinates = initialCoordinates;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "MoveDetails{" +
                "initialCoordinates=" + initialCoordinates +
                ", steps='" + steps + '\'' +
                '}';
    }
}



