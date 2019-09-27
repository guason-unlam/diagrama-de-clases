package juego.tablero.casillero;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import juego.item.ModificadorDado;
import juego.item.Pesos;
import juego.item.Recompensa;
import juego.personas.Jugador;

public class Casillero {
	// Id interno
	private int id;
	// Color del casillero
	private Color color;
	// Posicion fisica en el tablero
	private int posicionX;
	private int posicionY;
	// En caso de ser verdadero, le da las recompensas
	private boolean primeraVez;
	
	// Para saber si la estrella esta en este casillero
	private boolean tieneArbolito;
	private boolean tieneRecompensa;

	// Para el movimiento
	private ArrayList<Casillero> siguientes;
	private ArrayList<Casillero> anteriores;
	// Personajes actualmente en el casillero
	private Set<Jugador> jugadores = new TreeSet<Jugador>();
	
	private Recompensa recompensa;
	/*
	 * Permite agregar un personaje al actual casillero
	 * 
	 * @param Personaje
	 * 
	 * @return void
	 */

	public Casillero() {
		tieneArbolito = false;
		tieneRecompensa = false;
		primeraVez = true;
		double ran = Math.random();
		if(ran > 0.5) { // 50% de prob de que el casillero no tenga recompensa
			tieneRecompensa = true;
			if(ran > 0.85) // 15% de prob de q la recompensa sea un item
				recompensa = new ModificadorDado(); // de momento el unico item que tenemos es ModificadorDado, cuando haya mas se modificara aca
			else
				recompensa = new Pesos(50); // 35% de prob de que la recompensa sean 50p, de momento hardcodeado
		}
	}
	
	public void removerJugador(Jugador j) {
		this.jugadores.remove(j);
	}
	public void agregarJugador(Jugador j) {
		this.jugadores.add(j);
	}

	// Setters y getters default
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public boolean isPrimeraVez() {
		return primeraVez;
	}

	public void setPrimeraVez(boolean primeraVez) {
		this.primeraVez = primeraVez;
	}

	public ArrayList<Casillero> getSiguiente() {
		return siguientes;
	}

	public void setSiguiente(ArrayList<Casillero> siguiente) {
		this.siguientes = siguiente;
	}

	public ArrayList<Casillero> getAnteriores() {
		return anteriores;
	}

	public void setAnterior(ArrayList<Casillero> anteriores) {
		this.anteriores = anteriores;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Recompensa getRecompensa() {
		return this.recompensa;
	}
	public boolean isTieneArbolito() {
		return tieneArbolito;
	}
	public void setTieneArbolito(boolean tieneArbolito) {
		this.tieneArbolito = tieneArbolito;
	}

	public boolean isTieneRecompensa() {
		return tieneRecompensa;
	}

}
