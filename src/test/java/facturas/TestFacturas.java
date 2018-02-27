package facturas;

import clientes.Cliente;
import clientes.Direccion;
import clientes.Empresa;
import es.uji.belfern.generador.GeneradorDatosINE;
import gestion.Gestion;
import org.junit.jupiter.api.*;

import java.util.Calendar;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class TestFacturas {


    private static GeneradorDatosINE generador;
    private static Cliente cliente;
    private static Llamada llamada1;
    private static Llamada llamada2;
    private static Llamada llamada3;
    private static Factura factura1;
    private static Factura factura2;
    private static Gestion gestion;


    @BeforeAll
    public static void init() {
        generador = new GeneradorDatosINE();
        cliente = new Empresa(generador.getNIF(), generador.getNombre(), "empresa@uji.es", new Direccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new Tarifa(10));
        llamada1 = new Llamada(666666666, 1000);
        llamada2 = new Llamada(666666666, 500);
        llamada3 = new Llamada(123456789, 666);
        factura1 = new Factura(0, new Tarifa(10), 1299600);

    }


    @AfterAll
    public static void finish() {
        cliente = null;
        llamada1 = null;
        llamada2 = null;
        llamada3 = null;
        generador = null;
        factura1 =null;
    }

    @BeforeEach
    public void setUp() {
        gestion = new Gestion();
        gestion.darDeAltaCliente(cliente);
        gestion.darDeAltaLlamada(cliente.getNif(),llamada1);
        gestion.darDeAltaLlamada(cliente.getNif(),llamada2);
        gestion.darDeAltaLlamada(cliente.getNif(),llamada3);
    }

    @AfterEach
    public void tearDown() {
        gestion = null;
    }




    @DisplayName("Listar facturas")
    @Test
    public void testListarFacturasDeCliente() {
        gestion.darDeAltaLlamada(cliente.getNif(), llamada1);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada2);
        gestion.darDeAltaLlamada(cliente.getNif(), llamada3);

       assertNull(gestion.listarFacturas(cliente.getNif()));

       gestion.emitirFactura(cliente.getNif(), )



    }

}


