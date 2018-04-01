package facturacion.tarifas;

import es.uji.belfern.generador.GeneradorDatosINE;
import facturacion.clientes.Cliente;
import facturacion.clientes.Direccion;
import facturacion.clientes.Empresa;
import facturacion.excepciones.ClienteYaExiste;
import facturacion.facturas.*;
import facturacion.gestion.Gestion;
import facturacion.gestion.GestionEntreFechas;
import org.junit.jupiter.api.*;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestTarifas {


    private static Llamada llamada2;
    private static Llamada llamada3;
    private static Llamada llamada1;
    private static TarifaBasica tarifa1;


    @BeforeAll
    public static void init() {

        llamada1= new Llamada(666236666, 400);
        llamada2 = new Llamada(666666666, 500);
        llamada3 = new Llamada(123456789, 600);
        tarifa1 = new TarifaBasica(20);
        llamada1.getFecha().set(Calendar.DAY_OF_WEEK,7);
        llamada2.getFecha().set(Calendar.HOUR,16);
        llamada3.getFecha().set(Calendar.HOUR,8);



    }

    @AfterAll
    public static void finish() {

        llamada1 = null;
        llamada2 = null;
        llamada3 = null;
        tarifa1 = null;

    }


    @DisplayName("CalcularPrecioLlamada")
    @Test
    public void testPrecioLlamada() {


        tarifa1 = new ConTarifaTardesReducida(tarifa1, 10);
        assertThat(tarifa1.calculaPrecioLlamada(llamada2), is(5000.0f));

        tarifa1 = new ConTarifaMañanasReducidas(tarifa1, 10);
        assertThat(tarifa1.calculaPrecioLlamada(llamada3), is(6000.0f));

        tarifa1 = new ConTarifaDomingo(tarifa1, 0);
        assertThat(tarifa1.calculaPrecioLlamada(llamada1), is(0.0f));
    }


}