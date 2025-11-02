# Guerra de Bichos - Proyecto Portfolio Java

## Descripción General
Juego de estrategia táctico en tablero configurable implementado en Java, diseñado como proyecto de portafolio profesional.

## Estado del Proyecto
**v1.0.0 - Portfolio-Ready**
- Fecha de creación: 1 de noviembre, 2025
- Lenguaje: Java 17
- Framework: Maven 3.8+
- Tests: 24 tests unitarios (JUnit 5) - todos pasando
- Documentación: JavaDoc completo

## Mejoras Implementadas para Portafolio

### Arquitectura Profesional
- **Separación de Capas**: Models / Service / UI
- **Constantes Centralizadas**: GameConstants para configuración
- **Sistema de Estadísticas**: GameStatistics para tracking de rendimiento
- **Tableros Configurables**: 2x2 hasta 10x10 con presets de dificultad

### Calidad de Código
- **JavaDoc Completo**: Documentación exhaustiva de APIs públicas
- **Tests Unitarios**: 24 tests con JUnit 5
- **Validaciones Robustas**: Input validation y error handling
- **Código Limpio**: Sin números mágicos, nombres descriptivos

### Herramientas de Desarrollo
- **Maven**: Build automation completo
- **GitHub Actions**: CI/CD configurado
- **Documentación**: README profesional, CHANGELOG, LICENSE

## Estructura del Proyecto

```
guerra-de-bichos/
├── src/                        # Código fuente
│   ├── models/                 # Entidades del dominio
│   │   ├── Bicho.java          # Entidad principal
│   │   ├── TipoBicho.java      # Enum de tipos
│   │   ├── GameConstants.java  # Constantes centralizadas
│   │   └── GameStatistics.java # Sistema de estadísticas
│   ├── service/                # Lógica de negocio
│   │   └── BichoManager.java   # Controlador del juego
│   └── ui/                     # Interfaz de usuario
│       └── ConsolaJuego.java   # Consola con colores ANSI
├── test/                       # Tests unitarios
│   ├── models/
│   │   └── BichoTest.java      # 12 tests
│   └── service/
│       └── BichoManagerTest.java # 12 tests
├── .github/workflows/          # CI/CD
│   └── ci.yml                  # GitHub Actions
├── pom.xml                     # Maven configuration
├── README.md                   # Documentación principal
├── CHANGELOG.md                # Historial de versiones
├── LICENSE                     # MIT License
└── replit.md                   # Este archivo
```

## Tecnologías y Herramientas

### Core
- **Java 17**: Características modernas (text blocks, switch expressions)
- **Maven 3.8+**: Gestión de dependencias y build
- **JUnit 5**: Framework de testing

### Librerías
- **Gson 2.10.1**: Serialización JSON para persistencia

## Características del Juego

### Mecánicas
1. **Ataques**
   - Bala: Daño de 5 HP
   - Bomba: Eliminación instantánea
2. **Mutación**: Duplica salud del bicho más débil
3. **Persistencia**: Guardar/cargar en JSON
4. **Estadísticas**: Tracking completo de rendimiento

### Configuración
- Tableros desde 2x2 hasta 10x10
- Presets de dificultad (Fácil, Medio, Difícil, Experto)
- Tipos de bichos: NORMAL (10 HP), ALIEN (20 HP), VACIO (0 HP)

## Workflow Actual
**Nombre**: Guerra de Bichos
**Comando**: `mvn -q exec:java -Dexec.mainClass="ui.ConsolaJuego"`
**Tipo**: Consola interactiva

## Comandos Maven

### Desarrollo
```bash
# Compilar
mvn clean compile

# Ejecutar tests
mvn test

# Ejecutar el juego
mvn exec:java -Dexec.mainClass="ui.ConsolaJuego"

# Empaquetar JAR
mvn package

# Generar JavaDoc
mvn javadoc:javadoc
```

### Artifacts Generados
- `target/guerra-de-bichos-1.0.0.jar` - JAR principal
- `target/guerra-de-bichos-1.0.0-jar-with-dependencies.jar` - JAR ejecutable
- `target/site/apidocs/` - Documentación JavaDoc
- `target/surefire-reports/` - Reportes de tests

## Aspectos Destacados para Portafolio

### Buenas Prácticas
1. **Arquitectura Limpia**: MVC con separación clara de responsabilidades
2. **SOLID**: Single Responsibility en cada clase
3. **DRY**: Constantes centralizadas, no repetición
4. **Fail-Fast**: Validaciones tempranas con excepciones descriptivas
5. **Inmutabilidad**: Enums y constantes son finales

### Testing
- 24 tests unitarios
- Cobertura de casos edge
- Validación de lógica de negocio
- Tests de persistencia

### Documentación
- JavaDoc completo con ejemplos de uso
- README profesional con badges
- CHANGELOG con semantic versioning
- GitHub Actions para CI/CD

## Interfaz Mejorada

### Características Visuales
- Colores ANSI para mejor UX
- Emojis para identificación visual
- Bordes ASCII art
- Menús interactivos claros
- Estadísticas formateadas en tablas

### Ejemplo de Salida
```
╔═══════════════════════════════════════════════════╗
║     🐛  GUERRA DE BICHOS  🐛                      ║
║     Tactical Strategy Grid Game                  ║
╚═══════════════════════════════════════════════════╝

┌─── CAMPO DE BATALLA ───┐
  [0,0]🐛NORMAL-10  [0,1]👽ALIEN-20
  [1,0]⬜VACIO-0    [1,1]🐛NORMAL-10
└──────────────────────────┘
```

## Notas Técnicas

### Java 17 Features
- Switch expressions para código más limpio
- Text blocks para strings multilínea
- Records considerados para futuras versiones

### Compatibilidad
- Requiere Java 17+ (por uso de features modernas)
- Maven 3.8+ recomendado
- Funciona en cualquier OS con JVM

## Próximos Pasos (Roadmap)

1. **Características de Juego**
   - [ ] Más tipos de bichos con habilidades especiales
   - [ ] Sistema de achievements
   - [ ] Modos de juego adicionales

2. **Mejoras Técnicas**
   - [ ] Coverage reporting con JaCoCo
   - [ ] Integration tests
   - [ ] Performance profiling

3. **Interfaz**
   - [ ] Versión GUI con JavaFX
   - [ ] Soporte para temas de colores
   - [ ] Animaciones en consola

## Cambios Recientes

### v1.0.0 (2025-11-01)
- ✅ Refactorización completa para portafolio
- ✅ Maven build system configurado
- ✅ JavaDoc completo agregado
- ✅ 24 tests unitarios implementados
- ✅ Sistema de estadísticas agregado
- ✅ Tableros configurables (2x2 a 10x10)
- ✅ Constantes centralizadas
- ✅ Interfaz de consola mejorada
- ✅ README profesional con badges
- ✅ LICENSE y CHANGELOG agregados
- ✅ GitHub Actions CI/CD configurado
