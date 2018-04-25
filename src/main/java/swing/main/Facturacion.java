package swing.main;

import swing.vista.VistaPrincipal;

import javax.swing.*;

public class Facturacion {

    public static void main(String[] args) {
        VistaPrincipal vistaPrincipal = new VistaPrincipal();

        SwingUtilities.invokeLater(vistaPrincipal::cargarVista);
    }

}
