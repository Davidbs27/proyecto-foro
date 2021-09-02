/**
 * 
 */
package datosInternos;

import java.io.Serializable;

/**
 * @author grupo 18
 * En cada categoria en la que escribe un forero acumla el numero de mensajes publicados en esa categoria por un forero.
 */
public class CategoriaForero implements Comparable<CategoriaForero>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7549776337040652864L;
	private Categoria categoria;
	private int mensajesPublicados;
	/**
	 * @param categoria
	 * @param mensajesPublicados
	 * inizializa campos de la clase con los parametros externos.
	 */
	public CategoriaForero(Categoria categoria, int mensajesPublicados) {
		super();
		this.categoria = categoria;
		this.mensajesPublicados = mensajesPublicados;
	}
	/**
	 *  inizializa los mensajes publicados a 0;
	 */
	public CategoriaForero() {
		super();
		categoria=null;
		mensajesPublicados=0;
		
	}
	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}
	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	/**
	 * @return the mensajesPublicados
	 */
	public int getMensajesPublicados() {
		return mensajesPublicados;
	}
	/**
	 * @param mensajesPublicados the mensajesPublicados to set
	 */
	public void setMensajesPublicados(int mensajesPublicados) {
		this.mensajesPublicados = mensajesPublicados;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(CategoriaForero o) {
		return new Integer(this.getMensajesPublicados()).compareTo(new Integer(o.getMensajesPublicados()));
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {		
		return this.categoria.equals(((CategoriaForero)obj).getCategoria());
	}
	
	
	
}
