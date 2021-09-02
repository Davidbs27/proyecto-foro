package datosInternos;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Grupo 18
 * Esta clase representa el conjunto de categorias y usuarios del foro.
 */
public class Foro implements Serializable
{   
	private static final long serialVersionUID = 6797214101480278401L;
	private String tematica;
    private List<Categoria> categorias;
    private List<Forero> foreros;
    private Forero usuarioActual;
    
    /**
     * Constructor por defecto inizializa la lista de categorias y la lista de foreros.
     * 
     */
    public Foro()
    {        
        tematica="";
        categorias=new ArrayList<>();
        foreros=new ArrayList<>();
        usuarioActual=null;
    }
    
    

    /**
     * @param tematica
     * Este construcor inicializa la lista de categorias y foreros pero le añade la tematica externamente.
     */
    public Foro(String tematica) {
    	super();
    	this.tematica = tematica;
    	this.categorias=new ArrayList<>();
    	this.foreros=new ArrayList<>();
    	usuarioActual=null;
    }

    /**
     * @param tematica
     * @param categorias
     * @param foreros
     * Este constructor inizializa la lista de categorais y foreros recibidas  externamente.
     */
    public Foro(String tematica, List<Categoria> categorias, List<Forero> foreros) {
    	super();
    	this.tematica = tematica;
    	this.categorias = categorias;
    	this.foreros = foreros;
    	usuarioActual=null;
    }



	/**
	 * @param nombreCategoria
	 * Metodo que crea  una catergoria nueva si el usuario actual  es un administrador.
	 */
	public void crearCategoria(String nombreCategoria)
    {
		if(usuarioActual instanceof Administrador) {
			Categoria categoriaNueva=new Categoria(nombreCategoria);
			this.categorias.add(categoriaNueva);
		}
    }
    
    /**
     * @param c
     * Si el usuario es un administrador se elimina la categoria.
     */
    public void eliminarCategoria(Categoria c)
    {
    	if(usuarioActual instanceof Administrador) {
    		this.categorias.remove(c);
    	}
    }
    
    /**
     * @param f
     * Si el usuario es un administrador permite crear un moderador a partir de un forero.
     */
    public void nombrarModerador(Forero f)
    {
    	if(usuarioActual instanceof Administrador) {
    		this.foreros.remove(f);
    		this.foreros.add(new Moderador(f));
    	}
    }
    
    /**
     * @param f
     * Si Eres un administrador puedes destituir un moderador convirtiendolo en un forero normal.
     */
    public void destituirModerador(Forero f)
    {
    	if(usuarioActual instanceof Administrador) {
    		this.foreros.remove(f);
    		this.foreros.add(new Forero(f.getNick(),f.getContrasenia(),f.isSancionado(),f.getVeces_sancionado()));
    	}
    }
    
    /**
     * @return Lista de moderadores
     * Recorre toda la lista de objeros de foreros, cuando encuentra un moderador lo añade a otra lista que luego devuelve al final.
     * 
     */
    public List<Moderador> listadoModeradores()
    {
    	List<Moderador> moderadores=new ArrayList<>();
    	for (Forero forero : foreros) {
			if(forero instanceof Moderador) {
				moderadores.add((Moderador)forero);
			}
		}
    	return moderadores;
    }
    
    /**
     * @param ordenada
     * @return lista de moderadores ordenada por el numero de sanciones.
     * Si me piden que la ordene la ordenado y si no pues paso.
     */
    public List<Moderador> listadoModeradores(boolean ordenada)
    {
    	List<Moderador> moderadores=listadoModeradores();
    	if(ordenada) {
    		Collections.sort(moderadores);
    	}
    	return moderadores;
    }
    
    /**
     * @return lista categorias.
     */
    public List<Categoria> listadoCategorias()
    {
    	return categorias;
    }
    
    /**
     * @return  lista de categorias ordenadas por mensajes.
     * llama el metodo listado categorias creando un objeto de la clase comparador por mensajes 
     */
    public List<Categoria> listadoCategoriasPorMensajes(){    	
    	return listadoCatergorias(new ComparadorCategoriaMensajes());
    }
    
    /**
     * @return lista de categorias ordenadas por palabras.
     * llama el metodo listado categorias creando un objeto de la clase comparador por palabras.
     */
    public List<Categoria> listadoCategoriasPorPalabras(){
    	return listadoCatergorias(new ComparadorCategoriaPalabras());
    }
    
    /**
     * @param c
     * @return la lista ordenada.
     * El objeto que recive se usa unicamente para poder usar el metodo compare
     * del objeto comparadorcatergoriapalabras dentro de la coleccion .sort(copia,c).
     */
    public List<Categoria> listadoCatergorias(Comparator<Categoria> c)
    {
    	List<Categoria> copia=new ArrayList<>(categorias);
    	Collections.sort(copia, c);
    	return copia;
    }

    /**
     * @author Grupo 18
     * Compara 2 categorias mediante el numero de mensajes (invertida multiplicando por  -1)
     */
    public class ComparadorCategoriaMensajes implements Comparator<Categoria>{

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Categoria cat1, Categoria cat2) {
			return Integer.compare(cat1.getNumeroTotalMensajes(), cat2.getNumeroTotalMensajes())*-1;
		}
		
	}
	
	/**
	 * @author Grupo 18
	 *Compara 2 categorias mediante el numero de palabras (invertida multiplicando por  -1)
	 */
	public class ComparadorCategoriaPalabras implements Comparator<Categoria>{

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Categoria cat1, Categoria cat2) {
			return Integer.compare(cat1.getNumeroTotalPalabras(), cat2.getNumeroTotalPalabras())*-1;
		}
		
	}
    
    
    
    
	/**
	 * @return the tematica
	 */
	public String getTematica() {
		return tematica;
	}

	/**
	 * @param tematica the tematica to set
	 */
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}

	/**
	 * @return the categorias
	 */
	public List<Categoria> getCategorias() {
		return categorias;
	}

	/**
	 * @param categorias the categorias to set
	 */
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	/**
	 * @return the foreros
	 */
	public List<Forero> getForeros() {
		return foreros;
	}

	/**
	 * @param foreros the foreros to set
	 */
	public void setForeros(List<Forero> foreros) {
		this.foreros = foreros;
	}



	/**
	 * @return the usuarioActual
	 * El sincronizado esta puesto para proteger del acceso concurrente (segun eclipse)
	 */
	public synchronized Forero getUsuarioActual() {
		return usuarioActual;
	}


	/**
	 * @param usuarioActual the usuarioActual to set
	 */
	public synchronized void setUsuarioActual(Forero usuarioActual) {
		this.usuarioActual = usuarioActual;
	}
    
    /**
     * @param nick
     * @param password
     * @return cadena vacia si no hay errores y en caso contrario el error que se produce.
     */
    public String crearForero(String nick, String password) {
    	if(usuarioActual instanceof Administrador) {
    		Forero nuevoForero=new Forero();
    		nuevoForero.setNick(nick);
    		nuevoForero.setContrasenia(password);
    		if(this.foreros.contains(nuevoForero)) {
    			return "Ya existe un forero con ese nick";
    		}
    		else {
    			this.foreros.add(nuevoForero);
    			return "";
    		}
    	}
    	else {
    		return "Solo el administrador puede crear nuevos foreros";
    	}
    }



	/**
	 * @param sNickUsuario
	 * @param sPassUsuario
	 * @return cadena vacia si no hay errores y el error que se produce en caso contrario.
	 */
	public String cambiarUsuarioActual(String sNickUsuario, String sPassUsuario) {
		Forero nuevoForero=new Forero();
		nuevoForero.setNick(sNickUsuario);
		nuevoForero.setContrasenia(sPassUsuario);
		if(this.getForeros().contains(nuevoForero)) { //el contains usa el equals de objeto forero para buscar dentro de la lista
			Forero foreroGuardado=this.getForeros().get(this.getForeros().indexOf(nuevoForero));
			if(foreroGuardado.getContrasenia().equals(nuevoForero.getContrasenia())) {
				this.setUsuarioActual(foreroGuardado);
				return "";
			}
			else {
				return "Password erronea";
			}
		}
		else {
			return "No existe el usuario";
		}
	}
	
	

	/**
	 * @param autor
	 * @return la lista de hilos creados por el forero pasado como parametro.
	 * 
	 */
	public List<Hilo> getHilos(Forero autor) {
		List<Hilo> hilos=new ArrayList<>();
		for (Categoria categoria : categorias) {
			for(Hilo hilo:categoria.getListaHilos()) {
				if(hilo.getListaMensajes().size()>0) {
					if(hilo.getListaMensajes().get(0).getAutor().equalsIgnoreCase(autor.getNick())) {
						hilos.add(hilo);
					}
				}
			}
		}
		return hilos;
	}



	/**
	 * @param autor
	 * @return la lista de mensajes creados por el forero pasado como parametro.
	 * 
	 */
	public List<Mensaje> getMensajes(Forero autor) {
		List<Mensaje> mensajes=new ArrayList<>();
		for (Categoria categoria : categorias) {
			for(Hilo hilo:categoria.getListaHilos()) {
				for(Mensaje mensaje:hilo.getListaMensajes()) {
					if(mensaje.getAutor().equalsIgnoreCase(autor.getNick())) {
						mensajes.add(mensaje);
					}
				}
			}
		}
		return mensajes;
	}
    
}
