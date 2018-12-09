package cs1302.arcade;

import javafx.application.Platform;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SnakeGame implements Playable {
    
    ArrayList<Snake> list;
    private int directionX;
    private int directionY;
    private Scene scene;
    private Stage stage;
    private GridPane grid;
    private BorderPane pane;
    private TextArea instructions;
    private int score;
    private Apple apple;
    
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
	fillGrid();
	stage.setWidth(1280);
	stage.setHeight(720);
	scene = new Scene(pane, 1280,720);
	Timeline timeline = makeTimeLine();
	timeline.play();
	scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> moveSnake(event));
	stage.setScene(scene);
	stage.sizeToScene();
	stage.show();
    } // play

    public int getDirectionX() {
	return directionX;
    }
    public int getDirectionY() {
	return directionY;
    }

    public void setDirectionX(int x) {
	directionX = x;
    }
    public void setDirectionY(int y) {
	directionY = y;
    }

    public void gameOver() {
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("GAME OVER");
	alert.setContentText("YOU LOST");
	alert.setOnHidden(event -> Platform.exit());
	alert.show();
    }
    
    public Timeline makeTimeLine() {
	KeyFrame kf = new KeyFrame(Duration.millis(100), event -> {
		if (list.get(0).getX() >= 40 || list.get(0).getY() >= 40 || list.get(0).getX() <= 0 || list.get(0).getY() <= 0) { //too long by 31 char
		    list.get(0).setX(20);
		    list.get(0).setY(20);
		    directionX = 0;
		    directionY = 0;
		    gameOver();
		}
		if (list.size() > 1) {
		    for (int i = 1; i < list.size(); i++) {
			grid.getChildren().remove(list.get(i));
			list.get(i).setX(list.get(i - 1).getX()); //sets the location of every subsequent snakepart 10 times a second
			list.get(i).setY(list.get(i - 1).getY());
			grid.add(list.get(i), list.get(i).getX(), list.get(i).getY());
		    } // for
		} //if
		grid.getChildren().remove(list.get(0)); 
		list.get(0).setX(list.get(0).getX() + getDirectionX()); //updates the snakeheads location 10 times a second
		list.get(0).setY(list.get(0).getY() + getDirectionY());
		grid.add(list.get(0), list.get(0).getX(), list.get(0).getY());
		if (apple.getX() == list.get(0).getX() && apple.getY() == list.get(0).getY()) {
		    eatApple();
		}
	});
	Timeline timeline = new Timeline(kf);
	timeline.setCycleCount(Timeline.INDEFINITE);
	return timeline;
    } // makeTimeLine

    public void eatApple() {
	grid.getChildren().remove(apple);
	Snake snake = new Snake();
	snake.setFill(javafx.scene.paint.Color.GREEN);
	list.add(snake);
	ArrayList<Integer[]> positions = snakeLocation();
	while (checkLocation(positions)) {
	    int x = (int) (Math.random() * 40);
	    int y = (int) (Math.random() * 40);
	    apple.setX(x);
	    apple.setY(y);
	}
	grid.add(apple, apple.getY(), apple.getX()); 
    }

    public void moveSnake(KeyEvent e){
	KeyCode code = e.getCode();
	if (code == KeyCode.LEFT) {
	    setDirectionX(-1);
	    setDirectionY(0);
	}
	else if (code == KeyCode.RIGHT) {
	    setDirectionX(1);
	    setDirectionY(0);
	}
	else if (code == KeyCode.UP) {
	    setDirectionX(0);
	    setDirectionY(-1);
	}
	else if (code == KeyCode.DOWN) {
	    setDirectionX(0);
	    setDirectionY(1);
	}
    } // moveSnake
    
    public boolean checkLocation(ArrayList<Integer[]> positions) {
	for (int i = 0; i < list.size()-1; i++) {
	    if (positions.get(i)[0] == apple.getX() && positions.get(i)[1] == apple.getY()) {
		return true;
	    }
	}
	return false;
    }
    public ArrayList<Integer[]> snakeLocation() {
	ArrayList<Integer[]> positions = new ArrayList<Integer[]>();
	for (int i = 0; i < list.size(); i++) {
	    positions.add(i, new Integer[2]);
	    positions.get(i)[0] = list.get(i).getX();
	    positions.get(i)[1] = list.get(i).getY();
	}
	return positions;
    }
    
    public void setInstructions() {
	instructions.setText("Use the 4 arrow keys (not on the numpad) to control the snake.\n\nThe snake will always move in the last direction it was told to.\n\nIf the snake runs into itself or into a wall, then the game ends.\n\nCollect apples to get points.\n\n As the snake eats apples, it will grow longer, and your score will increase.\n\nTo win, make your snake fill up the entire game board.\n\nAs the game progresses, your snake will leave a trail of where it has been.\n\nThis trail will grow longer as the game goes on,\n\nand will last longer as the snake eats more apples.\n\nScore: " + score);
    }

    public Apple makeApple() {
	ArrayList<Integer[]> positions = snakeLocation();
	apple = new Apple();
	apple.setFill(javafx.scene.paint.Color.RED);
	while (checkLocation(positions)) {
	    int x = (int) (Math.random() * 40);
	    int y = (int) (Math.random() * 40);
	    apple.setX(x);
	    apple.setY(y);
	}
	return apple;
    }
    
    public void fillGrid() {
	Apple apple = makeApple();
	for(int row = 0; row < 40; row++){
	    for(int col = 0; col < 40; col++) {
		Rectangle r = new Rectangle(18, 18);
		r.setStyle("-fx-background-color: #000000;");
		grid.add(r, col, row);
		if (row == apple.getY() && col == apple.getX()) {
		    grid.add(apple, col, row);
		}
		if (row == list.get(0).getY() && col == list.get(0).getX()) {		 
		    grid.add(list.get(0), col, row);
		}
	    } // for
	} // for
    }

    
}
