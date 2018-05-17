package facturacion.tarifas;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import swing.modelo.factorias.FactoriaTarifas;
import swing.modelo.facturas.DiasFestivos;
import swing.modelo.facturas.Llamada;
import swing.modelo.facturas.Tarifa;
import swing.modelo.facturas.TipoPromocion;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class TestTarifas {

    private static FactoriaTarifas factoriaTarifas = new FactoriaTarifas();
    private static Llamada llamada2;
    private static Llamada llamada3;
    private static Llamada llamada1;

    private static Calendar fecha;

    private static Tarifa tarifaBasica;
    private static Tarifa tarifaMadrugada;
    private static Tarifa tarifaTarde;
    private static Tarifa tarifaDomingo;
    private static Tarifa tarifaFestivo;
    private static Tarifa tarifaMadrugadaDomingo;
    private static Tarifa tarifaTardeDomingo;
    private static Tarifa tarifaMadrugadaFestivo;
    private static Tarifa tarifaTardeFestivo;

    @BeforeAll
    static void init() {
        llamada1 = new Llamada(666236666, 100);
        llamada2 = new Llamada(666666666, 500);
        llamada3 = new Llamada(123456789, 1000);

        fecha = GregorianCalendar.getInstance();

        tarifaBasica = factoriaTarifas.crearTarifa();
        tarifaMadrugada = factoriaTarifas.añadirPromocion(tarifaBasica, TipoPromocion.MADRUGADA);
        tarifaTarde = factoriaTarifas.añadirPromocion(tarifaBasica, TipoPromocion.TARDE);
        tarifaDomingo = factoriaTarifas.añadirPromocion(tarifaBasica, TipoPromocion.DOMINGO);
        tarifaFestivo = factoriaTarifas.añadirPromocion(tarifaBasica, TipoPromocion.FESTIVO);

        tarifaMadrugadaDomingo = factoriaTarifas.añadirPromocion(tarifaMadrugada, TipoPromocion.DOMINGO);
        tarifaTardeDomingo = factoriaTarifas.añadirPromocion(tarifaTarde, TipoPromocion.DOMINGO);
        tarifaMadrugadaFestivo = factoriaTarifas.añadirPromocion(tarifaMadrugada, TipoPromocion.FESTIVO);
        tarifaTardeFestivo = factoriaTarifas.añadirPromocion(tarifaTarde, TipoPromocion.FESTIVO);
    }

    @AfterAll
    static void finish() {
        llamada1 = null;
        llamada2 = null;
        llamada3 = null;
        tarifaBasica = null;
        tarifaMadrugada = null;
        tarifaTarde = null;
        tarifaDomingo = null;
        tarifaFestivo = null;
    }

    @DisplayName("Calcular tarifa básica")
    @Test
    void testTarifaBasica() {
        assertAll(
                () -> assertThat(tarifaBasica.calcularPrecioLlamada(llamada1), is(1500f)),
                () -> assertThat(tarifaBasica.calcularPrecioLlamada(llamada2), is(7500f)),
                () -> assertThat(tarifaBasica.calcularPrecioLlamada(llamada3), is(15000f))
        );
    }

    @DisplayName("Calcular tarifa madrugadas")
    @Test
    void testTarifaMadrugada() {
        fecha.set(Calendar.HOUR_OF_DAY, 1);

        llamada1.cambiarFecha(fecha);
        llamada2.cambiarFecha(fecha);
        llamada3.cambiarFecha(fecha);

        assertAll(
                () -> assertThat(tarifaMadrugada.calcularPrecioLlamada(llamada1), is(500f)),
                () -> assertThat(tarifaMadrugada.calcularPrecioLlamada(llamada2), is(2500f)),
                () -> assertThat(tarifaMadrugada.calcularPrecioLlamada(llamada3), is(5000f))
        );
    }

    @DisplayName("Calcular tarifa tardes")
    @Test
    void testTarifaTarde() {
        llamada1.fecha().set(Calendar.HOUR_OF_DAY, 20);
        llamada2.fecha().set(Calendar.HOUR_OF_DAY, 20);
        llamada3.fecha().set(Calendar.HOUR_OF_DAY, 20);

        assertAll(
                () -> assertThat(tarifaTarde.calcularPrecioLlamada(llamada1), is(1000f)),
                () -> assertThat(tarifaTarde.calcularPrecioLlamada(llamada2), is(5000f)),
                () -> assertThat(tarifaTarde.calcularPrecioLlamada(llamada3), is(10000f))
        );
    }

    @DisplayName("Domingos y festivos")
    @Test
    void testTarifaFestivo() {
        assertAll(
                () -> assertThat(tarifaDomingo.calcularPrecioLlamada(llamada1), is(0f)),
                () -> assertThat(tarifaDomingo.calcularPrecioLlamada(llamada2), is(0f)),
                () -> assertThat(tarifaDomingo.calcularPrecioLlamada(llamada3), is(0f))
        );

        List<Calendar> diasFestivos = DiasFestivos.festivos();
        llamada1.fecha().set(Calendar.DAY_OF_MONTH, diasFestivos.get(0).get(Calendar.DAY_OF_MONTH));
        llamada1.fecha().set(Calendar.MONTH, diasFestivos.get(0).get(Calendar.MONTH));
        llamada2.fecha().set(Calendar.DAY_OF_MONTH, diasFestivos.get(diasFestivos.size() / 2).get(Calendar.DAY_OF_MONTH));
        llamada2.fecha().set(Calendar.MONTH, diasFestivos.get(0).get(Calendar.MONTH));
        llamada3.fecha().set(Calendar.DAY_OF_MONTH, diasFestivos.get(diasFestivos.size() - 1).get(Calendar.DAY_OF_MONTH));
        llamada3.fecha().set(Calendar.MONTH, diasFestivos.get(0).get(Calendar.MONTH));

        assertAll(
                () -> assertThat(tarifaFestivo.calcularPrecioLlamada(llamada1), is(0f)),
                () -> assertThat(tarifaFestivo.calcularPrecioLlamada(llamada2), is(0f)),
                () -> assertThat(tarifaFestivo.calcularPrecioLlamada(llamada3), is(0f))
        );
    }

    @DisplayName("Madrugadas y tardes de domingo")
    @Test
    void testTarifaMadrugadaTardeDomingo() {
        llamada1.fecha().set(Calendar.HOUR_OF_DAY, 0);
        llamada1.fecha().set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        llamada2.fecha().set(Calendar.HOUR_OF_DAY, 0);
        llamada2.fecha().set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        llamada3.fecha().set(Calendar.HOUR_OF_DAY, 0);
        llamada3.fecha().set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        assertAll(
                () -> assertThat(tarifaMadrugadaDomingo.calcularPrecioLlamada(llamada1), is(0f)),
                () -> assertThat(tarifaMadrugadaDomingo.calcularPrecioLlamada(llamada2), is(0f)),
                () -> assertThat(tarifaMadrugadaDomingo.calcularPrecioLlamada(llamada3), is(0f))
        );
        llamada1.fecha().set(Calendar.HOUR_OF_DAY, 20);
        llamada1.fecha().set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        llamada2.fecha().set(Calendar.HOUR_OF_DAY, 20);
        llamada2.fecha().set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        llamada3.fecha().set(Calendar.HOUR_OF_DAY, 20);
        llamada3.fecha().set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        assertAll(
                () -> assertThat(tarifaTardeDomingo.calcularPrecioLlamada(llamada1), is(0f)),
                () -> assertThat(tarifaTardeDomingo.calcularPrecioLlamada(llamada2), is(0f)),
                () -> assertThat(tarifaTardeDomingo.calcularPrecioLlamada(llamada3), is(0f))
        );
    }

}
