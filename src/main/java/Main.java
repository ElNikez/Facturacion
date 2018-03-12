import clientes.Cliente;
import clientes.Direccion;
import clientes.Empresa;
import facturas.Factura;
import facturas.Llamada;
import facturas.Tarifa;
import gestion.Gestion;
import gestion.GestionClientes;
import gestion.GestionFacturas;
import gestion.GestionLlamadas;
import misc.Consola;
import misc.MenuClientes;
import misc.MenuPrincipal;

import java.util.Calendar;
import java.util.Collection;

public class Main {

    public static void main(String[] args) {
       new Gestion();
        mostrarMenu();
    }

    private static void mostrarMenu() {
        Consola consola = new Consola();
        Gestion gestion= new Gestion();


        while (true) {
            consola.mostrarDatos(MenuPrincipal.mostrarMenu());
            consola.mostrarDatos("Introduce una opción: ");
            byte opcion = Byte.parseByte(consola.pedirDatos());
            String nif;
            float tarifa;

            MenuPrincipal opcionMenu = MenuPrincipal.opcion(opcion);
            switch (opcionMenu) {
                case DAR_ALTA_CLIENTE:

                            consola.mostrarDatos("Introduce el tipo de cliente: ");
                            consola.mostrarDatos("1-Empresa ");
                            consola.mostrarDatos("2-Partiular ");





                            consola.mostrarDatos("Introduce el NIF: ");
                            nif = consola.pedirDatos();
                            consola.mostrarDatos("Introduce el nombre: ");
                            String nombre = consola.pedirDatos();
                            consola.mostrarDatos("Introduce el correo electrónico: ");
                            String correoElectronico = consola.pedirDatos();
                            consola.mostrarDatos("Introduce el código postal: ");
                            int codpostal = Integer.parseInt(consola.pedirDatos());
                            consola.mostrarDatos("Introduce la población: ");
                            String poblacion = consola.pedirDatos();
                            consola.mostrarDatos("Introduce la provincia: ");
                            String provincia = consola.pedirDatos();
                            consola.mostrarDatos("Introduce la tarifa: ");
                            tarifa = Float.parseFloat(consola.pedirDatos());

                            Cliente empresa = new Empresa(nif, nombre, correoElectronico, new Direccion(codpostal, poblacion, provincia), new Tarifa(tarifa));

                           gestion.darDeAltaCliente(empresa);






                    break;
                case DAR_BAJA_CLIENTE:
                    consola.mostrarDatos("Introduce el NIF: ");

                    nif = consola.pedirDatos();


                    gestion.darDeBajaCliente(nif);
                    break;
                case CAMBIAR_TARIFA_CLIENTE:
                    consola.mostrarDatos("Introduce el NIF: ");
                    nif = consola.pedirDatos();
                    consola.mostrarDatos("Introduce la nueva tarifa: ");
                   tarifa = Float.parseFloat(consola.pedirDatos());

                   gestion.cambiarTarifa(nif, new Tarifa(tarifa));
                    break;
                case MOSTRAR_DATOS_CLIENTE:
                    consola.mostrarDatos("Introduce el NIF: ");
                    nif = consola.pedirDatos();
                    Cliente clientr = (Gestion.mostrarCliente(nif));
                    consola.mostrarDatos("Nif:"+clientr.getNif());
                    consola.mostrarDatos("Nombre:"+clientr.getNombre());
                    consola.mostrarDatos("Correo:"+clientr.getcorreo());
                    consola.mostrarDatos("Tarifa:"+clientr.getTarifa().getPrecio());


                    break;
                case MOSTRAR_LISTA_CLIENTES:
                    Collection<Cliente> lista = Gestion.listarClientes();
                    for (Cliente cliente : lista)
                        consola.mostrarDatos("Nif:"+cliente.getNif().toString());
                    break;
                case DAR_ALTA_LLAMADA:
                    consola.mostrarDatos("Introduce el NIF: ");
                    nif = consola.pedirDatos();
                    consola.mostrarDatos("Introduce el número de teléfono: ");
                    int numeroDeTelefono = Integer.parseInt(consola.pedirDatos());
                    consola.mostrarDatos("Introduce la duración (en ms): ");
                    int duracionDeLlamada = Integer.parseInt(consola.pedirDatos());
                    Llamada llamada =  new Llamada(numeroDeTelefono, duracionDeLlamada);

                    gestion.darDeAltaLlamada(nif,llamada );

                    break;
                case MOSTRAR_LISTA_LLAMADAS:
                    consola.mostrarDatos("Introduce el NIF: ");
                    nif = consola.pedirDatos();

                    Collection<Llamada> listallam = gestion.listarLlamadas(nif);
                    for (Llamada llamada2 : listallam)
                        consola.mostrarDatos("Numero:"+llamada2.getNumero());


                    break;
                case EMITIR_FACTURA:
                    consola.mostrarDatos("Introduce el NIF: ");
                    nif = consola.pedirDatos();
                    Calendar fechaEmision = Calendar.getInstance();
                    Calendar fechaFacturacion = fechaEmision;
                    fechaFacturacion.set(Calendar.MONTH, fechaFacturacion.get(Calendar.MONTH) - 1);
                    gestion.emitirFactura(nif, fechaFacturacion, fechaEmision);
                    break;
                case MOSTRAR_FACTURA_CODIGO:
                    consola.mostrarDatos("Introduce el código de la factura: ");
                    int codigo = Integer.parseInt(consola.pedirDatos());
                    Factura factura= gestion.mostrarFactura(codigo);
                    consola.mostrarDatos("Codigo:"+factura.getCodigoFactura());
                    consola.mostrarDatos("Importe:"+factura.getImporteTotal());
                    consola.mostrarDatos("Tarifa:"+factura.getTarifaAplicada().getPrecio());
                    consola.mostrarDatos("FechaEmision:"+factura.getFechaDeEmision().toString());

                    break;
                case MOSTRAR_FACTURAS_CLIENTE:
                    consola.mostrarDatos("Introduce el NIF: ");
                    nif = consola.pedirDatos();



                    Collection<Factura> listafac = gestion.listarFacturas(nif);
                    for (Factura fact : listafac)
                        consola.mostrarDatos("Codigo:"+fact.getCodigoFactura());
                    break;
                case SALIR:
                    consola.mostrarDatos("");
                    System.exit(0);
            }
        }
    }

}
