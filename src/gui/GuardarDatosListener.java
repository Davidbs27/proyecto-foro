/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import datosInternos.Administrador;
import datosInternos.Foro;

/**
 * @author Grupo 18
 * opcion de menu guardar todos los datos del foro.
 */
public class GuardarDatosListener implements ActionListener {
	
	private Foro foro;

	public GuardarDatosListener(Foro foro) {
		this.foro=foro;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(foro.getUsuarioActual() instanceof Administrador) {	// si eres administrador		
				
				JFileChooser fileChooser = new JFileChooser(); //fichero  a grabar.
				
				int seleccion=fileChooser.showSaveDialog(null);		
				
				String mensajeError="";
				switch (seleccion) {// si es o.k pues lo guardo.
				case JFileChooser.APPROVE_OPTION:					
					mensajeError=volcarAFichero(foro,fileChooser.getSelectedFile());
					if(mensajeError.length()==0) {
						JLabel label = new JLabel("Datos guardados correctamente en "+fileChooser.getSelectedFile().getPath());
						Object[] ob = {label};
						JOptionPane.showMessageDialog(null, ob, "Guardar datos", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, mensajeError, "Guardar datos", JOptionPane.ERROR_MESSAGE);
					}
					break;
				default:
					//no ha seleccionado nombre del fichero de salida
				}										
		}
		else {			
			JOptionPane.showMessageDialog(null, "Solo un Administrador puede ver esta opción", "Guardar datos", JOptionPane.ERROR_MESSAGE);        	
		}
	}
	
	private String volcarAFichero(Foro foro,File selectedFile) {	//aqui se guarda.	
		try {
			FileOutputStream fichero=new FileOutputStream(selectedFile);//formato binario.
			ObjectOutputStream out=new ObjectOutputStream(fichero);//se graba.
			out.writeObject(foro);
			out.close();
			return "";
		}catch (SecurityException e) {//errores
			return e.getMessage();
		}catch (FileNotFoundException e) {
			return e.getMessage();
		}catch (IOException e) {
			return e.getMessage();
		}
	}

}
