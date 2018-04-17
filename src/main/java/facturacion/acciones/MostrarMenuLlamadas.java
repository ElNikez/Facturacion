package facturacion.acciones;

import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;
import facturacion.misc.MenuLlamadas;

import static facturacion.misc.Mensaje.INTRODUCE_OPCION;
import static facturacion.misc.Mensaje.INTRODUCE_OPCION_CORRECTA;

public class MostrarMenuLlamadas implements EjecutaOpcion {

    @Override
    public void ejecuta(Gestion gestion) {
        MenuLlamadas opcionMenu;
        int opcionInt;
        do {
            consola.mostrarDatos(MenuLlamadas.mostrarMenu());
            String opcionString = consola.pedirDatos(INTRODUCE_OPCION);
            opcionInt = Integer.parseInt(opcionString);
            if (opcionInt >= MenuLlamadas.values().length)
                consola.mostrarDatos(INTRODUCE_OPCION_CORRECTA);
            else
                break;
        } while (true);
        opcionMenu = MenuLlamadas.opcion(opcionInt);
        opcionMenu.ejecuta(gestion);
    }

}
