package HW201.app;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        System.out.println("Граф після операцій додавання: " + graph.getAdjacencyView());
        System.out.println("Чи існує вершина 2: " + graph.hasVertex(2));
        System.out.println("Чи існує ребро 1-2: " + graph.hasEdge(1, 2));
        System.out.println("Чи існує ребро 1-3: " + graph.hasEdge(1, 3));

        graph.removeEdge(1, 2);
        System.out.println("Граф після removeEdge(1, 2): " + graph.getAdjacencyView());
        System.out.println("Чи існує ребро 1-2: " + graph.hasEdge(1, 2));

        graph.removeVertex(3);
        System.out.println("Граф після removeVertex(3): " + graph.getAdjacencyView());
        System.out.println("Чи існує вершина 3: " + graph.hasVertex(3));
    }
}
