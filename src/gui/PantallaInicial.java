package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import datosInternos.Administrador;
import datosInternos.Categoria;
import datosInternos.Forero;
import datosInternos.Foro;
import datosInternos.Hilo;
import datosInternos.Mensaje;

/**
 * @author Grupo 18
 * Aqui se crea la pantalla principal de la aplicacion(main).
 */
public class PantallaInicial {
// Jframe representa lo que es la pantalla(ventana) de la aplicación y foro es el objeto foro. Entonces esto es que se declara asi para pintar la pantalla
	private JFrame frame;
	private Foro foro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaInicial window = new PantallaInicial();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(); // nueva ventana
		frame.setBounds(100, 100, 450, 300); // tamaño de la ventana
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// que quieres hacer cuando le das a la x de cerrar ventana.(que se cierre)
		
		JFileChooser fileChooser = new JFileChooser(); // seleciona el fichero a cargar de datos del foro.
		
		int seleccion=fileChooser.showOpenDialog(frame);	// recoge el fiechero seleccionado	

		Forero usuarioActual=new Forero();
		boolean loginOk=true;
		switch (seleccion) { //el switch controla el aceptar o cancelar de la pantalla de seleccion de fichero.
		case JFileChooser.APPROVE_OPTION://aceptar			
			foro=leerFichero(fileChooser.getSelectedFile());
			if(foro== null) { 
				usuarioActual=crearForoPorDefecto();
			}
			else {
				loginOk=loginUsuario(foro);
				usuarioActual=foro.getUsuarioActual();
			}
			break;
		default: //en cualquiero otro caso creas el foro vacio.
			usuarioActual = crearForoPorDefecto();
	       
			break;
		}
		if(!loginOk) {
			System.exit(-1);
		}
		foro.setUsuarioActual(usuarioActual);// aqui nos dedicamos a dibujar todo lo que es la pantalla con opciones excepto los menus de arriba.	
		
		JLabel lblTematica = new JLabel("Tematica: "+foro.getTematica()); //arriba
		frame.getContentPane().add(lblTematica, BorderLayout.NORTH);
		
		JLabel lblUsuarioActual = new JLabel("Usuario Actual: "+foro.getUsuarioActual().getNick()+" como "+foro.getUsuarioActual().getClass().getSimpleName());
		frame.getContentPane().add(lblUsuarioActual, BorderLayout.SOUTH);//abajo
		
		JPanel panelCentral = new JPanel();
		frame.getContentPane().add(panelCentral, BorderLayout.CENTER);//parte central
		panelCentral.setLayout(new GridBagLayout()); //como se ordenan los componentes.
		GridBagConstraints restriccionesContenedor = new GridBagConstraints();

		
		Vector<Categoria> vAux=new Vector<>();
		for(Categoria categoria:foro.getCategorias()) {
			vAux.add(categoria);
		}		
		JList<Categoria> categorias = new JList<>(vAux);//ventana izquierda de categorias
		categorias.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		categorias.setLayoutOrientation(JList.VERTICAL);
		categorias.setToolTipText("Categorias");		
		JScrollPane scrollCategorias = new JScrollPane(categorias);// barras de scroll
		
		restriccionesContenedor.gridx=0; //primera celda 
		restriccionesContenedor.gridy=0;
		restriccionesContenedor.weightx=0.3;//tamaño relativo 30% 50%
		restriccionesContenedor.weighty=0.5;
		restriccionesContenedor.fill=GridBagConstraints.BOTH; // que el compomente que metas dentro ocupe todo.
		panelCentral.add(scrollCategorias,restriccionesContenedor);
		
		JList<Hilo> hilos = new JList<>();				//lo mismo pero con hilos.
		JScrollPane scrollHilos = new JScrollPane(hilos);
		restriccionesContenedor.gridx=1;
		restriccionesContenedor.gridy=0;
		hilos.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		hilos.setLayoutOrientation(JList.VERTICAL);
		hilos.setToolTipText("Hilos");
		panelCentral.add(scrollHilos,restriccionesContenedor);
		
		JList<Mensaje> mensajes = new JList<>(); // lo mismo para mensajes.
		JScrollPane scrollMensajes = new JScrollPane(mensajes);
		restriccionesContenedor.gridx=2;
		restriccionesContenedor.gridy=0;
		mensajes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		mensajes.setLayoutOrientation(JList.VERTICAL);
		mensajes.setToolTipText("Mensajes");
		panelCentral.add(scrollMensajes,restriccionesContenedor);
		

		JPanel panelMensajes = new JPanel(new BorderLayout());// panel de mensajes
		restriccionesContenedor.gridx=0;
		restriccionesContenedor.gridy=1;	
		restriccionesContenedor.gridwidth=GridBagConstraints.REMAINDER;//ocupame toda la fila.
		restriccionesContenedor.weighty=0.4;		//40% de la pantalla.
		panelCentral.add(panelMensajes, restriccionesContenedor);
		
		JLabel titulo=new JLabel("Titulo:");// etiqueta con el titulo del mensaje.
		panelMensajes.add(titulo,BorderLayout.NORTH);
		
		JTextArea mensaje=new JTextArea("");
		mensaje.setEditable(false); // el mensaje no se puede editar
		JScrollPane scrollMensaje = new JScrollPane(mensaje); // barras de scroll.
		panelMensajes.add(scrollMensaje,BorderLayout.CENTER);
		
		JLabel autor=new JLabel("Autor:"); //autor del mensaje.
		panelMensajes.add(autor,BorderLayout.SOUTH);
		
		
		JToolBar toolBarCategoria = new JToolBar();// barra de herramientas de abajo boton catergoria
		restriccionesContenedor.gridx=0;
		restriccionesContenedor.gridy=2;
		restriccionesContenedor.weightx=0.3;
		restriccionesContenedor.weighty=0.1;
		restriccionesContenedor.gridwidth=1;
		panelCentral.add(toolBarCategoria,restriccionesContenedor);
		
		
		JToolBar toolBarHilos= new JToolBar(); // igual con hilos
		restriccionesContenedor.gridx=1;
		restriccionesContenedor.gridy=2;
		panelCentral.add(toolBarHilos,restriccionesContenedor);
		
		JToolBar toolBarMensajes = new JToolBar(); // igual con mensajes
		restriccionesContenedor.gridx=2;
		restriccionesContenedor.gridy=2;
		panelCentral.add(toolBarMensajes,restriccionesContenedor);
		
		JButton btnNuevaCategoria = new JButton("Crear Categoria ...");		
		btnNuevaCategoria.setEnabled(usuarioActual instanceof Administrador);
		toolBarCategoria.add(btnNuevaCategoria);
		
		JButton btnNuevoHilo = new JButton("Crear Hilo ...");
		toolBarHilos.add(btnNuevoHilo);
		
		JButton btnPublicarMensaje = new JButton("Publicar Mensaje ...");
		toolBarMensajes.add(btnPublicarMensaje);
		

		categorias.addListSelectionListener(new CategoriasListener(hilos,foro));	// cuando pinches con el raton en la listaa de categorias llama a esta clase			
		categorias.addMouseListener(new MenuContextualCategorias(categorias,foro)); // cuando pase algo con el raton llama a esta clase.
		hilos.addListSelectionListener(new HilosListener(mensajes,categorias,foro));// mismo con hilos		
		hilos.addMouseListener(new MenuContextualHilos(categorias,hilos,foro));
		mensajes.addListSelectionListener(new MensajesListener(titulo,mensaje,autor,categorias,hilos,foro));//mismo con mensajes
		mensajes.addMouseListener(new MenuContextualMensajes(categorias,hilos,mensajes,foro));
		
		
		btnPublicarMensaje.addActionListener(new PublicarMensajeListener(categorias,hilos,mensajes,foro));//cuando pulsas los botones de abajo llama a esta clase.
		btnNuevoHilo.addActionListener(new NuevoHiloListener(categorias,hilos,foro));
		btnNuevaCategoria.addActionListener(new NuevaCategoriaListener(categorias,foro));
		
		
		//Creacion de los menus de la parte de arriba.

		JMenuBar menuBar; //Crear barras de menus deplegables de arriba.
		menuBar = new JMenuBar();

		JMenu menuAdministrador = new JMenu("Administrador"); //cada jmenu es un menu desplegable.	
		menuBar.add(menuAdministrador);
		JMenu mLModeradores=new JMenu("Lista de Moderadores"); //añades la sublista
		menuAdministrador.add(mLModeradores);
		JMenuItem mLModSO=new JMenuItem("Sin Ordenar ..."); // y la sublista de la sublista.
		mLModeradores.add(mLModSO);
		mLModSO.addActionListener(new ListadoModeradoresListener(foro,false));// action listener llama a la clase x cuando pinchas en el menu.
		JMenuItem mLModO=new JMenuItem("Ordenada por foreros sancionados ...");// todo se repite cambiando las clases llamadas.
		mLModeradores.add(mLModO);
		mLModO.addActionListener(new ListadoModeradoresListener(foro,true));		
		menuAdministrador.addSeparator();
		JMenu mLCategorias=new JMenu("Lista de Categorias");
		menuAdministrador.add(mLCategorias);
		JMenuItem mLCatONM=new JMenuItem("Ordenada por nº de mensajes ...");
		mLCategorias.add(mLCatONM);
		mLCatONM.addActionListener(new ListadoCategoriasListener(foro,true));
		JMenuItem mLCatONP=new JMenuItem("Ordenada por nº de palabras ...");
		mLCatONP.addActionListener(new ListadoCategoriasListener(foro,false));
		mLCategorias.add(mLCatONP);
		menuAdministrador.addSeparator();
		JMenuItem mLFor=new JMenuItem("Lista de Foreros ...");
		mLFor.addActionListener(new ListadoForerosListener(foro));
		menuAdministrador.add(mLFor);
		menuAdministrador.addSeparator();
		JMenuItem mGD=new JMenuItem("Guardar todos los datos ...");
		mGD.addActionListener(new GuardarDatosListener(foro));
		menuAdministrador.add(mGD);	

		JMenu menuModerador = new JMenu("Moderador");	
		menuBar.add(menuModerador);
		JMenuItem mSForero=new JMenuItem("Sancionar Forero...");
		menuModerador.add(mSForero);
		mSForero.addActionListener(new SancionarListener(foro));
		JMenuItem mLSForero=new JMenuItem("Levantar Sanción Forero...");
		menuModerador.add(mLSForero);
		mLSForero.addActionListener(new LevantarSancionListener(foro));		
		
		JMenu menuCambio = new JMenu("Gestión de usuarios");	
		menuBar.add(menuCambio);
		JMenuItem mCUsuario=new JMenuItem("Cambiar de usuario ...");
		menuCambio.add(mCUsuario);
		mCUsuario.addActionListener(new CambioUsuarioListener(foro,lblUsuarioActual,btnNuevaCategoria));
		menuCambio.addSeparator();
		JMenuItem mNMod=new JMenuItem("Nombrar moderador ...");
		mNMod.addActionListener(new NombrarModeradorListener(foro));
		menuCambio.add(mNMod);
		JMenuItem mDMod=new JMenuItem("Destituir moderador ...");
		mDMod.addActionListener(new DestituirModeradorListener(foro));
		menuCambio.add(mDMod);
		menuCambio.addSeparator();
		JMenuItem mCForero=new JMenuItem("Crear Forero ...");
		mCForero.addActionListener(new CrearForeroListener(foro));
		menuCambio.add(mCForero);

		frame.setJMenuBar(menuBar); //añade la barra y la pantalla ya esta creada.

		//Fin creacion menus
	}

	private Forero crearForoPorDefecto() { // por aqui se pasa si vamos a crear el foro de cero, hay que meter todos los datos.
		Forero usuarioActual;
		String tematica = JOptionPane.showInputDialog(frame,"Introduce una tematica para el foro","MiniForo",JOptionPane.QUESTION_MESSAGE);
		foro=new Foro(tematica);//creamos ventana que pide tematica.
		
		JLabel nickUsuarioLabel = new JLabel("Introduce el nick del usuario administrador");//creamos etiquetas
		JTextField nickUsuario = new JTextField();
		JLabel passUsuarioLabel = new JLabel("Introduce el password del usuario administrador");
		JTextField passUsuario = new JPasswordField();
		Object[] ob = {nickUsuarioLabel, nickUsuario, passUsuarioLabel, passUsuario};//pasamos las etiquetas al objet
		
		String sNickUsuario = nickUsuario.getText();
		String sPassUsuario = passUsuario.getText();
		while(sNickUsuario.trim().equalsIgnoreCase("") || sPassUsuario.trim().equalsIgnoreCase("")) {//damos vuetlas hasta que metes algo.
			int result = JOptionPane.showConfirmDialog(null, ob, "Usuario Administrador", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) { //cogo los datos
				sNickUsuario = nickUsuario.getText();
				sPassUsuario = passUsuario.getText();
			}
			else {
				sNickUsuario = ""; // lo pongo blanco.
				sPassUsuario = "";
			}
		}

		Administrador adm=new Administrador();//creamos administrador
		adm.setNick(sNickUsuario);
		adm.setContrasenia(sPassUsuario);

		foro.getForeros().add(adm);// añadimos administrador a lista de foreros.
		usuarioActual=adm;
		return usuarioActual; // devuelve usuario actual.
	}

	private boolean loginUsuario(Foro foro) {//Cuando has cargado el fichero y esta bien se llama a este metodo para meter nick y pasword.
		boolean ok=false;
		boolean cancelar=false;
		JLabel nickUsuarioLabel = new JLabel("Introduce el nick");//Se crean etiquetas y se mandan al objeto
		JTextField nickUsuario = new JTextField();
		JLabel passUsuarioLabel = new JLabel("Introduce el password");
		JTextField passUsuario = new JPasswordField();
		Object[] ob = {nickUsuarioLabel, nickUsuario, passUsuarioLabel, passUsuario};
		do {// doy vueltas hasta que me den un usuario correcto o cancelen.
			String sNickUsuario = nickUsuario.getText();
			String sPassUsuario = passUsuario.getText();
			cancelar=false;
			while((sNickUsuario.trim().equalsIgnoreCase("") || sPassUsuario.trim().equalsIgnoreCase("")) && !cancelar ) {
				int result = JOptionPane.showConfirmDialog(null, ob, "Login de usuario", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {
					sNickUsuario = nickUsuario.getText();
					sPassUsuario = passUsuario.getText();
				}
				else if(result == JOptionPane.CANCEL_OPTION) {
					cancelar=true;
				}
				else {
					sNickUsuario = "";
					sPassUsuario = "";
				}
			}
			if(!cancelar) {
				String mensajeError=foro.cambiarUsuarioActual(sNickUsuario, sPassUsuario);        	
				if(mensajeError.length()!=0) {
					JOptionPane.showMessageDialog(null, mensajeError, "Error Login de usuario", JOptionPane.ERROR_MESSAGE);
				}
				else {
					ok=true;
				}
			}
		}while(!ok && !cancelar);
		return ok;
	}

	private Foro leerFichero(File selectedFile) { //Leeo fichero del foro 
		try {
			FileInputStream fichero=new FileInputStream(selectedFile);
			ObjectInputStream in=new ObjectInputStream(fichero);
			Foro foro=(Foro)in.readObject();
			in.close();
			return foro;
		}catch (SecurityException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Cargar datos", JOptionPane.ERROR_MESSAGE);
			return null;
		}catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Cargar datos", JOptionPane.ERROR_MESSAGE);
			return null;
		}catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Cargar datos", JOptionPane.ERROR_MESSAGE);
			return null;
		}catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Cargar datos", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}


}
