package data;

import graph.Edge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DataReader {
    public HashMap<String, String> readAttractions(String filename) {
        HashMap<String, String> attractions = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                attractions.put(values[0], values[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attractions;
    }

    public HashMap <String, Edge<String>> readRoads(String filename) {
        HashMap<String, Edge<String>> roads = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Edge<String> road = new Edge<>(values[0], values[1], Integer.parseInt(values[2]));
                String key = values[0] + values[1];
                roads.put(key, road);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return roads;
    }

}
