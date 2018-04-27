package swing.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazGrafica {

    private JFrame ventana;
    private Container panel;
    private JPanel panelSuperior, panelInferior, panelBotones, panelFechas;
    private JScrollPane panelTexto;
    private JLabel principal, NIF, nombre, apellidos, codPostal, poblacion, provincia, correo, tarifa, fechaInicio, fechaFinal;
    private JTextField datoNIF, datoNombre, datoApellidos, datoCodPostal, datoPoblacion, datoProvincia, datoCorreo, datoFechaInicio, datoFechaFinal;
    private JTextArea areaTexto;
    private JButton botonAceptar, botonReiniciar, botonVolver;
    private JCheckBox botonFechas;
    private JDialog selectorFechaInicio, selectorFechaFinal;

    public void iniciarPrograma() {
        ventana = new JFrame("Facturación");

        panel = ventana.getContentPane();

        panelSuperior = new JPanel();
        panelInferior = new JPanel();
        panelBotones = new JPanel();
        panelTexto = new JScrollPane();
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
        ventana.setSize((int) (resolucion.getWidth() * 0.5), (int) (resolucion.getHeight() * 0.5));
        ventana.setLocation((int) (resolucion.getWidth() * 0.05), (int) (resolucion.getHeight() * 0.05));
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

            panelSuperior.add(apellidos, 4);
            apellidos.setEnabled(false);
            datoApellidos = new JTextField();
            datoApellidos.setEnabled(false);
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
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            panelInferior.add(panelTexto);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro DNI incorrecto\n");
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
                else
                    areaTexto.setText("AÑADIENDO EMPRESA CON NIF " + datoNIF.getText());
            });
            botonReiniciar.addActionListener(new EscuchadoraBotonReiniciar());
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
            panelSuperior.setLayout(new GridLayout(7, 2));

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
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            panelInferior.add(panelTexto);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro DNI incorrecto\n");
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
                else
                    areaTexto.setText("AÑADIENDO PARTICULAR CON NIF " + datoNIF.getText());
            });
            botonReiniciar.addActionListener(new EscuchadoraBotonReiniciar());
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
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            panelInferior.add(panelTexto);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro DNI incorrecto\n");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else
                    areaTexto.setText("BORRANDO CLIENTE CON NIF " + datoNIF.getText());
            });
            botonReiniciar.addActionListener(new EscuchadoraBotonReiniciar());
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

            panelSuperior.add(nombre, 2);
            datoNombre = new JTextField();
            panelSuperior.add(datoNombre, 3);

            panelInferior = new JPanel();

            areaTexto = new JTextArea((int) (ventana.getSize().getHeight() * 0.02), (int) (ventana.getSize().getWidth() * 0.085));
            areaTexto.setEditable(false);
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            panelInferior.add(panelTexto);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar = new JButton("Aceptar");
            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro DNI incorrecto\n");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else
                    areaTexto.setText("CAMBIANDO TARIFA DEL CLIENTE CON NIF " + datoNIF.getText());
            });
            botonReiniciar.addActionListener(new EscuchadoraBotonReiniciar());
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
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            panelInferior.add(panelTexto);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar.addActionListener(aceptar -> {
                StringBuilder resultado = new StringBuilder();
                if (datoNIF.getText().equals(""))
                    resultado.append("Parámetro DNI incorrecto\n");

                if (!resultado.toString().equals(""))
                    areaTexto.setText(resultado.toString());
                else
                    areaTexto.setText("MOSTRANDO CLIENTE CON NIF " + datoNIF.getText());
            });
            botonReiniciar.addActionListener(new EscuchadoraBotonReiniciar());
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

            panelFechas.setLayout(new GridLayout(2, 2));

            panelFechas.add(fechaInicio, 0);
            fechaInicio.setEnabled(false);
            datoFechaInicio = new JTextField();
            datoFechaInicio.setEnabled(false);
            panelFechas.add(datoFechaInicio, 1);

            panelFechas.add(fechaFinal, 2);
            fechaFinal.setEnabled(false);
            datoFechaFinal = new JTextField();
            datoFechaFinal.setEnabled(false);
            panelFechas.add(datoFechaFinal, 3);

            panelSuperior.add(panelFechas, BorderLayout.SOUTH);


            panelInferior = new JPanel();

            areaTexto = new JTextArea((int) (ventana.getSize().getHeight() * 0.04), (int) (ventana.getSize().getWidth() * 0.085));
            areaTexto.setEditable(false);
            panelTexto = new JScrollPane(areaTexto);
            panelTexto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            panelTexto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            panelInferior.add(panelTexto);

            panelBotones = new JPanel();
            panelBotones.setLayout(new FlowLayout());

            botonAceptar.addActionListener(aceptar -> {
                if (!botonFechas.isSelected())
                    areaTexto.setText("LISTANDO CLIENTES");
                else
                    areaTexto.setText("LISTANDO CLIENTES ENTRE " + datoFechaInicio.getText() + " Y " + datoFechaFinal.getText());
            });
            botonReiniciar.addActionListener(new EscuchadoraBotonReiniciar());
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

            JLabel texto = new JLabel("Texto inútil en ALTA LLAMADA");
            panel.add(texto);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraListarLlamadas implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();

            JLabel texto = new JLabel("Texto inútil en LISTAR LLAMADAS");
            panel.add(texto);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraEmitirFactura implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();

            JLabel texto = new JLabel("Texto inútil en EMITIR FACTURA");
            panel.add(texto);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraMostrarFactura implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();

            JLabel texto = new JLabel("Texto inútil en MOSTRAR FACTURA");
            panel.add(texto);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraListarFacturas implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();

            JLabel texto = new JLabel("Texto inútil en LISTAR FACTURAS");
            panel.add(texto);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraBotonReiniciar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            datoNIF.setText("");
            datoNombre.setText("");
            datoApellidos.setText("");
            datoCodPostal.setText("");
            datoPoblacion.setText("");
            datoProvincia.setText("");
            datoCorreo.setText("");

            areaTexto.setText("");
        }
    }

    private class EscuchadoraBotonVolver implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cargarPanelPrincipal();
        }
    }

}
