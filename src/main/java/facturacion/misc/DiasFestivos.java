package facturacion.misc;

import java.util.*;

import static java.util.GregorianCalendar.*;

public enum DiasFestivos {

    AÑO_NUEVO(1, JANUARY),
    EPIFANIA(6, JANUARY),
    DIA_TRABAJADOR(1, MAY),
    FIESTA_NACIONAL(12, OCTOBER),
    TODOS_SANTOS(1, NOVEMBER),
    CONSTITUCION(6, DECEMBER),
    NAVIDAD(25, DECEMBER);

    private int dia;
    private int mes;

    DiasFestivos(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    public static DiasFestivos festivo(int posicion) {
        return values()[posicion];
    }

    public static List<GregorianCalendar> festivos() {
        List<GregorianCalendar> diasFestivos = new LinkedList<>();
        Calendar fecha = GregorianCalendar.getInstance();

        for(DiasFestivos festivo : values())
            diasFestivos.add(new GregorianCalendar(fecha.get(YEAR), festivo.mes, festivo.dia));

        return diasFestivos;
    }

    public int dia() {
        return dia;
    }

    public int mes() {
        return mes;
    }
}
