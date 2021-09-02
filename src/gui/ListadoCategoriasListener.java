/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import datosInternos.Administrador;
import datosInternos.Categoria;
import datosInternos.Foro;

/**
 * @author Grupo 18
 *administrador->lista de categorias
 */
public class ListadoCategoriasListener implements ActionListener {
	
	private Foro foro;
	private boolean ordenadoPorMensajes;

	public ListadoCategoriasListener(Foro foro, boolean ordenadoPorMensajes) {
		this.foro=foro;
		this.ordenadoPorMensajes=ordenadoPorMensajes;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(foro.getUsuarioActual() instanceof Administrador) {//si eres administrador.
			JLabel label = new JLabel("Listado de Categorias");


			List<Categoria> categorias;
			if(ordenadoPorMensajes) { //si he ordenador por mensajes lista de categorias.
				categorias=foro.listadoCategoriasPorMensajes();//Obtengo la lista
			}
			else {
				categorias=foro.listadoCategoriasPorPalabras();//si estoy ordenado por palabras obtengo la lista por palabras.
			}

			Vector<Categoria> vAux=new Vector<>();//copia a la lista de categorias a mi lista depantalla
			for(Categoria categoria:categorias) {
				vAux.add(categoria);
			}						

			JList<Categoria> listaCategorias = new JList<>(vAux);			
			JScrollPane scrollCategorias = new JScrollPane(listaCategorias);
			Object[] ob = {label, scrollCategorias};

			JOptionPane.showMessageDialog(null, ob, "Listado de Categortias", JOptionPane.INFORMATION_MESSAGE);//muestro la lista que he creado antes en pantalla.				

		}
		else {			
			JOptionPane.showMessageDialog(null, "Solo un Administrador puede ver esta opción", "Listado de Categorias", JOptionPane.ERROR_MESSAGE);        	
		}
	}

}
