/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import datosInternos.Administrador;
import datosInternos.Foro;

/**
 * @author Grupo 18
 *Opcion de menu de cambiar de usuario.
 */
public class CambioUsuarioListener implements ActionListener {

	private Foro foro;
	private JLabel lblUsuarioActual;
	private JButton btnNuevaCategoria;
	
	public CambioUsuarioListener(Foro foro, JLabel lblUsuarioActual, JButton btnNuevaCategoria) {
		this.foro=foro;
		this.lblUsuarioActual=lblUsuarioActual;
		this.btnNuevaCategoria=btnNuevaCategoria;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) { // crea componentes pide nick y password nuevo.
		JLabel nickUsuarioLabel = new JLabel("Introduce el nick del Forero");
        JTextField nickUsuario = new JTextField();
        JLabel passUsuarioLabel = new JLabel("Introduce el password del Forero");
        JTextField passUsuario = new JPasswordField();
        Object[] ob = {nickUsuarioLabel, nickUsuario, passUsuarioLabel, passUsuario};
        
        String sNickUsuario = nickUsuario.getText();
        String sPassUsuario = passUsuario.getText();
        boolean cancelar=false;
        while((sNickUsuario.trim().equalsIgnoreCase("") || sPassUsuario.trim().equalsIgnoreCase("")) && !cancelar ) {
        	int result = JOptionPane.showConfirmDialog(null, ob, "Cambio de usuario", JOptionPane.OK_CANCEL_OPTION);

        	if (result == JOptionPane.OK_OPTION) {
        		sNickUsuario = nickUsuario.getText();
        		sPassUsuario = passUsuario.getText();
        	}
        	else if(result == JOptionPane.CANCEL_OPTION) {
				cancelar=true;
			}
        	else {
        		sNickUsuario = "";
        		sPassUsuario = "";
        	}
        }
        if(!cancelar) {// una vez tenemos los datos miramos si es posible cambiar de usuario, si no error.
        	String mensajeError=foro.cambiarUsuarioActual(sNickUsuario, sPassUsuario);        	
        	lblUsuarioActual.setText("Usuario Actual: "+foro.getUsuarioActual().getNick() +" como "+foro.getUsuarioActual().getClass().getSimpleName());
        	btnNuevaCategoria.setEnabled(foro.getUsuarioActual() instanceof Administrador);
        	if(mensajeError.length()!=0) {
        		JOptionPane.showMessageDialog(null, mensajeError, "Error Cambio Usuario", JOptionPane.ERROR_MESSAGE);
        	}
        }
	}

}
