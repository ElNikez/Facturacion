package swing.main;

import facturacion.gestion.Gestion;
import swing.controlador.Controlador;
import swing.vista.VistaPrincipal;

import javax.swing.*;

public class Facturacion {

    public static void main(String[] args) {

        VistaPrincipal vistaPrincipal = new VistaPrincipal();
        Controlador controlador = new Controlador();
        Gestion gestion = new Gestion();

        controlador.setVista(vistaPrincipal);
        controlador.setGestion(gestion);

        vistaPrincipal.setControlador(controlador);
        vistaPrincipal.setGestion(gestion);

//        gestion.setVistaPrincipal(vistaPrincipal);

        SwingUtilities.invokeLater(vistaPrincipal::iniciarPrograma);
    }

}
