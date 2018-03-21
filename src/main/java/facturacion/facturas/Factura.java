package facturacion.facturas;

import facturacion.interfaces.Fecha;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class Factura implements Fecha, Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Factura)) return false;
        Factura factura = (Factura) o;
        return codigoFactura == factura.codigoFactura
                && Double.compare(factura.importeTotal, importeTotal) == 0
                && Objects.equals(tarifaAplicada.getPrecio(), factura.tarifaAplicada.getPrecio());
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public Tarifa getTarifaAplicada() {
        return tarifaAplicada;
    }

    public Calendar getFechaDeEmision() {
        return fechaDeEmision;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    @Override
    public String toString() {
        return "Factura {" +
                "   codigoFactura=" + codigoFactura + "\n" +
                "   tarifaAplicada=" + tarifaAplicada + "\n" +
                "   fechaDeEmision=" + fechaDeEmision.get(Calendar.DAY_OF_MONTH) + "/" + (fechaDeEmision.get(Calendar.MONTH) + 1) + "/" + fechaDeEmision.get(Calendar.YEAR) + "\n" +
                "   importeTotal=" + importeTotal + "\n" +
                "}" + "\n";
    }

}
