package cs1302.arcade;

import javafx.scene.text.Text;
import javafx.scene.text.Font;

/**
 * Node to be displayed as part of the Sudoku Game,
 * where each Tile object is either predetermined
 * and notated by being surrounding by the
 * '*' character, or it is interactable by the user,
 * in which case it is by default represented by
 * A '-' character. When pressed by a mouse, these
 * not predetermined Tile objects will increment from 
 * 1 to 9, and after pressing 9 with a mouse the number
 * displayed will return to 1.
 *
 * @author Matthew Gebara, Connor Cummings
 */
public class Tile extends Text{
    
    private int numDisplayed;
    private SudokuGame game;

    /**
     * Constructs a Tile object that either displays a
     * predetermined number notated by being surrounded by
     * a '*' character, or being a not predetermined character
     * that by default is displayed as a '-' character until it
     * is clicked with a mouse, at which point it increments from
     * 1 to 9, where when the 9 is clicked with a mouse the number
     * displayed will return to 1.
     *
     * @param num The predetermined value displayed. If nothing is predetermined should be 0
     * @param g The SudokuGame that is currently being played
     */
    public Tile(int num, SudokuGame g){
	super("*" + num + "*"); // This goes to the constructor in the Text Class
	this.game = g;
	this.setFont(new Font(30));
	numDisplayed = num;
	if(num == 0){
	    this.setText(" - ");
	    setMouseClick();
	} // if
    } // Tile

    /**
     * Creates a response from the user clicking the Tile object,
     * where the number is incremented from 1 to 9, and after the number
     * 9 is clicked, the number displayed returns to 1.  
     */
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
		if(game.isWon()){
		    game.winState(); // Checks if the user has solved the puzzle
		} // if
	    });
    } // setMouseClick

    /**
     * Returns the integer stored in this Tile object.
     *
     * @return integer stored in this Tile object
     */
    public int getNum(){
	return numDisplayed;
    } // numInside

    /**
     * Changes this Tile object from being predetermined 
     * to being a value that the user can increment using a mouse
     * click.
     */
    public void setZero(){
	this.setText(" - ");
	this.numDisplayed = 0;
	this.setMouseClick();
    } // setZero
    
} // Tile
