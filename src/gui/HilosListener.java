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
import datosInternos.Mensaje;

/**
 * @author Grupo 18
 *Cuando pinchas en un hilo que actualize la lista de mensajes.
 */
public class HilosListener implements ListSelectionListener {
	
	private JList<Mensaje> mensajes;
	private JList<Categoria> categorias;
	private Foro foro;

	public HilosListener(JList<Mensaje> mensajes, JList<Categoria> categorias, Foro foro) {
		this.mensajes=mensajes;
		this.categorias=categorias;
		this.foro=foro;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int indiceCategoriaSeleccionada=categorias.getSelectedIndex();//si has selecionado una categoria
		JList<?> lHilos = (JList<?>)e.getSource();
		int indiceHiloSeleccionado=lHilos.getSelectedIndex();//y has seleccionado un hilo
		if(indiceCategoriaSeleccionada>=0 && indiceHiloSeleccionado>=0) {
			Categoria categoriaSelecionada=this.foro.getCategorias().get(indiceCategoriaSeleccionada);//coges la categoria del hilo
			Hilo hiloSeleccionado=categoriaSelecionada.getListaHilos().get(indiceHiloSeleccionado);		
			Vector<Mensaje> vAux=new Vector<>();//actualizas lista de mensajes.
			for(Mensaje mensaje:hiloSeleccionado.getListaMensajes()) {
				vAux.add(mensaje);
			}
			this.mensajes.setListData(vAux);
		}
		else {
			Vector<Mensaje> vAux=new Vector<>();
			this.mensajes.setListData(vAux);
		}
	}

}
