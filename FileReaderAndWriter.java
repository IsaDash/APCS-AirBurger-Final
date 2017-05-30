package src;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileReader.*;
import java.lang.reflect.Field;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument.Iterator;


/**
 *  The FileReaderAndWriter class is the 
 *  class that stores the user information
 *  and scores. 
 *  
 *  It allows the player to enter a username,
 *  old or new, and adds that player to the
 *  "scoreboard," which is a txt file 
 *  consisting of the users and the amount
 *  of money they made in total. The class 
 *  uses 2 data structures: TreeMap<Double, LinkedList>
 *  and LinkedList<String>. The key of the map
 *  is the score/amount of money and the list is 
 *  a list of names of all players with that score.
 *  
 *
 *  @author  Vivian Cheng, Rohit Kulkarni, Isa Dash
 *  @version May 29, 2017
 *  @author  Period: 3
 *  @author  Assignment: AirBurger-master 3
 *
 *  @author  Sources: none
 */
public class FileReaderAndWriter extends JFrame
{
    private static String username;
    private Double oldScore;

    private Map<Double, LinkedList<String>> users = new TreeMap<Double, LinkedList<String>>();

    static AirBurger a;
    
    
    /**
     * constructor; sets the private field username to the parameter, 
     * which is whatever name the player typed into console.
     * 
     * @param username the name the player types in the console
     */
    public FileReaderAndWriter(String username){
        this.username = username;
    }



    /**
     * the changeScore method updates the score of the
     * player after they played the game. It removes the 
     * player's name from the previous score "bucket" and
     * puts a new, updated key-value pair in the TreeMap.
     * 
     * @param cash the amount of cash the player earned 
     * in the last round
     */
    public void changeScore( double cash )
    {
        
                    
        users.get( oldScore ).remove( username );

        putInMap( oldScore + cash , username );

    }


    /**
     * the putInMap adds username s to an already existing linkedlist
     * corresponding to the same key of score d OR puts a new key-value 
     * pair with score d and username s in the TreeMap users.
     * @param d the score/money the user currently has
     * @param s the name of the user
     */
    public void putInMap( double d, String s )
    {
        if ( getUsers().containsKey( d ) )
        {
            getUsers().get( d ).add( s );
        }
        else
        {
            LinkedList<String> newList = new LinkedList<String>();
            newList.add( s );
            getUsers().put( d, newList );
        }

    }


    /**
     * Interprets the text file by splitting the string into 
     * respective scores and usernames. Also adds score and username
     * to the TreeMap by calling the putInMap method.
     * @param fileName name of the txt file that needs to be read
     */
    public void read( String fileName )
    {

        try
        {
            File file = new File( fileName );
            Scanner scan = new Scanner( file );

            String next;

            while ( scan.hasNextLine() )
            {

                next = scan.nextLine();
                if ( next.contains( ", " ) )
                {

                    String[] splitUser = next.split( ", " );
                    putInMap( Double.parseDouble( splitUser[0] ), splitUser[1] );

                }

            }

        }
        catch ( Exception e )
        {
            System.out.println( e.getMessage() );
            System.exit( 1 );

        }
    }


    /**
     * updates the text file by writing all the updated 
     * scores and users from the TreeMap
     * @param textFile name of file to write text in
     */
    public void write(String textFile)
    {
        try
        {
            FileWriter fw = new FileWriter(textFile);
            BufferedWriter bw = new BufferedWriter( fw );
            fw.write( "" );

            for ( Double i : getUsers().keySet() )
            {

                for ( String name : getUsers().get( i ) )
                {
                    bw.write( i + "0" + ", " + name );
                    bw.newLine();
                }

            }
            if ( bw != null )
                bw.close();
        }
        catch ( Exception e )
        {
            System.out.println( e.getMessage() );
            System.exit( 1 );

        }

    }


    /**
     * login checks if the username the player chooses is already taken.
     * if not, new user is created in the map. If it exists, that player
     * is "logged in" and can add to their previous score.
     * @throws IOException exception
     */
    public void login() throws IOException
    {
        boolean exists = false;

        for ( Double i : getUsers().keySet() )
        {

            for ( String name : getUsers().get( i ) )
            {
                if ( name.equals( username ) )
                {
                    exists = true;
                    oldScore = i;

                }
            }

        }
        if ( !exists )
        {
            oldScore = 0.0;
            putInMap( 0, username );

        }

    }



    /**
     * getter method for testing purposes
     * @return users TreeMap of users
     */
    public Map<Double, LinkedList<String>> getUsers()
    {
        return users;
    }



    /**
     * setter method for testing purposes
     * @param users TreeMap of users
     */
    public void setUsers( Map<Double, LinkedList<String>> users )
    {
        this.users = users;
    }
    
    /** 
     * toString method returns string representation of FileReaderAndWriter
     */
    public String toString()
    {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for ( Field field : fields )
        {
            try
            {
                str += separator + field.getType().getName() + " "
                    + field.getName() + ":" + field.get( this );
            }
            catch ( IllegalAccessException ex )
            {
                System.out.println( ex );
            }

            separator = ", ";
        }

        return str + "]";
    }

}
