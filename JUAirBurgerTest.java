package src;
import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.Test;
import java.awt.Image;
import java.awt.Rectangle;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.Test;

public class JUAirBurgerTest
{
   //Tests for Lettuce class methods******************************************
    
    /**
     * Test getRandomY method of Lettuce class
     */
    @Test
    public void testLetRandomY()
    {
        Lettuce let = new Lettuce(0,0);
        int y = let.getRandomY();
        assertTrue(y < -1 && y>-6001);
    }
    
    /**
     * Test getRandomX method of Lettuce class
     */
    @Test
    public void testLetRandomX()
    {
        Lettuce let = new Lettuce(0,0);
        int x = let.getRandomX();
        assertTrue(x < 700 && x>0);
    }
    
    /**
     * Test getXA method of Lettuce class
     */
    @Test
    public void testLetgetXA()
    {
        Lettuce let = new Lettuce(0,3);
        assertTrue(let.getXA() == 0);
    }
    
    /**
     * Test getYA method of Lettuce class
     */
    @Test
    public void testLetgetYA()
    {
        Lettuce let = new Lettuce(3,0);
        assertTrue(let.getYA() == 0);
                        
    }
    
    /**
     * Test getImage method of Lettuce class
     */
    @Test
    public void testLetgetImage()
    {
        Lettuce let = new Lettuce(3,0);
        ImageIcon ii = new ImageIcon (this.getClass ().getResource ("lettuce.png"));
        Image im = ii.getImage (); 
        assertTrue(let.getImage().equals( im ));
                        
    }
    
    /**
     * Test getImage method of Lettuce class
     */
    @Test
    public void testLetMove()
    {
        Lettuce let = new Lettuce(0,3);
        let.move( 4 );
        assertTrue(let.getXA() == 4);
    }
    
    
    
//Tests for Tomato class methods******************************************
    
    /**
     * Test getRandomY method of Tomato class
     */
    @Test
    public void testTomRandomY()
    {
        Tomato tom = new Tomato(0,0);
        int y = tom.getRandomY();
        assertTrue(y < -1 && y>-6001);
    }
    
    /**
     * Test getRandomX method of Tomato class
     */
    @Test
    public void testTomRandomX()
    {
        Tomato tom = new Tomato(0,0);
        int x = tom.getRandomX();
        assertTrue(x < 700 && x>0);
    }
    
    /**
     * Test getXA method of Tomato class
     */
    @Test
    public void testTomgetXA()
    {
        Tomato tom = new Tomato(0,3);
        assertTrue(tom.getXA() == 0);
    }
    
    /**
     * Test getYA method of Tomato class
     */
    @Test
    public void testTomgetYA()
    {
        Tomato tom = new Tomato(3,0);
        assertTrue(tom.getYA() == 0);
                        
    }
    
    /**
     * Test getImage method of Tomato class
     */
    @Test
    public void testTomgetImage()
    {
        Lettuce let = new Lettuce(3,0);
        ImageIcon ii = new ImageIcon (this.getClass ().getResource ("lettuce.png"));
        Image im = ii.getImage (); 
        assertTrue(let.getImage().equals( im ));
                        
    }
    
    /**
     * Test getImage method of Tomato class
     */
    @Test
    public void testTomMove()
    {
        Tomato tom = new Tomato(0,3);
        tom.move( 4 );
        assertTrue(tom.getXA() == 4);
    }


//Tests for Meat class methods******************************************
    
    /**
     * Test getRandomY method of Meat class
     */
    @Test
    public void testMeatRandomY()
    {
        Meat meat = new Meat(0,0);
        int y = meat.getRandomY();
        assertTrue(y < -1 && y>-6001);
    }
    
    /**
     * Test getRandomX method of Meat class
     */
    @Test
    public void testMeatRandomX()
    {
        Meat meat = new Meat(0,0);
        int x = meat.getRandomX();
        assertTrue(x < 700 && x>0);
    }
    
    /**
     * Test getXA method of Meat class
     */
    @Test
    public void testMeatgetXA()
    {
        Meat meat = new Meat(0,3);
        assertTrue(meat.getXA() == 0);
    }
    
    /**
     * Test getYA method of Meat class
     */
    @Test
    public void testMeatgetYA()
    {
        Meat meat = new Meat(3,0);
        assertTrue(meat.getYA() == 0);
                        
    }
    
    /**
     * Test getImage method of Meat class
     */
    @Test
    public void testMeatgetImage()
    {
        Meat meat = new Meat(3,0);
        ImageIcon ii = new ImageIcon (this.getClass ().getResource ("meat.png"));
        Image im = ii.getImage (); 
        assertTrue(meat.getImage().equals( im ));
                        
    }
    
    /**
     * Test getImage method of Meat class
     */
    @Test
    public void testMeatMove()
    {
        Tomato tom = new Tomato(0,3);
        tom.move( 4 );
        assertTrue(tom.getXA() == 4);
    }
    
    
//Tests for Onion class methods******************************************
    
    /**
     * Test getRandomY method of Onion class
     */
    @Test
    public void testOniRandomY()
    {
        Onion oni = new Onion(0,0);
        int y = oni.getRandomY();
        assertTrue(y < -1 && y>-6001);
    }
    
    /**
     * Test getRandomX method of Onion class
     */
    @Test
    public void testOniRandomX()
    {
        Onion oni = new Onion(0,0);
        int x = oni.getRandomX();
        assertTrue(x < 700 && x>0);
    }
    
    /**
     * Test getXA method of Onion class
     */
    @Test
    public void testOnigetXA()
    {
        Onion oni = new Onion(0,3);
        assertTrue(oni.getXA() == 0);
    }
    
    /**
     * Test getYA method of Onion class
     */
    @Test
    public void testOnigetYA()
    {
        Onion oni = new Onion(3,0);
        assertTrue(oni.getYA() == 0);
                        
    }
    
    /**
     * Test getImage method of Onion class
     */
    @Test
    public void testOnigetImage()
    {
        Onion oni = new Onion(3,0);
        ImageIcon ii = new ImageIcon (this.getClass ().getResource ("onions.png"));
        Image im = ii.getImage (); 
        assertTrue(oni.getImage().equals( im ));
                        
    }
    
    /**
     * Test getImage method of Onion class
     */
    @Test
    public void testOniMove()
    {
        Onion oni = new Onion(0,3);
        oni.move( 4 );
        assertTrue(oni.getXA() == 4);
    }


//Tests for Cheese class methods******************************************
    
    /**
     * Test getRandomY method of Cheese class
     */
    @Test
    public void testChRandomY()
    {
        Cheese tom = new Cheese(0,0);
        int y = tom.getRandomY();
        assertTrue(y < -1 && y>-6001);
    }
    
    /**
     * Test getRandomX method of Cheese class
     */
    @Test
    public void testChRandomX()
    {
        Cheese tom = new Cheese(0,0);
        int x = tom.getRandomX();
        assertTrue(x < 700 && x>0);
    }
    
    /**
     * Test getXA method of Cheese class
     */
    @Test
    public void testChgetXA()
    {
        Cheese tom = new Cheese(0,3);
        assertTrue(tom.getXA() == 0);
    }
    
    /**
     * Test getYA method of Cheese class
     */
    @Test
    public void testChgetYA()
    {
        Cheese tom = new Cheese(3,0);
        assertTrue(tom.getYA() == 0);
                        
    }
    
    
    /**
     * Test getImage method of Cheese class
     */
    @Test
    public void testChMove()
    {
        Tomato tom = new Tomato(0,3);
        tom.move( 4 );
        assertTrue(tom.getXA() == 4);
    }
    
    
//Tests for Pickle class methods******************************************
    
    /**
     * Test getRandomY method of Pickle class
     */
    @Test
    public void testPiRandomY()
    {
        Pickle tom = new Pickle(0,0);
        int y = tom.getRandomY();
        assertTrue(y < -1 && y>-6001);
    }
    
    /**
     * Test getRandomX method of Pickle class
     */
    @Test
    public void testPiRandomX()
    {
        Pickle tom = new Pickle(0,0);
        int x = tom.getRandomX();
        assertTrue(x < 700 && x>0);
    }
    
    /**
     * Test getXA method of Pickle class
     */
    @Test
    public void testPigetXA()
    {
        Pickle tom = new Pickle(0,3);
        assertTrue(tom.getXA() == 0);
    }
    
    /**
     * Test getYA method of Pickle class
     */
    @Test
    public void testPigetYA()
    {
        Pickle tom = new Pickle(3,0);
        assertTrue(tom.getYA() == 0);
                        
    }
    
    /**
     * Test getImage method of Pickle class
     */
    @Test
    public void testPigetImage()
    {
        Pickle let = new Pickle(3,0);
        ImageIcon ii = new ImageIcon (this.getClass ().getResource ("pickles.png"));
        Image im = ii.getImage (); 
        assertTrue(let.getImage().equals( im ));
                        
    }
    
    /**
     * Test getImage method of Pickle class
     */
    @Test
    public void testPiMove()
    {
        Pickle tom = new Pickle(0,3);
        tom.move( 4 );
        assertTrue(tom.getXA() == 4);
    }


    
    //Bun Class Tests **********************************************************
    
    /**
     * Test move method of Bun class
     */
    @Test
    public void testBunMove()
    {
        Bun b = new Bun();
        int x1 = b.getX();
        b.move();
        assertTrue(b.getX() == x1 + b.speedx);
    }
    
    /**
     * Test getImage() method of Bun class
     */
    @Test
    public void testGetImage()
    {
        Bun b = new Bun();
        ImageIcon ii = new ImageIcon (this.getClass ().getResource ("bottomBun.png"));
        Image image = ii.getImage ();
        assertTrue(b.getImage().equals( image ));
    }
    
    /**
     * Test getX method of Lettuce class
     */
    @Test
    public void testgetX()
    {
        Bun b = new Bun();
        assertTrue(b.getX() == 350);
    }
    
    /**
     * Test getY method of Lettuce class
     */
    @Test
    public void testgetY()
    {
        Bun b = new Bun();
        assertTrue(b.getY() == 600);
                        
    }
    
    // Tests for FileReaderAndWriter methods
    String username = "Bob";
    Double cashEarned = 5.0;
    File test = new File("test.txt");
    @Test
    public void fileReaderAndWriterConstructor(){
        
        FileReaderAndWriter frw = new FileReaderAndWriter( username );
        String toStr = frw.toString();
        assertTrue( "<< Invalid FileReaderAndWriter Constructor >>",
                    toStr.contains( "FileReaderAndWriter["));
                       
    }
    @Test
    public void fileReaderAndWriterChangeScore(){
        FileReaderAndWriter frw = new FileReaderAndWriter( username );
        frw.putInMap( 0.0, username );
        try
        {
            frw.login();
        }
        catch ( IOException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        frw.changeScore( 5.0 );
        assertTrue("<<FileReaderAndWriter: username should be removed>>",
           !frw.getUsers().get(0.0).contains( username ) );
        
        assertTrue("<<FileReaderAndWriter: expected updated score to be " + 
        cashEarned + ">>", frw.getUsers().get( 0 + cashEarned ).contains( username ));
        
    }
    @Test
    public void fileReaderAndWriterPutInMap(){
        FileReaderAndWriter frw = new FileReaderAndWriter(username);
        LinkedList<String> names = new LinkedList<String>();
        names.add( "Jill" );
        frw.getUsers().put( 0.0 , names );
        frw.putInMap( 0, username );
        assertTrue("<<FileReaderAndWriter: both usernames should be in 0.0 "
            + "bucket in the Map >>",
            frw.getUsers().get( 0.0 ).contains( username ) && 
            frw.getUsers().get( 0.0 ).contains( "Jill" ));
        frw.putInMap( 1.0, "Kate" );
        assertTrue("<<FileReaderAndWriter: username should be in new 1.0 "
            + "bucket in the Map", frw.getUsers().get( 1.0 ).contains( "Kate" ));
        
        
    }
    @Test
    public void fileReaderAndWriterRead(){
        FileReaderAndWriter frw = new FileReaderAndWriter(username);
        
        frw.read( "test.txt" );
        assertTrue("<<FileReaderAndWriter: map users should contain elements "
            + "from test.txt", frw.getUsers().get( 3.0 ).contains( "Po" ) &&
            frw.getUsers().get( 4.0 ).contains( "helo" ) &&
            frw.getUsers().get( 5.0 ).contains( "hi" ));
        
    }
    @Test
    public void fileReaderAndWriterWrite(){
        FileReaderAndWriter frw = new FileReaderAndWriter(username);
        
        frw.putInMap(cashEarned, username);
        
        frw.write("test1.txt");
        frw.read( "test1.txt" );
        assertTrue("<<FileReaderAndWriter: text file should contain elements "
            + "from Map users", frw.getUsers().get( cashEarned ).contains( username));
        
        
    }
    @Test
    public void fileReaderAndWriterLogin(){
        FileReaderAndWriter frw = new FileReaderAndWriter(username);
        frw.putInMap( 0.0, "Dora" );
        frw.putInMap( 1.0, "Winfred" );
        try
        {
            frw.login();
        }
        catch ( IOException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertTrue("<<FileReaderAndWriter: new user should have been created",
            frw.getUsers().get( 0.0 ).contains( username ));
        
        
    }
    @Test
    public void fileReaderAndWriterToString()

    {
        FileReaderAndWriter frw = new FileReaderAndWriter(username);
        assertNotNull( frw.toString() );

    }
    

}
