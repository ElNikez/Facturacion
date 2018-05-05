package facturacion.clientes;

import java.io.Serializable;

public class Direccion implements Serializable {

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

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String toString() {
        return codigoPostal + " " + poblacion + ", " + provincia;
    }
}
