package mapa;

public class UndirectedGraphList extends Graph {
    private List<Integer>[] adj; // Array de listas para la lista de adyacencia

    public UndirectedGraphList(int numNodes) {
        super(numNodes);
        adj = new List[numNodes];
        for (int i = 0; i < numNodes; ++i) {
            adj[i] = new List<>();
        }
    }

    @Override
    public void addEdge(int source, int destination) {
        // En un grafo no dirigido, si hay una arista de A a B, también hay de B a A
        adj[source].add(destination);
        adj[destination].add(source);
    }

    @Override
    public void printGraph() {
        System.out.println("Representación de Lista de Adyacencia (No Dirigido):");
        for (int i = 0; i < numNodes; ++i) {
            System.out.print("Node " + i + ":");
            for (int j = 0; j < adj[i].size(); j++) {
                System.out.print(" -> " + adj[i].get(j));
            }
            System.out.println();
        }
    }

    // --- DFS (Depth-First Search) ---
    @Override
    public void dfs(int start) {
        boolean[] visited = new boolean[numNodes]; // Array para llevar control de nodos visitados
        dfsUtil(start, visited);
        System.out.println(); // Salto de línea al finalizar
    }

    private void dfsUtil(int node, boolean[] visited) {
        visited[node] = true; // Marcar el nodo actual como visitado
        System.out.print(node + " "); // Imprimir el nodo visitado

        for (int i = 0; i < adj[node].size(); i++) {
            int neighbor = adj[node].get(i);
            if (!visited[neighbor]) { // Si el vecino no ha sido visitado, hacer DFS recursivamente
                dfsUtil(neighbor, visited);
            }
        }
    }
    
    public List<Integer> getAdyacentes(int nodo) {
    return adj[nodo];
    }
}