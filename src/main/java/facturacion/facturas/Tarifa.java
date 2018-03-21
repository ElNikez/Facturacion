package facturacion.facturas;

import java.io.Serializable;

public class Tarifa implements Serializable {

    private float precio;

    public Tarifa() {
        super();
    }

    public Tarifa(float precio) {

        this.precio = precio;
    }

    public Tarifa(Tarifa tarifa) {

        this.precio = tarifa.precio;
    }

    public float getPrecio() {

        return precio;
    }

    @Override
    public String toString() {
        return "Tarifa {" + "precio=" + precio + "}";
    }

}
