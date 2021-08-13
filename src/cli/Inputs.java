package cli;

import java.util.List;

public final class Inputs {
    private final String startingCity;
    private final String endingCity;
    private final List<String> attractions;

    public Inputs(String startingCity, String endingCity, List<String> attractions) {
        this.startingCity = startingCity;
        this.endingCity = endingCity;
        this.attractions = attractions;
    }

    public String getStartingCity() {
        return startingCity;
    }

    public String getEndingCity() {
        return endingCity;
    }

    public List<String> getAttractions() {
        return attractions;
    }
}
