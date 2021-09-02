/**
 * 
 */
package gui;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import datosInternos.Categoria;
import datosInternos.Foro;
import datosInternos.Hilo;
import datosInternos.Mensaje;

/**
 * @author Grupo 18
 *actualiza la lista de mensajes cuando pinchas en ella cuando se modifica la lista
 */
public class MensajesListener implements ListSelectionListener {
	
	private JLabel titulo;
	private JTextArea cuerpoMensaje;
	private JLabel autor;
	private JList<Categoria> categorias;
	private JList<Hilo> hilos;
	private Foro foro;

	public MensajesListener(JLabel titulo, JTextArea mensaje, JLabel autor, JList<Categoria> categorias,JList<Hilo> hilos, Foro foro) {
		this.titulo=titulo;
		this.cuerpoMensaje=mensaje;
		this.autor=autor;
		this.categorias=categorias;
		this.hilos=hilos;
		this.foro=foro;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int indiceCategoriaSeleccionada=categorias.getSelectedIndex();
		int indiceHiloSeleccionado=hilos.getSelectedIndex();		
		JList<?> lMensajes = (JList<?>)e.getSource();	
		int indiceMensajeSeleccionado=lMensajes.getSelectedIndex();
		if(indiceCategoriaSeleccionada>=0 && indiceHiloSeleccionado>=0 && indiceMensajeSeleccionado>=0) {//si has selecionado categoria/hilo/mensaje
			Categoria categoriaSelecionada=this.foro.getCategorias().get(indiceCategoriaSeleccionada);//recuperamos la categoria
			Hilo hiloSeleccionado=categoriaSelecionada.getListaHilos().get(indiceHiloSeleccionado);//recuperamos el hilo
			Mensaje mensajeSeleccionado=hiloSeleccionado.getListaMensajes().get(indiceMensajeSeleccionado);// recuperamos el mensaje
			this.titulo.setText("Titulo: "+mensajeSeleccionado.getTitulo());		//sacamos el titulo
			this.autor.setText("Autor: "+mensajeSeleccionado.getAutor());		    //sacamos el autor
			this.cuerpoMensaje.setText(mensajeSeleccionado.getCuerpo());            //sacamos el cueropo.
		}
		else {
			this.titulo.setText("Titulo: ");		//si no lo deja vacio.
			this.autor.setText("Autor: ");		
			this.cuerpoMensaje.setText("");
		}
	}

}
