/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a CommandWord and a string
 * (for example, if the command was "take map", then the two parts
 * are TAKE and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
import java.util.HashMap;
public class Command
{
    private CommandWord commandWord;
    private String secondWord;

    /**
     * Create a command object. First and second words must be supplied, but
     * the second may be null.
     * @param commandWord The CommandWord. UNKNOWN if the command word
     *                  was not recognised.
     * @param secondWord The second word of the command. May be null.
     */
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    /**
     * Return the command word (the first word) of this command.
     * @return The command word.
     */
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }


        private void test(Command command) {
    String f = command.getSecondWord();
    FileReader ff;
    BufferedReader br;
    try {
    ff = new FileReader(f);
    br = new BufferedReader(ff);
    String line;
    while ((line = br.readLine()) != null) {
    interpretCommand(line);
    }
    br.close();
    }
    catch(FileNotFoundException e) {
    gui.println("File not found");
    }
    catch(IOException e) {
    e.printStackTrace();
    }

    }

    public class Player {
    private Room currentRoom;
    private HashMap<Item,String> items;
    public Player() {
    currentRoom = null;
    items = new HashMap<Item, String>();
    }

    /**
    * @return the current room
    */

    public Room getCurrentRoom() {
    return currentRoom;
    }


    /**
    * Set the new current room.
    * @param room the new current room.
    */


    public void setCurrentRoom(Room room) {
    this.currentRoom = room;
    }


    /**
    * @return the item list.
    */
    public HashMap<Item,String> getItemsList() {
    return items;
    }


    /**
    *take an item
    * @param item the item to take.
    */

    public void takeItem(Item item) {
    items.put(item,"none");
    }

    /**
    * drop an item.
    */

    public void dropItem(Item item) {
    items.remove(item);
    }

    /**
    * Print the items of the player.
    */

    public String getItemsInfo() {
    if(items.isEmpty())
    return "";
    StringBuilder returnString = new StringBuilder( "Player's Items :\n" );
    for(Item it : items.keySet())
    returnString.append(" "+ it.getDescription()+"\n");
    return returnString.toString();
    }
    }



        public void interpretCommand(String commandLine)
    {
    gui.println(commandLine);
    Command command = parser.getCommand(commandLine);
    if(command.isUnknown()) {
    gui.println("I don't know what you mean...");
    return;
    }

    String commandWord = command.getCommandWord();
    if (commandWord.equals("help"))
    printHelp();
    else if (commandWord.equals("go"))
    goRoom(command);
    else if (commandWord.equals("look"))
    look();
    else if(commandWord.equals("eat"))
    eat();
    else if(commandWord.equals("back")) {
    if(command.hasSecondWord())
    gui.println("Back what ?");
    else
    back();
    }

    else if(commandWord.equals("test")) {
    if(!command.hasSecondWord())
    gui.println("test what ?");
    else
    test(command);
    }

    else if(commandWord.equals("take")) {
    if(!command.hasSecondWord())
    gui.println("take what ?");
    else
    take(command);
    }

    else if(commandWord.equals("drop")) {
    if(!command.hasSecondWord())
    gui.println("drop what ?");
    else
    drop(command);
    }

    else if (commandWord.equals("quit")) {
    if(command.hasSecondWord())
    gui.println("Quit what?");
    else
    endGame();
    }
        else if(commandWord.equals("items")) {
    if(command.hasSecondWord())
    gui.println("print what ?");
    else
    printItemsList();
        }

    }


}

