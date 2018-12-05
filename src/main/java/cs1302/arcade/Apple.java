package cs1302.arcade;

import javafx.scene.shape.Circle;
public class Apple extends Circle {

    private int appleX;
    private int appleY;
    
    public Apple() {
	int x = (int) (Math.random() * 40);
	int y = (int) (Math.random() * 40);
	appleX = x;
	appleY = y;
    }

    public Apple(int x, int y){
	appleX = x;
	appleY = y;
    }

    public int getAppleX() {
	return appleX;
    }

    public int getAppleY() {
	return appleY;
    }

    public void setAppleX(int x) {
	appleX = x;
    }

    public void setAppleY(int y) {
	appleY = y;
    }
	
}
