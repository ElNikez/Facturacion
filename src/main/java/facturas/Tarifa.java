package facturas;

public class Tarifa {

    private float precio;

    public Tarifa(){
        super();
    }

    public Tarifa(float precio){

        this.precio= precio;
    }

    public Tarifa(Tarifa tarifa){

        this.precio= tarifa.precio;
    }

    public float getPrecio(){

        return precio;
    }
}
