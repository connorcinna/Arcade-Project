package cs1302.arcade;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;

/**
 * A node that displays a grid of nine Tile objects
 * on top of nine StackPane objects, which represents
 * a single box in the Sudoku grid.
 * 
 * @author Matthew Gebara, Connor Cummings
 */
public class BoardPiece extends GridPane{

    StackPane[] panes;
    Tile[] tiles;
    boolean[] predeterminedSpots;

    /**
     * Constructs a BoardPiece object that displays an array of
     * Tile objects taken in as a parameter in the form of a grid.
     *
     * @param inputTiles the array of Tile objects that are displayed.
     */
    public BoardPiece(Tile[] inputTiles){
	super();
	// StackPane objects store Tile objects, as StackPane objects can have a visual border
	panes = new StackPane[9];
	tiles = inputTiles;
	predeterminedSpots = new boolean[9]; // Stores the predetermined locations of numbers
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
		// Adds StackPane objects to the BoardPiece
		this.add(panes[m + (i * 3)], m, i);
	    };
	} // for
    } // BoardPiece

    /**
     * Determines whether this BoardPiece has one of each number from
     * 1-9 in its internal Tile objects, as this is one of the requirements
     * for the Sudoku Board to be considered solved.
     *
     * @return whether this BoardPiece object has one of each integer from 1-9
     */
    public boolean isSolved(){
	boolean isSolved = true;
	boolean singleFound; // checks if only a single integer is found
	for(int i = 1; i <= 9; i++){
	    singleFound = false; // There can only be one of each integer
	    for(int j = 0; j < tiles.length; j++){
		if(singleFound && tiles[j].getNum() == i){
		    singleFound = false;
		    j = tiles.length; // ends the loop as the answer is found
		} // if
		else if(tiles[j].getNum() == i){
		    singleFound = true;
		} // else if
	    } // for
	    if(singleFound == false){
		isSolved = false;
		i = 10; // ends the loop as the answer is found
	    } // if
	} // for
	return isSolved;
    } //isSolved

    /**
     * Returns the integer stored in the Tile object in
     * a specific index in this BoardPiece object.
     *
     * @param index The index of the integer to be returned in the BoardPiece
     * @return integer that is at inputted index
     */
    public int getSpot(int index){
	return tiles[index].getNum();
    } // getSpot

} // BoardPiece

