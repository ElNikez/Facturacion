package facturacion.tarifas;

import facturacion.facturas.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static facturacion.facturas.Tarifa.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class TestTarifas {

    private static Llamada llamada2;
    private static Llamada llamada3;
    private static Llamada llamada1;
    private static TarifaBasica tarifaBasica;
    private static PromocionMadrugadas tarifaMadrugada;
    private static PromocionTardes tarifaTarde;
    private static PromocionDomingos tarifaDomingo;
    private static PromocionFestivos tarifaFestivo;

    @BeforeAll
    static void init() {
        llamada1 = new Llamada(666236666, 400);
        llamada2 = new Llamada(666666666, 500);
        llamada3 = new Llamada(123456789, 600);

        tarifaBasica = new TarifaBasica(PRECIO_BASICA);
        tarifaMadrugada = new PromocionMadrugadas(tarifaBasica, PRECIO_MADRUGADA);
        tarifaTarde = new PromocionTardes(tarifaBasica, PRECIO_TARDE);
        tarifaDomingo = new PromocionDomingos(tarifaBasica, PRECIO_DOMINGO);
        tarifaFestivo = new PromocionFestivos(tarifaBasica, PRECIO_FESTIVO);

        llamada1.getFecha().set(Calendar.DAY_OF_WEEK, 7);
        llamada2.getFecha().set(Calendar.HOUR, 16);
        llamada3.getFecha().set(Calendar.HOUR, 8);
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
        tarifaFestivos = null;
    }

    @Test
    void testTarifaBasica() {

    }

    @DisplayName("Calcular precio llamada")
    @Test
    void testPrecioLlamada() {
        tarifaBasica = new PromocionTardes(tarifaBasica, 10);
        assertThat(tarifaBasica.calcularPrecioLlamada(llamada2), is(5000.0f));

        tarifaBasica = new PromocionMadrugadas(tarifaBasica, 10);
        assertThat(tarifaBasica.calcularPrecioLlamada(llamada3), is(6000.0f));

        tarifaBasica = new PromocionDomingos(tarifaBasica, 0);
        assertThat(tarifaBasica.calcularPrecioLlamada(llamada1), is(0.0f));
    }

}
