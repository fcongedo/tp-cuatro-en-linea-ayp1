package juego;

import javax.swing.JOptionPane;

/**
 * Juego Cuatro en Línea
 * 
 * Reglas:
 * 
 * ...
 *
 */
public class CuatroEnLinea {

	/**
	 * pre : 'filas' y 'columnas' son mayores o iguales a 4 y menores o iguales
	 * a 12. post: empieza el juego entre el jugador que tiene fichas azules,
	 * identificado como 'jugadorAzul' y el jugador que tiene fichas amarillas,
	 * identificado como 'jugadorAmarillo'. Todo el tablero está vacío.
	 * 
	 * @param filas
	 *            : cantidad de filas que tiene el tablero.
	 * @param columnas
	 *            : cantidad de columnas que tiene el tablero.
	 * @param jugadorAzul
	 *            : nombre del jugador con fichas rojas.
	 * @param jugadorAmarillo
	 *            : nombre del jugador con fichas amarillas.
	 * 
	 */

	private Casillero tablero[][];

	private String errorNombresIguales = "No se puede ingresar dos nombres iguales";
	private String errorNombresVacios = "No se pueden ingresar dos nombres vacios";
	private String errorNombreVacioAmarillo = "Por favor ingrese un nombre al jugador oro actualmente (vacio)";
	private String errorNombreVacioAzul = "Por favor ingrese un nombre al jugador azul actualmente (vacio)";
	private String errorMaximoFilasYColumnas = "Las dimensiones maximas del tablero son 8x8";
	private String errorMaximoFilas = "El valor de las filas debe ser menor o igual a 8";
	private String errorMaximoColumnas = "El valor de las columnas debe ser menor o igual a 8";
	private String errorMinimoFilasYColumnas = "Las dimensiones minimas del tablero son 4x4";
	private String errorMinimoFilas = "El valor de la filas debe ser mayor o igual a 4";
	private String errorMinimoColumnas = "El valor de la columnas debe ser mayor o igual a 4";
	private String errorFueraDeRango = "No se puede soltar ficha, fuera del tablero";
	private String jugadorAzul;
	private String jugadorAmarillo;
	private String turnoDelJugador;
	private String ultimoJugadorEnSoltarFicha;

	private int filas;
	private int columnas;
	private int contadorFichasTotalesUsadas;
	private int contadorErrorCasilleroLleno = 0;

	public CuatroEnLinea(int filas, int columnas, String jugadorAzul,
			String jugadorAmarillo) {

		if (jugadorAmarillo.equals("") && jugadorAzul.equals("")) {

			JOptionPane.showMessageDialog(null, errorNombresVacios,
					"Fatal Error!", JOptionPane.WARNING_MESSAGE);
			throw new Error("nombres vacios");
		}

		if (jugadorAmarillo.equals("") || jugadorAzul.equals("")) {
			if (jugadorAmarillo.equals("")) {
				JOptionPane.showMessageDialog(null, errorNombreVacioAmarillo,
						"Fatal Error!", JOptionPane.WARNING_MESSAGE);
				throw new Error("nombre vacio amarillo");
			}
			JOptionPane.showMessageDialog(null, errorNombreVacioAzul,
					"Fatal Error!", JOptionPane.WARNING_MESSAGE);
			throw new Error("nombre vacio azul");
		}

		if (jugadorAmarillo.equalsIgnoreCase(jugadorAzul)) {

			JOptionPane.showMessageDialog(null, errorNombresIguales,
					"Fatal Error", JOptionPane.WARNING_MESSAGE);
			throw new Error("nombres iguales");
		}

		if (filas < 4 && columnas < 4) {
			JOptionPane.showMessageDialog(null, errorMinimoFilasYColumnas,
					"Fatal Error", JOptionPane.WARNING_MESSAGE);
			throw new Error(
					"La cantidad de filas y columnas deben ser iguales o mayores que 4");
		}
		if (filas < 4 || columnas < 4) {
			if (filas < 4) {
				JOptionPane.showMessageDialog(null, errorMinimoFilas,
						"Fatal Error", JOptionPane.WARNING_MESSAGE);
				throw new Error(
						"La cantidad de filas deben ser iguales o mayores que 4");
			}

			JOptionPane.showMessageDialog(null, errorMinimoColumnas,
					"Fatal Error", JOptionPane.WARNING_MESSAGE);
			throw new Error(
					"La cantidad de columnas deben ser iguales o mayores que 4");

		}
		if (filas > 8 && columnas > 8) {

			JOptionPane.showMessageDialog(null, errorMaximoFilasYColumnas,
					"Fatal Error!", JOptionPane.WARNING_MESSAGE);
			throw new Error(
					"El valor de las filas y columnas debe ser menor o igual a 8");
		}

		if (filas > 8 || columnas > 8) {
			if (filas > 8) {
				JOptionPane.showMessageDialog(null, errorMaximoFilas,
						"Fatal Error!", JOptionPane.WARNING_MESSAGE);
				throw new Error(
						"El valor de las filas debe ser menor o igual a 8");
			}
			JOptionPane.showMessageDialog(null, errorMaximoColumnas,
					"Fatal Error!", JOptionPane.WARNING_MESSAGE);
			throw new Error(
					"El valor de las columnas debe ser menor o igual a 8");
		}

		this.filas = filas;
		this.columnas = columnas;
		this.jugadorAmarillo = jugadorAmarillo;
		this.jugadorAzul = jugadorAzul;
		this.tablero = new Casillero[this.contarFilas()][this.contarColumnas()];
		this.iniciarTableroVacio();
		this.turnoDelJugador = jugadorAzul;
	}

	private void iniciarTableroVacio() {

		for (int i = 0; i < this.contarFilas(); i++) {
			for (int j = 0; j < this.contarColumnas(); j++) {
				this.tablero[i][j] = Casillero.VACIO;
			}
		}
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden apilar en el
	 * tablero.
	 */
	public int contarFilas() {

		return this.filas;
	}

	/**
	 * post: devuelve la cantidad máxima de fichas que se pueden alinear en el
	 * tablero.
	 */
	public int contarColumnas() {

		return this.columnas;
	}

	/**
	 * pre : fila está en el intervalo [1, contarFilas()], columnas está en el
	 * intervalo [1, contarColumnas()]. post: indica qué ocupa el casillero en
	 * la posición dada por fila y columna.
	 * 
	 * @param fila
	 * @param columna
	 */
	public Casillero obtenerCasillero(int fila, int columna) {

		if (fila < 1 && fila > this.contarFilas() || columna < 1
				&& columna > this.contarColumnas()) {

			throw new Error("Valor de filas y columnas invalidos");

		}

		return this.tablero[fila - 1][columna - 1];
	}

	/**
	 * Pre : el juego no terminó, columna está¡ en el intervalo [1,
	 * contarColumnas()] y aún queda un Casillero.VACIO en la columna indicada.
	 * Post: deja caer una ficha en la columna indicada.
	 * 
	 * @param columna
	 */
	public void soltarFicha(int columna) {

		String jugadorAnterior = this.turnoDelJugador;
		if (this.termino()
				|| !(columna >= 1 && columna <= this.contarColumnas())) {
			JOptionPane.showMessageDialog(null, "el juego termino", "error",
					JOptionPane.WARNING_MESSAGE);
			throw new Error(
					"El juego Termino");
			   
		}

		Integer casilleroLibre = verificarCasilleroLibreColumna(columna);

		if (casilleroLibre == null) {
			contadorErrorCasilleroLleno++;
			JOptionPane.showMessageDialog(null, errorFueraDeRango, "error",
					JOptionPane.WARNING_MESSAGE);
			throw new Error("fuera de rango");

		}
		this.tablero[casilleroLibre - 1][columna - 1] = obtenerCasilleroDelJugadorConTurno();
		this.turnoDelJugador = obtenerJugadorEnEspera();
		this.ultimoJugadorEnSoltarFicha = jugadorAnterior;

		contadorFichasTotalesUsadas++;
	}

	public int obtenerContadorErrorCasilleroLleno() {
		return contadorErrorCasilleroLleno;
	}

	/**
	 * post: devuelve la posición del último casillero VACIO en una columna de
	 * lo contrario devuelve null
	 */
	private Integer verificarCasilleroLibreColumna(int columna) {

		boolean hayCasilleroVacio = false;
		Integer ultimaPosicionVacia = null;

		for (int i = this.contarFilas() - 1; i >= 0 && !hayCasilleroVacio; i--) {
			if (this.tablero[i][columna - 1] == Casillero.VACIO) {
				hayCasilleroVacio = true;
				ultimaPosicionVacia = i + 1;
			}
		}

		return ultimaPosicionVacia;
	}

	/**
	 * post: indica si el juego terminó porque uno de los jugadores ganó o no
	 * existen casilleros vacíos.
	 */
	public boolean termino() {

		boolean juegoTerminado = (!elTableroTieneCasillerosVacios() || this
				.hayGanador());

		return juegoTerminado;
	}

	/**
	 * post: indica si el juego terminó y tiene un ganador.
	 */
	public boolean hayGanador() {

		return ganadorHorizontal() || ganadorVertical()
				|| ganadorDiagonalALaIzquierda() || ganadorDiagonalALaDerecha();
	}

	private boolean ganadorDiagonalALaIzquierda() {

		boolean ganadorDiagonal = false;

		for (int i = 3; i < this.contarColumnas() && !ganadorDiagonal; i++) {

			for (int j = 3; j < this.contarFilas() && !ganadorDiagonal; j++) {

				ganadorDiagonal = (this.tablero[j][i] == this.tablero[j - 1][i - 1]
						&& this.tablero[j][i] == this.tablero[j - 2][i - 2]
						&& this.tablero[j][i] == this.tablero[j - 3][i - 3] && this.tablero[j][i] != Casillero.VACIO);
			}
		}
		return ganadorDiagonal;
	}

	private boolean ganadorDiagonalALaDerecha() {

		boolean ganadorDiagonal = false;

		for (int i = 2; i < this.contarColumnas() - 1 && !ganadorDiagonal; i++) {

			for (int j = 2; j < this.contarFilas() - 1 && !ganadorDiagonal; j++) {

				ganadorDiagonal = (this.tablero[j - 1][i] == this.tablero[j][i - 1]
						&& this.tablero[j - 1][i] == this.tablero[j + 1][i - 2]
						&& this.tablero[j - 1][i] == this.tablero[j - 2][i + 1] && this.tablero[j - 1][i] != Casillero.VACIO);
			}
		}

		return ganadorDiagonal;
	}

	private boolean ganadorHorizontal() {

		boolean ganadorHorizontal = false;
		int cantidadDeFichasContiguas = 1;

		for (int i = 0; i < this.contarFilas() && !ganadorHorizontal; i++) {

			cantidadDeFichasContiguas = 1;
			for (int j = 1; j < this.contarColumnas() && !ganadorHorizontal; j++) {
				if (this.tablero[i][j - 1] == this.tablero[i][j]
						&& this.tablero[i][j - 1] != Casillero.VACIO) {
					cantidadDeFichasContiguas++;
				} else {
					cantidadDeFichasContiguas = 1;
				}
				ganadorHorizontal = cantidadDeFichasContiguas == 4;
			}

		}
		return ganadorHorizontal;
	}

	private boolean ganadorVertical() {

		boolean ganadorVertical = false;
		int cantidadDeFichasContiguas = 1;

		for (int i = 0; i < this.contarColumnas() && !ganadorVertical; i++) {

			cantidadDeFichasContiguas = 1;
			for (int j = 0; j < this.contarFilas() - 1 && !ganadorVertical; j++) {
				if (this.tablero[j][i] == this.tablero[j + 1][i]
						&& this.tablero[j][i] != Casillero.VACIO) {
					cantidadDeFichasContiguas++;
				} else {
					cantidadDeFichasContiguas = 1;
				}
				ganadorVertical = cantidadDeFichasContiguas == 4;
			}

		}
		return ganadorVertical;
	}

	/**
	 * pre : el juego terminó. post: devuelve el nombre del jugador que ganó el
	 * juego.
	 */
	public String obtenerGanador() {

		String ganador = null;

		if (this.termino() && elTableroTieneCasillerosVacios()) {
			ganador = this.ultimoJugadorEnSoltarFicha;
		}

		return ganador;
	}

	private boolean elTableroTieneCasillerosVacios() {
		return this.contadorFichasTotalesUsadas < (this.contarFilas() * this
				.contarColumnas());
	}

	private String obtenerJugadorEnEspera() {
		if (turnoDelJugador == jugadorAzul) {
			return jugadorAmarillo;
		}
		return jugadorAzul;
	}

	public Casillero obtenerCasilleroDelJugadorConTurno() {
		if (turnoDelJugador == jugadorAzul) {
			return Casillero.AZUL;

		}

		return Casillero.AMARILLO;

	}

}
