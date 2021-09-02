/**
 * 
 */
package gui;

import java.awt.event.MouseAdapter; // mouse adapter es una clase que implementa todas las interfaces del raton con metodos vacios.
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import datosInternos.Administrador;
import datosInternos.Categoria;
import datosInternos.Foro;

/**
 * @author Grupo 18
 *
 */
public class MenuContextualCategorias extends MouseAdapter { //la extendemos y sobreescribimos el metodo que nos interesa.

	private JPopupMenu menuConCat;
	private JMenuItem borraCategoria;	
	private Foro foro;



	public MenuContextualCategorias(JList<Categoria> categorias, Foro foro) {// aqui creas el menu contextual
		menuConCat=new JPopupMenu();
		borraCategoria=new JMenuItem("Borrar categoria seleccionada");
		borraCategoria.addActionListener(new BorrarCategoriaListener(categorias,foro));//cuando en la opcion del menu contextual llama a esta clase
		borraCategoria.setEnabled(foro.getUsuarioActual() instanceof Administrador); // activas el boton anterior solo si eres administrador
		menuConCat.add(borraCategoria);
		this.foro=foro;				
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// este metodo es el que se le llama cuando mantienes presionado uno de los botones del raton 
		borraCategoria.setEnabled(foro.getUsuarioActual() instanceof Administrador);// si el usuario actual es un administrador activas el boton si no no.
		if (e.isPopupTrigger()) { //si es el boton secundario.
			menuConCat.show(e.getComponent(),e.getX(), e.getY());//muestras el menu contextual. 
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		//lo mismo que el de arriba pero cuando lo sueltas.(dependiendo del s.o se llama uno o otro).
		borraCategoria.setEnabled(foro.getUsuarioActual() instanceof Administrador);
		if (e.isPopupTrigger()) { 
			menuConCat.show(e.getComponent(),e.getX(), e.getY());
		}
	}

}
