import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets
{
  static SpriteSheet sheet;

  private static int breite = 64;
  private static int hoehe = 64;

  public static BufferedImage[] texturen = new BufferedImage[18];

  public static BufferedImage gras, erde, blauerBlock, orangerBlock, schild, orangerBlockOhneKreuz, apfel, 
                              marienKaeferLinks, marienKaeferRechts, checkPointUnten, checkPointObenAus, 
                              checkPointObenAn, bieneLinks, bieneRechts, wasser, seerose, devBlock, herz,
                              fischOben, fischUnten, tod, spawn, ziel, weiss, blau;

  public void init()
  {
    try
    {
      sheet = new SpriteSheet(ImageIO.read(getClass().getResource("/textures/TilesSheet.png")));
    } catch (IOException e)
    {
      e.printStackTrace();
      System.exit(1);
    }

    gras = sheet.crop(0, 0, breite, hoehe);
    erde = sheet.crop(breite, 0, breite, hoehe);
    blauerBlock = sheet.crop(breite * 2, 0, breite, hoehe);
    orangerBlock = sheet.crop(breite * 3, 0, breite, hoehe);
    schild = sheet.crop(breite * 4, 0, breite, hoehe);
    orangerBlockOhneKreuz = sheet.crop(0, hoehe, breite, hoehe);
    apfel = sheet.crop(breite, hoehe, breite, hoehe);
    marienKaeferLinks = sheet.crop(breite*2, hoehe, breite, hoehe);
    marienKaeferRechts = sheet.crop(breite*3, hoehe, breite, hoehe);
    checkPointObenAn = sheet.crop(breite*4, hoehe, breite, hoehe);
    checkPointObenAus = sheet.crop(breite*4, hoehe*2, breite, hoehe);
    checkPointUnten = sheet.crop(breite*4, hoehe*3, breite, hoehe);
    bieneLinks = sheet.crop(breite*3, hoehe*2, breite, hoehe);
    bieneRechts = sheet.crop(breite*2, hoehe*2, breite, hoehe);
    wasser  = sheet.crop(breite, hoehe*2, breite, hoehe);
    seerose  = sheet.crop(0, hoehe*2, breite, hoehe);
    devBlock  = sheet.crop(0, hoehe*3, breite, hoehe);
    herz  = sheet.crop(breite, hoehe*3, breite, hoehe);
    fischOben = sheet.crop(breite*2, hoehe*3, breite, hoehe);
    fischUnten = sheet.crop(breite*3, hoehe*3, breite, hoehe);
    tod = sheet.crop(0, hoehe*4, breite, hoehe);
    spawn = sheet.crop(breite, hoehe*4, breite, hoehe);
    ziel = sheet.crop(breite*2, hoehe*4, breite, hoehe);
    weiss = sheet.crop(breite*3, hoehe*4, breite, hoehe);
    blau = sheet.crop(breite*4, hoehe*4, breite, hoehe);
    
    

    texturen[0] = gras;
    texturen[1] = erde;
    texturen[2] = checkPointObenAn;
    texturen[3] = orangerBlock;
    texturen[4] = orangerBlockOhneKreuz;
    texturen[5] = schild;
    texturen[6] = devBlock;
    texturen[7] = wasser;
    texturen[8] = seerose;
    texturen[9] = apfel;
    texturen[10] = marienKaeferLinks;
    texturen[11] = bieneLinks;
    texturen[12] = fischOben;
    texturen[13] = tod;
    texturen[14] = spawn;
    texturen[15] = ziel;
    texturen[16] = weiss;
    texturen[17] = blau;
    

    

  }

  public String getID(int texture)
  {
    String id = "";
    switch (texture)
    {
    case 0:
      id = "30";
      break;
    case 1:
      id = "31";
      break;
    case 2:
      id = "17";
      break;
    case 3:
      id = "52";
      break;
    case 4:
      id = "32";
      break;
    case 5:
      id = "19";
      break;
    case 6:
      id = "22";
      break;
    case 7:
      id = "33";
      break;
    case 8:
      id = "34";
      break;
    case 9:
      id = "53";
      break;
    case 10:
      id = "71";
      break;
    case 11:
      id = "72";
      break;
    case 12:
      id = "73";
      break;
    case 13:
      id = "18";
      break;
    case 14:
      id = "15";
      break;
    case 15:
      id = "16";
      break;
    case 16:
      id = "20";
      break;
    case 17:
      id = "21";
      break;
      
      
      
      
      
     

    }
    return id;

  }

}
