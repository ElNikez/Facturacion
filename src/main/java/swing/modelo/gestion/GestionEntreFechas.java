package swing.modelo.gestion;

import swing.modelo.misc.Fecha;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

public class GestionEntreFechas<T extends Fecha> extends Gestion {

    public Collection<T> entreFechas(Collection<T> coleccion, Calendar fechaInicio, Calendar fechaFin) {
        Collection<T> resultado = new HashSet<>();

        for(T elem : coleccion)
            if(elem.fecha().after(fechaInicio) && elem.fecha().before(fechaFin))
                resultado.add(elem);

        return resultado;
    }

}
