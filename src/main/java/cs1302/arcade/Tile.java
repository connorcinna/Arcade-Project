package cs1302.arcade;

import javafx.scene.text.Text;

public class Tile extends Text{
    
    int numDisplayed;
    
    public Tile(int num){
	super("[" + num + "]");
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
	    });
    } // setMouseClick

    public int numInside(){
	return numDisplayed;
    } // numInside
    
} // Tile
