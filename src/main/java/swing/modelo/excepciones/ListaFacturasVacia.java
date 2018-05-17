package swing.modelo.excepciones;

public class ListaFacturasVacia extends Exception {

    public ListaFacturasVacia() {
        super("La lista de facturas está vacía");
    }

}
