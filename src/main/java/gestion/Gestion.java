package gestion;

import clientes.Cliente;
import facturas.Factura;
import facturas.Llamada;
import facturas.Tarifa;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Gestion {

    private Map<String, Cliente> listaClientes;
    private Map<String, HashSet<Llamada>> listaLlamadas;
    private Map<String, HashSet<Factura>> listafacturasCliente;
    private Map<Integer, Factura> listafacturasPorCodigo;

    public Gestion() {
        listaClientes = new HashMap<>();
        listaLlamadas = new HashMap<>();
        listafacturasCliente = new HashMap<>();
        listafacturasPorCodigo = new HashMap<>();
    }

    public boolean darDeAltaCliente(Cliente cliente) {
        String nif = cliente.getNif();

        if (!listaClientes.containsKey(nif)) {
            listaClientes.put(nif, cliente);

            return true;
        }

        return false;
    }

    public boolean darDeBaja(String nif) {
        if (listaClientes.containsKey(nif)) {
            listaClientes.remove(nif);

            return true;
        }

        return false;
    }

    public boolean cambiarTarifa(String nif, Tarifa nuevaTarifa) {
        if (listaClientes.containsKey(nif)) {
            listaClientes.get(nif).setTarifa(nuevaTarifa);

            return true;
        }

        return false;
    }

    public Cliente mostrarCliente(String nif) {
        if (listaClientes.containsKey(nif))
            return listaClientes.get(nif);

        return null;
    }

    public Map<String, Cliente> listarClientes() {
        return listaClientes;
    }



    public boolean darDeAltaLlamada(String nif, Llamada llamada) {
        if (!listaLlamadas.containsKey(nif))
            return listaLlamadas.get(nif).add(llamada);

        return false;
    }

    public HashSet<Llamada> mostrarLlamadasDeCliente(String nif) {
        if (listaLlamadas.containsKey(nif))
            return listaLlamadas.get(nif);

        return null;
    }

    public HashSet<Llamada> listarLlamadas(String nif) {
        if (listaLlamadas.containsKey(nif))
            return listaLlamadas.get(nif);

        return null;
    }



    public Factura emitirFactura(String nif, int codigoFact, Calendar fechaFacturacion) {
        Cliente cliente = listaClientes.get(nif);
        Tarifa tarifaAplicada = cliente.getTarifa();
        HashSet<Llamada> llamadasCliente = listaLlamadas.get(nif);
        int duracionTotal = 0;

        for (Llamada llamada : llamadasCliente)
            duracionTotal += llamada.getDuracionDeLlamada();

        double precioMinuto = tarifaAplicada.getPrecio();
        double importe = (precioMinuto / 60) * duracionTotal;
        Factura facturaCliente = new Factura(codigoFact, tarifaAplicada, importe);

        return facturaCliente;
    }

    public Factura mostrarFactura(String codigo) {
        if (listafacturasPorCodigo.containsKey(codigo))
            return listafacturasPorCodigo.get(codigo);

        return null;
    }

    public HashSet<Factura> listarFacturas(String nif) {
        if (listafacturasCliente.containsKey(nif))
            return listafacturasCliente.get(nif);

        return null;
    }

}
