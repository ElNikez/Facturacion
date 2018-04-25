package swing.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPrincipal {

    private JFrame ventana;
    private Container panel;

    public void cargarVista() {
        ventana = new JFrame("Facturación");
        panel = ventana.getContentPane();
        JLabel textoInicio = new JLabel("PANTALLA PRINCIPAL");
        textoInicio.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(textoInicio);

        JMenuBar menu = new JMenuBar();
        JMenu menuClientes = new JMenu("Clientes");
        JMenu menuLlamadas = new JMenu("Llamadas");
        JMenu menuFacturas = new JMenu("Facturas");
        JMenu altaCliente = new JMenu("Dar de alta");

        JMenuItem altaEmpresa = new JMenuItem("Empresa");
        altaEmpresa.addActionListener(new EscuchadoraAltaEmpresa());
        JMenuItem altaParticular = new JMenuItem("Particular");
        altaParticular.addActionListener(new EscuchadoraAltaParticular());
        JMenuItem bajaCliente = new JMenuItem("Dar de baja");
        bajaCliente.addActionListener(new EscuchadoraBajaCliente());
        JMenuItem datosCliente = new JMenuItem("Mostrar datos");
        datosCliente.addActionListener(new EscuchadoraDatosCliente());
        JMenuItem cambiarTarifa = new JMenuItem("Cambiar tarifa");
        cambiarTarifa.addActionListener(new EscuchadoraCambiarTarifa());
        JMenuItem listarClientes = new JMenuItem("Listar clientes");
        listarClientes.addActionListener(new EscuchadoraListarClientes());
        altaCliente.add(altaEmpresa);
        altaCliente.add(altaParticular);
        menuClientes.add(altaCliente);
        menuClientes.add(bajaCliente);
        menuClientes.add(datosCliente);
        menuClientes.add(cambiarTarifa);
        menuClientes.add(listarClientes);
        menu.add(menuClientes);

        JMenuItem altaLlamada = new JMenuItem("Dar de alta");
        altaLlamada.addActionListener(new EscuchadoraAltaLlamada());
        JMenuItem listarLlamadas = new JMenuItem("Listar llamadas");
        listarLlamadas.addActionListener(new EscuchadoraListarLlamadas());
        menuLlamadas.add(altaLlamada);
        menuLlamadas.add(listarLlamadas);
        menu.add(menuLlamadas);

        JMenuItem emitirFactura = new JMenuItem("Emitir factura");
        emitirFactura.addActionListener(new EscuchadoraEmitirFactura());
        JMenuItem mostrarFactura = new JMenuItem("Mostrar factura por código");
        mostrarFactura.addActionListener(new EscuchadoraMostrarFactura());
        JMenuItem listarFacturas = new JMenuItem("Listar facturas cliente");
        listarFacturas.addActionListener(new EscuchadoraListarFacturas());
        menuFacturas.add(emitirFactura);
        menuFacturas.add(mostrarFactura);
        menuFacturas.add(listarFacturas);
        menu.add(menuFacturas);

        ventana.setJMenuBar(menu);

        Dimension resolucion = Toolkit.getDefaultToolkit().getScreenSize();
        ventana.setSize((resolucion.width / 2), (resolucion.height / 2));
        ventana.setLocation((int) (resolucion.width * 0.05), (int) (resolucion.height * 0.05));
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class EscuchadoraAltaEmpresa implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();

            JLabel texto = new JLabel("Texto inútil en ALTA PARTICULAR");
            panel.add(texto);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraAltaParticular implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();

            JLabel texto = new JLabel("Texto inútil en ALTA PARTICULAR");
            panel.add(texto);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraBajaCliente implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();

            JLabel texto = new JLabel("Texto inútil en BAJA CLIENTE");
            panel.add(texto);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraDatosCliente implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();

            JLabel texto = new JLabel("Texto inútil en DATOS CLIENTE");
            panel.add(texto);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraCambiarTarifa implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();

            JLabel texto = new JLabel("Texto inútil en CAMBIAR TARIFA");
            panel.add(texto);

            ventana.setContentPane(panel);
        }
    }

    private class EscuchadoraListarClientes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.removeAll();

            JLabel texto = new JLabel("Texto inútil en LISTAR CLIENTES");
            panel.add(texto);

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

}
