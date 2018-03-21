package facturacion.facturas;

import facturacion.interfaces.Fecha;

import java.io.Serializable;
import java.util.Calendar;

public class Llamada implements Fecha, Serializable {

    private int numeroDeTelefono;
    private Calendar fechaDeLlamada;
    private int duracionDeLlamada;

    public Llamada() {
        super();
    }

    public Llamada(int numeroDeTelefono, int duracionDeLlamada) {
        this.numeroDeTelefono = numeroDeTelefono;
        this.fechaDeLlamada = Calendar.getInstance();
        this.duracionDeLlamada = duracionDeLlamada;
    }

    public Llamada(Llamada llamada) {
        this.numeroDeTelefono = numeroDeTelefono;
        this.fechaDeLlamada = Calendar.getInstance();
        this.duracionDeLlamada = duracionDeLlamada;
    }

    public int getNumero() {
        return numeroDeTelefono;
    }

    public Calendar getFecha() {
        return fechaDeLlamada;
    }

    public int getDuracionDeLlamada() {
        return duracionDeLlamada;
    }

    @Override
    public String toString() {
        return "Llamada {" + "\n" +
                "   numeroDeTelefono=" + numeroDeTelefono + "\n" +
                "   fechaDeLlamada=" + fechaDeLlamada.get(Calendar.DAY_OF_MONTH) + "/" + (fechaDeLlamada.get(Calendar.MONTH) + 1) + "/" + fechaDeLlamada.get(Calendar.YEAR) + "\n" +
                "   duracionDeLlamada=" + duracionDeLlamada + " s" + "\n" +
                "}" + "\n";
    }

}
