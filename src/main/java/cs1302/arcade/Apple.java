package cs1302.arcade;

import javafx.scene.shape.Circle;
public class Apple extends Circle {

    int appleX;
    int appleY;
    
    public Apple() {
	super(9);
	appleX = (int) (Math.random() * 40);
	appleY = (int) (Math.random() * 40);
    }

    public Apple(int x, int y){
	super(9);
	appleX = x;
	appleY = y;
    }

    public int getX() {
	return appleX;
    }

    public int getY() {
	return appleY;
    }

    public void setX(int x) {
	appleX = x;
    }

    public void setY(int y) {
	appleY = y;
    }
	
}
