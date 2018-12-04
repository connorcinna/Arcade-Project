package cs1302.arcade;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import java.util.ArrayList;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.animation.Animation.Status;

public class SnakeGame implements Playable {
    
    private ArrayList<Snake> list;
    private int directionX;
    private int directionY;
    private Scene scene;
    private Stage stage;
    private GridPane grid;
    private BorderPane pane;
    private TextArea instructions;
    private int score;
    
    public void play() {
	score = 0;
	directionX = 0;
	directionY = 0;
	stage = new Stage();
	list = new ArrayList<Snake>();
	instructions = new TextArea();
	instructions.setEditable(false);
	setInstructions();
	pane = new BorderPane();
	grid = new GridPane();
	grid.setMaxSize(720, 720);
	pane.setMinSize(560, 720);
	pane.setLeft(grid);
	pane.setRight(instructions);
	Snake snakeHead = new Snake();
	snakeHead.setFill(javafx.scene.paint.Color.GREEN);
	list.add(snakeHead);
	Timeline timeline = makeTimeLine();
	fillGrid();
	stage.setWidth(1280);
	stage.setHeight(720);
	scene = new Scene(pane, 1280,720);
	timeline.play();
	scene.setOnKeyPressed(event -> moveSnake(event));
	stage.setScene(scene);
	stage.sizeToScene();
	stage.show();
    } // play

    public Timeline makeTimeLine(){
	Timeline timeline = new Timeline();
	KeyFrame kf = new KeyFrame(Duration.millis(100), event -> {
		if (list.size() > 1) {
		    for(int i = 1; i < list.size(); i++){
			list.get(i).setX(list.get(i - 1).getX()); //sets the location of every subsequent snakepart 10 times a second
			list.get(i).setY(list.get(i - 1).getY());
		    } // for
		} //if
		grid.getChildren().remove(list.get(0)); 
		list.get(0).setX(list.get(0).getX() + directionX); //updates the snakeheads location 10 times a second
		list.get(0).setY(list.get(0).getY() + directionY);
		grid.add(list.get(0), list.get(0).getX(), list.get(0).getY());
	});
	timeline.setCycleCount(Timeline.INDEFINITE);
	timeline.getKeyFrames().add(kf);
	return timeline;
    } // makeTimeLine

    public void moveSnake(KeyEvent e){
	/*
	if (timeline.getStatus() != Status.RUNNING) {
	    timeline.play();
	}
	*/
	System.out.println("Made It!");
	KeyCode code = e.getCode();
	if (code == KeyCode.LEFT) {
	    directionX = -1;
	    directionY = 0;
	}
	else if (code == KeyCode.RIGHT) {
	    directionX = 1;
	    directionY = 0;
	}
	else if (code == KeyCode.UP) {
	    directionX = 0;
	    directionY = -1;
	}
	else if (code == KeyCode.DOWN) {
	    directionX = 0;
	    directionY = 1;
	}
    } // moveSnake
    public void setInstructions() {
	instructions.setText("Use the 4 arrow keys (not on the numpad) to control the snake. The snake will always move in the last direction it was told to. If the snake runs into itself or into a wall, then the game ends. Collect apples to get points. As the snake eats apples, it will grow longer, and your score will increase. To win, make your snake fill up the entire game board. As the game progresses, your snake will leave a trail of where it has been. This trail will grow longer as the game goes on, and will last longer as the snake eats more apples. Score: " + score);
    }
    
    public void fillGrid() {
	for(int row = 0; row < 40; row++){
	    for(int col = 0; col < 40; col++) {
		if (row == list.get(0).getY() && col == list.get(0).getX()) {		 
		    grid.add(list.get(0), col, row);
		}
		Rectangle r = new Rectangle(18, 18);
		r.setStyle("-fx-background-color: #000000;");
		grid.add(r, col, row);
	    } // for
	} // for
    }
}
