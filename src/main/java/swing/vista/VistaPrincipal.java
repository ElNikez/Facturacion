package swing.vista;

import swing.controlador.Controlador;
import swing.controlador.GestionParaVista;
import swing.controlador.VistaParaControlador;
import swing.modelo.clientes.Cliente;
import swing.modelo.clientes.Particular;
import swing.modelo.excepciones.*;
import swing.modelo.facturas.Factura;
import swing.modelo.facturas.Llamada;
import swing.modelo.facturas.TipoPromocion;
import swing.modelo.gestion.GestionEntreFechas;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

public class VistaPrincipal implements VistaParaControlador {

    private GestionParaVista gestion;
    private Controlador controlador;

    private JFrame ventana;
    private Container panel;
    private JPanel panelSuperior, panelInferior, panelTexto, panelDatos, panelBotones, panelFechas;
    private JLabel principal, NIF, nombre, apellidos, codPostal, poblacion, provincia, correo, tarifa, fechaInicio, fechaFinal, fechaFacturacion, fechaEmision;
    private JLabel numeroDeTelefono, duracionDeLlamada;
    private JLabel codigoFactura;
    private JTextField datoNIF, datoNombre, datoApellidos, datoCodPostal, datoPoblacion, datoProvincia, datoCorreo;
    private JTextField datoNumeroDeTelefono, datoDuracionLLamada;
    private JTextField datoCodigoFactura;
    private JFormattedTextField datoFechaInicio, datoFechaFinal, datoFechaFacturacion, datoFechaEmision;
    private JComboBox<String> datoTarifa;
    private JTextArea areaTexto;
    private JButton botonAceptar, botonReiniciar, botonVolver;
    private JCheckBox botonFechas;
    private JList lista;
    private JScrollPane panelLista;
    private MaskFormatter formatoFecha;

    public void cambiarGestion(GestionParaVista gestion) {
        this.gestion = gestion;
    }

    public void cambiarControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void iniciarPrograma() {
        cargarVista();
    }

    private void cargarVista() {
        ventana = new JFrame("Facturación");

        panel = ventana.getContentPane();
        panelSuperior = new JPanel();
        panelInferior = new JPanel();
        panelBotones = new JPanel();
        panelTexto = new JPanel();
        panelDatos = new JPanel();
        panelFechas = new JPanel();

        principal = new JLabel("PANTALLA PRINCIPAL");
        NIF = new JLabel("NIF: ");
        nombre = new JLabel("Nombre: ");
        apellidos = new JLabel("Apellidos: ");
        codPostal = new JLabel("Código postal: ");
        poblacion = new JLabel("Población");
        provincia = new JLabel("Provincia: ");
        correo = new JLabel("Correo electrónico: ");
        tarifa = new JLabel("Tarifa: ");
        fechaInicio = new JLabel("Fecha inicio: ");
        fechaFinal = new JLabel("Fecha final: ");
        fechaFacturacion = new JLabel("Fecha de facturacion (dd/MM/yyyy)");
        fechaEmision = new JLabel("Fecha de emision (dd/MM/yyyy)");

        numeroDeTelefono = new JLabel("Número de teléfono");
        duracionDeLlamada = new JLabel("Duración de la llamada");

        codigoFactura = new JLabel("Codigo de la factura");

        datoNIF = new JTextField();
        datoNombre = new JTextField();
        datoApellidos = new JTextField();
        datoCorreo = new JTextField();
        datoCodPostal = new JTextField();
        datoPoblacion = new JTextField();
        datoProvincia = new JTextField();
        try {
            formatoFecha = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        datoTarifa = new JComboBox<>();
        datoNumeroDeTelefono = new JTextField();
        datoDuracionLLamada = new JTextField();
        datoCodigoFactura = new JTextField();
        datoFechaFacturacion = new JFormattedTextField(formatoFecha);
        datoFechaEmision = new JFormattedTextField(formatoFecha);
        datoFechaInicio = new JFormattedTextField(formatoFecha);
        datoFechaFinal = new JFormattedTextField(formatoFecha);

        botonAceptar = new JButton("Aceptar");
        botonReiniciar = new JButton("Reiniciar");
        botonReiniciar.addActionListener(new EscuchadoraBotonReiniciar());
        botonVolver = new JButton("Volver");
        botonVolver.addActionListener(new EscuchadoraBotonVolver());

        botonFechas = new JCheckBox("Entre fechas");

        lista = new JList<>();
        panelLista = new JScrollPane();

//        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        ventana.setUndecorated(true);
        Dimension resolucion = Toolkit.getDefaultToolkit().getScreenSize();
        ventana.setSize((int) (resolucion.getWidth() * 0.8), (int) (resolucion.getHeight() * 0.8));
        ventana.setLocation((int) (resolucion.getWidth() * 0.08), (int) (resolucion.getHeight() * 0.08));
        ventana.setResizable(false);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menu = new JMenuBar();
        JMenu menuClientes = new JMenu("Clientes");
        JMenu menuLlamadas = new JMenu("Llamadas");
        JMenu menuFacturas = new JMenu("Facturas");
        JMenu altaCliente = new JMenu("Dar de alta");

        JMenuItem altaEmpresa = new JMenuItem("Empresa");
        altaEmpresa.addActionListener(new EscuchadoraAltaEmpresa());
        altaCliente.add(altaEmpresa);

        JMenuItem altaParticular = new JMenuItem("Particular");
        altaParticular.addActionListener(new EscuchadoraAltaParticular());
        altaCliente.add(altaParticular);

        JMenuItem bajaCliente = new JMenuItem("Dar de baja");
        bajaCliente.addActionListener(new EscuchadoraBajaCliente());

        JMenuItem cambiarTarifa = new JMenuItem("Cambiar tarifa");
        cambiarTarifa.addActionListener(new EscuchadoraCambiarTarifa());

        JMenuItem datosCliente = new JMenuItem("Mostrar datos");
        datosCliente.addActionListener(new EscuchadoraDatosCliente());

        JMenuItem listarClientes = new JMenuItem("Listar clientes");
        listarClientes.addActionListener(new EscuchadoraListarClientes());

        menuClientes.add(altaCliente);
        menuClientes.add(bajaCliente);
        menuClientes.add(cambiarTarifa);
        menuClientes.add(datosCliente);
        menuClientes.add(listarClientes);

        JMenuItem altaLlamada = new JMenuItem("Dar de alta");
        altaLlamada.addActionListener(new EscuchadoraAltaLlamada());

        JMenuItem listarLlamadas = new JMenuItem("Listar llamadas");
        listarLlamadas.addActionListener(new EscuchadoraListarLlamadas());

        menuLlamadas.add(altaLlamada);
        menuLlamadas.add(listarLlamadas);

        JMenuItem emitirFactura = new JMenuItem("Emitir factura");
        emitirFactura.addActionListener(new EscuchadoraEmitirFactura());

        JMenuItem mostrarFactura = new JMenuItem("Mostrar factura por código");
        mostrarFactura.addActionListener(new EscuchadoraMostrarFactura());

        JMenuItem listarFacturas = new JMenuItem("Listar facturas cliente");
        listarFacturas.addActionListener(new EscuchadoraListarFacturas());
        menuFacturas.add(emitirFactura);
        menuFacturas.add(mostrarFactura);
        menuFacturas.add(listarFacturas);

        menu.add(menuClientes);
        menu.add(menuLlamadas);
        menu.add(menuFacturas);

        ventana.setJMenuBar(menu);

        panel = ventana.getContentPane();

        gestion.cargarDatos();

        cargarPanelPrincipal();
    }

    private void guardarDatos() {
        gestion.guardarDatos();
    }

    private void cargarPanelPrincipal() {
        panel.removeAll();

        panel.add(principal);

        ventana.setContentPane(panel);
    }

    @Override
    public String nif() {
        return datoNIF.getText();
    }

    @Override
    public String nombre() {
        return datoNombre.getText();
    }

    @Override
    public String apellidos() {
        return datoApellidos.getText();
    }

    @Override
    public int codigoPostal() {
        return Integer.parseInt(datoCodPostal.getText());
    }

    @Override
    public String poblacion() {
        return datoPoblacion.getText();
    }

    @Override
    public String provincia() {
        return datoProvincia.getText();
    }

    @Override
    public String correoElectronico() {
        return datoCorreo.getText();
    }

    @Override
    public int opcionTarifa() {
        return datoTarifa.getSelectedIndex();
    }

    @Override
    public String numeroTelefono() {
        return datoNumeroDeTelefono.getText();
    }

    @Override
    public String duracionLlamada() {
        return datoDuracionLLamada.getText();
    }

    @Override
    public String fechaEmision() {
        return datoFechaEmision.getText();
    }

    @Override
    public String fechaFacturacion() {
        return datoFechaFacturacion.getText();
    }

    private String mostrarCliente(Cliente cliente) {
        StringBuilder datos = new StringBuilder();
        datos.append("Nif: " + cliente.nif() + "\n");
        datos.append("Nombre: " + cliente.nombre() + "\n");
        if (cliente instanceof Particular)
            datos.append("Apellidos: " + ((Particular) cliente).apellidos() + "\n");
        datos.append("Correo: " + cliente.correo() + "\n");
        datos.append("Direccion: " + cliente.direccion().toString() + "\n");
        datos.append("Tarifa: " + cliente.tarifa().toString() + "\n");

        return datos.toString();
    }

    private class EscuchadoraAltaEmpresa implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            panelSuperior = new JPanel();
            panelSuperior.setLayout(new GridLayout(7, 2));

            panelSuperior.add(NIF, 0);
            datoNIF = new JTextField();
            panelSuperior.add(datoNIF, 1);

            panelSuperior.add(nombre, 2);
            datoNombre = new JTextField();
            panelSuperior.add(datoNombre, 3);

            panelSuperior.add(correo, 4);
            datoCorreo = new JTextField();
            panelSuperior.add(datoCorreo, 5);

            panelSuperior.add(codPostal, 6);
            datoCodPostal = new JTextField();
            panelSuperior.add(datoCodPostal, 7);

            panelSuperior.add(poblacion, 8);
            datoPoblacion = new JTextField();
            panelSuperior.add(datoPoblacion, 9);

            panelSuperior.add(provincia, 10);
            datoProvincia = new JTextField();
            panelSuperior.add(datoProvincia, 11);

            panelInferior = new JPanel();
            panelInferior.setLayout(new BorderLayout());

            areaTexto = new JTextArea((int) (ventana.getSize().getHeight() * 0.02), (int) (ventana.getSize().getWidth() * 0.085));
            areaTexto.setEditable(false);
            panelTexto = new JPanel();
            panelTexto.add(areaTexto);

            panelInferior.add(panelTexto, BorderLayout.CENTER);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro NIF incorrecto\n");
                else if (gestion.existeCliente(datoNIF.getText()))
                    resultado.append("El cliente con NIF " + datoNIF.getText() + " YA EXISTE\n");
                if (datoNombre.getText().equals(""))
                    resultado.append("Parámetro NOMBRE incorrecto\n");
                if (datoCorreo.getText().equals(""))
                    resultado.append("Parámetro CORREO incorrecto\n");
                if (datoCodPostal.getText().equals(""))
                    resultado.append("Parámetro CÓDIGO POSTAL incorrecto\n");
                if (datoPoblacion.getText().equals(""))
                    resultado.append("Parámetro POBLACIÓN incorrecto\n");
                if (datoProvincia.getText().equals(""))
                    resultado.append("Parámetro PROVINCIA incorrecto\n");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else {
                    if (controlador.darAltaEmpresa())
                        areaTexto.setText("AÑADIDO EMPRESA CON NIF " + datoNIF.getText() + "\n");
                    else
                        areaTexto.setText("NO SE HA PODIDO AÑADIR EMPRESA CON NIF " + datoNIF.getText() + "\n");

                    guardarDatos();
                }
            });

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panel.add(panelSuperior, BorderLayout.NORTH);
            panel.add(panelInferior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraAltaParticular implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            panelSuperior = new JPanel();
            panelSuperior.setLayout(new GridLayout(8, 2));

            panelSuperior.add(NIF, 0);
            datoNIF = new JTextField();
            panelSuperior.add(datoNIF, 1);

            panelSuperior.add(nombre, 2);
            datoNombre = new JTextField();
            panelSuperior.add(datoNombre, 3);

            panelSuperior.add(apellidos, 4);
            datoApellidos = new JTextField();
            panelSuperior.add(datoApellidos, 5);

            panelSuperior.add(correo, 6);
            datoCorreo = new JTextField();
            panelSuperior.add(datoCorreo, 7);

            panelSuperior.add(codPostal, 8);
            datoCodPostal = new JTextField();
            panelSuperior.add(datoCodPostal, 9);

            panelSuperior.add(poblacion, 10);
            datoPoblacion = new JTextField();
            panelSuperior.add(datoPoblacion, 11);

            panelSuperior.add(provincia, 12);
            datoProvincia = new JTextField();
            panelSuperior.add(datoProvincia, 13);

            panelInferior = new JPanel();

            areaTexto = new JTextArea((int) (ventana.getSize().getHeight() * 0.02), (int) (ventana.getSize().getWidth() * 0.085));
            areaTexto.setEditable(false);
            panelTexto = new JPanel();
            panelTexto.add(areaTexto);

            panelInferior.add(panelTexto);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro NIF incorrecto\n");
                else if (gestion.existeCliente(datoNIF.getText()))
                    resultado.append("El cliente con NIF " + datoNIF.getText() + " YA EXISTE\n");
                if (datoNombre.getText().equals(""))
                    resultado.append("Parámetro NOMBRE incorrecto\n");
                if (datoApellidos.getText().equals(""))
                    resultado.append("Parámetro APELLIDOS incorrecto\n");
                if (datoCorreo.getText().equals(""))
                    resultado.append("Parámetro CORREO incorrecto\n");
                if (datoCodPostal.getText().equals(""))
                    resultado.append("Parámetro CÓDIGO POSTAL incorrecto\n");
                if (datoPoblacion.getText().equals(""))
                    resultado.append("Parámetro POBLACIÓN incorrecto\n");
                if (datoProvincia.getText().equals(""))
                    resultado.append("Parámetro PROVINCIA incorrecto\n");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else {
                    if (controlador.darAltaParticular())
                        areaTexto.append("AÑADIDO PARTICULAR CON NIF " + datoNIF.getText());
                    else
                        areaTexto.append("NO SE HA PODIDO AÑADIR PARTICULAR CON NIF " + datoNIF.getText());

                    guardarDatos();
                }
            });

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panel.add(panelSuperior, BorderLayout.NORTH);
            panel.add(panelInferior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraBajaCliente implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            panelSuperior = new JPanel();
            panelSuperior.setLayout(new GridLayout(1, 2));

            panelSuperior.add(NIF, 0);
            datoNIF = new JTextField();
            panelSuperior.add(datoNIF, 1);

            panelInferior = new JPanel();

            areaTexto = new JTextArea((int) (ventana.getSize().getHeight() * 0.04), (int) (ventana.getSize().getWidth() * 0.085));
            areaTexto.setEditable(false);
            panelTexto = new JPanel();
            panelTexto.add(areaTexto);

            panelInferior.add(panelTexto);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro NIF incorrecto\n");
                else if (!gestion.existeCliente(datoNIF.getText()))
                    resultado.append("El cliente con NIF " + datoNIF.getText() + " NO EXISTE");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else {
                    if (controlador.darBajaCliente())
                        areaTexto.setText("BORRADO CLIENTE CON NIF " + datoNIF.getText());
                    else
                        areaTexto.setText("NO SE HA PODIDO BORRAR CLIENTE CON NIF " + datoNIF.getText());

                    guardarDatos();
                }
            });

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panel.add(panelSuperior, BorderLayout.NORTH);
            panel.add(panelInferior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraCambiarTarifa implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            panelSuperior = new JPanel();
            panelSuperior.setLayout(new GridLayout(2, 2));

            panelSuperior.add(NIF, 0);
            datoNIF = new JTextField();
            panelSuperior.add(datoNIF, 1);

            panelSuperior.add(tarifa, 2);
            String[] tarifas = {
                    TipoPromocion.MADRUGADA.descripcion(),
                    TipoPromocion.TARDE.descripcion(),
                    TipoPromocion.DOMINGO.descripcion(),
                    TipoPromocion.FESTIVO.descripcion()
            };
            datoTarifa = new JComboBox<>(tarifas);
            datoTarifa.setSelectedItem(new StringBuilder("Elige una tarifa"));
            panelSuperior.add(datoTarifa, 3);

            panelInferior = new JPanel();

            areaTexto = new JTextArea((int) (ventana.getSize().getHeight() * 0.02), (int) (ventana.getSize().getWidth() * 0.085));
            areaTexto.setEditable(false);
            panelTexto = new JPanel();
            panelTexto.add(areaTexto);

            panelInferior.add(panelTexto);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro NIF incorrecto\n");
                else if (!gestion.existeCliente(datoNIF.getText()))
                    resultado.append("El cliente con NIF " + datoNIF.getText() + " NO EXISTE\n");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else {
                    if (gestion.existeCliente(datoNIF.getText())) {
                        controlador.cambiarTarifa();
                        areaTexto.setText("CAMBIADA TARIFA DE CLIENTE CON NIF " + datoNIF.getText());
                    } else
                        areaTexto.setText("NO SE HA PODIDO CAMBIAR LA TARIFA");

                    guardarDatos();
                }
            });

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panel.add(panelSuperior, BorderLayout.NORTH);
            panel.add(panelInferior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraDatosCliente implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            panelSuperior = new JPanel();
            panelSuperior.setLayout(new GridLayout(1, 2));

            panelSuperior.add(NIF, 0);
            datoNIF = new JTextField();
            panelSuperior.add(datoNIF, 1);

            panelInferior = new JPanel();

            areaTexto = new JTextArea((int) (ventana.getSize().getHeight() * 0.02), (int) (ventana.getSize().getWidth() * 0.085));
            areaTexto.setEditable(false);
            panelTexto = new JPanel();
            panelTexto.add(areaTexto);

            panelInferior.add(panelTexto);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro NIF incorrecto\n");
                else if (!gestion.existeCliente(datoNIF.getText()))
                    resultado.append("El cliente con NIF " + datoNIF.getText() + " NO EXISTE\n");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else {
                    Cliente cliente = null;
                    try {
                        cliente = gestion.mostrarCliente(datoNIF.getText());
                    } catch (ClienteNoEncontrado clienteNoEncontrado) {
                        clienteNoEncontrado.printStackTrace();
                    }
                    areaTexto.setText("MOSTRANDO CLIENTE CON NIF " + cliente.nif());
                    areaTexto.append("\n" + cliente);
                }
            });

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panel.add(panelSuperior, BorderLayout.NORTH);
            panel.add(panelInferior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraListarClientes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            panelSuperior = new JPanel();
            panelSuperior.setLayout(new BorderLayout());

            botonFechas.addActionListener(new EscuchadoraBotonFechas());
            panelSuperior.add(botonFechas, BorderLayout.NORTH);

            panelFechas = new JPanel();
            panelFechas.setLayout(new GridLayout(2, 2));

            panelFechas.add(fechaInicio, 0);
            fechaInicio.setEnabled(false);
            datoFechaInicio = new JFormattedTextField(formatoFecha);
            datoFechaInicio.setEnabled(false);
            panelFechas.add(datoFechaInicio, 1);

            panelFechas.add(fechaFinal, 2);
            fechaFinal.setEnabled(false);
            datoFechaFinal = new JFormattedTextField(formatoFecha);
            datoFechaFinal.setEnabled(false);
            panelFechas.add(datoFechaFinal, 3);

            panelSuperior.add(panelFechas, BorderLayout.SOUTH);

            panelInferior = new JPanel();
            panelInferior.setLayout(new BorderLayout());

            panelTexto = new JPanel();
            panelDatos = new JPanel();

            panelInferior.add(panelTexto, BorderLayout.WEST);
            panelInferior.add(panelDatos, BorderLayout.EAST);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());
            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                if (!botonFechas.isSelected()) {
                    try {
                        String[] listaClientes = new String[gestion.listarClientes().size()];
                        int i = 0;
                        for (Cliente cliente : gestion.listarClientes())
                            listaClientes[i++] = cliente.nif();

                        lista = new JList<>(listaClientes);
                        lista.addMouseListener(new EscuchadoraDobleClick());
                        panelLista = new JScrollPane(lista);
                        panelLista.setSize((int) (ventana.getSize().getHeight() * 0.02), (int) (ventana.getSize().getWidth() * 0.025));
                        panelTexto.add(panelLista);
                        panelDatos = new JPanel();
                        panelInferior.updateUI();
                    } catch (ListaClientesVacio listaClientesVacio) {
                        listaClientesVacio.printStackTrace();
                    }
                } else {
                    StringBuilder resultado = new StringBuilder();
                    if (datoFechaInicio.getText().equals(""))
                        resultado.append("Parámetro FECHA INICIO incorrecto");
                    if (datoFechaFinal.getText().equals(""))
                        resultado.append("Parámetro FECHA FINAL incorrecto");

                    if (!resultado.toString().equals(""))
                        areaTexto.setText(resultado.toString());
                    else {
                        Collection<Cliente> listaClientes = null;
                        try {
                            listaClientes = gestion.listarClientes();
                            if (botonFechas.isSelected()) {
                                Calendar fechaInicio = GregorianCalendar.getInstance();
                                Calendar fechaFinal = GregorianCalendar.getInstance();

                                String[] vectorFechaInicio = datoFechaInicio.getText().split("/");
                                String[] vectorFechaFinal = datoFechaFinal.getText().split("/");

                                fechaInicio.set(Integer.parseInt(vectorFechaInicio[2]), Integer.parseInt(vectorFechaInicio[1]), Integer.parseInt(vectorFechaInicio[0]));
                                fechaFinal.set(Integer.parseInt(vectorFechaFinal[2]), Integer.parseInt(vectorFechaFinal[1]), Integer.parseInt(vectorFechaFinal[0]));

                                listaClientes = new GestionEntreFechas<Cliente>().entreFechas(listaClientes, fechaInicio, fechaFinal);
                            }
                        } catch (ListaClientesVacio listaClientesVacio) {
                            listaClientesVacio.printStackTrace();
                        }

                        if (listaClientes != null)
                            for (Cliente cliente : listaClientes)
                                areaTexto.append(cliente.toString());
                        else
                            areaTexto.setText("EL CLIENTE CON NIF " + datoNIF.getText() + " NO HA REALIZADO NINGUNA LLAMADA");
                    }
                }
            });

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panel.add(panelSuperior, BorderLayout.NORTH);
            panel.add(panelInferior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraAltaLlamada implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();

            panelSuperior = new JPanel();
            panelSuperior.setLayout(new GridLayout(4, 2));

            panelSuperior.add(NIF, 0);
            datoNIF = new JTextField();
            panelSuperior.add(datoNIF, 1);

            panelSuperior.add(numeroDeTelefono, 2);
            datoNumeroDeTelefono = new JTextField();
            panelSuperior.add(datoNumeroDeTelefono, 3);


            panelSuperior.add(duracionDeLlamada, 4);
            datoDuracionLLamada = new JTextField();
            panelSuperior.add(datoDuracionLLamada, 5);

            JLabel texto = new JLabel("Texto inútil en ALTA LLAMADA");
            panel.add(texto);

            panelInferior = new JPanel();

            areaTexto = new JTextArea((int) (ventana.getSize().getHeight() * 0.02), (int) (ventana.getSize().getWidth() * 0.085));
            areaTexto.setEditable(false);
            panelTexto = new JPanel();
            panelTexto.add(areaTexto);

            panelInferior.add(panelTexto);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro NIF incorrecto\n");
                if (datoNumeroDeTelefono.getText().equals(""))
                    resultado.append("Parámetro NumeroTelefono incorrecto\n");
                if (datoDuracionLLamada.getText().equals(""))
                    resultado.append("Parámetro DuracionDeLlamada incorrecto\n");
                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else {
                    if (controlador.darAltaLlamada())
                        areaTexto.setText("AÑADIDA LLAMADA CON NIF " + datoNIF.getText());
                    else
                        areaTexto.setText("NO SE HA PODIDO AÑADIR LA LLAMADA CON NIF " + datoNIF.getText());

                    guardarDatos();
                }
            });

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panel.add(panelSuperior, BorderLayout.NORTH);
            panel.add(panelInferior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraListarLlamadas implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            panelSuperior = new JPanel();
            panelSuperior.setLayout(new BorderLayout());

            botonFechas.addActionListener(new EscuchadoraBotonFechas());
            panelSuperior.add(botonFechas, BorderLayout.NORTH);
            panelFechas = new JPanel();
            panelFechas.setLayout(new GridLayout(3, 2));

            panelFechas.add(NIF, 0);
            datoNIF = new JTextField();
            panelFechas.add(datoNIF, 1);

            panelFechas.add(fechaInicio, 2);
            fechaInicio.setEnabled(false);
            datoFechaInicio = new JFormattedTextField(formatoFecha);
            datoFechaInicio.setEnabled(false);
            panelFechas.add(datoFechaInicio, 3);

            panelFechas.add(fechaFinal, 4);
            fechaFinal.setEnabled(false);
            datoFechaFinal = new JFormattedTextField(formatoFecha);
            datoFechaFinal.setEnabled(false);
            panelFechas.add(datoFechaFinal, 5);

            panelSuperior.add(panelFechas, BorderLayout.SOUTH);

            panelInferior = new JPanel();

            areaTexto = new JTextArea((int) (ventana.getSize().getHeight() * 0.035), (int) (ventana.getSize().getWidth() * 0.085));
            areaTexto.setEditable(false);
            panelTexto = new JPanel();

            panelInferior.add(panelTexto);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());
            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro NIF incorrecto");
                if (botonFechas.isSelected()) {
                    if (datoFechaInicio.getText().equals(""))
                        resultado.append("Parámetro FECHA INICIO incorrecto");
                    if (datoFechaFinal.getText().equals(""))
                        resultado.append("Parámetro FECHA FINAL incorrecto");
                }

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else {
                    try {
                        Collection<Llamada> listaLlamadas = gestion.listarLlamadas(datoNIF.getText());
                        if (botonFechas.isSelected()) {
                            Calendar fechaInicio = GregorianCalendar.getInstance();
                            Calendar fechaFinal = GregorianCalendar.getInstance();

                            String[] vectorFechaInicio = datoFechaInicio.getText().split("/");
                            String[] vectorFechaFinal = datoFechaFinal.getText().split("/");

                            fechaInicio.set(Integer.parseInt(vectorFechaInicio[2]), (Integer.parseInt(vectorFechaInicio[1]) - 1), Integer.parseInt(vectorFechaInicio[0]));
                            fechaFinal.set(Integer.parseInt(vectorFechaFinal[2]), (Integer.parseInt(vectorFechaFinal[1]) - 1), Integer.parseInt(vectorFechaFinal[0]));

                            listaLlamadas = new GestionEntreFechas<Llamada>().entreFechas(listaLlamadas, fechaInicio, fechaFinal);

                            if (listaLlamadas != null)
                                for (Llamada llamada : listaLlamadas)
                                    areaTexto.append(llamada.toString());
                            else
                                areaTexto.setText("EL CLIENTE CON NIF " + datoNIF.getText() + " NO HA REALIZADO NINGUNA LLAMADA");

                        }
                    } catch (ClienteNoEncontrado | ClienteNoLlamadas ex) {
                        ex.printStackTrace();
                    }
                }
            });

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panel.add(panelSuperior, BorderLayout.NORTH);
            panel.add(panelInferior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraEmitirFactura implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());
            panelTexto = new JPanel();

            panelSuperior = new JPanel();
            panelSuperior.setLayout(new BorderLayout());

            panelFechas = new JPanel();
            panelFechas.setLayout(new GridLayout(3, 2));

            panelFechas.add(NIF, 0);
            datoNIF = new JTextField();
            panelFechas.add(datoNIF, 1);

            panelFechas.add(fechaFacturacion, 2);
            datoFechaFacturacion = new JFormattedTextField(formatoFecha);
            panelFechas.add(datoFechaFacturacion, 3);

            panelFechas.add(fechaEmision, 4);
            datoFechaEmision = new JFormattedTextField(formatoFecha);
            panelFechas.add(datoFechaEmision, 5);

            panelSuperior.add(panelFechas, BorderLayout.SOUTH);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> controlador.emitirFactura());

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panel.add(panelSuperior, BorderLayout.NORTH);
            panel.add(panelInferior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraMostrarFactura implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            panelSuperior = new JPanel();
            panelSuperior.setLayout(new GridLayout(1, 2));

            panelSuperior.add(codigoFactura, 0);
            datoCodigoFactura = new JTextField();
            panelSuperior.add(datoCodigoFactura, 1);

            panelInferior = new JPanel();

            areaTexto = new JTextArea((int) (ventana.getSize().getHeight() * 0.02), (int) (ventana.getSize().getWidth() * 0.085));
            areaTexto.setEditable(false);
            panelTexto = new JPanel();
            panelTexto.add(areaTexto);

            panelInferior.add(panelTexto);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptarMostrarFactura -> {
                StringBuilder resultado = new StringBuilder();

                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro NIF incorrecto\n");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else {
                    int codigo = Integer.parseInt(codigoFactura.getText());
                    try {
                        areaTexto.setText("MOSTRANDO FACTURA CON CODIGO " + datoCodigoFactura.getText() + "/n" + gestion.mostrarFactura(codigo));
                    } catch (ListaFacturasVacia | FacturaNoEncontrada listaFacturasVacia) {
                        listaFacturasVacia.printStackTrace();
                    }
                }
            });

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panel.add(panelSuperior, BorderLayout.NORTH);
            panel.add(panelInferior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraListarFacturas implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            panel.removeAll();
            panel.setLayout(new BorderLayout());

            panelSuperior = new JPanel();
            panelSuperior.setLayout(new BorderLayout());

            botonFechas.addActionListener(new EscuchadoraBotonFechas());
            panelSuperior.add(botonFechas, BorderLayout.NORTH);

            panelFechas = new JPanel();
            panelFechas.setLayout(new GridLayout(3, 2));

            panelFechas.add(NIF, 0);
            datoNIF = new JTextField();
            panelFechas.add(datoNIF, 1);

            panelFechas.add(fechaInicio, 2);
            fechaInicio.setEnabled(false);
            datoFechaInicio = new JFormattedTextField(formatoFecha);
            datoFechaInicio.setEnabled(false);
            panelFechas.add(datoFechaInicio, 3);

            panelFechas.add(fechaFinal, 4);
            fechaFinal.setEnabled(false);
            datoFechaFinal = new JFormattedTextField(formatoFecha);
            datoFechaFinal.setEnabled(false);
            panelFechas.add(datoFechaFinal, 5);

            panelSuperior.add(panelFechas, BorderLayout.SOUTH);

            panelInferior = new JPanel();

            areaTexto = new JTextArea((int) (ventana.getSize().getHeight() * 0.035), (int) (ventana.getSize().getWidth() * 0.085));
            areaTexto.setEditable(false);
            panelTexto = new JPanel();

            panelInferior.add(panelTexto);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());
            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro NIF incorrecto");
                if (botonFechas.isSelected()) {
                    if (datoFechaInicio.getText().equals(""))
                        resultado.append("Parámetro FECHA INICIO incorrecto");
                    if (datoFechaFinal.getText().equals(""))
                        resultado.append("Parámetro FECHA FINAL incorrecto");
                }

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else {
                    Collection<Factura> listaFacturas = null;
                    try {
                        listaFacturas = gestion.listarFacturas(datoNIF.getText());
                        if (botonFechas.isSelected()) {
                            Calendar fechaInicio = GregorianCalendar.getInstance();
                            Calendar fechaFinal = GregorianCalendar.getInstance();

                            String[] vectorFechaInicio = datoFechaInicio.getText().split("/");
                            String[] vectorFechaFinal = datoFechaFinal.getText().split("/");

                            fechaInicio.set(Integer.parseInt(vectorFechaInicio[2]), Integer.parseInt(vectorFechaInicio[1]), Integer.parseInt(vectorFechaInicio[0]));
                            fechaFinal.set(Integer.parseInt(vectorFechaFinal[2]), Integer.parseInt(vectorFechaFinal[1]), Integer.parseInt(vectorFechaFinal[0]));

                            listaFacturas = new GestionEntreFechas<Factura>().entreFechas(listaFacturas, fechaInicio, fechaFinal);
                        }
                    } catch (ClienteNoEncontrado | ClienteNoFacturas ex) {
                        ex.printStackTrace();
                    }

                    if (listaFacturas != null)
                        for (Factura factura : listaFacturas)
                            areaTexto.append(factura.toString());
                    else
                        areaTexto.setText("EL CLIENTE CON NIF " + datoNIF.getText() + " NO TIENE NINGUNA FACTURA");
                }
            });

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panel.add(panelSuperior, BorderLayout.NORTH);
            panel.add(panelInferior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraBotonVolver implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cargarPanelPrincipal();
        }
    }

    private class EscuchadoraBotonFechas implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (botonFechas.isSelected()) {
                fechaInicio.setEnabled(true);
                datoFechaInicio.setEnabled(true);
                fechaFinal.setEnabled(true);
                datoFechaFinal.setEnabled(true);
            } else {
                fechaInicio.setEnabled(false);
                datoFechaInicio.setEnabled(false);
                fechaFinal.setEnabled(false);
                datoFechaFinal.setEnabled(false);
            }
        }
    }

    private class EscuchadoraBotonReiniciar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            datoNIF.setText("");
            datoNombre.setText("");
            datoApellidos.setText("");
            datoCorreo.setText("");
            datoCodPostal.setText("");
            datoPoblacion.setText("");
            datoProvincia.setText("");
            datoNumeroDeTelefono.setText("");
            datoDuracionLLamada.setText("");
            datoCodigoFactura.setText("");
            datoFechaInicio.setText("");
            datoFechaFinal.setText("");
            datoFechaFacturacion.setText("");
            datoFechaEmision.setText("");
            areaTexto.setText("");

            fechaInicio.setEnabled(false);
            datoFechaInicio.setEnabled(false);
            fechaFinal.setEnabled(false);
            datoFechaFinal.setEnabled(false);
        }
    }

    private class EscuchadoraDobleClick implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            lista = (JList) e.getSource();
            if (e.getClickCount() == 2) {
                int index = lista.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Object o = lista.getModel().getElementAt(index);
                    System.out.println("Double-clicked on: " + o.toString());

                    areaTexto = new JTextArea((int) (ventana.getSize().getHeight() * 0.02), (int) (ventana.getSize().getWidth() * 0.05));
                    areaTexto.setEditable(false);
                    panelTexto = new JPanel();
                    panelTexto.add(areaTexto);

                    panelInferior.add(panelTexto, BorderLayout.EAST);

                    try {
                        Cliente cliente = gestion.mostrarCliente(o.toString());

                        areaTexto.setText(mostrarCliente(cliente));

                    } catch (ClienteNoEncontrado clienteNoEncontrado) {
                        clienteNoEncontrado.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
