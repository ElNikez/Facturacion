package misc;

public enum Mensaje {

    // MENU
    MENU_GESTION("\nMENÚ DE GESTIÓN DE DATOS:\n"),
    MENU_CLIENTES("\nMENÚ DE CREACIÓN DE CLIENTES:\n"),
    ELIGE_OPCION("Elige una opción: "),
    TIPO_EMPRESA("Empresa"),
    TIPO_PARTICULAR("Particular"),

    // INTRODUCCION DE DATOS
    INTRODUCIR_NIF("Introduce el NIF: "),
    INTRODUCIR_NOMBRE("Introduce el nombre: "),
    INTRODUCIR_APELLIDOS("Introduce los apellidos: "),
    INTRODUCIR_CODIGO_POSTAL("Introduce el código postal: "),
    INTRODUCIR_POBLACION("Introduce la población: "),
    INTRODUCIR_PROVINCIA("Introduce la provincia: "),
    INTRODUCIR_CORREO("Introduce el correo electrónico: "),
    INTRODUCIR_TARIFA("Introduce la tarifa: "),
    INTRODUCIR_NUMERO("Introduce el número de teléfono:"),
    INTRODUCIR_DURACION("Introduce la duración:"),
    INTRODUCIR_CODIGO_FACTURA("Introduce el código de factura:"),

    // RESULTADO OPERACIONES
    CLIENTE_CREADO("CLIENTE CREADO CON ÉXITO.\n"),
    CLIENTE_BORRADO("CLIENTE BORRADO CON ÉXITO.\n"),
    CLIENTE_TARIFA("TARIFA MODIFICADA CON ÉXITO.\n"),
    CLIENTE_EXISTE("EL CLIENTE YA EXISTE.\n"),
    CLIENTE_NO_EXISTE("EL CLIENTE NO EXISTE.\n"),
    CLIENTE_NO_LLAMADAS("EL CLIENTE NO HA REALIZADO NINGUNA LLAMADA.\n"),
    CLIENTE_NO_FACTURAS("EL CLIENTE NO TIENE NINGUNA FACTURA.\n"),
    LLAMADA_CREADO("LLAMADA CREADA CON ÉXITO.\n"),
    FACTURA_CREADO("FACTURA CREADA CON ÉXITO.\n"),
    FACTURA_NO_EXISTE("LA FACTURA NO EXISTE.\n"),
    LISTA_CLIENTES("LISTA DE CLIENTES:\n"),
    LISTA_LLAMADAS("LISTA DE LLAMADAS:\n"),
    LISTA_FACTURAS("LISTA DE FACTURAS:\n"),
    CLIENTES_VACÍO("LA BASE DE DATOS DE CLIENTES ESTÁ VACÍA.\n"),
    LLAMADAS_VACÍO("LA BASE DE DATOS DE LLAMADAS ESTÁ VACÍA.\n"),
    FACTURAS_VACÍO("LA BASE DE DATOS DE FACTURAS ESTÁ VACÍA.\n"),

    // MENSAJES DEL SISTEMA
    SALIR("Cerrando el programa.\n");

    private String mensaje;

    Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return getMensaje();
    }
}