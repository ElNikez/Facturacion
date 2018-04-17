package facturacion.acciones;

import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.factorias.FactoriaTarifas;
import facturacion.gestion.Gestion;
import facturacion.interfaces.EjecutaOpcion;
import facturacion.misc.MenuCambioTarifa;

import static facturacion.misc.Mensaje.*;

public class CambiarTarifa implements EjecutaOpcion {

    private FactoriaTarifas factoriaTarifas = new FactoriaTarifas();

    @Override
    public void ejecuta(Gestion gestion) {
        String nif = consola.pedirDatos(INTRODUCE_NIF);

        MenuCambioTarifa opcionMenu;
        do {
            String opcionString;
            do {
                consola.mostrarDatos(MenuCambioTarifa.mostrarMenu());
                opcionString = consola.pedirDatos(INTRODUCE_OPCION);
                int opcionInt = Integer.parseInt(opcionString);
                if (opcionInt >= MenuCambioTarifa.values().length)
                    consola.mostrarDatos(INTRODUCE_OPCION_CORRECTA);
                else
                    break;
            } while (true);
            byte opcionByte = Byte.parseByte(opcionString);
            opcionMenu = MenuCambioTarifa.opcion(opcionByte);
            switch (opcionMenu) {
                case VOLVER:
                    break;
                case TARIFA_BASICA:
                    cambiarTarifa(gestion, nif, MenuCambioTarifa.TARIFA_BASICA);
                    break;
                case TARIFA_MADRUGADA:
                    cambiarTarifa(gestion, nif, MenuCambioTarifa.TARIFA_MADRUGADA);
                    break;
                case TARIFA_TARDE:
                    cambiarTarifa(gestion, nif, MenuCambioTarifa.TARIFA_TARDE);
                    break;
                case TARIFA_DOMINGO:
                    cambiarTarifa(gestion, nif, MenuCambioTarifa.TARIFA_DOMINGO);
                    break;
            }
        } while (opcionMenu != MenuCambioTarifa.VOLVER);
    }

    private void cambiarTarifa(Gestion gestion, String nif, MenuCambioTarifa tarifa) {
        try {
            switch (tarifa) {
                case TARIFA_BASICA:
                    gestion.cambiarTarifa(nif, factoriaTarifas.tarifaBasica());
                    break;
                case TARIFA_MADRUGADA:
                    gestion.cambiarTarifa(nif, factoriaTarifas.promocion(gestion.mostrarCliente(nif).getTarifa(), MenuCambioTarifa.TARIFA_MADRUGADA));
                    break;
                case TARIFA_TARDE:
                    gestion.cambiarTarifa(nif, factoriaTarifas.promocion(gestion.mostrarCliente(nif).getTarifa(), MenuCambioTarifa.TARIFA_TARDE));
                    break;
                case TARIFA_DOMINGO:
                    gestion.cambiarTarifa(nif, factoriaTarifas.promocion(gestion.mostrarCliente(nif).getTarifa(), MenuCambioTarifa.TARIFA_DOMINGO));
                    break;
            }
            consola.mostrarDatos(CLIENTE_CAMBIAR_TARIFA);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            consola.mostrarDatos(CLIENTE_NO_EXISTE);
        }
    }

}
