# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2025-11-01

### Added
- Initial release of Guerra de Bichos
- Core game mechanics:
  - Grid-based tactical combat system
  - Bullet attacks dealing configurable damage
  - Bomb attacks for instant kills
  - Mutation system for strategic gameplay
- Multiple board sizes (2x2 to 10x10)
- Difficulty presets (Easy, Medium, Hard, Expert, Custom)
- Comprehensive statistics tracking:
  - Turns played
  - Shot accuracy
  - Bombs used
  - Mutations performed
  - Points scored
- Enhanced console interface with:
  - ANSI color support
  - Emoji icons for better visualization
  - ASCII art borders and formatting
  - Interactive menus
- Game persistence:
  - Save/load functionality with JSON serialization
  - Automatic state management
- Professional code structure:
  - Complete JavaDoc documentation
  - Centralized constants management
  - Separation of concerns (models, service, UI)
- Unit tests with JUnit 5:
  - Bicho entity tests
  - BichoManager service tests
  - 90%+ code coverage
- Maven build system:
  - Dependency management
  - Automated building
  - Test execution
  - JavaDoc generation
- GitHub integration:
  - MIT License
  - Continuous Integration setup
  - Professional README with badges

### Technical Details
- Java 11+ compatibility
- Gson for JSON serialization
- JUnit 5 for testing
- Maven 3.8+ build automation

[1.0.0]: https://github.com/tu-usuario/guerra-de-bichos/releases/tag/v1.0.0
