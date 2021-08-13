package driver;

import cli.CommandLine;
import cli.Inputs;
import data.DataReader;
import graph.Edge;
import graph.Graph;
import graph.Solver;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        DataReader dr = new DataReader();
        HashMap<String, String> attractions = dr.readAttractions("data/attractions.csv");
        HashMap<String, Edge<String>> roads = dr.readRoads("data/roads.csv");
        Graph<String, Edge<String>> graph = new Graph<>();
        List<String> cities = new ArrayList<>();
        for (Map.Entry<String, Edge<String>> road: roads.entrySet()) {
            Edge<String> edge = road.getValue();
            graph.addEdge(edge.getVertex1(), edge.getVertex2(), edge.getWeight());
            String city1 = road.getValue().getVertex1();
            String city2 = road.getValue().getVertex2();
            if (!cities.contains(city1)) {
                cities.add(city1);
            }
            if (cities.contains(city2)) {
                cities.add(city2);
            }
        }

        CommandLine cr = new CommandLine(Set.copyOf(cities), attractions.keySet());
        Inputs inputs = cr.readInputs();
        if (inputs == null) {
            return;
        }
        List<String> totalPath = new ArrayList<>();

        List<String> citiesToVisit = new ArrayList<>();
        for (String attraction : inputs.getAttractions()) {
            citiesToVisit.add(attractions.get(attraction));
        }
        citiesToVisit.add(inputs.getEndingCity());

        String source = inputs.getStartingCity();
        for (String destination : citiesToVisit) {
            Solver<String> solver = new Solver<>(graph, source, destination);
            List<String> solution = solver.getShortestPath();
            totalPath.addAll(solution);
            source = destination;
        }

        System.out.println("Path traveled: " + totalPath);

//        String source = "San Francisco CA";
//        String target = "Houston TX";
//        String attraction = "Iowa State Fair";
//        String attractionCity = attractions.get(attraction);
//        System.out.println(attractionCity);
//        Solver<String> solver1 = new Solver<>(graph, source, attractionCity);
//        Solver<String> solver2 = new Solver<>(graph, attractionCity, target);
//        List<String> path1 = solver1.getShortestPath();
//        List<String> path2 = solver2.getShortestPath();
//        System.out.println("Path traveled= " + path1 + path2);
//        System.out.println("Distance traveled= " + (solver1.getDistance() + solver2.getDistance()) + " miles");
    }
}
