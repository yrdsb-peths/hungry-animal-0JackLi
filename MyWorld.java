import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
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
    private final int STARTING_SCORE = 0;
    private Label scoreLabel;
    private Label healthLabel;
    private SimpleTimer timer;
    private SimpleTimer timer2;
    private Apple apple;
    private Banana banana;
    private Grape grape;
    private Bomb bomb;
    public static int score;
    private int itemsCount = 4;
    private Actor[] actor;

    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        initializeActors();
        initializeTimer();

        timer.mark();
        timer2.mark();
        Background background = new Background(); 
        Elephant elephant = new Elephant();
        addObject(background,100, 150);
        addObject(elephant, 300, 300);
        spawnApple();
        spawnBanana();
        
        score = STARTING_SCORE;
        scoreLabel = new Label("Score: " + score, 40);
        healthLabel = new Label("Health: " + Elephant.speed, 40);
        addObject(scoreLabel, 480, 20);
        addObject(healthLabel, 150, 20);
    }
    
    public void act()
    {
        healthLabel.setValue("Health: " + Elephant.speed);
    }

    public int timeChecks(boolean first, boolean second)
    {
        if(first){
            return timer.millisElapsed();
        }
        else if(second)
        {
            return timer2.millisElapsed();
        }
        return 0;
    }

    public void timerMarks(boolean first, boolean second)
    {
        if(first){
            timer.mark();
        }
        else if(second)
        {
            timer2.mark();
        }
    }

    public void initializeTimer()
    {
        timer = new SimpleTimer();
        timer2 = new SimpleTimer();
    }

    public void initializeActors()
    {
        actor = new Actor[]{apple = new Apple(), banana = new Banana(), grape = new Grape()};
    }

    public void spawnApple()
    {
        apple = new Apple();
        GreenfootImage appImage = apple.getImage();
        randomScale(appImage);
        randomPosition(apple);
    }

    public void addScores(int score)
    {
        if(-(score) > this.score)
        {
            this.score = 0;
        }
        else{this.score += score;}
        scoreLabel.setValue("Score: " + this.score);
    }

    public void spawnBanana()
    {
        banana = new Banana();
        GreenfootImage banaImage = banana.getImage();
        randomScale(banaImage);
        randomPosition(banana);
    }

    public void spawnGrape()
    {
        grape = new Grape();
        GreenfootImage graImage = grape.getImage();
        randomScale(graImage);
        randomPosition(grape);
    }

    public void spawnBomb(int maxBomb, int maxNum)
    {
        int amount = Greenfoot.getRandomNumber(maxBomb);
        int chance = Greenfoot.getRandomNumber(maxNum);
        if(chance == 0){
            for(int i = 0; i < amount+1; i++)
            {
                bomb = new Bomb();
                GreenfootImage bombImage = bomb.getImage();
                randomScale(bombImage);
                randomPosition(bomb);
            }
        }
    }

    public void randomFruits()
    {
        int amount = Greenfoot.getRandomNumber(4);
        for(int i = 0; i < amount+1; i++)
        {
            int randItems = Greenfoot.getRandomNumber(itemsCount-1);
            spawnRandItems(randItems);
        }

    }

    private void spawnRandItems(int items)
    {
        initializeActors();
        GreenfootImage image = actor[items].getImage();
        randomScale(image);
        randomPosition(actor[items]);
    }

    public void randomScale(GreenfootImage img)
    {
        int rand = Greenfoot.getRandomNumber(60);
        img.scale(rand+10, rand+10);
    }

    public void randomPosition(Actor actor2)
    {
        int x = Greenfoot.getRandomNumber(600);
        int y = 0;
        addObject(actor2, x, y);
    }

    public void removeObj(Actor c)
    {
        removeObject(c);
    }

    public void gameEnd()
    {
        if(score <= 0 || Elephant.speed <= 0)
        {
            Greenfoot.setWorld(new EndingScreen());
        }
    }
}