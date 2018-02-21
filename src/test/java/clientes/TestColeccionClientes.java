package clientes;

import es.uji.belfern.generador.GeneradorDatosINE;
import facturas.Tarifa;
import gestion.Gestion;
import org.junit.jupiter.api.*;

import static org.junit.Assert.*;

public class TestColeccionClientes {

    private Gestion gestion;
    private static GeneradorDatosINE generador;
    private static Cliente empresa;
    private static Cliente particular;
    private static Cliente hombre;
    private static Cliente mujer;

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

        assertFalse(gestion.darDeAltaCliente(empresa));
        assertFalse(gestion.darDeAltaCliente(particular));
        assertFalse(gestion.darDeAltaCliente(hombre));
        assertFalse(gestion.darDeAltaCliente(mujer));
    }

    @Test
    public void testDarDeBaja() {
        gestion.darDeAltaCliente(empresa);
        gestion.darDeAltaCliente(particular);
        gestion.darDeAltaCliente(hombre);
        gestion.darDeAltaCliente(mujer);

        assertTrue(gestion.darDeBaja(empresa));
        assertTrue(gestion.darDeBaja(particular));
        assertTrue(gestion.darDeBaja(hombre));
        assertTrue(gestion.darDeBaja(mujer));

        assertNull(gestion.getCliente(empresa.getNif()));
        assertNull(gestion.getCliente(particular.getNif()));
        assertNull(gestion.getCliente(hombre.getNif()));
        assertNull(gestion.getCliente(mujer.getNif()));
    }

    @Test
    public void testCambiarTarifa() {
        gestion.darDeAltaCliente(empresa);
        gestion.darDeAltaCliente(particular);
        gestion.darDeAltaCliente(hombre);
        gestion.darDeAltaCliente(mujer);

        assertTrue(gestion.cambiarTarifa(empresa, new Tarifa(50)));
        assertTrue(gestion.cambiarTarifa(particular, new Tarifa(1)));
        assertTrue(gestion.cambiarTarifa(hombre, new Tarifa(15)));
        assertTrue(gestion.cambiarTarifa(mujer, new Tarifa(10)));
    }

}
