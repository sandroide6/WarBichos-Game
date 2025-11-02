package ui;

import models.Bicho;
import models.GameConstants;
import service.BichoManager;

import java.io.IOException;
import java.util.Scanner;

/**
 * Console-based user interface for Guerra de Bichos.
 * <p>
 * Provides an interactive menu system with ANSI color support,
 * ASCII art, statistics display, and complete game controls.
 * </p>
 *
 * @author Portfolio Project
 * @version 1.0.0
 * @since 2025-11-01
 */
public class ConsolaJuego {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String BOLD = "\u001B[1m";

    /**
     * Main entry point for the console game.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        mostrarBienvenida();
        
        Scanner sc = new Scanner(System.in);
        BichoManager manager = configurarJuego(sc);
        
        int opcion;

        do {
            mostrarMenu();
            while (!sc.hasNextInt()) {
                sc.next();
                System.out.print(RED + "❌ Ingrese un número válido: " + RESET);
            }
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    manager.crearBichosAleatorios();
                    System.out.println(GREEN + "✅ Bichos creados aleatoriamente." + RESET);
                    mostrarCampoMejorado(manager);
                }
                case 2 -> mostrarCampoMejorado(manager);
                case 3 -> dispararBala(sc, manager);
                case 4 -> {
                    boolean hit = manager.atacarBombaAleatoria();
                    System.out.println(RED + "💣 ¡BOMBA lanzada!" + RESET);
                    if (hit) {
                        System.out.println(YELLOW + "💥 ¡Impacto directo!" + RESET);
                    } else {
                        System.out.println(CYAN + "📍 La bomba cayó en terreno vacío." + RESET);
                    }
                }
                case 5 -> {
                    boolean mutated = manager.mutarMasDebil();
                    if (mutated) {
                        System.out.println(CYAN + "🧬 ¡Mutación aplicada al bicho más débil!" + RESET);
                    } else {
                        System.out.println(YELLOW + "⚠ No hay bichos vivos para mutar." + RESET);
                    }
                }
                case 6 -> {
                    try {
                        manager.guardarPartida();
                        System.out.println(GREEN + "💾 Partida guardada en '" + 
                            GameConstants.SAVE_FILE + "'." + RESET);
                    } catch (IOException e) {
                        System.out.println(RED + "❌ Error guardando partida: " + 
                            e.getMessage() + RESET);
                    }
                }
                case 7 -> {
                    try {
                        boolean ok = manager.cargarPartida();
                        if (ok) {
                            System.out.println(GREEN + "📂 Partida cargada exitosamente." + RESET);
                            mostrarCampoMejorado(manager);
                        } else {
                            System.out.println(YELLOW + "⚠ No existe '" + 
                                GameConstants.SAVE_FILE + "'." + RESET);
                        }
                    } catch (IOException e) {
                        System.out.println(RED + "❌ Error cargando partida: " + 
                            e.getMessage() + RESET);
                    }
                }
                case 8 -> mostrarEstadisticas(manager);
                case 9 -> {
                    System.out.println(MAGENTA + "\n¿Crear un nuevo juego? (Se perderá el progreso actual)" + RESET);
                    manager = configurarJuego(sc);
                    System.out.println(GREEN + "✨ Nuevo juego iniciado." + RESET);
                }
                case 0 -> {
                    mostrarEstadisticas(manager);
                    System.out.println(BOLD + CYAN + "\n👋 ¡Gracias por jugar Guerra de Bichos!" + RESET);
                    System.out.println(YELLOW + "   Desarrollado como proyecto de portafolio" + RESET);
                }
                default -> System.out.println(RED + "❌ Opción inválida." + RESET);
            }

            if (opcion != 0 && manager.finDelJuego()) {
                System.out.println(BOLD + RED + "\n🏆 ¡VICTORIA! Todos los bichos han sido eliminados." + RESET);
                mostrarCampoMejorado(manager);
                mostrarEstadisticas(manager);
                break;
            }

        } while (opcion != 0);

        sc.close();
    }

    /**
     * Displays the welcome banner with ASCII art.
     */
    private static void mostrarBienvenida() {
        System.out.println(BOLD + CYAN + """
        
        ╔═══════════════════════════════════════════════════╗
        ║                                                   ║
        ║     🐛  GUERRA DE BICHOS  🐛                      ║
        ║                                                   ║
        ║     Tactical Strategy Grid Game                  ║
        ║     v1.0.0 - Portfolio Project                   ║
        ║                                                   ║
        ╚═══════════════════════════════════════════════════╝
        """ + RESET);
    }

    /**
     * Configures initial game settings (board size).
     *
     * @param sc scanner for user input
     * @return configured BichoManager instance
     */
    private static BichoManager configurarJuego(Scanner sc) {
        System.out.println(BOLD + "\n⚙️  CONFIGURACIÓN DEL JUEGO" + RESET);
        System.out.println("\nSelecciona el tamaño del tablero:");
        System.out.println("1) 2x2 (Fácil)");
        System.out.println("2) 3x3 (Medio)");
        System.out.println("3) 4x4 (Difícil)");
        System.out.println("4) 5x5 (Experto)");
        System.out.println("5) Personalizado");
        System.out.print("Opción: ");
        
        int opcion = leerEntero(sc, 1, 5);
        
        int filas, columnas;
        switch (opcion) {
            case 1 -> { filas = 2; columnas = 2; }
            case 2 -> { filas = 3; columnas = 3; }
            case 3 -> { filas = 4; columnas = 4; }
            case 4 -> { filas = 5; columnas = 5; }
            default -> {
                System.out.print("Filas (2-10): ");
                filas = leerEntero(sc, GameConstants.MIN_BOARD_SIZE, GameConstants.MAX_BOARD_SIZE);
                System.out.print("Columnas (2-10): ");
                columnas = leerEntero(sc, GameConstants.MIN_BOARD_SIZE, GameConstants.MAX_BOARD_SIZE);
            }
        }
        
        System.out.println(GREEN + "\n✅ Tablero configurado: " + filas + "x" + columnas + RESET);
        return new BichoManager(filas, columnas);
    }

    /**
     * Reads an integer within a specified range.
     *
     * @param sc  scanner for input
     * @param min minimum valid value
     * @param max maximum valid value
     * @return validated integer
     */
    private static int leerEntero(Scanner sc, int min, int max) {
        while (true) {
            if (sc.hasNextInt()) {
                int valor = sc.nextInt();
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } else {
                sc.next();
            }
            System.out.print(RED + "❌ Ingrese un número entre " + min + " y " + max + ": " + RESET);
        }
    }

    /**
     * Displays the main game menu.
     */
    private static void mostrarMenu() {
        System.out.println(BOLD + "\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println(BOLD + "  MENÚ PRINCIPAL" + RESET);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("1) 🎲 Crear bichos aleatorios");
        System.out.println("2) 🗺️  Mostrar campo");
        System.out.println("3) 🔫 Disparar bala");
        System.out.println("4) 💣 Bomba aleatoria");
        System.out.println("5) 🧬 Mutar bicho más débil");
        System.out.println("6) 💾 Guardar partida");
        System.out.println("7) 📂 Cargar partida");
        System.out.println("8) 📊 Ver estadísticas");
        System.out.println("9) 🔄 Nuevo juego");
        System.out.println("0) 🚪 Salir");
        System.out.print(BOLD + "\nSelecciona: " + RESET);
    }

    /**
     * Handles bullet shooting interaction.
     *
     * @param sc      scanner for user input
     * @param manager game manager
     */
    private static void dispararBala(Scanner sc, BichoManager manager) {
        System.out.print("Fila (0-" + (manager.getFilas() - 1) + "): ");
        int f = leerEntero(sc, 0, manager.getFilas() - 1);
        System.out.print("Columna (0-" + (manager.getColumnas() - 1) + "): ");
        int c = leerEntero(sc, 0, manager.getColumnas() - 1);
        
        boolean hit = manager.atacarBala(f, c);
        
        if (hit) {
            System.out.println(YELLOW + "🎯 ¡Disparo certero en [" + f + "," + c + "]!" + RESET);
        } else {
            System.out.println(CYAN + "📍 Disparo falló - objetivo ya muerto o vacío." + RESET);
        }
    }

    /**
     * Displays improved board visualization with borders.
     *
     * @param manager game manager
     */
    private static void mostrarCampoMejorado(BichoManager manager) {
        Bicho[][] campo = manager.getCampo();
        int filas = campo.length;
        int cols = campo[0].length;
        
        System.out.println(BOLD + "\n┌─── CAMPO DE BATALLA ───┐" + RESET);
        
        for (int i = 0; i < filas; i++) {
            System.out.print("  ");
            for (int j = 0; j < cols; j++) {
                Bicho b = campo[i][j];
                String icono = obtenerIcono(b);
                String out = String.format("[%d,%d]%s%-12s", i, j, icono, b.toString());
                
                if (b.estaMuerto()) {
                    System.out.print(RED + out + RESET + "  ");
                } else {
                    System.out.print(GREEN + out + RESET + "  ");
                }
            }
            System.out.println();
        }
        
        System.out.println(BOLD + "└" + "─".repeat(30) + "┘" + RESET);
        System.out.println(GREEN + "🟢 = Vivo  " + RED + "🔴 = Muerto" + RESET);
    }

    /**
     * Gets emoji icon for a bicho.
     *
     * @param b bicho to get icon for
     * @return emoji string
     */
    private static String obtenerIcono(Bicho b) {
        if (b.estaMuerto()) {
            return "💀";
        }
        switch (b.getTipo()) {
            case NORMAL: return "🐛";
            case ALIEN: return "👽";
            default: return "⬜";
        }
    }

    /**
     * Displays current game statistics.
     *
     * @param manager game manager
     */
    private static void mostrarEstadisticas(BichoManager manager) {
        System.out.println(CYAN + manager.getEstadisticas().getReport() + RESET);
    }
}
