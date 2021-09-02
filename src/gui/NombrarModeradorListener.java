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
 * el evendo de cuando el administrador pulsa nombrar un moderador.
 */
public class NombrarModeradorListener implements ActionListener {
	
	private Foro foro;

	public NombrarModeradorListener(Foro foro) {
		this.foro=foro;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(foro.getUsuarioActual() instanceof Administrador) { //si eres un administrador entonces..
			JLabel label = new JLabel("Selecciona el Forero a convertir en Moderador");

			Vector<Forero> vAux=new Vector<>();
			for(Forero forero:foro.getForeros()) {//saco la lista de foreros que no son moderadores para que elija 1 para convertirlo.
				if(!(forero instanceof Moderador)) {
					vAux.add(forero);
				}
			}		

			JList<Forero> listaForeros = new JList<>(vAux); //creamos la parte grafica.
			JScrollPane scrollForeros = new JScrollPane(listaForeros);
			Object[] ob = {label, scrollForeros};

			int indiceForeroSeleccionado = listaForeros.getSelectedIndex();//varible del forero que vas a seleccionar.

			boolean cancelar=false;
			while(indiceForeroSeleccionado<0 && !cancelar ) {//mientras no hayas selecionado ninguno ni le des a cancelar a dar vueltas.
				int result = JOptionPane.showConfirmDialog(null, ob, "Nuevo Moderador", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) { //me quedo con el indice del forero seleccionado.
					indiceForeroSeleccionado = listaForeros.getSelectedIndex();        		
				}
				else if(result == JOptionPane.CANCEL_OPTION) {
					cancelar=true;
				}
				else {
					indiceForeroSeleccionado = -1;
				}
			}
			if(!cancelar && indiceForeroSeleccionado>=0) {   //si no he canceloado y he seleccionado uno     	
				Forero foreroAConvertir=vAux.get(indiceForeroSeleccionado);
				foro.nombrarModerador(foreroAConvertir);// lo convierto en moderador.
			}
		}
		else {			
			JOptionPane.showMessageDialog(null, "Solo un admninistrador puede nombrar Moderadores", "Error Crear Moderador", JOptionPane.ERROR_MESSAGE);        	
		}
	}

}
