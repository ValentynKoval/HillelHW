package HW201.app;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    private final Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();

    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new HashSet<>());
    }

    public void addEdge(int source, int destination) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }

    public void removeVertex(int vertex) {
        if (!hasVertex(vertex)) {
            return;
        }

        for (Integer neighbor : adjacencyList.get(vertex)) {
            adjacencyList.get(neighbor).remove(vertex);
        }
        adjacencyList.remove(vertex);
    }

    public void removeEdge(int source, int destination) {
        if (!hasVertex(source) || !hasVertex(destination)) {
            return;
        }

        adjacencyList.get(source).remove(destination);
        adjacencyList.get(destination).remove(source);
    }

    public boolean hasVertex(int vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public boolean hasEdge(int source, int destination) {
        return hasVertex(source) && adjacencyList.get(source).contains(destination);
    }

    public Map<Integer, Set<Integer>> getAdjacencyView() {
        Map<Integer, Set<Integer>> view = new HashMap<>();
        for (Map.Entry<Integer, Set<Integer>> entry : adjacencyList.entrySet()) {
            view.put(entry.getKey(), Collections.unmodifiableSet(entry.getValue()));
        }
        return Collections.unmodifiableMap(view);
    }
}
