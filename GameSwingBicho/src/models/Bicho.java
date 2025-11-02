package models;

/**
 * Represents a bicho (creature) in the game with health and type.
 * <p>
 * A bicho can receive damage from attacks, mutate to increase health,
 * and tracks whether it's alive or dead. This class is immutable in type
 * but mutable in health state.
 * </p>
 *
 * @author Portfolio Project
 * @version 1.0.0
 * @since 2025-11-01
 */
public class Bicho {
    
    private int salud;
    private final TipoBicho tipo;

    /**
     * Default constructor required for JSON deserialization.
     * Creates an empty (VACIO) bicho with zero health.
     */
    public Bicho() {
        this(0, TipoBicho.VACIO);
    }

    /**
     * Constructs a bicho with specified health and type.
     *
     * @param salud initial health points (negative values treated as 0)
     * @param tipo  the type of bicho (NORMAL, ALIEN, or VACIO)
     * @throws IllegalArgumentException if tipo is null
     */
    public Bicho(int salud, TipoBicho tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Bicho type cannot be null");
        }
        this.salud = Math.max(0, salud);
        this.tipo = tipo;
    }

    /**
     * Gets the current health of the bicho.
     *
     * @return current health points (0 or greater)
     */
    public int getSalud() {
        return salud;
    }

    /**
     * Gets the type of this bicho.
     *
     * @return the bicho type (immutable after construction)
     */
    public TipoBicho getTipo() {
        return tipo;
    }

    /**
     * Sets the health of the bicho.
     * Negative values are automatically clamped to 0.
     *
     * @param salud new health value
     */
    public void setSalud(int salud) {
        this.salud = Math.max(0, salud);
    }

    /**
     * Checks if the bicho is dead (health = 0).
     *
     * @return true if health is 0, false otherwise
     */
    public boolean estaMuerto() {
        return salud == 0;
    }

    /**
     * Applies bullet damage to this bicho.
     * Reduces health by {@value GameConstants#BULLET_DAMAGE} points.
     * Health cannot go below zero.
     *
     * @return true if the bicho was alive before the shot, false if already dead
     */
    public boolean recibirBala() {
        if (estaMuerto()) {
            return false;
        }
        setSalud(getSalud() - GameConstants.BULLET_DAMAGE);
        return true;
    }

    /**
     * Applies bomb damage to this bicho.
     * Instantly sets health to 0, killing the bicho.
     *
     * @return true if the bicho was alive before the bomb, false if already dead
     */
    public boolean recibirBomba() {
        if (estaMuerto()) {
            return false;
        }
        setSalud(0);
        return true;
    }

    /**
     * Mutates this bicho, multiplying its health.
     * Only applies if the bicho is currently alive.
     * Health is multiplied by {@value GameConstants#MUTATION_MULTIPLIER}.
     *
     * @return true if mutation was applied, false if bicho was dead
     */
    public boolean mutar() {
        if (getSalud() > 0) {
            setSalud(getSalud() * GameConstants.MUTATION_MULTIPLIER);
            return true;
        }
        return false;
    }

    /**
     * Returns a string representation of this bicho.
     * <p>
     * Format:
     * <ul>
     *   <li>Dead: "TIPO-X" (e.g., "NORMAL-X")</li>
     *   <li>Alive: "TIPO-HEALTH" (e.g., "ALIEN-20")</li>
     * </ul>
     * </p>
     *
     * @return string representation of bicho state
     */
    @Override
    public String toString() {
        return estaMuerto() ? tipo + "-X" : (tipo + "-" + salud);
    }

    /**
     * Checks equality based on type and health.
     *
     * @param obj object to compare
     * @return true if same type and health
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Bicho)) return false;
        Bicho other = (Bicho) obj;
        return salud == other.salud && tipo == other.tipo;
    }

    /**
     * Generates hash code based on type and health.
     *
     * @return hash code value
     */
    @Override
    public int hashCode() {
        return 31 * tipo.hashCode() + salud;
    }
}
