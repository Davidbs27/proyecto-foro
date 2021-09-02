package datosInternos;

import java.util.ArrayList;
import java.util.List;

/*
 * grupo 18
 *  Esta clase desciende forero y contiene los datos extras añadidos a un  forero normal.
 */
public class Moderador extends Forero implements Comparable<Moderador>
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -686757717415672619L;
	private int numeroSanciones;
	private List<String> forerosSancionados;

    /**
     *constructor por defecto inicializa las variables por defecto a 0. 
     */
    public Moderador()
    {
        super();
        this.numeroSanciones=0;
        forerosSancionados=new ArrayList<>();
    }
    
    /**
     * @param f
     * Este constructor llama al constructor de la clase madre forero para iniciazar sus campos y le añade los propios suyos.
     */
    public Moderador(Forero f) {
    	super(f.getNick(), f.getContrasenia(), f.isSancionado(), f.getVeces_sancionado());
    	numeroSanciones=0;
    	forerosSancionados=new ArrayList<>();
    }

    /**
     * @param f
     * Este metodo sanciona a un forero se incrementa el historial de sanciones y que actualmente sancionado.
     */
    public void  sancionar(Forero f)
    {
    	f.setSancionado(true);
    	f.setVeces_sancionado(f.getVeces_sancionado()+1);
    	numeroSanciones++;
    	forerosSancionados.add(f.getNick());
    }
    
    /**
     * @param f
     * desmarca  la sancion al forero que le pasan como parametros.
     */
    public void levantarSancion(Forero f)
    {
    	f.setSancionado(false);
    }
    
    /**
     * @param foro
     * @return
     * Devuelve la lista de foreros sancionados actualmente por este moderador.
     */
    public List<String> forerosSancionados(Foro foro)
    {
    	return forerosSancionados(false,foro);
    }
    
    /**
     * @param historico
     * @param foro
     * @return el historico de la lista de foreros sancionados por este moderador o los que estan actualmente sancionados(dependiendo de historico).
     * 
     *
     */
    public List<String> forerosSancionados(boolean historico,Foro foro)
    {
    	if(historico) {
    		return forerosSancionados;
    	}
    	else {
    		List<String> sancionados=new ArrayList<>();
    		Forero f=new Forero();
    		for (String nickForero : forerosSancionados) {
    			f.setNick(nickForero);
				if(foro.getForeros().contains(f)) {
					if(foro.getForeros().get(foro.getForeros().indexOf(f)).isSancionado()) {
						sancionados.add(nickForero);
					}
				}
			}
    		return sancionados;
    	}
    }
    
    

	/**
	 * @return the numeroSanciones
	 */
	public int getNumeroSanciones() {
		return numeroSanciones;
	}

	/**
	 * @param numeroSanciones the numeroSanciones to set
	 */
	public void setNumeroSanciones(int numeroSanciones) {
		this.numeroSanciones = numeroSanciones;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Moderador o) {
		return new Integer(this.getNumeroSanciones()).compareTo(new Integer(o.getNumeroSanciones()))*-1;
	}
    
    
}
