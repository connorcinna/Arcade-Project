package cs1302.arcade;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import java.util.ArrayList;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SnakeGame extends Application implements Playable {
    
    private ArrayList<Snake> list = new ArrayList<Snake>();
    private int directionX = 0;
    private int directionY = 0;
    
    public void play(Stage stage) {
	GridPane grid = new GridPane();
        grid.getColumnConstraints().add(new ColumnConstraints(35));
	grid.getRowConstraints().add(new RowConstraints(35));
	Snake snakeHead = new Snake();
	list.add(snakeHead);
	final Timeline timeline = new Timeline();
	EventHandler<ActionEvent> handler = makeHandlerTime(e);
	KeyFrame kf = new KeyFrame(Duration.seconds(1), handler);
	timeline.setCycleCount(Timeline.INDEFINITE);
	stage.setWidth(1280);
	stage.setHeight(720);
	Scene scene = new Scene(1280,720);
	scene.setOnKeyPressed(event -> makeHandlerKey(event));
    } // play

    public EventHandler<ActionEvent> makeHandlerTime() {
	EventHandler<ActionEvent> handler = event -> {
	    for(int i = 1; i < list.size(); i++){
		list.get(i).setX(list.get(i - 1).get(X));
		list.get(i).setY(list.get(i - 1).get(Y));
	    } // for
	    list.get(0).setX(list.get(0).getX() + directionX);
	    list.get(0).setY(list.get(0).getY() + directionY);
	};
	return handler;
    } // makeHandler

    public EventHandler<KeyEvent> makeHandlerKey(KeyEvent e) {
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
    }
    
}

/* TO DO: *********************************************************************************
 * Print Snake
 * See
 */
