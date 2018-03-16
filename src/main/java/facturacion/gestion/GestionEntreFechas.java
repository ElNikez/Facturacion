package facturacion.gestion;

import facturacion.interfaces.Fecha;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

public class GestionEntreFechas <T extends Fecha> {

    public Collection<T> muestraColeccionEntreFechas(Collection<T> coleccion, Calendar fechaInicio, Calendar fechaFin) {
        Collection<T> listaResultado = new HashSet<T>();

        for (T elem : coleccion)
            if (elem.getFecha().after(fechaInicio) && elem.getFecha().before(fechaFin))
                listaResultado.add(elem);

        return listaResultado;
    }

}
