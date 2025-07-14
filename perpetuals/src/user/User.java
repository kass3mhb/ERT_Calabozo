package user;

import estructuras.Item;
import estructuras.Inventario;

public class User {

    private String nombre;
    private Inventario inventario;
    private int salaActual = 1; // empieza en la celda, sala 1

    public User(String nombre) {
        this.nombre = nombre;
        this.inventario = new Inventario();
        
    }

    public void agregarItem(Item item) {
        inventario.agregar(item);
    }

    public void mostrarInventario() {
        inventario.mostrar();
    }

    public String getNombre() {
        return nombre;
    }

    public Inventario getInventario() {
        return inventario;
    }
    
    public boolean tieneItem(String nombre) {
    return inventario.contiene(nombre);
    }

    // metodos para moverse entre nodos
    public int getSalaActual() {
        return salaActual;
    }

    public void setSalaActual(int sala) {
        this.salaActual = sala;
    }
}
