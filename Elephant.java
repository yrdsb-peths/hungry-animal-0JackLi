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
    public static int speed = 5;
    private int maxNum = 20;
    private int maxBomb = 1;
    private int bombTimer = 5000;
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        if(world.timeChecks(true, false) > 500)
        {
            //marks the timer
            world.timerMarks(true, false);
            world.randomFruits();
            world.spawnBomb(maxBomb, maxNum);
            speedReduce(1, false);
            if(bombTimer > 550)
            {
                bombTimer -= 50;
            }
        }
        if(world.timeChecks(false, true) > bombTimer)
        {
            if(maxNum > 2)
            {
                maxNum--;
            }
            if(maxBomb < 5){
                maxBomb++;
            }
            world.timerMarks(false, true);
        }
        if(eat(Apple.class) || eat(Banana.class) || eat(Grape.class))
        {
            //generates a random fruits
            world.addScores(5);
            speed++;
        }
        else if(eat(Bomb.class))
        {
            world.addScores(-50);
            world.gameEnd();
            speedReduce(5, true);
        }
        if(Greenfoot.isKeyDown("a"))
        {
            move(-(speed));
        }
        else if(Greenfoot.isKeyDown("d"))
        {
            move(speed);
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
    
    public void speedReduce(int amount, boolean isBomb)
    {
        if(amount >= speed && !isBomb)
        {
            speed = 1;
        }
        else if(amount >= speed && isBomb)
        {
            speed = 0;
        }
        else{speed -= amount;}
    }
}
