/**
 * 
 */
package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import datosInternos.Categoria;
import datosInternos.Foro;
import datosInternos.Hilo;

/**
 * @author Grupo 18
 * Crea el menu contextual de hilos.
 */
public class MenuContextualHilos extends MouseAdapter {
	
	private JPopupMenu menuConHilo;
	private JMenuItem borraHilo;	



	public MenuContextualHilos(JList<Categoria> categorias,JList<Hilo> hilos, Foro foro) {
		menuConHilo=new JPopupMenu();//crea menu
		borraHilo=new JMenuItem("Borrar hilo seleccionado");
		borraHilo.addActionListener(new BorrarHiloListener(categorias,hilos,foro));//cuando pulses en la opcion de menu llamas a la clase borrar hilo 
		menuConHilo.add(borraHilo);				
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {	//cuando se produce el evento te muestra el menu.	
		if (e.isPopupTrigger()) { 
			menuConHilo.show(e.getComponent(),e.getX(), e.getY()); 
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) { // por lo del s.o.
		if (e.isPopupTrigger()) { 
			menuConHilo.show(e.getComponent(),e.getX(), e.getY());
		}
	}

}
