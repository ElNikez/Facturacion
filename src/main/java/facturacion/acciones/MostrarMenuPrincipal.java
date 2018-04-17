package facturacion.acciones;

import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;
import facturacion.misc.MenuPrincipal;

import static facturacion.misc.Mensaje.INTRODUCE_OPCION;
import static facturacion.misc.Mensaje.INTRODUCE_OPCION_CORRECTA;

public class MostrarMenuPrincipal implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        MenuPrincipal opcionMenu;
        int opcionInt;
        do {
            consola.mostrarDatos(MenuPrincipal.mostrarMenu());
            String opcionString = consola.pedirDatos(INTRODUCE_OPCION);
            opcionInt = Integer.parseInt(opcionString);
            if (opcionInt >= MenuPrincipal.values().length)
                consola.mostrarDatos(INTRODUCE_OPCION_CORRECTA);
            else
                break;
        } while (true);
        opcionMenu = MenuPrincipal.opcion(opcionInt);
        opcionMenu.ejecuta(gestion);
    }

}
