package estructuras;

public class Item {
    private String nombre;
    private String tipo; 

    public Item(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }
    
        public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return nombre + " [" + tipo + "]";
    }
}
