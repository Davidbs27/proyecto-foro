package datosInternos;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * @author Grupo 18
 * Esta clase agrupa todos los hilos dentro de una categoria.
 *
 */
public class Categoria implements Comparable<Categoria>,Serializable
{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 6950616413870712674L;
	private String nombre;
    private List <Hilo> listaHilos;

    /**
     * Constructor por defecto crea una nueva lista de hilos. 
     */
    public Categoria()
    {
        nombre="";
        listaHilos= new ArrayList <>();
    }

    /**
   	 * @param nombre
   	 * Crea la categoria con el nombre que se le pasa y la lista de hilos vacia.
   	 */
   	public Categoria(String nombre) {
   		super();
   		this.nombre = nombre;
   		this.listaHilos = new ArrayList <>();
   	}

  
    /**
	 * @param nombre
	 * @param listaHilos
	 * Carga los campos de categoria con lo que recibe por parametro.
	 */
	public Categoria(String nombre, List<Hilo> listaHilos) {
		super();
		this.nombre = nombre;
		this.listaHilos = listaHilos;
	}



	/**
	 * @param titulo
	 * @param mensajeInicial
	 * Se crea un hilo a partir del titulo y mensaje inicial recibiro por parametros y se añade a la lista de hilos.
	 */
	public void  crearHilo(String titulo,Mensaje mensajeInicial)
    {
		Hilo nuevoHilo=new Hilo(titulo, mensajeInicial);
		this.listaHilos.add(nuevoHilo);
    }
    
    /**
     * @param h
     * @param usuarioActual
     * @return cadena vacia si no ha habido errores, y en caso contrario el mensaje de error correspondiente.
     * Recibe un objeto de la clase hilo y de la clase foreroz, si el usuario es un moderador borras el hilo.si no si solo queda un mensaje 
     * y el forero es el autor tambien lo puedes borrar.En cualquier otro caso devuelve error.
     */
    public String eliminarHilo(Hilo h,Forero usuarioActual)
    {
    	if(usuarioActual instanceof Moderador) {
    		this.listaHilos.remove(h);
    		return "";
    	}
    	else {
    		if(h.getListaMensajes().size()>1) {
    			return "No se puede eliminar el hilo por que contine más de un mensaje.";
        	}
        	else {
        		if(h.getListaMensajes().size()==0) {
        			this.listaHilos.remove(h);
        			return "";
        		}
        		else {
        			if(h.getListaMensajes().get(0).getAutor().equalsIgnoreCase(usuarioActual.getNick())) {
        				this.listaHilos.remove(h);
        				return "";
        			}
        			else {
        				return "No puede eliminar el hilo por que no es suyo.";
        			}
        		}
        	}
    	}
    	
    }
    
    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Categoria c)
    {   
    	/*
    	 * Compara 2 catergorais una recibida por parametro y otra el objeto actual y devuelve
    	 * -1 si this<c ,0 si this=c,1 si this>c
    	 */
    	Integer numeroA= new Integer(this.getNumeroTotalMensajes());
    	Integer numeroB=new Integer(c.getNumeroTotalMensajes());
		return numeroA.compareTo(numeroB);
    }


	/**
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * @return listaHilos
	 */
	public List<Hilo> getListaHilos() {
		return listaHilos;
	}


	/**
	 * @param listaHilos
	 */
	public void setListaHilos(List<Hilo> listaHilos) {
		this.listaHilos = listaHilos;
	}
    
    /**
     * @return el numero total de mensajes de la categoria actual.
     */
    public int getNumeroTotalMensajes() {
    	int numMensajes=0;
    	for (Hilo hilo : listaHilos) {
			numMensajes=numMensajes+hilo.getListaMensajes().size();
		}
    	return numMensajes;
    }
    /**
     * @return devuelve el numero total de palabras de todos los mensajes de todos los hilos de la categoria actual.
     */
    public int getNumeroTotalPalabras() {
    	int numPalabras=0;
    	for (Hilo hilo : listaHilos) {
    		numPalabras=numPalabras+hilo.getNumeroPalabras();
		}
    	return numPalabras;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		/*
		 * Compara 2 categorias que seran igual si tienen el mismo nombre(distinge mayusculas y minusculas)
		 */
		return this.getNombre().equals(((Categoria)obj).getNombre());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		/*
		 * saca el campo nombre del objeto catergoria y lo devuelve.
		 */
		return nombre;
	}
    
    
}
