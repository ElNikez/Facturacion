package facturacion.acciones;

import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;
import facturacion.misc.MenuFacturas;

import static facturacion.misc.Mensaje.INTRODUCE_OPCION;
import static facturacion.misc.Mensaje.INTRODUCE_OPCION_CORRECTA;

public class MostrarMenuFacturas implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        MenuFacturas opcionMenu;
        int opcionInt;
        do {
            consola.mostrarDatos(MenuFacturas.mostrarMenu());
            String opcionString = consola.pedirDatos(INTRODUCE_OPCION);
            opcionInt = Integer.parseInt(opcionString);
            if (opcionInt >= MenuFacturas.values().length)
                consola.mostrarDatos(INTRODUCE_OPCION_CORRECTA);
            else
                break;
        } while (true);
        opcionMenu = MenuFacturas.opcion(opcionInt);
        opcionMenu.ejecuta(gestion);
    }

}
