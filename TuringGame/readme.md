Voici le `README.md` mis à jour sans la section sur la structure du projet :

```markdown
# TuringGame

## Description
TuringGame is a Java-based puzzle game that challenges players to solve logic-based problems inspired by the concept of the Turing machine. The game offers two different versions: a **Console Version** and a **Graphical Version** using JavaFX, each providing a unique user experience.

## Features
- **Two Game Versions:**
  - **Console Version:** A text-based interface that provides a simple and minimalistic gameplay experience.
  - **Graphical Version (JavaFX):** A visually interactive version with JavaFX, allowing players to interact with game elements using a graphical user interface.

- **Modular Design:** Both versions share common logic and controller components, making it easier to maintain and extend the game.

- **Real-time Feedback:** The game provides immediate feedback on player actions, helping players understand their decisions and improve their problem-solving skills.

## Requirements
- **Java Development Kit (JDK) 8 or higher**.
- **JavaFX library** (for the graphical version).

## Installation and Compilation
1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/your-username/turinggame.git
   ```
2. Navigate to the project directory:
   ```bash
   cd TuringGame
   ```
3. Compile the source files:
   - For the **Console Version**:
     ```bash
     javac src/main/java/ConsoleVersion/*.java src/main/java/ConsoleVersion/Controller/*.java src/main/java/ConsoleVersion/ConsoleView/*.java
     ```
   - For the **Graphical Version**:
     ```bash
     javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls src/main/java/JavaFxVersion/*.java src/main/java/JavaFxVersion/Controller/*.java src/main/java/JavaFxVersion/View/*.java
     ```

## How to Run the Game
- **Console Version:**
  1. Navigate to the project root directory.
  2. Run the console version using the following command:
     ```bash
     java -cp src/main/java ConsoleVersion.App
     ```

- **Graphical Version (JavaFX):**
  1. Make sure JavaFX is correctly set up on your system.
  2. Run the graphical version using the following command:
     ```bash
     java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -cp src/main/java JavaFxVersion.MainApplication
     ```

## Gameplay Instructions
- Start the game and follow the on-screen instructions.
- Use the keyboard and the mouse to navigate and interact with the game.


## Author
Zakaria M'hamdi  
Contact: 60423@etu.he2b.be
```

