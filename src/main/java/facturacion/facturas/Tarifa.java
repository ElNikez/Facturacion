package facturacion.facturas;

import java.io.Serializable;

public abstract class Tarifa implements Serializable {

    public float PRECIO_BASICA = 15;
    public float PRECIO_MADRUGADA = 5;
    public float PRECIO_TARDE = 10;
    public float PRECIO_DOMINGO = 0;

    private static float precio;

    public Tarifa() {
        super();
    }

    public Tarifa(float precio) {
        this.precio = precio;
    }

    public Tarifa(Tarifa tarifa) {
        this.precio = tarifa.precio;
    }

    public float precio() {
        return precio;
    }

    public abstract float calcularPrecioLlamada(Llamada llamada);

    @Override
    public String toString() {
        return "Tarifa {" +
                "precio=" + precio +
                "}";
    }

    public abstract String descripcion();

}
