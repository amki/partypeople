package a01.b02.partypeople.server;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import a01.b02.partypeople.PartyPeople;

public class CommandPPInv extends CommandBase {
	private PartyPeople partypeople;
	public CommandPPInv(PartyPeople partypeople) {
		super();
		this.partypeople = partypeople;
	}
    public String getCommandName()
    {
        return "ppinv";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
        return "Usage";
    }

    public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
    	par1ICommandSender.sendChatToPlayer("You managed to type. Cheerios!");
    	if(par2ArrayOfStr.length>0) {
    		/*
    		if(par2ArrayOfStr[0].equalsIgnoreCase("on")) {
    			areasave.setActive(true);
    		}
    		if(par2ArrayOfStr[0].equalsIgnoreCase("off")) {
    			areasave.setActive(false);
    		}
    		if(par2ArrayOfStr[0].equalsIgnoreCase("claim")) {
    			if(par2ArrayOfStr.length==2) {
    				System.out.println("Player "+par1ICommandSender.getCommandSenderName()+" at "+par1ICommandSender.getPlayerCoordinates().posX+"/"+par1ICommandSender.getPlayerCoordinates().posY+"/"+par1ICommandSender.getPlayerCoordinates().posZ+" wants to claim!");
    				try {
    					Claim c = new Claim(par1ICommandSender.getCommandSenderName(), par1ICommandSender.getPlayerCoordinates().posX,par1ICommandSender.getPlayerCoordinates().posZ, Integer.parseInt(par2ArrayOfStr[1]));
    					areasave.claims.add(c);
    				}catch(NumberFormatException e) {
    					par1ICommandSender.sendChatToPlayer("AreaSave: You must input a number for the distance you want to claim.");
    				}
    			}
    		}*/
    	}
    }

    
    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, this.getPlayers()) : null;
    }

    protected String[] getPlayers()
    {
        return MinecraftServer.getServer().getAllUsernames();
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     */
    public boolean isUsernameIndex(int par1)
    {
        return false;
    }
}
