package swing.modelo.facturas;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import static java.util.Calendar.*;

public enum DiasFestivos {

    AÑO_NUEVO(1, JANUARY),
    EPIFANIA(6, JANUARY),
    DIA_TRABAJADOR(1, MAY),
    FIESTA_NACIONAL(12, OCTOBER),
    TODOS_SANTOS(1, NOVEMBER),
    CONSTITUCION(6, DECEMBER),
    NAVIDAD(25, DECEMBER);

    private int dia, mes;

    DiasFestivos(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    public static DiasFestivos festivo(int posicion) {
        return values()[posicion];
    }

    public static List<Calendar> festivos() {
        List<Calendar> diasFestivos = new LinkedList<>();
        Calendar fecha = GregorianCalendar.getInstance();

        for(DiasFestivos festivo : values())
            diasFestivos.add(new GregorianCalendar(fecha.get(YEAR), festivo.mes, festivo.dia));

        return diasFestivos;
    }

}
