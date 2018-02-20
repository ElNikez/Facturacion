package facturas;

import interfaces.Fecha;

import java.util.Calendar;

public class Factura implements Fecha {

    private int codigoFactura;
    private Tarifa tarifaAplicada;
    private Calendar fechaDeEmision;
    private double importeTotal;

    public Factura() {
        super();
    }

    public Factura(int codigoFactura, Tarifa tarifaAplicada, double importeTotal) {
        this.codigoFactura = codigoFactura;
        this.tarifaAplicada = tarifaAplicada;
        this.fechaDeEmision = Calendar.getInstance();
        this.importeTotal = importeTotal;
    }

    public Factura(Factura factura) {
        this.codigoFactura = factura.codigoFactura;
        this.tarifaAplicada = factura.tarifaAplicada;
        this.fechaDeEmision = factura.fechaDeEmision;
        this.importeTotal = factura.importeTotal;
    }

    public Calendar getFecha() {
        return fechaDeEmision;
    }
}
