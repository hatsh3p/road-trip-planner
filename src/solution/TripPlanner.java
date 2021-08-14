package solution;

import data.DataReader;
import graph.DijkstraSolver;
import graph.Edge;
import graph.Graph;

import java.util.*;

public class TripPlanner {
    private final Map<String, String> attractionToCity;
    private final Set<String> attractions;
    private final Set<String> cities;
    private final Map<String, Edge<String>> roads;
    private final Graph<String, Edge<String>> graph;

    private int distance;

    public TripPlanner(String attractionsFilename, String roadsFilename) {
        DataReader dr = new DataReader();
        this.attractionToCity = dr.readAttractions(attractionsFilename);
        this.roads = dr.readRoads(roadsFilename);
        this.attractions = attractionToCity.keySet();
        this.cities = new HashSet<>();
        this.graph = new Graph<>();
        fillGraph();
    }

    public List<String> route(String start, String end, List<String> attractions) {
        distance = 0;
        List<String> route = new ArrayList<>();
        List<String> stops = convertAttractionsToCities(attractions);
        List<String> sortedStops = sortStops(start, end, stops);
        sortedStops.add(0, start);
        sortedStops.add(sortedStops.size(), end);
        for (String city: sortedStops) {
            DijkstraSolver<String> dijkstraSolver = new DijkstraSolver<>(graph, start, city);
            List<String> solution = dijkstraSolver.getShortestPath();
            distance += dijkstraSolver.getDistance();
            route.addAll(solution);
            start = city;
        }
        route = removeDuplicates(route);
        return route;
    }

    private void fillGraph() {
        for (Map.Entry<String, Edge<String>> road: roads.entrySet()) {
            Edge<String> edge = road.getValue();
            graph.addEdge(edge.getVertex1(), edge.getVertex2(), edge.getWeight());
            String city1 = road.getValue().getVertex1();
            String city2 = road.getValue().getVertex2();
            cities.add(city1);
            cities.add(city2);
        }
    }

    private List<String> convertAttractionsToCities(List<String> attractions) {
        List<String> cities = new ArrayList<>();
        for (String attraction: attractions)
            cities.add(attractionToCity.get(attraction));
        return cities;
    }

    private List<String> sortStops(String start, String end, List<String> stops) { //sort based on distance from start
        Map<Integer, String> costFromStartToStop = new HashMap<>();
        DijkstraSolver<String> dijkstraSolver = new DijkstraSolver<>(graph, start, end);
        for (String stop : stops) {
            Integer cost = dijkstraSolver.getCostFromSource(stop);
            costFromStartToStop.put(cost, stop);
        }
        Map<Integer, String> costToStop = new TreeMap<>(costFromStartToStop);
        Collection<String> sorted = costToStop.values();
        List<String> sortedStops = new ArrayList<>(sorted);
        return sortedStops;
    }

    private List<String> removeDuplicates(List<String> list) {
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!newList.contains(list.get(i)))
                newList.add(list.get(i));
        }
        return newList;
    }

    public void printRoute(List<String> route) {
        System.out.println("==========  ROUTE  ==========");
        for (int i = 0; i < route.size() - 1; i++)
            System.out.println("* " + route.get(i) + " -> " + route.get(i + 1));
        System.out.println("Distance traveled= " + distance + " miles");
    }

    public Set<String> getCities() {
        return this.cities;
    }

    public Set<String> getAttractions() {
        return this.attractions;
    }

}
