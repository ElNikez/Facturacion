package facturacion.acciones;

import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;

import static facturacion.misc.Mensaje.SALIR;

public class Salir implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        consola.mostrarDatos(SALIR);
    }

}
