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

/**
 * @author Grupo 18
 * administrador->lista de foreros
 */
public class ListadoForerosListener implements ActionListener {
	
	private Foro foro; 

	public ListadoForerosListener(Foro foro) {
		this.foro=foro;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(foro.getUsuarioActual() instanceof Administrador) {//si eres un administrador
			JLabel label = new JLabel("Listado de Foreros");

			Vector<Forero> vAux=new Vector<>();//copiamos los foreros a la pantalla.
			for(Forero forero:foro.getForeros()) {
				vAux.add(forero);
				
			}						

			JList<Forero> listaForeros = new JList<>(vAux);	//creamos la lista
			listaForeros.addMouseListener(new MenuContextualListaForeros(listaForeros,foro));//crea el menu contextual.
			JScrollPane scrollForeros = new JScrollPane(listaForeros);//scroll etc.
			Object[] ob = {label, scrollForeros};

			JOptionPane.showMessageDialog(null, ob, "Listado de Foreros", JOptionPane.INFORMATION_MESSAGE);	//muestra la lista  de fireros en pantalla.			

		}
		else {			
			JOptionPane.showMessageDialog(null, "Solo un Administrador puede ver esta opción", "Listado de Foreros", JOptionPane.ERROR_MESSAGE);        	
		}
	}

}
