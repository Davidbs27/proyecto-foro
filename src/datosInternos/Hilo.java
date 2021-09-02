package datosInternos;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * @author Grupo 18
 * Esta clase contiene los campos internos de cada hilo del foro  dentro de su categoria.
 */
public class Hilo implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2990508761653191814L;
	private String  titulo;
    private List<Mensaje> listaMensajes; 
    
    /**
     * Este constructor por defecto crea una lista de mensajes para cada hilo.
     */
    public Hilo()
    {
        titulo="";
        listaMensajes=new ArrayList<>();
        
    }
    
    /**
	 * @param titulo
	 * @param listaMensajes
	 * |Este constructor crea el hilo añadiendo el mensaje inicial. 
	 */
	public Hilo(String titulo, Mensaje mensajeInicial) {
		super();
		this.titulo = titulo;
		this.listaMensajes =new ArrayList<>();
		this.listaMensajes.add(mensajeInicial);
	}


    /**
	 * @param titulo
	 * @param listaMensajes
	 * Este constructor inicializa las variables internas con las pasadas por parametros.
	 */
	public Hilo(String titulo, List<Mensaje> listaMensajes) {
		super();
		this.titulo = titulo;
		this.listaMensajes = listaMensajes;
	}




	/**
	 * @param autor
	 * @param tituloMensaje
	 * @param cuerpoMensaje
	 * Este metodo genera un mensaje nuevo y luego lo añade a la lista de mensajes del hilo.
	 */
	public void  crearMensaje(Forero autor,String tituloMensaje,String cuerpoMensaje)
    {
        Mensaje m=new Mensaje(autor.getNick(),tituloMensaje,cuerpoMensaje);
        this.getListaMensajes().add(m);
    }
    
    /**
     * @param m
     * @param usuarioActual
     * Este metodo recibe un mensaje y si el usuario actual es un moderador entonces lo elimina de la lista.
     */
    public void eliminarMensaje (Mensaje m,Forero usuarioActual)
    {
    	if(usuarioActual instanceof Moderador) {
    		this.getListaMensajes().remove(m);
    	}
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
	 * @return the listaMensajes
	 */
	public List<Mensaje> getListaMensajes() {
		return listaMensajes;
	}


	/**
	 * @param listaMensajes the listaMensajes to set
	 */
	public void setListaMensajes(List<Mensaje> listaMensajes) {
		this.listaMensajes = listaMensajes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return titulo;
	}

	/**
	 * @return el numero de palabas total del hilo.
	 * 
	 */
	public int getNumeroPalabras() {
		int numPalabras=0;
		for (Mensaje mensaje : listaMensajes) {
			numPalabras=numPalabras+mensaje.getNumero_palabras();
		}
		return numPalabras;
	}
    
    
    
}
