package driver;

import cli.CommandLine;
import cli.Inputs;
import solution.TripPlanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        TripPlanner tripPlanner = new TripPlanner("data/attractions.csv", "data/roads.csv");
        CommandLine commandLine = new CommandLine(tripPlanner.getAttractions(), tripPlanner.getCities());
        Inputs inputs;
        do {
            inputs = commandLine.readInputs();
            if (inputs == null)
                break;
            List<String> route = tripPlanner.route(inputs.getStartingCity(), inputs.getEndingCity(), inputs.getAttractions());
            tripPlanner.printRoute(route);
        } while (inputs != null);
        commandLine.close();
    }
}
