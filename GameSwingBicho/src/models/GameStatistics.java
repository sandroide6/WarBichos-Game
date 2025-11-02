package models;

/**
 * Tracks game statistics and player performance metrics.
 * <p>
 * This class maintains comprehensive statistics about a game session,
 * including turns played, accuracy, points scored, and attack history.
 * </p>
 *
 * @author Portfolio Project
 * @version 1.0.0
 * @since 2025-11-01
 */
public class GameStatistics {
    
    private int turns;
    private int shotsFired;
    private int shotsHit;
    private int bombsUsed;
    private int mutationsPerformed;
    private int totalPoints;
    private int bichosDefeated;

    /**
     * Constructs a new GameStatistics with all counters initialized to zero.
     */
    public GameStatistics() {
        this.turns = 0;
        this.shotsFired = 0;
        this.shotsHit = 0;
        this.bombsUsed = 0;
        this.mutationsPerformed = 0;
        this.totalPoints = 0;
        this.bichosDefeated = 0;
    }

    /**
     * Increments the turn counter.
     */
    public void incrementTurns() {
        this.turns++;
    }

    /**
     * Records a bullet shot and whether it hit a living bicho.
     *
     * @param hit true if the shot hit a living bicho, false otherwise
     */
    public void recordShot(boolean hit) {
        this.shotsFired++;
        if (hit) {
            this.shotsHit++;
        }
    }

    /**
     * Records that a bomb was used.
     */
    public void recordBomb() {
        this.bombsUsed++;
    }

    /**
     * Records that a mutation was performed.
     */
    public void recordMutation() {
        this.mutationsPerformed++;
    }

    /**
     * Records that a bicho was defeated and awards points.
     *
     * @param tipo the type of bicho that was defeated
     */
    public void recordDefeat(TipoBicho tipo) {
        this.bichosDefeated++;
        switch (tipo) {
            case NORMAL:
                this.totalPoints += GameConstants.POINTS_NORMAL;
                break;
            case ALIEN:
                this.totalPoints += GameConstants.POINTS_ALIEN;
                break;
            default:
                break;
        }
    }

    /**
     * Calculates the accuracy percentage of shots fired.
     *
     * @return accuracy as a percentage (0-100), or 0 if no shots fired
     */
    public double getAccuracy() {
        return shotsFired == 0 ? 0.0 : (shotsHit * 100.0) / shotsFired;
    }

    /**
     * Generates a formatted statistics report.
     *
     * @return a multi-line string containing all statistics
     */
    public String getReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n╔══════════════════════════════╗\n");
        sb.append("║   ESTADÍSTICAS DEL JUEGO    ║\n");
        sb.append("╠══════════════════════════════╣\n");
        sb.append(String.format("║ Turnos jugados:    %9d ║\n", turns));
        sb.append(String.format("║ Disparos:          %9d ║\n", shotsFired));
        sb.append(String.format("║ Aciertos:          %9d ║\n", shotsHit));
        sb.append(String.format("║ Precisión:         %8.1f%% ║\n", getAccuracy()));
        sb.append(String.format("║ Bombas usadas:     %9d ║\n", bombsUsed));
        sb.append(String.format("║ Mutaciones:        %9d ║\n", mutationsPerformed));
        sb.append(String.format("║ Bichos eliminados: %9d ║\n", bichosDefeated));
        sb.append(String.format("║ Puntos totales:    %9d ║\n", totalPoints));
        sb.append("╚══════════════════════════════╝\n");
        return sb.toString();
    }

    // Getters

    public int getTurns() {
        return turns;
    }

    public int getShotsFired() {
        return shotsFired;
    }

    public int getShotsHit() {
        return shotsHit;
    }

    public int getBombsUsed() {
        return bombsUsed;
    }

    public int getMutationsPerformed() {
        return mutationsPerformed;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getBichosDefeated() {
        return bichosDefeated;
    }
}
