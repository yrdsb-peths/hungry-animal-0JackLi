     import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    
    /**
     * Write a description of class Elephant here.
     * 
     * @author (your name) 
     * @version (a version number or a date)
     */
public class Elephant extends Actor
{
        /**
         * Act - do whatever the Elephant wants to do. This method is called whenever
         * the 'Act' or 'Run' button gets pressed in the environment.
         */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        if(world.timeChecks() > 500)
        {
            world.timerMarks();
            world.randomFruits();
        }
        if(eat(Apple.class) || eat(Banana.class) || eat(Grape.class))
        {
            world.randomFruits();
        }
        else if(eat(Bomb.class))
        {
            world.gameEnd();
        }
        if(Greenfoot.isKeyDown("a"))
        {
            move(-5);
        }
        else if(Greenfoot.isKeyDown("d"))
        {
            move(5);
        }
    }
    
    public boolean eat(Class<?> cls){
        if(isTouching(cls))
        {
            removeTouching(cls);
            return true;
        }
        return false;
    }
}
