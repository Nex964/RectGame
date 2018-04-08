package tools;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import world.LevelDesigner;

public class TextBox
  extends JFrame
{
  public TextBox(final LevelDesigner ld)
  {
    super("Add Command");
    JLabel label = new JLabel("Add Command");
    final JTextField editText = new JTextField(10);
    JButton button = new JButton("Submit");
    setLayout(new FlowLayout(1));
    add(label);
    add(editText);
    add(button);
    setLocationRelativeTo(null);
    setVisible(true);
    pack();
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ld.event_cmd = editText.getText();
        ld.addToEventBlockList();
        System.out.println("Event block added");
      }
    });
  }
}
