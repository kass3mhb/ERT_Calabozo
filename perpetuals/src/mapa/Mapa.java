package mapa;

import mapa.UndirectedGraphList;
import mapa.List;


public class Mapa {

    private UndirectedGraphList grafo;

    public Mapa() {
        grafo = new UndirectedGraphList(6);
        grafo.addEdge(1, 2);
        grafo.addEdge(2, 3);
        grafo.addEdge(2, 4);
        grafo.addEdge(2, 5);
        grafo.addEdge(3, 4);
    }

    public List<Integer> getSalasVecinas(int salaActual) {
        return grafo.getAdyacentes(salaActual);
    }

    public boolean puedeMoverse(int desde, int hacia) {
        List<Integer> vecinos = grafo.getAdyacentes(desde);
        for (int i = 0; i < vecinos.size(); i++) {
            if (vecinos.get(i) == hacia) {
                return true;
            }
        }
        return false;
    }

    public void mostrarMapa() {
        grafo.printGraph();
    }
}