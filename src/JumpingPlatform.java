/**
 * Created by jared on 2/9/16.
 */

import com.jogamp.nativewindow.WindowClosingProtocol;
import com.jogamp.opengl.*;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.opengl.GLWindow;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.*;

public class JumpingPlatform {
	// Set this to true to force the game to exit.
	private static boolean shouldExit;

	// The previous frame's keyboard state.
	private static boolean kbPrevState[] = new boolean[256];

	// The current frame's keyboard state.
	private static boolean kbState[] = new boolean[256];

	// Position of the sprite.
	private static float[] spritePos = new float[] { 10, 300};
	private static float[] enemy1_1Pos = new float[] {3519,410};
	private static float[] enemy1_2Pos = new float[] {4050,306};
	private static float[] enemy1_3Pos = new float[] {4250,336};
	private static float[] enemy1_4Pos = new float[] {4450,366};
	private static float[] enemy1_5Pos = new float[] {4040,510};
	private static float[] enemy1_6Pos = new float[] {4193,510};
	private static float[] dogPos = new float[] {500,380};
	private static float[] firePos = new float[]{0, 0};
	private static float[] box1Pos = new float[]{978, 357};
	private static float[] box2Pos = new float[]{1350, 357};
	private static float[] box3Pos = new float[]{1650, 330};
	private static float[] box4Pos = new float[]{2000, 330};
	private static float[] box5Pos = new float[]{2250, 180};
	private static float[] trap1_1Pos = new float[]{2543, 204};
	private static float[] trap1_2Pos = new float[]{2543, 408};
	private static float[] trap1_3Pos = new float[]{2543, 612};
	private static float[] trap2_1Pos = new float[]{(float)2634, 306};
	private static float[] trap2_2Pos = new float[]{(float)2634, 510};
	private static float[] trap3_1Pos = new float[]{(float)2652, (float)178.5};
	private static float[] trap3_2Pos = new float[]{(float)2703, (float)178.5};
	private static float[] trap3_3Pos = new float[]{(float)2754, (float)178.5};
	private static float[] trap3_4Pos = new float[]{(float)2805, (float)178.5};
	private static float[] trap3_5Pos = new float[]{(float)2856, (float)178.5};
	private static float[] trap3_6Pos = new float[]{(float)2907, (float)178.5};
	private static float[] trap4_1Pos = new float[]{(float)3136.5, 204};
	private static float[] trap4_2Pos = new float[]{(float)3136.5, 255};
	private static float[] trap4_3Pos = new float[]{(float)3136.5, 306};
	private static float[] trap4_4Pos = new float[]{(float)3136.5, 357};
	private static float[] trap4_5Pos = new float[]{(float)3136.5, 408};
	private static float[] trap4_6Pos = new float[]{(float)3136.5, 459};
	private static float[] trap4_7Pos = new float[]{(float)3136.5, 510};
	private static float[] trap5_1Pos = new float[]{(float)2958, 204};
	private static float[] trap5_2Pos = new float[]{(float)2958, 255};
	private static float[] trap5_3Pos = new float[]{(float)2958, 306};
	private static float[] trap5_4Pos = new float[]{(float)2958, 357};
	private static float[] trap5_5Pos = new float[]{(float)2958, 408};
	private static float[] trap5_6Pos = new float[]{(float)2958, 459};
	private static float[] trap5_7Pos = new float[]{(float)2958, 510};
	private static float[] box6Pos = new float[]{3028, 510};
	private static float[] box7Pos = new float[]{3038, 357};
	private static float[] box8Pos = new float[]{3048, 227};
	private static float[] box9Pos = new float[]{3058, 255};
	
	private static float[] checkpoint1_Pos = new float[]{70, 460};
	private static float[] checkpoint2_Pos = new float[]{2430, 104};
	private static float[] lift1_Pos = new float[]{3825, 580};
	private static float[] lift2_Pos = new float[]{5406,427};
	
	private static float[] gatePos = new float[]{5500,153};

	// Texture for the sprite.
	private static int spriteTex;
	// Size of the sprite.
	private static int[] spriteSize = new int[2];
	
	private static int bgTex0;
	private static int bgTex1;
	private static int bgTex2;
	private static int[] bgSize2 = new int[2];
	private static int bgTex3;
	private static int[] bgSize3 = new int[2];
	private static int[] bgSize = new int[2];
	
	private static int boxTex1;
	private static int[] boxSize1 = new int[2];
	private static int boxTex2;
	private static int[] boxSize2 = new int[2];
	private static int boxTex3;
	private static int[] boxSize3 = new int[2];
	private static int boxTex4;
	private static int[] boxSize4 = new int[2];
	private static int boxTex5;
	private static int[] boxSize5 = new int[2];
	private static int trapTex1_1;
	private static int[] trapSize1_1 = new int[2];
	private static int trapTex1_2;
	private static int[] trapSize1_2 = new int[2];
	private static int trapTex1_3;
	private static int[] trapSize1_3 = new int[2];
	private static int trapTex2_1;
	private static int[] trapSize2_1 = new int[2];
	private static int trapTex2_2;
	private static int[] trapSize2_2 = new int[2];
	private static int trapTex3_1;
	private static int[] trapSize3_1 = new int[2];
	private static int trapTex3_2;
	private static int[] trapSize3_2 = new int[2];
	private static int trapTex3_3;
	private static int[] trapSize3_3 = new int[2];
	private static int trapTex3_4;
	private static int[] trapSize3_4 = new int[2];
	private static int trapTex3_5;
	private static int[] trapSize3_5 = new int[2];
	private static int trapTex3_6;
	private static int[] trapSize3_6 = new int[2];
	private static int checkpointTex;
	private static int[] checkpointSize = new int[2];
	
	private static int gateTex;
	private static int[] gateSize = new int[2];
	
	private static int liftTex1_1;
	private static int[] liftSize1_1 = new int[2];
	private static int liftTex1_2;
	private static int[] liftSize1_2 = new int[2];
	private static int liftTex2_1;
	private static int liftTex2_2;
	
	private static float saved_locationX = 0;
	private static float saved_locationY = 0;
	private static float saved_cameraX = 0;
	private static float saved_cameraY = 0;

	private static int count = 0;
	
	private static int dogTex;
	private static int[] dogSize = new int[2];
	
	private static int healthTex;
	private static int[] healthSize = new int[2];
	private static int healthBar = 5;
	
	private static int gravesTex;
	private static int[] gravesSize = new int[2];
	
	private static int megaManTex1;
	private static int megaManTex2;
	private static int megaManTex3;
	private static int megaManTex4;
	private static int megaManTex5;
	private static int megaManTex6;
	private static int[] megaManSize = new int[2];
	
	private static final int S_WIDTH = 1020; //25 sprites
	private static final int S_HEIGHT = 714; //14 sprites
	private static List<Enemy> enemies = new ArrayList<Enemy>();
	private static List<AnimationData> fireList = new ArrayList<AnimationData>();
	
	private static String direction = "R";
	private static int MOVABLE = 0;
	private static int rightBorder = 0;
	private static int botBorder = 0;
	private static int isMoving = 0;
	private static float[] tileLocation = new float[4];
	private static double nodmgTime = 2.5;
	
	/* Drawing background sprites */
	private static Tile t0 = new Tile();
	private static Tile t1 = new Tile(1,true);
	private static Tile t2 = new Tile(2,true);
	private static Tile t3 = new Tile(3,true);
	private static Tile[] level1 = new Tile[]{
			   t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t1,t1,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,
			   t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t1,t1,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,
			   t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t1,t1,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,
			   t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t1,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,
			   t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t0,t0,t1,t1,t1,t1,t1,t1,t0,t0,t0,t0,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,
			   t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t0,t0,t1,t1,t1,t1,t1,t1,t0,t0,t0,t0,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,
			   t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t0,t0,t1,t1,t1,t1,t1,t1,t0,t0,t0,t0,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,
			   t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t0,t0,t1,t1,t1,t1,t1,t1,t0,t0,t0,t0,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,
			   t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t0,t0,t1,t1,t1,t1,t1,t1,t0,t0,t0,t0,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,
			   t0,t0,t0,t0,t0,t0,t0,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t0,t0,t1,t1,t1,t1,t1,t1,t0,t0,t0,t0,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,
			   t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t0,t0,t1,t1,t1,t1,t1,t1,t0,t0,t0,t0,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,
			   t1,t1,t1,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,
			   t1,t1,t1,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,
			   t1,t1,t1,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t0,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1,t1
			};

	
	/* Add sound */
	
	public static void main(String[] args) {
		GLProfile gl2Profile;
		AnimationData catSprite;

		try {
			// Make sure we have a recent version of OpenGL
			//gl2Profile = GLProfile.get(GLProfile.GL2);
			gl2Profile = GLProfile.getDefault();
		}
		catch (GLException ex) {
			System.out.println("OpenGL max supported version is too low.");
			System.exit(1);
			return;
		}

		// Create the window and OpenGL context.
		GLWindow window = GLWindow.create(new GLCapabilities(gl2Profile));
		window.setSize(S_WIDTH, S_HEIGHT);
		window.setTitle("Jumping Platform");
		window.setVisible(true);
		window.setDefaultCloseOperation(WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);
		window.addKeyListener(new KeyListener() {
		    @Override
		    public void keyPressed(KeyEvent keyEvent) {
		        if (keyEvent.isAutoRepeat()) {
		            return;
		        }
		        kbState[keyEvent.getKeyCode()] = true;
		    }

		    @Override
		    public void keyReleased(KeyEvent keyEvent) {
		        if (keyEvent.isAutoRepeat()) {
		            return;
		        }
		        kbState[keyEvent.getKeyCode()] = false;
		    }
		});

		Sound jumpSound = Sound.loadFromFile("sound/jump.wav");
		
		// Setup OpenGL state.
		window.getContext().makeCurrent();
		GL2 gl = window.getGL().getGL2();
		//gl.glViewport(0, 0, S_WIDTH*2, S_HEIGHT*2);
		gl.glViewport(0, 0, S_WIDTH, S_HEIGHT);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glOrtho(0, S_WIDTH, S_HEIGHT, 0, 0, 100);
		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

		// Load the texture.
		
		bgTex0 = glTexImageTGAFile(gl, "background/bg0.tga",bgSize);
		bgTex1 = glTexImageTGAFile(gl, "background/bg1.tga",bgSize);
		bgTex2 = glTexImageTGAFile(gl, "background/bg2.tga",bgSize2);
		bgTex3 = glTexImageTGAFile(gl, "background/bg3.tga",bgSize3);
		megaManTex1 = glTexImageTGAFile(gl, "megaMan.tga", megaManSize);
		megaManTex2 = glTexImageTGAFile(gl, "megaMan.tga", megaManSize);
		megaManTex3 = glTexImageTGAFile(gl, "megaMan.tga", megaManSize);
		megaManTex4 = glTexImageTGAFile(gl, "megaMan.tga", megaManSize);
		megaManTex5 = glTexImageTGAFile(gl, "megaMan.tga", megaManSize);
		megaManTex6 = glTexImageTGAFile(gl, "megaMan.tga", megaManSize);
		spriteTex = glTexImageTGAFile(gl, "catMove0.tga", spriteSize);
		boxTex1 = glTexImageTGAFile(gl, "img/box1.tga",boxSize1);
		boxTex2 = glTexImageTGAFile(gl, "img/box2.tga", boxSize2);
		boxTex3 = glTexImageTGAFile(gl, "img/box2.tga", boxSize3);
		boxTex4 = glTexImageTGAFile(gl, "img/box2.tga", boxSize4);
		boxTex5 = glTexImageTGAFile(gl, "img/box3.tga", boxSize5);
		trapTex1_1 = glTexImageTGAFile(gl, "img/trap1.tga", trapSize1_1);
		trapTex1_2 = glTexImageTGAFile(gl, "img/trap1.tga", trapSize1_2);
		trapTex1_3 = glTexImageTGAFile(gl, "img/trap1.tga", trapSize1_3);
		trapTex2_1 = glTexImageTGAFile(gl, "img/trap2.tga", trapSize2_1);
		trapTex2_2 = glTexImageTGAFile(gl, "img/trap2.tga", trapSize2_2);
		trapTex3_1 = glTexImageTGAFile(gl, "img/trap3.tga", trapSize3_1);
		trapTex3_2 = glTexImageTGAFile(gl, "img/trap3.tga", trapSize3_2);
		trapTex3_3 = glTexImageTGAFile(gl, "img/trap3.tga", trapSize3_3);
		trapTex3_4 = glTexImageTGAFile(gl, "img/trap3.tga", trapSize3_4);
		trapTex3_5 = glTexImageTGAFile(gl, "img/trap3.tga", trapSize3_5);
		trapTex3_6 = glTexImageTGAFile(gl, "img/trap3.tga", trapSize3_6);
		checkpointTex = glTexImageTGAFile(gl, "img/checkpoint.tga", checkpointSize);
		liftTex1_1 = glTexImageTGAFile(gl, "img/lift1.tga", liftSize1_1);
		liftTex1_2 = glTexImageTGAFile(gl, "img/lift2.tga", liftSize1_2);
		liftTex2_1 = glTexImageTGAFile(gl, "img/lift1.tga", liftSize1_1);
		liftTex2_2 = glTexImageTGAFile(gl, "img/lift2.tga", liftSize1_2);
		healthTex = glTexImageTGAFile(gl, "health.tga", healthSize);
		gravesTex = glTexImageTGAFile(gl, "gravestone.tga", gravesSize);
		
		gateTex = glTexImageTGAFile(gl, "img/gate.tga", gateSize);
	   
		dogTex = glTexImageTGAFile(gl, "dog_0.tga", dogSize);
		
		System.out.println(bgSize[0] + "," + bgSize[1]);
		bgSize[0] = 5610;
		bgSize[1] = 714;
		
		healthSize[0] /= 4;
		healthSize[1] /= 4;
		
		gravesSize[0] = 50;
		gravesSize[1] = 60;
		// Create backgroundDef
		int[] dimension = {51,51};
		BackgroundDef bgDef = new BackgroundDef(5610, 714, dimension, level1);
		// Load the Camera
		Camera camera = new Camera(0,0,S_WIDTH,S_HEIGHT);
		//System.out.println(bgSize[0] + "," + bgSize[1]);
		
		// Set up right and bottom border
		rightBorder = S_WIDTH - spriteSize[0];
		botBorder = S_HEIGHT - spriteSize[1];

		// Create Array of Frames for Cat Movement
		FrameDef[] catMoveFrames = new FrameDef[3];
		for(int i = 0; i < 3;i++){
			int img = i;
			float frameTime = 300;
			FrameDef frame = new FrameDef(i,frameTime);
			catMoveFrames[i] = frame;
		}
		AnimationDef cat = new AnimationDef("catMove",catMoveFrames);
		AnimationData catData = new AnimationData(0, cat, catMoveFrames[0].getFrameDuration(), spritePos, spriteSize);
		AABB spriteBox = new AABB(spritePos[0], spritePos[1], spriteSize[0], spriteSize[1]);
		catData.setShape(spriteBox);
		
		FrameDef[] box1_frames = new FrameDef[1];
		box1_frames[0] = new FrameDef(0,1000);
		AnimationDef box1_def = new AnimationDef("box1", box1_frames);
		AnimationData box1_Data = new AnimationData(0, box1_def, box1_frames[0].getFrameDuration(), box1Pos, boxSize1);
		AABB box1_Shape = new AABB(box1Pos[0], box1Pos[1], boxSize1[0], boxSize1[1]);
		box1_Data.setShape(box1_Shape);
		box1_Data.setVelocity((float)1.3);
		
		FrameDef[] box2_frames = new FrameDef[1];
		box2_frames[0] = new FrameDef(0,1000);
		AnimationDef box2_def = new AnimationDef("box2", box2_frames);
		AnimationData box2_Data = new AnimationData(0, box2_def, box2_frames[0].getFrameDuration(), box2Pos, boxSize2);
		AABB box2_Shape = new AABB(box2Pos[0], box2Pos[1], boxSize2[0], boxSize2[1]);
		box2_Data.setShape(box2_Shape);
		box2_Data.setVelocity((float)1.3);
		
		FrameDef[] box3_frames = new FrameDef[1];
		box3_frames[0] = new FrameDef(0,1000);
		AnimationDef box3_def = new AnimationDef("box3", box3_frames);
		AnimationData box3_Data = new AnimationData(0, box3_def, box3_frames[0].getFrameDuration(), box3Pos, boxSize3);
		AABB box3_Shape = new AABB(box3Pos[0], box3Pos[1], boxSize3[0], boxSize3[1]);
		box3_Data.setShape(box3_Shape);
		box3_Data.setVelocity((float)1.3);
		
		FrameDef[] box4_frames = new FrameDef[1];
		box4_frames[0] = new FrameDef(0,1000);
		AnimationDef box4_def = new AnimationDef("box4", box4_frames);
		AnimationData box4_Data = new AnimationData(0, box4_def, box4_frames[0].getFrameDuration(), box4Pos, boxSize4);
		AABB box4_Shape = new AABB(box4Pos[0], box4Pos[1], boxSize4[0], boxSize4[1]);
		box4_Data.setShape(box4_Shape);
		box4_Data.setVelocity((float)-1.3);
		
		FrameDef[] box5_frames = new FrameDef[1];
		box5_frames[0] = new FrameDef(0,1000);
		AnimationDef box5_def = new AnimationDef("box5", box5_frames);
		AnimationData box5_Data = new AnimationData(0, box5_def, box5_frames[0].getFrameDuration(), box5Pos, boxSize5);
		AABB box5_Shape = new AABB(box5Pos[0], box5Pos[1], boxSize5[0], boxSize5[1]);
		box5_Data.setShape(box5_Shape);
		box5_Data.setVelocity((float)0);
		
		AnimationData box6_Data = new AnimationData(0, box4_def, box4_frames[0].getFrameDuration(), box6Pos, boxSize4);
		AABB box6_Shape = new AABB(box6Pos[0], box6Pos[1], boxSize4[0], boxSize4[1]);
		box6_Data.setShape(box6_Shape);
		box6_Data.setVelocity((float)-2);
		
		AnimationData box7_Data = new AnimationData(0, box4_def, box4_frames[0].getFrameDuration(), box7Pos, boxSize4);
		AABB box7_Shape = new AABB(box7Pos[0], box7Pos[1], boxSize4[0], boxSize4[1]);
		box7_Data.setShape(box7_Shape);
		box7_Data.setVelocity((float)-2);
		
		AnimationData box8_Data = new AnimationData(0, box4_def, box4_frames[0].getFrameDuration(), box8Pos, boxSize4);
		AABB box8_Shape = new AABB(box8Pos[0], box8Pos[1], boxSize4[0], boxSize4[1]);
		box8_Data.setShape(box8_Shape);
		box8_Data.setVelocity((float)-2);
		
		FrameDef[] trap1_1frames = new FrameDef[1];
		trap1_1frames[0] = new FrameDef(0,1000);
		AnimationDef trap1_1def = new AnimationDef("trap1_1", trap1_1frames);
		AnimationData trap1_1Data = new AnimationData(0, trap1_1def, trap1_1frames[0].getFrameDuration(), trap1_1Pos, trapSize1_1);
		AABB trap1_1Shape = new AABB(trap1_1Pos[0], trap1_1Pos[1], trapSize1_1[0], trapSize1_1[1]);
		trap1_1Data.setShape(trap1_1Shape);
		trap1_1Data.setVelocity((float)0);
		
		FrameDef[] trap1_2frames = new FrameDef[1];
		trap1_2frames[0] = new FrameDef(0,1000);
		AnimationDef trap1_2def = new AnimationDef("trap1_2", trap1_2frames);
		AnimationData trap1_2Data = new AnimationData(0, trap1_2def, trap1_2frames[0].getFrameDuration(), trap1_2Pos, trapSize1_2);
		AABB trap1_2Shape = new AABB(trap1_2Pos[0], trap1_2Pos[1], trapSize1_2[0], trapSize1_2[1]);
		trap1_2Data.setShape(trap1_2Shape);
		trap1_2Data.setVelocity((float)0);
		
		FrameDef[] trap1_3frames = new FrameDef[1];
		trap1_3frames[0] = new FrameDef(0,1000);
		AnimationDef trap1_3def = new AnimationDef("trap1_3", trap1_3frames);
		AnimationData trap1_3Data = new AnimationData(0, trap1_3def, trap1_3frames[0].getFrameDuration(), trap1_3Pos, trapSize1_3);
		AABB trap1_3Shape = new AABB(trap1_3Pos[0], trap1_3Pos[1], trapSize1_3[0], trapSize1_3[1]);
		trap1_3Data.setShape(trap1_3Shape);
		trap1_3Data.setVelocity((float)0);
		
		FrameDef[] trap2_1frames = new FrameDef[1];
		trap2_1frames[0] = new FrameDef(0,1000);
		AnimationDef trap2_1def = new AnimationDef("trap2_1", trap2_1frames);
		AnimationData trap2_1Data = new AnimationData(0, trap2_1def, trap2_1frames[0].getFrameDuration(), trap2_1Pos, trapSize2_1);
		AABB trap2_1Shape = new AABB(trap2_1Pos[0], trap2_1Pos[1], trapSize2_1[0], trapSize2_1[1]);
		trap2_1Data.setShape(trap2_1Shape);
		trap2_1Data.setVelocity((float)0);
		
		FrameDef[] trap2_2frames = new FrameDef[1];
		trap2_2frames[0] = new FrameDef(0,1000);
		AnimationDef trap2_2def = new AnimationDef("trap2_2", trap2_2frames);
		AnimationData trap2_2Data = new AnimationData(0, trap2_2def, trap2_2frames[0].getFrameDuration(), trap2_2Pos, trapSize2_2);
		AABB trap2_2Shape = new AABB(trap2_2Pos[0], trap2_2Pos[1], trapSize2_2[0], trapSize2_2[1]);
		trap2_2Data.setShape(trap2_2Shape);
		trap2_2Data.setVelocity((float)0);
		
		FrameDef[] trap3_1frames = new FrameDef[1];
		trap3_1frames[0] = new FrameDef(0,1000);
		AnimationDef trap3_1def = new AnimationDef("trap3_1", trap3_1frames);
		AnimationData trap3_1Data = new AnimationData(0, trap3_1def, trap3_1frames[0].getFrameDuration(), trap3_1Pos, trapSize3_1);
		AABB trap3_1Shape = new AABB(trap3_1Pos[0], trap3_1Pos[1], trapSize3_1[0], trapSize3_1[1]);
		trap3_1Data.setShape(trap3_1Shape);
		trap3_1Data.setVelocity((float)0);
		
		FrameDef[] trap3_2frames = new FrameDef[1];
		trap3_2frames[0] = new FrameDef(0,1000);
		AnimationDef trap3_2def = new AnimationDef("trap3_2", trap3_2frames);
		AnimationData trap3_2Data = new AnimationData(0, trap3_2def, trap3_2frames[0].getFrameDuration(), trap3_2Pos, trapSize3_2);
		AABB trap3_2Shape = new AABB(trap3_2Pos[0], trap3_2Pos[1], trapSize3_2[0], trapSize3_2[1]);
		trap3_2Data.setShape(trap3_1Shape);
		trap3_2Data.setVelocity((float)0);
		
		FrameDef[] trap3_3frames = new FrameDef[1];
		trap3_3frames[0] = new FrameDef(0,1000);
		AnimationDef trap3_3def = new AnimationDef("trap3_3", trap3_3frames);
		AnimationData trap3_3Data = new AnimationData(0, trap3_3def, trap3_3frames[0].getFrameDuration(), trap3_3Pos, trapSize3_3);
		AABB trap3_3Shape = new AABB(trap3_3Pos[0], trap3_3Pos[1], trapSize3_3[0], trapSize3_3[1]);
		trap3_3Data.setShape(trap3_3Shape);
		trap3_3Data.setVelocity((float)0);
		
		FrameDef[] trap3_4frames = new FrameDef[1];
		trap3_4frames[0] = new FrameDef(0,1000);
		AnimationDef trap3_4def = new AnimationDef("trap3_4", trap3_4frames);
		AnimationData trap3_4Data = new AnimationData(0, trap3_4def, trap3_4frames[0].getFrameDuration(), trap3_4Pos, trapSize3_4);
		AABB trap3_4Shape = new AABB(trap3_4Pos[0], trap3_4Pos[1], trapSize3_4[0], trapSize3_4[1]);
		trap3_4Data.setShape(trap3_1Shape);
		trap3_4Data.setVelocity((float)0);
		
		FrameDef[] trap3_5frames = new FrameDef[1];
		trap3_5frames[0] = new FrameDef(0,1000);
		AnimationDef trap3_5def = new AnimationDef("trap3_5", trap3_5frames);
		AnimationData trap3_5Data = new AnimationData(0, trap3_5def, trap3_5frames[0].getFrameDuration(), trap3_5Pos, trapSize3_5);
		AABB trap3_5Shape = new AABB(trap3_5Pos[0], trap3_5Pos[1], trapSize3_5[0], trapSize3_5[1]);
		trap3_5Data.setShape(trap3_5Shape);
		trap3_5Data.setVelocity((float)0);
		
		FrameDef[] trap3_6frames = new FrameDef[1];
		trap3_6frames[0] = new FrameDef(0,1000);
		AnimationDef trap3_6def = new AnimationDef("trap3_6", trap3_6frames);
		AnimationData trap3_6Data = new AnimationData(0, trap3_6def, trap3_6frames[0].getFrameDuration(), trap3_6Pos, trapSize3_6);
		AABB trap3_6Shape = new AABB(trap3_6Pos[0], trap3_6Pos[1], trapSize3_6[0], trapSize3_6[1]);
		trap3_6Data.setShape(trap3_6Shape);
		trap3_6Data.setVelocity((float)0);
		
		AnimationData trap4_1Data = new AnimationData(0, trap2_1def, trap2_1frames[0].getFrameDuration(), trap4_1Pos, trapSize2_1);
		AABB trap4_1Shape = new AABB(trap4_1Pos[0], trap4_1Pos[1], trapSize2_1[0], trapSize2_1[1]);
		trap4_1Data.setShape(trap4_1Shape);
		trap4_1Data.setVelocity((float)0);
		
		AnimationData trap4_2Data = new AnimationData(0, trap2_1def, trap2_1frames[0].getFrameDuration(), trap4_2Pos, trapSize2_1);
		AABB trap4_2Shape = new AABB(trap4_2Pos[0], trap4_2Pos[1], trapSize2_1[0], trapSize2_1[1]);
		trap4_2Data.setShape(trap4_2Shape);
		trap4_2Data.setVelocity((float)0);
		
		AnimationData trap4_3Data = new AnimationData(0, trap2_1def, trap2_1frames[0].getFrameDuration(), trap4_3Pos, trapSize2_1);
		AABB trap4_3Shape = new AABB(trap4_3Pos[0], trap4_3Pos[1], trapSize2_1[0], trapSize2_1[1]);
		trap4_3Data.setShape(trap4_3Shape);
		trap4_3Data.setVelocity((float)0);
		
		AnimationData trap4_4Data = new AnimationData(0, trap2_1def, trap2_1frames[0].getFrameDuration(), trap4_4Pos, trapSize2_1);
		AABB trap4_4Shape = new AABB(trap4_4Pos[0], trap4_4Pos[1], trapSize2_1[0], trapSize2_1[1]);
		trap4_4Data.setShape(trap4_4Shape);
		trap4_4Data.setVelocity((float)0);
		
		AnimationData trap4_5Data = new AnimationData(0, trap2_1def, trap2_1frames[0].getFrameDuration(), trap4_5Pos, trapSize2_1);
		AABB trap4_5Shape = new AABB(trap4_5Pos[0], trap4_5Pos[1], trapSize2_1[0], trapSize2_1[1]);
		trap4_5Data.setShape(trap4_5Shape);
		trap4_5Data.setVelocity((float)0);
		
		AnimationData trap4_6Data = new AnimationData(0, trap2_1def, trap2_1frames[0].getFrameDuration(), trap4_6Pos, trapSize2_1);
		AABB trap4_6Shape = new AABB(trap4_6Pos[0], trap4_6Pos[1], trapSize2_1[0], trapSize2_1[1]);
		trap4_6Data.setShape(trap4_6Shape);
		trap4_6Data.setVelocity((float)0);
		
		AnimationData trap4_7Data = new AnimationData(0, trap2_1def, trap2_1frames[0].getFrameDuration(), trap4_7Pos, trapSize2_1);
		AABB trap4_7Shape = new AABB(trap4_7Pos[0], trap4_7Pos[1], trapSize2_1[0], trapSize2_1[1]);
		trap4_7Data.setShape(trap4_7Shape);
		trap4_7Data.setVelocity((float)0);
		
		AnimationData trap5_1Data = new AnimationData(0, trap1_1def, trap1_1frames[0].getFrameDuration(), trap5_1Pos, trapSize1_1);
		AABB trap5_1Shape = new AABB(trap5_1Pos[0], trap5_1Pos[1], trapSize1_1[0], trapSize1_1[1]);
		trap5_1Data.setShape(trap4_1Shape);
		trap5_1Data.setVelocity((float)0);
		
		AnimationData trap5_2Data = new AnimationData(0, trap1_1def, trap1_1frames[0].getFrameDuration(), trap5_2Pos, trapSize1_1);
		AABB trap5_2Shape = new AABB(trap5_2Pos[0], trap5_2Pos[1], trapSize1_1[0], trapSize1_1[1]);
		trap5_2Data.setShape(trap5_2Shape);
		trap5_2Data.setVelocity((float)0);
		
		AnimationData trap5_3Data = new AnimationData(0, trap1_1def, trap1_1frames[0].getFrameDuration(), trap5_3Pos, trapSize1_1);
		AABB trap5_3Shape = new AABB(trap5_3Pos[0], trap5_3Pos[1], trapSize1_1[0], trapSize1_1[1]);
		trap5_3Data.setShape(trap5_3Shape);
		trap5_3Data.setVelocity((float)0);
		
		AnimationData trap5_4Data = new AnimationData(0, trap1_1def, trap1_1frames[0].getFrameDuration(), trap5_4Pos, trapSize1_1);
		AABB trap5_4Shape = new AABB(trap5_4Pos[0], trap5_4Pos[1], trapSize1_1[0], trapSize1_1[1]);
		trap5_4Data.setShape(trap5_4Shape);
		trap5_4Data.setVelocity((float)0);
		
		AnimationData trap5_5Data = new AnimationData(0, trap1_1def, trap1_1frames[0].getFrameDuration(), trap5_5Pos, trapSize2_1);
		AABB trap5_5Shape = new AABB(trap5_5Pos[0], trap5_5Pos[1], trapSize2_1[0], trapSize2_1[1]);
		trap5_5Data.setShape(trap5_5Shape);
		trap5_5Data.setVelocity((float)0);
		
		AnimationData trap5_6Data = new AnimationData(0, trap1_1def, trap1_1frames[0].getFrameDuration(), trap5_6Pos, trapSize1_1);
		AABB trap5_6Shape = new AABB(trap5_6Pos[0], trap5_6Pos[1], trapSize1_1[0], trapSize1_1[1]);
		trap5_6Data.setShape(trap5_6Shape);
		trap5_6Data.setVelocity((float)0);
		
		AnimationData trap5_7Data = new AnimationData(0, trap1_1def, trap1_1frames[0].getFrameDuration(), trap5_7Pos, trapSize1_1);
		AABB trap5_7Shape = new AABB(trap5_7Pos[0], trap5_7Pos[1], trapSize1_1[0], trapSize1_1[1]);
		trap5_7Data.setShape(trap5_7Shape);
		trap5_7Data.setVelocity((float)0);
		
		
		FrameDef[] enemy1_1frames = new FrameDef[1];
		enemy1_1frames[0] = new FrameDef(0,1000);
		AnimationDef enemy1_1def = new AnimationDef("enemy1_1", enemy1_1frames);
		AnimationData enemy1_1Data = new AnimationData(0, enemy1_1def, enemy1_1frames[0].getFrameDuration(), enemy1_1Pos, megaManSize);
		AABB enemy1_1Shape = new AABB(enemy1_1Pos[0], enemy1_1Pos[1], megaManSize[0], megaManSize[1]);
		enemy1_1Data.setShape(enemy1_1Shape);
		enemy1_1Data.setVelocity((float)0);
		
		AnimationData enemy1_2Data = new AnimationData(0, enemy1_1def, enemy1_1frames[0].getFrameDuration(), enemy1_2Pos, megaManSize);
		AABB enemy1_2Shape = new AABB(enemy1_2Pos[0], enemy1_2Pos[1], megaManSize[0], megaManSize[1]);
		enemy1_2Data.setShape(enemy1_2Shape);
		enemy1_2Data.setVelocity((float)1.5);
		
		AnimationData enemy1_3Data = new AnimationData(0, enemy1_1def, enemy1_1frames[0].getFrameDuration(), enemy1_3Pos, megaManSize);
		AABB enemy1_3Shape = new AABB(enemy1_3Pos[0], enemy1_3Pos[1], megaManSize[0], megaManSize[1]);
		enemy1_3Data.setShape(enemy1_3Shape);
		enemy1_3Data.setVelocity((float)1.5);

		AnimationData enemy1_4Data = new AnimationData(0, enemy1_1def, enemy1_1frames[0].getFrameDuration(), enemy1_4Pos, megaManSize);
		AABB enemy1_4Shape = new AABB(enemy1_4Pos[0], enemy1_4Pos[1], megaManSize[0], megaManSize[1]);
		enemy1_4Data.setShape(enemy1_4Shape);
		enemy1_4Data.setVelocity((float)1.5);
		
		AnimationData enemy1_5Data = new AnimationData(0, enemy1_1def, enemy1_1frames[0].getFrameDuration(), enemy1_5Pos, megaManSize);
		AABB enemy1_5Shape = new AABB(enemy1_5Pos[0], enemy1_5Pos[1], megaManSize[0], megaManSize[1]);
		enemy1_5Data.setShape(enemy1_5Shape);
		enemy1_5Data.setVelocity((float)0);
		
		AnimationData enemy1_6Data = new AnimationData(0, enemy1_1def, enemy1_1frames[0].getFrameDuration(), enemy1_6Pos, megaManSize);
		AABB enemy1_6Shape = new AABB(enemy1_6Pos[0], enemy1_6Pos[1], megaManSize[0], megaManSize[1]);
		enemy1_6Data.setShape(enemy1_6Shape);
		enemy1_6Data.setVelocity((float)0);
		
		///////////////////
		FrameDef[] checkpoint1_frames = new FrameDef[1];
		checkpoint1_frames[0] = new FrameDef(0,1000);
		AnimationDef checkpoint1_def = new AnimationDef("checkpoint1", checkpoint1_frames);
		AnimationData checkpoint1_Data = new AnimationData(0, checkpoint1_def, checkpoint1_frames[0].getFrameDuration(), checkpoint1_Pos, checkpointSize);
		AABB checkpoint1_Shape = new AABB(checkpoint1_Pos[0], checkpoint1_Pos[1], checkpointSize[0], checkpointSize[1]);
		checkpoint1_Data.setShape(checkpoint1_Shape);
		checkpoint1_Data.setVelocity((float)0);
		
		AnimationData checkpoint2_Data = new AnimationData(0, checkpoint1_def, checkpoint1_frames[0].getFrameDuration(), checkpoint2_Pos, checkpointSize);
		AABB checkpoint2_Shape = new AABB(checkpoint2_Pos[0], checkpoint2_Pos[1], checkpointSize[0], checkpointSize[1]);
		checkpoint2_Data.setShape(checkpoint2_Shape);
		checkpoint2_Data.setVelocity((float)0);
		
		/////////////////////////
		FrameDef[] lift_frames = new FrameDef[1];
		lift_frames[0] = new FrameDef(0,1000);
		AnimationDef lift_def = new AnimationDef("lift1", lift_frames);
		AnimationData lift1_Data = new AnimationData(0, lift_def, lift_frames[0].getFrameDuration(), lift1_Pos, liftSize1_1);
		AABB lift1_Shape = new AABB(lift1_Pos[0], lift1_Pos[1], liftSize1_1[0], liftSize1_1[1]);
		lift1_Data.setShape(lift1_Shape);
		lift1_Data.setVelocity((float)0);	
		
		AnimationData lift2_Data = new AnimationData(0, lift_def, lift_frames[0].getFrameDuration(), lift1_Pos, liftSize1_1);
		AABB lift2_Shape = new AABB(lift2_Pos[0], lift2_Pos[1], liftSize1_1[0], liftSize1_1[1]);
		lift2_Data.setShape(lift2_Shape);
		lift2_Data.setVelocity((float)0);	
		
	//Load dog animation
		FrameDef[] dogFrames = new FrameDef[2];
		float frameTime = 800;
		FrameDef frame = new FrameDef(0, frameTime);
		dogFrames[0] = frame;
		frameTime = 400;
		frame = new FrameDef(1, frameTime);
		dogFrames[1] = frame;
		AnimationDef dog = new AnimationDef("dog", dogFrames);
		AnimationData dogData = new AnimationData(0, dog, dogFrames[0].getFrameDuration(), dogPos, dogSize);
		AABB dogBox = new AABB(dogPos[0], dogPos[1], dogSize[0], dogSize[1]);
		dogData.setShape(dogBox);
		dogData.setVelocity((float)0.5);
		
		Enemy dogEnemy = new Enemy(dogData);
		enemies.add(dogEnemy);
		
	   //Set up Timing
		//Physics run at 100fps aka 10ms/frame
		long lastFrameNS;
		long curFrameNS = System.nanoTime();
		int physicsDeltaMs = 10;
		int lastPhysicsFrameMs;
		int curFrameMs = (int)(curFrameNS / 1000000);
		
		//bgDef.updateTile(bgDef.getTile(2, 11), 1);  
		//bgDef.updateTile(bgDef.getTile(20, 10), 1);
		
	 // Create AABB Test   
	   
		int c = 0;
		
	/* The game loop */
		while (!shouldExit) {
			System.arraycopy(kbState, 0, kbPrevState, 0, kbState.length);
			lastFrameNS = curFrameNS;
			lastPhysicsFrameMs = (int)(lastFrameNS / 1000000);

			// Actually, this runs the entire OS message pump.
			window.display();
			if (!window.isVisible()) {
				shouldExit = true;
				break;
				
			}           
			//update new currentFrameMS and calculate deltaTime
			curFrameNS = System.nanoTime();
			float deltaTimeMS = (curFrameNS - lastFrameNS) / 1000000;
		
			curFrameMs = (int)(curFrameNS/1000000);
			
			AABB cameraBox = new AABB(camera.getX(), camera.getY(), S_WIDTH, S_HEIGHT);
	
	/* PLayer.Update(deltaTimeMS) */
			
		//*****************/    
		/* Physics update */
		//*****************/    	
			do{
					//Physics movement
					// Game logic.
					if (kbState[KeyEvent.VK_ESCAPE]) {
						shouldExit = true;
					}
					
					
					if (kbState[KeyEvent.VK_LEFT] && catData.getX() > 0) {
						double halfScreen = 0.5*S_WIDTH - (0.5)*catData.getWidth();
						if((catData.getX() < halfScreen) && camera.getX() > 0){
							camera.updateX(-catData.getVelocity());
						}
						else{
							float x = catData.getX() - catData.getVelocity();
							catData.setX(x);
							
						}
					
					   
						direction = "L";
						catData.getShape().setX(catData.getX()+camera.getX());
					}

					if (kbState[KeyEvent.VK_RIGHT] && catData.getX() < rightBorder) {
						// If the sprite moves to the middle of the screen, then the background will
						// move as the sprite continues moving
						double halfScreen = 0.5*S_WIDTH - (0.5)*catData.getWidth();
						if((catData.getX() >= halfScreen) && camera.getX() < (bgSize[0]-S_WIDTH-5)){
							camera.updateX(catData.getVelocity());
						}
						else{
							float x = catData.getX() + catData.getVelocity();
							catData.setX(x);
						}
						direction = "R";
						catData.getShape().setX(catData.getX()+camera.getX());
					}
					
				/* Horizontal physics (collision detection and resolution) */
					float left1 = catData.getShape().getX();
				   	float right1 = catData.getShape().getX() + catData.getShape().getW();
				   	
					//Background collision 
					int start_tileX = (int) Math.floor(camera.getX() / bgDef.getTileW());
					int start_tileY = (int) Math.floor(camera.getY() / bgDef.getTileH());
					int end_tileX = (int) Math.floor((camera.getX() + S_WIDTH) / bgDef.getTileW());
					int end_tileY = (int) Math.floor((camera.getY() + S_HEIGHT) / bgDef.getTileH());
					
					int flag = 0;
					
					for(int i = start_tileY; i < end_tileY;i++){
						for(int j = start_tileX; j <= end_tileX; j++){
							int pos = bgDef.getTile(j,i);
				
							AABB bgBox = new AABB(j*bgDef.getTileW(), i*bgDef.getTileH(), 51, 51);
							if(bgDef.getTileCollision(pos) == true && AABBIntersect(bgBox, catData.getShape()) && flag == 0 && catData.isGrounded() == false){
								System.out.println("Horizontal Collision detected");
								flag = 1;
								float right2 = bgBox.getX()+bgDef.getTileW();
								float bot2 = bgBox.getY() + bgDef.getTileH();
								
								if(right1 > bgBox.getX() && direction == "R"){
									catData.setX(catData.getX() - (right1 - bgBox.getX()));
									System.out.println("work");
								}
								if(left1 < right2 && direction == "L"){
									catData.setX(catData.getX() + (right2 - left1));
									
								}
								catData.getShape().setX(catData.getX()+camera.getX());
							}
							else flag = 0;
						}
					}
					
					
					/*Horizontal collision with other SPRITES*/
					if(AABBIntersect(catData.getShape(), checkpoint1_Data.getShape())){
						//Physics collision resolution w/ wall: Back up player to prev position
						saved_locationX = checkpoint1_Data.getShape().getX();
						saved_locationY = checkpoint1_Data.getShape().getY()-15;
						saved_cameraX = camera.getX();
						saved_cameraY = camera.getY();
					}
					
					if(AABBIntersect(catData.getShape(), checkpoint2_Data.getShape())){
						//Physics collision resolution w/ wall: Back up player to prev position
						saved_locationX = checkpoint2_Data.getShape().getX();
						saved_locationY = checkpoint2_Data.getShape().getY()-15;
						saved_cameraX = camera.getX();
						saved_cameraY = camera.getY();
					}
				
				
					if(AABBIntersect(catData.getShape(), trap1_1Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());	
					}
					if(AABBIntersect(catData.getShape(), trap1_2Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap1_3Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap2_1Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap2_2Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap3_1Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap3_2Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap3_3Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap3_4Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap3_5Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap3_6Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					
					if(AABBIntersect(catData.getShape(), trap4_1Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap4_2Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap4_3Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap4_4Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap4_5Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap4_6Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap4_7Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					
					if(AABBIntersect(catData.getShape(), trap5_1Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap5_2Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap5_3Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap5_4Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap5_5Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap5_6Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
					if(AABBIntersect(catData.getShape(), trap5_7Data.getShape())){
						catData.setY(1000);
						catData.getShape().setY(catData.getY());
					}
				
				
				/*End horizontal collision detection and resolution */

				/* Start vertical collision detection and resolution */
					float yVelocity;
					/* Check whether character is on the ground (Action Motion) */
					if(catData.isGrounded()){
						catData.setYVelocity(0);
						yVelocity = 0;
					}
					/* End checking */
					


					if (kbState[KeyEvent.VK_UP] && catData.getY() > 0 && catData.isGrounded()) {
						jumpSound.play();
						if((catData.getY() <= 0.3*S_HEIGHT) && camera.getY() > 0){
							camera.updateY(-catData.getVelocity());
						}
						catData.setYVelocity(-20);
						catData.setGrounded(false);
						catData.getShape().setY(catData.getY()+camera.getY());
						direction = "U"; 
					}
					
					catData.setYVelocity(catData.getYVelocity() + catData.getGravity()*deltaTimeMS/100);
					yVelocity = catData.getYVelocity() + catData.getGravity()*deltaTimeMS/100;
					catData.setY(catData.getY() + catData.getYVelocity()*deltaTimeMS/100);
					catData.getShape().setY(catData.getY()+camera.getY());

					if (kbState[KeyEvent.VK_DOWN] || catData.getY() < botBorder) {
						if((catData.getY() >= 0.69*S_HEIGHT) && camera.getY() < (bgSize[1]-S_HEIGHT)){
							camera.updateY(catData.getVelocity());
						}
						else{
							float y = catData.getY() + catData.getYVelocity();
							catData.setY(y);
							catData.getShape().setY(catData.getY()+camera.getY());
						}

					}

					catData.update(deltaTimeMS);
		            String nameTGA = "catMove" + catData.getCurFrame() + ".tga";
		            spriteTex = glTexImageTGAFile(gl, nameTGA, spriteSize);
		
					/* Background Collision detection and resolution */
					float top1 = catData.getShape().getY();
					float bot1 = catData.getShape().getY() + catData.getShape().getH(); 
					for(int i = start_tileY; i < end_tileY;i++){
						for(int j = start_tileX; j <= end_tileX; j++){
							int pos = bgDef.getTile(j,i);
				
							//System.out.println(catData.getYVelocity());
							AABB bgBox = new AABB(j*bgDef.getTileW(), i*bgDef.getTileH(), 51, 51);
							if(bgDef.getTileCollision(pos) == true && AABBIntersect(bgBox, catData.getShape()) && flag == 0){
								//System.out.println("Vertical Collision detected");
								flag = 1;
								float right2 = bgBox.getX()+bgDef.getTileW();
								float bot2 = bgBox.getY() + bgDef.getTileH();
								
								 if(bot1 > bgBox.getY()){
									catData.setY(catData.getY() - (bot1-bgBox.getY()));
									catData.setGrounded(true);
									catData.setYVelocity(0);
								}	
								
							}
							else{ 
								flag = 0;	
								if(catData.getYVelocity() > 0){
									catData.setGrounded(false);
									if(catData.getYVelocity() > 5)
										catData.setYVelocity(5);
								}
							}
							catData.getShape().setY(catData.getY()+camera.getY());
						}
					}
					
					/*Vertical collision detection/resolution with other SPRITES */
					
					
					 //Vertical collision and resolution w/ other SPRITES
					if(AABBIntersect(catData.getShape(), enemy1_1Data.getShape())){					
						//Physics collision resolution w/ wall: Back up player to prev position
						top1 = catData.getShape().getY();
						bot1 = catData.getShape().getY() + catData.getShape().getH(); 	
						float top2 = enemy1_1Data.getShape().getY();
						float bot2 = enemy1_1Data.getShape().getY()+enemy1_1Data.getShape().getH();

						//top1 < bot2
						if(top2 < bot1 && catData.getYVelocity() > 0){
							jumpSound.play();
							catData.setY(catData.getY() - (bot1-top2));
							catData.setYVelocity(-25);
			
							enemy1_1Data.setY(1000);
							enemy1_1Data.getShape().setY(enemy1_1Data.getY());
							System.out.println("HIT");
						}
						catData.getShape().setY(catData.getY()+camera.getY());	
					} 
					
					if(AABBIntersect(catData.getShape(), enemy1_2Data.getShape())){					
						//Physics collision resolution w/ wall: Back up player to prev position
						top1 = catData.getShape().getY();
						bot1 = catData.getShape().getY() + catData.getShape().getH(); 	
						float top2 = enemy1_2Data.getShape().getY();
						float bot2 = enemy1_2Data.getShape().getY()+enemy1_2Data.getShape().getH();

						//top1 < bot2
						if(top2 < bot1 && catData.getYVelocity() > 0){
							jumpSound.play();
							catData.setY(catData.getY() - (bot1-top2));
							catData.setYVelocity(-25);
			
							//enemy1_1Data.setYVelocity(20);
							enemy1_2Data.setY(1000);
							enemy1_2Data.getShape().setY(enemy1_2Data.getY());
							//enemy1_1Data.getShape().setY(enemy1_1Data.getY()+camera.getY());
							System.out.println("HIT");
						}
						catData.getShape().setY(catData.getY()+camera.getY());	
					}
					
					if(AABBIntersect(catData.getShape(), enemy1_3Data.getShape())){					
						//Physics collision resolution w/ wall: Back up player to prev position
						top1 = catData.getShape().getY();
						bot1 = catData.getShape().getY() + catData.getShape().getH(); 	
						float top2 = enemy1_3Data.getShape().getY();
						float bot2 = enemy1_3Data.getShape().getY()+enemy1_3Data.getShape().getH();

						//top1 < bot2
						if(top2 < bot1 && catData.getYVelocity() > 0){
							jumpSound.play();
							catData.setY(catData.getY() - (bot1-top2));
							catData.setYVelocity(-25);
			
							//enemy1_1Data.setYVelocity(20);
							enemy1_3Data.setY(1000);
							enemy1_3Data.getShape().setY(enemy1_3Data.getY());
							//enemy1_1Data.getShape().setY(enemy1_1Data.getY()+camera.getY());
							//System.out.println("HIT");
						}
						catData.getShape().setY(catData.getY()+camera.getY());	
					}
					
					if(AABBIntersect(catData.getShape(), enemy1_4Data.getShape())){					
						//Physics collision resolution w/ wall: Back up player to prev position
						top1 = catData.getShape().getY();
						bot1 = catData.getShape().getY() + catData.getShape().getH(); 	
						float top2 = enemy1_4Data.getShape().getY();
						float bot2 = enemy1_4Data.getShape().getY()+enemy1_4Data.getShape().getH();

						//top1 < bot2
						if(top2 < bot1 && catData.getYVelocity() > 0){
							jumpSound.play();
							catData.setY(catData.getY() - (bot1-top2));
							catData.setYVelocity(-25);
			
							enemy1_4Data.setY(1000);
							enemy1_4Data.getShape().setY(enemy1_4Data.getY());
						}
						catData.getShape().setY(catData.getY()+camera.getY());	
					}
					
					if(AABBIntersect(catData.getShape(), lift1_Data.getShape())){					
						//Physics collision resolution w/ wall: Back up player to prev position
						top1 = catData.getShape().getY();
						bot1 = catData.getShape().getY() + catData.getShape().getH(); 	
						float top2 = lift1_Data.getShape().getY();
						float bot2 = lift1_Data.getShape().getY()+lift1_Data.getShape().getH();

						//top1 < bot2
						if(top2 < bot1 && catData.getYVelocity() > 0){
							jumpSound.play();
							catData.setY(catData.getY() - (bot1-top2));
							catData.setYVelocity(-28);
			
							//enemy1_1Data.getShape().setY(enemy1_1Data.getY()+camera.getY());
							System.out.println("HIT");
						}
						catData.getShape().setY(catData.getY()+camera.getY());	
					}
					
					
					
					if(AABBIntersect(catData.getShape(), box1_Data.getShape())){
						//Physics collision resolution w/ wall: Back up player to prev position
						top1 = catData.getShape().getY();
						bot1 = catData.getShape().getY() + catData.getShape().getH();
						float top2 = box1_Data.getShape().getY();
						float bot2 = box1_Data.getShape().getY()+box1_Data.getShape().getH();
						
						//right1 > left2
						if(top2 < bot1 && catData.getYVelocity() > 0){
							catData.setY(catData.getY() - (bot1-top2));
							catData.setYVelocity(0);
							catData.setGrounded(true);
							//System.out.println("HIT");
						}
						catData.getShape().setY(catData.getY()+camera.getY());	
					}
					
					if(AABBIntersect(catData.getShape(), box2_Data.getShape())){
						//Physics collision resolution w/ wall: Back up player to prev position
						top1 = catData.getShape().getY();
						bot1 = catData.getShape().getY() + catData.getShape().getH();
						float top2 = box2_Data.getShape().getY();
						float bot2 = box2_Data.getShape().getY()+box2_Data.getShape().getH();
						
						//right1 > left2
						if(top2 < bot1 && catData.getYVelocity() > 0){
							catData.setY(catData.getY() - (bot1-top2));
							catData.setYVelocity(0);
							catData.setX(catData.getX() + box2_Data.getVelocity());
							
							catData.setGrounded(true);
							//System.out.println("HIT");
						}
						catData.getShape().setY(catData.getY()+camera.getY());	
						catData.getShape().setX(catData.getX()+camera.getX());
					}
					
					if(AABBIntersect(catData.getShape(), box3_Data.getShape())){
						//Physics collision resolution w/ wall: Back up player to prev position
						top1 = catData.getShape().getY();
						bot1 = catData.getShape().getY() + catData.getShape().getH();
						float top2 = box3_Data.getShape().getY();
						float bot2 = box3_Data.getShape().getY()+box3_Data.getShape().getH();
						
						//right1 > left2
						if(top2 < bot1 && catData.getYVelocity() > 0){
							catData.setY(catData.getY() - (bot1-top2));
							catData.setYVelocity(0);
							catData.setX(catData.getX() + box3_Data.getVelocity());
							
							catData.setGrounded(true);
							//System.out.println("HIT");
						}
						catData.getShape().setY(catData.getY()+camera.getY());	
						catData.getShape().setX(catData.getX()+camera.getX());
					}
					
					if(AABBIntersect(catData.getShape(), box4_Data.getShape())){
						//Physics collision resolution w/ wall: Back up player to prev position
						top1 = catData.getShape().getY();
						bot1 = catData.getShape().getY() + catData.getShape().getH();
						float top2 = box4_Data.getShape().getY();
						float bot2 = box4_Data.getShape().getY()+box4_Data.getShape().getH();
						
						//right1 > left2
						if(top2 < bot1 && catData.getYVelocity() > 0){
							catData.setY(catData.getY() - (bot1-top2));
							catData.setYVelocity(0);
							catData.setX(catData.getX() + box4_Data.getVelocity());
							
							catData.setGrounded(true);
							//System.out.println("HIT");
						}
						catData.getShape().setY(catData.getY()+camera.getY());	
						catData.getShape().setX(catData.getX()+camera.getX());
					}
					
					if(AABBIntersect(catData.getShape(), box5_Data.getShape())){
						//Physics collision resolution w/ wall: Back up player to prev position
						top1 = catData.getShape().getY();
						bot1 = catData.getShape().getY() + catData.getShape().getH();
						float top2 = box5_Data.getShape().getY();
						float bot2 = box5_Data.getShape().getY()+box5_Data.getShape().getH();
						
						//right1 > left2
						if(top2 < bot1 && catData.getYVelocity() > 0){
							catData.setY(catData.getY() - (bot1-top2));
							catData.setYVelocity(0);
							catData.setGrounded(true);
							//System.out.println("HIT");
						}
						catData.getShape().setY(catData.getY()+camera.getY());	
						catData.getShape().setX(catData.getX()+camera.getX());
					}
					
					if(AABBIntersect(catData.getShape(), box6_Data.getShape())){
						//Physics collision resolution w/ wall: Back up player to prev position
						top1 = catData.getShape().getY();
						bot1 = catData.getShape().getY() + catData.getShape().getH();
						float top2 = box6_Data.getShape().getY();
						float bot2 = box6_Data.getShape().getY()+box6_Data.getShape().getH();
						
						//right1 > left2
						if(top2 < bot1 && catData.getYVelocity() > 0){
							catData.setY(catData.getY() - (bot1-top2));
							catData.setYVelocity(0);
							catData.setGrounded(true);
							//System.out.println("HIT");
						}
						catData.getShape().setY(catData.getY()+camera.getY());	
						catData.getShape().setX(catData.getX()+camera.getX());
					}
					
					if(AABBIntersect(catData.getShape(), box7_Data.getShape())){
						//Physics collision resolution w/ wall: Back up player to prev position
						top1 = catData.getShape().getY();
						bot1 = catData.getShape().getY() + catData.getShape().getH();
						float top2 = box7_Data.getShape().getY();
						float bot2 = box7_Data.getShape().getY()+box7_Data.getShape().getH();
						
						//right1 > left2
						if(top2 < bot1 && catData.getYVelocity() > 0){
							catData.setY(catData.getY() - (bot1-top2));
							catData.setYVelocity(0);
							catData.setGrounded(true);
							//System.out.println("HIT");
						}
						catData.getShape().setY(catData.getY()+camera.getY());	
						catData.getShape().setX(catData.getX()+camera.getX());
					}
					
					if(AABBIntersect(catData.getShape(), box8_Data.getShape())){
						//Physics collision resolution w/ wall: Back up player to prev position
						top1 = catData.getShape().getY();
						bot1 = catData.getShape().getY() + catData.getShape().getH();
						float top2 = box8_Data.getShape().getY();
						float bot2 = box8_Data.getShape().getY()+box8_Data.getShape().getH();
						
						//right1 > left2
						if(top2 < bot1 && catData.getYVelocity() > 0){
							catData.setY(catData.getY() - (bot1-top2));
							catData.setYVelocity(0);
							catData.setGrounded(true);
							//System.out.println("HIT");
						}
						catData.getShape().setY(catData.getY()+camera.getY());	
						catData.getShape().setX(catData.getX()+camera.getX());
					}
					
				
			/* End vertical collision detection and resolution */
				
			/* Physics collision resolution enemy sprites */
				
				
				
		
			
			/* End physics collision resolution with enemy sprites */
				
				//update
				lastPhysicsFrameMs += physicsDeltaMs;
			}while(lastPhysicsFrameMs + physicsDeltaMs < curFrameMs);
				
			
			// Moving Camera
			if(kbState[KeyEvent.VK_W]){
				int temp = -10;
				float currY = camera.getY();
				if(currY > 0)
					camera.updateY(temp);
			}
			
			if(kbState[KeyEvent.VK_S]){
				int temp = 10;
				float currY = camera.getY();
				if(currY < (bgSize[1]-S_HEIGHT-50))
					camera.updateY(temp);
			}
			
			if(kbState[KeyEvent.VK_A]){
				int temp = -10;
				float currX = camera.getX();
				if(currX > 0)
					camera.updateX(temp);
			}
			
			if(kbState[KeyEvent.VK_D]){
				int temp = 10;
				float currX = camera.getX();
				if(currX < (bgSize[0]-S_WIDTH-50))
					camera.updateX(temp);
			}
		 
	/* ENEMY UPDATE */
	/* Update for every enemy */
	
	
			
			
	
	/* Start DRAWING SECTION */		
	 
			gl.glClearColor(0, 0, 0, 1);
			gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
			  
			MOVABLE = 0;
			
			int start_tileX = (int) Math.floor(camera.getX() / bgDef.getTileW());
			int start_tileY = (int) Math.floor(camera.getY() / bgDef.getTileH());
			int end_tileX = (int) Math.floor((camera.getX() + S_WIDTH) / bgDef.getTileW());
			int end_tileY = (int) Math.floor((camera.getY() + S_HEIGHT) / bgDef.getTileH());
			
			//System.out.println("startX = " + start_tileX + ", startY = " + start_tileY);
			//System.out.println("endX = " + end_tileX + ", endY = " + end_tileY);
	 /* ------------------------------- */ 
	 /* draw on-screen background tiles */
	 /* ------------------------------- */ 
			for(int i = start_tileY ; i < end_tileY;i++){
				for(int j = start_tileX; j <= end_tileX; j++){
					int pos = bgDef.getTile(j,i);	
					
					if(bgDef.getTileValue(pos) == 0){
						glDrawSprite(gl, bgTex0, (int)(j*bgDef.getTileW()-camera.getX()), (int)(i*bgDef.getTileH()-camera.getY()), 51, 51);
					}else if(bgDef.getTileValue(pos) == 1){
						glDrawSprite(gl, bgTex1, (int)(j*bgDef.getTileW()-camera.getX()), (int)(i*bgDef.getTileH()-camera.getY()), 51, 51);
					}
					else if(bgDef.getTileValue(pos) == 2){
						glDrawSprite(gl, bgTex2, (int)(j*bgDef.getTileW()-camera.getX()), (int)(i*bgDef.getTileH()-camera.getY()), bgSize2[0], bgSize2[1]);
					}
					else if(bgDef.getTileValue(pos) == 3){
						glDrawSprite(gl, bgTex3, (int)(j*bgDef.getTileW()-camera.getX()), (int)(i*bgDef.getTileH()-camera.getY()), bgSize3[0], bgSize3[1]);
					}
					else
						System.out.println("2");
				}
			}
	 
	 /* -------------------------------------------------------- */    
	 /* Drawing optimization #2: check and draw onscreen sprites */
	 /* -------------------------------------------------------- */
			 cameraBox.setX(camera.getX());
			 cameraBox.setY(camera.getY());
			 //If the sprite box intersects with camera box, draw it!
			 if(AABBIntersect(cameraBox, enemy1_1Data.getShape())){
				 glDrawSprite(gl, megaManTex1, (int)(enemy1_1Data.getShape().getX()-camera.getX()), (int)(enemy1_1Data.getShape().getY()-camera.getY()), megaManSize[0], megaManSize[1]);
			 }
			 if(AABBIntersect(cameraBox, enemy1_2Data.getShape())){
				 float y = enemy1_2Data.getY()+enemy1_2Data.getVelocity();
				 enemy1_2Data.setY(y);
				 enemy1_2Data.getShape().setY(y);
				 if(y >= 366){
					 enemy1_2Data.setVelocity((float)-1.5);
          		 }
          		 else if(y <= 236){
          			 enemy1_2Data.setVelocity((float)1.5);
          		 }
				 glDrawSprite(gl, megaManTex2, (int)(enemy1_2Data.getShape().getX()-camera.getX()), (int)(enemy1_2Data.getShape().getY()-camera.getY()), megaManSize[0], megaManSize[1]);
			 }
			 
			 if(AABBIntersect(cameraBox, enemy1_3Data.getShape())){
				 float y = enemy1_3Data.getY()+enemy1_3Data.getVelocity();
				 enemy1_3Data.setY(y);
				 enemy1_3Data.getShape().setY(y);
				 if(y >= 366){
					 enemy1_3Data.setVelocity((float)-1.5);
          		 }
          		 else if(y <= 236){
          			enemy1_3Data.setVelocity((float)1.5);
          		 }
				 glDrawSprite(gl, megaManTex3, (int)(enemy1_3Data.getShape().getX()-camera.getX()), (int)(enemy1_3Data.getShape().getY()-camera.getY()), megaManSize[0], megaManSize[1]);
			 }
			 if(AABBIntersect(cameraBox, enemy1_4Data.getShape())){
				 float y = enemy1_4Data.getY()+enemy1_4Data.getVelocity();
				 enemy1_4Data.setY(y);
				 enemy1_4Data.getShape().setY(y);
				 if(y >= 366){
					 enemy1_4Data.setVelocity((float)-1.5);
          		 }
          		 else if(y <= 236){
          			enemy1_4Data.setVelocity((float)1.5);
          		 }
				 glDrawSprite(gl, megaManTex4, (int)(enemy1_4Data.getShape().getX()-camera.getX()), (int)(enemy1_4Data.getShape().getY()-camera.getY()), megaManSize[0], megaManSize[1]);
			 }
			 
			 if(AABBIntersect(cameraBox, box1_Data.getShape())){
				 float y = box1_Data.getY()+box1_Data.getVelocity();
          		 box1_Data.setY(y);
          		 box1_Data.getShape().setY(y);
          		 if(y >= 427){
          			 box1_Data.setVelocity((float)-1.3);
          		 }
          		 else if(y <= 207){
          			 box1_Data.setVelocity((float)1.3);
          		 }
				 glDrawSprite(gl, boxTex1, (int)(box1_Data.getX()-camera.getX()), (int)(box1_Data.getY()-camera.getY()), boxSize1[0], boxSize1[1]);
			 }
			 
			 if(AABBIntersect(cameraBox, box2_Data.getShape())){
				 float x = box2_Data.getX()+box2_Data.getVelocity();
          		 box2_Data.setX(x);
          		 box2_Data.getShape().setX(x);
          		 if(x >= 1450){
          			 box2_Data.setVelocity((float)-1.3);
          		 }
          		 else if(x <= 1250){
          			 box2_Data.setVelocity((float)1.3);
          		 }
				 glDrawSprite(gl, boxTex2, (int)(box2_Data.getX()-camera.getX()), (int)(box2_Data.getY()-camera.getY()), boxSize2[0], boxSize2[1]);
			 }
			 
			 if(AABBIntersect(cameraBox, box3_Data.getShape())){
				 float x = box3_Data.getX()+box3_Data.getVelocity();
          		 box3_Data.setX(x);
          		 box3_Data.getShape().setX(x);
          		 float y = box3_Data.getY()+box3_Data.getVelocity();
         		 box3_Data.setY(y);
         		 box3_Data.getShape().setY(y);
          		 if(x >= 1800){
          			 box3_Data.setVelocity((float)-1.3);
          		 }
          		 else if(x <= 1550){
          			 box3_Data.setVelocity((float)1.3);
          		 }
				 glDrawSprite(gl, boxTex3, (int)(box3_Data.getX()-camera.getX()), (int)(box3_Data.getY()-camera.getY()), boxSize3[0], boxSize3[1]);
			 }
			 
			 if(AABBIntersect(cameraBox, box4_Data.getShape())){
				 float x = box4_Data.getX()+box4_Data.getVelocity();
          		 box4_Data.setX(x);
          		 box4_Data.getShape().setX(x);
          		 float y = box4_Data.getY()-box4_Data.getVelocity();
         		 box4_Data.setY(y);
         		 box4_Data.getShape().setY(y);
          		 if(x >= 2050){
          			 box4_Data.setVelocity((float)-1.3);
          		 }
          		 else if(x <= 1900){
          			 box4_Data.setVelocity((float)1.3);
          		 }
				 glDrawSprite(gl, boxTex4, (int)(box4_Data.getX()-camera.getX()), (int)(box4_Data.getY()-camera.getY()), boxSize4[0], boxSize4[1]);
			 }
			 
			 if(AABBIntersect(cameraBox, box5_Data.getShape())){
				 glDrawSprite(gl, boxTex5, (int)(box5_Data.getX()-camera.getX()), (int)(box5_Data.getY()-camera.getY()), boxSize5[0], boxSize5[1]);
			 }
			 
			 if(AABBIntersect(cameraBox, box6_Data.getShape())){
				 float x = box6_Data.getX()+box6_Data.getVelocity();
				 box6_Data.setX(x);
				 box6_Data.getShape().setX(x);

          		 if(x >= 3050){
          			box6_Data.setVelocity((float)-1);
          		 }
          		 else if(x <= 2945){
          			box6_Data.setVelocity((float)1);
          		 }
				 glDrawSprite(gl, boxTex4, (int)(box6_Data.getX()-camera.getX()), (int)(box6_Data.getY()-camera.getY()), boxSize4[0], boxSize4[1]);
			 }
			 
			 if(AABBIntersect(cameraBox, box7_Data.getShape())){
				 float x = box7_Data.getX()+box7_Data.getVelocity();
				 box7_Data.setX(x);
				 box7_Data.getShape().setX(x);

          		 if(x >= 3050){
          			box7_Data.setVelocity((float)-1);
          		 }
          		 else if(x <= 2945){
          			box7_Data.setVelocity((float)1);
          		 }
				 glDrawSprite(gl, boxTex4, (int)(box7_Data.getX()-camera.getX()), (int)(box7_Data.getY()-camera.getY()), boxSize4[0], boxSize4[1]);
			 }
			 
			 
			 if(AABBIntersect(cameraBox, box8_Data.getShape())){
				 float x = box8_Data.getX()+box8_Data.getVelocity();
				 box8_Data.setX(x);
				 box8_Data.getShape().setX(x);

          		 if(x >= 3050){
          			box8_Data.setVelocity((float)-1);
          		 }
          		 else if(x <= 2945){
          			box8_Data.setVelocity((float)1);
          		 }
				 glDrawSprite(gl, boxTex4, (int)(box8_Data.getX()-camera.getX()), (int)(box8_Data.getY()-camera.getY()), boxSize4[0], boxSize4[1]);
			 }
			 /*
			 
			 if(AABBIntersect(cameraBox, box9_Data.getShape())){
				 float x = box9_Data.getX()+box9_Data.getVelocity();
				 box9_Data.setX(x);
				 box9_Data.getShape().setX(x);

          		 if(x >= 3050){
          			box9_Data.setVelocity((float)-1);
          		 }
          		 else if(x <= 2945){
          			box9_Data.setVelocity((float)1);
          		 }
				 glDrawSprite(gl, boxTex4, (int)(box9_Data.getX()-camera.getX()), (int)(box9_Data.getY()-camera.getY()), boxSize4[0], boxSize4[1]);
			 }*/
			 
			 //trap1
			 if(AABBIntersect(cameraBox, trap1_1Data.getShape())){
				 glDrawSprite(gl, trapTex1_1, (int)(trap1_1Data.getX()-camera.getX()), (int)(trap1_1Data.getY()-camera.getY()), trapSize1_1[0], trapSize1_1[1]);
			 }
			 if(AABBIntersect(cameraBox, trap1_2Data.getShape())){
				 glDrawSprite(gl, trapTex1_2, (int)(trap1_2Data.getX()-camera.getX()), (int)(trap1_2Data.getY()-camera.getY()), trapSize1_2[0], trapSize1_2[1]);
			 }
			 if(AABBIntersect(cameraBox, trap1_3Data.getShape())){
				 glDrawSprite(gl, trapTex1_3, (int)(trap1_3Data.getX()-camera.getX()), (int)(trap1_3Data.getY()-camera.getY()), trapSize1_3[0], trapSize1_3[1]);
			 }
			 //trap2
			 if(AABBIntersect(cameraBox, trap2_1Data.getShape())){
				 glDrawSprite(gl, trapTex2_1, (int)(trap2_1Data.getX()-camera.getX()), (int)(trap2_1Data.getY()-camera.getY()), trapSize2_1[0], trapSize2_1[1]);
			 }
			 if(AABBIntersect(cameraBox, trap2_2Data.getShape())){
				 glDrawSprite(gl, trapTex2_2, (int)(trap2_2Data.getX()-camera.getX()), (int)(trap2_2Data.getY()-camera.getY()), trapSize2_2[0], trapSize2_2[1]);
			 }
			 //trap 3
			 if(AABBIntersect(cameraBox, trap3_1Data.getShape())){
				 glDrawSprite(gl, trapTex3_1, (int)(trap3_1Data.getX()-camera.getX()), (int)(trap3_1Data.getY()-camera.getY()), trapSize3_1[0], trapSize3_1[1]);
			 }
			 if(AABBIntersect(cameraBox, trap3_2Data.getShape())){
				 glDrawSprite(gl, trapTex3_2, (int)(trap3_2Data.getX()-camera.getX()), (int)(trap3_2Data.getY()-camera.getY()), trapSize3_2[0], trapSize3_2[1]);
			 }
			 if(AABBIntersect(cameraBox, trap3_3Data.getShape())){
				 glDrawSprite(gl, trapTex3_3, (int)(trap3_3Data.getX()-camera.getX()), (int)(trap3_3Data.getY()-camera.getY()), trapSize3_3[0], trapSize3_3[1]);
			 }
			 if(AABBIntersect(cameraBox, trap3_4Data.getShape())){
				 glDrawSprite(gl, trapTex3_4, (int)(trap3_4Data.getX()-camera.getX()), (int)(trap3_4Data.getY()-camera.getY()), trapSize3_4[0], trapSize3_4[1]);
			 }
			 if(AABBIntersect(cameraBox, trap3_5Data.getShape())){
				 glDrawSprite(gl, trapTex3_5, (int)(trap3_5Data.getX()-camera.getX()), (int)(trap3_5Data.getY()-camera.getY()), trapSize3_5[0], trapSize3_5[1]);
			 }
			 if(AABBIntersect(cameraBox, trap3_6Data.getShape())){
				 glDrawSprite(gl, trapTex3_6, (int)(trap3_6Data.getX()-camera.getX()), (int)(trap3_6Data.getY()-camera.getY()), trapSize3_6[0], trapSize3_6[1]);
			 }
			 
			//trap 4
			 if(AABBIntersect(cameraBox, trap4_1Data.getShape())){
				 glDrawSprite(gl, trapTex2_1, (int)(trap4_1Data.getX()-camera.getX()), (int)(trap4_1Data.getY()-camera.getY()), trapSize2_1[0], trapSize2_1[1]);
			 }
			 if(AABBIntersect(cameraBox, trap4_2Data.getShape())){
				 glDrawSprite(gl, trapTex2_1, (int)(trap4_2Data.getX()-camera.getX()), (int)(trap4_2Data.getY()-camera.getY()), trapSize2_1[0], trapSize2_1[1]);
			 }
			 if(AABBIntersect(cameraBox, trap4_3Data.getShape())){
				 glDrawSprite(gl, trapTex2_1, (int)(trap4_3Data.getX()-camera.getX()), (int)(trap4_3Data.getY()-camera.getY()), trapSize2_1[0], trapSize2_1[1]);
			 }
			 if(AABBIntersect(cameraBox, trap4_4Data.getShape())){
				 glDrawSprite(gl, trapTex2_1, (int)(trap4_4Data.getX()-camera.getX()), (int)(trap4_4Data.getY()-camera.getY()), trapSize2_1[0], trapSize2_1[1]);
			 }
			 if(AABBIntersect(cameraBox, trap4_5Data.getShape())){
				 glDrawSprite(gl, trapTex2_1, (int)(trap4_5Data.getX()-camera.getX()), (int)(trap4_5Data.getY()-camera.getY()), trapSize2_1[0], trapSize2_1[1]);
			 }
			 if(AABBIntersect(cameraBox, trap4_6Data.getShape())){
				 glDrawSprite(gl, trapTex2_1, (int)(trap4_6Data.getX()-camera.getX()), (int)(trap4_6Data.getY()-camera.getY()), trapSize2_1[0], trapSize2_1[1]);
			 }
			 if(AABBIntersect(cameraBox, trap4_7Data.getShape())){
				 glDrawSprite(gl, trapTex2_1, (int)(trap4_7Data.getX()-camera.getX()), (int)(trap4_7Data.getY()-camera.getY()), trapSize2_1[0], trapSize2_1[1]);
			 }
			 
			//trap 4
			 if(AABBIntersect(cameraBox, trap5_1Data.getShape())){
				 glDrawSprite(gl, trapTex1_1, (int)(trap5_1Data.getX()-camera.getX()), (int)(trap5_1Data.getY()-camera.getY()), trapSize1_1[0], trapSize1_1[1]);
			 }
			 if(AABBIntersect(cameraBox, trap5_2Data.getShape())){
				 glDrawSprite(gl, trapTex1_1, (int)(trap5_2Data.getX()-camera.getX()), (int)(trap5_2Data.getY()-camera.getY()), trapSize1_1[0], trapSize1_1[1]);
			 }
			 if(AABBIntersect(cameraBox, trap5_3Data.getShape())){
				 glDrawSprite(gl, trapTex1_1, (int)(trap5_3Data.getX()-camera.getX()), (int)(trap5_3Data.getY()-camera.getY()), trapSize1_1[0], trapSize1_1[1]);
			 }
			 if(AABBIntersect(cameraBox, trap5_4Data.getShape())){
				 glDrawSprite(gl, trapTex1_1, (int)(trap5_4Data.getX()-camera.getX()), (int)(trap5_4Data.getY()-camera.getY()), trapSize1_1[0], trapSize1_1[1]);
			 }
			 if(AABBIntersect(cameraBox, trap5_5Data.getShape())){
				 glDrawSprite(gl, trapTex1_1, (int)(trap5_5Data.getX()-camera.getX()), (int)(trap5_5Data.getY()-camera.getY()), trapSize1_1[0], trapSize1_1[1]);
			 }
			 if(AABBIntersect(cameraBox, trap5_6Data.getShape())){
				 glDrawSprite(gl, trapTex1_1, (int)(trap5_6Data.getX()-camera.getX()), (int)(trap5_6Data.getY()-camera.getY()), trapSize1_1[0], trapSize1_1[1]);
			 }
			 if(AABBIntersect(cameraBox, trap5_7Data.getShape())){
				 glDrawSprite(gl, trapTex1_1, (int)(trap5_7Data.getX()-camera.getX()), (int)(trap5_7Data.getY()-camera.getY()), trapSize1_1[0], trapSize1_1[1]);
			 }
			 
			 
			 if(AABBIntersect(cameraBox, checkpoint1_Data.getShape())){
				 glDrawSprite(gl, checkpointTex, (int)(checkpoint1_Data.getX()-camera.getX()), (int)(checkpoint1_Data.getY()-camera.getY()), checkpointSize[0], checkpointSize[1]);
			 }
			 if(AABBIntersect(cameraBox, checkpoint2_Data.getShape())){
				 glDrawSprite(gl, checkpointTex, (int)(checkpoint2_Data.getX()-camera.getX()), (int)(checkpoint2_Data.getY()-camera.getY()), checkpointSize[0], checkpointSize[1]);
			 }
			 
			 if(AABBIntersect(cameraBox, lift1_Data.getShape())){
				 glDrawSprite(gl, liftTex1_1, (int)(lift1_Data.getX()-camera.getX()), (int)(lift1_Data.getY()-camera.getY()), liftSize1_1[0], liftSize1_1[1]);
			 }
			 
			 if(AABBIntersect(cameraBox, lift2_Data.getShape())){
				 glDrawSprite(gl, liftTex2_1, (int)(lift2_Data.getX()-camera.getX()), (int)(lift2_Data.getY()-camera.getY()), liftSize1_1[0], liftSize1_1[1]);
			 }
			 
			 System.out.println(saved_locationX + "," + saved_locationY);

	// FIRE EVERYWHERE FROM TOP TO BOTTOM
	/*
			 int counting = 0;
				 if(!fireList.isEmpty()){
				 	for(int i = 0; i < fireList.size()-4; i++){
				 		AnimationData element = fireList.get(i);
				 					 		
				 		//if(AABBIntersect(cameraBox, element.getShape())){
			         			float y = element.getY()+element.getVelocity();
			             		element.setY(y);
			             		element.getShape().setY(y);
			             		glDrawSprite(gl, fireTex, (int)(element.getX()-camera.getX()), (int)(element.getY()-camera.getY()), fireSize[0], fireSize[1]);
				 			
				 		
				 	}
				 }
	*/   
		
	// Draw cat sprite (with animation)
	  // MOVABLE = 1; 
				 
			if(catData.getStatus().equals("dead") || (AABBIntersect(cameraBox, catData.getShape()) == false)){
				 catData.setX(saved_locationX-saved_cameraX);
				 catData.setY(saved_locationY-saved_cameraY);
				 camera.setX(saved_cameraX);
				 camera.setY(saved_cameraY);
				 catData.setGrounded(false);
				 
				 enemy1_1Data.setX(enemy1_1Pos[0]);
				 enemy1_1Data.getShape().setX(enemy1_1Data.getX());
				 enemy1_1Data.setY(enemy1_1Pos[1]);
				 enemy1_1Data.getShape().setY(enemy1_1Data.getY());
				 
				 enemy1_2Data.setX(enemy1_2Pos[0]);
				 enemy1_2Data.getShape().setX(enemy1_2Data.getX());
				 enemy1_2Data.setY(enemy1_2Pos[1]);
				 enemy1_2Data.getShape().setY(enemy1_2Data.getY());	
				 
				 enemy1_3Data.setX(enemy1_3Pos[0]);
				 enemy1_3Data.getShape().setX(enemy1_3Data.getX());
				 enemy1_3Data.setY(enemy1_3Pos[1]);
				 enemy1_3Data.getShape().setY(enemy1_3Data.getY());
				 
				 enemy1_4Data.setX(enemy1_4Pos[0]);
				 enemy1_4Data.getShape().setX(enemy1_4Data.getX());
				 enemy1_4Data.setY(enemy1_4Pos[1]);
				 enemy1_4Data.getShape().setY(enemy1_4Data.getY());
				 
				 enemy1_5Data.setX(enemy1_5Pos[0]);
				 enemy1_5Data.getShape().setX(enemy1_5Data.getX());
				 enemy1_5Data.setY(enemy1_5Pos[1]);
				 enemy1_5Data.getShape().setY(enemy1_5Data.getY());
				 
			}
			glDrawSprite(gl, spriteTex, (int)catData.getX(), (int)catData.getY(), catData.getWidth(), catData.getHeight());
			// Present to the player.
			//window.swapBuffers();
		}
		System.exit(0);
	}

	// Load a file into an OpenGL texture and return that texture.
	public static int glTexImageTGAFile(GL2 gl, String filename, int[] out_size) {
		final int BPP = 4;

		DataInputStream file = null;
		try {
			// Open the file.
			file = new DataInputStream(new FileInputStream(filename));
		} catch (FileNotFoundException ex) {
			System.err.format("File: %s -- Could not open for reading.", filename);
			return 0;
		}

		try {
			// Skip first two bytes of data we don't need.
			file.skipBytes(2);

			// Read in the image type.  For our purposes the image type
			// should be either a 2 or a 3.
			int imageTypeCode = file.readByte();
			if (imageTypeCode != 2 && imageTypeCode != 3) {
				file.close();
				System.err.format("File: %s -- Unsupported TGA type: %d", filename, imageTypeCode);
				return 0;
			}

			// Skip 9 bytes of data we don't need.
			file.skipBytes(9);

			int imageWidth = Short.reverseBytes(file.readShort());
			int imageHeight = Short.reverseBytes(file.readShort());
			int bitCount = file.readByte();
			file.skipBytes(1);

			// Allocate space for the image data and read it in.
			byte[] bytes = new byte[imageWidth * imageHeight * BPP];

			// Read in data.
			if (bitCount == 32) {
				for (int it = 0; it < imageWidth * imageHeight; ++it) {
					bytes[it * BPP + 0] = file.readByte();
					bytes[it * BPP + 1] = file.readByte();
					bytes[it * BPP + 2] = file.readByte();
					bytes[it * BPP + 3] = file.readByte();
				}
			} else {
				for (int it = 0; it < imageWidth * imageHeight; ++it) {
					bytes[it * BPP + 0] = file.readByte();
					bytes[it * BPP + 1] = file.readByte();
					bytes[it * BPP + 2] = file.readByte();
					bytes[it * BPP + 3] = -1;
				}
			}

			file.close();

			// Load into OpenGL
			int[] texArray = new int[1];
			gl.glGenTextures(1, texArray, 0);
			int tex = texArray[0];
			gl.glBindTexture(GL2.GL_TEXTURE_2D, tex);
			gl.glTexImage2D(
					GL2.GL_TEXTURE_2D, 0, GL2.GL_RGBA, imageWidth, imageHeight, 0,
					GL2.GL_BGRA, GL2.GL_UNSIGNED_BYTE, ByteBuffer.wrap(bytes));
			gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
			gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);

			out_size[0] = imageWidth;
			out_size[1] = imageHeight;
			return tex;
		}
		catch (IOException ex) {
			System.err.format("File: %s -- Unexpected end of file.", filename);
			return 0;
		}
	}
	
	public static void glDrawTileBackground(GL2 gl, int tex, float[] loc, int x, int y, int w, int h){
		 gl.glBindTexture(GL2.GL_TEXTURE_2D, tex);
		 gl.glBegin(GL2.GL_QUADS);
		 {
 	            gl.glColor3ub((byte)-1, (byte)-1, (byte)-1);
 	            gl.glTexCoord2f(loc[0], loc[2]);
 	            //gl.glTexCoord2f(0, 1);
 	            gl.glVertex2i(x, y);
 	            gl.glTexCoord2f(loc[0]+loc[1], loc[2]);
 	            //gl.glTexCoord2f(1, 1);
 	            gl.glVertex2i(x + w, y);
 	            gl.glTexCoord2f(loc[0]+loc[1], loc[2]-loc[3]);
 	            //gl.glTexCoord2f(1, 0);
 	            gl.glVertex2i(x + w, y + h);
 	            gl.glTexCoord2f(loc[0], loc[2]-loc[3]);
 	            //gl.glTexCoord2f(0, 0);
 	            gl.glVertex2i(x, y + h);
		 }
		 gl.glEnd();
	}

	public static void glDrawSprite(GL2 gl, int tex, int x, int y, int w, int h) {
		gl.glBindTexture(GL2.GL_TEXTURE_2D, tex);
		gl.glBegin(GL2.GL_QUADS);
		{
			if(MOVABLE == 0){
	            gl.glColor3ub((byte)-1, (byte)-1, (byte)-1);
	            gl.glTexCoord2f(0,1);
	            gl.glVertex2i(x, y);
	            gl.glTexCoord2f(1,1);
	            gl.glVertex2i(x + w, y);
	            gl.glTexCoord2f(1,0);
	            gl.glVertex2i(x + w, y + h);
	            gl.glTexCoord2f(0,0);
	            gl.glVertex2i(x, y + h);
			}
			else if(MOVABLE == 1){
				 gl.glColor3ub((byte)-1, (byte)-1, (byte)-1);
				 gl.glTexCoord2f(0, 1);
				 gl.glVertex2i(x, y);
				 
				 if(direction.equals("L") || direction.equals("UL") || direction.equals("DL")){
				 	gl.glTexCoord2f(-1, 1);
				 	gl.glVertex2i(x + w, y);
				 	gl.glTexCoord2f(-1, 0);
				 }
				 else if(direction.equals("R") || direction.equals("UR") || direction.equals("DR")){
				 	gl.glTexCoord2f(1, 1);
				 	gl.glVertex2i(x + w, y);
				 	gl.glTexCoord2f(1, 0);
				 }
				
				 gl.glVertex2i(x + w, y + h);
				 gl.glTexCoord2f(0, 0);
				 gl.glVertex2i(x, y + h);
			}
		}
		gl.glEnd();
	}
	
	/* Check on-screen sprites
	//return 0 if there's no collision and true if there's collision
	//return 1 if box1 on top of box2, 
	//return 2 if box1 on right of box2
	//return 3 if box1 on bottom of box2
	return 4 if box1 on the left of box2
	*/
	public static boolean AABBIntersect(AABB box1, AABB box2){
		boolean intersect = true;
		int amount = 0;
		
		// box1 to the right
		if(box1.getX() >= box2.getX() + box2.getW()){
			intersect = false;
		}
		else 
		// box1 to the left
		if(box1.getX() + box1.getW() <= box2.getX()){
			intersect = false;
		}
		// box1 below
		if(box1.getY() >= box2.getY() + box2.getH()){
			intersect = false;
		}
		// box1 above
		if(box1.getY() + box1.getH() <= box2.getY()){
			intersect = false;
		}
		
		return intersect;
	}
}
