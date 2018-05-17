package swing.modelo.excepciones;

public class ClienteNoFacturas extends Exception {

    public ClienteNoFacturas() {
        super("El cliente no tiene facturas");
    }

}