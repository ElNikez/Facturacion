package gestion;

import clientes.Cliente;
import clientes.Direccion;
import clientes.Empresa;
import clientes.Particular;
import es.uji.belfern.generador.GeneradorDatosINE;
import facturas.Tarifa;
import gestion.Gestion;
import org.junit.jupiter.api.*;

import static org.junit.Assert.*;

public class TestClientes {

    private static GeneradorDatosINE generador;
    private static Cliente empresa;
    private static Cliente particular;
    private static Cliente hombre;
    private static Cliente mujer;
    private Gestion gestion;

    @BeforeAll
    public static void init() {
        generador = new GeneradorDatosINE();
        empresa = new Empresa(generador.getNIF(), generador.getNombre(), "empresa@uji.es", new Direccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new Tarifa(10));
        particular = new Particular(generador.getNIF(), generador.getNombre(), generador.getApellido(), "particular@gmail.com", new Direccion(54321, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new Tarifa(5));
        hombre = new Particular(generador.getNIF(), generador.getNombreHombre(), generador.getApellido(), "hombre@hotmail.com", new Direccion(11111, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new Tarifa(15));
        mujer = new Particular(generador.getNIF(), generador.getNombreMujer(), generador.getApellido(), "mujer@yahoo.es", new Direccion(55555, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new Tarifa(1));
    }

    @AfterAll
    public static void finish() {
        generador = null;
        empresa = null;
        particular = null;
        hombre = null;
        mujer = null;
    }

    @BeforeEach
    public void setUp() {
        gestion = new Gestion();
    }

    @AfterEach
    public void tearDown() {
        gestion = null;
    }

    @Test
    public void testDarDeAltaCliente() {
        assertTrue(gestion.darDeAltaCliente(empresa));
        assertTrue(gestion.darDeAltaCliente(particular));
        assertTrue(gestion.darDeAltaCliente(hombre));
        assertTrue(gestion.darDeAltaCliente(mujer));
    }

    @Test
    public void testDarDeBaja() {
        gestion.darDeAltaCliente(empresa);
        gestion.darDeAltaCliente(particular);
        gestion.darDeAltaCliente(hombre);
        gestion.darDeAltaCliente(mujer);

        assertTrue(gestion.darDeBaja(empresa.getNif()));
        assertTrue(gestion.darDeBaja(particular.getNif()));
        assertTrue(gestion.darDeBaja(hombre.getNif()));
        assertTrue(gestion.darDeBaja(mujer.getNif()));
    }

    @Test
    public void testCambiarTarifa() {
        gestion.darDeAltaCliente(empresa);
        gestion.darDeAltaCliente(particular);
        gestion.darDeAltaCliente(hombre);
        gestion.darDeAltaCliente(mujer);

        assertTrue(gestion.cambiarTarifa(empresa.getNif(), new Tarifa(50)));
        assertTrue(gestion.cambiarTarifa(particular.getNif(), new Tarifa(1)));
        assertTrue(gestion.cambiarTarifa(hombre.getNif(), new Tarifa(15)));
        assertTrue(gestion.cambiarTarifa(mujer.getNif(), new Tarifa(10)));
    }

    @Test
    public void testMostrarCliente() {
        gestion.darDeAltaCliente(empresa);
        gestion.darDeAltaCliente(particular);
        gestion.darDeAltaCliente(hombre);
        gestion.darDeAltaCliente(mujer);

        assertNotNull(gestion.mostrarCliente(empresa.getNif()));
        assertNotNull(gestion.mostrarCliente(particular.getNif()));
        assertNotNull(gestion.mostrarCliente(hombre.getNif()));
        assertNotNull(gestion.mostrarCliente(mujer.getNif()));
    }

    @Test
    public void testListarClientes() {
        gestion.darDeAltaCliente(empresa);
        gestion.darDeAltaCliente(particular);
        gestion.darDeAltaCliente(hombre);
        gestion.darDeAltaCliente(mujer);

        assertNotNull(gestion.listarClientes());
    }

}
