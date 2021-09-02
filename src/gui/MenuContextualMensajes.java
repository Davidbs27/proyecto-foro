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
import datosInternos.Mensaje;
import datosInternos.Moderador;

/**
 * @author Grupo 18
 *
 */
public class MenuContextualMensajes extends MouseAdapter {
	
	private JPopupMenu menuConMensj;
	private JMenuItem borraMensaje;	
	private Foro foro;



	public MenuContextualMensajes(JList<Categoria> categorias,JList<Hilo> hilos,JList<Mensaje> mensajes, Foro foro) {
		menuConMensj=new JPopupMenu();
		borraMensaje=new JMenuItem("Borrar mensaje seleccionado");
		borraMensaje.addActionListener(new BorrarMensajeListener(categorias,hilos,mensajes,foro));//si pulso llamo a la opcion 1 borrar mensaje en el menu contextual
		borraMensaje.setEnabled(foro.getUsuarioActual() instanceof Moderador);
		menuConMensj.add(borraMensaje);
		this.foro=foro;				
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		borraMensaje.setEnabled(foro.getUsuarioActual() instanceof Moderador);//si eres moderador activa el boton
		if (e.isPopupTrigger()) { //boton secudario del mouse te muestro el menu
			menuConMensj.show(e.getComponent(),e.getX(), e.getY()); 
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		borraMensaje.setEnabled(foro.getUsuarioActual() instanceof Moderador); //si eres moderador
		if (e.isPopupTrigger()) { //boton secundario del mouse te muestro el menu contextual.
			menuConMensj.show(e.getComponent(),e.getX(), e.getY());
		}
	}

}
