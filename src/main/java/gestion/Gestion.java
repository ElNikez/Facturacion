package gestion;

import clientes.Cliente;
import facturas.Factura;
import facturas.Llamada;
import facturas.Tarifa;
import interfaces.Fecha;

import java.util.*;

public  class Gestion{

    private static Map<String, Cliente> listaClientes;
    private static Map<String, HashSet<Llamada>> listaLlamadas;
    private static Map<String, HashSet<Factura>> listafacturasCliente;
    private static Map<Integer, Factura> listafacturasPorCodigo;

    public Gestion() {
        listaClientes = new HashMap<>();
        listaLlamadas = new HashMap<>();
        listafacturasCliente = new HashMap<>();
        listafacturasPorCodigo = new HashMap<>();
    }

    // GESTIÓN DE CLIENTES

    public static boolean darDeAltaCliente(Cliente cliente) {
        String nif = cliente.getNif();

        if (!listaClientes.containsKey(nif)) {
            listaClientes.put(nif, cliente);

            return true;
        }

        return false;
    }

    public static boolean darDeBajaCliente(String nif) {
        if (listaClientes.containsKey(nif)) {
            listaClientes.remove(nif);

            return true;
        }

        return false;
    }

    public static boolean cambiarTarifa(String nif, Tarifa nuevaTarifa) {
        if (listaClientes.containsKey(nif)) {
            listaClientes.get(nif).setTarifa(nuevaTarifa);

            return true;
        }

        return false;
    }

    public static Cliente mostrarCliente(String nif) {
        if (listaClientes.containsKey(nif))
            return listaClientes.get(nif);

        return null;
    }

    public static Collection<Cliente> listarClientes() {
        return listaClientes.values();
    }

    // GESTIÓN DE LLAMADAS

    public static boolean darDeAltaLlamada(String nif, Llamada llamada) {
        if (!listaLlamadas.containsKey(nif))
            listaLlamadas.put(nif, new HashSet<>());

        listaLlamadas.get(nif).add(llamada);

        return true;
    }

    public static HashSet<Llamada> listarLlamadas(String nif) {
        if (listaLlamadas.containsKey(nif))
            return listaLlamadas.get(nif);

        return null;
    }

    // GESTIÓN DE FACTURAS

    public static Factura emitirFactura(String nif, Calendar fechaFacturacion, Calendar fechaEmision) {
        Tarifa tarifaAplicada = listaClientes.get(nif).getTarifa();
        HashSet<Llamada> llamadasCliente = listaLlamadas.get(nif);

        int duracionTotal = 0;

        for (Llamada llamada : llamadasCliente)
            if (llamada.getFecha().after(fechaFacturacion) && llamada.getFecha().before(fechaEmision))
                duracionTotal += llamada.getDuracionDeLlamada();

        double precioMinuto = tarifaAplicada.getPrecio();
        double importe = (precioMinuto / 60) * duracionTotal;
        int codigoFactura = listafacturasPorCodigo.size();
        Factura facturaCliente = new Factura(codigoFactura, tarifaAplicada, importe);

        if (listafacturasCliente.get(nif) == null)
            listafacturasCliente.put(nif, new HashSet<>());
        listafacturasCliente.get(nif).add(facturaCliente);
        listafacturasPorCodigo.put(codigoFactura, facturaCliente);

        return facturaCliente;
    }

    public static Factura mostrarFactura(int codigo) {
        if (listafacturasPorCodigo.containsKey(codigo))
            return listafacturasPorCodigo.get(codigo);

        return null;
    }

    public static HashSet<Factura> listarFacturas(String nif) {
        if (listafacturasCliente.containsKey(nif))
            return listafacturasCliente.get(nif);

        return null;
    }

    public static int cantidadFacturas() {
        return listafacturasPorCodigo.size();
    }




public static <T extends Fecha> HashSet<T> muestraColeccionEntreFechas( HashSet<T> coleccion, Calendar fechaInicio, Calendar fechaFin){

    HashSet<T> listaResultado = new HashSet<T>();

    for (T dato : coleccion) {

        if (dato.getFecha().after(fechaInicio) && dato.getFecha().before(fechaFin)) {

            listaResultado.add(dato);
        }
    }
    return listaResultado;


}


}