
public class AnimationDef {
	String name;
	FrameDef[] frames;
	
	public AnimationDef(String n, FrameDef[] f){
		name = n;
		frames = f;
	}

	public FrameDef[] getFrames(){return frames;}
	public String getName(){return name;}
}
