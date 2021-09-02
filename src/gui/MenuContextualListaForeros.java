/**
 * 
 */
package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import datosInternos.Forero;
import datosInternos.Foro;

/**
 * @author Grupo 18
 *cuando tienes la lista de foreros del administrador y te da las opciones de ver detalles de forero o exportar su mensajes a un fichero de texto.
 */
public class MenuContextualListaForeros extends MouseAdapter {
	
	private JPopupMenu menuConListaForero;
	private JMenuItem detalles;	
	private JMenuItem mensajesAFichero;



	public MenuContextualListaForeros(JList<Forero> foreros, Foro foro) {
		menuConListaForero=new JPopupMenu();
		detalles=new JMenuItem("Detalles ...");
		detalles.addActionListener(new DetallesForeroListener(foro,foreros));//llama a la opcion 1 cuando la pulsas
		mensajesAFichero=new JMenuItem("Exportar mensajes ...");
		mensajesAFichero.addActionListener(new ExportarMensajesListener(foro,foreros));//llama a la opcion 2 cuando la pulsas
		menuConListaForero.add(detalles);				
		menuConListaForero.add(mensajesAFichero);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {		//si has pulsado boton derecho te muestro el menu contextual
		if (e.isPopupTrigger()) { 
			menuConListaForero.show(e.getComponent(),e.getX(), e.getY()); 
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {		//si has pulsado el boton derecho te muestro el menu contextual.
		if (e.isPopupTrigger()) { 
			menuConListaForero.show(e.getComponent(),e.getX(), e.getY());
		}
	}

}
