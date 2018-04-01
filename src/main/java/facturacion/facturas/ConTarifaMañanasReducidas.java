package facturacion.facturas;

import java.util.Calendar;

public class ConTarifaMañanasReducidas extends TarifaExtra {

    public ConTarifaMañanasReducidas(Tarifa tarifa,float precio){
        super(tarifa,precio);

    }

    public float calculaPrecioLLamada(Llamada llamada){

        float precio1 = llamada.getDuracionDeLlamada()*super.getPrecio();
        float precio=tarifa.calculaPrecioLlamada(llamada);
        if(llamada.getFecha().get(Calendar.HOUR)>=8 && llamada.getFecha().get(Calendar.HOUR)<=12 )
            return precio1;
        return  precio;
    }
    public String descripcion() {
        return "Tardes de 8:00 a 12:00 con "+super.toString()+" centimos/minuto";
    }
}
