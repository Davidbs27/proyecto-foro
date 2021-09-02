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

import datosInternos.Forero;
import datosInternos.Foro;
import datosInternos.Moderador;

/**
 * @author Grupo 18
 *Cuando le moderador le da a levantar sancion.
 */
public class LevantarSancionListener implements ActionListener {
	
	private Foro foro;

	public LevantarSancionListener(Foro foro) {
		this.foro=foro;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(foro.getUsuarioActual() instanceof Moderador) { // si eres un moderador.
			JLabel label = new JLabel("Selecciona el Forero al que levantar la sancion");

			Vector<Forero> vAux=new Vector<>();
			for(Forero forero:foro.getForeros()) { // busco los foreros
				if(!(forero instanceof Moderador) && forero.isSancionado()) {//si no eres moderador ni administrador y estas sancionado.
					vAux.add(forero); //hago la lista a mostrar por pantalla;
				}
			}		

			JList<Forero> listaForeros = new JList<>(vAux); //creo el panel
			JScrollPane scrollForeros = new JScrollPane(listaForeros);
			Object[] ob = {label, scrollForeros};

			int indiceForeroSeleccionado = listaForeros.getSelectedIndex();

			boolean cancelar=false;
			while(indiceForeroSeleccionado<0 && !cancelar ) {//muestra lista de candidatos  a levantar la sancion.
				int result = JOptionPane.showConfirmDialog(null, ob, "Levantar Sanción", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {
					indiceForeroSeleccionado = listaForeros.getSelectedIndex();        		
				}
				else if(result == JOptionPane.CANCEL_OPTION) {
					cancelar=true;
				}
				else {
					indiceForeroSeleccionado = -1;
				}
			}
			if(!cancelar && indiceForeroSeleccionado>=0) {        	
				Forero foreroDesancionar=vAux.get(indiceForeroSeleccionado);
				((Moderador)foro.getUsuarioActual()).levantarSancion(foreroDesancionar);//levanta la sancion.
			}
		}
		else {			
			JOptionPane.showMessageDialog(null, "Solo un Moderador puede levantar sanciones", "Error Levantar Sanción", JOptionPane.ERROR_MESSAGE);        	
		}
	}

}
