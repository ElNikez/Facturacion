package facturacion.facturas;

public class TarifaBasica extends Tarifa {

    public TarifaBasica(float precio) {
        super(precio);
    }

    public float calcularPrecioLlamada(Llamada llamada) {
        if (esFestivo(llamada.getFecha()))
            return 0;

        return llamada.getDuracionDeLlamada() * precio();
    }

    @Override
    public String descripcion() {
        return "Tarifa básica de 15 cént/min";
    }

}
