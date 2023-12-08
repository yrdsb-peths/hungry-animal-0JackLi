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
    private final int STARTING_SPEED = 5;
    private final int STARTING_HEALTH = 5;
    public static int speed;
    public static int health;
    private int maxNum = 20;
    private int maxBomb = 1;
    private int bombTimer = 5000;
    GreenfootImage[] rightIdle = new GreenfootImage[8];
    GreenfootImage[] leftIdle = new GreenfootImage[8];
    GreenfootImage[] rightWalking = new GreenfootImage[8];
    GreenfootImage[] leftWalking = new GreenfootImage[8];
    private String direction = "right";
    private SimpleTimer timer = new SimpleTimer();
    private SimpleTimer healthTimer = new SimpleTimer();

    /** 
     * Elephant Constructor 
     * Create an elephant with all the animations
     */
    public Elephant()
    {
        speed = STARTING_SPEED;
        health = STARTING_HEALTH;
        for(int i = 0; i < rightIdle.length; i++)
        {
            rightIdle[i] = new GreenfootImage("images/elephant_idle/idle" + i + ".png");
        }

        for(int i = 0; i < leftIdle.length; i++){
            leftIdle[i] = new GreenfootImage("images/elephant_idle/idle" + i + ".png");
            leftIdle[i].mirrorHorizontally();
        }
        for(int i = 0; i < rightWalking.length; i++)
        {
            rightWalking[i] = new GreenfootImage("images/elephant_walking/tile00" + i + ".png");
        }
        for(int i = 0; i < leftWalking.length; i++)
        {
            leftWalking[i] = new GreenfootImage("images/elephant_walking/tile00" + i + ".png");
            leftWalking[i].mirrorHorizontally();
        }
        setImage(rightIdle[0]);
        timer.mark();
    }

    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        respawnItems();
        keyDown();
        //perform animation for every 20 milliseconds
        if(timer.millisElapsed() > 20)
        {
            if(direction.equals("right"))
            {
                idleAnimation(rightWalking);
            }
            else if(direction.equals("left"))
            {
                idleAnimation(leftWalking);
            }
            else if(direction.equals("rightIdle")){
                idleAnimation(rightIdle);
            }
            else
            {
                idleAnimation(leftIdle);
            }
            timer.mark();
        }

        if(healthTimer.millisElapsed() >= 800)
        {
            reduceHealth(1, false);
            healthTimer.mark();
        }

        //eats the items
        if(eat(Apple.class) || eat(Banana.class) || eat(Grape.class))
        {
            //addes 5 scores and increase the elephant speed
            GreenfootSound sound = new GreenfootSound("sounds/eatsound.mp3");
            sound.play();
            world.addScores(5);
            increaseHealth(1);
            speedCheck();
        }
        else if(eat(Bomb.class))
        {
            //take out 50 scores and reduce the speed
            GreenfootSound sound = new GreenfootSound("sounds/explosion.mp3");
            sound.play();
            world.addScores(-50);
            reduceHealth(5, true);
            speedCheck();
            world.gameEnd();
        }

    }

    /**
     * Perform idle animation when the elephant is standing
     * still
     */
    int idleIndex = 0;
    public void idleAnimation(GreenfootImage[] idle)
    {
        idleIndex = (idleIndex + 1) % idle.length;
        setImage(idle[idleIndex]);
    }

    /**
     * remove the object when touching
     */
    public boolean eat(Class<?> cls){
        if(isTouching(cls))
        {
            removeTouching(cls);
            return true;
        }
        return false;
    }

    /**
     * check which key is pressed and decide which way the elephant move
     * 
     */
    public void keyDown()
    {
        if(Greenfoot.isKeyDown("a"))
        {
            move(-(speed));
            direction = "left";
        }
        else if(Greenfoot.isKeyDown("d"))
        {
            move(speed);
            direction = "right";
        }
        else
        {
            if(direction == "left")
            {
                direction = "leftIdle";
            }
            else if(direction == "right")
            {
                direction = "rightIdle";
            }
        }
    }

    public void reduceHealth(int amount, boolean isBomb)
    {
        if(isBomb)
        {
            if(amount > health)
            {
                health = 0;
            }
            else
            {
                health -= amount;
            }
        }
        else{if(health > 5){health -= amount;}}
    }

    private void increaseHealth(int amount)
    {
        if(health + amount < 30)
        {
            health += amount;
        }
        else
        {
            health = 30;
        }
    }

    private void speedCheck()
    {
        if(health <= 10)
        {
            speed = health;
        }
        else
        {
            speed = 10;
        }
    }

    /**
     * spawn random items on the canvas
     */
    public void respawnItems()
    {
        MyWorld world = (MyWorld) getWorld();
        if(world.timeChecks(true, false) > 500)
        {
            //marks the timer
            world.timerMarks(true, false);
            world.randomFruits();
            world.spawnBomb(maxBomb, maxNum);
            speedCheck();
            if(bombTimer > 100)
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
            if(maxBomb < 8){
                maxBomb++;
            }
            world.timerMarks(false, true);
        }
    }
}