package cs1302.arcade;

public class Snake {
    int x;
    int y;
    

    public Snake() {
	x = (int) (Math.random()*35);
	y = (int) (Math.random()*35);
	
    } // Snake

    public Snake(int a, int b){
	x = a;
	y = b;
    } // Smake
    
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
