/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

import java.util.HashMap;
import java.util.Set;
public class ItemList {
private HashMap<Item,String> items;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }


    private void printItemsList() {
    String it = player.getItemsInfo();
    gui.println(it);
    }

    /**
    *
    * @param command the command with item's name in it
    */
    private void take(Command command) {

    String i = command.getSecondWord();
    HashMap<Item,String> items = player.getCurrentRoom().getItemsList();
    for(Item it : items.keySet()) {
    if(i.equals(it.getName())) {//Si l'item qu'on veut prendre est bien dans la pièce
    player.takeItem(it);

    player.getCurrentRoom().removeItem(it);
    printItemsList();
    return;
    }

    }
    gui.println("No item with this name");
    }

    /**
    * Drop the item of the player
    */
    private void drop(Command command) {
    String i = command.getSecondWord();
    HashMap<Item, String> tmp = player.getItemsList();
    for(Item it : tmp.keySet()) {
    if(i.equals(it.getName())) {
    player.getCurrentRoom().addItem(it);
    player.dropItem(it);
    return;
    }

    }
    gui.println("No item with this name");
    }





        /**
    * Constructor.
    */

    public ItemList() {
    items = new HashMap<Item, String>();
    }

    /**
    * Get all the items.
    * @return the items.
    */

    public HashMap<Item,String> getItemList() {
    return items;
    }

    /**
    * Push an item into the HashMap.
    * @param item the item to push.
    */

    public void pushItem(Item item) {
    items.put(item,"none");
    }

    /**
    * delete an item
    * @param item the item to remove
    */

    public void removeItem(Item item) {
    items.remove(item);
    }

    /**
    * @return true if the HashMap is empty, false eitherway.
    */

    public boolean emptyDumpty() {
    return items.isEmpty();
    }
    }

        public boolean takeItem(Item item) {
    int tmp = currentPoid + item.getWeight();
    if(tmp <= poidsMax) {
    items.put(item,"none");
    currentPoid += item.getWeight();
    return true;
    } else
    return false;
    }

        private void take(Command command) {
    String i = command.getSecondWord();
    HashMap<Item,String> items = player.getCurrentRoom().getItemsList();
    for(Item it : items.keySet()) {
    if(i.equals(it.getName())) {//Si l'item qu'on veut prendre est bien dans la pièce
    boolean tmp = player.takeItem(it);
    if(tmp == true)
    player.getCurrentRoom().removeItem(it);
    else
    gui.println("Too heavy");
    return;
    }
    }



    gui.println("No item with this name");
    }
}

public class Door {
boolean locked;
String toUnlock;//the item's name to unlock.
/**
* set the door's lock.
* @param b the boolean if locked.
*/

public Door(String toUnlock) {
this.locked = true;
this.toUnlock = toUnlock;
}

/**
* verify if the door is locked.
* @return locked.
*/
public boolean ifLocked() {
return locked;
}

/**
* get the key.
* @return toUnlock the key's name.
*/
public String getKey() {
return toUnlock;
}
/**
* set the door's lock
* @param b the lock's setting.
*/

public void setLock(boolean b) {
locked = b;
}

public void setDoors(String direction, Door door)
{
doors.put(direction,door);
}

/**
* get the door in the given direction
* @param direction the direction.
*/

public Door getDoor(String direction) {
return doors.get(direction);
}

public Beamer(String description, int poid)
{
super(description,poid);
this.loaded = false;
this.loadedRoom = null;
}

/**
* load the beamer.
*/
public void load(Room room) {
loaded = true;
loadedRoom = room;
}

/**
* fire : teleport to the loadedRoom.
*/

public Room fire() {
loaded = false;
Room tmp = loadedRoom;
loadedRoom = null;
return tmp;
}
/**

* verify if the beamer is loaded
* @return loaded the boolean.
*/
public boolean ifLoaded() {
return loaded;
}
}


public class Beamer extends Item {
private boolean loaded;
private Room loadedRoom;

/**
* Create a beamer described "description".
* @param description The beamer's description.
* @param poid The weight of the beamer.
*/

public Beamer(String description, int poid)
{
super(description,poid);
this.loaded = false;
this.loadedRoom = null;
}

/**
* load the beamer.
*/
public void load(Room room) {
loaded = true;
loadedRoom = room;
}

/**
* fire : teleport to the loadedRoom.
*/

public Room fire() {
loaded = false;
Room tmp = loadedRoom;
loadedRoom = null;
return tmp;
}
* verify if the beamer is loaded
* @return loaded the boolean.
*/
public boolean ifLoaded() {
return loaded;
}
}
public void load() {
String b = "beamer";
HashMap<Item, String> tmp = player.getItemsList();
for(Item it : tmp.keySet()) {
if(b.equals(it.getName())) {
Beamer tmp2 = new Beamer(it.getName(),it.getWeight());
tmp2.load(player.getCurrentRoom());
player.dropItem(it);
player.takeItem(tmp2);
return;
}
}

gui.println("You don't have a beamer");
}
/**
* fire the beamer if the player have it
* and the beamer is loaded.
*/
public void fire() {
String b = "beamer";
HashMap<Item, String> tmp = player.getItemsList();
for(Item it : tmp.keySet()) {
if(b.equals(it.getName())) {
Beamer tmp2 = (Beamer)it;
if(tmp2.ifLoaded()) {
Room r = tmp2.fire();
setPlayer(r);
printLocationInfo();
if(player.getCurrentRoom().getImageName() != null)
gui.showImage(player.getCurrentRoom().getImageName());
player.dropItem(it);
player.takeItem(tmp2);
}
else
gui.println("Load it if you want to fire it.");
return;
}
}

gui.println("You don't have a beamer");
}


