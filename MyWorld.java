import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    Apple apple;
    Elephant elephant;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        Background background = new Background(); 
        elephant = new Elephant();
        addObject(background,100, 150);
        addObject(elephant, 300, 300);
        spawnApple();
    }
    
    public void spawnApple()
    {
        apple = new Apple();
        GreenfootImage image = apple.getImage();
        int x = Greenfoot.getRandomNumber(600);
        int y = 0;
        int rand = Greenfoot.getRandomNumber(90);
        image.scale(rand+10, rand+10);
        addObject(apple, x, y);
    }
    
    public void removeObj(Actor c)
    {
        removeObject(c);
    }
    
    public void gameEnd()
    {
       GreenfootImage eleImage = elephant.getImage();
       GreenfootImage appImage = apple.getImage();
       if(eleImage.getWidth() < appImage.getWidth())
       {
           Greenfoot.stop();
       }
    }
}
