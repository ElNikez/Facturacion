package swing.modelo.excepciones;

public class ListaClientesVacio extends Exception {

    public ListaClientesVacio() {
        super("La lista de clientes está vacía");
    }

}
