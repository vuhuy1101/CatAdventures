
public class FrameDef {
	int image;
	float frameTimeSecs;
	
	public FrameDef(int img, float secs){
		image = img;
		frameTimeSecs = secs;
	}
	
	public int getFrameNumber(){return image;}
	public float getFrameDuration(){return frameTimeSecs;}
}
