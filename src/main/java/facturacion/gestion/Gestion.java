package facturacion.gestion;

import facturacion.clientes.Cliente;
import facturacion.facturas.Factura;
import facturacion.facturas.Llamada;
import facturacion.facturas.Tarifa;

import java.io.*;
import java.util.*;


public class Gestion {

    private static Map<String, Cliente> listaClientes;
    private static Map<String, HashSet<Llamada>> listaLlamadas;
    private static Map<String, HashSet<Factura>> listafacturasCliente;
    private static Map<Integer, Factura> listaFacturasCodigo;

    public Gestion() {
        listaClientes = new HashMap<>();
        listaLlamadas = new HashMap<>();
        listafacturasCliente = new HashMap<>();
        listaFacturasCodigo = new HashMap<>();
    }

    public static boolean darDeAltaCliente(Cliente cliente) {
        String nif = cliente.getNif();

        if (!listaClientes.containsKey(nif)) {
            listaClientes.put(nif, cliente);

            return true;
        }

        return false;
    }

    // GESTIÓN DE CLIENTES

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

    public static boolean darDeAltaLlamada(String nif, Llamada llamada) {
        if (!listaLlamadas.containsKey(nif))
            listaLlamadas.put(nif, new HashSet<>());

        listaLlamadas.get(nif).add(llamada);

        return true;
    }

    // GESTIÓN DE LLAMADAS

    public static HashSet<Llamada> listarLlamadas(String nif) {
        if (listaLlamadas.containsKey(nif))
            return listaLlamadas.get(nif);

        return null;
    }

    public static Factura emitirFactura(String nif, Calendar fechaFacturacion, Calendar fechaEmision) {
        Tarifa tarifaAplicada = listaClientes.get(nif).getTarifa();
        HashSet<Llamada> llamadasCliente = listaLlamadas.get(nif);

        int duracionTotal = 0;

        for (Llamada llamada : llamadasCliente)
            if (llamada.getFecha().after(fechaFacturacion) && llamada.getFecha().before(fechaEmision))
                duracionTotal += llamada.getDuracionDeLlamada();

        double precioMinuto = tarifaAplicada.getPrecio();
        double importe = (precioMinuto / 60) * duracionTotal;
        int codigoFactura = listaFacturasCodigo.size();
        Factura facturaCliente = new Factura(codigoFactura, tarifaAplicada, importe);

        if (listafacturasCliente.get(nif) == null)
            listafacturasCliente.put(nif, new HashSet<>());
        listafacturasCliente.get(nif).add(facturaCliente);
        listaFacturasCodigo.put(codigoFactura, facturaCliente);

        return facturaCliente;
    }

    // GESTIÓN DE FACTURAS

    public static Factura mostrarFactura(int codigo) {
        if (listaFacturasCodigo.containsKey(codigo))
            return listaFacturasCodigo.get(codigo);

        return null;
    }

    public static HashSet<Factura> listarFacturas(String nif) {
        if (listafacturasCliente.containsKey(nif))
            return listafacturasCliente.get(nif);

        return null;
    }

    public static int cantidadFacturas() {
        return listaFacturasCodigo.size();
    }

    public void cargarDatos() {
        try {
            ObjectInputStream ois;
            FileInputStream fis;
            fis = new FileInputStream("clientes.bin");
            ois = new ObjectInputStream(fis);
            listaClientes = (Map<String, Cliente>) ois.readObject();
            fis = new FileInputStream("llamadas.bin");
            ois = new ObjectInputStream(fis);
            listaLlamadas = (Map<String, HashSet<Llamada>>) ois.readObject();
            fis = new FileInputStream("facturasCliente.bin");
            ois = new ObjectInputStream(fis);
            listafacturasCliente = (Map<String, HashSet<Factura>>) ois.readObject();
            fis = new FileInputStream("facturasCodigo.bin");
            ois = new ObjectInputStream(fis);
            listaFacturasCodigo = (Map<Integer, Factura>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void guardarDatos() {
        try {
            ObjectOutputStream oos;
            FileOutputStream fos;
            fos = new FileOutputStream("clientes.bin");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listaClientes);
            fos = new FileOutputStream("llamadas.bin");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listaLlamadas);
            fos = new FileOutputStream("facturasCliente.bin");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listafacturasCliente);
            fos = new FileOutputStream("facturasCodigo.bin");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listaFacturasCodigo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}