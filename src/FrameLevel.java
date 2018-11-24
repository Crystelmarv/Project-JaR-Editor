import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.RootPaneContainer;
import javax.swing.ScrollPaneConstants;

public class FrameLevel extends JPanel implements ActionListener
{

  JFrame frameLevel;
  JPanel panelLevel;
  JPanel panelLevelMain;
  JScrollPane scrollpane;
  JButton[][] buttons = new JButton[25][300];
  String[][] ids = new String[25][300];
  JButton gedrueckterButton;
  JViewport viewport;
  Point viewPoint;
  SteuerungMain steuerungMain;
  Assets assets;
  FrameBlocke frameBlocke;
  JMenuBar menuBar;
  JMenu menuOptionen;
  JMenuItem menuItemSpeichern;
  JMenuItem menuItemOeffnen;
  JMenuItem menuItemConfig;
  LevelFileWriter levelFileWriter;
  LevelFileReader levelFileReader;
  FrameConfig frameConfig;
  int letzterButtonX;
  int letzterButtonY;

  boolean left = false;
  boolean right = false;
  boolean down = false;
  boolean up = false;
  boolean shift = false;
  boolean shiftGestezt = false;
  boolean shiftHelpAktiv = false;
  boolean zweiterButtonGedrueckt = false;
  boolean focus = true;

  int letzterButtonXTemp;
  int letzterButtonYTemp;

  int x = 0;
  int y = 0;

  public FrameLevel(SteuerungMain steuerungMain)
  {
    this.steuerungMain = steuerungMain;
  }

  public void FrameLevelErstellen()
  {
    int i, j;
    int x = 20;
    int y = 20;

    frameLevel = new JFrame("");
    panelLevel = new JPanel();
    panelLevelMain = new JPanel();
    viewport = new JViewport();
    viewPoint = new Point(this.x, this.y);
    FrameLevel game = new FrameLevel(steuerungMain);
    frameBlocke = steuerungMain.getFrameBlocke();
    levelFileWriter = steuerungMain.getLevelFileWriter();
    levelFileReader = steuerungMain.getLevelFileReader();
    
    frameConfig = new FrameConfig(this);
    frameConfig.frameConfigErstellen();

    menuBar = new JMenuBar();
    menuOptionen = new JMenu("Optionen");
    menuItemSpeichern = new JMenuItem("Speichern");
    menuItemOeffnen = new JMenuItem("Öffnen");
    menuItemConfig = new JMenuItem("Congig");

    menuOptionen.add(menuItemSpeichern);
    menuOptionen.add(menuItemOeffnen);
    menuOptionen.add(menuItemConfig);
    menuBar.add(menuOptionen);
    menuItemSpeichern.addActionListener(this);
    menuItemOeffnen.addActionListener(this);
    menuItemConfig.addActionListener(this);
    frameLevel.add(menuBar);
    frameLevel.setJMenuBar(menuBar);

    frameLevel.add(game);

    frameLevel.setSize(1450, 900);
    frameLevel.setTitle("JaR Level Editor");
    frameLevel.setResizable(true);
    frameLevel.setLocationRelativeTo(null);

    frameLevel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frameLevel.setLayout(null);

    panelLevel.setSize(100000, 100000);
    panelLevel.setLayout(null);

    panelLevelMain.setSize(1450, 900);
    panelLevelMain.setLayout(null);

    KeyListener listener = new KeyListener()
    {
      @Override
      public void keyTyped(KeyEvent e)
      {
      }

      @Override
      public void keyPressed(KeyEvent e)
      {
        int key = e.getKeyCode();

        switch (key)
        {

        case KeyEvent.VK_LEFT:
          left = true;
          break;
        case KeyEvent.VK_RIGHT:
          right = true;
          break;
        case KeyEvent.VK_DOWN:
          down = true;
          break;
        case KeyEvent.VK_UP:
          up = true;
          break;
        case KeyEvent.VK_SHIFT:
          shift = true;
          break;
        }
      }

      @Override
      public void keyReleased(KeyEvent ee)
      {
        int key2 = ee.getKeyCode();

        switch (key2)
        {
        case KeyEvent.VK_LEFT:
          left = false;
          break;
        case KeyEvent.VK_RIGHT:
          right = false;
          break;
        case KeyEvent.VK_DOWN:
          down = false;
          break;
        case KeyEvent.VK_UP:
          up = false;
          break;
        case KeyEvent.VK_SHIFT:
          shift = false;
          break;

        }
      }
    };

    /*
     * JScrollPane scrollPane = new JScrollPane (panelLevel,
     * ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
     * ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
     */
    // scrollPane.setViewportView(panelLevel);
    // scrollBar.setColumnHeaderView(panelLevel);

    // scrollPane.add(panelLevel);
    // panelLevelMain.add(scrollPane);
    // frameLevel.getContentPane().add(scrollPane);

    frameLevel.addKeyListener(listener);
    
    
    
      frameLevel.setFocusable(true);
      frameLevel.requestFocus();
    
    

    // frameLevel.add(scrollPane);

    viewport.add(panelLevel);
    viewport.setScrollMode(WHEN_IN_FOCUSED_WINDOW);
    viewport.setViewPosition(viewPoint);

    // frameLevel.add(scrollBar);

    for (i = 0; i < buttons.length; i++)
    {
      for (j = 0; j < buttons[1].length; j++)
      {
        buttons[i][j] = new JButton();
        buttons[i][j].setBounds(x, y, 64, 64);
        ids[i][j] = "20";
        // buttons[i][j].setSize(640, 64);
        // buttons[i]j].setIcon(new ImageIcon(assets.texturen[k]));
        buttons[i][j].addActionListener(this);
        panelLevel.add(buttons[i][j]);

        x = x + 64;

      }
      x = 20;
      y = y + 64;
    }
    frameLevel.add(panelLevel);
    panelLevel.repaint();
    panelLevel.setVisible(true);
    frameLevel.setVisible(true);

  }

  public void update()
  {
    frameBlocke = steuerungMain.getFrameBlocke();
    levelFileWriter = steuerungMain.getLevelFileWriter();
    levelFileReader = steuerungMain.getLevelFileReader();
    assets = steuerungMain.getAssets();
    
    if(focus == true)
    {
    frameLevel.requestFocus();
    }

    shiftHelp();

    // frameLevel.requestFocus();
    if (right == true)
    {
      x = x - 30;

    }

    if (left == true)
    {
      x = x + 30;
    }

    if (up == true)
    {
      y = y + 30;
    }

    if (down == true)
    {
      y = y - 30;
    }
    viewPoint.move(x, y);
    panelLevel.setLocation(viewPoint);

    panelLevel.repaint();
    frameLevel.repaint();

  }

  public void paint(Graphics g)
  {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    System.out.println("P");
  }

  @Override
  public void actionPerformed(ActionEvent b)
  {
    int i, j;

    System.out.println("BUTTTON");

    if (menuItemSpeichern.equals(b.getSource()))
    {
      levelFileWriter.speichern();
    } else if (menuItemOeffnen.equals(b.getSource()))
    {
      try
      {
        levelFileReader.oeffnen();
      } catch (IOException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else if(menuItemConfig.equals(b.getSource()))
    {
      frameConfig.setVisible(true);
      focus = false;
    }
    else

    {

      for (i = 0; i < buttons.length; i++)
      {
        for (j = 0; j < buttons[1].length; j++)
        {
          if (buttons[i][j].equals(b.getSource()))
          {
            assets = steuerungMain.getAssets();
            buttons[i][j].setIcon(new ImageIcon(assets.texturen[frameBlocke.getTexture()]));
            ids[i][j] = assets.getID(frameBlocke.getTexture());
            letzterButtonX = j;
            letzterButtonY = i;
            if (shiftGestezt == true && shiftHelpAktiv == true)
            {
              zweiterButtonGedrueckt = true;
            }
          }

        }

      }

    }

  }

  public String getBlockIds(int i, int j)
  {
    return ids[i][j];
  }
  
  public FrameConfig getFrameConfig()
  {
    return frameConfig;
  }

  public void oeffnenButtonsUpdate()
  {
    int i, j, k;
    for (i = 0; i < buttons.length; i++)
    {

      for (j = 0; j < buttons[1].length; j++)
      {
        assets = steuerungMain.getAssets();
        for (k = 0; k < assets.texturen.length; k++)
        {
          // System.out.println("id " +ids[i][j]);
          // System.out.println(assets.getID(k));
          if (ids[i][j].equals(assets.getID(k)))
          {
            System.out.println("id " + ids[i][j]);
            System.out.println(assets.getID(k));
            buttons[i][j].setIcon(new ImageIcon(assets.texturen[k]));
          }
        }

      }
    }

  }

  public void shiftHelp()
  {
    int i;

    if (shift == true && shiftGestezt == false)
    {
      letzterButtonXTemp = letzterButtonX;
      letzterButtonYTemp = letzterButtonY;
      shiftGestezt = true;
    }
    if (shift == false)
    {
      shiftHelpAktiv = true;

    }

    if (shiftGestezt == true && shiftHelpAktiv == true && zweiterButtonGedrueckt == true)
    {

      if (letzterButtonXTemp == letzterButtonX)
      {
        shift = false;
        System.out.println("X GLEICH");

        if (letzterButtonYTemp > letzterButtonY)
        {
          for (i = letzterButtonYTemp; i >= letzterButtonY; i--)
          {
            buttons[i][letzterButtonX].setIcon(new ImageIcon(assets.texturen[frameBlocke.getTexture()]));
            ids[i][letzterButtonX] = assets.getID(frameBlocke.getTexture());
          }
        }

        if (letzterButtonYTemp < letzterButtonY)
        {
          for (i = letzterButtonYTemp; i <= letzterButtonY; i++)
          {
            buttons[i][letzterButtonX].setIcon(new ImageIcon(assets.texturen[frameBlocke.getTexture()]));
            ids[i][letzterButtonX] = assets.getID(frameBlocke.getTexture());
          }
        }

      }

      if (letzterButtonYTemp == letzterButtonY)
      {
        shift = false;
        System.out.println("Y GLEICH");

        if (letzterButtonXTemp > letzterButtonX)
        {
          for (i = letzterButtonXTemp; i >= letzterButtonX; i--)
          {
            buttons[letzterButtonY][i].setIcon(new ImageIcon(assets.texturen[frameBlocke.getTexture()]));
            ids[letzterButtonY][i] = assets.getID(frameBlocke.getTexture());
          }
        }

        if (letzterButtonXTemp < letzterButtonX)
        {
          for (i = letzterButtonXTemp; i <= letzterButtonX; i++)
          {
            buttons[letzterButtonY][i].setIcon(new ImageIcon(assets.texturen[frameBlocke.getTexture()]));
            ids[letzterButtonY][i] = assets.getID(frameBlocke.getTexture());
          }
        }

      }
      shiftGestezt = false;
      zweiterButtonGedrueckt = false;

    }
  }

}
