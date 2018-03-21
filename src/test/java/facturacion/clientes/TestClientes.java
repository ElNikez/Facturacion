package facturacion.clientes;

import es.uji.belfern.generador.GeneradorDatosINE;
import facturacion.excepciones.ClienteNoEncontrado;
import facturacion.excepciones.ClienteYaExiste;
import facturacion.excepciones.ListaClientesVacio;
import facturacion.facturas.Tarifa;
import facturacion.gestion.Gestion;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test clientes")
public class TestClientes {

    private static Gestion gestion;
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

    @DisplayName("Dar de alta")
    @Test
    public void testDarDeAltaCliente() {
        assertAll("Dar de alta",
                () -> assertThat(gestion.darDeAltaCliente(empresa), is(empresa)),
                () -> assertThat(gestion.darDeAltaCliente(particular), is(particular)),
                () -> assertThat(gestion.darDeAltaCliente(hombre), is(hombre)),
                () -> assertThat(gestion.darDeAltaCliente(mujer), is(mujer))
        );
    }

    @DisplayName("Dar de baja")
    @Test
    public void testDarDeBaja() {
        try {
            gestion.darDeAltaCliente(empresa);
            gestion.darDeAltaCliente(particular);
            gestion.darDeAltaCliente(hombre);
            gestion.darDeAltaCliente(mujer);
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.getMessage();
        }

        assertAll("Dar de baja",
                () -> assertThat(gestion.darDeBajaCliente(empresa.getNif()), is(empresa)),
                () -> assertThat(gestion.darDeBajaCliente(particular.getNif()), is(particular)),
                () -> assertThat(gestion.darDeBajaCliente(hombre.getNif()), is(hombre)),
                () -> assertThat(gestion.darDeBajaCliente(mujer.getNif()), is(mujer))
        );
    }

    @DisplayName("Cambiar la tarifa")
    @Test
    public void testCambiarTarifa() {
        try {
            gestion.darDeAltaCliente(empresa);
            gestion.darDeAltaCliente(particular);
            gestion.darDeAltaCliente(hombre);
            gestion.darDeAltaCliente(mujer);
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.getMessage();
        }
        Tarifa tarifa5 = new Tarifa(5);
        Tarifa tarifa10 = new Tarifa(10);

        assertAll("Cambiar la tarifa",
                () -> assertThat(gestion.cambiarTarifa(empresa.getNif(), tarifa5), is((tarifa5))),
                () -> assertThat(gestion.cambiarTarifa(particular.getNif(), tarifa10), is(tarifa10)),
                () -> assertThat(gestion.cambiarTarifa(hombre.getNif(), tarifa5), is(not(tarifa10))),
                () -> assertThat(gestion.cambiarTarifa(mujer.getNif(), tarifa10), is(not(tarifa5)))
        );
    }

    @DisplayName("Mostrar un cliente")
    @Test
    public void testMostrarCliente() {
        try {
            gestion.darDeAltaCliente(empresa);
            gestion.darDeAltaCliente(particular);
            gestion.darDeAltaCliente(hombre);
            gestion.darDeAltaCliente(mujer);
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.getMessage();
        }

        assertAll("Mostrar un cliente",
                () -> assertThat(gestion.mostrarCliente(empresa.getNif()), is(empresa)),
                () -> assertThat(gestion.mostrarCliente(particular.getNif()), is(particular)),
                () -> assertThat(gestion.mostrarCliente(hombre.getNif()), is(hombre)),
                () -> assertThat(gestion.mostrarCliente(mujer.getNif()), is(mujer))
        );
    }

    @DisplayName("Mostrar los clientes")
    @Test
    public void testListarClientes() {
        try {
            gestion.darDeAltaCliente(empresa);
            gestion.darDeAltaCliente(particular);
            gestion.darDeAltaCliente(hombre);
            gestion.darDeAltaCliente(mujer);
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.getMessage();
        }

        try {
            assertNotNull(gestion.listarClientes());
        } catch (ListaClientesVacio listaClientesVacio) {
            listaClientesVacio.getMessage();
        }
    }

    @DisplayName("Excepciones")
    @Test
    public void testExcepciones() {
        try {
            gestion.darDeAltaCliente(empresa);
            gestion.darDeAltaCliente(particular);
            gestion.darDeAltaCliente(hombre);
            gestion.darDeAltaCliente(mujer);
        } catch (ClienteYaExiste clienteYaExiste) {
            clienteYaExiste.getMessage();
        }

        assertAll("Excepciones",
                () -> assertThrows(ClienteYaExiste.class, () -> {
                    gestion.darDeAltaCliente(empresa);
                    gestion.darDeAltaCliente(particular);
                    gestion.darDeAltaCliente(hombre);
                    gestion.darDeAltaCliente(mujer);
                })
        );
    }

}
