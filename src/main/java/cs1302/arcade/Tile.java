package cs1302.arcade;

import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class Tile extends Text{
    
    int numDisplayed;
    SudokuGame game;
    
    public Tile(int num, SudokuGame g){
	super("-" + num + "-");
	this.game = g;
	this.setFont(new Font(30));
	numDisplayed = num;
	if(num == 0){
	    this.setText(" - ");
	    setMouseClick();
	} // if
    } // Tile
    
    private void setMouseClick(){
	this.setOnMouseClicked(event -> {
		if(numDisplayed == 9){
		    numDisplayed = 1;
		    this.setText("" + numDisplayed);
		} // if
		else{
		    numDisplayed++;
		    this.setText("" + numDisplayed);
		} // else
		game.isWon();
	    });
    } // setMouseClick

    public int getNum(){
	return numDisplayed;
    } // numInside
    
    public void setZero(){
	this.setText(" - ");
	this.numDisplayed = 0;
	this.setMouseClick();
    } // setZero
    
} // Tile
