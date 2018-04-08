package menus;

import Game.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import tools.DataBaseManager;
import tools.MessageBox;

public class LoginMenu
  extends JFrame
{
  private static final long serialVersionUID = -444514151034009618L;
  private DataBaseManager DBManager;
  private Main game;
  
  public LoginMenu(Main game)
  {
    super("Login");
    this.game = game;
    setSize(200, 200);
    setLocationRelativeTo(null);
    setVisible(true);
    DBManager = new DataBaseManager();
    addComponents();
  }
  
  public void addComponents()
  {
    JPanel login_panel = new JPanel();
    login_panel.setBorder(new TitledBorder("Login"));
    JLabel username_text = new JLabel("Username :");
    JLabel password_text = new JLabel("Password :");
    final JTextField username_field = new JTextField(10);
    final JTextField password_field = new JTextField(10);
    JButton submit = new JButton("Submit");
    login_panel.setLayout(new BoxLayout(login_panel, 1));
    login_panel.add(username_text);
    login_panel.add(username_field);
    login_panel.add(password_text);
    login_panel.add(password_field);
    login_panel.add(submit);
    add(login_panel);
    pack();
    submit.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (DBManager.login(username_field.getText(), password_field.getText()))
        {
          new MessageBox("MessageBox", "Information", "Logged in successfully");
          game.isLoggedIn = true;
        }
        else
        {
          new MessageBox("MessageBox", "Information", "Your account doesn't exists or\nwrong username or password");
          System.exit(0);
          game.isLoggedIn = false;
        }
      }
    });
    addWindowListener(new WindowListener()
    {
      public void windowActivated(WindowEvent arg0) {}
      
      public void windowClosed(WindowEvent arg0) {}
      
      public void windowClosing(WindowEvent arg0) {}
      
      public void windowDeactivated(WindowEvent arg0)
      {
        if (!game.isLoggedIn) {
          System.exit(0);
        }
      }
      
      public void windowDeiconified(WindowEvent arg0) {}
      
      public void windowIconified(WindowEvent arg0) {}
      
      public void windowOpened(WindowEvent arg0) {}
    });
  }
  
  public void dispose() {}
}
