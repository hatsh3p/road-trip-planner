#Road Trip Planner

## TODO
* [ ] Use a CSV parser that reads road.csv as Road objects and store in a HashMap
* [ ] Use a CSV parser that reads attractions.csv and store in a HashMap
* [ ] Create a Command Line Interface that takes a start city, an end city, and attractions
* [ ] Convert CLI inputs into City objects to use in Dijkstra's algorithm
* [ ] Implement Dijkstra's algorithm (fuzzy)
  * Input: Graph [weighted, directed]
  * Source vertex: s (startCity)
  * Target vertex: t (endCity)
  * Uses a table with 4 elements: String vertex, boolean known, String path, int bestCost
* [ ] Store the result of Dijkstra's algorithm and return as output

## Dijkstra's Algorithm Implementation
* [ ] Create a graph to look up outgoing vertices from each vertex
* *** Should I just use a Priority Queue here?
* [ ] Use a HashMap<Vertex, ShortestPath> as the table for Dijkstra's algorithm
* [ ] Create a ShortestPath object that stores known, path, cost
  * Default: false, null, -1
  * Source vertex: false, null, 0
* [ ] Populate the HashMap with:
  * <Source Vertex, ShortestPath>
  * <All Other Vertices, Default ShortestPath>
* [ ] Get the least cost vertex from table
* [ ] Check all vertices that connect to the current vertex using the graph
* [ ] Update the current vertex's ShortestPath record with the lowest cost

## Structure
* Command Line Interface
* Data
* Driver
* Graph