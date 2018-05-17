package swing.modelo.facturas;

public class TarifaBasica extends Tarifa {

    public TarifaBasica(float precioTarifa) {
        super(precioTarifa);
    }

    @Override
    public float calcularPrecioLlamada(Llamada llamada) {
        return llamada.duracion() * precio();
    }

    @Override
    public String toString() {
        return "Tarifa básica de " + Tarifa.PRECIO_BASICA + " cént/min";
    }

}
