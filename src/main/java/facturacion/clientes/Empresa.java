package facturacion.clientes;

import facturacion.facturas.Tarifa;

import java.util.GregorianCalendar;

public class Empresa extends Cliente {

    public Empresa(String nif, String nombre, String correoElectronico, Direccion direccion, Tarifa tarifa) {
        super(nif, nombre, correoElectronico, direccion, tarifa);
    }

    @Override
    public String toString() {
        return "Empresa {" + "\n" +
                "   nif=" + getNif() + "\n" +
                "   nombre=" + getNombre() + "\n" +
                "   correoElectronico=" + getCorreo() + "\n" +
                "   direccion=" + getDireccion() + "\n" +
                "   fechaDeAlta=" + getFecha().get(GregorianCalendar.DAY_OF_MONTH) + "/" + (getFecha().get(GregorianCalendar.MONTH) + 1) + "/" + getFecha().get(GregorianCalendar.YEAR) + "\n" +
                "   tarifa=" + getTarifa().descripcion() + "\n" +
                "}" + "\n";
    }

}
