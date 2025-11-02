# 🐛 Guerra de Bichos

[![Java CI](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/tu-usuario/guerra-de-bichos)
[![Java Version](https://img.shields.io/badge/Java-17%2B-orange)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Maven](https://img.shields.io/badge/Maven-3.8%2B-C71A36)](https://maven.apache.org/)
[![Tests](https://img.shields.io/badge/tests-24%20passing-brightgreen)](test/)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/tu-usuario/guerra-de-bichos/pulls)

> **Tactical grid-based strategy game demonstrating professional Java development practices**

Guerra de Bichos is a portfolio project showcasing clean architecture, comprehensive testing, and modern Java best practices. Players strategically eliminate creatures ("bichos") using various attack types and mutations on a configurable grid.

## ✨ Features

### 🎮 **Gameplay**
- **Multiple Board Sizes**: 2x2 to 10x10 grids with difficulty presets
- **Strategic Combat**: Bullet attacks, instant-kill bombs, and tactical mutations
- **Statistics Tracking**: Accuracy, points, turns, and performance metrics
- **Save/Load System**: JSON-based game state persistence
- **Dynamic Difficulty**: Configurable board size and bicho distribution

### 💻 **Technical Highlights**
- **Clean Architecture**: Separation of concerns (Models, Service, UI layers)
- **SOLID Principles**: Single responsibility, maintainable design
- **Comprehensive JavaDoc**: Complete documentation for all public APIs
- **Unit Tests**: 24 passing tests with JUnit 5
- **Build Automation**: Maven with CI/CD pipeline
- **Professional Standards**: Well-structured, production-ready code

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.8+ (optional, for building from source)

### Installation

```bash
# Clone the repository
git clone https://github.com/tu-usuario/guerra-de-bichos.git
cd guerra-de-bichos

# Compile with Maven
mvn clean compile

# Run the game
mvn exec:java -Dexec.mainClass="ui.ConsolaJuego"
```

### Alternative: Pre-compiled JAR

```bash
# Build JAR with dependencies
mvn clean package

# Run the JAR
java -jar target/guerra-de-bichos-1.0.0-jar-with-dependencies.jar
```

## 📖 How to Play

### 🎯 **Objective**
Eliminate all bichos on the board using strategic attacks and mutations.

### 🎲 **Game Setup**
1. Choose board size (2x2 Easy → 5x5 Expert, or Custom)
2. Generate random bichos on the board
3. Use various tactics to defeat them all

### ⚔️ **Combat Mechanics**

| Action | Effect | Use Case |
|--------|--------|----------|
| **🔫 Bullet** | -5 HP damage | Precise, controlled attacks |
| **💣 Bomb** | Instant kill | Quick elimination, random target |
| **🧬 Mutation** | 2x HP to weakest | Make enemies harder (strategic challenge) |

### 📊 **Bicho Types**

| Type | Icon | Initial HP | Points |
|------|------|------------|--------|
| **Normal** | 🐛 | 10 | 10 |
| **Alien** | 👽 | 20 | 20 |
| **Vacio** | ⬜ | 0 | 0 |

### 🎨 **Console Interface**

```
╔═══════════════════════════════════════════════════╗
║                                                   ║
║     🐛  GUERRA DE BICHOS  🐛                      ║
║                                                   ║
║     Tactical Strategy Grid Game                  ║
║     v1.0.0 - Portfolio Project                   ║
║                                                   ║
╚═══════════════════════════════════════════════════╝

┌─── CAMPO DE BATALLA ───┐
  [0,0]🐛NORMAL-10      [0,1]👽ALIEN-20     
  [1,0]⬜VACIO-0        [1,1]🐛NORMAL-10    
└──────────────────────────┘
🟢 = Vivo  🔴 = Muerto

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  MENÚ PRINCIPAL
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
1) 🎲 Crear bichos aleatorios
2) 🗺️  Mostrar campo
3) 🔫 Disparar bala
4) 💣 Bomba aleatoria
5) 🧬 Mutar bicho más débil
6) 💾 Guardar partida
7) 📂 Cargar partida
8) 📊 Ver estadísticas
9) 🔄 Nuevo juego
0) 🚪 Salir
```

### 📈 **Statistics**

```
╔══════════════════════════════╗
║   ESTADÍSTICAS DEL JUEGO    ║
╠══════════════════════════════╣
║ Turnos jugados:           15 ║
║ Disparos:                 12 ║
║ Aciertos:                 10 ║
║ Precisión:              83.3% ║
║ Bombas usadas:             2 ║
║ Mutaciones:                1 ║
║ Bichos eliminados:         4 ║
║ Puntos totales:           60 ║
╚══════════════════════════════╝
```

## 🏗️ Architecture

### **Project Structure**

```
guerra-de-bichos/
├── src/
│   ├── models/              # Domain entities
│   │   ├── Bicho.java       # Bicho entity with health/type
│   │   ├── TipoBicho.java   # Bicho type enumeration
│   │   ├── GameConstants.java    # Centralized constants
│   │   └── GameStatistics.java   # Statistics tracker
│   ├── service/             # Business logic
│   │   └── BichoManager.java     # Core game controller
│   └── ui/                  # User interface
│       └── ConsolaJuego.java     # Console UI with ANSI colors
├── test/                    # Unit tests
│   ├── models/
│   │   └── BichoTest.java
│   └── service/
│       └── BichoManagerTest.java
├── .github/
│   └── workflows/
│       └── ci.yml           # GitHub Actions CI/CD
├── pom.xml                  # Maven configuration
├── LICENSE                  # MIT License
├── CHANGELOG.md             # Version history
└── README.md                # This file
```

### **Design Patterns & Principles**

- **Model-View-Controller (MVC)**: Clear separation of data, logic, and presentation
- **Single Responsibility**: Each class has one clear purpose
- **Dependency Injection**: Testable, loosely coupled components
- **Immutability**: Constants and enums are final/immutable
- **Fail-Fast**: Input validation with meaningful exceptions

### **Class Diagram (Simplified)**

```
┌─────────────────┐
│   ConsolaJuego  │
│     (UI)        │
└────────┬────────┘
         │ uses
         ▼
┌─────────────────┐      ┌──────────────────┐
│  BichoManager   │─────>│ GameStatistics   │
│   (Service)     │      │    (Model)       │
└────────┬────────┘      └──────────────────┘
         │ manages
         ▼
┌─────────────────┐      ┌──────────────────┐
│     Bicho[][]   │      │  GameConstants   │
│    (Model)      │      │    (Config)      │
└─────────────────┘      └──────────────────┘
         │
         ▼
┌─────────────────┐
│   TipoBicho     │
│     (Enum)      │
└─────────────────┘
```

## 🧪 Testing

### Run Tests

```bash
mvn test
```

### Test Coverage

- **Bicho**: Entity validation, state transitions, edge cases
- **BichoManager**: Game logic, statistics, persistence
- **Total**: 24 comprehensive unit tests covering core functionality

### Example Test

```java
@Test
void testAtacarBalaValidCoordinates() {
    manager.getCampo()[0][0] = new Bicho(10, TipoBicho.NORMAL);
    boolean hit = manager.atacarBala(0, 0);
    
    assertTrue(hit);
    assertEquals(5, manager.getCampo()[0][0].getSalud());
}
```

## 📦 Building

### Maven Commands

```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Package JAR
mvn package

# Generate JavaDoc
mvn javadoc:javadoc

# Run the application
mvn exec:java -Dexec.mainClass="ui.ConsolaJuego"

# Create executable JAR with dependencies
mvn assembly:single
```

### Build Artifacts

- `target/guerra-de-bichos-1.0.0.jar` - Main JAR
- `target/guerra-de-bichos-1.0.0-jar-with-dependencies.jar` - Uber JAR
- `target/site/apidocs/` - JavaDoc documentation
- `target/surefire-reports/` - Test reports

## 📝 Code Quality

### Standards
- **JavaDoc**: Complete API documentation
- **Formatting**: Consistent code style
- **Naming**: Clear, intention-revealing names
- **Error Handling**: Proper exception usage with validation

### Example JavaDoc

```java
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
    // ...
}
```

## 🔧 Configuration

### Game Constants

Edit `src/models/GameConstants.java` to customize:

```java
public static final int NORMAL_HEALTH = 10;      // Normal bicho HP
public static final int ALIEN_HEALTH = 20;       // Alien bicho HP
public static final int BULLET_DAMAGE = 5;       // Bullet damage
public static final int MUTATION_MULTIPLIER = 2; // Mutation factor
public static final int MIN_BOARD_SIZE = 2;      // Min grid size
public static final int MAX_BOARD_SIZE = 10;     // Max grid size
```

## 🛠️ Technologies

- **Language**: Java 17+
- **Build Tool**: Maven 3.8+
- **Testing**: JUnit 5
- **Serialization**: Gson 2.10.1
- **CI/CD**: GitHub Actions
- **Version Control**: Git

## 📚 Learning Outcomes

This project demonstrates:

1. **Clean Code**: Readable, maintainable, well-documented code
2. **Testing**: Comprehensive unit tests with high coverage
3. **Architecture**: Proper layer separation and design patterns
4. **Build Automation**: Maven project management
5. **Version Control**: Git best practices with semantic versioning
6. **Documentation**: Professional README, JavaDoc, and changelog
7. **CI/CD**: Automated testing and build pipelines

## 🗺️ Roadmap

- [ ] Multiplayer support (network play)
- [ ] Additional bicho types with special abilities
- [ ] GUI version using JavaFX
- [ ] Campaign mode with levels
- [ ] Leaderboard and achievements
- [ ] AI opponents with difficulty levels

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**Portfolio Project**

- GitHub: [@sandroide6](https://github.com/sandroide6)


## 🙏 Acknowledgments

- Developed as a portfolio demonstration project
- Inspired by classic grid-based strategy games
- Built with modern Java best practices

---

<div align="center">

**⭐ Star this repository if you found it helpful!**

Made with ❤️ for learning and showcasing Java development skills

</div>
