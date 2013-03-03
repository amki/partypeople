package ca.a01.b02.partypeople.server;

import java.util.Iterator;
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

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender par1iCommandSender) {
        return true;
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
        if (!(par1ICommandSender instanceof EntityPlayerMP)) {
            par1ICommandSender.sendChatToPlayer("You are not an MP player, you may not party.");
            return;
        }
        if (par2ArrayOfStr.length > 0) {
            if (par2ArrayOfStr[0].equalsIgnoreCase("create")) {
                this.handleCreate(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("invite") || par2ArrayOfStr[0].equalsIgnoreCase("inv")) {
                this.handleInvite(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("accept")) {
                this.handleAccept(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("decline")) {
                this.handleDecline(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("claim")) {
                this.handleClaim(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("quit")) {
                this.handleQuit(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("kick")) {
                this.handleKick(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("promote")) {
                this.handlePromote(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("list")) {
                this.handleList(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("debuginfo")) {
                this.handleDebugInfo(par1ICommandSender, par2ArrayOfStr);
            } else if (par2ArrayOfStr[0].equalsIgnoreCase("debug")) {
                this.handleDebug(par1ICommandSender, par2ArrayOfStr);
            }
        }
    }

    public void handleCreate(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        Party p = this.pHandler.createParty((EntityPlayerMP) par1ICommandSender);
    }

    public void handleInvite(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        if (((EntityPlayerMP) par1ICommandSender).username.equalsIgnoreCase(par2ArrayOfStr[1])) {
            par1ICommandSender.sendChatToPlayer("You can't invite yourself, sorry. :(");
            return;
        }
        // Find the party
        Party p = this.pHandler.getPartyByPlayer((EntityPlayerMP) par1ICommandSender);
        if (p == null) {
            par1ICommandSender.sendChatToPlayer("Party not found.");
            return;
        }
        // Find the invitee
        EntityPlayer invitee = ((EntityPlayerMP) par1ICommandSender).getServerForPlayer().getPlayerEntityByName(
                par2ArrayOfStr[1]);
        if (invitee == null || !(invitee instanceof EntityPlayerMP)) {
            par1ICommandSender.sendChatToPlayer("Player" + par2ArrayOfStr[1] + " could not be found.");
            return;
        }
        this.pHandler.invitePlayer((EntityPlayerMP) par1ICommandSender, (EntityPlayerMP) invitee);
    }

    public void handleAccept(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        this.pHandler.joinParty((EntityPlayerMP) par1ICommandSender);
    }

    public void handleDecline(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        this.pHandler.declineParty((EntityPlayerMP) par1ICommandSender);
    }

    public void handleClaim(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        this.pHandler.claimParty((EntityPlayerMP) par1ICommandSender);
    }

    public void handleQuit(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        this.pHandler.quitParty((EntityPlayerMP) par1ICommandSender);
    }

    public void handleKick(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        par1ICommandSender.sendChatToPlayer("KICK");
    }

    public void handlePromote(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        par1ICommandSender.sendChatToPlayer("PROMOTE");
    }

    public void handleList(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        Party p = this.pHandler.getPartyByPlayer((EntityPlayerMP) par1ICommandSender);
        if (p == null) {
            par1ICommandSender.sendChatToPlayer("Party not found.");
            return;
        }
        par1ICommandSender.sendChatToPlayer("Partylist:");
        par1ICommandSender.sendChatToPlayer("Leader: " + p.leader);
        par1ICommandSender.sendChatToPlayer("----------");
        Iterator<String> players = p.members.iterator();
        while (players.hasNext()) {
            par1ICommandSender.sendChatToPlayer(players.next());
        }
    }

    public void handleDebugInfo(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        par1ICommandSender.sendChatToPlayer("Your entityId is " + ((EntityPlayer) par1ICommandSender).entityId);
        Party p = this.pHandler.getPartyByPlayer((EntityPlayerMP) par1ICommandSender);
        if (p != null) {
            par1ICommandSender.sendChatToPlayer("Your partyId is " + p.id);
        }

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
