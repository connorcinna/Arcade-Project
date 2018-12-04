package cs1302.arcade;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

public class SudokuGame implements Playable {
    
    private Stage stage;
    
    public void play() {
	
	HBox hbox = new HBox(new Button("Test!"));
	stage = new Stage();
	Scene scene = new Scene(hbox);
	stage.setScene(scene);
	stage.sizeToScene();
	stage.show();
	
    } // play
}
