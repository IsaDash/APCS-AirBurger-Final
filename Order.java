

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
    private ArrayList<Lettuce> lettuceAL;
    private ArrayList<Meat> meatsAL;
    private ArrayList<Tomato> tomatoesAL;
    private ArrayList<Onion> onionsAL;
    private ArrayList<Pickle> picklesAL;
    private ArrayList<Cheese> cheesesAL;
    private ArrayList<TopBun> topBunsAL;

    // Number of each ingredient
    int numOfLettuce;
    int numOfMeat;
    int numOfTomato;
    int numOfOnion;
    int numOfPickle;
    int numOfCheese;
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
        this.setOpaque(true);
        this.setBackground(new Color(158, 232, 239));

        this.setDoubleBuffered(true);
        this.setSize(800, 700);

        // Game has not ended
        this.gameNotEnded = true;

        // New bottom bun object
        this.bun = new Bun();

        // Initialize the ingredients
        initToppings();

        // Order generated for each ingredient
        numOfLettuce = generateOrder();
        numOfMeat = generateOrder();
        numOfTomato = generateOrder();
        numOfOnion = generateOrder();
        numOfPickle = generateOrder();
        numOfCheese = generateOrder();

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
        lettuceAL = new ArrayList<Lettuce>();
        meatsAL = new ArrayList<Meat>();
        tomatoesAL = new ArrayList<Tomato>();
        onionsAL = new ArrayList<Onion>();
        picklesAL = new ArrayList<Pickle>();
        cheesesAL = new ArrayList<Cheese>();
        topBunsAL = new ArrayList<TopBun>();

        for (int i = 0; i < loc.length; i++) {
            lettuceAL.add(new Lettuce(loc[i][0], loc[i][1]));
            meatsAL.add(new Meat(meatLoc[i][0], meatLoc[i][1]));
            tomatoesAL.add(new Tomato(tomLoc[i][0], tomLoc[i][1]));
            onionsAL.add(new Onion(oniLoc[i][0], oniLoc[i][1]));
            picklesAL.add(new Pickle(picLoc[i][0], picLoc[i][1]));
            cheesesAL.add(new Cheese(cheLoc[i][0], cheLoc[i][1]));
            topBunsAL.add(new TopBun(topLoc[i][0], topLoc[i][1]));
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
            for (int i = 0; i < lettuceAL.size(); i++) {
                Lettuce a = (Lettuce) lettuceAL.get(i);
                if (a.isVisible())
                    g2d.drawImage(a.getImage(), a.getXCoord(), a.getYCoord(), this);

                Meat m = (Meat) meatsAL.get(i);
                if (a.isVisible())
                    g2d.drawImage(m.getImage(), m.getXCoord(), m.getYCoord(), this);

                Tomato t = (Tomato) tomatoesAL.get(i);
                if (t.isVisible())
                    g2d.drawImage(t.getImage(), t.getXCoord(), t.getYCoord(), this);

                Onion o = (Onion) onionsAL.get(i);
                if (o.isVisible())
                    g2d.drawImage(o.getImage(), o.getXCoord(), o.getYCoord(), this);

                Pickle p = (Pickle) picklesAL.get(i);
                if (p.isVisible())
                    g2d.drawImage(p.getImage(), p.getXCoord(), p.getYCoord(), this);

                Cheese c = (Cheese) cheesesAL.get(i);
                if (c.isVisible())
                    g2d.drawImage(c.getImage(), c.getXCoord(), c.getYCoord(), this);

                TopBun b = (TopBun) topBunsAL.get(i);
                if (b.isVisible())
                    g2d.drawImage(b.getImage(), b.getXCoord(), b.getYCoord(), this);
            }

            // Shows the order on the screen, along with the cost and the number
            // of mistakes left
            Font small = new Font("Arial", Font.PLAIN, 18);
            FontMetrics metr = this.getFontMetrics(small);

            g2d.setColor(Color.BLACK);
            g2d.setFont(small);
            g2d.drawString("Lettuce: " + numOfLettuce, 5, 20);
            g2d.drawString("Meat:   " + numOfMeat, 5, 40);
            g2d.drawString("Tomato:  " + numOfTomato, 5, 60);
            g2d.drawString("Onion:   " + numOfOnion, 5, 80);
            g2d.drawString("Pickle:  " + numOfPickle, 5, 100);
            g2d.drawString("Cheese:  " + numOfCheese, 5, 120);
            // Shows the money earned

            g2d.drawString("Cash Earned: " + "$" + cash + "0", 500, 20);
            // Number of errors left
            g2d.drawString("Errors remaining: " + tries, 200, 20);

            if (win) // If game won
            {
                
                String msg = "Burger done! You got $" + cash + "0" + "!";
                Font fontSize = new Font("Arial", Font.PLAIN, 40);
                FontMetrics fontMet = this.getFontMetrics(fontSize);

                g.setColor(Color.BLACK);
                g.setFont(fontSize);
                g.drawString(msg, (B_WIDTH - fontMet.stringWidth(msg)) / 2, B_HEIGHT / 2);
                gameInProgress = false;
            }

            else if (topbunB && !win) // If top bun collected but order not finished
            {
                String msg = "Incomplete";
                Font fontSize1 = new Font("Arial", Font.PLAIN, 80);
                FontMetrics fo = this.getFontMetrics(fontSize1);

                g.setColor(Color.BLACK);
                g.setFont(fontSize1);
                g.drawString(msg, (B_WIDTH - fo.stringWidth(msg)) / 2, B_HEIGHT / 2);
                gameInProgress = false;
            }
            else if(tries <= 0){
                String msg = "Game Over";
                Font font2 = new Font("Arial", Font.PLAIN, 65);
                FontMetrics fontmetric = this.getFontMetrics(font2);

                g.setColor(Color.BLACK);
                g.setFont(font2);
                g.drawString(msg, (B_WIDTH - fontmetric.stringWidth(msg)) / 2, B_HEIGHT / 2);
                gameInProgress= false;
            }
        } else {
            String msg = "Game Over";
            Font font2 = new Font("Arial", Font.PLAIN, 65);
            FontMetrics fontmetric = this.getFontMetrics(font2);

            g.setColor(Color.BLACK);
            g.setFont(font2);
            g.drawString(msg, (B_WIDTH - fontmetric.stringWidth(msg)) / 2, B_HEIGHT / 2);
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
        for (int i = 0; i < lettuceAL.size(); i++) {
            Lettuce lettu = (Lettuce) lettuceAL.get(i);
            if (!lettu.hasBeenStacked())
                lettu.continueFall();
            else
                lettu.move(bun.getX());

            Meat mea = (Meat) meatsAL.get(i);
            if (!mea.hasBeenStacked())
                mea.continueFall();
            else
                mea.move(bun.getX());

            Tomato tomat = (Tomato) tomatoesAL.get(i);
            if (!tomat.hasBeenStacked())
                tomat.continueFall();
            else
                tomat.move(bun.getX());

            Onion onio = (Onion) onionsAL.get(i);
            if (!onio.hasBeenStacked())
                onio.continueFall();
            else
                onio.move(bun.getX());

            Pickle pickl = (Pickle) picklesAL.get(i);
            if (!pickl.hasBeenStacked())
                pickl.continueFall();
            else
                pickl.move(bun.getX());

            Cheese chees = (Cheese) cheesesAL.get(i);
            if (!chees.hasBeenStacked())
                chees.continueFall();
            else
                chees.move(bun.getX());

            TopBun topBu = (TopBun) topBunsAL.get(i);

            if (!topBu.hasBeenStacked())
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

        Lettuce firstLettuce = (Lettuce) lettuceAL.get(0);
        Rectangle rFirstLettuce = firstLettuce.getBounds();
        Lettuce secondLettuce = (Lettuce) lettuceAL.get(1);
        Rectangle rSecondLettuce = secondLettuce.getBounds();
        Lettuce thirdLettuce = (Lettuce) lettuceAL.get(2);
        Rectangle rThirdLettuce = thirdLettuce.getBounds();

        Meat firstMeat = (Meat) meatsAL.get(0);
        Rectangle rfirstMeat = firstMeat.getBounds();
        Meat secondMeat = (Meat) meatsAL.get(1);
        Rectangle rsecondMeat = secondMeat.getBounds();
        Meat thirdMeat = (Meat) meatsAL.get(2);
        Rectangle rthirdMeat = thirdMeat.getBounds();

        Tomato firstTomato = (Tomato) tomatoesAL.get(0);
        Rectangle rfirstTomato = firstTomato.getBounds();
        Tomato secondTomato = (Tomato) tomatoesAL.get(1);
        Rectangle rsecondTomato = secondTomato.getBounds();
        Tomato thirdTomato = (Tomato) tomatoesAL.get(2);
        Rectangle rthirdTomato = thirdTomato.getBounds();

        Onion firstOnion = (Onion) onionsAL.get(0);
        Rectangle rfirstOnion = firstOnion.getBounds();
        Onion secondOnion = (Onion) onionsAL.get(1);
        Rectangle rsecondOnion = secondOnion.getBounds();
        Onion thirdOnion = (Onion) onionsAL.get(2);
        Rectangle rthirdOnion = thirdOnion.getBounds();

        Pickle firstPickle = (Pickle) picklesAL.get(0);
        Rectangle rfirstPickle = firstPickle.getBounds();
        Pickle secondPickle = (Pickle) picklesAL.get(1);
        Rectangle rsecondPickle = secondPickle.getBounds();
        Pickle thirdPickle = (Pickle) picklesAL.get(2);
        Rectangle rthirdPickle = thirdPickle.getBounds();

        Cheese firstCheese = (Cheese) cheesesAL.get(0);
        Rectangle rfirstCheese = firstCheese.getBounds();
        Cheese secondCheese = (Cheese) cheesesAL.get(1);
        Rectangle rsecondCheese = secondCheese.getBounds();
        Cheese thirdCheese = (Cheese) cheesesAL.get(2);
        Rectangle rthirdCheese = thirdCheese.getBounds();

        TopBun firstTopBun = (TopBun) topBunsAL.get(0);
        Rectangle rfirstTopBun = firstTopBun.getBounds();
        TopBun secondTopBun = (TopBun) topBunsAL.get(1);
        Rectangle rsecondTopBun = secondTopBun.getBounds();
        TopBun thirdTopBun = (TopBun) topBunsAL.get(2);
        Rectangle rthirdTopBun = thirdTopBun.getBounds();

        // the rectangular border of bottom bun collides with a falling
        // ingredient
        if (border.intersects(rFirstLettuce) || border.intersects(rSecondLettuce) || border.intersects(rThirdLettuce) ||

                border.intersects(rfirstMeat) || border.intersects(rsecondMeat) || border.intersects(rthirdMeat) || border.intersects(rfirstTomato)
                || border.intersects(rsecondTomato) || border.intersects(rthirdTomato) || border.intersects(rfirstOnion) || border.intersects(rsecondOnion)
                || border.intersects(rthirdOnion) ||

                border.intersects(rfirstPickle) || border.intersects(rsecondPickle) || border.intersects(rthirdPickle) || border.intersects(rfirstCheese)
                || border.intersects(rsecondCheese) || border.intersects(rthirdCheese) ||

                border.intersects(rfirstTopBun) || border.intersects(rsecondTopBun) || border.intersects(rthirdTopBun)) {
            // Records that the first ingredient is stacked
            recordStack = true;

            // intersects but ingredient not stacked
            if (border.intersects(rFirstLettuce) && !firstLettuce.hasBeenStacked()) {
                border = firstLettuce.getBounds();
                stackN++;
                firstLettuce.stopFall();
                firstLettuce.stack(bun, stackN); // Add ingredient to the burger

                // Tries left, number of lettuces in the order remaining
                if (numOfLettuce == 0)
                    tries--;
                if (numOfLettuce > 0) {
                    numOfLettuce--;
                    cash++;
                }
            } else if (border.intersects(rSecondLettuce) && !secondLettuce.hasBeenStacked()) {
                border = secondLettuce.getBounds();
                stackN++;
                secondLettuce.stopFall();
                secondLettuce.stack(bun, stackN);

                if (numOfLettuce == 0)
                    tries--;
                if (numOfLettuce > 0) {
                    numOfLettuce--;
                    cash++;
                }
            } else if (border.intersects(rThirdLettuce) && !thirdLettuce.hasBeenStacked()) {
                border = thirdLettuce.getBounds();
                stackN++;
                thirdLettuce.stopFall();
                thirdLettuce.stack(bun, stackN);

                if (numOfLettuce == 0)
                    tries--;
                if (numOfLettuce > 0) {
                    numOfLettuce--;
                    cash++;
                }

            }

            else if (border.intersects(rfirstMeat) && !firstMeat.hasBeenStacked()) {
                border = firstMeat.getBounds();
                stackN++;
                firstMeat.stopFall();
                firstMeat.stack(bun, stackN);

                if (numOfMeat == 0)
                    tries--;
                if (numOfMeat > 0) {
                    numOfMeat--;
                    cash++;
                }
            } else if (border.intersects(rsecondMeat) && !secondMeat.hasBeenStacked()) {
                border = secondMeat.getBounds();
                stackN++;
                secondMeat.stopFall();
                secondMeat.stack(bun, stackN);

                if (numOfMeat == 0)
                    tries--;
                if (numOfMeat > 0) {
                    numOfMeat--;
                    cash++;
                }
            } else if (border.intersects(rthirdMeat) && !thirdMeat.hasBeenStacked()) {
                border = thirdMeat.getBounds();
                stackN++;
                thirdMeat.stopFall();
                thirdMeat.stack(bun, stackN);

                if (numOfMeat == 0)
                    tries--;
                if (numOfMeat > 0) {
                    numOfMeat--;
                    cash++;
                }
            } else if (border.intersects(rfirstTomato) && !firstTomato.hasBeenStacked()) {
                border = firstTomato.getBounds();
                stackN++;
                firstTomato.stopFall();
                firstTomato.stack(bun, stackN);

                if (numOfTomato == 0)
                    tries--;
                if (numOfTomato > 0) {
                    numOfTomato--;
                    cash++;
                }
            } else if (border.intersects(rsecondTomato) && !secondTomato.hasBeenStacked()) {
                border = secondTomato.getBounds();
                stackN++;
                secondTomato.stopFall();
                secondTomato.stack(bun, stackN);

                if (numOfTomato == 0)
                    tries--;
                if (numOfTomato > 0) {
                    numOfTomato--;
                    cash++;
                }
            } else if (border.intersects(rthirdTomato) && !thirdTomato.hasBeenStacked()) {
                border = thirdTomato.getBounds();
                stackN++;
                thirdTomato.stopFall();
                thirdTomato.stack(bun, stackN);

                if (numOfTomato == 0)
                    tries--;
                if (numOfTomato > 0) {
                    numOfTomato--;
                    cash++;
                }

            }

            else if (border.intersects(rfirstOnion) && !firstOnion.hasBeenStacked()) {
                border = firstOnion.getBounds();
                stackN++;
                firstOnion.stopFall();
                firstOnion.stack(bun, stackN);

                if (numOfOnion == 0)
                    tries--;
                if (numOfOnion > 0) {
                    numOfOnion--;
                    cash++;
                }
            } else if (border.intersects(rsecondOnion) && !secondOnion.hasBeenStacked()) {
                border = secondOnion.getBounds();
                stackN++;
                secondOnion.stopFall();
                secondOnion.stack(bun, stackN);

                if (numOfOnion == 0)
                    tries--;
                if (numOfOnion > 0) {
                    numOfOnion--;
                    cash++;
                }
            } else if (border.intersects(rthirdOnion) && !thirdOnion.hasBeenStacked()) {
                border = thirdOnion.getBounds();
                stackN++;
                thirdOnion.stopFall();
                thirdOnion.stack(bun, stackN);

                if (numOfOnion == 0)
                    tries--;
                if (numOfOnion > 0) {
                    numOfOnion--;
                    cash++;
                }

            }

            else if (border.intersects(rfirstPickle) && !firstPickle.hasBeenStacked()) {
            	bun.lessenSpeed();
                border = firstPickle.getBounds();
                stackN++;
                firstPickle.stopFall();
                firstPickle.stack(bun, stackN);

                if (numOfPickle == 0)
                    tries--;
                if (numOfPickle > 0) {
                    numOfPickle--;
                    cash++;
                }
            } else if (border.intersects(rsecondPickle) && !secondPickle.hasBeenStacked()) {
            	bun.lessenSpeed();
                border = secondPickle.getBounds();
                stackN++;
                secondPickle.stopFall();
                secondPickle.stack(bun, stackN);

                if (numOfPickle == 0)
                    tries--;
                if (numOfPickle > 0) {
                    numOfPickle--;
                    cash++;
                }
            } else if (border.intersects(rthirdPickle) && !thirdPickle.hasBeenStacked()) {
            	bun.lessenSpeed();
                border = thirdPickle.getBounds();
                stackN++;
                thirdPickle.stopFall();
                thirdPickle.stack(bun, stackN);

                if (numOfPickle == 0)
                    tries--;
                if (numOfPickle > 0) {
                    numOfPickle--;
                    cash++;
                }

            }

            else if (border.intersects(rfirstCheese) && !firstCheese.hasBeenStacked()) {
                border = firstCheese.getBounds();
                stackN++;
                firstCheese.stopFall();
                firstCheese.stack(bun, stackN);

                if (numOfCheese == 0)
                    tries--;
                if (numOfCheese > 0) {
                    numOfCheese--;
                    cash++;
                }
            } else if (border.intersects(rsecondCheese) && !secondCheese.hasBeenStacked()) {
                border = secondCheese.getBounds();
                stackN++;
                secondCheese.stopFall();
                secondCheese.stack(bun, stackN);

                if (numOfCheese == 0)
                    tries--;
                if (numOfCheese > 0) {
                    numOfCheese--;
                    cash++;
                }
            } else if (border.intersects(rthirdCheese) && !thirdCheese.hasBeenStacked()) {
                border = thirdCheese.getBounds();
                stackN++;
                thirdCheese.stopFall();
                thirdCheese.stack(bun, stackN);

                if (numOfCheese == 0)
                    tries--;
                if (numOfCheese > 0) {
                    numOfCheese--;
                    cash++;
                }

            }

            else if (border.intersects(rfirstTopBun) && !firstTopBun.hasBeenStacked()) {
                border = (null);
                stackN++;
                firstTopBun.stopFall();
                firstTopBun.stack(bun, stackN);
                topbunB = true;

                if (numOfLettuce == 0 && numOfMeat == 0 && numOfTomato == 0 && numOfOnion == 0 && numOfPickle == 0 && numOfCheese == 0 && tries != 0) {
                    win = true;
                    
                }
            } else if (border.intersects(rsecondTopBun) && !secondTopBun.hasBeenStacked()) {
                border = (null);
                stackN++;
                secondTopBun.stopFall();
                secondTopBun.stack(bun, stackN);
                topbunB = true;

                if (numOfLettuce == 0 && numOfMeat == 0 && numOfTomato == 0 && numOfOnion == 0 && numOfPickle == 0 && numOfCheese == 0 && tries != 0) {
                    win = true;
                }
            } else if (border.intersects(rthirdTopBun) && !thirdTopBun.hasBeenStacked()) {
                border = (null);
                stackN++;
                thirdTopBun.stopFall();
                thirdTopBun.stack(bun, stackN);
                topbunB = true;

                if (numOfLettuce == 0 && numOfMeat == 0 && numOfTomato == 0 && numOfOnion == 0 && numOfPickle == 0 && numOfCheese == 0 && tries != 0) {
                    win = true;
                }

            }
        }

        if (topbunB) // If top bun has been collected
        {
            if (!firstLettuce.hasBeenStacked()) {

                firstLettuce.stopFall();
            }
            if (!secondLettuce.hasBeenStacked()) {

                secondLettuce.stopFall();
            }
            if (!thirdLettuce.hasBeenStacked()) {

                thirdLettuce.stopFall();
            }

            if (!firstMeat.hasBeenStacked()) {

                firstMeat.stopFall();
            }
            if (!secondMeat.hasBeenStacked()) {

                secondMeat.stopFall();
            }
            if (!thirdMeat.hasBeenStacked()) {

                thirdMeat.stopFall();
            }

            if (!firstTomato.hasBeenStacked()) {

                firstTomato.stopFall();
            }
            if (!secondTomato.hasBeenStacked()) {

                secondTomato.stopFall();
            }
            if (!thirdTomato.hasBeenStacked()) {

                thirdTomato.stopFall();
            }

            if (!firstOnion.hasBeenStacked()) {

                firstOnion.stopFall();
            }
            if (!secondOnion.hasBeenStacked()) {

                secondOnion.stopFall();
            }
            if (!thirdOnion.hasBeenStacked()) {

                thirdOnion.stopFall();
            }

            if (!firstPickle.hasBeenStacked()) {

                firstPickle.stopFall();
            }
            if (!secondPickle.hasBeenStacked()) {

                secondPickle.stopFall();
            }
            if (!thirdPickle.hasBeenStacked()) {

                thirdPickle.stopFall();
            }

            if (!firstCheese.hasBeenStacked()) {

                firstCheese.stopFall();
            }
            if (!secondCheese.hasBeenStacked()) {

                secondCheese.stopFall();
            }
            if (!thirdCheese.hasBeenStacked()) {

                thirdCheese.stopFall();
            }

            if (!secondTopBun.hasBeenStacked()) {

                secondTopBun.stopFall();
            }
            if (!thirdTopBun.hasBeenStacked()) {

                thirdTopBun.stopFall();
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