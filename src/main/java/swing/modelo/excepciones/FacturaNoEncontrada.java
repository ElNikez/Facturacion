package swing.modelo.excepciones;

public class FacturaNoEncontrada extends Exception {

    public FacturaNoEncontrada() {
        super("La factura no existe");
    }

}