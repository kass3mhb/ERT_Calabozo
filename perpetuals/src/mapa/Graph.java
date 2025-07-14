package mapa;

abstract class Graph {
    protected int numNodes; // Número de nodos en el grafo

    public Graph(int numNodes) {
        this.numNodes = numNodes;
    }

    public abstract void addEdge(int source, int destination);

    public abstract void printGraph();

    public abstract void dfs(int start);
}