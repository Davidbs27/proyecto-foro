/**
 * 
 */
package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import datosInternos.Foro;
import datosInternos.Moderador;

/**
 * @author Grupo 18
 * menu contextual de la lista de moderadores.
 */
public class MenuContextualListaModeradores extends MouseAdapter {
	
	private JPopupMenu menuConListaMod;
	private JMenuItem listaForerosSancionadosActuales;	
	private JMenuItem listaForerosSancionadosTotales;



	public MenuContextualListaModeradores(JList<Moderador> moderadores, Foro foro) {
		menuConListaMod=new JPopupMenu();
		listaForerosSancionadosActuales=new JMenuItem("Lista de Foreros Sancionados actualmente");
		listaForerosSancionadosActuales.addActionListener(new ListaForerosSancionadosListener(foro,true,moderadores)); //cuando pulsas vas a la opcion1
		listaForerosSancionadosTotales=new JMenuItem("Lista de Foreros Sancionados Totales");
		listaForerosSancionadosTotales.addActionListener(new ListaForerosSancionadosListener(foro,false,moderadores));//cuando pulsas vas a la opcion2
		menuConListaMod.add(listaForerosSancionadosActuales);				
		menuConListaMod.add(listaForerosSancionadosTotales);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {		//Cuando es el boton derecho te muestro el menu
		if (e.isPopupTrigger()) { 
			menuConListaMod.show(e.getComponent(),e.getX(), e.getY()); 
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {		//cuando es el boton derecho te muestro el menu.
		if (e.isPopupTrigger()) { 
			menuConListaMod.show(e.getComponent(),e.getX(), e.getY());
		} 
	}

}
