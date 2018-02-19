package facturas;

import tarifas.Tarifa;

import java.util.Calendar;

public class Factura {

    private String codigoFactura;
    private Tarifa tarifaAplicada;
    private Calendar fechaDeEmision;
    private double importeTotal;

    public Factura() {
        super();
    }

    public Factura(String codigoFactura, Tarifa tarifaAplicada, double importeTotal) {
        this.codigoFactura = codigoFactura;
        this.tarifaAplicada = tarifaAplicada;
        this.fechaDeEmision = Calendar.getInstance();
        this.importeTotal = importeTotal;
    }

    public Calendar getFechaDeEmision() {
        return fechaDeEmision;
    }
}
