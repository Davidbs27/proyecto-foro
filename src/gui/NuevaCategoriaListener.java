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
import javax.swing.JTextField;

import datosInternos.Administrador;
import datosInternos.Categoria;
import datosInternos.Foro;

/**
 * @author Grupo 18
 *Cuando pinchas en el boton de nueva categoria.
 */
public class NuevaCategoriaListener implements ActionListener {
	
	private JList<Categoria> categorias;
	private Foro foro; 

	public NuevaCategoriaListener(JList<Categoria> categorias, Foro foro) {
		this.categorias=categorias;
		this.foro=foro; 
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(foro.getUsuarioActual() instanceof Administrador) {	//si eres un administrador entonces....		
			JLabel tituloLabel = new JLabel("Introduce el titulo de la categoria");//creo graficos
			JTextField titulo = new JTextField();			
			Object[] ob = {tituloLabel, titulo};

			String sTitulo = titulo.getText();
			boolean cancelar=false;
			while( sTitulo.trim().equalsIgnoreCase("") && !cancelar) {// mientras no meta el titulo de la categoira y no pulses cancelar te lo sigo mostranto.
				int result = JOptionPane.showConfirmDialog(null, ob, "Crear Categoria", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {				//si le das a o.k cojo el titulo.
					sTitulo = titulo.getText();     		
				}
				else if(result == JOptionPane.CANCEL_OPTION) {
					cancelar=true;
				}
				else {					
					sTitulo = "";					
				}
			}


			foro.crearCategoria(sTitulo);//creas la categoria con el titulo

			Vector<Categoria> vAux=new Vector<>();
			for(Categoria categoria:foro.getCategorias()) { //actualizar la lista de categorias visibles en pantalla.
				vAux.add(categoria);
			}
			this.categorias.setListData(vAux);//muestra la lista de pantalla.l

		}
		else {
			JOptionPane.showMessageDialog(null, "Usted no es administrador, no puede crear categorias", "Error Crear Categoria", JOptionPane.ERROR_MESSAGE);
		}
	}

}
