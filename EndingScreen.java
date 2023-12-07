import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndingScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndingScreen extends World
{

    /**
     * Constructor for objects of class EndingScreen.
     * 
     */
    Label gameOverDisplay = new Label("Game Over", 90);
    GreenfootSound outro = new GreenfootSound("sounds/outro.mp3");

    public EndingScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        Label label = new Label("Click anywhere to restart", 40);
        Label highScoreLabel = new Label("Final Score: " + MyWorld.score, 40);
        addObject(label, getWidth()/2, getHeight()/2+100); 
        addObject(gameOverDisplay, getWidth()/2, getHeight()/2-20);
        addObject(highScoreLabel, 150, 30);
        outro.playLoop();

        if(MyWorld.score <= 0)
        {
            Label scoreEnd = new Label("Your score reached 0", 40);
            addObject(scoreEnd, getWidth()/2, getHeight()/2+40);
        }
        else if(Elephant.health <= 0)
        {
            Label speedEnd = new Label("Your health reached 0", 40);
            addObject(speedEnd, getWidth()/2, getHeight()/2+40);
        }
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(null))
        {
            Greenfoot.setWorld(new StartingScreen());
            outro.stop();
        }
    }
}
