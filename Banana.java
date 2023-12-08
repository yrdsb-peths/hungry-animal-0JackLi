import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Banana here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Banana extends Actor
{
    /**
     * Act - do whatever the Banana wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        GreenfootImage image = getImage();
        setLocation(getX(), getY()+5);
        MyWorld world = (MyWorld) getWorld();
        if(getY() >= world.getHeight() - image.getHeight())
        {
            world.removeObj(this);
        }
    }    
}