package facturacion.clientes;

import facturacion.facturas.Tarifa;

import java.util.Calendar;

public class Empresa extends Cliente {

    public Empresa() {
        super();
    }

    public Empresa(String nif, String nombre, String correoElectronico, Direccion direccion, Tarifa tarifa) {
        super(nif, nombre, correoElectronico, direccion,tarifa);
    }

    public Empresa(Empresa empresa) {
        super(empresa);
    }

    @Override
    public String toString() {
        return "Empresa{" + "\n" +
                "   nif=" + getNif() + "\n" +
                "   nombre=" + getNombre() + "\n" +
                "   correoElectronico=" + getCorreo() + "\n" +
                "   direccion=" + getDireccion() + "\n" +
                "   fechaDeAlta=" + getFecha().get(Calendar.DAY_OF_MONTH) + "/" + (getFecha().get(Calendar.MONTH) + 1) + "/" + getFecha().get(Calendar.YEAR) + "\n" +
                "   tarifa=" + getTarifa().getPrecio() + "e/min" + "\n" +
                "   }" + "\n";
    }

}
