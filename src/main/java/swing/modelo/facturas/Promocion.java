package swing.modelo.facturas;

public class Promocion extends TarifaBasica {

    private Tarifa tarifa;

    public Promocion(Tarifa tarifa, float precioTarifa) {
        super(precioTarifa);
        this.tarifa = tarifa;
    }

}
