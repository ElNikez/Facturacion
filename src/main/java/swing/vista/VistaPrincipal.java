package swing.vista;


import facturacion.excepciones.*;
import facturacion.facturas.Factura;
import facturacion.gestion.VistaGestionParaGrafica;
import facturacion.clientes.Cliente;
import swing.controlador.VistaControlador;



import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class VistaPrincipal implements VistaGraficaParaControlador {


    private VistaGestionParaGrafica vistaGestionParaGrafica;
    private VistaControlador vistaControlador;
    private JFrame ventana;
    private Container panel;
    private JPanel panelSuperior, panelInferior, panelTexto, panelBotones, panelFechas;
    private JLabel principal, NIF, nombre, apellidos, codPostal, poblacion, provincia, correo, tarifa, fechaInicio, fechaFinal,fechaFacturacion,fechaEmision;
    private JLabel numeroDeTelefono,fechaDeLLamada,duracionDeLlamada;
    private JLabel codigoFactura;
    private JTextField datoNIF, datoNombre, datoApellidos, datoCodPostal, datoPoblacion, datoProvincia, datoCorreo;
    private JTextField datoNumeroDeTelefono,datoDuracionLLamada;
    private JTextField datoCodigoFactura;
    private JFormattedTextField  datoFechaInicio, datoFechaFinal,datoFechaFacturacion,datoFechaEmision;
    private JComboBox<String> datoTarifa;
    private JTextArea areaTexto;
    private JButton botonAceptar, botonReiniciar, botonVolver;
    private JCheckBox botonFechas;
    private JDialog selectorFechaInicio, selectorFechaFinal;
    private JScrollPane menuScrollPane;

    @Override
    public String getNIf(){return datoNIF.getText(); }
    @Override
    public String getNombre() {
        return datoNombre.getText();
    }
    @Override
    public String getApellidos() {
        return datoApellidos.getText();
    }
    @Override
    public int getCodigoPostal() {
        return Integer.parseInt(datoCodPostal.getText());
    }
    @Override
    public String getPoblacion() {
        return datoPoblacion.getText();
    }
    @Override
    public String getProvincia() {
        return datoProvincia.getText();
    }
    @Override
    public String getCorreo() {
        return datoCorreo.getText();
    }
    @Override
    public String getTarifa() {
        return (String) datoTarifa.getSelectedItem();
    }
    public String getFechaEmision() {
        return datoFechaEmision.getText();
    }
    public String getFechaFacturacion() {
        return datoFechaFacturacion.getText();
    }
    public String getNumeroDeTelefono() {
        return datoNumeroDeTelefono.getText();
    }
    public String getDuracionDeLlamada() {
        return datoDuracionLLamada.getText();
    }
    public void setControlador(VistaControlador vistaControlador){
        this.vistaControlador= vistaControlador;
    }
    public void setGestion(VistaGestionParaGrafica vistaGestionParaGrafica){
        this.vistaGestionParaGrafica= vistaGestionParaGrafica;
    }
    public void iniciarPrograma() {

        ventana = new JFrame("Facturación");

        panel = ventana.getContentPane();

        panelSuperior = new JPanel();
        panelInferior = new JPanel();
        panelBotones = new JPanel();
        panelTexto = new JPanel();
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
        fechaFacturacion = new JLabel("FechaFacturacion (dd/MM/yyyy)");
        fechaEmision = new JLabel("FechaEmision (dd/MM/yyyy)");

        numeroDeTelefono = new JLabel("NumeroDeTelefono");
        fechaDeLLamada = new JLabel("FechaDeLlamada");
        duracionDeLlamada = new JLabel("DuracionDeLlamada");

        codigoFactura = new JLabel("CodigoFactura");

        botonAceptar = new JButton("Aceptar");
        botonReiniciar = new JButton("Reiniciar");
        botonVolver = new JButton("Volver");

        botonFechas = new JCheckBox("Entre fechas");

        selectorFechaInicio = new JDialog();
        selectorFechaFinal = new JDialog();

        cargarVista();
    }

    private void cargarVista() {
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

        cargarPanelPrincipal();
    }

    private void cargarPanelPrincipal() {
        panel.removeAll();

        panel.add(principal);

        ventana.setContentPane(panel);
    }

    public String mostrarClientes(Cliente cliente) {

            return
                    "Nif: "+ cliente.getNif()+"/n"+
                    "Nombre: "+cliente.getNombre()+"/n"+
                    "Correo: "+cliente.getCorreo()+"/n"+
                    "Direccion: " + cliente.getDireccion().toString() +"/n"+
                    "Tarifa: " + cliente.getTarifa().toString();
    }
    public String mostrarFacturas(Factura factura) {

        return
                "Codigo: "+ factura.getCodigoFactura()+"/n"+ "Tarifa: " + factura.getTarifaAplicada().toString();
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

            panelSuperior.add(codPostal, 4);
            datoCodPostal = new JTextField();
            panelSuperior.add(datoCodPostal, 5);

            panelSuperior.add(poblacion, 6);
            datoPoblacion = new JTextField();
            panelSuperior.add(datoPoblacion, 7);

            panelSuperior.add(provincia, 8);
            datoProvincia = new JTextField();
            panelSuperior.add(datoProvincia, 9);

            panelSuperior.add(correo, 10);
            datoCorreo = new JTextField();
            panelSuperior.add(datoCorreo, 11);


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
                if (datoNombre.getText().equals(""))
                    resultado.append("Parámetro NOMBRE incorrecto\n");
                if (datoCodPostal.getText().equals(""))
                    resultado.append("Parámetro CÓDIGO POSTAL incorrecto\n");
                if (datoPoblacion.getText().equals(""))
                    resultado.append("Parámetro POBLACIÓN incorrecto\n");
                if (datoProvincia.getText().equals(""))
                    resultado.append("Parámetro PROVINCIA incorrecto\n");
                if (datoCorreo.getText().equals(""))
                    resultado.append("Parámetro CORREO incorrecto\n");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else{
                    //areaTexto.setText("AÑADIENDO EMPRESA CON NIF " + datoNIF.getText());

                boolean anyadido = vistaControlador.darDeAltaEmpresa();
                if (anyadido) {
                    areaTexto.setText("AÑADIDO EMPRESA CON NIF " + datoNIF.getText());

                    datoNIF.setText("");
                    datoNombre.setText("");
                    datoCodPostal.setText("");
                    datoPoblacion.setText("");
                    datoProvincia.setText("");
                    datoCorreo.setText("");
                } else {
                    areaTexto.setText("NO SE HA PODIDO AÑADIR EMPRESA CON NIF " + datoNIF.getText());

                    datoNIF.setText("");
                    datoNombre.setText("");
                    datoCodPostal.setText("");
                    datoPoblacion.setText("");
                    datoProvincia.setText("");
                    datoCorreo.setText("");
                }

            }
            });
            botonReiniciar.addActionListener(reiniciar -> {
                datoNIF.setText("");
                datoNombre.setText("");
                datoCodPostal.setText("");
                datoPoblacion.setText("");
                datoProvincia.setText("");
                datoCorreo.setText("");

                areaTexto.setText("");
            });
            botonVolver.addActionListener(new EscuchadoraBotonVolver());

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

            panelSuperior.add(codPostal, 6);
            datoCodPostal = new JTextField();
            panelSuperior.add(datoCodPostal, 7);

            panelSuperior.add(poblacion, 8);
            datoPoblacion = new JTextField();
            panelSuperior.add(datoPoblacion, 9);

            panelSuperior.add(provincia, 10);
            datoProvincia = new JTextField();
            panelSuperior.add(datoProvincia, 11);

            panelSuperior.add(correo, 12);
            datoCorreo = new JTextField();
            panelSuperior.add(datoCorreo, 13);


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
                if (datoNombre.getText().equals(""))
                    resultado.append("Parámetro NOMBRE incorrecto\n");
                if (datoApellidos.getText().equals(""))
                    resultado.append("Parámetro APELLIDOS incorrecto\n");
                if (datoCodPostal.getText().equals(""))
                    resultado.append("Parámetro CÓDIGO POSTAL incorrecto\n");
                if (datoPoblacion.getText().equals(""))
                    resultado.append("Parámetro POBLACIÓN incorrecto\n");
                if (datoProvincia.getText().equals(""))
                    resultado.append("Parámetro PROVINCIA incorrecto\n");
                if (datoCorreo.getText().equals(""))
                    resultado.append("Parámetro CORREO incorrecto\n");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else{
                    areaTexto.setText("AÑADIENDO PARTICULAR CON NIF " + datoNIF.getText());


                    boolean añadido = vistaControlador.darDeAltaParticular();
                    if (añadido){
                        areaTexto.append("AÑADIDO PARTICULAR CON NIF " + datoNIF.getText());
                    }
                    else{
                        areaTexto.append("NO SE HA PODIDO AÑADIR PARTICULAR CON NIF " + datoNIF.getText());
                    }


                }
            });
            botonReiniciar.addActionListener(reiniciar -> {
                datoNIF.setText("");
                datoNombre.setText("");
                datoApellidos.setText("");
                datoCodPostal.setText("");
                datoPoblacion.setText("");
                datoProvincia.setText("");
                datoCorreo.setText("");

                areaTexto.setText("");
            });
            botonVolver.addActionListener(new EscuchadoraBotonVolver());

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

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else
                    areaTexto.setText("BORRANDO CLIENTE CON NIF " + datoNIF.getText());
                boolean borrado = false;
                try {
                    borrado = vistaControlador.darDeBaja();
                } catch (ClienteNoEncontrado clienteNoEncontrado) {
                    clienteNoEncontrado.printStackTrace();
                }
                if (borrado){
                        areaTexto.setText("BORRADO CLIENTE CON NIF " + datoNIF.getText());
                    }
                    else{
                        areaTexto.setText("NO SE HA PODIDO BORRAR CLIENTE CON NIF " + datoNIF.getText());
                    }
            });
            botonReiniciar.addActionListener(reiniciar -> {
                datoNIF.setText("");

                areaTexto.setText("");
            });
            botonVolver.addActionListener(new EscuchadoraBotonVolver());

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
                    "Tarifa básica",
                    "Promoción madrugadas",
                    "Promoción tardes",
                    "Promoción domingos",
                    "Promoción festivos"
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

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else
                    areaTexto.setText("CAMBIANDO TARIFA DEL CLIENTE CON NIF " + datoNIF.getText());
                try {
                    vistaControlador.cambiarTarifa();
                } catch (ClienteNoEncontrado clienteNoEncontrado) {
                    clienteNoEncontrado.printStackTrace();
                }
            });
            botonReiniciar.addActionListener(reiniciar -> {
                datoNIF.setText("");
                datoTarifa.setSelectedIndex(0);

                areaTexto.setText("");
            });
            botonVolver.addActionListener(new EscuchadoraBotonVolver());

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
            botonAceptar.addActionListener(aceptariyhgiyg -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro NIF incorrecto\n");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else {
                    Cliente cliente = null;
                    try {
                        cliente = vistaGestionParaGrafica.mostrarCliente(datoNIF.getText());
                    } catch (ClienteNoEncontrado clienteNoEncontrado) {
                        clienteNoEncontrado.printStackTrace();
                    }
                    areaTexto.setText("MOSTRANDO CLIENTE CON NIF " + datoNIF.getText() + "/n" +
                            mostrarClientes(cliente));

                }

            });
            botonReiniciar.addActionListener(reiniciar -> {
                datoNIF.setText("");

                areaTexto.setText("");
            });
            botonVolver.addActionListener(new EscuchadoraBotonVolver());

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

            botonFechas.addActionListener(entreFechas -> {

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
            });
            panelSuperior.add(botonFechas, BorderLayout.NORTH);

            panelFechas= new JPanel();
            panelFechas.setLayout(new GridLayout(2, 2));

            MaskFormatter formatter = null;
            try {
                formatter = new MaskFormatter("##/##/####");
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

            panelFechas.add(fechaInicio, 0);
            fechaInicio.setEnabled(false);
            datoFechaInicio = new JFormattedTextField(formatter);
            datoFechaInicio.setEnabled(false);
            panelFechas.add(datoFechaInicio, 1);

            panelFechas.add(fechaFinal, 2);
            fechaFinal.setEnabled(false);
            datoFechaFinal = new JFormattedTextField(formatter);
            datoFechaFinal.setEnabled(false);
            panelFechas.add(datoFechaFinal, 3);

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
                if (!botonFechas.isSelected()) {

                    try {
                        DefaultListModel<Cliente> clientes = new DefaultListModel<>();
                        JList<Cliente> listaClientes = new JList<>();
                        for (Cliente cliente :vistaGestionParaGrafica.listarClientes()) {
                            clientes.addElement(cliente);
                        }
                        listaClientes.setModel(clientes);
                        menuScrollPane = new JScrollPane(listaClientes);
                        panelTexto.add(menuScrollPane, BorderLayout.CENTER);

                    } catch (ListaClientesVacio listaClientesVacio) {
                        listaClientesVacio.printStackTrace();
                    }
                }
                else {
                    StringBuilder resultado = new StringBuilder();
                    if (datoFechaInicio.getText().equals(""))
                        resultado.append("Parámetro FECHA INICIO incorrecto");
                    if (datoFechaFinal.getText().equals(""))
                        resultado.append("Parámetro FECHA FINAL incorrecto");

                    if (!resultado.toString().equals(""))
                        areaTexto.setText(resultado.toString());
                    else
                        areaTexto.setText("LISTANDO CLIENTES ENTRE " + datoFechaInicio.getText() + " Y " + datoFechaFinal.getText());
                }
            });
            botonReiniciar.addActionListener(reiniciar -> {
                datoFechaInicio.setText("");
                datoFechaFinal.setText("");

                areaTexto.setText("");
            });
            botonVolver.addActionListener(new EscuchadoraBotonVolver());

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
            datoNumeroDeTelefono= new JTextField();
            panelSuperior.add(datoNumeroDeTelefono, 3);


            panelSuperior.add(duracionDeLlamada, 4);
            datoDuracionLLamada= new JTextField();
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
                    boolean anyadido = false;
                    try {
                        anyadido = vistaControlador.darDeAltaLlamada();
                    } catch (ClienteNoEncontrado clienteNoEncontrado) {
                        clienteNoEncontrado.printStackTrace();
                    }
                    if (anyadido) {
                        areaTexto.setText("AÑADIDA LLAMADA CON NIF " + datoNIF.getText());
                        datoNIF.setText("");
                        datoNumeroDeTelefono.setText("");
                        datoDuracionLLamada.setText("");
                    } else {
                        areaTexto.setText("NO SE HA PODIDO AÑADIR LA LLAMADA CON NIF " + datoNIF.getText());
                        datoNIF.setText("");
                        datoNumeroDeTelefono.setText("");
                        datoDuracionLLamada.setText("");
                    }

                }
            });
            botonReiniciar.addActionListener(reiniciar -> {
                datoNIF.setText("");
                datoNumeroDeTelefono.setText("");
                datoDuracionLLamada.setText("");
                areaTexto.setText("");
            });
            botonVolver.addActionListener(new EscuchadoraBotonVolver());

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

            botonFechas.addActionListener(entreFechas -> {

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
            });
            panelSuperior.add(botonFechas, BorderLayout.NORTH);
            panelFechas = new JPanel();
            panelFechas.setLayout(new GridLayout(3, 2));

            MaskFormatter formatter = null;
            try {
                formatter = new MaskFormatter("##/##/####");
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

            panelFechas.add(NIF, 0);
            datoNIF = new JTextField();
            panelFechas.add(datoNIF, 1);

            panelFechas.add(fechaInicio, 2);
            fechaInicio.setEnabled(false);
            datoFechaInicio = new JFormattedTextField(formatter);
            datoFechaInicio.setEnabled(false);
            panelFechas.add(datoFechaInicio, 3);

            panelFechas.add(fechaFinal, 4);
            fechaFinal.setEnabled(false);
            datoFechaFinal = new JFormattedTextField(formatter);
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
                if (!botonFechas.isSelected()) {

                    try {
                      vistaGestionParaGrafica.listarLlamadas(datoNIF.getText());
                    } catch (ClienteNoEncontrado clienteNoEncontrado ) {
                        clienteNoEncontrado.printStackTrace();
                    } catch (ClienteNoLlamadas clienteNoLlamadas) {
                        clienteNoLlamadas.printStackTrace();
                    }
                }
                else {
                    StringBuilder resultado = new StringBuilder();
                    if(datoNIF.getText().equals(""))
                        resultado.append("Parámetro NIF incorrecto");
                    if (datoFechaInicio.getText().equals(""))
                        resultado.append("Parámetro FECHA INICIO incorrecto");
                    if (datoFechaFinal.getText().equals(""))
                        resultado.append("Parámetro FECHA FINAL incorrecto");

                    if (!resultado.toString().equals(""))
                        areaTexto.setText(resultado.toString());
                    else
                        areaTexto.setText("LISTANDO CLIENTES ENTRE " + datoFechaInicio.getText() + " Y " + datoFechaFinal.getText());
                }
            });
            botonReiniciar.addActionListener(reiniciar -> {
                datoFechaInicio.setText("");
                datoFechaFinal.setText("");

                areaTexto.setText("");
            });
            botonVolver.addActionListener(new EscuchadoraBotonVolver());

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

            panelFechas= new JPanel();
            panelFechas.setLayout(new GridLayout(3, 2));

            MaskFormatter formatter = null;
            try {
                formatter = new MaskFormatter("##/##/####");
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

            panelFechas.add(NIF, 0);
            datoNIF = new JTextField();
            panelFechas.add(datoNIF, 1);

            panelFechas.add(fechaFacturacion, 2);
            datoFechaFacturacion = new JFormattedTextField(formatter);
            panelFechas.add(datoFechaFacturacion, 3);

            panelFechas.add(fechaEmision, 4);
            datoFechaEmision = new JFormattedTextField(formatter);
            panelFechas.add(datoFechaEmision, 5);


            panelSuperior.add(panelFechas, BorderLayout.SOUTH);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {

                try {
                    vistaControlador.emitirFactura();
                    datoNIF.setText("");
                    datoFechaInicio.setText("");
                    datoFechaFinal.setText("");

                } catch (ClienteNoEncontrado clienteNoEncontrado) {
                    clienteNoEncontrado.printStackTrace();
                } catch (ClienteNoLlamadas clienteNoLlamadas) {
                    clienteNoLlamadas.printStackTrace();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                JLabel texto = new JLabel("Texto inútil en EMITIR FACTURA");

            });
            botonReiniciar.addActionListener(reiniciar -> {
                datoFechaInicio.setText("");
                datoFechaFinal.setText("");
                areaTexto.setText("");
            });
            botonVolver.addActionListener(new EscuchadoraBotonVolver());

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
                    Factura factura = null;
                    try {
                        int codigo = Integer.parseInt(codigoFactura.getText());
                        factura = vistaGestionParaGrafica.mostrarFactura(codigo);
                            datoCodigoFactura.setText("");
                    }
                    catch (ListaFacturasVacia listaFacturasVacia) {
                        listaFacturasVacia.printStackTrace();
                    } catch (FacturaNoEncontrada facturaNoEncontrada) {
                        facturaNoEncontrada.printStackTrace();
                    }
                    areaTexto.setText("MOSTRANDO FACTURA CON CODIGO " + datoCodigoFactura.getText() + "/n" +
                            mostrarFacturas(factura));

                }

            });
            botonReiniciar.addActionListener(reiniciar -> {
                datoNIF.setText("");

                areaTexto.setText("");
            });
            botonVolver.addActionListener(new EscuchadoraBotonVolver());

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

            botonFechas.addActionListener(entreFechass -> {

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
            });
            panelSuperior.add(botonFechas, BorderLayout.NORTH);

            panelFechas= new JPanel();
            panelFechas.setLayout(new GridLayout(3, 2));

            MaskFormatter formatter = null;
            try {
                formatter = new MaskFormatter("##/##/####");
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

            panelFechas.add(NIF, 0);
            datoNIF = new JTextField();
            panelFechas.add(datoNIF, 1);

            panelFechas.add(fechaInicio, 2);
            fechaInicio.setEnabled(false);
            datoFechaInicio = new JFormattedTextField(formatter);
            datoFechaInicio.setEnabled(false);
            panelFechas.add(datoFechaInicio, 3);

            panelFechas.add(fechaFinal, 4);
            fechaFinal.setEnabled(false);
            datoFechaFinal = new JFormattedTextField(formatter);
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
                if (!botonFechas.isSelected()) {


                        DefaultListModel<Factura> facturas = new DefaultListModel<>();
                        JList<Factura> listaFacturas = new JList<>();
                    try {
                        for (Factura factura :vistaGestionParaGrafica.listarFacturas(datoNIF.getText())) {
                            facturas.addElement(factura);
                        }
                        datoNIF.setText("");
                        datoFechaInicio.setText("");
                        datoFechaFinal.setText("");
                    } catch (ClienteNoEncontrado clienteNoEncontrado) {
                        clienteNoEncontrado.printStackTrace();
                    } catch (ClienteNoFacturas clienteNoFacturas) {
                        clienteNoFacturas.printStackTrace();
                    }
                    listaFacturas.setModel(facturas);
                        menuScrollPane = new JScrollPane(listaFacturas);
                        panelTexto.add(menuScrollPane, BorderLayout.CENTER);


                }
                else {
                    StringBuilder resultado = new StringBuilder();
                    if (datoFechaInicio.getText().equals(""))
                        resultado.append("Parámetro FECHA INICIO incorrecto");
                    if (datoFechaFinal.getText().equals(""))
                        resultado.append("Parámetro FECHA FINAL incorrecto");

                    if (!resultado.toString().equals(""))
                        areaTexto.setText(resultado.toString());
                    else
                        areaTexto.setText("LISTANDO CLIENTES ENTRE " + datoFechaInicio.getText() + " Y " + datoFechaFinal.getText());
                }
            });
            botonReiniciar.addActionListener(reiniciar -> {
                datoFechaInicio.setText("");
                datoFechaFinal.setText("");

                areaTexto.setText("");
            });
            botonVolver.addActionListener(new EscuchadoraBotonVolver());

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

}
