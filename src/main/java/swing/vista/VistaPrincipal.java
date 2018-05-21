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
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

public class VistaPrincipal implements VistaParaControlador {

    private GestionParaVista gestion;
    private Controlador controlador;

    private JFrame ventana;
    private Container panel;
    private JPanel panelSuperior, panelBotones, panelFechas;
    private JScrollPane panelTexto;
    private JPanel panelNIF, panelNombre, panelApellidos, panelCorreo, panelCodPostal, panelPoblacion, panelProvincia, panelTarifa;
    private JPanel panelNumeroDeTelefono, panelDuracionDeLlamada;
    private JPanel panelCodigoFactura;
    private JLabel principal, NIF, nombre, apellidos, codPostal, poblacion, provincia, correo, tarifa;
    private JLabel numeroDeTelefono, duracionDeLlamada;
    private JLabel codigoFactura;
    private JLabel fechaInicio, fechaFinal, fechaFacturacion, fechaEmision;
    private JTextField datoNIF, datoNombre, datoApellidos, datoCodPostal, datoPoblacion, datoProvincia, datoCorreo;
    private JComboBox<String> datoTarifa;
    private JTextField datoNumeroDeTelefono, datoDuracionLLamada;
    private JTextField datoCodigoFactura;
    private JFormattedTextField datoFechaInicio, datoFechaFinal;
    private JFormattedTextField datoFechaFacturacion, datoFechaEmision;
    private JTextArea areaTexto;
    private JButton botonAceptar, botonReiniciar, botonVolver;
    private JCheckBox botonFechas;
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
        panelBotones = new JPanel();
        panelTexto = new JScrollPane();
        panelFechas = new JPanel();

        panelNIF = new JPanel();
        panelNombre = new JPanel();
        panelApellidos = new JPanel();
        panelCorreo = new JPanel();
        panelCodPostal = new JPanel();
        panelPoblacion = new JPanel();
        panelProvincia = new JPanel();
        panelTarifa = new JPanel();

        principal = new JLabel("PANTALLA PRINCIPAL");
        NIF = new JLabel("NIF: ");
        nombre = new JLabel("Nombre: ");
        apellidos = new JLabel("Apellidos: ");
        codPostal = new JLabel("Código postal: ");
        poblacion = new JLabel("Población");
        provincia = new JLabel("Provincia: ");
        correo = new JLabel("Correo electrónico: ");
        tarifa = new JLabel("Tarifa: ");

        numeroDeTelefono = new JLabel("Número de teléfono: ");
        duracionDeLlamada = new JLabel("Duración de la llamada: ");

        codigoFactura = new JLabel("Codigo de la factura: ");

        fechaInicio = new JLabel("Fecha inicio (dd/MM/yyyy): ");
        fechaFinal = new JLabel("Fecha final (dd/MM/yyyy): ");
        fechaFacturacion = new JLabel("Fecha de facturación (dd/MM/yyyy): ");
        fechaEmision = new JLabel("Fecha de emisión (dd/MM/yyyy): ");

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
        datoFechaInicio = new JFormattedTextField(formatoFecha);
        datoFechaFinal = new JFormattedTextField(formatoFecha);
        datoFechaFacturacion = new JFormattedTextField(formatoFecha);
        datoFechaEmision = new JFormattedTextField(formatoFecha);

        botonAceptar = new JButton("Aceptar");
        botonReiniciar = new JButton("Reiniciar");
        botonReiniciar.addActionListener(new EscuchadoraBotonReiniciar());
        botonVolver = new JButton("Volver");
        botonVolver.addActionListener(new EscuchadoraBotonVolver());

        botonFechas = new JCheckBox("Entre fechas");

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

        cargarDatos();

        guardarDatos();

        cargarPanelPrincipal();
    }

    private void cargarDatos() {
        gestion.cargarDatos();
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
    public int numeroTelefono() {
        return Integer.parseInt(datoNumeroDeTelefono.getText());
    }

    @Override
    public int duracionLlamada() {
        return Integer.parseInt(datoDuracionLLamada.getText());
    }

    @Override
    public int codigoFactura() {
        return Integer.parseInt(datoCodigoFactura.getText());
    }

    @Override
    public String fechaInicio() {
        return datoFechaInicio.getText();
    }

    @Override
    public String fechaFinal() {
        return datoFechaFinal.getText();
    }

    private String mostrarCliente(String nif) {
        try {
            Cliente cliente = gestion.mostrarCliente(nif);
            StringBuilder datos = new StringBuilder();
            datos.append("Nif: " + cliente.nif() + "\n");
            datos.append("Nombre: " + cliente.nombre() + "\n");
            if (cliente instanceof Particular)
                datos.append("Apellidos: " + ((Particular) cliente).apellidos() + "\n");
            datos.append("Correo: " + cliente.correo() + "\n");
            datos.append("Direccion: " + cliente.direccion().toString() + "\n");
            datos.append("Tarifa: " + cliente.tarifa().toString() + "\n");

            return datos.toString();
        } catch (ClienteNoEncontrado clienteNoEncontrado) {
            clienteNoEncontrado.printStackTrace();
        }

        return "El cliente no existe en la base de datos";
    }

    private String mostrarFactura(Factura factura) {
        StringBuilder datos = new StringBuilder();
        datos.append("Código: " + factura.codigoFactura() + "\n");
        datos.append("Tarifa: " + factura.tarifaAplicada().toString() + "\n");
        datos.append("Importe: " + factura.importeTotal() + "\n");
        datos.append("Fecha de emisión: " + factura.fecha().toString() + "\n");

        return datos.toString();
    }

    private class EscuchadoraAltaEmpresa implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            SpringLayout layout = new SpringLayout();
            panelSuperior = new JPanel();
            panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
            panelSuperior.setLayout(layout);

            panelNIF = new JPanel();
            panelNIF.add(NIF);
            datoNIF = new JTextField(10);
            panelNIF.add(datoNIF);

            panelNombre = new JPanel();
            panelNombre.add(nombre);
            datoNombre = new JTextField(30);
            panelNombre.add(datoNombre);

            panelCorreo = new JPanel();
            panelCorreo.add(correo);
            datoCorreo = new JTextField(30);
            panelCorreo.add(datoCorreo);

            panelCodPostal = new JPanel();
            panelCodPostal.add(codPostal);
            datoCodPostal = new JTextField(30);
            panelCodPostal.add(datoCodPostal);

            panelPoblacion = new JPanel();
            panelPoblacion.add(poblacion);
            datoPoblacion = new JTextField(30);
            panelPoblacion.add(datoPoblacion);

            panelProvincia = new JPanel();
            panelProvincia.add(provincia);
            datoProvincia = new JTextField(30);
            panelProvincia.add(datoProvincia);

            areaTexto = new JTextArea(15, 95);
            areaTexto.setEditable(false);
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro NIF incorrecto\n");
                else if (gestion.existeCliente(datoNIF.getText()))
                    resultado.append("El cliente con NIF " + datoNIF.getText() + " ya existe\n");
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
                    if (controlador.darAltaEmpresa()) {
                        areaTexto.setText("Añadida la empresa con NIF " + datoNIF.getText() + "\n");
                        areaTexto.append(mostrarCliente(datoNIF.getText()));
                    } else
                        areaTexto.setText("No se ha podido añadir la empresa con NIF " + datoNIF.getText() + "\n");

                    guardarDatos();
                    new EscuchadoraBotonReiniciar();
                }
            });

            layout.putConstraint(SpringLayout.NORTH, panelNombre, 25, SpringLayout.NORTH, panelNIF);
            layout.putConstraint(SpringLayout.NORTH, panelCorreo, 25, SpringLayout.NORTH, panelNombre);
            layout.putConstraint(SpringLayout.NORTH, panelCodPostal, 25, SpringLayout.NORTH, panelCorreo);
            layout.putConstraint(SpringLayout.NORTH, panelPoblacion, 25, SpringLayout.NORTH, panelCodPostal);
            layout.putConstraint(SpringLayout.NORTH, panelProvincia, 25, SpringLayout.NORTH, panelPoblacion);
            layout.putConstraint(SpringLayout.NORTH, panelTexto, 75, SpringLayout.NORTH, panelProvincia);

            panelSuperior.add(panelNIF);
            panelSuperior.add(panelNombre);
            panelSuperior.add(panelCorreo);
            panelSuperior.add(panelCodPostal);
            panelSuperior.add(panelPoblacion);
            panelSuperior.add(panelProvincia);
            panelSuperior.add(panelTexto);

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panelSuperior.updateUI();
            panelBotones.updateUI();

            panel.add(panelSuperior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraAltaParticular implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            SpringLayout layout = new SpringLayout();
            panelSuperior = new JPanel();
            panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
            panelSuperior.setLayout(layout);

            panelNIF = new JPanel();
            panelNIF.add(NIF);
            datoNIF = new JTextField(10);
            panelNIF.add(datoNIF);

            panelNombre = new JPanel();
            panelNombre.add(nombre);
            datoNombre = new JTextField(30);
            panelNombre.add(datoNombre);

            panelApellidos = new JPanel();
            panelApellidos.add(apellidos);
            datoApellidos = new JTextField(30);
            panelApellidos.add(datoApellidos);

            panelCorreo = new JPanel();
            panelCorreo.add(correo);
            datoCorreo = new JTextField(30);
            panelCorreo.add(datoCorreo);

            panelCodPostal = new JPanel();
            panelCodPostal.add(codPostal);
            datoCodPostal = new JTextField(30);
            panelCodPostal.add(datoCodPostal);

            panelPoblacion = new JPanel();
            panelPoblacion.add(poblacion);
            datoPoblacion = new JTextField(30);
            panelPoblacion.add(datoPoblacion);

            panelProvincia = new JPanel();
            panelProvincia.add(provincia);
            datoProvincia = new JTextField(30);
            panelProvincia.add(datoProvincia);

            areaTexto = new JTextArea(15, 95);
            areaTexto.setEditable(false);
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro NIF incorrecto\n");
                else if (gestion.existeCliente(datoNIF.getText()))
                    resultado.append("El cliente con NIF " + datoNIF.getText() + " ya existe\n");
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
                    if (controlador.darAltaParticular()) {
                        areaTexto.setText("Añadido el particular con NIF " + datoNIF.getText() + "\n");
                        areaTexto.append(mostrarCliente(datoNIF.getText()));
                    } else
                        areaTexto.setText("No se ha podido añadir el particular con NIF " + datoNIF.getText() + "\n");

                    guardarDatos();
                }
            });

            layout.putConstraint(SpringLayout.NORTH, panelNombre, 25, SpringLayout.NORTH, panelNIF);
            layout.putConstraint(SpringLayout.NORTH, panelApellidos, 25, SpringLayout.NORTH, panelNombre);
            layout.putConstraint(SpringLayout.NORTH, panelCorreo, 25, SpringLayout.NORTH, panelApellidos);
            layout.putConstraint(SpringLayout.NORTH, panelCodPostal, 25, SpringLayout.NORTH, panelCorreo);
            layout.putConstraint(SpringLayout.NORTH, panelPoblacion, 25, SpringLayout.NORTH, panelCodPostal);
            layout.putConstraint(SpringLayout.NORTH, panelProvincia, 25, SpringLayout.NORTH, panelPoblacion);
            layout.putConstraint(SpringLayout.NORTH, panelTexto, 50, SpringLayout.NORTH, panelProvincia);

            panelSuperior.add(panelNIF);
            panelSuperior.add(panelNombre);
            panelSuperior.add(panelApellidos);
            panelSuperior.add(panelCorreo);
            panelSuperior.add(panelCodPostal);
            panelSuperior.add(panelPoblacion);
            panelSuperior.add(panelProvincia);
            panelSuperior.add(panelTexto);

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panelSuperior.updateUI();
            panelBotones.updateUI();

            panel.add(panelSuperior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraBajaCliente implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            SpringLayout layout = new SpringLayout();
            panelSuperior = new JPanel();
            panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
            panelSuperior.setLayout(layout);

            panelNIF = new JPanel();
            panelNIF.add(NIF);
            datoNIF = new JTextField(10);
            panelNIF.add(datoNIF);

            areaTexto = new JTextArea(15, 95);
            areaTexto.setEditable(false);
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro NIF incorrecto\n");
                else if (!gestion.existeCliente(datoNIF.getText()))
                    resultado.append("El cliente con NIF " + datoNIF.getText() + " no existe\n");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else {
                    if (controlador.darBajaCliente())
                        areaTexto.setText("Borrado el cliente con NIF " + datoNIF.getText() + "\n");
                    else
                        areaTexto.setText("No se ha podido borrar el cliente con NIF " + datoNIF.getText() + "\n");

                    guardarDatos();
                }
            });

            layout.putConstraint(SpringLayout.NORTH, panelTexto, 200, SpringLayout.NORTH, panelNIF);

            panelSuperior.add(panelNIF);
            panelSuperior.add(panelTexto);

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panelSuperior.updateUI();
            panelBotones.updateUI();

            panel.add(panelSuperior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraCambiarTarifa implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            SpringLayout layout = new SpringLayout();
            panelSuperior = new JPanel();
            panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
            panelSuperior.setLayout(layout);

            panelNIF = new JPanel();
            panelNIF.add(NIF);
            datoNIF = new JTextField(10);
            panelNIF.add(datoNIF);

            panelTarifa = new JPanel();
            panelTarifa.add(tarifa);
            String[] tarifas = {
                    TipoPromocion.BASICA.descripcion(),
                    TipoPromocion.MADRUGADA.descripcion(),
                    TipoPromocion.TARDE.descripcion(),
                    TipoPromocion.DOMINGO.descripcion(),
                    TipoPromocion.FESTIVO.descripcion()
            };
            datoTarifa = new JComboBox<>(tarifas);
            datoTarifa.setSelectedItem("Elige una tarifa");
            panelTarifa.add(datoTarifa);

            areaTexto = new JTextArea(15, 95);
            areaTexto.setEditable(false);
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro NIF incorrecto\n");
                else if (!gestion.existeCliente(datoNIF.getText()))
                    resultado.append("El cliente con NIF " + datoNIF.getText() + " no existe\n");
                if (datoTarifa.getSelectedItem() == "Elige una tarifa")
                    resultado.append("Elige una tarifa");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else {
                    if (controlador.cambiarTarifa())
                        areaTexto.setText("Cambiada tarifa del cliente con NIF " + datoNIF.getText() + "\n");
                    else
                        areaTexto.setText("No se ha podido cambiar la tarifa del cliente con NIF " + datoNIF.getText() + "\n");

                    guardarDatos();
                }
            });

            layout.putConstraint(SpringLayout.NORTH, panelTarifa, 25, SpringLayout.NORTH, panelNIF);
            layout.putConstraint(SpringLayout.NORTH, panelTexto, 175, SpringLayout.NORTH, panelTarifa);

            panelSuperior.add(panelNIF);
            panelSuperior.add(panelTarifa);
            panelSuperior.add(panelTexto);

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panelSuperior.updateUI();
            panelBotones.updateUI();

            panel.add(panelSuperior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraDatosCliente implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            SpringLayout layout = new SpringLayout();
            panelSuperior = new JPanel();
            panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
            panelSuperior.setLayout(layout);

            panelNIF = new JPanel();
            panelNIF.add(NIF);
            datoNIF = new JTextField(10);
            panelNIF.add(datoNIF);

            areaTexto = new JTextArea(15, 95);
            areaTexto.setEditable(false);
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro NIF incorrecto\n");
                else if (!gestion.existeCliente(datoNIF.getText()))
                    resultado.append("El cliente con NIF " + datoNIF.getText() + " no existe\n");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else {
                    try {
                        Cliente cliente = gestion.mostrarCliente(datoNIF.getText());
                        areaTexto.setText("MOSTRANDO CLIENTE CON NIF " + cliente.nif());
                        areaTexto.append("\n" + cliente);
                    } catch (ClienteNoEncontrado ex) {
                        ex.printStackTrace();
                    }

                    guardarDatos();
                }
            });

            layout.putConstraint(SpringLayout.NORTH, panelTexto, 200, SpringLayout.NORTH, panelTarifa);

            panelSuperior.add(panelNIF);
            panelSuperior.add(panelTexto);

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panelSuperior.updateUI();
            panelBotones.updateUI();

            panel.add(panelSuperior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraListarClientes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            SpringLayout layout = new SpringLayout();
            panelSuperior = new JPanel();
            panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
            panelSuperior.setLayout(layout);

            panelFechas = new JPanel();
            panelFechas.setLayout(new GridLayout(3, 2));

            botonFechas = new JCheckBox("Entre fechas");
            botonFechas.addActionListener(new EscuchadoraBotonFechas());
            panelFechas.add(botonFechas, 0);
            panelFechas.add(new JLabel(), 1);

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

            areaTexto = new JTextArea(15, 95);
            areaTexto.setEditable(false);
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                if (gestion.hayClientes()) {
                    StringBuilder resultado = new StringBuilder();
                    if (botonFechas.isSelected()) {
                        if (datoFechaInicio.getText().equals("  /  /    "))
                            resultado.append("Parámetro FECHA INICIO incorrecto\n");
                        if (datoFechaFinal.getText().equals("  /  /    "))
                            resultado.append("Parámetro FECHA FINAL incorrecto\n");
                    }

                    if (!resultado.toString().equals(""))
                        areaTexto.setText(resultado.toString());
                    else {
                        try {
                            Collection<Cliente> listaClientes = gestion.listarClientes();
                            if (botonFechas.isSelected()) {
                                Calendar fechaInicio = GregorianCalendar.getInstance();
                                Calendar fechaFinal = GregorianCalendar.getInstance();

                                String[] vectorFechaInicio = datoFechaInicio.getText().split("/");
                                String[] vectorFechaFinal = datoFechaFinal.getText().split("/");

                                fechaInicio.set(Integer.parseInt(vectorFechaInicio[2]), Integer.parseInt(vectorFechaInicio[1]), Integer.parseInt(vectorFechaInicio[0]));
                                fechaFinal.set(Integer.parseInt(vectorFechaFinal[2]), Integer.parseInt(vectorFechaFinal[1]), Integer.parseInt(vectorFechaFinal[0]));

                                listaClientes = new GestionEntreFechas<Cliente>().entreFechas(listaClientes, fechaInicio, fechaFinal);
                            }

                            areaTexto.setText("");
                            if (!listaClientes.isEmpty())
                                for (Cliente cliente : listaClientes)
                                    areaTexto.append(cliente.toString());
                            else
                                areaTexto.setText("No hay clientes en la base de datos");
                        } catch (ListaClientesVacio ex) {
                            ex.printStackTrace();
                        }
                    }
                } else
                    areaTexto.setText("No hay clientes en la base de datos");
            });

            layout.putConstraint(SpringLayout.NORTH, panelTexto, 200, SpringLayout.NORTH, panelFechas);

            panelSuperior.add(panelFechas);
            panelSuperior.add(panelTexto);

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panelSuperior.updateUI();
            panelBotones.updateUI();

            panel.add(panelSuperior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraAltaLlamada implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            SpringLayout layout = new SpringLayout();
            panelSuperior = new JPanel();
            panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
            panelSuperior.setLayout(layout);

            panelNIF = new JPanel();
            panelNIF.add(NIF);
            datoNIF = new JTextField(10);
            panelNIF.add(datoNIF);

            panelNumeroDeTelefono = new JPanel();
            panelNumeroDeTelefono.add(numeroDeTelefono);
            datoNumeroDeTelefono = new JTextField(10);
            panelNumeroDeTelefono.add(datoNumeroDeTelefono);

            panelDuracionDeLlamada = new JPanel();
            panelDuracionDeLlamada.add(duracionDeLlamada);
            datoDuracionLLamada = new JTextField(10);
            panelDuracionDeLlamada.add(datoDuracionLLamada);

            areaTexto = new JTextArea(15, 95);
            areaTexto.setEditable(false);
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                if (gestion.hayClientes()) {
                    StringBuilder resultado = new StringBuilder();
                    if (datoNIF.getText().equals(""))
                        resultado.append("Parámetro NIF incorrecto\n");
                    else if (!gestion.existeCliente(datoNIF.getText()))
                        resultado.append("El cliente con NIF " + datoNIF.getText() + " no existe\n");
                    if (datoNumeroDeTelefono.getText().equals(""))
                        resultado.append("Parámetro NUMERO incorrecto\n");
                    if (datoDuracionLLamada.getText().equals(""))
                        resultado.append("Parámetro DURACION incorrecto\n");

                    if (!resultado.toString().equals(""))
                        areaTexto.setText(resultado.toString());
                    else {
                        if (controlador.darAltaLlamada())
                            areaTexto.setText("Añadida la llamada al cliente con NIF " + datoNIF.getText() + "\n");
                        else
                            areaTexto.setText("No se ha podido añadir la llamada al cliente con NIF " + datoNIF.getText() + "\n");

                        guardarDatos();
                    }
                } else
                    areaTexto.setText("No hay clientes en la base de datos");
            });

            layout.putConstraint(SpringLayout.NORTH, panelNumeroDeTelefono, 25, SpringLayout.NORTH, panelNIF);
            layout.putConstraint(SpringLayout.NORTH, panelDuracionDeLlamada, 25, SpringLayout.NORTH, panelNumeroDeTelefono);
            layout.putConstraint(SpringLayout.NORTH, panelTexto, 150, SpringLayout.NORTH, panelDuracionDeLlamada);

            panelSuperior.add(panelNIF);
            panelSuperior.add(panelNumeroDeTelefono);
            panelSuperior.add(panelDuracionDeLlamada);
            panelSuperior.add(panelTexto);

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panelSuperior.updateUI();
            panelBotones.updateUI();

            panel.add(panelSuperior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraListarLlamadas implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            SpringLayout layout = new SpringLayout();
            panelSuperior = new JPanel();
            panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
            panelSuperior.setLayout(layout);

            panelNIF = new JPanel();
            panelNIF.add(NIF);
            datoNIF = new JTextField(10);
            panelNIF.add(datoNIF);

            panelFechas = new JPanel();
            panelFechas.setLayout(new GridLayout(3, 2));

            botonFechas = new JCheckBox("Entre fechas");
            botonFechas.addActionListener(new EscuchadoraBotonFechas());
            panelFechas.add(botonFechas, 0);
            panelFechas.add(new JLabel(), 1);

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

            areaTexto = new JTextArea(15, 95);
            areaTexto.setEditable(false);
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                if (gestion.hayClientes()) {
                    StringBuilder resultado = new StringBuilder();
                    if (datoNIF.getText().equals(""))
                        resultado.append("Parámetro NIF incorrecto\n");
                    else if (!gestion.existeCliente(datoNIF.getText()))
                        resultado.append("El cliente con NIF " + datoNIF.getText() + " no existe");
                    else if (gestion.clienteConLlamadas(datoNIF.getText()))
                        areaTexto.setText("El cliente con NIF " + datoNIF.getText() + " no tiene llamadas\n");
                    if (botonFechas.isSelected()) {
                        if (datoFechaInicio.getText().equals("  /  /    "))
                            resultado.append("Parámetro FECHA INICIO incorrecto\n");
                        if (datoFechaFinal.getText().equals("  /  /    "))
                            resultado.append("Parámetro FECHA FINAL incorrecto\n");
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

                                fechaInicio.set(Integer.parseInt(vectorFechaInicio[2]), Integer.parseInt(vectorFechaInicio[1]), Integer.parseInt(vectorFechaInicio[0]));
                                fechaFinal.set(Integer.parseInt(vectorFechaFinal[2]), Integer.parseInt(vectorFechaFinal[1]), Integer.parseInt(vectorFechaFinal[0]));

                                listaLlamadas = new GestionEntreFechas<Llamada>().entreFechas(listaLlamadas, fechaInicio, fechaFinal);
                            }

                            areaTexto.setText("");
                            for (Llamada llamada : listaLlamadas)
                                areaTexto.append(llamada.toString());
                        } catch (ClienteNoEncontrado | ClienteNoLlamadas ex) {
                            ex.printStackTrace();
                        }
                    }
                } else
                    areaTexto.setText("No hay clientes en la base de datos");
            });

            layout.putConstraint(SpringLayout.NORTH, panelFechas, 25, SpringLayout.NORTH, panelNIF);
            layout.putConstraint(SpringLayout.NORTH, panelTexto, 175, SpringLayout.NORTH, panelFechas);

            panelSuperior.add(panelNIF);
            panelSuperior.add(panelFechas);
            panelSuperior.add(panelTexto);

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panelSuperior.updateUI();
            panelBotones.updateUI();

            panel.add(panelSuperior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraEmitirFactura implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            SpringLayout layout = new SpringLayout();
            panelSuperior = new JPanel();
            panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
            panelSuperior.setLayout(layout);

            panelNIF = new JPanel();
            panelNIF.add(NIF);
            datoNIF = new JTextField(10);
            panelNIF.add(datoNIF);

            panelFechas = new JPanel();
            panelFechas.setLayout(new GridLayout(2, 2));

            panelFechas.add(fechaFacturacion, 0);
            datoFechaFacturacion = new JFormattedTextField(formatoFecha);
            panelFechas.add(datoFechaFacturacion, 1);

            panelFechas.add(fechaEmision, 2);
            datoFechaEmision = new JFormattedTextField(formatoFecha);
            panelFechas.add(datoFechaEmision, 3);

            areaTexto = new JTextArea(15, 95);
            areaTexto.setEditable(false);
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                if (gestion.hayClientes()) {
                    StringBuilder resultado = new StringBuilder();
                    if (datoNIF.getText().equals(""))
                        resultado.append("Parámetro NIF incorrecto\n");
                    else if (!gestion.existeCliente(datoNIF.getText()))
                        resultado.append("El cliente con NIF " + datoNIF.getText() + " no existe\n");
                    else if (!gestion.clienteConLlamadas(datoNIF.getText()))
                        areaTexto.setText("El cliente con NIF " + datoNIF.getText() + " no ha realizado ninguna llamada");
                    if (datoFechaInicio.getText().equals("  /  /    "))
                        resultado.append("Parámetro FECHA EMISIÓN incorrecto\n");
                    if (datoFechaFinal.getText().equals("  /  /    "))
                        resultado.append("Parámetro FECHA FACTURACIÓN incorrecto\n");

                    if (!resultado.toString().equals(""))
                        areaTexto.setText(resultado.toString());
                    else {
                        if (controlador.emitirFactura())
                            areaTexto.setText("Emitida la factura del cliente con NIF " + datoNIF.getText() + "\n");
                        else
                            areaTexto.setText("No se ha podido emitir la factura del cliente con NIF " + datoNIF.getText() + "\n");

                        guardarDatos();
                    }
                } else
                    areaTexto.setText("No hay clientes en la base de datos");
            });

            layout.putConstraint(SpringLayout.NORTH, panelFechas, 25, SpringLayout.NORTH, panelNIF);
            layout.putConstraint(SpringLayout.NORTH, panelTexto, 175, SpringLayout.NORTH, panelFechas);

            panelSuperior.add(panelNIF);
            panelSuperior.add(panelFechas);
            panelSuperior.add(panelTexto);

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panelSuperior.updateUI();
            panelBotones.updateUI();

            panel.add(panelSuperior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraMostrarFactura implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            SpringLayout layout = new SpringLayout();
            panelSuperior = new JPanel();
            panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
            panelSuperior.setLayout(layout);

            panelCodigoFactura = new JPanel();
            panelCodigoFactura.add(codigoFactura);
            datoCodigoFactura = new JTextField(10);
            panelCodigoFactura.add(datoCodigoFactura);

            areaTexto = new JTextArea(15, 95);
            areaTexto.setEditable(false);
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoCodigoFactura.getText().equals(""))
                    resultado.append("Parámetro CÓDIGO FACTURA incorrecto\n");
                else if (!gestion.existeFactura(Integer.parseInt(datoCodigoFactura.getText())))
                    resultado.append("La factura con CÓDIGO " + datoCodigoFactura.getText() + " no existe\n");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else {
                    try {
                        Factura factura = gestion.mostrarFactura(codigoFactura());
                        areaTexto.setText("MOSTRANDO FACTURA CON CÓDIGO " + factura.codigoFactura());
                        areaTexto.append("\n" + factura);
                    } catch (ListaFacturasVacia | FacturaNoEncontrada ex) {
                        ex.printStackTrace();
                    }

                    guardarDatos();
                }
            });

            layout.putConstraint(SpringLayout.NORTH, panelTexto, 200, SpringLayout.NORTH, panelTarifa);

            panelSuperior.add(panelNIF);
            panelSuperior.add(panelTexto);

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panelSuperior.updateUI();
            panelBotones.updateUI();

            panel.add(panelSuperior, BorderLayout.CENTER);
            panel.add(panelBotones, BorderLayout.SOUTH);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraListarFacturas implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            SpringLayout layout = new SpringLayout();
            panelSuperior = new JPanel();
            panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
            panelSuperior.setLayout(layout);

            panelNIF = new JPanel();
            panelNIF.add(NIF);
            datoNIF = new JTextField(10);
            panelNIF.add(datoNIF);

            panelFechas = new JPanel();
            panelFechas.setLayout(new GridLayout(3, 2));

            botonFechas = new JCheckBox("Entre fechas");
            botonFechas.addActionListener(new EscuchadoraBotonFechas());
            panelFechas.add(botonFechas, 0);
            panelFechas.add(new JLabel(), 1);

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

            areaTexto = new JTextArea(15, 95);
            areaTexto.setEditable(false);
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                if (gestion.hayClientes()) {
                    StringBuilder resultado = new StringBuilder();
                    if (datoNIF.getText().equals(""))
                        resultado.append("Parámetro NIF incorrecto\n");
                    else if (!gestion.existeCliente(datoNIF.getText()))
                        resultado.append("El cliente con NIF " + datoNIF.getText() + " no existe");
                    if (botonFechas.isSelected()) {
                        if (datoFechaInicio.getText().equals("  /  /    "))
                            resultado.append("Parámetro FECHA INICIO incorrecto\n");
                        if (datoFechaFinal.getText().equals("  /  /    "))
                            resultado.append("Parámetro FECHA FINAL incorrecto\n");
                    }

                    if (!resultado.toString().equals(""))
                        areaTexto.setText(resultado.toString());
                    else {
                        if (gestion.clienteConFacturas(datoNIF.getText())) {
                            try {
                                Collection<Factura> listaFacturas = gestion.listarFacturas(datoNIF.getText());
                                if (botonFechas.isSelected()) {
                                    Calendar fechaInicio = GregorianCalendar.getInstance();
                                    Calendar fechaFinal = GregorianCalendar.getInstance();

                                    String[] vectorFechaInicio = datoFechaInicio.getText().split("/");
                                    String[] vectorFechaFinal = datoFechaFinal.getText().split("/");

                                    fechaInicio.set(Integer.parseInt(vectorFechaInicio[2]), Integer.parseInt(vectorFechaInicio[1]), Integer.parseInt(vectorFechaInicio[0]));
                                    fechaFinal.set(Integer.parseInt(vectorFechaFinal[2]), Integer.parseInt(vectorFechaFinal[1]), Integer.parseInt(vectorFechaFinal[0]));

                                    listaFacturas = new GestionEntreFechas<Factura>().entreFechas(listaFacturas, fechaInicio, fechaFinal);
                                }

                                areaTexto.setText("");
                                for (Factura factura : listaFacturas)
                                    areaTexto.append(factura.toString());
                            } catch (ClienteNoEncontrado | ClienteNoFacturas ex) {
                                ex.printStackTrace();
                            }
                        }
                        areaTexto.setText("El cliente con NIF " + datoNIF.getText() + " no tiene llamadas\n");
                    }
                } else
                    areaTexto.setText("No hay clientes en la base de datos");
            });

            layout.putConstraint(SpringLayout.NORTH, panelFechas, 25, SpringLayout.NORTH, panelNIF);
            layout.putConstraint(SpringLayout.NORTH, panelTexto, 175, SpringLayout.NORTH, panelFechas);

            panelSuperior.add(panelNIF);
            panelSuperior.add(panelFechas);
            panelSuperior.add(panelTexto);

            panelBotones.add(botonAceptar, FlowLayout.LEFT);
            panelBotones.add(botonReiniciar, FlowLayout.CENTER);
            panelBotones.add(botonVolver, FlowLayout.RIGHT);

            panelSuperior.updateUI();
            panelBotones.updateUI();

            panel.add(panelSuperior, BorderLayout.CENTER);
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
            areaTexto.setText("");

            fechaInicio.setEnabled(false);
            datoFechaInicio.setEnabled(false);
            fechaFinal.setEnabled(false);
            datoFechaFinal.setEnabled(false);
        }
    }

}
