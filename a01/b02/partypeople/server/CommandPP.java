package a01.b02.partypeople.server;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.server.FMLServerHandler;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.NextTickListEntry;
import a01.b02.partypeople.Party;
import a01.b02.partypeople.PartyHandler;
import a01.b02.partypeople.PartyModel;
import a01.b02.partypeople.PartyPeople;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.terraingen.BiomeEvent.CreateDecorator;

public class CommandPP extends CommandBase {
	private PartyModel pModel;
	private PartyHandler pHandler;
	
	public CommandPP(PartyModel pModel) {
		super();
		this.pModel = pModel;
		this.pHandler = new PartyHandler(this.pModel);
	}
	
    public String getCommandName()
    {
        return "pp";
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
    	if(par2ArrayOfStr.length>0) {
    		if(par2ArrayOfStr[0].equalsIgnoreCase("create")) {
    			handleCreate(par1ICommandSender, par2ArrayOfStr);
    		} else if(par2ArrayOfStr[0].equalsIgnoreCase("invite") || par2ArrayOfStr[0].equalsIgnoreCase("inv")) {
    			handleInvite(par1ICommandSender, par2ArrayOfStr);
    		} else if(par2ArrayOfStr[0].equalsIgnoreCase("accept")) {
    			handleAccept(par1ICommandSender, par2ArrayOfStr);
    		} else if(par2ArrayOfStr[0].equalsIgnoreCase("quit")) {
    			handleQuit(par1ICommandSender, par2ArrayOfStr);
    		} else if(par2ArrayOfStr[0].equalsIgnoreCase("kick")) {
    			handleKick(par1ICommandSender, par2ArrayOfStr);
    		} else if(par2ArrayOfStr[0].equalsIgnoreCase("promote")) {
    			handlePromote(par1ICommandSender, par2ArrayOfStr);
    		} else if(par2ArrayOfStr[0].equalsIgnoreCase("debug")) {
    			handleDebug(par1ICommandSender, par2ArrayOfStr);
    		}
    	}
    }
    
    public void handleCreate(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
    	if(par1ICommandSender instanceof EntityPlayer) {
    		Party p = pHandler.createParty((EntityPlayer) par1ICommandSender);
			par1ICommandSender.sendChatToPlayer("Party with ID "+p.id+" created.");
		} else {
			par1ICommandSender.sendChatToPlayer("You are not a player, you can't create a party.");
		}
    }
    
    public void handleInvite(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
    	// If the commander is a player
		if(par1ICommandSender instanceof EntityPlayer) {
			// If he is in a party
			if(((EntityPlayer)par1ICommandSender).partyId != 0) {
				// Find the invitee
				EntityPlayer invitee = ((EntityPlayerMP)par1ICommandSender).getServerForPlayer().getPlayerEntityByName(par2ArrayOfStr[1]);
				// If the invitee exists and is a player
				if(invitee != null && invitee instanceof EntityPlayer) {
					Party p = pHandler.inviteParty((EntityPlayer) par1ICommandSender, invitee);
					par1ICommandSender.sendChatToPlayer("Invited player "+invitee.username+".");
					invitee.sendChatToPlayer("Player "+((EntityPlayer)par1ICommandSender).username+" has invited you to party "+p.id+". /pp accept/decline.");
				} else {
					par1ICommandSender.sendChatToPlayer("The specified player "+par2ArrayOfStr[1]+" could not be found.");
				}
			} else {
				par1ICommandSender.sendChatToPlayer("You are not in a party.");
			}
		} else {
			par1ICommandSender.sendChatToPlayer("You are not a player, you may not invite someone.");
		}
    }
    
    public void handleAccept(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
    	pHandler.joinParty((EntityPlayer) par1ICommandSender);
    }
    
    public void handleDecline(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
    	pHandler.declineParty((EntityPlayer) par1ICommandSender);
    }
    
    public void handleQuit(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
    	par1ICommandSender.sendChatToPlayer("QUIT");
    }
    
    public void handleKick(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
    	par1ICommandSender.sendChatToPlayer("KICK");
    }
    
    public void handlePromote(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
    	par1ICommandSender.sendChatToPlayer("PROMOTE");
    }
    
    public void handleDebug(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
		if(par1ICommandSender instanceof EntityPlayer) {
			par1ICommandSender.sendChatToPlayer("Your name is: "+((EntityPlayer)par1ICommandSender).username);
			String[] names = FMLCommonHandler.instance().getMinecraftServerInstance().getAllUsernames();
			par1ICommandSender.sendChatToPlayer("Userlist:");
			par1ICommandSender.sendChatToPlayer("----------");
			for (String name : names) {
				par1ICommandSender.sendChatToPlayer(name);
			}
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
