package facturacion.llamadas;

import es.uji.belfern.generador.GeneradorDatosINE;
import org.junit.jupiter.api.*;
import swing.modelo.clientes.Cliente;
import swing.modelo.excepciones.ClienteNoEncontrado;
import swing.modelo.excepciones.ClienteNoLlamadas;
import swing.modelo.excepciones.ClienteYaExiste;
import swing.modelo.factorias.FactoriaClientes;
import swing.modelo.facturas.Llamada;
import swing.modelo.facturas.Tarifa;
import swing.modelo.facturas.TarifaBasica;
import swing.modelo.gestion.Gestion;
import swing.modelo.gestion.GestionEntreFechas;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test llamadas")
class TestLlamadas {

    private static FactoriaClientes factoriaClientes = new FactoriaClientes();
    private static Gestion gestion;
    private static GeneradorDatosINE generador;
    private static Cliente cliente;
    private static Llamada llamada1;
    private static Llamada llamada2;
    private static Llamada llamada3;

    @BeforeAll
    static void init() {
        generador = new GeneradorDatosINE();
        cliente = factoriaClientes.crearEmpresa(generador.getNIF(), generador.getNombre(), "empresa@uji.es", factoriaClientes.crearDireccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new TarifaBasica(Tarifa.PRECIO_BASICA));
        llamada1 = new Llamada(666666666, 1000);
        llamada2 = new Llamada(666666666, 500);
        llamada3 = new Llamada(123456789, 666);
    }

    @AfterAll
    static void finish() {
        generador = null;
        cliente = null;
        llamada1 = null;
        llamada2 = null;
        llamada3 = null;
    }

    @BeforeEach
    void setUp() {
        gestion = new Gestion();
        try {
            gestion.darAltaCliente(cliente);
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        gestion = null;
    }

    @DisplayName("Dar de alta")
    @Test
    void testDarDeAltaLlamada() {
        assertAll(
                () -> assertTrue(gestion.darAltaLlamada(cliente.nif(), llamada1)),
                () -> assertTrue(gestion.darAltaLlamada(cliente.nif(), llamada2)),
                () -> assertTrue(gestion.darAltaLlamada(cliente.nif(), llamada3))
        );
    }

    @DisplayName("Mostrar llamadas")
    @Test
    void testMostrarLlamadasDeCliente() {
        try {
            gestion.darAltaLlamada(cliente.nif(), llamada1);
            gestion.darAltaLlamada(cliente.nif(), llamada2);
            gestion.darAltaLlamada(cliente.nif(), llamada3);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }

        try {
            assertNotNull(gestion.listarLlamadas(cliente.nif()));
        } catch (ClienteNoEncontrado | ClienteNoLlamadas clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }
    }

    @DisplayName("Llamadas entre fechas")
    @Test
    void testListarLlamadasEntreFechas() {
        try {
            gestion.darAltaLlamada(cliente.nif(), llamada1);
            gestion.darAltaLlamada(cliente.nif(), llamada2);
            gestion.darAltaLlamada(cliente.nif(), llamada3);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }

        GestionEntreFechas<Llamada> gestionFechas = new GestionEntreFechas<>();
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.set(Calendar.MONTH, fechaInicio.get(Calendar.MONTH) - 1);
        Calendar fechaFinal = Calendar.getInstance();

        try {
            assertNotNull(gestionFechas.entreFechas(gestion.listarLlamadas(cliente.nif()), fechaInicio, fechaFinal));
        } catch (ClienteNoEncontrado | ClienteNoLlamadas e) {
            e.printStackTrace();
        }
    }
}
