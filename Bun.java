package src;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * @author Vivian, Isa, Rohit (Period 3)
 * Bun Class
 *
 */
public class Bun {

	

	private Image image;

	//Fields for coordinates, speed
	double speedx;
	double speedy;
	public int x;
	public int y;
	public double speed = 3.0;
	private Timer timer;

	// Bun dimension
	private int width;
	public int height;
	
	public void lessenSpeed()
	{
		speed = speed - 0.5;
	}

    // Whether bun is visible
    private boolean visible;

    /**
     * Constructor of bun
     */
    public Bun()
    {
        // Creates the image representing the bun
        ImageIcon ii = new ImageIcon(this.getClass().getResource("bottomBun.png"));
        image = ii.getImage();

        //bun image size
        width = image.getWidth(null);
        height = image.getHeight(null);

        visible = true;

         //initial bottom bun coordinates
        x = 350;
        y = 600;

    }

    /**
     * Moves the bun 
     */
    public void move()
    {
        x += speedx;

        //Cannot surpass the edges of the screen
        if (x < 1)
        {
            x = 1;
        }
        else if (x > 700)
        {
            x = 700;
        }
    }

    /**
     * Returns x coordinate of bun
     * @return x
     *      x coordinate
     */
    public int getX()
    {
        return x;
    }


    /**
     * Returns y coordinate of bun
     * @return y
     *      y coordinate
     */
    public int getY()
    {
        return y;
    }

    


    /**
     * Returns image of bun
     * @return image
     *      Image of bun
     */
    public Image getImage() 
    {
        return image;
    }

    /**
     * Returns if bun is visible
     * @param visible
     *      If bun is visible
     */
    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }


    /**
     * Whether bun is visible
     * @return visible
     *      If bun is visible
     */
    public boolean isVisible()
    {
        return visible;
    }

    /**
     * Returns the rectangular border of the bun image
     * @return Rectangle
     *      Rectangular bounds of bun
     */
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, width, height);
    }
/**
 * What happens when the key is pressed based on if it's right or left arrow key
 * @param e
 * KeyEvent
 */
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        

        if (key == KeyEvent.VK_RIGHT)
        {
            speedx = speed;
        }
        if (key == KeyEvent.VK_LEFT)
        {
            speedx = speed * -1;
        }
    }

    /**
     * When key is released
     * @param e
     *  KeyEvent
     */
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        

        if (key == KeyEvent.VK_RIGHT)
        {
            speedx = 0;
        }
        if (key == KeyEvent.VK_LEFT)
        {
            speedx = 0;
        }
    }
}
