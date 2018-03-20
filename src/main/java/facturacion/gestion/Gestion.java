package facturacion.gestion;

import facturacion.clientes.Cliente;
import facturacion.excepciones.*;
import facturacion.facturas.Factura;
import facturacion.facturas.Llamada;
import facturacion.facturas.Tarifa;

import java.io.*;
import java.util.*;


public class Gestion {

    private Map<String, Cliente> listaClientes;
    private Map<String, HashSet<Llamada>> listaLlamadas;
    private Map<String, HashSet<Factura>> listafacturasCliente;
    private Map<Integer, Factura> listaFacturasCodigo;

    public Gestion() {
        listaClientes = new HashMap<>();
        listaLlamadas = new HashMap<>();
        listafacturasCliente = new HashMap<>();
        listaFacturasCodigo = new HashMap<>();
    }


    // GESTIÓN DE CLIENTES

    public boolean darDeAltaCliente(Cliente cliente) throws ClienteYaExiste {

        String nif = cliente.getNif();

        if (listaClientes.containsKey(nif)) {
            throw new ClienteYaExiste();
        }
        listaClientes.put(nif, cliente);

        return true;
    }

    public boolean darDeBajaCliente(String nif) {
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


    public Cliente mostrarCliente(String nif) throws ClienteNoEncontrado {
            if (!listaClientes.containsKey(nif)){
                throw new ClienteNoEncontrado();
            }
            return listaClientes.get(nif);

    }

    public Collection<Cliente> listarClientes() {
        return listaClientes.values();
    }

    public boolean darDeAltaLlamada(String nif, Llamada llamada) {
        if (!listaLlamadas.containsKey(nif))
            listaLlamadas.put(nif, new HashSet<>());

        listaLlamadas.get(nif).add(llamada);

        return true;
    }

    // GESTIÓN DE LLAMADAS


    public HashSet<Llamada> listarLlamadas(String nif) throws ClienteNoLlamadas {
        if (!listaLlamadas.containsKey(nif))
            throw new ClienteNoLlamadas();
        return listaLlamadas.get(nif);

    }

    public Factura emitirFactura(String nif, Calendar fechaFacturacion, Calendar fechaEmision) {
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


    public Factura mostrarFactura(int codigo) throws FacturaNoEncontrada {
            if (!listaFacturasCodigo.containsKey(codigo))
                throw new FacturaNoEncontrada();
            return listaFacturasCodigo.get(codigo);

    }


    public HashSet<Factura> listarFacturas(String nif) throws ClienteNoFacturas {
        if (!listafacturasCliente.containsKey(nif))
            throw new ClienteNoFacturas();
        return listafacturasCliente.get(nif);

    }

    public int cantidadFacturas() {
        return listaFacturasCodigo.size();
    }

    // OTROS

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