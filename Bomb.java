import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Actor
{
    /**
     * Act - do whatever the Bomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
       setLocation(getX(), getY()+5);
       MyWorld world = (MyWorld) getWorld();
       if(getY() >= world.getHeight() - 1)
       {
           world.removeObj(this);
           //world.randomFruits();
       }
    }    
}
