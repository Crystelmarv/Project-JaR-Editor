import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SteuerungMain
{
   FrameLevel frameLevel;
   FrameBlocke frameBlocke;
   Assets assets;
   static SteuerungMain steuerungMain;
   boolean init = false;
   LevelFileWriter levelFileWriter;
   LevelFileReader levelFileReader;
 
   
   double fps = 100.0;
   double timePerTick = 1000000000.0 / fps;
   double delta = 0.0;
   long now;
   long lastTime = System.nanoTime();
   int ticks = 0;
   long timer = 0;
   
   
  // GameStates gameState = new GameStates(this);
  // static KeyLis keyLis = new KeyLis(this, frame);

  static int wHoehe = 900;
  static int wBreite = 1450;

  KeyListener key;

  public SteuerungMain()
  {

    frameLevel = new FrameLevel(this);
    frameLevel.FrameLevelErstellen();

    frameBlocke = new FrameBlocke();
    frameBlocke.FrameBlockeErstellen();
    assets = new Assets();
    
    levelFileWriter = new LevelFileWriter(frameLevel);
    levelFileReader = new LevelFileReader(frameLevel);
    
    

init = true;
  }

  private void move() throws InterruptedException
  {

  }

  private void update() throws InterruptedException 

  {
    while (init)
    {
      
      now = System.nanoTime();
      delta += (now - lastTime) / timePerTick;
      timer += now - lastTime;
      lastTime = now;

      if (delta >= 1.0)
      {
     
       frameLevel.update();

       frameBlocke.update();

       
     
      ticks++;
      delta--;
      }
      Thread.sleep(1);
      if (timer >= 1000000000)
      {
        System.out.println("FPS: " + ticks);
        ticks = 0;
        timer = 0;
      }
    }
  }

  /*
   * @Override public void paint(Graphics g) {
   * 
   * System.out.println("y");
   * 
   * }
   */

  public static void main(String[] args) throws InterruptedException, IOException
  {
    
    steuerungMain = new SteuerungMain();

   steuerungMain.update();
  }
  
  public FrameBlocke getFrameBlocke()
  {
    return frameBlocke;
  }
  
  public Assets getAssets()
  {
    return assets;
  }
  
  public LevelFileWriter getLevelFileWriter()
  {
    return levelFileWriter;
  }
  
  public LevelFileReader getLevelFileReader()
  {
    return levelFileReader;
  }
  
 

 

}