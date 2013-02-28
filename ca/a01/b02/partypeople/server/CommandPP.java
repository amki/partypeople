package ca.a01.b02.partypeople.server;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import ca.a01.b02.partypeople.Party;
import ca.a01.b02.partypeople.PartyHandler;
import ca.a01.b02.partypeople.PartyModel;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommandPP extends CommandBase {

    private final PartyModel   pModel;
    private final PartyHandler pHandler;

    public CommandPP(PartyModel pModel) {
        super();
        this.pModel = pModel;
        this.pHandler = new PartyHandler(this.pModel);
    }

    @Override
    public String getCommandName() {
        return "pp";
    }

    /**
     * Return the required permission level for this command.
     */
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender par1ICommandSender) {
        return "Usage";
    }

    @Override
    public void processCommand(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        if (par2ArrayOfStr.length > 0) {
            if (par2ArrayOfStr[0].equalsIgnoreCase("create")) {
                this.handleCreate(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("invite") || par2ArrayOfStr[0].equalsIgnoreCase("inv")) {
                this.handleInvite(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("accept")) {
                this.handleAccept(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("quit")) {
                this.handleQuit(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("kick")) {
                this.handleKick(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("promote")) {
                this.handlePromote(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("debug")) {
                this.handleDebug(par1ICommandSender, par2ArrayOfStr);
            }
        }
    }

    public void handleCreate(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        if (par1ICommandSender instanceof EntityPlayer) {
            Party p = this.pHandler.createParty((EntityPlayer) par1ICommandSender);
            par1ICommandSender.sendChatToPlayer("Party with ID " + p.id + " created.");
        } else {
            par1ICommandSender.sendChatToPlayer("You are not a player, you can't create a party.");
        }
    }

    public void handleInvite(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        // If the commander is a player
        if (par1ICommandSender instanceof EntityPlayer) {
            // If he is in a party
            if (((EntityPlayer) par1ICommandSender).partyId != 0) {
                // Find the invitee
                EntityPlayer invitee = ((EntityPlayerMP) par1ICommandSender).getServerForPlayer()
                        .getPlayerEntityByName(par2ArrayOfStr[1]);
                // If the invitee exists and is a player
                if (invitee != null && invitee instanceof EntityPlayer) {
                    Party p = this.pHandler.inviteParty((EntityPlayer) par1ICommandSender, invitee);
                    par1ICommandSender.sendChatToPlayer("Invited player " + invitee.username + ".");
                    invitee.sendChatToPlayer("Player " + ((EntityPlayer) par1ICommandSender).username
                            + " has invited you to party " + p.id + ". /pp accept/decline.");
                } else {
                    par1ICommandSender.sendChatToPlayer("The specified player " + par2ArrayOfStr[1]
                            + " could not be found.");
                }
            } else {
                par1ICommandSender.sendChatToPlayer("You are not in a party.");
            }
        } else {
            par1ICommandSender.sendChatToPlayer("You are not a player, you may not invite someone.");
        }
    }

    public void handleAccept(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        this.pHandler.joinParty((EntityPlayer) par1ICommandSender);
    }

    public void handleDecline(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        this.pHandler.declineParty((EntityPlayer) par1ICommandSender);
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
        if (par1ICommandSender instanceof EntityPlayer) {
            par1ICommandSender.sendChatToPlayer("Your name is: " + ((EntityPlayer) par1ICommandSender).username);
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
    @Override
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, this.getPlayers()) : null;
    }

    protected String[] getPlayers() {
        return MinecraftServer.getServer().getAllUsernames();
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     */
    @Override
    public boolean isUsernameIndex(int par1) {
        return false;
    }
}
