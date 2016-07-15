
public class Tile {
	int image;
	boolean coll;
	
	public Tile(){
		image = 0;
		coll = false;
	}
	
	public Tile(int image, boolean coll){
		this.image = image;
		this.coll = coll;
	}
	
	public int getImage(){return image;}
	public boolean isCollided(){return coll;}
	
	public void setImage(int img){image = img;}
	public void setCollision(boolean c){coll = c;}
}
