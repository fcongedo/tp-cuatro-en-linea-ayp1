package juego;

import org.junit.Assert;
import org.junit.Test;

public class CuatroEnLineaTest {

	@Test(expected = Error.class)
	public void PrueboErrorAlCrearJuegoConValorFilaNegativo() {

		new CuatroEnLinea(-7, 5, "Rojo", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboErrorAlCrearJuegoConValorColumnaNegativo() {

		new CuatroEnLinea(7, -7, "Rojo", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboErrorAlCrearJuegoConValorFilaYColumnaNegativo() {

		new CuatroEnLinea(-7, -7, "Rojo", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboErrorAlCrearJuegoConValorUnoEnFilaYColumna() {

		new CuatroEnLinea(1, 1, "Rojo", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboErrorAlCrearJuegoConValorUnoEnFila() {

		new CuatroEnLinea(1, 4, "Rojo", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboErrorAlCrearJuegoConValorUnoColumna() {

		new CuatroEnLinea(4, 1, "Rojo", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboElErrorsiSeCreaUnJuegoConTreFilasYCuatroColumnasDaError() {

		new CuatroEnLinea(3, 4, "Rojo", "Amarillo");
	}

	@Test(expected = Error.class)
	public void PrueboElErrorsiSeCreaUnJuegoConCuatroFilasYTresColumnasDaError() {

		new CuatroEnLinea(4, 3, "Rojo", "Amarillo");
	}

	@Test
	public void alComenzarElJuegoComprueboQueNoHayGanador() {
		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");

		Assert.assertFalse(juego.hayGanador());
		Assert.assertNull(juego.obtenerGanador());
	}

	@Test
	public void ComprueboLaCorrectaCreacionDeElTableroDeTamañoCuatroXCuatro() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");

		Assert.assertEquals(4, juego.contarFilas());
		Assert.assertEquals(4, juego.contarColumnas());
	}

	@Test
	public void ComprueboLaCorrectaCreacionDeElTableroDeTamañoCuarentaXTrenta() {

		CuatroEnLinea juego = new CuatroEnLinea(40, 30, "Rojo", "Amarillo");

		Assert.assertEquals(40, juego.contarFilas());
		Assert.assertEquals(30, juego.contarColumnas());
	}

	@Test
	public void ComprueboQueAlCrearElJuegoDichoJuegoNoTermino() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");

		Assert.assertFalse(juego.termino());
	}

	@Test
	public void PrueboQueAlCrearElJuegoElTableroSeCreaVacio() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");

		for (int i = 1; i <= juego.contarFilas(); i++) {
			for (int j = 1; j <= juego.contarColumnas(); j++) {
				Assert.assertEquals(Casillero.VACIO,
						juego.obtenerCasillero(i, j));
			}
		}
	}

	@Test
	public void PrueboQueAlSoltarCuatroFichasEnColumna1VaciaCompletaLaFila() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);

		Assert.assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(1, 1));
		Assert.assertEquals(Casillero.ROJO, juego.obtenerCasillero(2, 1));
		Assert.assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(3, 1));
		Assert.assertEquals(Casillero.ROJO, juego.obtenerCasillero(4, 1));

	}

	@Test
	public void PrueboQueAlSoltarTresFichasEnColumna1VaciaCaeEnLasTresUltimasFilas() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);

		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(1, 1));
		Assert.assertEquals(Casillero.ROJO, juego.obtenerCasillero(2, 1));
		Assert.assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(3, 1));
		Assert.assertEquals(Casillero.ROJO, juego.obtenerCasillero(4, 1));

	}

	@Test
	public void PrueboQueAlSoltarDosFichasEnColumna1VaciaCompletaLaFila() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");
		juego.soltarFicha(1);
		juego.soltarFicha(1);

		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(1, 1));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(2, 1));
		Assert.assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(3, 1));
		Assert.assertEquals(Casillero.ROJO, juego.obtenerCasillero(4, 1));

	}

	@Test
	public void PrueboQueAlSoltarFichaEnColumna1VaciaCaeEnLaultimaFilaDeLaColumnaUno() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");
		juego.soltarFicha(1);

		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(1, 1));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(2, 1));
		Assert.assertEquals(Casillero.VACIO, juego.obtenerCasillero(3, 1));
		Assert.assertEquals(Casillero.ROJO, juego.obtenerCasillero(4, 1));

	}

	@Test
	public void PrueboQueSiComienzaElRojoYCompletaUnaColumnaDeCuatroCasillerosElProximoTurnoEsElRojo() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(2);

		Assert.assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(1, 1));
		Assert.assertEquals(Casillero.ROJO, juego.obtenerCasillero(2, 1));
		Assert.assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(3, 1));
		Assert.assertEquals(Casillero.ROJO, juego.obtenerCasillero(4, 1));
		Assert.assertEquals(Casillero.ROJO, juego.obtenerCasillero(4, 2));

	}

	@Test
	public void PrueboQueSiComienzaElRojoYCompletaUnaColumnaDeCincoCasillerosElProximoTurnoEsElAmarillo() {

		CuatroEnLinea juego = new CuatroEnLinea(5, 4, "Rojo", "Amarillo");
		
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(2);

		Assert.assertEquals(Casillero.ROJO, juego.obtenerCasillero(1, 1));
		Assert.assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(2, 1));
		Assert.assertEquals(Casillero.ROJO, juego.obtenerCasillero(3, 1));
		Assert.assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(4, 1));
		Assert.assertEquals(Casillero.ROJO, juego.obtenerCasillero(5, 1));
		Assert.assertEquals(Casillero.AMARILLO, juego.obtenerCasillero(5, 2));

	}

	@Test
	public void PrueboQueAlLlenarElTableroSinHacerCuatroEnLineaElJuegoTerminoYNoHayGanador() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");

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
	public void PrueboQueGanaElJugadorRojoHaciendoCuatroEnLineaDeFormaHorizontalEnUltimaFila() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");
		juego.soltarFicha(1);
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(2);
		juego.soltarFicha(3);
		juego.soltarFicha(3);
		juego.soltarFicha(4);

		Assert.assertTrue(juego.hayGanador());
		Assert.assertEquals("Rojo", juego.obtenerGanador());
		Assert.assertTrue(juego.termino());
	}

	@Test
	public void PrueboQueElJugadorRojoNoHaceCuatroEnLineaHorizontalEnLaUltimaFilaEntoncesNoHayGanador() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");
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
	public void PrueboQueGanaElJugadorRojoHaciendoCuatroEnLineaDeFormaVerticalEnLaPrimerColumna() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");
		juego.soltarFicha(1);
		juego.soltarFicha(2);
		juego.soltarFicha(1);
		juego.soltarFicha(3);
		juego.soltarFicha(1);
		juego.soltarFicha(4);
		juego.soltarFicha(1);

		Assert.assertTrue(juego.hayGanador());
		Assert.assertEquals("Rojo", juego.obtenerGanador());
		Assert.assertTrue(juego.termino());
	}

	@Test
	public void PrueboQueNoGanaElJugadorRojoPorqueNoHaceCuatroEnLineaDeFormaVerticalEnLaPrimerColumna() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");
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
	public void PrueboQueGanaElJugadorRojoHaciendoCuatroEnLineaDiagonalEmpezandoElPrimeroPorAbajo() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");
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
		Assert.assertEquals("Rojo", juego.obtenerGanador());
		Assert.assertTrue(juego.termino());
	}

	@Test
	public void PrueboQueGanaElJugadorAmarilloHaciendoCuatroEnLineaDiagonalEmpezandoElPrimeroPorArriba() {

		CuatroEnLinea juego = new CuatroEnLinea(4, 4, "Rojo", "Amarillo");
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
}
