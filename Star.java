import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Star here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Star extends Actor
{
    /**
     * Act - do whatever the Star wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    SimpleTimer timer = new SimpleTimer();
    GreenfootImage[] image = new GreenfootImage[6];
    public Star()
    {
        GreenfootImage thisImage = getImage();
        thisImage.scale(50, 50);
        for(int i = 0; i < image.length; i++)
        {
            image[i] = new GreenfootImage("images/acheivement/ach" + i + ".png");
            image[i].scale(50, 50);
        }
        timer.mark();
    }
    int index;
    boolean oneLoop;
    public void act()
    {
        // Add your action code here.
        if(timer.millisElapsed() > 100 && !oneLoop)
        {
            index = (index + 1) % image.length;
            setImage(image[index]);
            timer.mark();
        }
    }
}
