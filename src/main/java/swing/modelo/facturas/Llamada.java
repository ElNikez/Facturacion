package swing.modelo.facturas;

import swing.modelo.misc.Fecha;

import java.io.Serializable;
import java.util.Calendar;

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
    public Calendar fecha() {
        return null;
    }

    @Override
    public String toString() {
        return "Llamada {" +
                "   Número de teléfono: " + numeroDeTelefono + "\n" +
                "   Fecha de llamada: " + fechaDeLlamada + "\n" +
                "   Duración de la llamada: " + duracion + "\n" + 
                "}" + "\n";
    }
}
