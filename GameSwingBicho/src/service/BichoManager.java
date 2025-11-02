package service;

import com.google.gson.Gson;
import models.Bicho;
import models.GameConstants;
import models.GameStatistics;
import models.TipoBicho;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

/**
 * Core game manager handling all game logic and state.
 * <p>
 * This class is the central controller for Guerra de Bichos, managing:
 * <ul>
 *   <li>Game board state and bicho placement</li>
 *   <li>Attack mechanics (bullets and bombs)</li>
 *   <li>Mutations and special abilities</li>
 *   <li>Game statistics and scoring</li>
 *   <li>Persistence (save/load functionality)</li>
 * </ul>
 * </p>
 *
 * <p><strong>Usage Example:</strong></p>
 * <pre>{@code
 * BichoManager manager = new BichoManager(3, 3);
 * manager.crearBichosAleatorios();
 * manager.atacarBala(0, 0);
 * if (manager.finDelJuego()) {
 *     System.out.println("Victory!");
 * }
 * }</pre>
 *
 * @author Portfolio Project
 * @version 1.0.0
 * @since 2025-11-01
 */
public class BichoManager {

    private final int FILAS;
    private final int COLUMNAS;
    private Bicho[][] campo;
    private final Random random;
    private final GameStatistics estadisticas;

    /**
     * Constructs a BichoManager with default 2x2 board size.
     */
    public BichoManager() {
        this(GameConstants.DEFAULT_ROWS, GameConstants.DEFAULT_COLS);
    }

    /**
     * Constructs a BichoManager with custom board dimensions.
     *
     * @param filas    number of rows (must be between MIN and MAX board size)
     * @param columnas number of columns (must be between MIN and MAX board size)
     * @throws IllegalArgumentException if dimensions are out of valid range
     */
    public BichoManager(int filas, int columnas) {
        validateBoardSize(filas, columnas);
        this.FILAS = filas;
        this.COLUMNAS = columnas;
        this.campo = new Bicho[FILAS][COLUMNAS];
        this.random = new Random();
        this.estadisticas = new GameStatistics();
        inicializarCampo();
    }

    /**
     * Validates board dimensions are within acceptable range.
     *
     * @param filas    number of rows
     * @param columnas number of columns
     * @throws IllegalArgumentException if dimensions are invalid
     */
    private void validateBoardSize(int filas, int columnas) {
        if (filas < GameConstants.MIN_BOARD_SIZE || filas > GameConstants.MAX_BOARD_SIZE) {
            throw new IllegalArgumentException(
                String.format("Rows must be between %d and %d", 
                    GameConstants.MIN_BOARD_SIZE, GameConstants.MAX_BOARD_SIZE));
        }
        if (columnas < GameConstants.MIN_BOARD_SIZE || columnas > GameConstants.MAX_BOARD_SIZE) {
            throw new IllegalArgumentException(
                String.format("Columns must be between %d and %d", 
                    GameConstants.MIN_BOARD_SIZE, GameConstants.MAX_BOARD_SIZE));
        }
    }

    /**
     * Initializes the board with empty cells.
     * All cells are set to VACIO type with zero health.
     */
    public void inicializarCampo() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                campo[i][j] = new Bicho(0, TipoBicho.VACIO);
            }
        }
    }

    /**
     * Creates random bichos across the board.
     * <p>
     * Each cell has an equal 1/3 probability of being:
     * <ul>
     *   <li>VACIO (empty)</li>
     *   <li>NORMAL (health: {@value GameConstants#NORMAL_HEALTH})</li>
     *   <li>ALIEN (health: {@value GameConstants#ALIEN_HEALTH})</li>
     * </ul>
     * </p>
     */
    public void crearBichosAleatorios() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                int n = random.nextInt(3);
                switch (n) {
                    case 1:
                        campo[i][j] = new Bicho(GameConstants.NORMAL_HEALTH, TipoBicho.NORMAL);
                        break;
                    case 2:
                        campo[i][j] = new Bicho(GameConstants.ALIEN_HEALTH, TipoBicho.ALIEN);
                        break;
                    default:
                        campo[i][j] = new Bicho(0, TipoBicho.VACIO);
                        break;
                }
            }
        }
    }

    /**
     * Attacks a specific cell with a bullet.
     * <p>
     * Bullets deal {@value GameConstants#BULLET_DAMAGE} damage.
     * Coordinates are validated before attacking.
     * </p>
     *
     * @param fila row coordinate (0-indexed)
     * @param col  column coordinate (0-indexed)
     * @return true if attack hit a living bicho, false otherwise
     */
    public boolean atacarBala(int fila, int col) {
        estadisticas.incrementTurns();
        
        if (!coordenadasValidas(fila, col)) {
            estadisticas.recordShot(false);
            return false;
        }
        
        Bicho b = campo[fila][col];
        boolean wasAlive = !b.estaMuerto();
        boolean hit = b.recibirBala();
        
        estadisticas.recordShot(hit);
        
        if (wasAlive && b.estaMuerto()) {
            estadisticas.recordDefeat(b.getTipo());
        }
        
        return hit;
    }

    /**
     * Attacks a random cell with a bomb.
     * <p>
     * Bombs instantly kill the targeted bicho.
     * The target is selected randomly from all board cells.
     * </p>
     *
     * @return true if bomb hit a living bicho, false otherwise
     */
    public boolean atacarBombaAleatoria() {
        int f = random.nextInt(FILAS);
        int c = random.nextInt(COLUMNAS);
        return atacarBombaEn(f, c);
    }

    /**
     * Attacks a specific cell with a bomb.
     * <p>
     * Bombs instantly kill the targeted bicho.
     * Coordinates are validated before attacking.
     * </p>
     *
     * @param fila row coordinate (0-indexed)
     * @param col  column coordinate (0-indexed)
     * @return true if bomb hit a living bicho, false otherwise
     */
    public boolean atacarBombaEn(int fila, int col) {
        estadisticas.incrementTurns();
        estadisticas.recordBomb();
        
        if (!coordenadasValidas(fila, col)) {
            return false;
        }
        
        Bicho b = campo[fila][col];
        boolean wasAlive = !b.estaMuerto();
        boolean hit = b.recibirBomba();
        
        if (wasAlive && b.estaMuerto()) {
            estadisticas.recordDefeat(b.getTipo());
        }
        
        return hit;
    }

    /**
     * Validates that coordinates are within board bounds.
     *
     * @param fila row coordinate
     * @param col  column coordinate
     * @return true if coordinates are valid, false otherwise
     */
    private boolean coordenadasValidas(int fila, int col) {
        return fila >= 0 && fila < FILAS && col >= 0 && col < COLUMNAS;
    }

    /**
     * Checks if the game has ended.
     * <p>
     * The game ends when all bichos on the board are dead
     * (health = 0 or type = VACIO).
     * </p>
     *
     * @return true if all bichos are dead, false otherwise
     */
    public boolean finDelJuego() {
        int muertos = 0;
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (campo[i][j].estaMuerto()) {
                    muertos++;
                }
            }
        }
        return muertos == FILAS * COLUMNAS;
    }

    /**
     * Mutates the weakest living bicho.
     * <p>
     * Finds the bicho with the lowest health (must be > 0) and
     * multiplies its health by {@value GameConstants#MUTATION_MULTIPLIER}.
     * If multiple bichos have the same minimum health, one is chosen arbitrarily.
     * </p>
     *
     * @return true if a mutation was performed, false if no living bichos exist
     */
    public boolean mutarMasDebil() {
        Bicho masDebil = null;
        int min = Integer.MAX_VALUE;
        
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                Bicho b = campo[i][j];
                if (!b.estaMuerto() && b.getSalud() < min) {
                    min = b.getSalud();
                    masDebil = b;
                }
            }
        }
        
        if (masDebil != null) {
            masDebil.mutar();
            estadisticas.recordMutation();
            return true;
        }
        
        return false;
    }

    /**
     * Gets a reference to the game board.
     * <p>
     * <strong>Warning:</strong> This returns a direct reference to the internal
     * array. Modifications will affect the game state.
     * </p>
     *
     * @return 2D array representing the game board
     */
    public Bicho[][] getCampo() {
        return campo;
    }

    /**
     * Replaces the current game board with a new one.
     * <p>
     * The new board must match the configured dimensions.
     * This method is primarily used for loading saved games.
     * </p>
     *
     * @param nuevoCampo new game board
     * @return true if board was set, false if dimensions don't match
     */
    public boolean setCampo(Bicho[][] nuevoCampo) {
        if (nuevoCampo != null && 
            nuevoCampo.length == FILAS && 
            nuevoCampo[0].length == COLUMNAS) {
            this.campo = nuevoCampo;
            return true;
        }
        return false;
    }

    /**
     * Gets the current game statistics.
     *
     * @return statistics object tracking game metrics
     */
    public GameStatistics getEstadisticas() {
        return estadisticas;
    }

    /**
     * Gets the number of rows in the board.
     *
     * @return row count
     */
    public int getFilas() {
        return FILAS;
    }

    /**
     * Gets the number of columns in the board.
     *
     * @return column count
     */
    public int getColumnas() {
        return COLUMNAS;
    }

    /**
     * Saves the current game state to a JSON file.
     * <p>
     * The game board is serialized to {@value GameConstants#SAVE_FILE}
     * using Gson. Any existing save file is overwritten.
     * </p>
     *
     * @throws IOException if file cannot be written
     */
    public void guardarPartida() throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(campo);
        Files.writeString(Path.of(GameConstants.SAVE_FILE), json);
    }

    /**
     * Loads a previously saved game from a JSON file.
     * <p>
     * Attempts to read from {@value GameConstants#SAVE_FILE} and
     * deserialize the game board. The loaded board must match
     * the current board dimensions.
     * </p>
     *
     * @return true if game was loaded successfully, false if no save exists
     *         or if save file dimensions don't match
     * @throws IOException if file exists but cannot be read
     */
    public boolean cargarPartida() throws IOException {
        Path p = Path.of(GameConstants.SAVE_FILE);
        if (!Files.exists(p)) {
            return false;
        }
        
        String json = Files.readString(p);
        Gson gson = new Gson();
        Bicho[][] loaded = gson.fromJson(json, Bicho[][].class);

        if (loaded != null && 
            loaded.length == FILAS && 
            loaded[0].length == COLUMNAS) {
            this.campo = loaded;
            return true;
        }
        
        return false;
    }

    /**
     * Generates a compact text representation of the game board.
     * <p>
     * Each cell shows its coordinates and bicho state in the format:
     * [row,col]=TYPE-HEALTH
     * </p>
     *
     * @return multi-line string representation of the board
     */
    public String campoToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                sb.append(String.format("[%d,%d]=%s  ", i, j, campo[i][j].toString()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
