package llamadas;

import clientes.Cliente;
import clientes.Direccion;
import clientes.Empresa;
import es.uji.belfern.generador.GeneradorDatosINE;
import facturas.Llamada;
import facturas.Tarifa;
import gestion.Gestion;
import org.junit.jupiter.api.*;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test llamadas")
public class TestLlamadas {

    private static Gestion gestion;
    private static Cliente cliente;
    private static Llamada llamada1;
    private static Llamada llamada2;
    private static Llamada llamada3;
    private static GeneradorDatosINE generador;

    @BeforeAll
    public static void init() {
        cliente = new Cliente(generador.getNIF(), generador.getNombre(), "empresa@uji.es", new Direccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new Tarifa(10));
        llamada1 = new Llamada(666666666, 1000);
        llamada2 = new Llamada(666666666, 500);
        llamada3 = new Llamada(123456789, 666);
        generador = new GeneradorDatosINE();
    }

    @AfterAll
    public static void finish() {
        cliente = null;
        llamada1 = null;
        llamada2 = null;
        llamada3 = null;
        generador = null;
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

    @DisplayName("Mostrar llamadas")
    @Test
    public void testMostrarLlamadasDeCliente() {
        gestion.darDeAltaLlamada(cliente.getNif(), llamada1);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada2);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada3);

        HashSet<Llamada> llamadas = new HashSet<>();
        llamadas.add(llamada1);
        llamadas.add(llamada2);
        llamadas.add(llamada3);

        assertEquals(llamadas, gestion.mostrarLlamadasDeCliente(cliente.getNif()));
    }

}
