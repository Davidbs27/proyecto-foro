/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import datosInternos.Administrador;
import datosInternos.Forero;
import datosInternos.Foro;
import datosInternos.Hilo;

/**
 * @author Grupo 18
 * Cuando te ha sacado la lista de todos los foreros del foro y seleccionas a 1 +botos derecho +detalles llamas a esta clase
 */
public class DetallesForeroListener implements ActionListener {
	
	private Foro foro;
	private JList<Forero> foreros;

	public DetallesForeroListener(Foro foro, JList<Forero> foreros) {
		this.foreros=foreros;
		this.foro=foro;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(foro.getUsuarioActual() instanceof Administrador) {// si eres administrador 
			Forero foreroSeleccionado=foreros.getSelectedValue();// que has forero has seleccionado.
			if(foreroSeleccionado!=null) {//si has selecionado alguno 
				//hace la pantalla donde saca todos los datos del forero
				JLabel label = new JLabel("Detalle forero "+foreroSeleccionado.getNick());

				JLabel lNumMensajes = new JLabel("Nº mensajes publicados: "+foreroSeleccionado.numMensajes());
				List<Hilo> hilosCreados=foro.getHilos(foreroSeleccionado);
				Vector<Hilo> vAux=new Vector<>();
				for(Hilo hilo:hilosCreados) {
					vAux.add(hilo);
				}
				JList<Hilo> listaHilos = new JList<>(vAux);
				JLabel lHilosCreados = new JLabel("Hilos creados: ");
				JScrollPane scrollHilos = new JScrollPane(listaHilos);
				JLabel lNumSanciones = new JLabel("Nº veces sancionado: "+foreroSeleccionado.getVeces_sancionado());
				JLabel lSancionadoActual;
				if(foreroSeleccionado.isSancionado()) {
					lSancionadoActual = new JLabel("Actualmente Sancionado");
				}
				else {
					lSancionadoActual = new JLabel("Sin sanción activa");
				}
				JLabel lCatPubl = new JLabel("Categoria Favorita: "+foreroSeleccionado.categoriaMasFrecuente().getNombre());

				Object[] ob = {label, lNumMensajes,lHilosCreados,scrollHilos,lNumSanciones,lSancionadoActual,lCatPubl};

				JOptionPane.showMessageDialog(null, ob, "Detalle forero", JOptionPane.INFORMATION_MESSAGE);	//muestra los datos en la pantalla.			
			}
			else {
				JOptionPane.showMessageDialog(null, "No ha seleccionado ningún forero", "Detalle forero", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {			
			JOptionPane.showMessageDialog(null, "Solo un Administrador puede ver esta opción", "Detalle forero", JOptionPane.ERROR_MESSAGE);        	
		}
	}


	
	
}
