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
    private Label scoreLabel;
    private SimpleTimer timer;
    private Apple apple;
    private Banana banana;
    private Grape grape;
    private Bomb bomb;
    private int score = 0;
    private int itemsCount = 4;
    private Actor[] actor;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        initializeActors();
        initializeTimer();
        
        timer.mark();
        Background background = new Background(); 
        Elephant elephant = new Elephant();
        addObject(background,100, 150);
        addObject(elephant, 300, 300);
        spawnApple();
        spawnBanana();
        
        scoreLabel = new Label(score, 70);
        addObject(scoreLabel, 480, 20);
    }
    
    public int timeChecks()
    {
        return timer.millisElapsed();
    }
    public void timerMarks()
    {
        timer.mark();
    }
    public void initializeTimer()
    {
        timer = new SimpleTimer();
    }
    public void initializeActors()
    {
        actor = new Actor[]{apple = new Apple(), banana = new Banana(), grape = new Grape()
            , bomb = new Bomb()};
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
        this.score += score;
        scoreLabel.setValue(this.score);
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
    
    public void spawnBomb()
    {
        bomb = new Bomb();
        GreenfootImage bombImage = bomb.getImage();
        randomScale(bombImage);
        randomPosition(bomb);
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
        randomPosition(actor[items]);
    }
    
    public void randomScale(GreenfootImage img)
    {
        int rand = Greenfoot.getRandomNumber(90);
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
       
    }
}
