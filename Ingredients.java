//package src;
//import java.awt.Image;
//import java.awt.Rectangle;
//
//public class Ingredients
//{
//    private int x;
//    private int y;
//    private int width;
//    private int height;
//    private boolean visible;
//    // Booleans determine if its falling or stacked
//    private boolean fall = true;
//    private boolean stacked = false;
//    private Image image;
//
//    // Method to make it fall
//    public void moveAlien()
//    {
//        if (fall == true)
//        {
//            if (this.y > 700) 
//            {
//                this.y = getRandomY();
//                this.x = getRandomX();
//            }
//            this.y += 1;
//
//        }
//    }
//
//    public int getRandomY()
//    {
//        int result = (int) (Math.random() * 6000 + 1);
//        result = 0 - result;
//        return result;
//    }
//
//    // Acquires random x-coordinate for falling
//    public int getRandomX()
//    {
//        int result = (int) (Math.random() * 700 + 1);
//        return result;
//    }
//
//    // Method to make it move after stacked
//    public void move(int x)
//    {
//        this.x = x;
//    }
//
//    // Sets the fall boolean to false
//    public void stopFall()
//    {
//        this.fall = false;
//    }
//
//    // Stacks the topping using bun coordnates and number of toppinngs stacked
//    public void stack(Bun a, int b) 
//    {
//        this.stacked = true; // boolean to show topping is stacked
//
//        this.x = a.getX();
//        this.y = 600 - b * 30;
//    }
//
//    // These methods return things
//
//    public void setVisible(boolean visible)
//    {
//        this.visible = visible;
//    }
//
//    public Image getImage()
//    {
//        return image;
//    }
//
//    // Creates Rectangle using topping bounds
//    public Rectangle getBounds()
//    {
//        return new Rectangle(x, y, width, height);
//    }
//}