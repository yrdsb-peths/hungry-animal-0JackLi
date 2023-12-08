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
    GreenfootSound gameMusic = new GreenfootSound("sounds/gamemusic.mp3");
    SimpleTimer[] timer;
    GreenfootImage sceneImage;
    private final int STARTING_SCORE = 0;
    private Label scoreLabel;
    private Label healthLabel;
    private Apple apple;
    private Banana banana;
    private Grape grape;
    private Bomb bomb;
    private Star star;
    private Morning morning;
    private Midday midday;
    private Afternoon afternoon;
    private Night night;
    private Label achiLabel;
    public static int score;
    private int itemsCount = 4;
    private Actor[] actor;
    private Actor[] scene;
    private GreenfootImage[] background = new GreenfootImage[4];
    private int nextPoint = 100;
    private int spawnCount = 1;

    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1300, 389, 1);
        score = STARTING_SCORE;

        Elephant elephant = new Elephant();
        scoreLabel = new Label("Score: " + score, 40);
        healthLabel = new Label("Health: " + Elephant.health, 40);
        achiLabel = new Label("", 25);
        star = new Star();
        addObject(scoreLabel, 1000, 20);
        addObject(healthLabel, 150, 20);
        addObject(elephant, 300, 365);

        initializeActors();
        initializeTimer(5);

        sceneImage = scene[0].getImage();
        sceneImage.scale(getWidth(), getHeight());
        setBackground(sceneImage);
        gameMusic.playLoop();

        for(int i = 0; i < background.length; i++)
        {
            background[i] = scene[i].getImage();
            background[i].scale(getWidth(), getHeight());
        }
        setBackground(background[0]);
    }

    int nextScene = 0;
    public void act()
    {
        healthLabel.setValue("Health: " + Elephant.health);
        if(this.score >= nextPoint)
        {
            achiLabel.setValue(nextPoint + " points");
            addObject(star, getWidth()/2, 70);
            addObject(achiLabel, getWidth()/2, 110);
            timer[2].mark();
            nextPoint += 100;
        }
        if(timer[2].millisElapsed() >= 2000)
        {
            removeObj(star);
            removeObj(achiLabel);
        }
        if(timer[3].millisElapsed() >= 20000)
        {
            nextScene = (nextScene + 1) % scene.length;
            setBackground(background[nextScene]);
            timer[3].mark();
        }

        if(timer[4].millisElapsed() >= 5000)
        {
            if(spawnCount <= 8)
            {
                spawnCount++;
            }
            timer[4].mark();
        }
    }

    public int timeChecks(boolean first, boolean second)
    {
        if(first){
            return timer[0].millisElapsed();
        }
        else if(second)
        {
            return timer[1].millisElapsed();
        }
        return 0;
    }

    public void timerMarks(boolean first, boolean second)
    {
        if(first){
            timer[0].mark();
        }
        else if(second)
        {
            timer[1].mark();
        }
    }

    public void initializeTimer(int amount)
    {
        timer = new SimpleTimer[amount];
        for(int i = 0; i < amount; i++)
        {
            timer[i] = new SimpleTimer();
        }
    }

    public void initializeActors()
    {
        actor = new Actor[]{apple = new Apple(), banana = new Banana(), grape = new Grape()};
        scene = new Actor[]{morning = new Morning(), midday = new Midday(), afternoon = new Afternoon(), night = new Night()};
    }

    /**
     * keep count of the score 
     */
    public void addScores(int score)
    {
        if(-(score) > this.score)
        {
            this.score = 0;
        }
        else{this.score += score;}
        scoreLabel.setValue("Score: " + this.score);
    }

    /**
     * create and display apple actor on canvas
     */
    public void spawnApple()
    {
        apple = new Apple();
        GreenfootImage appImage = apple.getImage();
        randomScale(appImage);
        randomPosition(apple);
    }

    /**
     * create and display banana actor on canvas
     */
    public void spawnBanana()
    {
        banana = new Banana();
        GreenfootImage banaImage = banana.getImage();
        randomScale(banaImage);
        randomPosition(banana);
    }

    /**
     * create and display grape actor on canvas
     */
    public void spawnGrape()
    {
        grape = new Grape();
        GreenfootImage graImage = grape.getImage();
        randomScale(graImage);
        randomPosition(grape);
    }

    /**
     * create and display a certain amount of bomb actor on canvas
     */
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

    /**
     * gets a random amount and add that amount of random actor to another method
     */
    public void randomFruits()
    {
        int amount = Greenfoot.getRandomNumber(spawnCount);
        for(int i = 0; i < amount+1; i++)
        {
            int randItems = Greenfoot.getRandomNumber(itemsCount-1);
            spawnRandItems(randItems);
        }

    }

    /**
     * turn the specific actor to a GreenfootImage
     */
    private void spawnRandItems(int items)
    {
        initializeActors();
        GreenfootImage image = actor[items].getImage();
        randomScale(image);
        randomPosition(actor[items]);
    }

    /**
     * set a random size for a GreenfootImage
     */
    public void randomScale(GreenfootImage img)
    {
        int rand = Greenfoot.getRandomNumber(40);
        img.scale(rand+10, rand+10);
    }

    /**
     * set a random positon for an actor
     */
    public void randomPosition(Actor actor2)
    {
        int x = Greenfoot.getRandomNumber(getWidth());
        int y = 0;
        addObject(actor2, x, y);
    }

    public void removeObj(Actor c)
    {
        removeObject(c);
    }

    /**
     * teleport to ending screen when the score/health reached 0
     */
    public void gameEnd()
    {
        if(score <= 0 || Elephant.health <= 0)
        {
            Greenfoot.setWorld(new EndingScreen());
            gameMusic.stop();
        }
    }
}