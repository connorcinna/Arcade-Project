package cs1302.arcade;


import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.animation.Timeline;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
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
import javafx.scene.control.ButtonType;
import javafx.animation.Animation.Status;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
/** 
    SnakeGame is the main class for running the Snake game. It creates snake and apple objects 
    and utilizes a timeline to move these snake objects around the game scene. 
    It functions in accordance with traditional implementations of Snake, and allows the player
    to exit the game once they complete it or fail. It implements the interface for both our
    games, Playable, which only contains the method play().
 */
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
    private Snake s;
    private boolean stopped;
    private Stage loserStage;
    private Scene loserScene;
    private boolean timelineRunning;
    /**
       The method which is called when the user selects Snake to play. This method functions
       similar to start in other programs, as it creates a scene and stage and shows the 
       scene in the stage. 
     */
    
    public void play() {
	stopped = false;
	timelineRunning = false;
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
	s = new Snake();
	list.add(s);
	fillGrid();
	stage.setWidth(1280);
	stage.setHeight(720);
	scene = new Scene(pane, 1280,720);
	Timeline timeline = makeTimeLine();
	if (timelineRunning) {
	    timeline.stop();
	    timelineRunning = false;
	} // if
	timeline.play();
	timelineRunning = true;
	scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> moveSnake(event));
	stage.setTitle("SNAKE");
	stage.setScene(scene);
	stage.sizeToScene();
	stage.show();
    } // play
    /**
       Once the player loses, either by running out of bounds or by making the snake
       eat itself, this method creates a popup that tells them they lost, and allows
       them to exit back to the main arcade app menu. This method also sets a boolean flag
       equal to true in order to prevent the code in the timeline from continuing to execute
       after losing.
     */
	  
    public void gameOver() {
	loserStage = new Stage();
	loserStage.setMinWidth(360);
	loserStage.setMinHeight(200);
	BorderPane loserPane = new BorderPane();
	HBox hbox = new HBox();
	hbox.setPadding(new Insets(150));
	Button exit = new Button("EXIT");
	exit.setOnAction(event -> {
		loserStage.close();
		stage.close();
	    });
	loserPane.setMinSize(360, 200);
	TextArea over = new TextArea("GAME OVER");
	over.setMaxWidth(360);
	over.setMaxHeight(50);
	over.setEditable(false);
	loserPane.setTop(over);
	loserPane.setCenter(hbox);
	hbox.getChildren().addAll(exit);
	loserScene = new Scene(loserPane, 360, 200);
	loserStage.setScene(loserScene);
	loserStage.setMaximized(true);
	loserStage.sizeToScene();
	loserStage.setTitle("YOU DIED");
	loserStage.show();
	stopped = true;
    }
    /**
       Provides the same functionality as gameOver, but for the condition of winning, which is
       covering every square in the game with the snake.
     */
    public void win() {
	winnerStage = new Stage();
	winnerStage.setMinWidth(360);
	winnerStage.setMinHeight(200);
	BorderPane loserPane = new BorderPane();
	HBox hbox = new HBox();
	hbox.setPadding(new Insets(150));
	Button exit = new Button("EXIT");
	exit.setOnAction(event -> {
		winnerStage.close();
		stage.close();
	    });
	loserPane.setMinSize(360, 200);
	TextArea over = new TextArea("YOU WIN!");
	over.setMaxWidth(360);
	over.setMaxHeight(50);
	over.setEditable(false);
	loserPane.setTop(over);
	loserPane.setCenter(hbox);
	hbox.getChildren().addAll(exit);
	winnerScene = new Scene(loserPane, 360, 200);
	winnerStage.setScene(winnerScene);
	winnerStage.setMaximized(true);
	winnerStage.sizeToScene();
	winnerStage.setTitle("WINNER");
	winnerStage.show();
	stopped = true;
    }
    /**
       Creates a timeline that moves the snake around the board. The keyframe executes every
       100 milliseconds, making the game run at 10 frames per second. Includes a lose condition
       if the snake goes out of bounds or the snake touches any other part of it's body.
       Also includes a win condition if every spot in the gameboard is covered by the snake. 
       @return Timeline returns a timeline that is played in the play method.
     */
    
    public Timeline makeTimeLine() {
	KeyFrame kf = new KeyFrame(Duration.millis(100), event -> {
		//prevents code in timeline from running if player either loses or wins
		if (!stopped) {
		    if (list.size() == 1600) { //win condition
			win();
		    }
		    //going out of bounds
		    if (s.getX() >= 40 || s.getY() >= 40 || s.getX() <= 0 || s.getY() <= 0) {
			directionX = 0;
			directionY = 0;
			gameOver();
		    }
		    if (list.size() > 1) {
			for (int i = list.size() - 1; i > 0; i--) {
			    if (s.getX() == list.get(i).getX() && s.getY() == list.get(i).getY()) {
				gameOver();
			    }
			    grid.getChildren().remove(list.get(i));
			    //sets the location of every subsequent snakepart 10 times a second
			    list.get(i).setX(list.get(i - 1).getX()); 
			    list.get(i).setY(list.get(i - 1).getY());
			    grid.add(list.get(i), list.get(i).getX(), list.get(i).getY());
			} // for
		    } //if
		    grid.getChildren().remove(s);
		    s.setX(s.getX() + directionX); //updates the snakeheads location 10 times a second
		    s.setY(s.getY() + directionY);
		    grid.add(s, s.getX(), s.getY());
		    if ((apple.getX() == s.getX()) && (apple.getY() == s.getY())) { 
			eatApple();
		    }
		}
	});
	Timeline timeline = new Timeline(kf);
	timeline.setCycleCount(Timeline.INDEFINITE);
	return timeline;
    } // makeTimeLine
    /**
       Removes the apple from the game and creates another snake object right behind 
       the last snake in the list. Updates the score when an apple is eaten.
     */

    public void eatApple() {
	grid.getChildren().remove(apple);
	int snakeX = 0, snakeY = 0;
	//determines the location of where the next snake should be based on direction
	if (directionX == 1) {
	    snakeX = list.get(list.size()-1).getX()-1;
	    snakeY = list.get(list.size()-1).getY();
	}
	else if (directionY == 1) {
	    snakeY = list.get(list.size()-1).getY()-1;
	    snakeX = list.get(list.size()-1).getX();
	}
	else if (directionY == -1) {
	    snakeY = list.get(list.size()-1).getY()+1;
	    snakeX = list.get(list.size()-1).getX();
	}
	else if (directionX == -1) {
	    snakeX = list.get(list.size()-1).getX()+1;
	    snakeY = list.get(list.size()-1).getY();
	}
	Snake snake = new Snake(snakeX, snakeY);
	grid.add(snake, snakeX, snakeY);
	list.add(snake);
	apple.setX((int) (Math.random() * 40));
	apple.setY((int) (Math.random() * 40));
	ArrayList<Integer[]> positions = snakeLocation();
	while (checkLocation(positions)) {
	    apple.setX((int) (Math.random() * 40));
	    apple.setY((int) (Math.random() * 40));
	}
	grid.add(apple, apple.getX(), apple.getY());
	score++;
	setInstructions();
    }
    /**
       When used in an event filter, takes in the KeyEvent and updates the direction of the snake
       according to the key pressed. Does not allow for the snake to move backwards if it's
       size is greater than one (just the head).
     */

    public void moveSnake(KeyEvent e){
	KeyCode code = e.getCode();
	if (list.size() > 1) {
	    if (code == KeyCode.LEFT && directionX != 1) {
		directionX = -1;
		directionY = 0;
	    }
	    else if (code == KeyCode.RIGHT && directionX != -1) {
		directionX = 1;
		directionY = 0;
	    }
	    else if (code == KeyCode.UP && directionY != 1) {
		directionX = 0;
		directionY = -1;
	    }
	    else if (code == KeyCode.DOWN && directionY != -1) {
		directionX = 0;
		directionY = 1;
	    }
	}
	else {
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
	}
	
    } // moveSnake
    /**
       Checks if the location for where the apple will spawn is on top of a snake part.
       @param positions An ArrayList of Integer arrays of size 2, where index 0 represents the 
       x coordinate and 1 represents the Y coordinate.
       @return boolean Whether or not the apple is on top of the snake or not.
     */
    
    public boolean checkLocation(ArrayList<Integer[]> positions) {
	for (int i = 0; i < list.size(); i++) {
	    if (positions.get(i)[0] == apple.getX() && positions.get(i)[1] == apple.getY()) {
		return true;
	    }
	}
	return false;
    }
    /**
       Creates an ArrayList of Integer arrays of size 2 that each hold the X and Y coordinates
       of the snakes locations.
       @return ArrayList<Integer[]> The locations of all the parts of the snake.
     */
    
    public ArrayList<Integer[]> snakeLocation() {
	ArrayList<Integer[]> positions = new ArrayList<Integer[]>();
	for (int i = 0; i < list.size(); i++) {
	    positions.add(new Integer[2]);
	    positions.get(i)[0] = list.get(i).getX();
	    positions.get(i)[1] = list.get(i).getY();
	}
	return positions;
    }
    /**
       Sets the instructions that will be displayed on the right of the game being played
     */
    
    public void setInstructions() {
	String string = "Use the 4 arrow keys (not on the numpad) to control the snake.\n\n";
	string += "The snake will always move in the last direction it was told to.\n\n";
	string += "If the snake runs into itself or into a wall, then the game ends.\n\n";
	string += "Collect apples to get points.\n\n";
	string += "As the snake eats apples, it will grow longer, and your score will increase.\n\n";
	string += "To win, make your snake fill up the entire game board.\n\n";
	string += "As the game progresses, your snake will leave a trail of where it has been.\n\n";
	string += "This trail will grow longer as the game goes on,\n\n";
	string += "and will last longer as the snake eats more apples.\n\n";
	string += "Score: " + score;
	instructions.setText(string);
    }
    /**
       Creates an apple object and makes sure that it's coordinates aren't on top of the snake
       @return Apple the apple object.
     */

    public Apple makeApple() {
	ArrayList<Integer[]> positions = snakeLocation();
	apple = new Apple();
	while (checkLocation(positions)) {
	    apple.setX((int) (Math.random() * 40));
	    apple.setY((int) (Math.random() * 40));
	}
	return apple;
    }
    /**
       Fills the gridpane that the game is played in with black squares in a 40 by 40 grid. 
       Also draws the first instance of the snake object nad the apple to the grid.
     */
    
    public void fillGrid() {
	apple = makeApple();
	for(int row = 0; row < 40; row++){
	    for(int col = 0; col < 40; col++) {
		Rectangle r = new Rectangle(18, 18);
		r.setStyle("-fx-background-color: #000000;");
		grid.add(r, col, row);
		if (row == apple.getY() && col == apple.getX()) {
		    grid.add(apple, col, row);
		}
		if (row == s.getY() && col == s.getX()) {		 
		    grid.add(s, col, row);
		}
	    } // for
	} // for
    }

    
}
