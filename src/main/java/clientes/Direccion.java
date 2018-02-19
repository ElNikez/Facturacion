package clientes;

public class Direccion {

    private int codigoPostal;
    private String poblacion, provincia;

    public Direccion() {
        super();
    }

    public Direccion(int codigoPostal, String poblacion, String provincia) {
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }

}
