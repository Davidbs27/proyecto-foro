/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import datosInternos.Administrador;
import datosInternos.Forero;
import datosInternos.Foro;
import datosInternos.Mensaje;

/**
 * @author Grupo 18
 * Dado un forero exportar todos sus mensjaes a un fichero de texto plano
 */
public class ExportarMensajesListener implements ActionListener {

	private Foro foro;
	private JList<Forero> foreros;
	
	public ExportarMensajesListener(Foro foro, JList<Forero> foreros) {
		this.foreros=foreros;
		this.foro=foro;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(foro.getUsuarioActual() instanceof Administrador) {// si eres administrador
			Forero foreroSeleccionado=foreros.getSelectedValue();
			if(foreroSeleccionado!=null) {//si has seleccionado un forero.
				
				JFileChooser fileChooser = new JFileChooser(); //escoge fichero a grabar
				
				int seleccion=fileChooser.showSaveDialog(foreros);		
				
				String mensajeError="";
				switch (seleccion) {// si le has dado a aceptar grabas
				case JFileChooser.APPROVE_OPTION:
					List<Mensaje> listaMensajes=foro.getMensajes(foreroSeleccionado);//foro dame todos los mensajes del forero.
					mensajeError=volcarAFichero(listaMensajes,fileChooser.getSelectedFile());//llama al metodo vuelca todos los menasjes al fichero que has dicho.
					if(mensajeError.length()==0) {
						JLabel label = new JLabel("Mensajes exportados correctamente a "+fileChooser.getSelectedFile().getPath());
						Object[] ob = {label};
						JOptionPane.showMessageDialog(null, ob, "Exportar Mensajes", JOptionPane.INFORMATION_MESSAGE);// mensajes de exito
					}
					else {
						JOptionPane.showMessageDialog(null, mensajeError, "Exportar Mensajes", JOptionPane.ERROR_MESSAGE);//mensajes de error.
					}
					break;
				default:
					//no ha seleccionado nombre del fichero de salida
				}							
			}
			else {
				JOptionPane.showMessageDialog(null, "No ha seleccionado ningún forero", "Exportar Mensajes", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {			
			JOptionPane.showMessageDialog(null, "Solo un Administrador puede ver esta opción", "Exportar Mensajes", JOptionPane.ERROR_MESSAGE);        	
		}
	}

	private String volcarAFichero(List<Mensaje> listaMensajes, File selectedFile) {		//metodo se vuelca a fichero con lista de mensajes y fichero seleccoinado.
		try {
			FileWriter fichero=new FileWriter(selectedFile);
			PrintWriter out=new PrintWriter(fichero);
			for (Mensaje mensaje : listaMensajes) {// por cada mensaje en la lista de mensajes escribo los campos en el fichero.
				out.println(mensaje.getTitulo());
				out.println();
				out.println(mensaje.getCuerpo());
				out.println("-----------------------------------------");
			}
			out.close(); //cerrar fichero
			return ""; //mensaje o.k 
		}catch (SecurityException e) { //error 
			return e.getMessage();
		}catch (FileNotFoundException e) {//error
			return e.getMessage();
		}catch (IOException e) {//error
			return e.getMessage();
		}
	}
	
}
