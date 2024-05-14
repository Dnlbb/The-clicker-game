# Game Project

## Overview

This project is a simple Java-based graphical game using the Swing framework. The objective of the game is to click on a moving ball to score points. The game features multiple difficulty levels and a special mode where the ball can change color.

## Features

- **Game Modes**: 
  - Easy
  - Medium
  - Hard
  - Color Switch (ball changes between red and black)

- **Score Tracking**: 
  - Points are gained by clicking the red ball.
  - Points are lost by clicking the black ball in Color Switch mode or clicking outside the ball.

- **Timers**:
  - Game timer to track the duration of the game.
  - Ball move timer to periodically change the ball's position.

## Components

- `JFrame`: The main window of the game.
- `JPanel`: Container for displaying the ball and other components.
- `JButton`: Used for the start button and the ball.
- `JLabel`: Displaying the timer and score.
- `JComboBox`: For selecting the game mode.
- `Timer`: Used for updating the game time and ball position.

## How to Run

1. **Compile the Code**: Ensure you have Java installed and configured on your system. Compile the code using:
   ```bash
   javac Game.java
   ```
2. **Run the Game**: Run the compiled class file using:
   ```bash
   java Game
   ```
## How to Play
1. **Start the Game**:
   * Launch the application.
   * Click the 'Start' button to begin the game.
2. **Choose Difficulty Level**:
   * Select a difficulty level from the dropdown menu (Easy, Medium, Hard, Color Switch).
   * The ball size and movement speed will adjust based on the selected level.
3. **Score Points**:
   * Click the red ball to increase your score.
   * Avoid clicking the black ball in Color Switch mode to avoid losing points.
   * Clicking outside the ball in any mode (except when the ball is black in Color Switch mode) will decrease your score.
4. **End Game**:
   * The game continues until you close the application.
## Detailed Class Explanation
### `Game` Class
- **Constructor**:
* Initializes the game window with a title, size, and default settings.
* Sets up the main game panel and other components (buttons, labels, combo box).
- **`setupComponents()` Method**:
* Configures the visual properties of the components.
* Adds action listeners for the start button and mode combo box.
* Creates top and bottom panels to hold the components.
- **`setupTimers()` Method**:
* Initializes the game timer to update the elapsed time every second.
* Initializes the ball move timer to relocate the ball at set intervals.
- **`startGame()` Method**:
* Resets the score and time.
* Starts the game and ball move timers.
* Calls `setMode()` to configure the game based on the selected difficulty level.
* Calls `moveBall()` to place the ball on the game panel.
- **`setMode(int mode)` Method**:
* Configures the ball size and movement delay based on the selected game mode.
* Updates the current mode and timer delay accordingly.
- **`moveBall()` Method**:
* Randomly places the ball within the game panel boundaries.
* Handles ball color changes for Color Switch mode.
* Adds an action listener to the ball to handle scoring.
- **`CircleButton` Class**:
* Extends JButton to create a circular button.
* Overrides the paintComponent method to draw an oval representing the ball.
* Sets the foreground color of the ball.
- **`main` Method**:
* Launches the game by creating an instance of the Game class and making it visible.
## Dependencies
* Java Development Kit (JDK) 8 or higher.
