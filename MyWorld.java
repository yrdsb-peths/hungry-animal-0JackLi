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
    private Label scoreLabel;
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
        initilizeActors();
        Background background = new Background(); 
        Elephant elephant = new Elephant();
        addObject(background,100, 150);
        addObject(elephant, 300, 300);
        spawnApple();
        spawnBanana();
        
        scoreLabel = new Label(score, 70);
        addObject(scoreLabel, 20, 20);
    }
    
    public void initilizeActors()
    {
        actor = new Actor[]{apple, banana, grape, bomb};
    }
    
    public void spawnApple()
    {
        apple = new Apple();
        GreenfootImage appImage = apple.getImage();
        randomScale(appImage);
        randomPosition(apple);
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
        int randItems = Greenfoot.getRandomNumber(itemsCount-1);
        
    }
    
    private void spawnRandItems(int items)
    {
        randomPosition(actor[items]);
    }
    
    public void randomScale(GreenfootImage img)
    {
        int rand = Greenfoot.getRandomNumber(90);
        img.scale(rand, rand);
    }
    
    public void randomPosition(Actor actor)
    {
        int x = Greenfoot.getRandomNumber(600);
        int y = 0;
        addObject(actor, x, y);
    }
    
    public void removeObj(Actor c)
    {
        removeObject(c);
    }
    
    public void gameEnd()
    {
       
    }
}
