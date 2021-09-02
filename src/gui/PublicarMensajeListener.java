/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import datosInternos.Categoria;
import datosInternos.Foro;
import datosInternos.Hilo;
import datosInternos.Mensaje;

/**
 * @author Grupo 18
 * Cuando pulsas el boton de publicar mensaje se llama a esta clase.
 */
public class PublicarMensajeListener implements ActionListener {
	
	private JList<Categoria> categorias;
	private JList<Hilo> hilos;
	private JList<Mensaje> mensajes;
	private Foro foro; 
	

	public PublicarMensajeListener(JList<Categoria> categorias, JList<Hilo> hilos, JList<Mensaje> mensajes, Foro foro) {
		this.categorias=categorias;
		this.hilos=hilos;
		this.mensajes=mensajes;
		this.foro=foro; 
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!foro.getUsuarioActual().isSancionado()) { //si el usuario actual no esta sancionado.
			JLabel tituloLabel = new JLabel("Introduce el titulo del mensaje"); //hacemos componentes de la pantalla.
			JTextField titulo = new JTextField();
			JLabel cuerpoLabel = new JLabel("Introduce el cuerpo del mensaje");
			JTextArea cuerpo = new JTextArea(10, 90);
			JScrollPane scrollCuerpo = new JScrollPane(cuerpo);
			Object[] ob = {tituloLabel, titulo, cuerpoLabel, scrollCuerpo};

			String sTitulo = titulo.getText(); // cogemos los campos.
			String sCuerpo = cuerpo.getText();
			boolean cancelar=false;
			while((sTitulo.trim().equalsIgnoreCase("") || sCuerpo.trim().equalsIgnoreCase("")) && !cancelar) {//Mientras que no canceles y no rellenes los datos nedesarios damos vueltas esperando que los entres.
				int result = JOptionPane.showConfirmDialog(null, ob, "Publicar Mensaje", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {//si pulsas o.k recogemos los datos de la pantalla.
					sTitulo = titulo.getText();
					sCuerpo = cuerpo.getText();        		
				}
				else if(result == JOptionPane.CANCEL_OPTION) {// si cancelanos vacios.
					cancelar=true;
				}
				else {
					sTitulo = "";
					sCuerpo = "";
				}
			}
			if (!cancelar) { //si no hemos cancelado
				int indiceCategoriaSeleccionada=categorias.getSelectedIndex();//cogemos la categoria seleccionada
				int indiceHiloSeleccionado=hilos.getSelectedIndex();//cogemos el hilo seleccionado
				if(indiceCategoriaSeleccionada<0 || indiceHiloSeleccionado<0) {//si no has seleccionado una categoria o un hilo error.
					JOptionPane.showMessageDialog(null, "No ha seleccionado o una categoria o un hilo", "Error Publicar Mensaje", JOptionPane.ERROR_MESSAGE);
				}
				else { //si has seleccionado alguno
					Categoria categoriaSelecionada=this.foro.getCategorias().get(indiceCategoriaSeleccionada);//cojo la catergoria
					Hilo hiloSeleccionado=categoriaSelecionada.getListaHilos().get(indiceHiloSeleccionado);//cojo el hilo
					foro.getUsuarioActual().publicarMensaje(categoriaSelecionada, hiloSeleccionado, sTitulo, sCuerpo);//publico el mensaje del usuario actual.
					Vector<Mensaje> vAux=new Vector<>();
					for(Mensaje mensaje:hiloSeleccionado.getListaMensajes()) {//actualizo la lista de mensajes  en la pantalla.
						vAux.add(mensaje);
					}
					this.mensajes.setListData(vAux);
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Usted esta sanciondado, no puede publicar", "Error Publicar Mensaje", JOptionPane.ERROR_MESSAGE);
		}
		

	}

}
