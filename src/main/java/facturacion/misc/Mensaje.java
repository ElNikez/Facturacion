package facturacion.misc;

public enum Mensaje {

    // MENÚ
    MENU_GESTION("\nMENÚ DE GESTIÓN DE DATOS\n"),
    MENU_CLIENTES("\nMENÚ DE GESTIÓN DE CLIENTES\n"),

    // OPERACIONES
    INTRODUCE_OPCION("\nIntroduce una opción: "),
    INTRODUCE_NIF("Introduce el NIF: "),
    INTRODUCE_NOMBRE("Introduce el nombre: "),
    INTRODUCE_APELLIDOS("Introduce los apellidos: "),
    INTRODUCE_CORREO("Introduce el correo electrónico: "),
    INTRODUCE_COD_POSTAL("Introduce el código postal: "),
    INTRODUCE_POBLACION("Introduce la población: "),
    INTRODUCE_PROVINCIA("Introduce la provincia: "),
    INTRODUCE_TARIFA("Introduce la tarifa (en euros): "),
    INTRODUCE_TELEFONO("Introduce el número de teléfono: "),
    INTRODUCE_DURACION("Introduce la duración (en ms): "),
    INTRODUCE_CODIGO("Introduce el código de la factura: "),
    INTRODUCE_FECHA_INICIO("Introduce una fecha de inicio (dd/mm/aaaa): "),
    INTRODUCE_FECHA_FINAL("Introduce una fecha de fin (dd/mm/aaaa): "),

    // RESULTADO DE OPERACIONES
    CLIENTE_DAR_DE_ALTA("\nCLIENTE DADO DE ALTA CON ÉXITO\n"),
    CLIENTE_DAR_DE_BAJA("\nCLIENTE DADO DE BAJA CON ÉXITO\n"),
    CLIENTE_CAMBIAR_TARIFA("\nTARIFA CAMBIADA CON ÉXITO\n"),
    LLAMADA_CREADA("\nLLAMADA REGISTRADA CON ÉXITO\n"),
    FACTURA_EMITIDA("\nFACTURA EMITIDA CON ÉXITO\n"),
    LISTA_CLIENTES("\nLISTA DE CLIENTES: \n"),
    LISTA_LLAMADAS("\nLISTA DE LLAMADAS: \n"),
    LISTA_FACTURAS("\nLISTA DE FACTURAS: \n"),

    // EXCEPCIONES
    CLIENTE_YA_EXISTE("\nEL CLIENTE YA EXISTE EN LA BASE DE DATOS\n"),
    CLIENTE_NO_EXISTE("\nEL CLIENTE NO EXISTE EN LA BASE DE DATOS\n"),
    CLIENTE_NO_LLAMADAS("\nEL CLIENTE NO HA REALIZADO NINGUNA LLAMADA\n"),
    CLIENTE_NO_FACTURAS("\nEL CLIENTE NO TIENE NINGUNA FACTURA EMITIDA\n"),
    FACTURA_NO_EXISTE("\nLA FACTURA NO EXISTE\n"),
    FECHAS_NO_VALIDAS("\nLAS FECHAS INTRODUCIDAS NO SON CORRECTAS"),
    CLIENTES_VACIO("\nLA BASE DE DATOS DE CLIENTES ESTÁ VACÍA\n"),
    LLAMADAS_VACIO("\nLA BASE DE DATOS DE LLAMADAS ESTÁ VACÍA\n"),
    FACTURAS_VACIO("\nLA BASE DE DATOS DE FACTURAS ESTÁ VACÍA\n"),

    // MENSAJES DEL SISTEMA
    SALIR("\nAPAGANDO SERVIDOR\n");

    public String mensaje;

    Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String mostrarMensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return mostrarMensaje();
    }

}
