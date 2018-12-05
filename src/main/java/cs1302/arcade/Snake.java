package cs1302.arcade;

import javafx.scene.shape.Circle;

public class Snake extends Circle{
    int x;
    int y;
    
    public Snake() {
	super(9);
	x = (int) (Math.random()*40);
	y = (int) (Math.random()*40);
    } // Snake

    public Snake(int a, int b){
	super(9);
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
