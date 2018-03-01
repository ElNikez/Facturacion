package facturas;

import clientes.Cliente;
import clientes.Direccion;
import es.uji.belfern.generador.GeneradorDatosINE;
import gestion.Gestion;
import org.junit.jupiter.api.*;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class TestFacturas {

    private static GeneradorDatosINE generador;
    private static Cliente cliente;
    private static Llamada llamada1;
    private static Llamada llamada2;
    private static Llamada llamada3;
    private static Factura factura;
    private static Gestion gestion;

    @BeforeAll
    public static void init() {
        generador = new GeneradorDatosINE();
        cliente = new Cliente(generador.getNIF(), generador.getNombre(), "empresa@uji.es", new Direccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new Tarifa(10));
        llamada1 = new Llamada(666666666, 1000);
        llamada2 = new Llamada(666666666, 500);
        llamada3 = new Llamada(123456789, 666);
        factura = new Factura(0, new Tarifa(10), 361);
    }

    @AfterAll
    public static void finish() {
        generador = null;
        cliente = null;
        llamada1 = null;
        llamada2 = null;
        llamada3 = null;
        factura = null;
    }

    @BeforeEach
    public void setUp() {
        gestion = new Gestion();
        gestion.darDeAltaCliente(cliente);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada1);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada2);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada3);
    }

    @AfterEach
    public void tearDown() {
        gestion = null;
    }

    @DisplayName("Emitir factura")
    @Test
    public void testEmitirFactura() {
        gestion.darDeAltaLlamada(cliente.getNif(), llamada1);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada2);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada3);

        Calendar fechaFact = Calendar.getInstance();
        Calendar fechaEmi = Calendar.getInstance();
        fechaFact.set(Calendar.MONTH, fechaFact.get(Calendar.MONTH) - 1);

        Factura facturaTest = gestion.emitirFactura(cliente.getNif(), fechaFact, fechaEmi);

        assertEquals(factura, facturaTest);
    }

    @DisplayName("Listar facturas")
    @Test
    public void testListarFacturasDeCliente() {
        gestion.darDeAltaLlamada(cliente.getNif(), llamada1);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada2);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada3);

        assertNull(gestion.listarFacturas(cliente.getNif()));

        Calendar fechaFact = Calendar.getInstance();
        Calendar fechaEmi = Calendar.getInstance();
        fechaFact.set(Calendar.MONTH, fechaFact.get(Calendar.MONTH) - 1);

        gestion.emitirFactura(cliente.getNif(), fechaFact, fechaEmi);

        assertNotNull(gestion.listarFacturas(cliente.getNif()));
    }

    @DisplayName("Mostrar Facturas")
    @Test
    public void testMostrarFacturas() {
        gestion.darDeAltaLlamada(cliente.getNif(), llamada1);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada2);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada3);

        Calendar fechaFact = Calendar.getInstance();
        Calendar fechaEmi = Calendar.getInstance();

        assertNull(gestion.mostrarFactura(factura.getCodigoFactura()));

        gestion.emitirFactura(cliente.getNif(), fechaFact, fechaEmi);

        assertNotNull(gestion.mostrarFactura(factura.getCodigoFactura()));
    }

}
