package juego.personas;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import juego.item.Inventario;
import juego.lobby.Usuario;
import juego.lobby.Partida;

import juego.misc.Dado;
import juego.tablero.Tablero;
import juego.tablero.casillero.Casillero;

public class Jugador {
	// Necesito tener una referencia a la partida, es el objeto padre de todo
	private Partida partida;
	private String nombre;
	private int pesos;
	private Color color;
	private int dolares;
	private Inventario inventario;
	private Personaje personaje;
	private Casillero posicion;
	private Dado dado;

	public Jugador(Usuario usuario, Tablero tablero,Partida partida) {
		this.partida = partida;
		this.nombre = usuario.getUsername();
		this.pesos = 100;
		this.dolares = 0;
		Random rand = new Random(System.currentTimeMillis());
		this.color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		this.dado = new Dado(6);
	}

	public void tirarDado() {
		int numero = this.dado.tirar();
		System.out.println("Sali");
		avanzar(numero);
		dado = new Dado(6);
	}

	private void avanzar(int d) {
		this.posicion.removerJugador(this);
		for (int i = 0; i < d; i++) {
			avanzarUnCasillero();
			if(this.posicion.isTieneArbolito() == true ){ //Si el casillero por el q acabo de pasar tiene un arbolito, puedo comprar un dolar
				juego.Main.mostrar("Desea comprar un dolar por "+partida.getPrecioDolar()+" pesos?");
				char respuesta;
				
				do
					respuesta = (char) juego.Main.leer();
				while(respuesta != 'S' && respuesta != 'N');
				
				if(respuesta == 'S') {
					if(this.pesos >= partida.getPrecioDolar()) {
						this.dolares++;
						this.pesos-=partida.getPrecioDolar();
						juego.Main.mostrar("No te alcanza");
					}
				}
			}
		}
		if(this.posicion.isTieneRecompensa() && this.posicion.isPrimeraVez())
		{
			this.posicion.getRecompensa().darRecompensa(this);
		}
		this.posicion.agregarJugador(this);
	}

	private void avanzarUnCasillero() {
		ArrayList<Casillero> siguiente = this.posicion.getSiguiente();
		int caminoElegido;
		if(siguiente.size() == 1) //si solo hay 1 camino, avanzo
		{
			this.posicion = siguiente.get(0);
		}
		System.out.println("Elije un camino"); //cuando hay mas de 1 camino, el jugador elije
		caminoElegido = juego.Main.leer(); 
		this.posicion = siguiente.get(caminoElegido);
	}

	/*
	 * SETTERS Y GETTERS
	 * 
	 */

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPesos() {
		return pesos;
	}

	public void setPesos(int pesos) {
		this.pesos = pesos;
	}

	public void setPuntaje(int pesos) {
		this.pesos = pesos;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return this.nombre + "            " + this.pesos;
	}

	public int getDolares() {
		return this.dolares;
	}

	public void setPuntosEnPartida(int dolares) {
		this.dolares = dolares;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	public Casillero getPosicion() {
		return posicion;
	}

	public void setPosicion(Casillero posicion) {
		this.posicion = posicion;
	}
	
	public Dado getDado() {
		return this.dado;
	}
	
	public Partida getPartida() {
		return this.partida;
	}

	public void setDado(Dado dado) {
		this.dado = dado;
	}
/*
 * Hay que ver como encaramos la etapa de accion para que un jugador use un item.
 * */
	public void etapaAccion() {
		// TODO Auto-generated method stub
		
	}
	
}
