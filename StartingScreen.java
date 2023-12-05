import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartingScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartingScreen extends World
{

    /**
     * Constructor for objects of class StartingScreen.
     * 
     */
    public StartingScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        Label label = new Label("Hungry Elephant\n Game", 70);
        Label label2 = new Label("Click anywhere to begin", 40);
        addObject(label, getWidth()/2, getHeight()/2 - 35);
        addObject(label2, getWidth()/2, getHeight()/2+100);
    }
    
    public void act()
    {
        if(Greenfoot.mouseClicked(null))
        {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
