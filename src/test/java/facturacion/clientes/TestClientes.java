package facturacion.clientes;

import es.uji.belfern.generador.GeneradorDatosINE;
import org.junit.jupiter.api.*;
import swing.modelo.clientes.Cliente;
import swing.modelo.excepciones.ClienteYaExiste;
import swing.modelo.excepciones.ListaClientesVacio;
import swing.modelo.factorias.FactoriaClientes;
import swing.modelo.factorias.FactoriaTarifas;
import swing.modelo.facturas.Tarifa;
import swing.modelo.gestion.Gestion;
import swing.modelo.gestion.GestionEntreFechas;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test clientes")
class TestClientes {

    private static FactoriaClientes factoriaClientes = new FactoriaClientes();
    private static FactoriaTarifas factoriaTarifas = new FactoriaTarifas();
    private static Gestion gestion;
    private static GeneradorDatosINE generador;
    private static Cliente empresa;
    private static Cliente particular;
    private static Cliente hombre;
    private static Cliente mujer;

    @BeforeAll
    static void init() {
        generador = new GeneradorDatosINE();
        empresa = factoriaClientes.crearEmpresa(generador.getNIF(), generador.getNombre(), "empresa@uji.es", factoriaClientes.crearDireccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), factoriaTarifas.crearTarifa());
        particular = factoriaClientes.crearParticular(generador.getNIF(), generador.getNombre(), generador.getApellido(), "particular@gmail.com", factoriaClientes.crearDireccion(54321, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), factoriaTarifas.crearTarifa());
        hombre = factoriaClientes.crearParticular(generador.getNIF(), generador.getNombreHombre(), generador.getApellido(), "hombre@hotmail.com", factoriaClientes.crearDireccion(11111, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), factoriaTarifas.crearTarifa());
        mujer = factoriaClientes.crearParticular(generador.getNIF(), generador.getNombreMujer(), generador.getApellido(), "mujer@yahoo.es", factoriaClientes.crearDireccion(55555, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), factoriaTarifas.crearTarifa());
    }

    @AfterAll
    static void finish() {
        generador = null;
        empresa = null;
        particular = null;
        hombre = null;
        mujer = null;
    }

    @BeforeEach
    void setUp() {
        gestion = new Gestion();
    }

    @AfterEach
    void tearDown() {
        gestion = null;
    }

    @DisplayName("Dar de alta")
    @Test
    void testDarDeAltaCliente() {
        assertAll(
                () -> assertTrue(gestion.darAltaCliente(empresa)),
                () -> assertTrue(gestion.darAltaCliente(particular)),
                () -> assertTrue(gestion.darAltaCliente(hombre)),
                () -> assertTrue(gestion.darAltaCliente(mujer))
        );
    }

    @DisplayName("Dar de baja")
    @Test
    void testDarDeBaja() {
        try {
            gestion.darAltaCliente(empresa);
            gestion.darAltaCliente(particular);
            gestion.darAltaCliente(hombre);
            gestion.darAltaCliente(mujer);
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.printStackTrace();
        }

        assertAll(
                () -> assertTrue(gestion.darBajaCliente(empresa.nif())),
                () -> assertTrue(gestion.darBajaCliente(particular.nif())),
                () -> assertTrue(gestion.darBajaCliente(hombre.nif())),
                () -> assertTrue(gestion.darBajaCliente(mujer.nif()))
        );
    }

    @DisplayName("Cambiar la tarifa")
    @Test
    void testCambiarTarifa() {
        try {
            gestion.darAltaCliente(empresa);
            gestion.darAltaCliente(particular);
            gestion.darAltaCliente(hombre);
            gestion.darAltaCliente(mujer);
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.printStackTrace();
        }

        Tarifa tarifaEmpresa = empresa.tarifa();
        Tarifa tarifaParticular = particular.tarifa();
        Tarifa tarifaHombre = hombre.tarifa();
        Tarifa tarifaMujer = mujer.tarifa();

        assertAll(
                () -> assertTrue(gestion.cambiarTarifa(empresa.nif(), tarifaParticular)),
                () -> assertTrue(gestion.cambiarTarifa(particular.nif(), tarifaEmpresa)),
                () -> assertTrue(gestion.cambiarTarifa(hombre.nif(), tarifaMujer)),
                () -> assertTrue(gestion.cambiarTarifa(mujer.nif(), tarifaHombre))
        );
    }

    @DisplayName("Mostrar un cliente")
    @Test
    void testMostrarCliente() {
        try {
            gestion.darAltaCliente(empresa);
            gestion.darAltaCliente(particular);
            gestion.darAltaCliente(hombre);
            gestion.darAltaCliente(mujer);
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.printStackTrace();
        }

        assertAll(
                () -> assertThat(gestion.mostrarCliente(empresa.nif()), is(empresa)),
                () -> assertThat(gestion.mostrarCliente(particular.nif()), is(particular)),
                () -> assertThat(gestion.mostrarCliente(hombre.nif()), is(hombre)),
                () -> assertThat(gestion.mostrarCliente(mujer.nif()), is(mujer))
        );
    }

    @DisplayName("Mostrar los clientes")
    @Test
    void testListarClientes() {
        try {
            gestion.darAltaCliente(empresa);
            gestion.darAltaCliente(particular);
            gestion.darAltaCliente(hombre);
            gestion.darAltaCliente(mujer);
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.printStackTrace();
        }

        try {
            assertNotNull(gestion.listarClientes());
        } catch (ListaClientesVacio listaClientesVacio) {
            listaClientesVacio.printStackTrace();
        }
    }

    @DisplayName("Clientes entre fechas")
    @Test
    void testListarClientesEntreFechas() {
        try {
            gestion.darAltaCliente(empresa);
            gestion.darAltaCliente(particular);
            gestion.darAltaCliente(hombre);
            gestion.darAltaCliente(mujer);
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.printStackTrace();
        }

        GestionEntreFechas<Cliente> gestionFechas = new GestionEntreFechas<>();
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.set(Calendar.MONTH, fechaInicio.get(Calendar.MONTH) - 1);
        Calendar fechaFinal = Calendar.getInstance();

        try {
            assertNotNull(gestionFechas.entreFechas(gestion.listarClientes(), fechaInicio, fechaFinal));
        } catch (ListaClientesVacio listaClientesVacio) {
            listaClientesVacio.printStackTrace();
        }
    }

}
