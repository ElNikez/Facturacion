package facturacion.facturas;

public abstract class TarifaExtra extends TarifaBasica{

    public Tarifa tarifa;



    public TarifaExtra(Tarifa tarifa,float precio) {
        super(precio);
        this.tarifa= tarifa;
    }


    public String descripcionTarifa() {
        return tarifa.toString();
    }
}
