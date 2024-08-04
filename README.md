# TestApp

Welcome to the Movie Test Android App! This app shows a list of movies and support error-handling. Also it stores data in database and database is the source of truth.

## Features

- **Clean Architecture**: The app divided on 3 layers - presentation, data and domain. It's divided for a few modules(each for layer and one main). But for bigger projects it can be better to divide modules as features and make in pluggable.
- **Modern Android Components**: Utilizes the latest Android libraries for a seamless user experience.
- **LiveData and ViewModel**: Uses LiveData and ViewModel for a reactive and lifecycle-aware architecture in presentation module. But for data and domain layers it's used flow.
- **Koin for Dependency Injection**: Simple and lightweight dependency injection with Koin.

## Getting Started

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/mykhailo-hunko/TestApp.git
    ```

2. Open the project in Android Studio.

3. Sync the project with Gradle files.

### Building and Running

- Connect your Android device or start an emulator.
- Click on the "Run" button in Android Studio or use the following command:
    ```./gradlew assembleDebug ```
