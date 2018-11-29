
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


public class ArcadeApp extends Application {

    private Random rng = new Random();
    private Playable playable;
    private boolean running;

    @Override
    public void start(Stage stage) {
	running = false;
	Group group = new Group();
	Menu menu = new Menu("File");
	MenuBar menuBar = new MenuBar(menu);
	menuBar.setStyle("-fx-background-color: #39ff14;");
	MenuItem exit = new MenuItem("Exit");
	exit.setOnAction(event -> {
		Platform.exit();
	    });
	menu.getItems().add(exit);
	stage.setWidth(640);
	stage.setHeight(480);
	Button snake = new Button("SNAKE");
	snake.setOnAction(event -> {
		if (!running) {
		playable = new SnakeGame();
		running = true;
		playable.play();
		}
	    });
	Button sudoku = new Button("SUDOKU");
	snake.setOnAction(event -> {
		if (!running) {
		playable = new SnakeGame();
		running = true;
		playable.play();
		}
	    });
	snake.setMinSize(200,100);
	sudoku.setMinSize(200,100);
	snake.setStyle("-fx-background-color: #39ff14;");
	sudoku.setStyle("-fx-background-color: #39ff14;");
	snake.setLayoutX(100);
	sudoku.setLayoutX(350);
	snake.setLayoutY(240);
	sudoku.setLayoutY(240);
	group.getChildren().addAll(menuBar, snake, sudoku);
	
	/*
	Group group = new Group();           // main container
	Rectangle r = new Rectangle(20, 20); // some rectangle
	r.setX(50);                          // 50px in the x direction (right)
	r.setY(50);                          // 50ps in the y direction (down)
	group.getChildren().add(r);          // add to main container
	
	// when the user clicks on the rectangle, send to random position
	r.setOnMouseClicked(event -> {
		System.out.println(event);
		r.setX(rng.nextDouble() * (640 - r.getWidth()));
		r.setY(rng.nextDouble() * (480 - r.getHeight()));
	    });

	// when the user presses left and right, move the rectangle
	group.setOnKeyPressed(event -> {
		System.out.println(event);
		if (event.getCode() == KeyCode.LEFT)  r.setX(r.getX() - 10.0);
		if (event.getCode() == KeyCode.RIGHT) r.setX(r.getX() + 10.0);
		// TODO bounds checking
	    });
	*/
	group.setStyle("-fx-background-color: #dc143c;");
        Scene scene = new Scene(group, 640, 480, Color.BLACK);
        stage.setTitle("cs1302-arcade!");
        stage.setScene(scene);
	stage.sizeToScene();
        stage.show();

	// the group must request input focus to receive key events
	// @see https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#requestFocus--
	group.requestFocus();

    } // start

    public boolean getRunning() {
	return running;
    }

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

