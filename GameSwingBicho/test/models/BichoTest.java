package models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Bicho class.
 */
class BichoTest {

    private Bicho normalBicho;
    private Bicho alienBicho;

    @BeforeEach
    void setUp() {
        normalBicho = new Bicho(GameConstants.NORMAL_HEALTH, TipoBicho.NORMAL);
        alienBicho = new Bicho(GameConstants.ALIEN_HEALTH, TipoBicho.ALIEN);
    }

    @Test
    void testDefaultConstructor() {
        Bicho bicho = new Bicho();
        assertEquals(0, bicho.getSalud());
        assertEquals(TipoBicho.VACIO, bicho.getTipo());
        assertTrue(bicho.estaMuerto());
    }

    @Test
    void testConstructorWithValues() {
        assertEquals(GameConstants.NORMAL_HEALTH, normalBicho.getSalud());
        assertEquals(TipoBicho.NORMAL, normalBicho.getTipo());
        assertFalse(normalBicho.estaMuerto());
    }

    @Test
    void testConstructorRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Bicho(10, null));
    }

    @Test
    void testNegativeHealthClamped() {
        Bicho bicho = new Bicho(-5, TipoBicho.NORMAL);
        assertEquals(0, bicho.getSalud());
        assertTrue(bicho.estaMuerto());
    }

    @Test
    void testRecibirBala() {
        int initialHealth = normalBicho.getSalud();
        boolean hit = normalBicho.recibirBala();
        
        assertTrue(hit);
        assertEquals(initialHealth - GameConstants.BULLET_DAMAGE, normalBicho.getSalud());
    }

    @Test
    void testRecibirBalaOnDeadBicho() {
        Bicho dead = new Bicho(0, TipoBicho.VACIO);
        boolean hit = dead.recibirBala();
        
        assertFalse(hit);
        assertEquals(0, dead.getSalud());
    }

    @Test
    void testRecibirBomba() {
        boolean hit = alienBicho.recibirBomba();
        
        assertTrue(hit);
        assertTrue(alienBicho.estaMuerto());
        assertEquals(0, alienBicho.getSalud());
    }

    @Test
    void testMutar() {
        int initialHealth = normalBicho.getSalud();
        boolean mutated = normalBicho.mutar();
        
        assertTrue(mutated);
        assertEquals(initialHealth * GameConstants.MUTATION_MULTIPLIER, normalBicho.getSalud());
    }

    @Test
    void testMutarDeadBicho() {
        Bicho dead = new Bicho(0, TipoBicho.VACIO);
        boolean mutated = dead.mutar();
        
        assertFalse(mutated);
        assertEquals(0, dead.getSalud());
    }

    @Test
    void testToString() {
        assertEquals("NORMAL-" + GameConstants.NORMAL_HEALTH, normalBicho.toString());
        
        normalBicho.recibirBomba();
        assertEquals("NORMAL-X", normalBicho.toString());
    }

    @Test
    void testEquals() {
        Bicho another = new Bicho(GameConstants.NORMAL_HEALTH, TipoBicho.NORMAL);
        assertEquals(normalBicho, another);
        
        another.recibirBala();
        assertNotEquals(normalBicho, another);
    }

    @Test
    void testHashCode() {
        Bicho another = new Bicho(GameConstants.NORMAL_HEALTH, TipoBicho.NORMAL);
        assertEquals(normalBicho.hashCode(), another.hashCode());
    }
}
