package swing.modelo;

import es.uji.belfern.generador.GeneradorDatosINE;
import facturacion.clientes.Cliente;
import facturacion.clientes.Direccion;
import facturacion.clientes.Empresa;
import facturacion.clientes.Particular;
import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteYaExiste;
import facturacion.excepciones.ListaClientesVacio;
import facturacion.facturas.Tarifa;
import facturacion.facturas.TarifaBasica;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;
import org.junit.jupiter.api.*;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test clientes")
class TestClientes {

    private static Gestion gestion;
    private static GeneradorDatosINE generador;
    private static Cliente empresa;
    private static Cliente particular;
    private static Cliente hombre;
    private static Cliente mujer;

    @BeforeAll
    static void init() {
        generador = new GeneradorDatosINE();
        empresa = new Empresa(generador.getNIF(), generador.getNombre(), "empresa@uji.es", new Direccion(12345, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new TarifaBasica(10));
        particular = new Particular(generador.getNIF(), generador.getNombre(), generador.getApellido(), "particular@gmail.com", new Direccion(54321, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new TarifaBasica(5));
        hombre = new Particular(generador.getNIF(), generador.getNombreHombre(), generador.getApellido(), "hombre@hotmail.com", new Direccion(11111, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new TarifaBasica(15));
        mujer = new Particular(generador.getNIF(), generador.getNombreMujer(), generador.getApellido(), "mujer@yahoo.es", new Direccion(55555, generador.getPoblacion(generador.getProvincia()), generador.getProvincia()), new TarifaBasica(1));
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
                () -> assertTrue(gestion.darDeAltaCliente(empresa)),
                () -> assertTrue(gestion.darDeAltaCliente(particular)),
                () -> assertTrue(gestion.darDeAltaCliente(hombre)),
                () -> assertTrue(gestion.darDeAltaCliente(mujer))
        );
    }

    @DisplayName("Dar de baja")
    @Test
    void testDarDeBaja() throws ClienteYaExiste {
        gestion.darDeAltaCliente(empresa);
        gestion.darDeAltaCliente(particular);
        gestion.darDeAltaCliente(hombre);
        gestion.darDeAltaCliente(mujer);

        assertAll(
                () -> assertTrue(gestion.darDeBajaCliente(empresa.getNif())),
                () -> assertTrue(gestion.darDeBajaCliente(particular.getNif())),
                () -> assertTrue(gestion.darDeBajaCliente(hombre.getNif())),
                () -> assertTrue(gestion.darDeBajaCliente(mujer.getNif()))
        );
    }

    @DisplayName("Cambiar la tarifa")
    @Test
    void testCambiarTarifa() throws ClienteYaExiste {
        gestion.darDeAltaCliente(empresa);
        gestion.darDeAltaCliente(particular);
        gestion.darDeAltaCliente(hombre);
        gestion.darDeAltaCliente(mujer);

        Tarifa tarifaEmpresa = empresa.getTarifa();
        Tarifa tarifaParticular = particular.getTarifa();
        Tarifa tarifaHombre = hombre.getTarifa();
        Tarifa tarifaMujer = mujer.getTarifa();

        assertAll(
                () -> assertTrue(gestion.cambiarTarifa(empresa.getNif(), tarifaParticular)),
                () -> assertTrue(gestion.cambiarTarifa(particular.getNif(), tarifaEmpresa)),
                () -> assertTrue(gestion.cambiarTarifa(hombre.getNif(), tarifaMujer)),
                () -> assertTrue(gestion.cambiarTarifa(mujer.getNif(), tarifaHombre))
        );
    }

    @DisplayName("Mostrar un cliente")
    @Test
    void testMostrarCliente() throws ClienteYaExiste {
        gestion.darDeAltaCliente(empresa);
        gestion.darDeAltaCliente(particular);
        gestion.darDeAltaCliente(hombre);
        gestion.darDeAltaCliente(mujer);

        assertAll(
                () -> assertThat(gestion.mostrarCliente(empresa.getNif()), is(empresa)),
                () -> assertThat(gestion.mostrarCliente(particular.getNif()), is(particular)),
                () -> assertThat(gestion.mostrarCliente(hombre.getNif()), is(hombre)),
                () -> assertThat(gestion.mostrarCliente(mujer.getNif()), is(mujer))
        );
    }

    @DisplayName("Mostrar los clientes")
    @Test
    void testListarClientes() throws ClienteYaExiste, ListaClientesVacio {
        gestion.darDeAltaCliente(empresa);
        gestion.darDeAltaCliente(particular);
        gestion.darDeAltaCliente(hombre);
        gestion.darDeAltaCliente(mujer);

        assertNotNull(gestion.listarClientes());
    }

    @DisplayName("Clientes entre fechas")
    @Test
    void testListarClientesEntreFechas() throws ClienteYaExiste, ListaClientesVacio {
        gestion.darDeAltaCliente(empresa);
        gestion.darDeAltaCliente(particular);
        gestion.darDeAltaCliente(hombre);
        gestion.darDeAltaCliente(mujer);

        GestionEntreFechas<Cliente> entreFechas = new GestionEntreFechas<>();
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.set(Calendar.MONTH, fechaInicio.get(Calendar.MONTH) - 1);
        Calendar fechaFinal = Calendar.getInstance();

        assertNotNull(entreFechas.muestraColeccionEntreFechas(gestion.listarClientes(), fechaInicio, fechaFinal));
    }

    @DisplayName("Excepciones")
    @Test
    void testExcepciones() throws ClienteYaExiste, ClienteNoEncontrado {
        assertThrows(ListaClientesVacio.class, () -> gestion.listarClientes());

        gestion.darDeAltaCliente(empresa);
        gestion.darDeAltaCliente(particular);
        gestion.darDeAltaCliente(hombre);
        gestion.darDeAltaCliente(mujer);

        assertAll("ClienteYaExiste",
                () -> assertThrows(ClienteYaExiste.class, () -> {
                    gestion.darDeAltaCliente(empresa);
                    gestion.darDeAltaCliente(particular);
                    gestion.darDeAltaCliente(hombre);
                    gestion.darDeAltaCliente(mujer);
                })
        );

        gestion.darDeBajaCliente(empresa.getNif());
        gestion.darDeBajaCliente(particular.getNif());
        gestion.darDeBajaCliente(hombre.getNif());
        gestion.darDeBajaCliente(mujer.getNif());

        assertAll("ClienteNoEncontrado",
                () -> assertThrows(ClienteNoEncontrado.class, () -> {
                    gestion.darDeBajaCliente(empresa.getNif());
                    gestion.darDeBajaCliente(particular.getNif());
                    gestion.darDeBajaCliente(hombre.getNif());
                    gestion.darDeBajaCliente(mujer.getNif());

                    gestion.mostrarCliente(empresa.getNif());
                    gestion.mostrarCliente(particular.getNif());
                    gestion.mostrarCliente(hombre.getNif());
                    gestion.mostrarCliente(mujer.getNif());
                })
        );
    }

}
