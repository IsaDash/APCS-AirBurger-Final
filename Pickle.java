
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * 
 * Lettuce class in Ingredients
 * 
 * @author Vivian Cheng, Isa Dash, Rohit Kulkarni
 *
 */
public class Pickle  {
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean visible;
	private boolean fall = true;
	private boolean stacked = false;
	private Image image;

	/**
	 * Constructor
	 * @param x
	 * 		x coordinate
	 * @param y
	 * 		y coordinate
	 */
	public Pickle(int x, int y) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource("pickles.png"));
		image = ii.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);
		visible = true;
		this.x = x;
		this.y = y;
	}

	/**
	 * make ingredient fall
	 */
	public void continueFall() {
		if (fall == true) {
			if (this.y > 700) {
				this.y = getRandomY();
				this.x = getRandomX();
			}
			this.y += 1;
		}
	}

	/**
	 * @param x
	 *            move object to coordinate x
	 */
	public void move(int x) {
		this.x = x;
	}

	/**
	 * gets a random y value
	 * 
	 * @return random integer result
	 */
	public int getRandomY() {
		int result = (int) (Math.random() * 6000 + 1);
		result = 0 - result;
		return result;
	}

	/**
	 * gets a random x value
	 * @return result
	 * 			random integer
	 */
	public int getRandomX() {
		int result = (int) (Math.random() * 700 + 1);
		return result;
	}

	/**
	 * ingredient stopBunsAL falling
	 */
	public void stopFall() {
		this.fall = false;
	}

	/**
	 * stacks the ingredient onto the bottom bun
	 */
	public void stack(Bun a, int b) {
		this.stacked = true;

		this.x = a.getX();
		this.y = (600 - b * 30);
	}

	/**
	 * returns x value
	 * 
	 * @return x coordinate x
	 */
	public int getXCoord() {
		return x;
	}

	/**
	 * returns y value
	 * 
	 * @return y coordinate y
	 */
	public int getYCoord() {
		return y;
	}

	/**
	 * visible ingredient
	 * 
	 * @return visible ingredient is visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * if the ingredient is stacked
	 * 
	 * @return stacked ingredient is stacked
	 */
	public boolean hasBeenStacked() {
		return stacked;
	}

	/**
	 * make ingredient visible
	 * 
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Returns image
	 * 
	 * @return image an image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Creates new rectangle with ingredient's border
	 * 
	 * @return Rectangle Rectangle bounds
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}