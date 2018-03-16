package facturacion.clientes;

public class Direccion {

    private int codigoPostal;
    private String poblacion;
    private String provincia;

    public Direccion() {
        super();
    }

    public Direccion(int codigoPostal, String poblacion, String provincia) {
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }

    public Direccion(Direccion direccion) {
        this.codigoPostal = direccion.codigoPostal;
        this.poblacion = direccion.poblacion;
        this.provincia = direccion.provincia;
    }

    public String toString() {
        return codigoPostal + " " + poblacion + ", " + provincia;
    }
}
