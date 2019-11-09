package juego.tablero;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.TreeMap;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import juego.misc.ExcepcionArchivos;
import juego.misc.LectorEscritor;
import juego.tablero.casillero.Casillero;

public class Tablero {
	// Me interesa tener un id->value, ordenado
	// Puede cambiar en el futuro
	Map<Integer, Casillero> casilleros;
	private int id;
	private int dimensionX;
	private int dimensionY;

	public Tablero(String arch) throws ExcepcionArchivos, FileNotFoundException {
		this.casilleros = new TreeMap<Integer, Casillero>();
		crearCasillerosYCrearTablero(arch);
	}

	private void crearCasillerosYCrearTablero(String arch) throws ExcepcionArchivos, FileNotFoundException {
		// Por ahora voy a usar un .txt para levantar los casilleros
		LectorEscritor le = new LectorEscritor();
		le.leerTablero(arch, this);

	}

	public Map<Integer, Casillero> getCasilleros() {
		return casilleros;
	}

	public void setCasilleros(Map<Integer, Casillero> casilleros) {
		this.casilleros = casilleros;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDimensionX() {
		return dimensionX;
	}

	public void setDimensionX(int dimensionX) {
		this.dimensionX = dimensionX;
	}

	public int getDimensionY() {
		return dimensionY;
	}

	public void setDimensionY(int dimensionY) {
		this.dimensionY = dimensionY;
	}

	public void actualizar() {
		// TODO ACA IRIA TODA LA LOGICA PARA REFRESCAR EL MAPA, HAY QUE VER COMO SACARLO
		// DEL CLIENTE

	}

	public JsonArrayBuilder toJson() {
		JsonArrayBuilder casilleros = Json.createArrayBuilder();

		for (Integer u : this.casilleros.keySet()) {
			casilleros.add(convertirAJson(this.casilleros.get(u)));
		}

		return casilleros;
	}

	private JsonObject convertirAJson(Casillero casillero) {
		return Json.createObjectBuilder().add("id", casillero.getId()).add("x", casillero.getPosicionX())
				.add("y", casillero.getPosicionY()).add("primeraVez", casillero.isPrimeraVez())
				.add("tieneArbolito", casillero.isTieneArbolito()).add("tieneRecompensa", casillero.isTieneRecompensa())
				.build();

	}

	/*
	 * Me devuelve la cantidad de casilleros que me puedo mover hasta terminar Para
	 * no pasarme de rango, voy a ir recorriendo cada rama y me quedo con la mas
	 * peque�a
	 */
}
