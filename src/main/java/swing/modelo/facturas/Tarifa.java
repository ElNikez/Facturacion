package swing.modelo.facturas;

import java.io.Serializable;
import java.util.Calendar;

public abstract class Tarifa implements Serializable {

    public final static float PRECIO_BASICA = 15;
    public final static float PRECIO_MADRUGADA = 5;
    public final static float PRECIO_TARDE = 10;
    public final static float PRECIO_DOMINGO = 0;
    public final static float PRECIO_FESTIVO = 0;

    private float precioTarifa;

    public Tarifa(float precioTarifa) {
        this.precioTarifa = precioTarifa;
    }

    public float precio() {
        return this.precioTarifa;
    }

    public boolean esFestivo(Calendar fecha) {
        for(Calendar festivo : DiasFestivos.festivos())
            if(fecha.get(Calendar.DAY_OF_MONTH) == festivo.get(Calendar.DAY_OF_MONTH) &&
                    fecha.get(Calendar.MONTH) == festivo.get(Calendar.MONTH))
                return true;

        return false;
    }

    public abstract float calcularPrecioLlamada(Llamada llamada);

}
