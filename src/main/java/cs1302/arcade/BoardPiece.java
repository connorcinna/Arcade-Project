package cs1302.arcade;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;

public class BoardPiece extends GridPane{

    StackPane[] panes;
    Tile[] tiles;
    boolean[] predeterminedSpots;
    
    public BoardPiece(Tile[] inputTiles){
	super();
	panes = new StackPane[9];
	tiles = inputTiles;
	predeterminedSpots = new boolean[9];
	for(int j = 0; j < tiles.length; j++){
	    if(tiles[j].getNum() == 0){
		predeterminedSpots[j] = false;
	    } // if
	    else{
		predeterminedSpots[j] = true;
	    } // else
	} // for
	for(int z = 0; z < tiles.length; z++){
	    panes[z] = new StackPane();
	    panes[z].setMinWidth(75);
	    panes[z].setMinHeight(75);
	    panes[z].getChildren().add(tiles[z]);
	    panes[z].setStyle("-fx-border-style: solid inside;"
			      + "-fx-padding: 10;"
			      + "-fx-background-color: #dc143c;"
			      + "-fx-border-width: 2;");
	} // for
	for(int i = 0; i < 3; i++){
	    for(int m = 0; m < 3; m++){
		this.add(panes[m + (i * 3)], m, i);
	    };
	} // for
    } // BoardPiece
    
    public boolean isSolved(){
	boolean isSolved = true;
	boolean singleFound; // checks if only a single integer is found
	for(int i = 1; i <= 9; i++){
	    singleFound = false;
	    for(int j = 0; j < tiles.length; j++){
		if(singleFound && tiles[j].getNum() == i){
		    singleFound = false;
		    j = tiles.length;
		} // if
		else if(tiles[j].getNum() == i){
		    singleFound = true;
		} // else if
	    } // for
	    if(singleFound == false){
		isSolved = false;
		i = 10;
	    } // if
	} // for
	return isSolved;
    } //isSolved
    
    public int getSpot(int index){
	return tiles[index].getNum();
    } // getSpot

} // BoardPiece

