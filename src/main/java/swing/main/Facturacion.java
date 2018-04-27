package swing.main;

import swing.vista.InterfazGrafica;

import javax.swing.*;

public class Facturacion {

    public static void main(String[] args) {
        InterfazGrafica interfazGrafica = new InterfazGrafica();

        SwingUtilities.invokeLater(interfazGrafica::iniciarPrograma);
    }

}
