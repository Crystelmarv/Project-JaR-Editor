import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;

public class LevelFileReader
{
  static boolean levelGelesen = false;
  static boolean levelNameGelesen = false;
  static String levelPfad = "";
  static String levelPfadSchild = "";
  static int level = 1;
  static String[] schildText;
  private static String levelName = "#ERROR levelName";
  
  private static String trennerName = "NAME";
  private static String trennerLevel = "LEVEL";
  private static String trennerSchild = "SCHILD";
  
  boolean schildImLevel = false;
  
  FrameLevel frameLevel;
  FrameConfig frameConfig;

  public LevelFileReader(FrameLevel frameLevel)
  {
    this.frameLevel = frameLevel;
  }

  public void oeffnen() throws IOException
  {
 JFileChooser fileChooser = new JFileChooser();
    
    fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
  
    int rueckgabeWert = fileChooser.showOpenDialog(null);
    
    //wurde speichern gedrückt?
    if(rueckgabeWert == JFileChooser.APPROVE_OPTION)
    {
         // Ausgabe der ausgewaehlten Datei
      levelPfad = fileChooser.getSelectedFile().getPath();
      System.out.println(levelPfad);
    }
    LevelRead();
    if(schildImLevel == true)
    {
      schildEinlesen();
    }
   
    levelNameLaden();
  }
  public void LevelRead() throws IOException
  {
   
    

    int i, j;
    int y = 0;

    String currentLine;

    
    InputStream is = new FileInputStream(levelPfad);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

    
    do
    {
      currentLine = bufferedReader.readLine();
    
    } while (!currentLine.regionMatches(0, trennerLevel, 0, trennerLevel.length()));
    while ((currentLine = bufferedReader.readLine()) != null &&
        !currentLine.regionMatches(0, trennerName, 0, trennerName.length()) 
        && !currentLine.regionMatches(0, trennerSchild, 0, trennerSchild.length()))
    {
      System.out.println(currentLine);
      String[] values = currentLine.trim().split(" ");

      for (i = 0; i < values.length; i++)
      {
        frameLevel.ids[y][i] = values[i];
        if(values[i].equals("19"))
        {
          schildImLevel = true;
        }
      }
      y++; 
    }
    frameLevel.oeffnenButtonsUpdate();
    levelGelesen = true;

  }

  

  

  

  public static void schildEinlesen() throws IOException
  {
    schildArrayInit();

    String currentLine;
    int i = 0;
    String trenner = "123ABC";

    InputStream is = new FileInputStream(levelPfad);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

    
    do
    {
      currentLine = bufferedReader.readLine();
    
    } while (!currentLine.regionMatches(0, trennerSchild, 0, trennerSchild.length()));
    
    while ((currentLine = bufferedReader.readLine()) != null)
    {

      if (currentLine.regionMatches(0, trenner, 0, trenner.length()))
      {
        i++;
      } else
      {
        schildText[i] = currentLine;
      }

    }

  }

  public static void schildArrayInit() throws IOException
  {
    String trenner = "123ABC";
    String currentLine;
    int i = 1;

    InputStream is =new FileInputStream(levelPfad);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

    
    do
    {
      currentLine = bufferedReader.readLine();
     // System.out.println("5");
    
    } while (!currentLine.regionMatches(0, trennerSchild, 0, trennerSchild.length()));
    
    while ((currentLine = bufferedReader.readLine()) != null)
    {
      if (currentLine.regionMatches(0, trenner, 0, trenner.length()))
      {

        i++;

      }

    }

    schildText = new String[i];
  }
  
  public static String getLevelName()
  {
    return levelName;
  }
  
  public void levelNameLaden() throws IOException
  {
    String currentLine;
    InputStream is = new FileInputStream(levelPfad);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
    do
    {
      currentLine = bufferedReader.readLine();
      System.out.println(currentLine);
    
    } while (!currentLine.regionMatches(0, trennerName, 0, trennerName.length()));
    
    levelName = bufferedReader.readLine();
    frameConfig = frameLevel.getFrameConfig();
    
    frameConfig.textFieldLevelName.setText(levelName);
    frameConfig.setLevelName(levelName);
    
  }
}
