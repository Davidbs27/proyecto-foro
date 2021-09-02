package datosInternos;

import java.io.Serializable;

/**
 * @author Grupo 18
 *
 *  Esta clase contiene los campos de cada mensaje del foro.
 */
public class Mensaje implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4364306494152087386L;
	private String autor;
    private String titulo;
    private String cuerpo;
    private int numero_palabras;
    
    /**
     * Este es el constructor por defecto de los mensajes, inicializa los campos del mensaje a valores por defecto.
     */
    public Mensaje()
    {
        autor="";
        titulo="";
        cuerpo="";
        numero_palabras=0;
    }
    
    

	/**
	 * @param autor
	 * @param titulo
	 * @param cuerpo
	 * @param numero_palabras
	 * Este es otro constructor que inizializa los valores de los campos del mensaje segun lo que recibe por parametros.
	 */
	public Mensaje(String autor, String titulo, String cuerpo, int numero_palabras) {
		super();
		this.autor = autor;
		this.titulo = titulo;
		this.cuerpo = cuerpo;
		this.numero_palabras = numero_palabras;
	}
	
	/**
	 * @param autor
	 * @param titulo
	 * @param cuerpo
	 * Este constructor inizializa todos los campos y ademas cuenta el numero de palabras del mensaje. 
	 */
	public Mensaje(String autor, String titulo, String cuerpo) {
		super();
		this.autor = autor;
		this.titulo = titulo;
		this.cuerpo = cuerpo;
		this.numero_palabras = cuerpo.split("\\s").length;
	}



	/**
	 * @return the autor 
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * @param autor the autor to set
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the cuerpo
	 */
	public String getCuerpo() {
		return cuerpo;
	}

	/**
	 * @param cuerpo the cuerpo to set
	 */
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	/**
	 * @return the numero_palabras
	 */
	public int getNumero_palabras() {
		return numero_palabras;
	}

	/**
	 * @param numero_palabras the numero_palabras to set
	 */
	public void setNumero_palabras(int numero_palabras) {
		this.numero_palabras = numero_palabras;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return titulo;
	}

    
    
    
}
