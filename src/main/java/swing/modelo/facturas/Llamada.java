package swing.modelo.facturas;

import swing.modelo.misc.Fecha;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Llamada implements Fecha, Serializable {

    private int numeroDeTelefono;
    private Calendar fechaDeLlamada;
    private int duracion;

    public Llamada(int numeroDeTelefono, int duracion) {
        this.numeroDeTelefono = numeroDeTelefono;
        this.fechaDeLlamada = Calendar.getInstance();
        this.duracion = duracion;
    }

    public int duracion() {
        return this.duracion;
    }

    public void cambiarFecha(Calendar fechaDeLlamada) {
        this.fechaDeLlamada = fechaDeLlamada;
    }

    @Override
    public GregorianCalendar fecha() {
        return null;
    }
}
