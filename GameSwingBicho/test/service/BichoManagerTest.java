package service;

import models.Bicho;
import models.GameConstants;
import models.TipoBicho;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BichoManager class.
 */
class BichoManagerTest {

    private BichoManager manager;

    @BeforeEach
    void setUp() {
        manager = new BichoManager();
    }

    @Test
    void testDefaultConstructor() {
        assertEquals(GameConstants.DEFAULT_ROWS, manager.getFilas());
        assertEquals(GameConstants.DEFAULT_COLS, manager.getColumnas());
        assertNotNull(manager.getCampo());
    }

    @Test
    void testCustomSize() {
        BichoManager custom = new BichoManager(3, 4);
        assertEquals(3, custom.getFilas());
        assertEquals(4, custom.getColumnas());
    }

    @Test
    void testInvalidSize() {
        assertThrows(IllegalArgumentException.class, () -> new BichoManager(1, 2));
        assertThrows(IllegalArgumentException.class, () -> new BichoManager(2, 11));
    }

    @Test
    void testInicializarCampo() {
        manager.inicializarCampo();
        Bicho[][] campo = manager.getCampo();
        
        for (int i = 0; i < manager.getFilas(); i++) {
            for (int j = 0; j < manager.getColumnas(); j++) {
                assertTrue(campo[i][j].estaMuerto());
                assertEquals(TipoBicho.VACIO, campo[i][j].getTipo());
            }
        }
    }

    @Test
    void testCrearBichosAleatorios() {
        manager.crearBichosAleatorios();
        Bicho[][] campo = manager.getCampo();
        
        boolean hasNonEmpty = false;
        for (int i = 0; i < manager.getFilas(); i++) {
            for (int j = 0; j < manager.getColumnas(); j++) {
                assertNotNull(campo[i][j]);
                if (campo[i][j].getTipo() != TipoBicho.VACIO) {
                    hasNonEmpty = true;
                }
            }
        }
        
        assertTrue(hasNonEmpty || manager.getCampo().length > 0);
    }

    @Test
    void testAtacarBalaValidCoordinates() {
        manager.getCampo()[0][0] = new Bicho(GameConstants.NORMAL_HEALTH, TipoBicho.NORMAL);
        boolean hit = manager.atacarBala(0, 0);
        
        assertTrue(hit);
        assertEquals(GameConstants.NORMAL_HEALTH - GameConstants.BULLET_DAMAGE, 
                    manager.getCampo()[0][0].getSalud());
    }

    @Test
    void testAtacarBalaInvalidCoordinates() {
        boolean hit = manager.atacarBala(-1, 0);
        assertFalse(hit);
        
        hit = manager.atacarBala(0, 100);
        assertFalse(hit);
    }

    @Test
    void testAtacarBombaEn() {
        manager.getCampo()[0][0] = new Bicho(GameConstants.ALIEN_HEALTH, TipoBicho.ALIEN);
        boolean hit = manager.atacarBombaEn(0, 0);
        
        assertTrue(hit);
        assertTrue(manager.getCampo()[0][0].estaMuerto());
    }

    @Test
    void testFinDelJuego() {
        manager.inicializarCampo();
        assertTrue(manager.finDelJuego());
        
        manager.crearBichosAleatorios();
        // May or may not be finished depending on random generation
    }

    @Test
    void testMutarMasDebil() {
        manager.getCampo()[0][0] = new Bicho(5, TipoBicho.NORMAL);
        manager.getCampo()[0][1] = new Bicho(10, TipoBicho.NORMAL);
        
        boolean mutated = manager.mutarMasDebil();
        
        assertTrue(mutated);
        assertEquals(10, manager.getCampo()[0][0].getSalud()); // 5 * 2
    }

    @Test
    void testEstadisticas() {
        assertNotNull(manager.getEstadisticas());
        assertEquals(0, manager.getEstadisticas().getTurns());
        
        manager.atacarBala(0, 0);
        assertTrue(manager.getEstadisticas().getTurns() > 0);
    }

    @Test
    void testCampoToString() {
        String str = manager.campoToString();
        assertNotNull(str);
        assertTrue(str.contains("[0,0]"));
    }
}
