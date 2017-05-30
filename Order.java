package src;

import java.util.ArrayList;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


/**
 * @author Vivian Cheng, Rohit Kulkarni, Isa Dash
 * Order class to generate burger recipe
 *
 */
public class Order extends JComponent implements ActionListener {
    // Creates new timer to update animations
    private Timer timer;
    // bottom bun to catch ingredients
    private Bun bun;
    double cash = 0;
    
    // Lists of ingredients
    private ArrayList<Lettuce> lets;
    private ArrayList<Meat> meats;
    private ArrayList<Tomato> toms;
    private ArrayList<Onion> onis;
    private ArrayList<Pickle> pics;
    private ArrayList<Cheese> ches;
    private ArrayList<TopBun> tops;

    // Number of each ingredient
    int lettuceN;
    int meatN;
    int tomatoN;
    int onionN;
    int pickleN;
    int cheeseN;
    boolean topbunB;

    // Indicates if game is won
    boolean win = false;
    // Game records
    boolean recordStack = false;
    // Amount of ingredients stacked
    int stackN;
    // How many tries are left out of 3
    int tries;

    // If game is not over yet
    static boolean gameNotEnded;
    static boolean gameInProgress = true;

    // Game area width
    private int B_WIDTH;
    // Game area height
    private int B_HEIGHT;

    // Randomizes starting locations
    // Lettuce
    private int[][] loc = { { getRandomX(), getRandomY() }, { getRandomX(), getRandomY() },
            { getRandomX(), getRandomY() } };
    // Meat
    private int[][] meatLoc = { { getRandomX(), getRandomY() }, { getRandomX(), getRandomY() },
            { getRandomX(), getRandomY() } };
    // Tomato
    private int[][] tomLoc = { { getRandomX(), getRandomY() }, { getRandomX(), getRandomY() },
            { getRandomX(), getRandomY() } };
    // Onion
    private int[][] oniLoc = { { getRandomX(), getRandomY() }, { getRandomX(), getRandomY() },
            { getRandomX(), getRandomY() } };
    // Pickle
    private int[][] picLoc = { { getRandomX(), getRandomY() }, { getRandomX(), getRandomY() },
            { getRandomX(), getRandomY() } };
    // Cheese
    private int[][] cheLoc = { { getRandomX(), getRandomY() }, { getRandomX(), getRandomY() },
            { getRandomX(), getRandomY() } };
    // Top Bun
    private int[][] topLoc = { { getRandomX(), getRandomY() }, { getRandomX(), getRandomY() },
            { getRandomX(), getRandomY() }, { getRandomX(), getRandomY() } };

    /**
     * gets a random value for y
     * 
     * @return result random Y
     */
    public static int getRandomY() {
        int r = (int) (Math.random() * 6000 + 1);
        r = 0 - r;
        return r;
    }

    /**
     * gets a random value for x
     * 
     * @return result random x
     */
    public static int getRandomX() {
        int r = (int) (Math.random() * 700 + 1);
        return r;
    }

    /**
     * Generates an order by randomizing values from 0-4
     * 
     * @return result randomized result
     */
    public static int generateOrder() {
        int r = (int) (Math.random() * 4);
        return r;
    }

    /**
     * Constructor for Order Class
     * 
     * @throws Exception
     *             if exception occurs
     */
    public Order() throws Exception {
        // use left and right arrow keys to control with new KeyListener
        this.addKeyListener(new TAdapter());

        this.setFocusable(true);
        this.setBackground(Color.CYAN);

        this.setDoubleBuffered(true);
        this.setSize(800, 700);

        // Game has not ended
        this.gameNotEnded = true;

        // New bottom bun object
        this.bun = new Bun();

        // Initialize the ingredients
        initToppings();

        // Order generated for each ingredient
        lettuceN = generateOrder();
        meatN = generateOrder();
        tomatoN = generateOrder();
        onionN = generateOrder();
        pickleN = generateOrder();
        cheeseN = generateOrder();

        // Top bun not collected yet
        topbunB = false;

        // Updates falling animations every 10 milliseconds
        timer = new Timer(5, this);
        timer.start();

    }

    /**
     * addNotify method
     */
    public void addNotify() {
        super.addNotify();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();
    }

    /**
     * creates new objects of each ingredient and adds to arraylist
     */
    public void initToppings() {
        lets = new ArrayList<Lettuce>();
        meats = new ArrayList<Meat>();
        toms = new ArrayList<Tomato>();
        onis = new ArrayList<Onion>();
        pics = new ArrayList<Pickle>();
        ches = new ArrayList<Cheese>();
        tops = new ArrayList<TopBun>();

        for (int i = 0; i < loc.length; i++) {
            lets.add(new Lettuce(loc[i][0], loc[i][1]));
            meats.add(new Meat(meatLoc[i][0], meatLoc[i][1]));
            toms.add(new Tomato(tomLoc[i][0], tomLoc[i][1]));
            onis.add(new Onion(oniLoc[i][0], oniLoc[i][1]));
            pics.add(new Pickle(picLoc[i][0], picLoc[i][1]));
            ches.add(new Cheese(cheLoc[i][0], cheLoc[i][1]));
            tops.add(new TopBun(topLoc[i][0], topLoc[i][1]));
        }
    }

    /**
     * GUI component of game to draw all animations
     */
    public void paint(Graphics g) {
        super.paint(g);
        detectCollisions();

        if (gameNotEnded) { // There are tries left

            Graphics2D g2d = (Graphics2D) g;

            // draw bottom bun
            if (bun.isVisible()) {

                g2d.drawImage(bun.getImage(), bun.getX(), bun.getY(), this);
            }

            // Paint each ingredient's GUI
            for (int i = 0; i < lets.size(); i++) {
                Lettuce a = (Lettuce) lets.get(i);
                if (a.isVisible())
                    g2d.drawImage(a.getImage(), a.getXA(), a.getYA(), this);

                Meat m = (Meat) meats.get(i);
                if (a.isVisible())
                    g2d.drawImage(m.getImage(), m.getXA(), m.getYA(), this);

                Tomato t = (Tomato) toms.get(i);
                if (t.isVisible())
                    g2d.drawImage(t.getImage(), t.getXA(), t.getYA(), this);

                Onion o = (Onion) onis.get(i);
                if (o.isVisible())
                    g2d.drawImage(o.getImage(), o.getXA(), o.getYA(), this);

                Pickle p = (Pickle) pics.get(i);
                if (p.isVisible())
                    g2d.drawImage(p.getImage(), p.getXA(), p.getYA(), this);

                Cheese c = (Cheese) ches.get(i);
                if (c.isVisible())
                    g2d.drawImage(c.getImage(), c.getXA(), c.getYA(), this);

                TopBun b = (TopBun) tops.get(i);
                if (b.isVisible())
                    g2d.drawImage(b.getImage(), b.getXA(), b.getYA(), this);
            }

            // Shows the order on the screen, along with the cost and the number
            // of mistakes left
            Font small = new Font("Arial", Font.PLAIN, 18);
            FontMetrics metr = this.getFontMetrics(small);

            g2d.setColor(Color.BLACK);
            g2d.setFont(small);
            g2d.drawString("Lettuce: " + lettuceN, 5, 20);
            g2d.drawString("Meat:   " + meatN, 5, 40);
            g2d.drawString("Tomato:  " + tomatoN, 5, 60);
            g2d.drawString("Onion:   " + onionN, 5, 80);
            g2d.drawString("Pickle:  " + pickleN, 5, 100);
            g2d.drawString("Cheese:  " + cheeseN, 5, 120);
            // Shows the money earned

            g2d.drawString("Cash Earned: " + "$" + cash + "0", 500, 20);
            // Number of errors left
            g2d.drawString("Errors remaining: " + tries, 200, 20);

            if (win) // If game won
            {
                
                String msg = "Burger done! You got $" + cash + "0" + "!";
                Font small2 = new Font("Arial", Font.PLAIN, 40);
                FontMetrics metr2 = this.getFontMetrics(small2);

                g.setColor(Color.BLACK);
                g.setFont(small2);
                g.drawString(msg, (B_WIDTH - metr2.stringWidth(msg)) / 2, B_HEIGHT / 2);
                gameInProgress = false;
            }

            else if (topbunB && !win) // If top bun collected but order not finished
            {
                String msg = "Incomplete";
                Font small3 = new Font("Arial", Font.PLAIN, 80);
                FontMetrics metrHit = this.getFontMetrics(small3);

                g.setColor(Color.BLACK);
                g.setFont(small3);
                g.drawString(msg, (B_WIDTH - metrHit.stringWidth(msg)) / 2, B_HEIGHT / 2);
                gameInProgress = false;
            }
            else if(tries <= 0){
                String msg = "Game Over";
                Font small4 = new Font("Arial", Font.PLAIN, 65);
                FontMetrics metr1 = this.getFontMetrics(small4);

                g.setColor(Color.BLACK);
                g.setFont(small4);
                g.drawString(msg, (B_WIDTH - metr1.stringWidth(msg)) / 2, B_HEIGHT / 2);
                gameInProgress= false;
            }
        } else {
            String msg = "Game Over";
            Font small4 = new Font("Arial", Font.PLAIN, 65);
            FontMetrics metr1 = this.getFontMetrics(small4);

            g.setColor(Color.BLACK);
            g.setFont(small4);
            g.drawString(msg, (B_WIDTH - metr1.stringWidth(msg)) / 2, B_HEIGHT / 2);
            gameNotEnded = false;
            
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    /**
     * Updates game
     * 
     * @param e
     *            ActionEvent e
     */
    public void actionPerformed(ActionEvent e) {
        // Bun moves when keyboard pressed
        bun.move();

        // Updates the location of each ingredient
        for (int i = 0; i < lets.size(); i++) {
            Lettuce lettu = (Lettuce) lets.get(i);
            if (!lettu.isStack())
                lettu.continueFall();
            else
                lettu.move(bun.getX());

            Meat mea = (Meat) meats.get(i);
            if (!mea.isStack())
                mea.continueFall();
            else
                mea.move(bun.getX());

            Tomato tomat = (Tomato) toms.get(i);
            if (!tomat.isStack())
                tomat.continueFall();
            else
                tomat.move(bun.getX());

            Onion onio = (Onion) onis.get(i);
            if (!onio.isStack())
                onio.continueFall();
            else
                onio.move(bun.getX());

            Pickle pickl = (Pickle) pics.get(i);
            if (!pickl.isStack())
                pickl.continueFall();
            else
                pickl.move(bun.getX());

            Cheese chees = (Cheese) ches.get(i);
            if (!chees.isStack())
                chees.continueFall();
            else
                chees.move(bun.getX());

            TopBun topBu = (TopBun) tops.get(i);

            if (!topBu.isStack())
                topBu.continueFall();
            else
                topBu.move(bun.getX());

            // Checks to see if there are any collisions
            detectCollisions();
            repaint();
        }
    }

    /**
     * Sees if ingredients have touched the bottom bun
     */
    public void detectCollisions() {
        // the rectangular border that surrounds bottom bun
        Rectangle border = new Rectangle(bun.getX(), 600 - stackN * 30, 100, 30);

        // Updates game by resetting amount of ingredients stacked and mistakes
        // left
        if (recordStack == false) {
            stackN = 0;
            tries = 3;
        }

        Lettuce a1 = (Lettuce) lets.get(0);
        Rectangle rL1 = a1.getBounds();
        Lettuce a2 = (Lettuce) lets.get(1);
        Rectangle rL2 = a2.getBounds();
        Lettuce a3 = (Lettuce) lets.get(2);
        Rectangle rL3 = a3.getBounds();

        Meat m1 = (Meat) meats.get(0);
        Rectangle rM1 = m1.getBounds();
        Meat m2 = (Meat) meats.get(1);
        Rectangle rM2 = m2.getBounds();
        Meat m3 = (Meat) meats.get(2);
        Rectangle rM3 = m3.getBounds();

        Tomato t1 = (Tomato) toms.get(0);
        Rectangle rT1 = t1.getBounds();
        Tomato t2 = (Tomato) toms.get(1);
        Rectangle rT2 = t2.getBounds();
        Tomato t3 = (Tomato) toms.get(2);
        Rectangle rT3 = t3.getBounds();

        Onion o1 = (Onion) onis.get(0);
        Rectangle rO1 = o1.getBounds();
        Onion o2 = (Onion) onis.get(1);
        Rectangle rO2 = o2.getBounds();
        Onion o3 = (Onion) onis.get(2);
        Rectangle rO3 = o3.getBounds();

        Pickle p1 = (Pickle) pics.get(0);
        Rectangle rP1 = p1.getBounds();
        Pickle p2 = (Pickle) pics.get(1);
        Rectangle rP2 = p2.getBounds();
        Pickle p3 = (Pickle) pics.get(2);
        Rectangle rP3 = p3.getBounds();

        Cheese c1 = (Cheese) ches.get(0);
        Rectangle rC1 = c1.getBounds();
        Cheese c2 = (Cheese) ches.get(1);
        Rectangle rC2 = c2.getBounds();
        Cheese c3 = (Cheese) ches.get(2);
        Rectangle rC3 = c3.getBounds();

        TopBun b1 = (TopBun) tops.get(0);
        Rectangle rB1 = b1.getBounds();
        TopBun b2 = (TopBun) tops.get(1);
        Rectangle rB2 = b2.getBounds();
        TopBun b3 = (TopBun) tops.get(2);
        Rectangle rB3 = b3.getBounds();

        // the rectangular border of bottom bun collides with a falling
        // ingredient
        if (border.intersects(rL1) || border.intersects(rL2) || border.intersects(rL3) ||

                border.intersects(rM1) || border.intersects(rM2) || border.intersects(rM3) || border.intersects(rT1)
                || border.intersects(rT2) || border.intersects(rT3) || border.intersects(rO1) || border.intersects(rO2)
                || border.intersects(rO3) ||

                border.intersects(rP1) || border.intersects(rP2) || border.intersects(rP3) || border.intersects(rC1)
                || border.intersects(rC2) || border.intersects(rC3) ||

                border.intersects(rB1) || border.intersects(rB2) || border.intersects(rB3)) {
            // Records that the first ingredient is stacked
            recordStack = true;

            // intersects but ingredient not stacked
            if (border.intersects(rL1) && !a1.isStack()) {
                border = a1.getBounds();
                stackN++;
                a1.stopFall();
                a1.stack(bun, stackN); // Add ingredient to the burger

                // Tries left, number of lettuces in the order remaining
                if (lettuceN == 0)
                    tries--;
                if (lettuceN > 0) {
                    lettuceN--;
                    cash++;
                }
            } else if (border.intersects(rL2) && !a2.isStack()) {
                border = a2.getBounds();
                stackN++;
                a2.stopFall();
                a2.stack(bun, stackN);

                if (lettuceN == 0)
                    tries--;
                if (lettuceN > 0) {
                    lettuceN--;
                    cash++;
                }
            } else if (border.intersects(rL3) && !a3.isStack()) {
                border = a3.getBounds();
                stackN++;
                a3.stopFall();
                a3.stack(bun, stackN);

                if (lettuceN == 0)
                    tries--;
                if (lettuceN > 0) {
                    lettuceN--;
                    cash++;
                }

            }

            else if (border.intersects(rM1) && !m1.isStack()) {
                border = m1.getBounds();
                stackN++;
                m1.stopFall();
                m1.stack(bun, stackN);

                if (meatN == 0)
                    tries--;
                if (meatN > 0) {
                    meatN--;
                    cash++;
                }
            } else if (border.intersects(rM2) && !m2.isStack()) {
                border = m2.getBounds();
                stackN++;
                m2.stopFall();
                m2.stack(bun, stackN);

                if (meatN == 0)
                    tries--;
                if (meatN > 0) {
                    meatN--;
                    cash++;
                }
            } else if (border.intersects(rM3) && !m3.isStack()) {
                border = m3.getBounds();
                stackN++;
                m3.stopFall();
                m3.stack(bun, stackN);

                if (meatN == 0)
                    tries--;
                if (meatN > 0) {
                    meatN--;
                    cash++;
                }
            } else if (border.intersects(rT1) && !t1.isStack()) {
                border = t1.getBounds();
                stackN++;
                t1.stopFall();
                t1.stack(bun, stackN);

                if (tomatoN == 0)
                    tries--;
                if (tomatoN > 0) {
                    tomatoN--;
                    cash++;
                }
            } else if (border.intersects(rT2) && !t2.isStack()) {
                border = t2.getBounds();
                stackN++;
                t2.stopFall();
                t2.stack(bun, stackN);

                if (tomatoN == 0)
                    tries--;
                if (tomatoN > 0) {
                    tomatoN--;
                    cash++;
                }
            } else if (border.intersects(rT3) && !t3.isStack()) {
                border = t3.getBounds();
                stackN++;
                t3.stopFall();
                t3.stack(bun, stackN);

                if (tomatoN == 0)
                    tries--;
                if (tomatoN > 0) {
                    tomatoN--;
                    cash++;
                }

            }

            else if (border.intersects(rO1) && !o1.isStack()) {
                border = o1.getBounds();
                stackN++;
                o1.stopFall();
                o1.stack(bun, stackN);

                if (onionN == 0)
                    tries--;
                if (onionN > 0) {
                    onionN--;
                    cash++;
                }
            } else if (border.intersects(rO2) && !o2.isStack()) {
                border = o2.getBounds();
                stackN++;
                o2.stopFall();
                o2.stack(bun, stackN);

                if (onionN == 0)
                    tries--;
                if (onionN > 0) {
                    onionN--;
                    cash++;
                }
            } else if (border.intersects(rO3) && !o3.isStack()) {
                border = o3.getBounds();
                stackN++;
                o3.stopFall();
                o3.stack(bun, stackN);

                if (onionN == 0)
                    tries--;
                if (onionN > 0) {
                    onionN--;
                    cash++;
                }

            }

            else if (border.intersects(rP1) && !p1.isStack()) {
                border = p1.getBounds();
                stackN++;
                p1.stopFall();
                p1.stack(bun, stackN);

                if (pickleN == 0)
                    tries--;
                if (pickleN > 0) {
                    pickleN--;
                    cash++;
                }
            } else if (border.intersects(rP2) && !p2.isStack()) {
                border = p2.getBounds();
                stackN++;
                p2.stopFall();
                p2.stack(bun, stackN);

                if (pickleN == 0)
                    tries--;
                if (pickleN > 0) {
                    pickleN--;
                    cash++;
                }
            } else if (border.intersects(rP3) && !p3.isStack()) {
                border = p3.getBounds();
                stackN++;
                p3.stopFall();
                p3.stack(bun, stackN);

                if (pickleN == 0)
                    tries--;
                if (pickleN > 0) {
                    pickleN--;
                    cash++;
                }

            }

            else if (border.intersects(rC1) && !c1.isStack()) {
                border = c1.getBounds();
                stackN++;
                c1.stopFall();
                c1.stack(bun, stackN);

                if (cheeseN == 0)
                    tries--;
                if (cheeseN > 0) {
                    cheeseN--;
                    cash++;
                }
            } else if (border.intersects(rC2) && !c2.isStack()) {
                border = c2.getBounds();
                stackN++;
                c2.stopFall();
                c2.stack(bun, stackN);

                if (cheeseN == 0)
                    tries--;
                if (cheeseN > 0) {
                    cheeseN--;
                    cash++;
                }
            } else if (border.intersects(rC3) && !c3.isStack()) {
                border = c3.getBounds();
                stackN++;
                c3.stopFall();
                c3.stack(bun, stackN);

                if (cheeseN == 0)
                    tries--;
                if (cheeseN > 0) {
                    cheeseN--;
                    cash++;
                }

            }

            else if (border.intersects(rB1) && !b1.isStack()) {
                border = (null);
                stackN++;
                b1.stopFall();
                b1.stack(bun, stackN);
                topbunB = true;

                if (lettuceN == 0 && meatN == 0 && tomatoN == 0 && onionN == 0 && pickleN == 0 && cheeseN == 0 && tries != 0) {
                    win = true;
                    
                }
            } else if (border.intersects(rB2) && !b2.isStack()) {
                border = (null);
                stackN++;
                b2.stopFall();
                b2.stack(bun, stackN);
                topbunB = true;

                if (lettuceN == 0 && meatN == 0 && tomatoN == 0 && onionN == 0 && pickleN == 0 && cheeseN == 0 && tries != 0) {
                    win = true;
                }
            } else if (border.intersects(rB3) && !b3.isStack()) {
                border = (null);
                stackN++;
                b3.stopFall();
                b3.stack(bun, stackN);
                topbunB = true;

                if (lettuceN == 0 && meatN == 0 && tomatoN == 0 && onionN == 0 && pickleN == 0 && cheeseN == 0 && tries != 0) {
                    win = true;
                }

            }
        }

        if (topbunB) // If top bun has been collected
        {
            if (!a1.isStack()) {

                a1.stopFall();
            }
            if (!a2.isStack()) {

                a2.stopFall();
            }
            if (!a3.isStack()) {

                a3.stopFall();
            }

            if (!m1.isStack()) {

                m1.stopFall();
            }
            if (!m2.isStack()) {

                m2.stopFall();
            }
            if (!m3.isStack()) {

                m3.stopFall();
            }

            if (!t1.isStack()) {

                t1.stopFall();
            }
            if (!t2.isStack()) {

                t2.stopFall();
            }
            if (!t3.isStack()) {

                t3.stopFall();
            }

            if (!o1.isStack()) {

                o1.stopFall();
            }
            if (!o2.isStack()) {

                o2.stopFall();
            }
            if (!o3.isStack()) {

                o3.stopFall();
            }

            if (!p1.isStack()) {

                p1.stopFall();
            }
            if (!p2.isStack()) {

                p2.stopFall();
            }
            if (!p3.isStack()) {

                p3.stopFall();
            }

            if (!c1.isStack()) {

                c1.stopFall();
            }
            if (!c2.isStack()) {

                c2.stopFall();
            }
            if (!c3.isStack()) {

                c3.stopFall();
            }

            if (!b2.isStack()) {

                b2.stopFall();
            }
            if (!b3.isStack()) {

                b3.stopFall();
            }

        }
//        if(win)
//            gameNotEnded = false;

        if (tries == 0) // Game ends when 3 mistakes made
            gameNotEnded = false;
    }

    /**
     * 
     * Keylistener to control the bottom bun using left and right arrow keys
     *
     */
    private class TAdapter extends KeyAdapter {
        /**
         * when the keys are released
         */
        public void keyReleased(KeyEvent e) {
            bun.keyReleased(e);
        }

        /**
         * when the keys are pressed
         */
        public void keyPressed(KeyEvent e) {
            bun.keyPressed(e);
        }
    }
}