import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameBlocke extends JPanel implements ActionListener
{
  
  JFrame frameBlocke;
  JButton[] buttons = new JButton[18];
  Assets assets;
  private int ausgewTexture = 2;
  
  
  public FrameBlocke()
  {
    
  }
  
  public void FrameBlockeErstellen()
  {
    int i,j;
    int x = 20;
    int y = 20;
    int k = 0;
    
    frameBlocke = new JFrame("");
    FrameLevel game = new FrameLevel(null);
    assets = new Assets();
    assets.init();

    frameBlocke.add(game);

    frameBlocke.setSize(310, 800);
    frameBlocke.setTitle("JaR Level Editor");
    frameBlocke.setResizable(false);
    frameBlocke.setLocationRelativeTo(game);
    frameBlocke.setLayout(null);
    
    frameBlocke.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    for(i=0; i < buttons.length/3; i++)
    {
      for(j=0; j < buttons.length/(buttons.length/3); j++)
      {
        buttons[k] = new JButton();
        buttons[k].setBounds(x, y, 80, 80);
        buttons[k].setIcon(new ImageIcon(assets.texturen[k]));
        buttons[k].addActionListener(this);
        frameBlocke.add(buttons[k]);
        
        x = x + 90;
        k++;
      }
      x = 20;
      y = y + 90;
     
    }
    
    frameBlocke.setVisible(true);

  }
  
  public void update()
  {
   // frameBlocke.requestFocus();
    frameBlocke.repaint();
  }
  
  public void paint(Graphics g)
  {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    System.out.println("l");
  }

  @Override
  public void actionPerformed(ActionEvent b)
  {
    int i;
    
    for(i=0; i < buttons.length; i++)
    {
     
        if(buttons[i].equals(b.getSource()))
        {
          ausgewTexture = i;
      }
     
     
    }
    
  }
  
  public int getTexture()
  {
    return ausgewTexture;
  }

}
