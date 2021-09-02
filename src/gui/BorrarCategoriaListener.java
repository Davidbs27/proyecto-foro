/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JOptionPane;

import datosInternos.Categoria;
import datosInternos.Foro;

/**
 * @author Grupo 18
 * Cuando pinchas en borrar categoria borras la categoria si eres administrador.
 */
public class BorrarCategoriaListener implements ActionListener {
	
	private JList<Categoria> categorias;
	private Foro foro;

	public BorrarCategoriaListener(JList<Categoria> categorias, Foro foro) {
		this.categorias=categorias;
		this.foro=foro;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int indiceCategoriaSeleccionada=categorias.getSelectedIndex();
		if(indiceCategoriaSeleccionada>=0 && indiceCategoriaSeleccionada<foro.getCategorias().size()) {// si tengo alguna categoria seleccionada.
			Categoria categoriaSeleccionada=foro.getCategorias().get(indiceCategoriaSeleccionada);// la recupero (de la lista que se muestra en pantalla)
			foro.eliminarCategoria(categoriaSeleccionada);//la elimino
			Vector<Categoria> vAux=new Vector<>();// actualizar la lista de categorias mostradas en pantalla.
			for(Categoria categoria:foro.getCategorias()) {
				vAux.add(categoria);
			}
			this.categorias.setListData(vAux);
		}
		else {
			JOptionPane.showMessageDialog(null, "No hay categoria seleccionada", "Error Borrar Categoria", JOptionPane.ERROR_MESSAGE);
		}

	}

}
