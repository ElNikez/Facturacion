package facturacion.llamadas;

import es.uji.belfern.generador.GeneradorDatosINE;
import facturacion.clientes.Cliente;
import facturacion.clientes.Direccion;
import facturacion.clientes.Empresa;
import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteNoLlamadas;
import facturacion.excepciones.ClienteYaExiste;
import facturacion.facturas.Llamada;
import facturacion.facturas.Tarifa;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;
import org.junit.jupiter.api.*;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test llamadas")
public class TestLlamadas {

    private static Gestion gestion;
    private static GestionEntreFechas<Llamada> entreFechas;
    private static GeneradorDatosINE generador;
    private static Cliente cliente;
    private static Llamada llamada1;
    private static Llamada llamada2;
    private static Llamada llamada3;

    @BeforeAll
    public static void init() {
        generador = new GeneradorDatosINE();
        cliente = new Empresa(generador.getNIF(), generador.getNombre(), "empresa@uji.es", new Direccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new Tarifa(10));
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
    public void setUp() throws ClienteYaExiste {
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
        assertAll(
                () -> assertTrue(gestion.darDeAltaLlamada(cliente.getNif(), llamada1)),
                () -> assertTrue(gestion.darDeAltaLlamada(cliente.getNif(), llamada2)),
                () -> assertTrue(gestion.darDeAltaLlamada(cliente.getNif(), llamada3))
        );
    }

    @DisplayName("Mostrar llamadas")
    @Test
    public void testMostrarLlamadasDeCliente() throws ClienteNoEncontrado, ClienteNoLlamadas {
        gestion.darDeAltaLlamada(cliente.getNif(), llamada1);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada2);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada3);

        assertNotNull(gestion.listarLlamadas(cliente.getNif()));
    }

    @DisplayName("Llamadas entre fechas")
    @Test
    public void testListarLlamadasEntreFechas() throws ClienteNoEncontrado, ClienteNoLlamadas {
        gestion.darDeAltaLlamada(cliente.getNif(), llamada1);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada2);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada3);

        entreFechas = new GestionEntreFechas<>();
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.set(Calendar.MONTH, fechaInicio.get(Calendar.MONTH) - 1);
        Calendar fechaFinal = Calendar.getInstance();

        assertNotNull(entreFechas.muestraColeccionEntreFechas(gestion.listarLlamadas(cliente.getNif()), fechaInicio, fechaFinal));
    }

    @DisplayName("Excepciones")
    @Test
    public void testExcepciones() {
        assertThrows(ClienteNoLlamadas.class, () -> gestion.listarLlamadas(cliente.getNif()));
    }

}
