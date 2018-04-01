package facturacion.facturas;

import java.util.Calendar;
import java.util.Date;

public class ConTarifaDomingo extends TarifaExtra {


    public ConTarifaDomingo(Tarifa tarifa,float precio){
        super(tarifa,precio);

    }

    public float calculaPrecioLLamada(Llamada llamada){

        float precio1 = tarifa.calculaPrecioLlamada(llamada);
        float precio=0;
        if(llamada.getFecha().get(Calendar.DAY_OF_WEEK )== 7)
            return precio;
        return  precio1;
    }
    public String descripcion() {
        return "Domingos gratis con"+super.toString()+"centimos/minuto";
    }
}
