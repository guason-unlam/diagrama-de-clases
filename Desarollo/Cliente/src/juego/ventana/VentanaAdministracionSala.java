package juego.ventana;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import cliente.Musica;
import graphics.Game;
import graphics.GameWindow;
import juego.Constantes;
import juego.lobby.TipoCondicionVictoria;
import juego.tablero.Tablero;
import juego.tablero.casillero.Casillero;

public class VentanaAdministracionSala extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3071454957161090149L;
	private JFrame lobby;
	private JPanel panel;
	private JButton btnJoin;
	private JButton btnVolver;
	private JLabel lblNewLabel;
	private BufferedImage image;
	private JLabel labelLeft;
	private JPanel panel2;
	private JLabel imageLabel;
	private int actual = 1;
	private ArrayList<JLabel> labelsIconos;
	private ListIterator<JLabel> lis;
	private JLabel labelRight;
	private BufferedImage image2;
	private BufferedImage image3;
	private BufferedImage imageSheet;
	private JList<String> listUsuarios;
	private DefaultListModel<String> modelUsuariosLista = new DefaultListModel<String>();
	private JLabel lblPersonaje;
	private JComboBox<Object> comboMapa;
	private JComboBox<Object> comboCantRondas;
	private JLabel cantidadLabel;
	private JComboBox<Object> cantidadDeBotsComboBox;
	private JLabel lblCantBots;
	private JLabel cantidadDeBotsLabel;
	private JLabel cantidadRondasLabel;
	private JLabel mapaParaNoAdmin;
	private JComboBox<Object> condicionVictoria;
	private JLabel labelCondicionVictoria;
	private int totalRondas;
	private Musica musica;
	private String nombreSala;
	private boolean esAdmin;
	private Game game;
	private JComboBox<Object> comboBoxEstilo;
	private JLabel lblEstilo;
	private JLabel labelEstilo;

	public VentanaAdministracionSala(JFrame ventanaLobby, String nombreSala, boolean esAdmin) {
		this.esAdmin = esAdmin;
		// Me guardo la referencia para hacerlo visible, etc
		this.lobby = ventanaLobby;
		this.musica = new Musica(Constantes.MUSICA_SELECT);
		this.musica.loop();
		setTitle("Bienvenido a " + nombreSala);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		setBounds(0, 0, 456, 400);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		btnJoin = new JButton("Jugar!");
		btnJoin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnJoin.setBounds(329, 314, 111, 46);
		btnJoin.setEnabled(false);
		panel.add(btnJoin);
		setLocationRelativeTo(this.lobby);

		btnVolver = new JButton("Salir\r\n");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnVolver.setBounds(10, 314, 111, 46);
		panel.add(btnVolver);
		this.nombreSala = nombreSala;
		lblNewLabel = new JLabel(nombreSala);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 0, 430, 46);

		panel.add(lblNewLabel);
		panel2 = new JPanel();
		panel2.setLocation(143, 234);
		panel2.setSize(50, 50);

		labelLeft = new JLabel("");
		labelLeft.setIcon(new ImageIcon(
				new ImageIcon(Constantes.ARROW_LEFT).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
		labelLeft.setFont(new Font("Tahoma", Font.BOLD, 17));
		labelLeft.setBounds(109, 244, 32, 32);

		getContentPane().add(panel2);
		getContentPane().add(labelLeft);

		labelRight = new JLabel("");
		labelRight.setFont(new Font("Tahoma", Font.BOLD, 17));
		labelRight.setIcon(new ImageIcon(
				new ImageIcon(Constantes.ARROW_RIGHT).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
		labelRight.setFont(new Font("Tahoma", Font.BOLD, 17));
		labelRight.setBounds(195, 244, 32, 32);
		panel.add(labelRight);

		labelsIconos = new ArrayList<JLabel>();

		try {
			imageSheet = ImageIO
					.read(new File(Constantes.ASSETS_PATH + Constantes.IMAGEN_PATH + "/characters_sheet.png"));
			image = grabImage(imageSheet, 1, 1, 44, 44);
			image2 = grabImage(imageSheet, 2, 1, 44, 44);
			image3 = grabImage(imageSheet, 3, 1, 44, 44);
			imageLabel = new JLabel(new ImageIcon(image));
			labelsIconos.add(imageLabel);
			panel2.add(imageLabel);
			labelsIconos.add(new JLabel(new ImageIcon(image2)));
			labelsIconos.add(new JLabel(new ImageIcon(image3)));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.listUsuarios = new JList<String>(modelUsuariosLista);
		listUsuarios.setFont(new Font("Century Gothic", Font.BOLD, 14));
		this.listUsuarios.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.listUsuarios.setBounds(264, 60, 176, 222);
		this.listUsuarios.setEnabled(false);
		this.listUsuarios.setOpaque(false);
		getContentPane().add(this.listUsuarios);

		JLabel lblUsuariosConectados = new JLabel("Usuarios conectados");
		lblUsuariosConectados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuariosConectados.setBounds(292, 40, 200, 24);
		lblUsuariosConectados.setSize(lblUsuariosConectados.getPreferredSize());
		getContentPane().add(lblUsuariosConectados);

		lblPersonaje = new JLabel("Personaje");
		lblPersonaje.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPersonaje.setBounds(10, 239, 64, 32);
		panel.add(lblPersonaje);

		JLabel labelMapa = new JLabel("Tam mapa");
		labelMapa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelMapa.setBounds(10, 132, 98, 20);
		panel.add(labelMapa);

		comboMapa = new JComboBox<Object>();
		comboMapa.setToolTipText("Selecione un mapa");

		comboMapa.setBounds(109, 134, 134, 20);
		comboMapa.addItem("Seleccione mapa");
		comboMapa.addItem("Chico");
		comboMapa.addItem("Mediano");
		comboMapa.addItem("Grande");
		comboMapa.setSize(new Dimension(130, 20));
		panel.add(comboMapa);

		comboCantRondas = new JComboBox<Object>();
		comboCantRondas.setBounds(new Rectangle(109, 96, 119, 20));
		comboCantRondas.setToolTipText("Seleccione cantidad");
		comboCantRondas.addItem("Seleccione");
		comboCantRondas.addItem("1");
		comboCantRondas.addItem("2");
		comboCantRondas.addItem("3");
		comboCantRondas.addItem("4");
		comboCantRondas.addItem("5");
		comboCantRondas.setSize(new Dimension(130, 20));
		panel.add(comboCantRondas);

		cantidadDeBotsComboBox = new JComboBox<Object>();
		cantidadDeBotsComboBox.setEnabled(false);
		cantidadDeBotsComboBox.setToolTipText("Debe seleccionar cantidad de bots");
		cantidadDeBotsComboBox
				.setModel(new DefaultComboBoxModel<Object>(new String[] { "0", "1", "2", "3", "4", "5" }));
		cantidadDeBotsComboBox.setBounds(109, 172, 130, 20);
		panel.add(cantidadDeBotsComboBox);

		cantidadLabel = new JLabel();
		cantidadLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cantidadLabel.setText("Cantidad");
		cantidadLabel.setBounds(10, 94, 151, 20);
		panel.add(cantidadLabel);

		lblCantBots = new JLabel();
		lblCantBots.setEnabled(false);
		lblCantBots.setText("Cant. Bots");
		lblCantBots.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantBots.setBounds(10, 172, 151, 20);
		panel.add(lblCantBots);

		cantidadDeBotsLabel = new JLabel("-");
		cantidadDeBotsLabel.setEnabled(false);
		cantidadDeBotsLabel.setBounds(109, 172, 130, 20);
		panel.add(cantidadDeBotsLabel);

		cantidadRondasLabel = new JLabel("-");
		cantidadRondasLabel.setBounds(109, 96, 130, 20);
		panel.add(cantidadRondasLabel);

		mapaParaNoAdmin = new JLabel("-");
		mapaParaNoAdmin.setBounds(109, 134, 130, 20);
		panel.add(mapaParaNoAdmin);

		condicionVictoria = new JComboBox<Object>();
		condicionVictoria.setToolTipText("Selecione una condicion de victoria");
		condicionVictoria.setBounds(109, 57, 130, 20);
		condicionVictoria.addItem("Seleccione condicion");
		for (TipoCondicionVictoria tipo : TipoCondicionVictoria.values()) {
			condicionVictoria.addItem(tipo);
		}
		condicionVictoria.setSize(new Dimension(130, 20));
		panel.add(condicionVictoria);

		labelCondicionVictoria = new JLabel("-");
		labelCondicionVictoria.setBounds(109, 57, 130, 20);
		panel.add(labelCondicionVictoria);

		JLabel lblCondicin = new JLabel();
		lblCondicin.setText("Condici\u00F3n");
		lblCondicin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCondicin.setBounds(10, 57, 151, 20);
		panel.add(lblCondicin);

		comboBoxEstilo = new JComboBox<Object>();
		comboBoxEstilo.setEnabled(false);
		comboBoxEstilo.setToolTipText("Debe seleccionar cantidad de bots");
		comboBoxEstilo.setBounds(109, 203, 130, 20);
		panel.add(comboBoxEstilo);

		lblEstilo = new JLabel();
		lblEstilo.setEnabled(false);
		lblEstilo.setText("Estilo mapa");
		lblEstilo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstilo.setBounds(10, 203, 151, 20);
		panel.add(lblEstilo);

		labelEstilo = new JLabel("-");
		labelEstilo.setBounds(109, 203, 130, 20);
		panel.add(labelEstilo);

		// Si es admin, le muestro algunas cosas, si no otras
		if (esAdmin) {
			comboMapa.setEnabled(true);
			comboCantRondas.setEnabled(true);
			mapaParaNoAdmin.setVisible(false);
			cantidadDeBotsLabel.setVisible(false);
			labelCondicionVictoria.setVisible(false);
			comboBoxEstilo.setVisible(true);
			labelEstilo.setVisible(false);
		} else {
			comboMapa.setVisible(false);
			// Solo el admin puede arrancar la partida
			btnJoin.setVisible(false);
			comboCantRondas.setVisible(false);
			mapaParaNoAdmin.setVisible(true);
			cantidadDeBotsLabel.setVisible(true);
			cantidadLabel.setVisible(true);
			cantidadDeBotsComboBox.setVisible(false);
			condicionVictoria.setVisible(false);
			labelCondicionVictoria.setVisible(true);
			labelEstilo.setVisible(true);
			comboBoxEstilo.setVisible(false);

		}
		modelUsuariosLista.addElement(VentanaLogin.nombreUser);
		this.addListener();
	}

	private void addListener() {
		// Cosas internas
//		btnJoin.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//
//				switch (actual) {
//				case 1:
//					System.out.println("Elegiste Mario!");
//					break;
//				case 2:
//					System.out.println("Elegiste Luigi!");
//					break;
//				case 3:
//					System.out.println("Elegiste Yoshi!");
//					break;
//				}
//				
//
//			}
//		});
		// Listener que se encarga de mostrar u ocultar la contrase�a
		labelLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (actual > 1) {
					actual--;

					if (actual >= 1) {
						lis = labelsIconos.listIterator(actual);
						JLabel icono = lis.hasPrevious() ? lis.previous() : null;
						if (icono != null) {
							Component[] components = panel2.getComponents();

							for (Component component : components) {
								panel2.remove(component);
							}

							panel2.revalidate();
							panel2.repaint();
							panel2.add(icono);

						}
					}
				}
			}
		});

		labelRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (actual < labelsIconos.size()) {
					lis = labelsIconos.listIterator(actual);

					JLabel icono = null;
					if (actual == 0) {
						if (lis.hasNext()) {
							lis.next();
							if (lis.hasNext()) {
								icono = lis.next();
								actual++;
							}
						}
					} else {
						if (lis.hasNext()) {
							icono = lis.next();
						}
					}
					if (icono != null) {
						Component[] components = panel2.getComponents();

						for (Component component : components) {
							panel2.remove(component);
						}

						panel2.revalidate();
						panel2.repaint();
						panel2.add(icono);

						actual++;
					}
				}
			}
		});
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(getContentPane(), "Desea cerrar la ventana?", "Atencion!",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					abandonarSala();
					System.exit(0);
				}
			}
		});

		/// COSAS QUE AFECTAN AL RESTO
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				abandonarSala();
			}
		});

		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				empezarJuego();
			}
		});

		btnJoin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					empezarJuego();
				}
			}
		});

		// Todo esto deberia hacer requests al sv
		// Para que los otros lo vean
		comboMapa.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				actualizarSala();

			}
		});

		comboCantRondas.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				actualizarSala();

			}
		});

		condicionVictoria.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				actualizarSala();

			}
		});

		cantidadDeBotsComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				actualizarSala();
			}
		});
	}

	public static BufferedImage grabImage(BufferedImage image, int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		return img;
	}

	public void cerrarSala() {
		JOptionPane.showMessageDialog(this, "El administrador abandono la sala", "Sala terminada",
				JOptionPane.INFORMATION_MESSAGE);
		this.setVisible(false);
	}

	protected void empezarJuego() {
		int totalBots = Integer.parseInt((String) cantidadDeBotsComboBox.getSelectedItem());
		this.totalRondas = Integer.parseInt((String) comboCantRondas.getSelectedItem());
		TipoCondicionVictoria condicion = (TipoCondicionVictoria) condicionVictoria.getSelectedItem();
		String mapa = (String) comboMapa.getSelectedItem();

		/*
		 * if (!Cliente.getConexionInterna().usuariosEnSala()) {
		 * JOptionPane.showMessageDialog(this, "Hay usuarios aun en la partida",
		 * "Atencion!", JOptionPane.INFORMATION_MESSAGE); return; }
		 */

		// LE AVISO AL SERVER QUE VA A ARRANCAR
//		/* if ( */Cliente.getConexionInterna().comenzarJuego(totalBots, totalRondas, condicion, mapa);/* == false) { */
//			System.out.println("Error al crear el juego");
//			return;
//		} else {
		// Si no creo el thread, no funciona porque queda "colgado"
		new Thread(() -> {
			Cliente.getConexionInterna().comenzarJuego(totalBots, totalRondas, condicion, mapa);
		}).start();
		Coordinador.ventanaAdministracionSala.prepararArranqueJuego();
//		}
//		JsonObject paquetePedirMinijuego = Json.createObjectBuilder().add("type", Constantes.NOTICE_ARRANCAR_JUEGO)
//				.add("sala", nombreSala).build();
//
//		// Le aviso al sv que estoy listo para jugar
//		Cliente.getConexionServidor().enviarAlServidor(paquetePedirMinijuego);
	}

	protected void validacionBotonJugar() {
		if (comboMapa.getSelectedIndex() != 0 && comboCantRondas.getSelectedIndex() != 0

				&& (this.listUsuarios.getModel().getSize() >= 2
						|| this.cantidadDeBotsComboBox.getSelectedIndex() != 0)) {
			btnJoin.setEnabled(true);
		} else {
			btnJoin.setEnabled(false);
		}
	}

	private void abandonarSala() {
		this.lobby.setVisible(true);
		setVisible(false);
		JsonObject paqueteSalirSala = Json.createObjectBuilder().add("type", Constantes.LEAVE_ROOM_REQUEST)
				.add("sala", this.nombreSala).build();

		Cliente.getConexionInterna().salirSala(this.nombreSala);
		Coordinador.setVentanaAdministracionSala(null);
		Cliente.getConexionServidor().enviarAlServidor(paqueteSalirSala);
	}

	public void actualizarSala() {

		JsonObjectBuilder datosSala = Json.createObjectBuilder();

		// Agrego parametros
		datosSala.add("type", Constantes.REFRESH_PARAM_ROOM);
		datosSala.add("sala", this.nombreSala);

		if (condicionVictoria.getSelectedIndex() == 0) {
			datosSala.add("condicion", "-");
		} else {
			datosSala.add("condicion", String.valueOf(condicionVictoria.getSelectedItem()));
		}
		if (comboMapa.getSelectedIndex() == 0) {
			datosSala.add("mapa", "-");
		} else {
			datosSala.add("mapa", (String) comboMapa.getSelectedItem());
		}

		if (comboCantRondas.getSelectedIndex() == 0) {
			datosSala.add("cantidad", "-");
		} else {
			datosSala.add("cantidad", (String) comboCantRondas.getSelectedItem());
		}

		datosSala.add("bots", String.valueOf(cantidadDeBotsComboBox.getSelectedItem()));

		Cliente.getConexionServidor().enviarAlServidor(datosSala.build());

	}

	public void actualizarUsuarios(JsonObject entradaJson) {
		String tipoDeActualizacion = entradaJson.getString("type");

		System.out.println(tipoDeActualizacion);
		if (tipoDeActualizacion.equals(Constantes.REFRESH_ROOM)) {
			JsonArray arrayUsuariosConectados = entradaJson.getJsonArray("usuarios");
			this.modelUsuariosLista.clear();

			for (int i = 0; i < arrayUsuariosConectados.size(); i++) {
				this.modelUsuariosLista.addElement(arrayUsuariosConectados.getString(i));

			}
			this.listUsuarios.setModel(this.modelUsuariosLista);
		} else {
			if (!this.esAdmin) {
				this.labelCondicionVictoria.setText(entradaJson.getString("condicion"));
				this.cantidadRondasLabel.setText(entradaJson.getString("cantidad"));
				this.mapaParaNoAdmin.setText(entradaJson.getString("mapa"));
				this.cantidadDeBotsLabel.setText(entradaJson.getString("bots"));
			}
		}

	}

	public void prepararArranqueJuego() {
		game = new Game();
		new GameWindow(800, 600, "Mario Party Prototipo", game);
		this.setVisible(false);
	}

}
