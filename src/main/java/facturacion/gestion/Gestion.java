package facturacion.gestion;

import facturacion.clientes.Cliente;
import facturacion.excepciones.*;
import facturacion.facturas.Factura;
import facturacion.facturas.Llamada;
import facturacion.facturas.Tarifa;

import javax.xml.transform.sax.SAXSource;
import java.io.*;
import java.util.*;

public class Gestion implements VistaGestionParaGrafica,VistaGestionParaControlador{


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

        if (listaClientes.containsKey(nif))
            throw new ClienteYaExiste();

        listaClientes.put(nif, cliente);

        return true;
    }

    public boolean darDeBajaCliente(String nif) throws ClienteNoEncontrado {
        if (!listaClientes.containsKey(nif))
            throw new ClienteNoEncontrado();

        listaClientes.remove(nif);

        return true;
    }

    public boolean cambiarTarifa(String nif, Tarifa nuevaTarifa) throws ClienteNoEncontrado {
        if (!listaClientes.containsKey(nif))
            throw new ClienteNoEncontrado();

        listaClientes.get(nif).setTarifa(nuevaTarifa);

        return true;
    }

    public Cliente mostrarCliente(String nif) throws ClienteNoEncontrado {
        if (!listaClientes.containsKey(nif))
            throw new ClienteNoEncontrado();

        return listaClientes.get(nif);
    }

    public Collection<Cliente> listarClientes() throws ListaClientesVacio {
        if (listaClientes.isEmpty())
            throw new ListaClientesVacio();

        return listaClientes.values();
    }

    // GESTIÓN DE LLAMADAS

    public boolean darDeAltaLlamada(String nif, Llamada llamada) throws ClienteNoEncontrado {
        if (!listaClientes.containsKey(nif))
            throw new ClienteNoEncontrado();

        if (!listaLlamadas.containsKey(nif))
            listaLlamadas.put(nif, new HashSet<>());

        return listaLlamadas.get(nif).add(llamada);
    }

    public HashSet<Llamada> listarLlamadas(String nif) throws ClienteNoEncontrado, ClienteNoLlamadas {
        if (!listaClientes.containsKey(nif))
            throw new ClienteNoEncontrado();

        if (!listaLlamadas.containsKey(nif))
            throw new ClienteNoLlamadas();

        return listaLlamadas.get(nif);
    }

    public boolean emitirFactura(String nif, Calendar fechaFacturacion, Calendar fechaEmision) throws ClienteNoEncontrado, ClienteNoLlamadas {
        if (!listaClientes.containsKey(nif))
            throw new ClienteNoEncontrado();

        if (!listaLlamadas.containsKey(nif))
            throw new ClienteNoLlamadas();

        Tarifa tarifaAplicada = listaClientes.get(nif).getTarifa();
        HashSet<Llamada> llamadasCliente = listaLlamadas.get(nif);
        int duracionTotal = 0;

        for (Llamada llamada : llamadasCliente)
            if (llamada.getFecha().after(fechaFacturacion) && llamada.getFecha().before(fechaEmision))
                duracionTotal += llamada.getDuracionDeLlamada();

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

    public Factura mostrarFactura(int codigo) throws ListaFacturasVacia, FacturaNoEncontrada {
        if (listaFacturasCodigo.isEmpty())
            throw new ListaFacturasVacia();
        if (!listaFacturasCodigo.containsKey(codigo))
            throw new FacturaNoEncontrada();

        return listaFacturasCodigo.get(codigo);
    }

    public HashSet<Factura> listarFacturas(String nif) throws ClienteNoEncontrado, ClienteNoFacturas {
        if (!listaClientes.containsKey(nif))
            throw new ClienteNoEncontrado();

        if (listafacturasCliente.isEmpty())
            throw new ClienteNoFacturas();

        return listafacturasCliente.get(nif);
    }

    // OTROS

    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        ObjectInputStream ois;
        FileInputStream fis;
        try {
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
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void guardarDatos() {
        ObjectOutputStream oos;
        FileOutputStream fos;
        try {
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
        } catch (IOException e) {
            e.getMessage();
        }
    }

}
