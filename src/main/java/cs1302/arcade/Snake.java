package cs1302.arcade;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class Snake extends Circle{
    int x;
    int y;
    
    public Snake() {
	super(9, Color.GREEN);
	x = (int) (Math.random()*30) + 5;
	y = (int) (Math.random()*30) + 5;
    } // Snake

    public Snake(int a, int b){
	super(9, Color.GREEN);
	x = a;
	y = b;
	
    } // Snake
    
    public int getX() {
	return x;
    } // getX
    public int getY() {
	return y;
    } // getY

    public void setX(int x) {
	this.x = x;
    }
    public void setY(int y) {
	this.y = y;
    }
}
