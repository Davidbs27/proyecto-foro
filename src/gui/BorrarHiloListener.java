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
import datosInternos.Hilo;

/**
 * @author Grupo 18
 *Cuando pulsas con el raton en borrar hilo se llama aqui.
 */
public class BorrarHiloListener implements ActionListener {
	
	
	private JList<Categoria> categorias;
	private JList<Hilo> hilos;
	private Foro foro;

	public BorrarHiloListener(JList<Categoria> categorias, JList<Hilo> hilos, Foro foro) {

		this.categorias=categorias;
		this.hilos=hilos;
		this.foro=foro;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int indiceCategoriaSeleccionada=categorias.getSelectedIndex();
		if(indiceCategoriaSeleccionada>=0 && indiceCategoriaSeleccionada<foro.getCategorias().size()) {// si hay una categoria seleccionada
			int indiceHiloSeleccionado=hilos.getSelectedIndex();// cogo el hilo que este seleccionado en pantalla.
			if(indiceHiloSeleccionado>=0 && indiceHiloSeleccionado<foro.getCategorias().get(indiceCategoriaSeleccionada).getListaHilos().size()) {//si hay hilo seleccionado
				Categoria categoriaSeleccionada=foro.getCategorias().get(indiceCategoriaSeleccionada);
				Hilo hiloSeleccionado=categoriaSeleccionada.getListaHilos().get(indiceHiloSeleccionado);
				String mensajeError=foro.getUsuarioActual().eliminarHilo(categoriaSeleccionada, hiloSeleccionado);//elimina hilo pero si no puede retorna error.
				if(mensajeError.length()==0) {// si no hay errores actualiza la lista de hilos.
					Vector<Hilo> vAux=new Vector<>();
					for(Hilo hilo:categoriaSeleccionada.getListaHilos()) {
						vAux.add(hilo);
					}
					this.hilos.setListData(vAux);
				}
				else {
					JOptionPane.showMessageDialog(null, mensajeError, "Error Borrar Hilo", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "No hay hilo seleccionado", "Error Borrar Hilo", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "No hay categoria seleccionada", "Error Borrar Hilo", JOptionPane.ERROR_MESSAGE);
		}
	}

}
