import java.util.ArrayList;
import java.util.Random;


public class Enemy {
	AnimationData anim;
	AnimationData target;
	AnimationData fireBullet;
	AnimationData thunderBullet;
	AnimationData tornadoBullet;
	ArrayList<AnimationData> bulletList = new ArrayList<AnimationData>();
	int speed;
	int count = 0;
	
	
	public Enemy(AnimationData a){
		anim = a;
	}
	
	public void update(float deltaTime, AnimationData targ, int[] fireSize, int[] thunderSize, int[] tornadoSize){
		anim.update(deltaTime);
		this.target = targ;
		
		//Move to target, choose new target
		//when needed
		
		if(anim.getX() == target.getX()){
			requestNewTarget();
		}
		
		//Logic
		float deltaX;
		float deltaY;
		if(anim.getY() < target.getY()){
			deltaY = Math.min(anim.getVelocity(), target.getY() - anim.getY());
		}
		else 
			deltaY = - (Math.min(anim.getVelocity(), anim.getY() - target.getY()));
		
		System.out.println(deltaY);
		//Actually move
		anim.setY(anim.getY() + ( deltaY * deltaTime / 10));		
		
		//update Bullet using behavior
		if(anim.getCurFrame() == 1 && count < 1){
			 count++;
			 Random rand = new Random();
			 float chance = rand.nextFloat();
			 createFireBullet(fireSize);
			 createThunderBullet(thunderSize);
			 createTornado(tornadoSize);
			 
			 if(chance < 0.5){ 
				 bulletList.add(fireBullet);
			 }else if(chance < 0.8){
				 bulletList.add(thunderBullet);
			 }
			 else{
				 bulletList.add(tornadoBullet);
			 }
		 }
		 if(anim.getCurFrame() == 0)
			 count = 0;	
	}

	public void requestNewTarget(){
		
	}
	
	public void createFireBullet(int[] fireSize){
		 FrameDef[] fireFrames = new FrameDef[1];
		 float frameTime = 10000;
		 FrameDef frame = new FrameDef(0, frameTime);
		 fireFrames[0] = frame;
		 AnimationDef fire = new AnimationDef("fire", fireFrames);
		 fireBullet = new AnimationData(0, fire, fireFrames[0].getFrameDuration(), new float[]{anim.getX(), anim.getY()+50}, fireSize);
		 fireBullet.setVelocity(-3);
		 AABB fireBox = new AABB(fireBullet.getX(), fireBullet.getY(), fireSize[0], fireSize[1]);
		 fireBullet.setShape(fireBox);
	}
	
	public void createThunderBullet(int[] thunderSize){
		 FrameDef[] thunderFrames = new FrameDef[1];
		 float frameTime = 10000;
		 FrameDef frame = new FrameDef(0, frameTime);
		 thunderFrames[0] = frame;
		 AnimationDef thunder = new AnimationDef("thunder", thunderFrames);
		 thunderBullet = new AnimationData(0, thunder, thunderFrames[0].getFrameDuration(), new float[]{anim.getX()-30, anim.getY()+20}, thunderSize);
		 thunderBullet.setVelocity(-3);
		 AABB thunderBox = new AABB(thunderBullet.getX(), thunderBullet.getY(), thunderSize[0], thunderSize[1]);
		 thunderBullet.setShape(thunderBox);
	}
	
	public void createTornado(int[] tornadoSize){
		 FrameDef[] tornadoFrames = new FrameDef[1];
		 float frameTime = 10000;
		 FrameDef frame = new FrameDef(0, frameTime);
		 tornadoFrames[0] = frame;
		 AnimationDef tornado = new AnimationDef("tornado", tornadoFrames);
		 tornadoBullet = new AnimationData(0, tornado, tornadoFrames[0].getFrameDuration(), new float[]{anim.getX()-65, anim.getY()-35}, tornadoSize);
		 tornadoBullet.setVelocity(-3);
		 AABB tornadoBox = new AABB(tornadoBullet.getX(), tornadoBullet.getY(), tornadoSize[0], tornadoSize[1]);
		 tornadoBullet.setShape(tornadoBox);
	}
	
	public int getSpeed(){ return speed;}
	public AnimationData getTarget(){return target;}
	public AnimationData getAnim(){return anim;}
	public ArrayList<AnimationData> getBulletList(){return bulletList;}
	
	
	public void setSpeed(int speed){this.speed = speed;}
	public void setTarget(AnimationData target){this.target = target;}
	public void setBulletList(ArrayList<AnimationData> list){bulletList = list;}
}
