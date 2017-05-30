package src;
import java.util.Scanner;

import javax.swing.*;

/**
 *  The AirBurger class launches the entire game.
 *  
 *  The constructor generates the JFrame, which is the window that will pop up.
 *  It adds an Order object to the frame to create the current "round"
 *  
 *  The main class calls methods of FileReaderAndWriter to login the player
 *  and update their score after they exit the game. 
 *
 *  @author  Vivian Cheng, Rohit Kulkarni, Isa Dash
 *  @version May 30, 2017
 *  @author  Period: 3
 *  @author  Assignment: AirBurger-master 3
 *
 *  @author  Sources: none
 */
public class AirBurger extends JFrame
{
    static double cashEarned;
    static Order o;
    /**
     * constructor 
     * @throws Exception 
     */
    public AirBurger() throws Exception
    {
        o = new Order();
        this.getContentPane().add(o);
        

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("AirBurger");
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * main method. this prompts the player for their username and launches game.
     * @param args default
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        System.out.println( "Username:" );
        Scanner scan = new Scanner( System.in );
        String username = scan.nextLine();
        FileReaderAndWriter frw = new FileReaderAndWriter(username);

        frw.read("Users.txt" );
        frw.login();
        try
        {
            new AirBurger();
            //cashEarned = o.cash;
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        while(o.gameInProgress){
            System.out.print("");
            
        }
        //System.out.println("here");
        frw.changeScore( o.cash );

        frw.write("Users.txt");
        
        
        
    }
}