package juego;

import org.junit.Assert;
import org.junit.Test;

public class CuatroEnLineaTest {

	@Test(expected = Error.class)
	public void PrueboErrorCrearJugadoresConNombresIguales() {

		new CuatroEnLinea(4, 4, "carlos", "carlos");
	}

	@Test(expected = Error.class)
	public void PrueboErrorCrearJugadoresConNombresVacios() {
		new CuatroEnLinea(4, 4, "", "");
	}

	@Test(expected = Error.class)
	public void PrueboErrorCrearJugadorAzulConNombreVacio() {
		new CuatroEnLinea(4, 4, "", "carlos");
	}

	@Test(expected = Error.class)
	public void PrueboErrorCrearJugadorAmarilloConNombreVacio() {
		new CuatroEnLinea(4, 4, "carlos", "");
	}

	@Test(expected = Error.class)
	public void PrueboErrorAlCrearJuegoConValorFilaNegativo() {

		new CuatroEnLinea(-7, 5, "Azul", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboErrorAlCrearJuegoConValorColumnaNegativo() {

		new CuatroEnLinea(7, -7, "Azul", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboErrorAlCrearJuegoConValorFilaYColumnaNegativo() {

		new CuatroEnLinea(-7, -7, "Azul", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboErrorAlCrearJuegoConValorUnoEnFilaYColumna() {

		new CuatroEnLinea(1, 1, "Azul", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboErrorAlCrearJuegoConValorUnoEnFila() {

		new CuatroEnLinea(1, 4, "Azul", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboErrorAlCrearJuegoConValorUnoColumna() {

		new CuatroEnLinea(4, 1, "Azul", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboElErrorsiSeCreaUnJuegoConTreFilasYCuatroColumnasDaError() {

		new CuatroEnLinea(3, 4, "Azul", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboElErrorsiSeCreaUnJuegoConCuatroFilasYTresColumnasDaError() {

		new CuatroEnLinea(4, 3, "Azul", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboElErrorsiSeCreaUnJuegoConNombresIguales() {
		new CuatroEnLinea(4, 4, "carlos", "carlos");
	}

	@Test(expected = Error.class)
	public void PrueboElErrorsiSeCreaUnJuegoConTreceFilasYTreceColumnasDaError() {
		// pruebo pasar limite maximo de filas y columnas validas da error

		new CuatroEnLinea(13, 13, "Azul", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboElErrorsiSeCreaUnJuegoConTresFilasYTresColumnasDaError() {
		// pruebo pasar limite maximo de filas y columnas validas da error

		new CuatroEnLinea(3, 3, "Azul", "Amarillo");
	}

	@Test
	public void alComenzarElJuegoComprueboQueNoHayGanador() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");

		Assert.assertFalse(juego.hayGanador());
		Assert.assertNull(juego.obtenerGanador());
	}

	@Test
	public void ComprueboLaCorrectaCreacionDeElTableroDeTamañoCuatroXCuatroMinimo() {
		// pruebo limite minimo de columnas y filas validas

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");

		Assert.assertEquals(4, juego.contarFilas());
		Assert.assertEquals(4, juego.contarColumnas());
	}

	@Test
	public void ComprueboLaCorrectaCreacionDeElTableroDeTamaño8X8Maximo() {
		// pruebo limite maximo de columnas y filas maximas
		CuatroEnLinea juego = new CuatroEnLinea(8, 8, "Azul", "Amarillo");

		Assert.assertEquals(8, juego.contarFilas());
		Assert.assertEquals(8, juego.contarColumnas());
	}

	@Test
	public void ComprueboQueAlCrearElJuegoDichoJuegoNoTermino() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");

		Assert.assertFalse(juego.termino());
	}

	@Test
	public void PrueboQueAlCrearElJuegoElTableroSeCreaVacio() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");

		for (int i = 1; i <= juego.contarFilas(); i++) {
			for (int j = 1; j <= juego.contarColumnas(); j++) {
				Assert.assertEquals(Casillero.VACIO,
						juego.obtenerCasillero(i, j));
			}
		}
	}

	@Test
	public void PrueboQueAlSoltarCuatroFichasEnColumna1VaciaCompletaLaFila() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");

		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);

		Assert.assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(1, 1));
		Assert.assertEquals(Casillero.AZUL, juego.obtenerCasillero(2, 1));
		Assert.assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(3, 1));
		Assert.assertEquals(Casillero.AZUL, juego.obtenerCasillero(4, 1));

	}

	@Test
	public void PrueboQueAlSoltarTresFichasEnColumna1VaciaCaeEnLasTresUltimasFilas() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);

		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(1, 1));
		Assert.assertEquals(Casillero.AZUL, juego.obtenerCasillero(2, 1));
		Assert.assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(3, 1));
		Assert.assertEquals(Casillero.AZUL, juego.obtenerCasillero(4, 1));

	}

	@Test
	public void PrueboQueAlSoltarDosFichasEnColumna1VaciaCompletaLaFila() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");
		juego.soltarFicha(1);
		juego.soltarFicha(1);

		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(1, 1));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(2, 1));
		Assert.assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(3, 1));
		Assert.assertEquals(Casillero.AZUL, juego.obtenerCasillero(4, 1));

	}

	@Test
	public void PrueboQueAlSoltarFichaEnColumna1VaciaCaeEnLaultimaFilaDeLaColumnaUno() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");
		juego.soltarFicha(1);

		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(1, 1));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(2, 1));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(3, 1));
		Assert.assertEquals(Casillero.AZUL, juego.obtenerCasillero(4, 1));

	}

	@Test
	public void PrueboQueSiComienzaElAzulYCompletaUnaColumnaDeCuatroCasillerosElProximoTurnoEsElAZUL() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);

		Assert.assertEquals(Casillero.AZUL,
				juego.obtenerCasilleroDelJugadorConTurno());

	}

	@Test
	public void PrueboQueAlLlenarElTableroSinHacerCuatroEnLineaElJuegoTerminoYNoHayGanador() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");

		Assert.assertFalse(juego.termino());

		for (int i = 1; i < 3; i++) {
			juego.soltarFicha(1);
			juego.soltarFicha(2);
			juego.soltarFicha(3);
			juego.soltarFicha(4);
		}

		for (int i = 1; i < 3; i++) {
			juego.soltarFicha(4);
			juego.soltarFicha(3);
			juego.soltarFicha(2);
			juego.soltarFicha(1);
		}

		Assert.assertTrue(juego.termino());
		Assert.assertNull(juego.obtenerGanador());
	}

	@Test
	public void PrueboQueGanaElJugadorAzulHaciendoCuatroEnLineaDeFormaHorizontalEnUltimaFila() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(4);

		Assert.assertTrue(juego.hayGanador());
		Assert.assertEquals("Azul", juego.obtenerGanador());
		Assert.assertTrue(juego.termino());
	}

	@Test
	public void PrueboQueElJugadorAzulNoHaceCuatroEnLineaHorizontalEnLaUltimaFilaEntoncesNoHayGanador() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(1);

		Assert.assertFalse(juego.hayGanador());
		Assert.assertNull(juego.obtenerGanador());
		Assert.assertFalse(juego.termino());
	}

	@Test
	public void PrueboQueGanaElJugadorAzulHaciendoCuatroEnLineaDeFormaVerticalEnLaPrimerColumna() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(3);
		juego.soltarFicha(1);
		juego.soltarFicha(4);
		juego.soltarFicha(1);

		Assert.assertTrue(juego.hayGanador());
		Assert.assertEquals("Azul", juego.obtenerGanador());
		Assert.assertTrue(juego.termino());
	}

	@Test
	public void PrueboQueNoGanaElJugadorAZULPorqueNoHaceCuatroEnLineaDeFormaVerticalEnLaPrimerColumna() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");
		juego.soltarFicha(1);
		juego.soltarFicha(3);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(1);

		Assert.assertFalse(juego.hayGanador());
		Assert.assertNull(juego.obtenerGanador());
		Assert.assertFalse(juego.termino());
	}

	@Test
	public void PrueboQueGanaElJugadorAzulHaciendoCuatroEnLineaDiagonalEmpezandoElPrimeroPorAbajo() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(4);
		juego.soltarFicha(4);

		Assert.assertTrue(juego.hayGanador());
		Assert.assertEquals("Azul", juego.obtenerGanador());
		Assert.assertTrue(juego.termino());
	}

	@Test
	public void PrueboQueGanaElJugadorAmarilloHaciendoCuatroEnLineaDiagonalEmpezandoElPrimeroPorArriba() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(4);

		Assert.assertTrue(juego.hayGanador());
		Assert.assertEquals("Amarillo", juego.obtenerGanador());
		Assert.assertTrue(juego.termino());
	}

	@Test(expected = Error.class)
	public void PrueboErrorSoltarFichaFueraDeRangoMatriz4x4DevuelveUnError() {
		// creo un tablero de 4x4, suelto 4 fichas, y al intentar soltar la
		// quinta tira error

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");

		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);

		Assert.assertEquals(1, juego.obtenerContadorErrorCasilleroLleno());

	}

	@Test(expected = Error.class)
	public void PrueboErrorSoltarFichaFueraDeRangoMatriz4x4Devuelve4Errores() {
		// creo un tablero de 4x4, suelto 4 fichas, y al intentar soltar la
		// quinta tira error

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Azul", "Amarillo");

		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);

		Assert.assertEquals(4, juego.obtenerContadorErrorCasilleroLleno());
	}

}
