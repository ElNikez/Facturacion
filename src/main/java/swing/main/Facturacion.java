package swing.main;

import swing.controlador.Controlador;
import swing.modelo.gestion.Gestion;
import swing.vista.VistaPrincipal;

import javax.swing.*;

public class Facturacion {

    public static void main(String[] args) {
        Gestion gestion = new Gestion();
        Controlador controlador = new Controlador();
        VistaPrincipal vista = new VistaPrincipal();

        controlador.cambiarVista(vista);
        controlador.cambiarGestion(gestion);

        vista.cambiarControlador(controlador);
        vista.cambiarGestion(gestion);

        SwingUtilities.invokeLater(vista::iniciarPrograma);
    }

}
