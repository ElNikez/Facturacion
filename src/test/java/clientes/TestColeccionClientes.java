package clientes;

import facturas.Tarifa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.HashMap;
import java.util.List;

public class TestColeccionClientes {

    private HashMap<String, Cliente> clientes;
    private List<Cliente> listaClientes;

    @BeforeEach
    public void setUp() {
        clientes = new HashMap<>();
    }

    @AfterEach
    public void tearDown() {
        clientes = null;
    }

    @ParameterizedTest

    public void testDarAltaCliente(Cliente cliente) {

    }

}
