package swing.modelo.clientes;

import java.io.Serializable;

public class Direccion implements Serializable {

    private int codigoPostal;
    private String poblacion;
    private String provincia;

    public Direccion(int codigoPostal, String poblacion, String provincia) {
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }

    public int codigoPostal() {
        return this.codigoPostal;
    }

    public String poblacion() {
        return this.poblacion;
    }

    public String provincia() {
        return this.provincia;
    }

    @Override
    public String toString() {
        return this.codigoPostal() + " " + this.poblacion() + ", " + this.provincia();
    }

}
