package gestion;

import clientes.Cliente;
import facturas.Factura;
import facturas.Llamada;
import facturas.Tarifa;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Gestion {

    private static Map<String, Cliente> listaClientes;
    private static Map<String, HashSet<Llamada>> listaLlamadas;
    private static Map<String, HashSet<Factura>> listafacturasCliente;
    private static Map<Integer, Factura> listafacturasPorCodigo;

    public Gestion() {
        listaClientes = new HashMap<String, Cliente>();
        listaLlamadas = new HashMap<String, HashSet<Llamada>>();
        listafacturasCliente = new HashMap<String, HashSet<Factura>>();
        listafacturasPorCodigo = new HashMap<Integer, Factura>();
    }

    public static Map<String, Cliente> listarClientes() {
        return listaClientes;
    }

    public static HashSet<Factura> listarFacturas(String nif) {


        if (listafacturasCliente.containsKey(nif)) {

            return listafacturasCliente.get(nif);
        }
        return null;
    }

    //Cambiar la tarifa de un cliente. y devuelve el valor de la antigua tarifa

    public boolean darDeAltaCliente(Cliente cliente) {

        String nif = cliente.getNif();

        if (!listaClientes.containsKey(nif)) {

            listaClientes.put(nif, cliente);

            return true;
        }
        return false;
    }

    public boolean darDeBaja(Cliente cliente) {

        String nif = cliente.getNif();

        if (listaClientes.containsKey(nif)) {

            listaClientes.remove(cliente);
            return true;
        }
        return false;
    }

    public boolean cambiarTarifa(Cliente cliente, Tarifa nuevaTarifa) {

        String nif = cliente.getNif();

        if (listaClientes.containsKey(nif)) {

            return cliente.setTarifa(nuevaTarifa);
        }

        return false;
    }

    public Cliente getCliente(String nif) {

        if (listaClientes.containsKey(nif)) {

            return listaClientes.get(nif);
        }
        return null;
    }

    public boolean darDeAltaLlamada(String nif, Llamada llamada) {

        if (!listaLlamadas.containsKey(nif)) {

            HashSet<Llamada> llamadasClientes = listaLlamadas.get(nif);
            llamadasClientes.add(llamada);

            return true;
        }
        return false;

    }

    public HashSet<Llamada> getClienteLlamadas(String nif) {

        if (listaLlamadas.containsKey(nif)) {

            return listaLlamadas.get(nif);
        }
        return null;
    }

    public Factura emitirFactura(String nif, int codigoFact, Date fechaEmision, Date fechaFacturacion) {

        Cliente cliente = listaClientes.get(nif);

        Tarifa tarifaAplicada = cliente.getTarifa();


        HashSet<Llamada> llamadasCliente = listaLlamadas.get(nif);

        int duracionTotal = 0;

        for (Llamada llamada : llamadasCliente) {

            duracionTotal += llamada.getDuracionDeLlamada();
        }

        double tarifa = tarifaAplicada.getPrecio();

        double importe = tarifa * duracionTotal;

        Factura facturaCliente = new Factura(codigoFact, tarifaAplicada, fechaEmision, fechaFacturacion, importe);

        return facturaCliente;
    }

    public Factura getFactura(String codigo) {

        if (listafacturasPorCodigo.containsKey(codigo)) {

            return listafacturasPorCodigo.get(codigo);
        }
        return null;
    }
}
