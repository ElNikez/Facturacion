package clientes;

import es.uji.belfern.generador.GeneradorDatosINE;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.HashMap;
import java.util.List;

public class TestColeccionClientes {

    private HashMap<String, Cliente> clientes;
    private List<Cliente> listaClientes;
    private GeneradorDatosINE generador;

    @BeforeEach
    public void setUp() {
        clientes = new HashMap<>();
        generador = new GeneradorDatosINE();
    }

    @AfterEach
    public void tearDown() {
        clientes = null;
        generador = null;
    }

    @ParameterizedTest

    public void testDarAltaCliente(Cliente cliente) {

    }

}
