package estructuras;

public class Inventario {

    // clase interna nodo para no tenerla por ahi tirada en otro .java
    private Nodo cabeza;

    private class Nodo {
        Item item;
        Nodo siguiente;

        Nodo(Item item) {
            this.item = item;
            this.siguiente = null;
        }
    }

    public void agregar(Item item) {
        Nodo nuevo = new Nodo(item);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public void mostrar() {
        Nodo actual = cabeza;
        System.out.println("\nInventario:\n");
        if (actual == null) {
            System.out.println("- (vacío)");
            return;
        }
        while (actual != null) {
            System.out.println("- " + actual.item);
            actual = actual.siguiente;
        }
    }
    
    public boolean contiene(String nombre) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.item.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
}
