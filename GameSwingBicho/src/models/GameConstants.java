package models;

/**
 * Centralized game constants and configuration values.
 * <p>
 * This class contains all magic numbers and game configuration
 * parameters, making the codebase more maintainable and allowing
 * easy difficulty adjustments.
 * </p>
 *
 * @author Portfolio Project
 * @version 1.0.0
 * @since 2025-11-01
 */
public final class GameConstants {

    private GameConstants() {
        throw new AssertionError("Constants class should not be instantiated");
    }

    /**
 * Default health for NORMAL type bichos.
     */
    public static final int NORMAL_HEALTH = 10;

    /**
     * Default health for ALIEN type bichos.
     */
    public static final int ALIEN_HEALTH = 20;

    /**
     * Damage dealt by a single bullet attack.
     */
    public static final int BULLET_DAMAGE = 5;

    /**
     * Multiplier applied when a bicho mutates.
     */
    public static final int MUTATION_MULTIPLIER = 2;

    /**
     * Default number of rows in the game board.
     */
    public static final int DEFAULT_ROWS = 2;

    /**
     * Default number of columns in the game board.
     */
    public static final int DEFAULT_COLS = 2;

    /**
     * Minimum board dimension allowed.
     */
    public static final int MIN_BOARD_SIZE = 2;

    /**
     * Maximum board dimension allowed.
     */
    public static final int MAX_BOARD_SIZE = 10;

    /**
     * Name of the save file for game persistence.
     */
    public static final String SAVE_FILE = "partida.json";

    /**
     * Points awarded for defeating a NORMAL bicho.
     */
    public static final int POINTS_NORMAL = 10;

    /**
     * Points awarded for defeating an ALIEN bicho.
     */
    public static final int POINTS_ALIEN = 20;
}
