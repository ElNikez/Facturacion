package swing.modelo.excepciones;

public class ClienteNoLlamadas extends Exception {

    public ClienteNoLlamadas() {
        super("El cliente no tiene llamadas");
    }

}
