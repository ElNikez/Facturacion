package facturacion.facturas;

import es.uji.belfern.generador.GeneradorDatosINE;
import facturacion.clientes.Cliente;
import facturacion.clientes.Direccion;
import facturacion.clientes.Empresa;
import facturacion.excepciones.*;
import facturacion.gestion.Gestion;
import org.junit.jupiter.api.*;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TestFacturas {

    private static GeneradorDatosINE generador;
    private static Gestion gestion;
    private static Cliente cliente;
    private static Llamada llamada1;
    private static Llamada llamada2;
    private static Llamada llamada3;
    private static Factura factura;

    @BeforeAll
    public static void init() {
        generador = new GeneradorDatosINE();
        cliente = new Empresa(generador.getNIF(), generador.getNombre(), "empresa@uji.es", new Direccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new Tarifa(10));
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
    public void setUp() throws ClienteYaExiste, ClienteNoEncontrado {
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
    public void testEmitirFactura() throws ClienteNoLlamadas, ClienteNoEncontrado {
        Calendar fechaEmision = Calendar.getInstance();
        Calendar fechaFacturacion = Calendar.getInstance();
        fechaFacturacion.set(Calendar.MONTH, fechaFacturacion.get(Calendar.MONTH) - 1);

        assertTrue(gestion.emitirFactura(cliente.getNif(), fechaFacturacion, fechaEmision));
    }

    @DisplayName("Listar facturas")
    @Test
    public void testListarFacturas() throws ClienteNoEncontrado, ClienteNoFacturas, ClienteNoLlamadas {
        Calendar fechaFacturacion = Calendar.getInstance();
        Calendar fechaEmision = Calendar.getInstance();
        fechaFacturacion.set(Calendar.MONTH, fechaFacturacion.MONTH - 1);

        gestion.emitirFactura(cliente.getNif(), fechaFacturacion, fechaEmision);

        assertNotNull(gestion.listarFacturas(cliente.getNif()));
    }

    @DisplayName("Mostrar facturas")
    @Test
    public void testMostrarFacturas() throws FacturaNoEncontrada, ClienteNoLlamadas, ClienteNoEncontrado {
        Calendar fechaEmision = Calendar.getInstance();
        Calendar fechaFacturacion = Calendar.getInstance();
        fechaFacturacion.set(Calendar.MONTH, fechaFacturacion.MONTH - 1);

        gestion.emitirFactura(cliente.getNif(), fechaFacturacion, fechaEmision);

        assertThat(gestion.mostrarFactura(0), is(factura));
    }

}
