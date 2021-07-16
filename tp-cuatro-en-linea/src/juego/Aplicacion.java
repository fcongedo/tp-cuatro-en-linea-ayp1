package juego;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Aplicación del juego Cuatro en Lí­nea.
 * 
 * Punto de entrada del programa.
 * 
 */
public class Aplicacion extends Application {

	public static final String TITULO = "Cuatro en Línea\n  Versión Boca";

	private String mensajeBienvenida = "                                                                                   Bienvenidos a 4 en linea Versión Boca\n\nReglas: Uno de los jugadores coloca cuatro fichas en una línea contínua vertical, horizontal o diagonalmente. Este jugador gana la partida.\n\nTodas las casillas del tablero están ocupadas y ningún jugador cumple la condición anterior para ganar. En este caso la partida finaliza en empate.\n\nDimensiones del tablero: Maximo 8x8 Minimo 4x4";

	private GridPane grilla;

	private TextField campoNombreJugadorAzul;
	private TextField campoNombreJugadorAmarillo;

	private TextField campoColumnas;
	private TextField campoFilas;

	private Button botonIniciar;

	@Override
	public void start(Stage escenarioPrincipal) {

		crearGrilla();

		Scene escena = new Scene(grilla, 400, 300);
		escenarioPrincipal.setScene(escena);
		escenarioPrincipal.setTitle(TITULO);
		escenarioPrincipal.show();
	}

	private void crearGrilla() {

		grilla = new GridPane();
		grilla.setAlignment(Pos.CENTER);
		grilla.setHgap(20);
		grilla.setVgap(20);

		Text textoTitulo = new Text(TITULO);
		textoTitulo.setFont(new Font(16));

		crearControles();

		grilla.add(textoTitulo, 0, 0, 2, 1);
		grilla.add(new Label("Jugador Azul"), 0, 1);
		grilla.add(campoNombreJugadorAzul, 1, 1);
		grilla.add(new Label("Jugador Oro"), 0, 2);
		grilla.add(campoNombreJugadorAmarillo, 1, 2);
		grilla.add(new Label("Filas"), 0, 3);
		grilla.add(campoFilas, 1, 3);
		grilla.add(new Label("Columnas"), 0, 4);
		grilla.add(campoColumnas, 1, 4);
		grilla.add(botonIniciar, 0, 5, 2, 1);

		GridPane.setHalignment(botonIniciar, HPos.CENTER);
		GridPane.setHalignment(textoTitulo, HPos.CENTER);

		JOptionPane.showMessageDialog(null, mensajeBienvenida, "Info",
				JOptionPane.INFORMATION_MESSAGE);

	}

	private void crearControles() {

		campoNombreJugadorAzul = new TextField("");
		campoNombreJugadorAmarillo = new TextField("");

		campoColumnas = new TextField("8");
		campoFilas = new TextField("8");

		botonIniciar = new Button("Iniciar");
		botonIniciar.setOnAction(new IniciarJuego(this));

	}

	/**
	 * post: crea un juego CuatroEnLinea, lo asocia a una Tablero y comienza su
	 * ejecución.
	 * 
	 */
	public void iniciar() {

		String nombreJugadorAzul = campoNombreJugadorAzul.getText();
		String nombreJugadorAmarillo = campoNombreJugadorAmarillo.getText();
		int filas = Integer.parseInt(campoFilas.getText());
		int columnas = Integer.parseInt(campoColumnas.getText());

		CuatroEnLinea juego = new CuatroEnLinea(filas, columnas,
				nombreJugadorAzul, nombreJugadorAmarillo);

		Tablero tablero = new Tablero(juego);
		tablero.mostrar();

	}

	public static void main(String[] args) {

		launch(args);
	}

}
