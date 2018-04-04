package facturacion.facturas;

public class Promocion extends TarifaBasica {

    private Tarifa tarifa;

    public Promocion(Tarifa tarifa, float precio) {
        super(precio);
        this.tarifa = tarifa;
    }

}
