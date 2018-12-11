
package cs1302.arcade;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.paint.Color;

/**
 * Application that works like a virtual arcade cabinet that
 * can be used to launch the games Sudoku or Snake using buttons.
 * After a game is launched and completed, either the same
 * game or the other available game can be launched.
 */
public class ArcadeApp extends Application {

    private Random rng = new Random();
    private Playable playable;
    private boolean running;

    /**
     * Manages the GUI displayed to the user.
     *
     * @param stage The stage on which this application is displayed.
     */
    @Override
    public void start(Stage stage) {
	running = false;
	Group group = new Group(); // Container storing the nodes displayed in the GUI
	Menu menu = new Menu("File");
	MenuBar menuBar = new MenuBar(menu); // MenuBar constructed
	menuBar.setStyle("-fx-background-color: #39ff14;");
	MenuItem exit = new MenuItem("Exit");
	exit.setOnAction(event -> {
		Platform.exit();
	    });
	menu.getItems().add(exit);
	stage.setWidth(640);
	stage.setHeight(480);
	// These two buttons launch their respective game
	Button snake = makeSnakeButton();
	Button sudoku = makeSudokuButton();
	// Buttons must have their size adjusted to stylize the application
	snake.setMinSize(200,100);
	sudoku.setMinSize(200,100);
	// Buttons must have their color changed to stylize the application
	snake.setStyle("-fx-background-color: #39ff14;");
	sudoku.setStyle("-fx-background-color: #39ff14;");
	// Buttons must be in a certain location in the GUI
	snake.setLayoutX(100);
	sudoku.setLayoutX(350);
	snake.setLayoutY(240);
	sudoku.setLayoutY(240);
	group.getChildren().addAll(menuBar, snake, sudoku);
	group.setStyle("-fx-background-color: #dc143c;");
        Scene scene = new Scene(group, 640, 480, Color.BLACK);
        stage.setTitle("cs1302-arcade!");
        stage.setScene(scene);
	stage.sizeToScene();
        stage.show();
	// the group must request input focus to receive key events
	group.requestFocus();
    } // start

    /**
     * Creates a Button that is used to start the Snake game.
     *
     * @return Button that starts the Snake game.
     */
    private Button makeSnakeButton(){
	Button snake = new Button("SNAKE");
	snake.setOnAction(event -> {
		if(!running){
		    playable = new SnakeGame(); // Creates new Snake Game
		    running = true; // makes sure two games cannot be running at once
		    playable.play();
		    running = false;
		} // if
	    });
	return snake;
    } // makeSnakeButton

    /**
     * Creates a Button that is used to start the Sudoku Game.
     *
     * @return Button that starts the Sudoku Game.
     */
    private Button makeSudokuButton(){
	Button sudoku = new Button("SUDOKU");
	sudoku.setOnAction(event -> {
		if (!running){
		    playable = new SudokuGame(); // Creates new Sudoku Game
		    running = true; // makes sure two games cannot be running at once
		    playable.play();
		    running = false;
		} // if
	    });
	return sudoku;
    } // makeSudokuButton

    /**
     * Returns whether an instance of either SnakeGame or SudokuGame
     * is currently being played and thus running.
     *
     * @return whether an instance of either SnakeGame of SudokuGame is currently running
     */
    public boolean getRunning() {
	return running;
    }

    /**
     * Main method that launches the GUI. This contains a check
     * that catches if X server connection has been lost, and informs
     * the user on how to fix this issue.
     *
     * @param args Command Line arguments
     */
    public static void main(String[] args) {
	try {
	    Application.launch(args);
	} catch (UnsupportedOperationException e) {
	    System.out.println(e);
	    System.err.println("If this is a DISPLAY problem, then your X server connection");
	    System.err.println("has likely timed out. This can generally be fixed by logging");
	    System.err.println("out and logging back in.");
	    System.exit(1);
	} // try
    } // main

} // ArcadeApp

