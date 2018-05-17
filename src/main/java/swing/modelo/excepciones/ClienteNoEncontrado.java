package swing.modelo.excepciones;

public class ClienteNoEncontrado extends Exception {

    public ClienteNoEncontrado() {
        super("El cliente no existe");
    }

}
