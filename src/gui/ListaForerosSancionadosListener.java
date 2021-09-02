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
import datosInternos.Foro;
import datosInternos.Moderador;

/**
 * @author Grupo 18
 *Lista de foreros sancionados actualmente menu contextual.
 */
public class ListaForerosSancionadosListener implements ActionListener {
	
	private Foro foro;
	private boolean actuales;
	private JList<Moderador> moderadores;

	public ListaForerosSancionadosListener(Foro foro, boolean actuales, JList<Moderador> moderadores) {
		this.foro=foro;
		this.actuales=actuales;
		this.moderadores=moderadores;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(foro.getUsuarioActual() instanceof Administrador) {// si eres un administrador
			JLabel label = new JLabel("Listado de Sancionados");

			Moderador moderador=moderadores.getSelectedValue();// moderador seleccionado
			if(moderador!=null) {
			Vector<String> vAux=new Vector<>();
			for(String forero:moderador.forerosSancionados(!actuales, foro)) { //recorre la lista de foreros sancionados la añade a la auxilar para mostrar la pantalla
				vAux.add(forero);
			}						
			
			JList<String> listaForeros = new JList<>(vAux);// dibujamos el cuadro de dialogo			
			JScrollPane scrollForeros = new JScrollPane(listaForeros);
			Object[] ob = {label, scrollForeros};

			JOptionPane.showMessageDialog(null, ob, "Listado de Foreros Sancionados", JOptionPane.INFORMATION_MESSAGE); //mostrar la lista en pantalla.				

			}
			else {
				JOptionPane.showMessageDialog(null, "No has seleccionado ningún moderador", "Listado de Foreros Sancionados", JOptionPane.ERROR_MESSAGE); 
			}
		}
		else {			
			JOptionPane.showMessageDialog(null, "Solo un Administrador puede ver esta opción", "Listado de Foreros Sancionados", JOptionPane.ERROR_MESSAGE);        	
		}
	}

}
