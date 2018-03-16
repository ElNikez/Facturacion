package facturacion.llamadas;

import facturacion.clientes.Cliente;
import facturacion.clientes.Direccion;
import es.uji.belfern.generador.GeneradorDatosINE;
import facturacion.facturas.Llamada;
import facturacion.facturas.Tarifa;
import facturacion.gestion.Gestion;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test facturacion.llamadas")
public class TestLlamadas {

    private static GeneradorDatosINE generador;
    private static Gestion gestion;
    private static Cliente cliente;
    private static Llamada llamada1;
    private static Llamada llamada2;
    private static Llamada llamada3;

    @BeforeAll
    public static void init() {
        generador = new GeneradorDatosINE();
        cliente = new Cliente(generador.getNIF(), generador.getNombre(), "empresa@uji.es", new Direccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new Tarifa(10));
        llamada1 = new Llamada(666666666, 1000);
        llamada2 = new Llamada(666666666, 500);
        llamada3 = new Llamada(123456789, 666);
    }

    @AfterAll
    public static void finish() {
        generador = null;
        cliente = null;
        llamada1 = null;
        llamada2 = null;
        llamada3 = null;
    }

    @BeforeEach
    public void setUp() {
        gestion = new Gestion();
        gestion.darDeAltaCliente(cliente);
    }

    @AfterEach
    public void tearDown() {
        gestion = null;
    }

    @DisplayName("Dar de alta")
    @Test
    public void testDarDeAltaLlamada() {
        assertAll("Dar de alta",
                () -> assertTrue(gestion.darDeAltaLlamada(cliente.getNif(), llamada1)),
                () -> assertTrue(gestion.darDeAltaLlamada(cliente.getNif(), llamada2)),
                () -> assertTrue(gestion.darDeAltaLlamada(cliente.getNif(), llamada3))
        );
    }

    @DisplayName("Mostrar facturacion.llamadas")
    @Test
    public void testMostrarLlamadasDeCliente() {
        assertNull(gestion.listarLlamadas(cliente.getNif()));

        gestion.darDeAltaLlamada(cliente.getNif(), llamada1);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada2);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada3);

        assertNotNull(gestion.listarLlamadas(cliente.getNif()));
    }

}
