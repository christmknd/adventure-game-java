import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class CommandWords
{
    // A mapping between a command word and the CommandWord
    // associated with it.
    private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        validCommands.put("go", CommandWord.GO);
        validCommands.put("help", CommandWord.HELP);
        validCommands.put("quit", CommandWord.QUIT);
    }

    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll() 
    {
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }

        public void interpretCommand(String commandLine)
    {
    Command command = parser.getCommand(commandLine);
    //gui.println(commandLine);
    CommandWord commandWord = command.getCommandWord();
    if(commandWord == CommandWord.UNKNOWN) {
    gui.println("I don't know what you mean...");
    return;
    }

        //String commandWord = command.getCommandWord();
    if (commandWord == CommandWord.HELP)
    printHelp();
    else if (commandWord == CommandWord.GO)
    goRoom(command);
    else if (commandWord == CommandWord.LOOK)
    look();
    else if(commandWord == CommandWord.EAT)
    eat();
    else if(commandWord == CommandWord.BACK) {
    if(command.hasSecondWord())
    gui.println("Back what ?");
    else
    back();
    }

    else if(commandWord == CommandWord.TEST) {
    if(!command.hasSecondWord())
    gui.println("test what ?");
    else
    test(command);
    }

    else if(commandWord == CommandWord.TAKE) {
    if(!command.hasSecondWord())
    gui.println("take what ?");
    else
    take(command);
    }


    else if(commandWord == CommandWord.DROP) {
    if(!command.hasSecondWord())
    gui.println("drop what ?");
    else
    drop(command);
    }

    else if(commandWord == CommandWord.ITEMS) {
    if(command.hasSecondWord())
    gui.println("print what ?");
    else
    printItemsList();
    }

    else if (commandWord == CommandWord.QUIT) {
    if(command.hasSecondWord())
    gui.println("Quit what?");
    else
    endGame();
    }
    }
        public void interpretCommand(String commandLine)
    {
    Command command = parser.getCommand(commandLine);
    //gui.println(commandLine);
    CommandWord commandWord = command.getCommandWord();
    switch(commandWord) {
    case UNKNOWN:
    gui.println("I don't know what you mean...");
    return;
    case HELP:
    printHelp();
    break;
    case GO:
    goRoom(command);
    break;

    case LOOK:
    look();
    break;
    case EAT:
    eat();
    break;
    case BACK:
    if(command.hasSecondWord())
    gui.println("Back what ?");
    else
    back();
    break;
    case TEST:
    if(!command.hasSecondWord())
    gui.println("test what ?");
    else
    test(command);
    break;
    case TAKE:
    if(!command.hasSecondWord())
    gui.println("take what ?");
    else
    take(command);
    break;
    case DROP:
    if(!command.hasSecondWord())
    gui.println("drop what ?");
    else
    drop(command);
    break;
    case ITEMS:
    if(command.hasSecondWord())
    gui.println("print what ?");
    else
    printItemsList();
    break;
    case QUIT:
    if(command.hasSecondWord())
    gui.println("quit what ?");
    else
    endGame();
    }
    }


        public CommandWords()
    {
    validCommands = new HashMap<String, CommandWord>();
    validCommands.put("god", CommandWord.GO);
    validCommands.put("helpd", CommandWord.HELP);
    validCommands.put("quitd", CommandWord.QUIT);
    validCommands.put("lookd", CommandWord.LOOK);
    validCommands.put("eatd", CommandWord.EAT);
    validCommands.put("backd", CommandWord.BACK);
    validCommands.put("testd", CommandWord.TEST);
    validCommands.put("taked", CommandWord.TAKE);
    validCommands.put("dropd", CommandWord.DROP);
    validCommands.put("itemsd", CommandWord.ITEMS);
    }

        public enum Position
    {
    TOP,MIDDLE,BOTTOM
    }

    GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), UNKNOWN("?");






}
