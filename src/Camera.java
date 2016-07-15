
public class Camera {
	private float x;
	private float y;
	private int width;
	private int height;
	
	public Camera(float x, float y, int w, int h){
		width = w;
		height = h;
		this.x = x;
		this.y = y;
	}
	
	public Camera(){
		x = 0;
		y = 0;
	}
	public float getX(){return x;}
	public float getY(){return y;}
	
	public void updateX(float newX){
		x += newX;
	}
	public void updateY(float newY){
		y += newY;
	}
	
	public void setX(float new_x){x = new_x;}
	public void setY(float new_y){y = new_y;}
}
