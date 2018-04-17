package facturacion.acciones;

import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;
import facturacion.misc.MenuAltaClientes;

import static facturacion.misc.Mensaje.INTRODUCE_OPCION;
import static facturacion.misc.Mensaje.INTRODUCE_OPCION_CORRECTA;

public class MostrarMenuAltaClientes implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        MenuAltaClientes opcionMenu;
        int opcionInt;
        do {
            consola.mostrarDatos(MenuAltaClientes.mostrarMenu());
            String opcionString = consola.pedirDatos(INTRODUCE_OPCION);
            opcionInt = Integer.parseInt(opcionString);
            if (opcionInt >= MenuAltaClientes.values().length)
                consola.mostrarDatos(INTRODUCE_OPCION_CORRECTA);
            else
                break;
        } while (true);
        opcionMenu = MenuAltaClientes.opcion(opcionInt);
        opcionMenu.ejecuta(gestion);
    }

}
