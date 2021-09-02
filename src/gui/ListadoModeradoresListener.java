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
 * administrador ->lista de moderardores.
 */
public class ListadoModeradoresListener implements ActionListener {
	
	private Foro foro;
	private boolean ordenado;

	public ListadoModeradoresListener(Foro foro, boolean ordenado) {
		this.foro=foro;
		this.ordenado=ordenado;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(foro.getUsuarioActual() instanceof Administrador) {// si eres un administrador.
			JLabel label = new JLabel("Listado de Moderadores");

			Vector<Moderador> vAux=new Vector<>();
			for(Moderador moderador:foro.listadoModeradores(ordenado)) {//pediamos la lista de moderadores ordenada o no 
				vAux.add(moderador);//añadiendo a la lista de pantalla
			}						
			
			JList<Moderador> listaModeradores = new JList<>(vAux);//creamos el componente lista.
			listaModeradores.addMouseListener(new MenuContextualListaModeradores(listaModeradores,foro));//menu contextual
			JScrollPane scrollForeros = new JScrollPane(listaModeradores);//scrolll
			Object[] ob = {label, scrollForeros}; //se pasa a objeto

			JOptionPane.showMessageDialog(null, ob, "Listado de moderadores", JOptionPane.INFORMATION_MESSAGE);//mostramos la lista por pantalla.				
			
		}
		else {			
			JOptionPane.showMessageDialog(null, "Solo un Administrador puede ver esta opción", "Error Listado administradores", JOptionPane.ERROR_MESSAGE);        	
		}
	}

}
