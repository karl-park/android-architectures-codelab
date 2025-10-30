# Android Architecture Patterns Showcase

This project serves as a practical demonstration and comparison of several popular architectural patterns commonly used in Android application development. 
It provides a clear, side-by-side implementation of each pattern within a simple counter application, allowing developers to understand their structure, benefits, and trade-offs in a tangible context.

## Features

The application offers the following core functionalities:

*   **Counter Functionality**: A basic counter that can be incremented and decremented.
This simple feature is implemented across all showcased architectural patterns to highlight their structural differences.
  
* **Architectural Pattern Exploration**: Dedicated screens for each architectural pattern, implementing the same counter functionality.
This allows for direct comparison of how each pattern organizes its code and handles user interaction.
  
* **Navigation**: Easy navigation between different architectural pattern implementations from a central main screen, enabling users to quickly switch and observe each pattern in action.

## Architectural Patterns Demonstrated

This project showcases the implementation of the following architectural patterns:

*   **Model-View-ViewModel (MVVM)**: A pattern that separates the UI (View) from the business logic (ViewModel) using data binding. The ViewModel exposes data streams that the View observes, promoting a reactive approach to UI updates.
*   **Model-View-Intent (MVI)**: A unidirectional data flow pattern where user intentions (Intents) are processed by the Model, which then emits new States that the View renders. This pattern emphasizes immutability and a single source of truth for the application state.
*   **Model-View-Presenter (MVP)**: A pattern that separates the UI (View) from the business logic (Presenter). The Presenter acts as an intermediary, handling all UI logic, interacting with the Model, and updating the View through an interface.
*   **Model-View-Controller (MVC)**: A traditional pattern where the Controller handles user input, updates the Model, and then updates the View. In Android, Activities often serve as the Controller, managing both UI and business logic to some extent.
*   **VIPER**: An architectural pattern that emphasizes a clean separation of concerns, breaking down an application into distinct modules: View, Interactor, Presenter, Entity, and Router. This pattern is particularly well-suited for large and complex applications.

## Dependency Injection

For managing dependencies throughout the application, this project utilizes the **Service Locator** pattern.
This approach centralizes the creation and provision of dependencies, making them accessible to components that require them without those components needing to know how the dependencies are constructed.

## Project Structure

The project is organized to clearly delineate each architectural pattern's implementation.
You will find separate packages or modules for MVVM, MVI, MVP, MVC, and VIPER, each containing the necessary components (e.g., Views, ViewModels, Presenters, Interactors, etc.) for their respective patterns.
This structure facilitates understanding and comparison.
