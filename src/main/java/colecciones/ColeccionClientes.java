package colecciones;

import clientes.Cliente;

import java.util.HashMap;

public class ColeccionClientes {

    private HashMap<String, Cliente> clientes;

    public ColeccionClientes() {
        clientes = new HashMap<>();
    }

    public void darAltaCliente(Cliente cliente) {
        clientes.put(cliente.getNif(), cliente);
    }

}
