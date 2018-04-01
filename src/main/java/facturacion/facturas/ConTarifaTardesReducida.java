package facturacion.facturas;

import java.util.Calendar;

public class ConTarifaTardesReducida extends TarifaExtra {

    public ConTarifaTardesReducida(Tarifa tarifa, float precio){
        super(tarifa,precio);

    }

    public float calculaPrecioLLamada(Llamada llamada){

        float precio1 = llamada.getDuracionDeLlamada()*super.getPrecio();
        float precio=tarifa.calculaPrecioLlamada(llamada);
        if(llamada.getFecha().get(Calendar.HOUR)>=16 && llamada.getFecha().get(Calendar.HOUR)<=20 )
            return precio1;
        return  precio;
    }
    public String descripcion() {
        return "Tardes de 16:00 a 20:00 con "+super.toString()+" centimos/minuto";
    }
}
