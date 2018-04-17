package facturacion.acciones;

import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;
import facturacion.misc.MenuClientes;

import static facturacion.misc.Mensaje.INTRODUCE_OPCION;
import static facturacion.misc.Mensaje.INTRODUCE_OPCION_CORRECTA;

public class MostrarMenuClientes implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        MenuClientes opcionMenu;
        int opcionInt;
        do {
            consola.mostrarDatos(MenuClientes.mostrarMenu());
            String opcionString = consola.pedirDatos(INTRODUCE_OPCION);
            opcionInt = Integer.parseInt(opcionString);
            if (opcionInt >= MenuClientes.values().length)
                consola.mostrarDatos(INTRODUCE_OPCION_CORRECTA);
            else
                break;
        } while (true);
        opcionMenu = MenuClientes.opcion(opcionInt);
        opcionMenu.ejecuta(gestion);
    }

}
