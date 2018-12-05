package cs1302.arcade;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import java.awt.event.KeyEvent;
import javafx.scene.input.KeyCode;

public class SudokuGame implements Playable {
    
    private Stage stage;
    private int[] fullGameData;
    
    public void play() {
	
	HBox hbox = new HBox();
	fullGameData = new int[81];
	for(int i = 0; i < 81; i++){ // TO TEST
	    fullGameData[i] = 0;
	} // for
	int[] temp = new int[9];
	for(int q = 0; q > temp.length; q++){
	    temp[q] = 0;
	} // for TEMPORARY
	BoardPiece bp = new BoardPiece(temp);
	hbox.getChildren().add(bp);
	stage = new Stage();
	Scene scene = new Scene(hbox);
	stage.setScene(scene);
	stage.sizeToScene();
	stage.show();
	
    } // play
    
} // SudokuGame
