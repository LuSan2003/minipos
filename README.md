#  MiniPOS - Android Point of Sale

Aplicación nativa Android desarrollada en Kotlin que simula un punto de venta básico, permitiendo realizar transacciones y visualizar un historial local.

##  Instrucciones de Ejecución

1.  **Requisitos**: Android Studio Koala o superior, JDK 17+.
2.  **Abrir Proyecto**: Clona este repositorio y abre la carpeta `minipos` desde Android Studio.
3.  **Sincronizar**: Espera a que Gradle descargue las dependencias y finalice el Sync.
4.  **Ejecutar**:
    *   Conecta un dispositivo físico (Android 7.0+) o inicia un Emulador.
    *   Presiona el botón **Run** en Android Studio.
    *   *Nota*: La base de datos se crea automáticamente en el primer inicio.

##  Arquitectura (MVVM + Clean Arch)

El proyecto sigue una arquitectura **MVVM** moderna priorizando la separación de responsabilidades:

*   **UI Layer**: 100% **Jetpack Compose** (Declarativa). Sin XML ni Fragments.
*   **Presentation**: ViewModels gestionan el estado reactivo usando `StateFlow` y `Coroutines`.
*   **Domain/Data**: **Repository Pattern** como única fuente de verdad.
*   **Persistence**: **Room Database** (SQLite) para almacenamiento local robusto y offline.
*   **Data Flow**: Unidireccional (UDF). La UI observa cambios en la BD mediante `Flow`.
*   **Build**: Kotlin DSL, Version Catalog (TOML) y **KSP** para procesamiento eficiente.
