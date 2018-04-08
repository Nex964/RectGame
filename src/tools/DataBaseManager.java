package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManager
{
  private PreparedStatement stat;
  private ResultSet result;
  
  public void connect()
  {
    try
    {
      Connection con = DriverManager.getConnection("jdbc:mysql://sql210.epizy.com/epiz_21901407_Account", "epiz_21901407", "ashishkar");
      stat = con.prepareStatement("select * from Account where username = ? and pass = ?");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public boolean login(String username, String password)
  {
    connect();
    try
    {
      stat.setString(1, username);
      stat.setString(2, password);
      result = stat.executeQuery();
      if (result.next()) {
        return true;
      }
      return false;
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return false;
  }
  
  public void register(String username, String password) {}
}
