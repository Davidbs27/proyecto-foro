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
import datosInternos.Mensaje;

/**
 * @author Grupo 18
 * Si le damos borrar mensaje llamamos a esta clase.
 */
public class BorrarMensajeListener implements ActionListener {
	
	private JList<Categoria> categorias;
	private JList<Hilo> hilos;
	private JList<Mensaje> mensajes;
	private Foro foro;
	

	public BorrarMensajeListener(JList<Categoria> categorias, JList<Hilo> hilos, JList<Mensaje> mensajes, Foro foro) {
		this.categorias=categorias;
		this.hilos=hilos;
		this.mensajes=mensajes;
		this.foro=foro;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {// si has seleccionado una categoria,un hilo, y un mensaje lo itenta borrar.Lo recupera de la lista de pantalla para buscarlo en foro y lo eliminas si puedes en foro y se actualiza.
		int indiceCategoriaSeleccionada=categorias.getSelectedIndex();
		if(indiceCategoriaSeleccionada>=0 && indiceCategoriaSeleccionada<foro.getCategorias().size()) {
			int indiceHiloSeleccionado=hilos.getSelectedIndex();
			if(indiceHiloSeleccionado>=0 && indiceHiloSeleccionado<foro.getCategorias().get(indiceHiloSeleccionado).getListaHilos().size()) {
				int indiceMensajeSeleccionado=mensajes.getSelectedIndex();
				if(indiceMensajeSeleccionado>=0 && indiceMensajeSeleccionado<foro.getCategorias().get(indiceCategoriaSeleccionada).getListaHilos().get(indiceHiloSeleccionado).getListaMensajes().size()) {
					Categoria categoriaSeleccionada=foro.getCategorias().get(indiceCategoriaSeleccionada);
					Hilo hiloSeleccionado=categoriaSeleccionada.getListaHilos().get(indiceHiloSeleccionado);
					Mensaje mensajeSeleccionado=hiloSeleccionado.getListaMensajes().get(indiceMensajeSeleccionado);
					hiloSeleccionado.eliminarMensaje(mensajeSeleccionado,foro.getUsuarioActual());
					Vector<Mensaje> vAux=new Vector<>();
					for(Mensaje mensaje:hiloSeleccionado.getListaMensajes()) {
						vAux.add(mensaje);
					}
					this.mensajes.setListData(vAux);
				}
				else {
					JOptionPane.showMessageDialog(null, "No hay mensaje seleccionado", "Error Borrar Mensaje", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "No hay hilo seleccionado", "Error Borrar Mensaje", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "No hay categoria seleccionada", "Error Borrar Mensaje", JOptionPane.ERROR_MESSAGE);
		}
	}

}
