package cs1302.arcade;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import java.awt.event.KeyEvent;
import javafx.scene.input.KeyCode;

public class SudokuGame implements Playable {
    
    private Stage stage;
    
    public void play() {
	
	HBox hbox = new HBox(new Button("Test!"));
	stage = new Stage();
	Scene scene = new Scene(hbox);
	scene.setOnKeyPressed(ke -> {
		KeyCode keyCode = ke.getCode();
		if (keyCode.equals(KeyCode.S)) {
		    System.out.println("Clicked!");
		}
	    });
	stage.setScene(scene);
	stage.sizeToScene();
	stage.show();
    } // play
}
