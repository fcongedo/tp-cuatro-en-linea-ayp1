package juego;

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
	 * pre : 'filas' y 'columnas' son mayores o iguales a 4.
	 * post: empieza el juego entre el jugador que tiene fichas rojas, identificado como
	 * 'jugadorRojo' y el jugador que tiene fichas amarillas, identificado como
	 * 'jugadorAmarillo'. Todo el tablero está vacío.
	 * 
	 * @param filas: cantidad de filas que tiene el tablero.
	 * @param columnas: cantidad de columnas que tiene el tablero.
	 * @param jugadorRojo: nombre del jugador con fichas rojas.
	 * @param jugadorAmarillo: nombre del jugador con fichas amarillas.
	 * 
	 */

	private Casillero tablero[][];

	private String jugadorRojo;
	private String jugadorAmarillo;
	private String TurnoDelJugador;
	private String ultimoJugadorEnSoltarFicha;

	private int filas;
	private int columnas;
	private int ContadorFichasTotalesUsadas;

	public CuatroEnLinea(int filas, int columnas, String jugadorRojo,
			String jugadorAmarillo) {

		if (filas < 4 || columnas < 4) {
			throw new Error(
					"La cantidad de filas y columnas deben ser iguales o mayores que 4");
		}
		

		this.filas = filas;
		this.columnas = columnas;
		this.jugadorAmarillo = jugadorAmarillo;
		this.jugadorRojo = jugadorRojo;
		this.tablero = new Casillero[this.contarFilas()][this.contarColumnas()];
		this.iniciarTableroVacio();
		this.TurnoDelJugador = jugadorRojo;
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
		String JugadorAnterior = this.TurnoDelJugador;

		if (this.termino() || columna < 1 && columna > this.contarColumnas()) {
			throw new Error("El juego no termino");

		}

		Integer casilleroLibre = verificarCasilleroLibreColumna(columna);

		if (casilleroLibre != null) {

			this.tablero[casilleroLibre - 1][columna - 1] = obtenerCasilleroDelJugadorConTurno();
			this.TurnoDelJugador = obtenerJugadorEnEspera();
			this.ultimoJugadorEnSoltarFicha = JugadorAnterior;

			ContadorFichasTotalesUsadas++;
		}

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
		return this.ContadorFichasTotalesUsadas < (this.contarFilas() * this
				.contarColumnas());
	}

	private String obtenerJugadorEnEspera() {
		if (TurnoDelJugador == jugadorRojo) {
			return jugadorAmarillo;
		}
		return jugadorRojo;
	}

	private Casillero obtenerCasilleroDelJugadorConTurno() {
		if (TurnoDelJugador == jugadorRojo) {
			return Casillero.ROJO;

		}

		return Casillero.AMARILLO;

	}

}
