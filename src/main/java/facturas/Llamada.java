package facturas;

import java.util.Calendar;

public class Llamada {

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

    public Calendar getFechaDeLlamada() {
        return fechaDeLlamada;
    }

    public int getDuracionDeLlamada(){
        return duracionDeLlamada;
    }
}
