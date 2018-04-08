package tools;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class MessageBox
  extends JFrame
{
  private static final long serialVersionUID = -205130919185318093L;
  
  public MessageBox(String title, String sub_title, String message_text)
  {
    super(title);
    JTextArea message = new JTextArea(message_text);
    message.setEditable(false);
    message.setBorder(new TitledBorder(sub_title));
    add(message);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
}
