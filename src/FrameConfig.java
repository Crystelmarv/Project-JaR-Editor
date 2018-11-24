import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameConfig extends JPanel implements ActionListener
{
  JFrame frameConfig;
  JLabel labelLevelName;
  JTextField textFieldLevelName;
  JButton buttonOK;
  FrameLevel frameLevel;
  private String levelName = "";
  
  
  public FrameConfig(FrameLevel frameLevel)
  {
    this.frameLevel = frameLevel;
  }
  
  public void frameConfigErstellen()
  {
    frameConfig = new JFrame("");
    FrameConfig game = new FrameConfig(null);
    labelLevelName = new JLabel("Level Name:");
    textFieldLevelName = new JTextField(20);
    buttonOK = new JButton("OK");

    frameConfig.add(game);

    frameConfig.setSize(500, 700);
    frameConfig.setTitle("JaR Level Editor Config");
    frameConfig.setResizable(false);
    frameConfig.setLocationRelativeTo(null);
    frameConfig.setLayout(null);
    
    labelLevelName.setBounds(40, 40, 100, 60);
    textFieldLevelName.setBounds(130, 50, 200, 40);
    buttonOK.setBounds(200, 550, 100, 60);
     
    frameConfig.add(labelLevelName);
    frameConfig.add(textFieldLevelName);
    frameConfig.add(buttonOK);
    
   buttonOK.addActionListener(this);
    
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    
    levelName = textFieldLevelName.getText();
    frameConfig.setVisible(false);
    frameLevel.focus = true;
    
    
    
  }
  
  public String getLevelName()
  {
    return levelName;
  }
  
  public void setLevelName(String name)
  {
    levelName = name;
  }
  
  public void setVisible(boolean vis)
  {
    frameConfig.setVisible(vis);
  }
  
}
