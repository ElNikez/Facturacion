package swing.modelo.gestion;

import swing.controlador.GestionParaControlador;
import swing.controlador.GestionParaVista;
import swing.modelo.clientes.Cliente;
import swing.modelo.excepciones.*;
import swing.modelo.facturas.Factura;
import swing.modelo.facturas.Llamada;
import swing.modelo.facturas.Tarifa;

import java.io.*;
import java.util.*;

public class Gestion implements GestionParaControlador, GestionParaVista {

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

    @Override
    public boolean existeCliente(String nif) {
        return listaClientes.containsKey(nif);
    }

    @Override
    public boolean hayClientes() {
        return !listaClientes.isEmpty();
    }

    @Override
    public boolean clienteConLlamadas(String nif) {
        return listaLlamadas.containsKey(nif);
    }

    @Override
    public boolean existeFactura(int codigo) {
        return listaFacturasCodigo.containsKey(codigo);
    }

    @Override
    public boolean darAltaCliente(Cliente cliente) throws ClienteYaExiste {
        String nif = cliente.nif();

        if (listaClientes.containsKey(nif))
            throw new ClienteYaExiste();

        listaClientes.put(nif, cliente);

        return true;
    }

    @Override
    public boolean darBajaCliente(String nif) throws ClienteNoEncontrado {
        if (!listaClientes.containsKey(nif))
            throw new ClienteNoEncontrado();

        listaClientes.remove(nif);

        return true;
    }

    @Override
    public boolean cambiarTarifa(String nif, Tarifa nuevaTarifa) throws ClienteNoEncontrado {
        if (!listaClientes.containsKey(nif))
            throw new ClienteNoEncontrado();

        listaClientes.get(nif).cambiarTarifa(nuevaTarifa);

        return true;
    }

    @Override
    public Cliente mostrarCliente(String nif) throws ClienteNoEncontrado {
        if (!listaClientes.containsKey(nif))
            throw new ClienteNoEncontrado();

        return listaClientes.get(nif);
    }

    @Override
    public Collection<Cliente> listarClientes() throws ListaClientesVacio {
        if (listaClientes.isEmpty())
            throw new ListaClientesVacio();

        return listaClientes.values();
    }

    // GESTIÓN DE LLAMADAS

    @Override
    public boolean darAltaLlamada(String nif, Llamada llamada) throws ClienteNoEncontrado {
        if (!listaClientes.containsKey(nif))
            throw new ClienteNoEncontrado();

        if (!listaLlamadas.containsKey(nif))
            listaLlamadas.put(nif, new HashSet<>());

        return listaLlamadas.get(nif).add(llamada);
    }

    @Override
    public HashSet<Llamada> listarLlamadas(String nif) throws ClienteNoEncontrado, ClienteNoLlamadas {
        if (!listaClientes.containsKey(nif))
            throw new ClienteNoEncontrado();

        if (!listaLlamadas.containsKey(nif))
            throw new ClienteNoLlamadas();

        return listaLlamadas.get(nif);
    }

    @Override
    public boolean emitirFactura(String nif, Calendar fechaFacturacion, Calendar fechaEmision) throws ClienteNoEncontrado, ClienteNoLlamadas {
        if (!listaClientes.containsKey(nif))
            throw new ClienteNoEncontrado();

        if (!listaLlamadas.containsKey(nif))
            throw new ClienteNoLlamadas();

        Tarifa tarifaAplicada = listaClientes.get(nif).tarifa();
        HashSet<Llamada> llamadasCliente = listaLlamadas.get(nif);
        int duracionTotal = 0;

        for (Llamada llamada : llamadasCliente)
            if (llamada.fecha().after(fechaFacturacion) && llamada.fecha().before(fechaEmision))
                duracionTotal += llamada.duracion();

        float precioMinuto = tarifaAplicada.precio();
        float importe = (precioMinuto / 60) * duracionTotal;
        int codigoFactura = listaFacturasCodigo.size();
        Factura facturaCliente = new Factura(codigoFactura, tarifaAplicada, importe);

        if (!listafacturasCliente.containsKey(nif))
            listafacturasCliente.put(nif, new HashSet<>());
        listafacturasCliente.get(nif).add(facturaCliente);
        listaFacturasCodigo.put(codigoFactura, facturaCliente);

        return true;
    }

    // GESTIÓN DE FACTURAS

    @Override
    public Factura mostrarFactura(int codigo) throws ListaFacturasVacia, FacturaNoEncontrada {
        if (listaFacturasCodigo.isEmpty())
            throw new ListaFacturasVacia();
        if (!listaFacturasCodigo.containsKey(codigo))
            throw new FacturaNoEncontrada();

        return listaFacturasCodigo.get(codigo);
    }

    @Override
    public HashSet<Factura> listarFacturas(String nif) throws ClienteNoEncontrado, ClienteNoFacturas {
        if (!listaClientes.containsKey(nif))
            throw new ClienteNoEncontrado();

        if (listafacturasCliente.isEmpty())
            throw new ClienteNoFacturas();

        return listafacturasCliente.get(nif);
    }

    // OTROS

    @Override
    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        String[] ficheros = {
                "clientes.bin",
                "llamadas.bin",
                "facturasCliente.bin",
                "facturasCodigo.bin"
        };

        ObjectInputStream ois;
        FileInputStream fis;
        try {
            fis = new FileInputStream(ficheros[0]);
            ois = new ObjectInputStream(fis);
            listaClientes = (Map<String, Cliente>) ois.readObject();
            fis = new FileInputStream(ficheros[1]);
            ois = new ObjectInputStream(fis);
            listaLlamadas = (Map<String, HashSet<Llamada>>) ois.readObject();
            fis = new FileInputStream(ficheros[2]);
            ois = new ObjectInputStream(fis);
            listafacturasCliente = (Map<String, HashSet<Factura>>) ois.readObject();
            fis = new FileInputStream(ficheros[3]);
            ois = new ObjectInputStream(fis);
            listaFacturasCodigo = (Map<Integer, Factura>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("DATOS CARGADOS");
    }

    @Override
    public void guardarDatos() {
        String[] ficheros = {
                "clientes.bin",
                "llamadas.bin",
                "facturasCliente.bin",
                "facturasCodigo.bin"
        };
        ObjectOutputStream oos;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(ficheros[0], true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listaClientes);
            fos = new FileOutputStream(ficheros[1], true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listaLlamadas);
            fos = new FileOutputStream(ficheros[2], true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listafacturasCliente);
            fos = new FileOutputStream(ficheros[3], true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listaFacturasCodigo);
        } catch (IOException e) {
            e.getMessage();
        }
        System.out.println("DATOS GUARDADOS");
    }

}
