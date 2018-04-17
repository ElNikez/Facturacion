package facturacion.facturas;

import facturacion.interfaces.Fecha;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Factura implements Fecha, Serializable {

    private int codigoFactura;
    private Tarifa tarifaAplicada;
    private Calendar fechaDeEmision;
    private float importeTotal;

    public Factura(int codigoFactura, Tarifa tarifaAplicada, float importeTotal) {
        this.codigoFactura = codigoFactura;
        this.tarifaAplicada = tarifaAplicada;
        this.fechaDeEmision = GregorianCalendar.getInstance();
        this.importeTotal = importeTotal;
    }

    public Calendar getFecha() {
        return fechaDeEmision;
    }

    @Override
    public String toString() {
        return "Factura {" + "\n" +
                "   codigoFactura=" + codigoFactura + "\n" +
                "   tarifaAplicada=" + tarifaAplicada + "\n" +
                "   fechaDeEmision=" + fechaDeEmision.get(Calendar.DAY_OF_MONTH) + "/" + (fechaDeEmision.get(Calendar.MONTH) + 1) + "/" + fechaDeEmision.get(Calendar.YEAR) + "\n" +
                "   importeTotal=" + importeTotal + "\n" +
                "}";
    }

}
