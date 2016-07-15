
public class AABB {
	float x, y;
	int w, h;
	
	public AABB(float x, float y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	// Getters
	public float getX(){return x;}
	public float getY(){return y;}
	public int getW(){return w;}
	public int getH(){return h;}
	
	// Setters
	public void setX(float new_X){x = new_X;}
	public void setY(float new_Y){y = new_Y;}
	
}
