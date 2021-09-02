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

/**
 * @author Grupo 18
 * cuando pulsas pulsas el boton de crear nuevo hilo
 */
public class NuevoHiloListener implements ActionListener {
	
	private JList<Categoria> categorias;
	private JList<Hilo> hilos;	
	private Foro foro; 

	public NuevoHiloListener(JList<Categoria> categorias, JList<Hilo> hilos, Foro foro) {
		this.categorias=categorias;
		this.hilos=hilos;
		this.foro=foro; 
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!foro.getUsuarioActual().isSancionado()) {//si no estas sancionado
			JLabel tituloHiloLabel = new JLabel("Introduce el titulo del hilo");//creo componentes de la pantalla.
			JTextField tituloHilo = new JTextField();
			JLabel tituloLabel = new JLabel("Introduce el titulo del mensaje");
			JTextField titulo = new JTextField();
			JLabel cuerpoLabel = new JLabel("Introduce el cuerpo del mensaje");
			JTextArea cuerpo = new JTextArea(10, 90);
			JScrollPane scrollCuerpo = new JScrollPane(cuerpo);
			Object[] ob = {tituloHiloLabel,tituloHilo,tituloLabel, titulo, cuerpoLabel, scrollCuerpo};

			String sTituloHilo = tituloHilo.getText();//inicializa las variables que vas a usar para luego recuperar los datos.
			String sTitulo = titulo.getText();
			String sCuerpo = cuerpo.getText();
			boolean cancelar=false;
			while((sTituloHilo.trim().equalsIgnoreCase("") || sTitulo.trim().equalsIgnoreCase("") || sCuerpo.trim().equalsIgnoreCase("")) && !cancelar) {//mientras que no rellenes los campos ni le des a cancelar
				int result = JOptionPane.showConfirmDialog(null, ob, "Crear Hilo", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {//si das a o.k recoges los datos de la pantalla.
					sTituloHilo = tituloHilo.getText();
					sTitulo = titulo.getText();
					sCuerpo = cuerpo.getText();        		
				}
				else if(result == JOptionPane.CANCEL_OPTION) {//si cancelas pones los datos vacios.
					cancelar=true;
				}
				else {
					sTituloHilo ="";
					sTitulo = "";
					sCuerpo = "";
				}
			}
			if (!cancelar) {// si no cancelas coges los datos que has metido y creas un nuebvo hilo.

				int indiceCategoriaSeleccionada=categorias.getSelectedIndex();
				if(indiceCategoriaSeleccionada<0) { //si no has seleccionado categoria damos error.
					JOptionPane.showMessageDialog(null, "No ha seleccionado una categoria ", "Error Crear Hilo", JOptionPane.ERROR_MESSAGE);
				}
				else {//si has seleccionado categoria.
					Categoria categoriaSelecionada=this.foro.getCategorias().get(indiceCategoriaSeleccionada);			
					foro.getUsuarioActual().crearHilo(categoriaSelecionada, sTituloHilo, sTitulo, sCuerpo);//crea un hilo en con esta categoria, este titulo, y este mensaje.
					Vector<Hilo> vAux=new Vector<>();
					for(Hilo hilo:categoriaSelecionada.getListaHilos()) { //actualiza la lista de hilos de la pantalla.
						vAux.add(hilo);
					}
					this.hilos.setListData(vAux);
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Usted esta sanciondado, no puede crear hilos", "Error Crear Hilo", JOptionPane.ERROR_MESSAGE);
		}
	}

}
