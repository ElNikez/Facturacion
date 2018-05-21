package swing.modelo.facturas;

public enum TipoPromocion {

    BASICA("Tarifa básica"),
    MADRUGADA("Madrugadas extra-reducidas"),
    TARDE("Tardes reducidas"),
    DOMINGO("Domingos gratis"),
    FESTIVO("Festivos gratis");

    private String descripcion;

    TipoPromocion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String descripcion() {
        return descripcion;
    }

    public static TipoPromocion promocion(int posicion) {
        return values()[posicion];
    }

}
