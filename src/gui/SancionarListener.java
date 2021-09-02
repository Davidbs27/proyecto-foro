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
 *Cuando pulsas en el menu de moderador sancionar a un forero.
 */
public class SancionarListener implements ActionListener {
	
	private Foro foro;

	public SancionarListener(Foro foro) {
		this.foro=foro;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(foro.getUsuarioActual() instanceof Moderador) { //cuando eres un moderador 
			JLabel label = new JLabel("Selecciona el Forero al que sancionar");

			Vector<Forero> vAux=new Vector<>();
			for(Forero forero:foro.getForeros()) {//busco la lista de foreros que no estan sancionados
				if(!(forero instanceof Moderador) && !forero.isSancionado()) {//si no eres un moderador y no estas sancionado
					vAux.add(forero);//te añado a la lista
				}
			}		

			JList<Forero> listaForeros = new JList<>(vAux);//creo la pantalla donte te muestra la lista 
			JScrollPane scrollForeros = new JScrollPane(listaForeros);
			Object[] ob = {label, scrollForeros};

			int indiceForeroSeleccionado = listaForeros.getSelectedIndex();//inizaolica la variable de la lista que has seleccionado de foreros.

			boolean cancelar=false;
			while(indiceForeroSeleccionado<0 && !cancelar ) { //mientras no selecciiones ninguno ni le des a cancelar das vueltas hasta que pase algo. 
				int result = JOptionPane.showConfirmDialog(null, ob, "Sancionar", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) { //si pulso ok entonces
					indiceForeroSeleccionado = listaForeros.getSelectedIndex();//cojo el indicie de la lista de foreros sleecccionada.        		
				}
				else if(result == JOptionPane.CANCEL_OPTION) { //si cancelo pues nada
					cancelar=true;
				}
				else {
					indiceForeroSeleccionado = -1;
				}
			}
			if(!cancelar && indiceForeroSeleccionado>=0) {        	//si no he cancelado y he seleccionado alguno.
				Forero foreroASancionar=vAux.get(indiceForeroSeleccionado);
				
				((Moderador)foro.getUsuarioActual()).sancionar(foreroASancionar);//sancionado.
			}
		}
		else {			
			JOptionPane.showMessageDialog(null, "Solo un Moderador puede Sancionar", "Error Sancionar", JOptionPane.ERROR_MESSAGE);        	
		}
	}

}
