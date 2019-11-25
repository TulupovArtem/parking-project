package test.task.parking.Parking;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    private int numberPlaces;
    private List<Place> places;

    public Parking(int numberPlaces) {
        this.numberPlaces = numberPlaces;
        this.places = new ArrayList<>(numberPlaces);
        for (int num = 1; num != numberPlaces + 1; num++) {
            this.places.add(new Place("A" + num));
        }
    }

    public int getNumberPlaces() {
        return numberPlaces;
    }

    public void setNumberPlaces(int numberPlaces) {
        this.numberPlaces = numberPlaces;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}
