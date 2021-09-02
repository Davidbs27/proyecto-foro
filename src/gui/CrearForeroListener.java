/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import datosInternos.Foro;

/**
 * @author Grupo 18
 * Cuando pulas en crear forero llamas aqui(solo para administrado)
 */
public class CrearForeroListener implements ActionListener {
	
	private Foro foro;

	public CrearForeroListener(Foro foro) {
		this.foro=foro;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {// dame el nick del nuevo forero y el password.
		JLabel nickUsuarioLabel = new JLabel("Introduce el nick del nuevo Forero");
        JTextField nickUsuario = new JTextField();
        JLabel passUsuarioLabel = new JLabel("Introduce el password del nuevo Forero");
        JTextField passUsuario = new JPasswordField();
        Object[] ob = {nickUsuarioLabel, nickUsuario, passUsuarioLabel, passUsuario};
        
        String sNickUsuario = nickUsuario.getText();
        String sPassUsuario = passUsuario.getText();
        boolean cancelar=false;
        while((sNickUsuario.trim().equalsIgnoreCase("") || sPassUsuario.trim().equalsIgnoreCase("")) && !cancelar ) {// si me has metido algo en los campos
        	int result = JOptionPane.showConfirmDialog(null, ob, "Nuevo Forero", JOptionPane.OK_CANCEL_OPTION);

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
        if(!cancelar) {
        	String mensajeError=foro.crearForero(sNickUsuario, sPassUsuario);//si no hay error se crea forero.
        	if(mensajeError.length()!=0) {
        		JOptionPane.showMessageDialog(null, mensajeError, "Error Crear Forero", JOptionPane.ERROR_MESSAGE);
        	}
        }
	}

}
