
package cs1302.arcade;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/**
   The class that creates all the Apples that are made in Snake. Apple extends Circle,
   because apples are just red Circles that have X and Y coordinates associated with them.
   @author Matthew Gebara, Connor Cummings
 */


public class Apple extends Circle {

    int appleX;
    int appleY;
    /**
       The constructor for apples. Uses the constructor for the Circle class, giving it a radius
       of 9 (because each tile in the game is 18x18 pixels) and filling it the color red.
       Also gives the apple a default coordinate to spawn at anywhere on the grid
    */ 
    public Apple() {
	super(9, Color.RED);
	appleX = (int) (Math.random() * 40);
	appleY = (int) (Math.random() * 40);
    }
     /**
       Overloaded constructor for the apple, allows specifying the coordinates 
       that the apple will spawn at.
     */
    public Apple(int x, int y){
	super(9);
	appleX = x;
	appleY = y;
    }
    /**
       Getter for the X coordinate of the apple object.
       @return int The X coordinate of the apple. 
    */

    public int getX() {
	return appleX;
    }
    /**
       Getter for the Y coordinate of the apple object.
       @return int The Y coordinate of the apple. 
    */
    public int getY() {
	return appleY;
    }
    /**
       Mutator for the X coordinate of the apple object.
       @param x the x coordinate the apple is being set to. 
     */

    public void setX(int x) {
	appleX = x;
    }
    /**
       Mutator for the Y coordinate of the apple object.
       @param y the y coordinate the apple is being set to. 
     */
    public void setY(int y) {
	appleY = y;
    }
	
}
