package facturacion.facturas;

import java.util.GregorianCalendar;

public class TarifaBasica extends Tarifa {



    public TarifaBasica(){

        super();
    }
    public TarifaBasica(float precio){
        super(precio);
    }
    public TarifaBasica(TarifaBasica tarifaBasica){
        super(tarifaBasica);
    }
    public float calculaPrecioLlamada(Llamada llamada){
        return llamada.getDuracionDeLlamada()*getPrecio();
    }


}

