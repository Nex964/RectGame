package menus;

import menus.components.Component;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class Menu
{

  protected List<Component> buttonList = new ArrayList<>();

  public abstract void render(Graphics paramGraphics);
  
  public abstract void tick();

  public List<Component> getComponentList(){
    return buttonList;
  }
}
