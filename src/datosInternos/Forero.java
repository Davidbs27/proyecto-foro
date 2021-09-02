package datosInternos;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * Esta clase contiene los datos de cada forero.
 */
public class Forero implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 972264994037097001L;
	
    private String nick;
    private String contrasenia;
    private boolean sancionado;
    private int veces_sancionado;
    private int numeroMensajes;
    // Treemap Es una tabla con un campo clave y un campo valor asociado al campo clave. El treemap se autoordena por el campo clave cada vez lee secuencialmente
    // se usa para saber la catergoria mas frecuente, tienes en cada forero cada categoria y cuento el numero de mensajes en cada categoria, el treemap lo mantiene ordenado.
    private TreeMap<CategoriaForero,Integer> tablaCaterogiaFrecuente; 
    
    

    /**
     * Constructor por defecto inizializa los cmapos de la clase por defecto.
     */
    public Forero()
    {
        nick="";
        contrasenia="";
        sancionado=false;
        veces_sancionado=0;
        numeroMensajes=0;
        tablaCaterogiaFrecuente=new TreeMap<>();
        
    }

  
    
    /**
	 * @param nick
	 * @param contrasenia
	 * @param sancionado
	 * @param veces_sancionado
	 * Inizializa los campos de la clase forero con los valores recibidos.
	 */
	public Forero(String nick, String contrasenia, boolean sancionado, int veces_sancionado) {
		super();
		this.nick = nick;
		this.contrasenia = contrasenia;
		this.sancionado = sancionado;
		this.veces_sancionado = veces_sancionado;
		this.numeroMensajes=0;
		this.tablaCaterogiaFrecuente=new TreeMap<>();		
	}



	/**
	 * @param categoria
	 * @param tituloHilo
	 * @param tituloMensaje
	 * @param cuerpoMensaje
	 * Crea un hilo a partir de los datos de los campos que recibe por parametro y ademas incremente el acumulador de numero de mensajes.
	 */ 
	public void crearHilo(Categoria categoria,String tituloHilo,String tituloMensaje,String cuerpoMensaje)
    {
		Mensaje inicial=new Mensaje(this.getNick(), tituloMensaje, cuerpoMensaje);
		categoria.crearHilo(tituloHilo, inicial);
		this.numeroMensajes++;
		this.actualizarFrecuenciaCategoria(categoria);
    }
    
    /**
     * @param categoria
     * @param h
     * @return cadena vacia si no ha habido errores, y en caso contrario el mensaje de error correspondiente.
     *  Recibe un objeto de la clase categoria y el hilo que vas a eliminar 
     * y llama al metodo eliminar hilo de la clase categoria. 
     */
    public String eliminarHilo(Categoria categoria,Hilo h)
    {
    	return categoria.eliminarHilo(h,this);
    }
    
    /**
     * @param categoria
     * @param h
     * @param tituloMensaje
     * @param cuerpoMensaje
     * Si no esta sancionado publica un mensaje y ademas actualiza el numero de mensajes publicados(con el treemap)
     */
    public void publicarMensaje(Categoria categoria,Hilo h,String tituloMensaje,String cuerpoMensaje)
    {
    	if(!sancionado) {
    		h.crearMensaje(this, tituloMensaje, cuerpoMensaje);
    		this.numeroMensajes++;
    		this.actualizarFrecuenciaCategoria(categoria);
    	}
    }
    
    public int  numMensajes()
    {
    	return this.numeroMensajes;
    }
    
    /**
     * @return devuelve la categoria mas frecuente coge la variable tipo treemap y la pide la ultima(esta ordenado de menor a mayor)
     */
    public Categoria categoriaMasFrecuente()
    {
    	
    	if (tablaCaterogiaFrecuente.size()==0) //parque que arregla el fallo de la detalles de forero que no sale si no has publicado
    	{
    		return new Categoria("");
    	}
    	
    	return tablaCaterogiaFrecuente.lastKey().getCategoria();
    }



	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}



	/**
	 * @param nick the nick to set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}



	/**
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}



	/**
	 * @param contrasenia the contrasenia to set
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}



	/**
	 * @return the sancionado
	 */
	public boolean isSancionado() {
		return sancionado;
	}



	/**
	 * @param sancionado the sancionado to set
	 */
	public void setSancionado(boolean sancionado) {
		this.sancionado = sancionado;
	}



	/**
	 * @return the veces_sancionado
	 */
	public int getVeces_sancionado() {
		return veces_sancionado;
	}



	/**
	 * @param veces_sancionado the veces_sancionado to set
	 */
	public void setVeces_sancionado(int veces_sancionado) {
		this.veces_sancionado = veces_sancionado;
	}
    
    /**
     * @param categoria
     * Aqui para usar el la autoordenacion del treemap, lo que hacemos es mirar si ya hemos publicado en esa catergora, si yo ya he publicado un mensaje
     * entonces saco de el treemap mi numero de mensajes publicados en esa categoria, le sumo un 1 y vuelvo a meter, porque el treemap no añade 2 elementos iguales 
     * si no que los sobreescribe.Y si no existe se mete directamente.
     */
    public void actualizarFrecuenciaCategoria(Categoria categoria)
    {
    	CategoriaForero cf=new CategoriaForero(categoria, 1);
    	if (this.tablaCaterogiaFrecuente.containsKey(cf)) 
    	{
    		cf.setMensajesPublicados(this.tablaCaterogiaFrecuente.get(cf)+1);
    		this.tablaCaterogiaFrecuente.put(cf,cf.getMensajesPublicados());
    	}
    	else 
    	{
    		this.tablaCaterogiaFrecuente.put(cf, cf.getMensajesPublicados());
    	}
    }



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		//dos foreros son iguales si tienen el mismo nick pero no se distinguen entre mayusculas y minusculas.
		return this.getNick().equalsIgnoreCase(((Forero)obj).getNick());
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getNick();
	}
    
    
    
}
