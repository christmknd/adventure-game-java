import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.Math;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}


public class TransporterRoom extends Room {
/**
* Create a room described "description". Initially, it has
* no exits. "description" is something like "a kitchen" or
* "an open court yard".
* @param description The room's description.
* @param image The image of the room.
*/

public TransporterRoom(String description, String image)
{
super(description,image);
}

/**
* Surcharge of the constructor.
* @param description The room's description.
* @param image The image of the room.
* @param
*
*/

public TransporterRoom(String description, String image, Item item)
{
super(description,image,item);
}
public Room teleport() {
Room nextRoom = null;
int nb = 0;
String direction="";
while(nextRoom == null) {
nb = (int) (Math.random()*7);
switch(nb) {
case 0:
direction = "north";
break;
case 1:
direction = "south";
break;
case 2:
direction = "east";
break;
case 3:
direction = "west";
break;
case 4:
direction = "up";
break;
case 5:
direction = "down";
break;
}
nextRoom = super.getExit(direction);
}
return nextRoom;
}
}


