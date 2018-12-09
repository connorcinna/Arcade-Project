# Reflection

Add to this file to satisfy the "Reflection Updates" non-functional requirement
for this project. Please keep this document organized using Markdown. If you
click on this file in your team's GitHub repository website, then you will see
that the Markdown is transformed into nice looking HTML. 

 

## FRI 2018-11-30 @ 3:38 PM EST

1. **DONE:** Downloaded skeleton code, made a main menu for the app, made classes for both games Sudoku and Snake, made a Snake class to represent the snake, drew the board for Snake, included the instructions for how to play Snake, created a Playable interface that both games implement that contains a single method play().

2. **TODO:** Make the snake move in the Snake game, create food, make snake grow bigger when it eats food, create win and lose conditions, create boundaries that the snake can't pass, do everything for sudoku

3. **PROB:** Changed some code and now the snake won't appear initially when the game starts, when it did before. Also, can't figure out how to make snake move.

## FRE 2018-12-07 @ 9:34 PM EST

1. **DONE:** Create moving snake in Snake game that is able to move around the board in a clean and orderly mannor. Sudoku is now in a possition where the baord is created, and using a predetermined array of String objects, a game of Sudoku can be played. In addition to this, it is possible to check whether each box has one of each number type.

2. **TODO:** Make visible apples that expand the size of the snake, add a score to snake and sudoku, create game boards for Sudoku, stylize the Sudoku game baord, create a win condition for Sudoku, create a lose condition for Snake, create a Game Over screen for snake, and end the game when the snake moves out of bounds.

3. **PROB:** After ending Snake, the code keeps running in the background. In addition to this, it is not currently possible to end an instance of one game, and then play it again after closing it, which is a problem.