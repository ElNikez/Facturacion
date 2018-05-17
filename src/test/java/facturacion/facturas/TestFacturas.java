package facturacion.facturas;

import es.uji.belfern.generador.GeneradorDatosINE;
import org.junit.jupiter.api.*;
import swing.modelo.clientes.Cliente;
import swing.modelo.excepciones.*;
import swing.modelo.factorias.FactoriaClientes;
import swing.modelo.facturas.Factura;
import swing.modelo.facturas.Llamada;
import swing.modelo.facturas.Tarifa;
import swing.modelo.facturas.TarifaBasica;
import swing.modelo.gestion.Gestion;
import swing.modelo.gestion.GestionEntreFechas;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test facturas")
class TestFacturas {

    private static FactoriaClientes factoriaClientes = new FactoriaClientes();
    private static Gestion gestion;
    private static GeneradorDatosINE generador;
    private static Cliente cliente;
    private static Llamada llamada1;
    private static Llamada llamada2;
    private static Llamada llamada3;
    private static Factura factura;

    @BeforeAll
    static void init() {
        generador = new GeneradorDatosINE();
        cliente = factoriaClientes.crearEmpresa(generador.getNIF(), generador.getNombre(), "empresa@uji.es", factoriaClientes.crearDireccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new TarifaBasica(10));
        llamada1 = new Llamada(666666666, 1000);
        llamada2 = new Llamada(666666666, 500);
        llamada3 = new Llamada(123456789, 666);
        factura = new Factura(0, new TarifaBasica(Tarifa.PRECIO_BASICA), 361);
    }

    @AfterAll
    static void finish() {
        generador = null;
        cliente = null;
        llamada1 = null;
        llamada2 = null;
        llamada3 = null;
        factura = null;
    }

    @BeforeEach
    void setUp() {
        gestion = new Gestion();
        try {
            gestion.darAltaCliente(cliente);
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.printStackTrace();
        }
        try {
            gestion.darAltaLlamada(cliente.nif(), llamada1);
            gestion.darAltaLlamada(cliente.nif(), llamada2);
            gestion.darAltaLlamada(cliente.nif(), llamada3);
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        gestion = null;
    }

    @DisplayName("Emitir factura")
    @Test
    void testEmitirFactura() {
        Calendar fechaEmision = Calendar.getInstance();
        Calendar fechaFacturacion = Calendar.getInstance();
        fechaFacturacion.set(Calendar.MONTH, fechaFacturacion.get(Calendar.MONTH) - 1);

        try {
            assertTrue(gestion.emitirFactura(cliente.nif(), fechaFacturacion, fechaEmision));
        } catch (ClienteNoEncontrado | ClienteNoLlamadas clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }
    }

    @DisplayName("Listar facturas")
    @Test
    void testListarFacturas() {
        Calendar fechaFacturacion = Calendar.getInstance();
        Calendar fechaEmision = Calendar.getInstance();
        fechaFacturacion.set(Calendar.MONTH, fechaFacturacion.get(Calendar.MONTH) - 1);

        try {
            gestion.emitirFactura(cliente.nif(), fechaFacturacion, fechaEmision);
        } catch (ClienteNoEncontrado | ClienteNoLlamadas clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }

        try {
            assertNotNull(gestion.listarFacturas(cliente.nif()));
        } catch (ClienteNoEncontrado | ClienteNoFacturas clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }
    }

    @DisplayName("Mostrar facturas")
    @Test
    void testMostrarFacturas() {
        Calendar fechaEmision = Calendar.getInstance();
        Calendar fechaFacturacion = Calendar.getInstance();
        fechaFacturacion.set(Calendar.MONTH, fechaFacturacion.get(Calendar.MONTH) - 1);

        try {
            gestion.emitirFactura(cliente.nif(), fechaFacturacion, fechaEmision);
        } catch (ClienteNoEncontrado | ClienteNoLlamadas clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }

        try {
            assertThat(gestion.mostrarFactura(0), is(factura));
        } catch (ListaFacturasVacia | FacturaNoEncontrada e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Facturas entre fechas")
    @Test
    void testListarLlamadasEntreFechas() {
        Calendar fechaEmision = Calendar.getInstance();
        Calendar fechaFacturacion = Calendar.getInstance();
        fechaFacturacion.set(Calendar.MONTH, fechaFacturacion.get(Calendar.MONTH) - 1);

        try {
            gestion.emitirFactura(cliente.nif(), fechaFacturacion, fechaEmision);
        } catch (ClienteNoEncontrado | ClienteNoLlamadas clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }

        GestionEntreFechas<Factura> gestionFechas = new GestionEntreFechas<>();
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.set(Calendar.MONTH, fechaInicio.get(Calendar.MONTH) - 1);
        Calendar fechaFinal = Calendar.getInstance();

        try {
            assertNotNull(gestionFechas.entreFechas(gestion.listarFacturas(cliente.nif()), fechaInicio, fechaFinal));
        } catch (ClienteNoEncontrado | ClienteNoFacturas clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }
    }

}
