package models;

/**
 * Enumeration of bicho types available in the game.
 * <p>
 * Each bicho type has different characteristics and provides
 * different point values when defeated.
 * </p>
 *
 * @author Portfolio Project
 * @version 1.0.0
 * @since 2025-11-01
 */
public enum TipoBicho {
    
    /**
     * Normal bicho with standard health.
     * Default health: {@value GameConstants#NORMAL_HEALTH}
     */
    NORMAL,
    
    /**
     * Alien bicho with higher health.
     * Default health: {@value GameConstants#ALIEN_HEALTH}
     */
    ALIEN,
    
    /**
     * Empty cell with no bicho present.
     * Health: 0
     */
    VACIO
}
