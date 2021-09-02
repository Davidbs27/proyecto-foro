/**
 * 
 */
package gui;

import java.util.Vector;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import datosInternos.Categoria;
import datosInternos.Foro;
import datosInternos.Hilo;

/**
 * @author Grupo 18
 *Cuando cambia el valor selecionado de la lista de categorias se llama.
 */
public class CategoriasListener implements ListSelectionListener {
	
	private JList<Hilo> hilos;
	private Foro foro;

	/**
	 * @param hilos
	 * @param foro
	 */
	public CategoriasListener(JList<Hilo> hilos, Foro foro) {
		this.hilos=hilos;
		this.foro=foro;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {// si hemos selecccionado una categoria cargamos la lista de hilos de los hilos del foro en la ventana del al lado.
		JList<?> lCategorias = (JList<?>)e.getSource();
		int indiceCategoriaSeleccionada=lCategorias.getSelectedIndex();
		if(indiceCategoriaSeleccionada>=0) {
			Categoria categoriaSelecionada=this.foro.getCategorias().get(indiceCategoriaSeleccionada);
			Vector<Hilo> vAux=new Vector<>();
			for(Hilo hilo:categoriaSelecionada.getListaHilos()) {
				vAux.add(hilo);
			}
			this.hilos.setListData(vAux);
		}
		else {
			Vector<Hilo> vAux=new Vector<>();
			this.hilos.setListData(vAux);
		}
	}

}
