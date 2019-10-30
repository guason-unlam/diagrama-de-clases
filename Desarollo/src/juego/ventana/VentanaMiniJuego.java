package juego.ventana;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import juego.Constantes;
import juego.personas.Jugador;
import juego.tablero.MejorDeDiez;
import juego.tablero.MiniJuego;

class VentanaMiniJuego implements ActionListener {
	JPanel cards; // a panel that uses CardLayout
	final static String BUTTONPANEL = "Card with JButtons";
	final static String TEXTPANEL = "Card with JTextField";
	final static String GANADORES = "ganadores";

	/**
	 * @param ganadores
	 * @wbp.parser.entryPoint
	 */
	public void addComponentToPane(Container pane, String resultadoGanadores) {
		// Put the JComboBox in a JPanel to get a nicer look.

		// Create the "cards".
		JPanel card1 = new JPanel();

		JButton aceptarButton = new JButton("Aceptar\r\n");
		aceptarButton.setForeground(Color.DARK_GRAY);
		aceptarButton.setBackground(new Color(152, 251, 152));
		aceptarButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		aceptarButton.setBounds(403, 355, 133, 52);
		aceptarButton.addActionListener(this);
		card1.add(aceptarButton);

		JPanel instruccionesPanel = new JPanel();
		JTextArea instrucciones = new JTextArea();
		instrucciones.setFont(new Font("Dialog", Font.PLAIN, 22));
		instrucciones.setText(
				"El juego consta de varios jugadores,los cuales tirar\u00E1n 10 veces un dado y se realizar\u00E1 la suma de todos los tiros.");
		instrucciones.setEditable(false);
		instrucciones.setLineWrap(true);
		instrucciones.setOpaque(false);
		instrucciones.setWrapStyleWord(true);

		instrucciones.setBorder(BorderFactory.createEmptyBorder());
		instrucciones.setSize(400, 100);
		instruccionesPanel.add(instrucciones, BorderLayout.CENTER);

		JPanel card2 = new JPanel();
		JTextArea ganadores = new JTextArea();
System.out.println(resultadoGanadores);
		ganadores.setFont(new Font("Dialog", Font.PLAIN, 22));
		ganadores.setText(resultadoGanadores);
		ganadores.setEditable(false);
		ganadores.setLineWrap(true);
		ganadores.setOpaque(false);
		ganadores.setWrapStyleWord(true);

		ganadores.setBorder(BorderFactory.createEmptyBorder());
		ganadores.setSize(400, 100);
		card2.add(ganadores, BorderLayout.PAGE_END);

		// Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());
		cards.add(card1, BUTTONPANEL);

		cards.add(card2, GANADORES);
		pane.add(instruccionesPanel, BorderLayout.PAGE_START);
		pane.add(cards, BorderLayout.CENTER);
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event dispatch thread.
	 * 
	 * @param string
	 * @wbp.parser.entryPoint
	 */
	private static void createAndShowGUI(String ganadores) {
		// Create and set up the window.
		JFrame frame = new JFrame("CardLayoutDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		VentanaMiniJuego demo = new VentanaMiniJuego();
		demo.addComponentToPane(frame.getContentPane(), ganadores);
		frame.setPreferredSize(new Dimension(Constantes.MAPA_WIDTH, Constantes.MAPA_HEIGHT));
		// Display the window.
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	public static void ejecutar(ArrayList<Jugador> jugadores) {
		MejorDeDiez mejorDeDiez = new MejorDeDiez(jugadores);
		mejorDeDiez.iniciar();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI(mejorDeDiez.getResultados());
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String comStr = ae.getActionCommand().replaceAll("\\s+", "");

		CardLayout cl = (CardLayout) (cards.getLayout());

		if (comStr.equals("Aceptar")) {

			cl.show(cards, GANADORES);
		}

	}
}
