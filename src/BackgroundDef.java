import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Arrays;


public class BackgroundDef {
	//background width and height
	int tileW;
	int tileH;
	int width;
	int height;
	Tile[] tiles;
	
	public BackgroundDef(int width, int height, int[] dimension, Tile[] tiles){
		tileW = dimension[0];
		tileH = dimension[1];
		this.width = width/tileW;
		this.height = height/tileH;
		int size = (width/tileW)*(height/tileH);
		Tile t0 = new Tile();
		Tile t1 = new Tile(1,true);
		this.tiles = tiles;
	}
	
	public void updateTile(int position, int img, boolean collision){
		tiles[position].setImage(img);
		tiles[position].setCollision(collision);
	}
	
	public int getTileValue(int position){
		return tiles[position].getImage();
	}
	public boolean getTileCollision(int position){
		return tiles[position].isCollided();
	}
	
	public int getTile(int x, int y){
		return (y*width + x);
	}
	
	public int getTileSize(){
		return tiles.length;
	}
	
	public int getTileW(){return tileW;}
	public int getTileH(){return tileH;}
	public int getWidth(){return width;}
	public int getHeight(){return height;}
}
	