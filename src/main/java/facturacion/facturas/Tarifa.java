package facturacion.facturas;

import facturacion.misc.DiasFestivos;

import java.io.Serializable;
import java.util.Calendar;

public abstract class Tarifa implements Serializable {

    public static float PRECIO_BASICA = 15;
    public static float PRECIO_MADRUGADA = 5;
    public static float PRECIO_TARDE = 10;
    public static float PRECIO_DOMINGO = 0;
    public static float PRECIO_FESTIVO = 0;

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

    boolean esFestivo(Calendar fecha) {
        for (Calendar festivo : DiasFestivos.festivos())
            if (fecha.get(Calendar.DAY_OF_MONTH) == festivo.get(Calendar.DAY_OF_MONTH) &&
                    fecha.get(Calendar.MONTH) == festivo.get(Calendar.MONTH))
                return true;

        return false;
    }

}
