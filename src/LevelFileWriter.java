import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class LevelFileWriter
{
  FrameLevel frameLevel;
  FrameConfig frameConfig;
  String speicherPfad;
  
  public LevelFileWriter(FrameLevel frameLevel)
  {
    this.frameLevel = frameLevel;
  }

  public void speichern()
  {
    JFileChooser fileChooser = new JFileChooser();
    
    fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
  
    int rueckgabeWert = fileChooser.showSaveDialog(null);
    
    //wurde speichern gedrückt?
    if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
    {
         // Ausgabe der ausgewaehlten Datei
      speicherPfad = fileChooser.getSelectedFile().getPath();
      writeFile();
    }
  }
  public void writeFile()
  {
    frameConfig = frameLevel.getFrameConfig();
    int i, j;
    try
    {
      FileWriter writer = new FileWriter(speicherPfad + ".txt", false);
      BufferedWriter bufferedWriter = new BufferedWriter(writer);

      bufferedWriter.write("LEVEL");
      bufferedWriter.newLine();
      
      for (i = 0; i < frameLevel.buttons.length; i++)
      {
        for (j = 0; j < frameLevel.buttons[1].length; j++)
        {
          bufferedWriter.write(frameLevel.getBlockIds(i, j)+ " ");
        }
        bufferedWriter.newLine();
      }
      
      bufferedWriter.write("NAME");
      bufferedWriter.newLine();
      bufferedWriter.write(frameConfig.getLevelName());
      
      
        

      bufferedWriter.close();
    } catch (IOException e)
    {
      e.printStackTrace();
    }

  }

}
