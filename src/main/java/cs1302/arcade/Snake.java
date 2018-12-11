package cs1302.arcade;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
/**
   The class that creates all the snakes that are made in Snake. Snake extends Circle,
   because snakes are just green Circles that have X and Y coordinates associated with them.

   @author Matthew Gebara, Connor Cummings
 */
public class Snake extends Circle{
    int x;
    int y;
    /**
       The constructor for snakes. Uses the constructor for the Circle class, giving it a radius
       of 9 (because each tile in the game is 18x18 pixels) and filling it the color green.
       Also gives the snake a default coordinate to spawn at.
     */
    public Snake() {
	super(9, Color.GREEN);
	//doesn't have a range of 40 so that snakes don't spawn on edges
	x = (int) (Math.random()*30) + 5; 
	y = (int) (Math.random()*30) + 5;
    } // Snake
    /**
       Overloaded constructor for the snake, allows specifying the coordinates 
       that the snake will spawn at.
     */

    public Snake(int a, int b){
	super(9, Color.GREEN);
	x = a;
	y = b;
	
    } // Snake
    /**
       Getter for the X coordinate of the snake object.
       @return int The X coordinate of the snake. 
     */
    
    public int getX() {
	return x;
    } // getX
    /**
       Getter for the Y coordinate of the snake object.
       @return int The Y coordinate of the snake. 
     */
    public int getY() {
	return y;
    } // getY
    /**
       Mutator for the X coordinate of the snake object.
       @param x the x coordinate the snake is being set to. 
     */

    public void setX(int x) {
	this.x = x;
    }
    /**
       Mutator for the Y coordinate of the snake object.
       @param y the y coordinate the snake is being set to. 
     */
    public void setY(int y) {
	this.y = y;
    }
}
