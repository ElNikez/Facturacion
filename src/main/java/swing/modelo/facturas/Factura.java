package swing.modelo.facturas;

import swing.modelo.misc.Fecha;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Factura implements Fecha, Serializable {

    private int codigoFactura;
    private Tarifa tarifaAplicada;
    private float importeTotal;
    private Calendar fechaDeEmision;

    public Factura(int codigoFactura, Tarifa tarifaAplicada, float importeTotal) {
        this.codigoFactura = codigoFactura;
        this.tarifaAplicada = tarifaAplicada;
        this.importeTotal = importeTotal;
        this.fechaDeEmision = GregorianCalendar.getInstance();
    }

    public int codigoFactura() {
        return this.codigoFactura;
    }

    public Tarifa tarifaAplicada() {
        return this.tarifaAplicada;
    }

    public float importeTotal() {
        return this.importeTotal;
    }

    @Override
    public Calendar fecha() {
        return this.fechaDeEmision;
    }

    @Override
    public String toString() {
        return "Factura {" + "\n" +
                "   Código factura: " + codigoFactura() + "\n" +
                "   Tarifa aplicada: " + tarifaAplicada() + "\n" +
                "   Fecha de emisión: " + fecha().get(Calendar.DAY_OF_MONTH) + "/" + fecha().get(Calendar.MONTH) + "/" + fecha().get(Calendar.YEAR) + "\n" +
                "   Importe total: " + importeTotal() + "\n" +
                "}" + "\n";
    }

}
