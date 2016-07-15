
public class AnimationData {
	AnimationDef def;
	int curFrame;
	float secsUntilNextFrame;
	float velocity;
	float x;
	float y;
	AABB shape;
	boolean collided;
	String status = "alive";
	int w;
	int h;
	int invincible;
	boolean isGrounded;
	
	float yVelocity;
	float yGravity = (float)9.8;
	
	
	public AnimationData(int startFrame, AnimationDef def, float secs, float pos[], int size[]){
		this.def = def;
		curFrame = startFrame;
		secsUntilNextFrame = secs;
		velocity = (float)5;
		x = pos[0];
		y = pos[1];
		collided = false;
		w = size[0];
		h = size[1];
		invincible = 3;
		isGrounded = false;
		yVelocity = 0;
	}
	
	
	public void update(float deltaTime){
		if(secsUntilNextFrame - deltaTime <= 0){
			// update curFrame to nextFrame
			// update time of the nextFrame
			if(curFrame < def.getFrames().length - 1)
				curFrame++;
			else curFrame = 0;
			secsUntilNextFrame = def.getFrames()[curFrame].getFrameDuration();		
		}
		else
			// update secs until next frame
			secsUntilNextFrame -= deltaTime;
	}
	
	public int getCurFrame(){return curFrame;}
	public float getSecsUntilNextFrame(){return secsUntilNextFrame;}
	public float getVelocity(){return velocity;}
	public float getX(){return x;}
	public float getY(){return y;}
	public int getWidth(){return w;}
	public int getHeight(){return h;}
	public AABB getShape(){return shape;}
	public boolean isCollided(){return collided;}
	public String getStatus(){return status;}
	public int getInvincible(){return invincible;}
	public boolean isGrounded(){return isGrounded;}
	public float[] getShootingPosition(){float arr[] = {x,y+50}; return arr;}
	public AnimationDef getDef(){return def;}
	public float getYVelocity(){return yVelocity;}
	public float getGravity(){return yGravity;}
	
	public void setX(float x){this.x = x;}
	public void setY(float y){this.y = y;}
	public void setWidth(int w){this.w = w;}
	public void setHeight(int h){this.h = h;}
	public void setVelocity(float v){velocity = v;}
	public void setShape(AABB s){shape = s;}
	public void setCollided(boolean c){collided = c;}
	public void setStatus(String s){status = s;}
	public void setInvincible(int s){invincible = s;}
	public void setGrounded(boolean b){isGrounded = b;}
	public void setYVelocity(float value){yVelocity = value;}
	
	
	//void draw(int x, int y);
}
