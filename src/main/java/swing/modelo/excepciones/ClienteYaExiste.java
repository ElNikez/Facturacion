package swing.modelo.excepciones;

public class ClienteYaExiste extends Exception {

    public ClienteYaExiste() {
        super("El cliente ya existe");
    }

}
