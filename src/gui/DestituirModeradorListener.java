/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import datosInternos.Administrador;
import datosInternos.Forero;
import datosInternos.Foro;
import datosInternos.Moderador;

/**
 * @author Grupo 18
 *Cuando el administrador pulsa destituir moderador.
 */
public class DestituirModeradorListener implements ActionListener {
	
	private Foro foro;

	public DestituirModeradorListener(Foro foro) {
		this.foro=foro;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(foro.getUsuarioActual() instanceof Administrador) {// si eres administrador
			JLabel label = new JLabel("Selecciona el Moderador a destituir");

			Vector<Forero> vAux=new Vector<>();
			for(Forero forero:foro.getForeros()) {// te saco la lista de moderadores actuales.
				if(forero instanceof Moderador && !(forero instanceof Administrador)) {
					vAux.add(forero);
				}
			}		

			JList<Forero> listaForeros = new JList<>(vAux); //muestra la lista
			JScrollPane scrollForeros = new JScrollPane(listaForeros);
			Object[] ob = {label, scrollForeros};

			int indiceForeroSeleccionado = listaForeros.getSelectedIndex();

			boolean cancelar=false;
			while(indiceForeroSeleccionado<0 && !cancelar ) {
				int result = JOptionPane.showConfirmDialog(null, ob, "Destituir Moderador", JOptionPane.OK_CANCEL_OPTION);//muestra lista de moderadores

				if (result == JOptionPane.OK_OPTION) {
					indiceForeroSeleccionado = listaForeros.getSelectedIndex();// si he seleccionado alguno		
				}
				else if(result == JOptionPane.CANCEL_OPTION) {
					cancelar=true;
				}
				else {
					indiceForeroSeleccionado = -1;
				}
			}
			if(!cancelar && indiceForeroSeleccionado>=0) {        	
				Forero foreroAConvertir=vAux.get(indiceForeroSeleccionado);// si no he cancelado extraigo el moderador seleccionado.
				foro.destituirModerador(foreroAConvertir); // a la calle.(dejarlo como forero)
			}
		}
		else {			
			JOptionPane.showMessageDialog(null, "Solo un admninistrador puede destituir Moderadores", "Error destituir Moderador", JOptionPane.ERROR_MESSAGE);        	
		}
	}

}
